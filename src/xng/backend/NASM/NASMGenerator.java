package xng.backend.NASM;

import xng.XIR.*;
import xng.backend.ASMGenerator;

import java.util.*;

public class NASMGenerator {

    private Vector<NASMInst> instList = new Vector<>();

    private HashMap<Integer,NASMReg> regMap = new HashMap<>();
    private int[] availReg = {17,12,13,14,15,8,9,18,22,23};
    private int[] paramReg = {23,22,19,18,8,9};

    private Stack<Integer> curSavedReg = new Stack<>();
    private int[] calleeSavedReg = {17,12,13,14,15,21};
    private int[] callerSavedReg = {16,18,19,8,9,10,11,22,23};

    private int curAvailReg = 0;
    private int curStackOffset = 0;
    private int paramStackOffset = 0;
    private XIRProcInfo curProcInfo = null;
    private HashSet<Integer> visitFlag = new HashSet<>();
    private XIRInstAddr lastDivOpr1;
    private XIRInstAddr lastDivOpr2;
//    private XCFGNode nextJumpNode = null;

    private XCFG cfg;
    private ASMGenerator asm;

    private XIRInst.opType lastOp = XIRInst.opType.op_none;

    private boolean isWParam = false;

    public NASMGenerator(ASMGenerator _asm, XCFG _cfg){
        asm = _asm;
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        cfg.Proc.stream().map(globalNode -> globalNode.entryNode.name).forEach(asm::defGlobal);
        cfg.dataList.stream().map(data -> data.name).forEach(asm::defGlobal);
        asm.emitLine();
        for (XIRProcInfo i : cfg.Proc) {
            curProcInfo = i;
            visitXCFGNode(i.entryNode);
            curAvailReg = 0;
        }
        asm.emitLine();
//        cfg.dataList.forEach(this::visitXIRData);
        asm.defSection(ASMGenerator.sectType.DATA);
        cfg.dataList.stream().filter(i -> i.dType != XIRData.dataType.d_res).forEach(this::visitXIRData);
        asm.emitLine();
        asm.defSection(ASMGenerator.sectType.BSS);
        cfg.dataList.stream().filter(i -> i.dType == XIRData.dataType.d_res).forEach(this::visitXIRData);
    }

    private boolean isExp2(int t){
        return (t>0) && (t & (t-1)) == 0;
    }

    private void visitXIRData(XIRData data){
        asm.emitLine(false,data.name+':');
        StringBuilder sb = new StringBuilder();
        switch (data.dType){
            case d_num:{
                sb.append("dq\t");
                sb.append(data.val);
                break;
            }
            case d_res: {
                sb.append("resw\t");
                sb.append(data.size);
                break;
            }
            case d_str: {
                sb.append("db\t");
                data.strval.chars().forEach(i-> sb.append(String.format("%02X",i)).append("H, "));
                sb.append("00H");
                break;
            }
        }
        asm.emitLine(true,sb.toString());
    }

    private int getAvailReg() {
        if (curAvailReg>=availReg.length) curAvailReg -= availReg.length;
        return availReg[curAvailReg++];
    }

    private int getExp2Sup(int t){
        int r = 1;
        while ((r<<=1)<t);
        return r;
    }

    private void pushSavedReg() {
        for (int i:curSavedReg) {
            emitNASMInst(NASMOp.opType.PUSH,new NASMRegAddr(new NASMReg(i,NASMWordType.QWORD)),null);
        }
    }

    private void popSavedReg() {
        while (!curSavedReg.empty()) {
            int t = curSavedReg.pop();
            emitNASMInst(NASMOp.opType.POP,new NASMRegAddr(new NASMReg(t,NASMWordType.QWORD)),null);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        System.out.println("visit:"+node.nodeID);
        asm.defLabel(node.name);
        visitFlag.add(node.nodeID);
        if (!node.instList.isEmpty()) {
            XIRInst lastInst = node.instList.lastElement();
            if (lastInst.op == XIRInst.opType.op_jcc
                    || lastInst.op == XIRInst.opType.op_jmp){
                lastInst.oprList.firstElement().str = node.nextNode.firstElement().name;
//                nextJumpNode = node.nextNode.firstElement();
            }
        }
        if (curProcInfo != null) {
            XIRInst pi = new XIRInst(XIRInst.opType.op_push);
            pi.oprList.add(XIRInstAddr.newRegAddr(-1));
            visitXIRInst(pi);
            XIRInst mi = new XIRInst(XIRInst.opType.op_mov);
            mi.oprList.add(XIRInstAddr.newRegAddr(-1));
            mi.oprList.add(XIRInstAddr.newRegAddr(-3));
            visitXIRInst(mi);
            int stsz = curProcInfo.stackSize;
            curStackOffset = stsz > 8 ? Integer.max(getExp2Sup(stsz),32) : 0;
//            curStackOffset = 32;
            if (curStackOffset != 0){
                XIRInst si = new XIRInst(XIRInst.opType.op_sub);
                si.oprList.add(XIRInstAddr.newRegAddr(-3));
                si.oprList.add(XIRInstAddr.newImmAddr(curStackOffset, 0));
                visitXIRInst(si);
            }
            curProcInfo = null;
        }
        node.instList.forEach(this::visitXIRInst);
        if (node.nextNode.size()==1 && (node.instList.isEmpty()
                || node.instList.lastElement().op != XIRInst.opType.op_ret)
                && visitFlag.contains(node.nextNode.firstElement().nodeID)){
            emitNASMInst(XIRInst.opType.op_jmp,XIRInstAddr.newJumpAddr(node.nextNode.get(0)),null);
        } else {
            System.out.println("NASMGen:end");
        }
        Vector<XCFGNode> nextNode = node.nextNode;
        for (int i = nextNode.size()-1; i >= 0; --i) {
            XCFGNode xcfgNode = nextNode.get(i);
            if (i==1 && visitFlag.contains(xcfgNode.nodeID)) {
                emitNASMInst(XIRInst.opType.op_jmp,XIRInstAddr.newJumpAddr(xcfgNode),null);
            } else visitXCFGNode(xcfgNode);
        }
    }

    private NASMReg getNASMReg(int num, NASMWordType wt) {
        switch (num){
            case 0:
                return null;
            case -1:
                return new NASMReg(21,NASMWordType.QWORD);
            case -2: {
//                if (curAvailReg>0) {
//                    System.out.println("NASMGen:func return reg:"+curAvailReg);
//                    return new NASMReg(availReg[curAvailReg++],wt);
//                }
                return new NASMReg(16,wt);
            }
            case -3:
                return new NASMReg(20,NASMWordType.QWORD);
            case -4:
                return new NASMReg(16,wt);
            case -5:
                return new NASMReg(19,wt);
        }
        if (regMap.containsKey(num)) {
            NASMReg reg = regMap.get(num);
//            if (reg.wt != wt) {
//                NASMReg nr = new NASMReg(16,reg.wt);
//                emitNASMInst(NASMOp.opType.MOV,new NASMRegAddr(nr),new NASMRegAddr(reg));
//                emitNASMInst(NASMOp.opType.CDQE,null,null);
//                reg = nr;
//                reg.wt = NASMWordType.QWORD;
//            }
            return reg;
        }
        NASMReg reg = new NASMReg(getAvailReg(),wt);
        regMap.put(num,reg);
        return reg;
    }

    private NASMAddr getNASMAddr(XIRInstAddr opr, NASMWordType wt){
        if (opr == null) return null;
        switch (opr.type){
            case a_imm: {
                return new NASMImmAddr(opr.lit1);
            }
            case a_reg:{
                return new NASMRegAddr(getNASMReg(opr.lit1, wt));
            }
            case a_mem:{
                NASMReg base = null;
                NASMReg offset = null;
                int val = opr.lit4;
                if (opr.addr1==null) {
                    base = getNASMReg(opr.lit1,NASMWordType.QWORD);
                    offset = getNASMReg(opr.lit2,NASMWordType.QWORD);
                } else {
                    System.out.println("idx mem:"+opr.addr1+' '+opr.addr2);
                    switch (opr.addr1.type){
                        case a_label:
                        case a_stack:
                            break;
                        case a_static: {
//                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
//                            emitNASMInst(XIRInst.opType.op_lea,tmp,opr.addr1);
//                            base = getNASMReg(tmp.lit1,NASMWordType.QWORD);
                            base = new NASMReg(opr.addr1.str);
                            break;
                        }
                        case a_mem:{
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            emitNASMInst(XIRInst.opType.op_mov,tmp,opr.addr1);
                            base = getNASMReg(tmp.lit1,NASMWordType.QWORD);
                            break;
                        }
                        case a_reg:
                            base = getNASMReg(opr.addr1.lit1,NASMWordType.QWORD);
                            break;
                        case a_imm:
                            base = null;
                            val += opr.addr1.lit1;
                            break;
                    }
                    if (opr.addr2 != null)
                        switch (opr.addr2.type){
                        case a_label:
                        case a_stack:
                            break;
                        case a_static: {
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            emitNASMInst(XIRInst.opType.op_mov,tmp,opr.addr2);
                            offset = getNASMReg(tmp.lit1,NASMWordType.QWORD);
                            break;
                        }
                        case a_mem: {
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            emitNASMInst(XIRInst.opType.op_mov,tmp,opr.addr2);
                            offset = getNASMReg(tmp.lit1,NASMWordType.QWORD);
                            break;
                        }
                        case a_reg:
                            offset = getNASMReg(opr.addr2.lit1,NASMWordType.QWORD);
                            break;
                        case a_imm:
                            offset = null;
                            val += opr.lit3 * opr.addr2.lit1;
                            break;
                    }
                }
                return new NASMMemAddr(wt,base,offset,opr.lit3, val);
            }
            case a_label:{
//                if (nextJumpNode == null)
                return new NASMLabelAddr(opr.str);
//                String label = nextJumpNode.name;
//                nextJumpNode = null;
//                return new NASMLabelAddr(label);
//                return new NASMLabelAddr(getLabel(cfg.getNode(opr.lit1)));
            }
            case a_static:{
                return new NASMLabelAddr(opr.str);
//                return new NASMMemAddr(wt,new NASMReg(opr.str),null,0,0);
            }
        }
        return null;
    }

    private NASMOp.opType getNASMOp(XIRInst.opType op){
        switch (op){
            case op_mov:
                return NASMOp.opType.MOV;
            case op_lea:
                return NASMOp.opType.LEA;
            case op_jcc:
                return NASMOp.newJccRevOp(lastOp);
            case op_jmp:
                return NASMOp.opType.JMP;
            case op_add:
                return NASMOp.opType.ADD;
            case op_and:
                return NASMOp.opType.AND;
            case op_eq:
            case op_ne:
            case op_ge:
            case op_gt:
            case op_le:
            case op_lt:
                return NASMOp.opType.CMP;
            case op_mult:
                return NASMOp.opType.IMUL;
            case op_or:
                return NASMOp.opType.OR;
            case op_dec:
                return NASMOp.opType.DEC;
            case op_inc:
                return NASMOp.opType.INC;
            case op_neg:
                return NASMOp.opType.NEG;
            case op_xor:
                return NASMOp.opType.XOR;
            case op_call:
                return NASMOp.opType.CALL;
            case op_div:
                return NASMOp.opType.IDIV;
            case op_not:
                return NASMOp.opType.NOT;
            case op_push:
                return NASMOp.opType.PUSH;
            case op_ret:
                return NASMOp.opType.RET;
            case op_shl:
                return NASMOp.opType.SAL;
            case op_shr:
                return NASMOp.opType.SAR;
            case op_sub:
                return NASMOp.opType.SUB;
            case op_int:
                return NASMOp.opType.INT;
            case op_pop:
                return NASMOp.opType.POP;
        }
        System.out.println("NASMGen:error:"+op);
        return NASMOp.opType._NONE;
    }

    private void emitNASMInst(NASMInst inst) {
        System.out.println("NASMGen:emitNASMInst:"+inst);
        instList.add(inst);
//        if (inst.opr1 instanceof NASMRegAddr) {
//            curSavedReg.add(((NASMRegAddr)inst.opr1).reg.regId);
//        }
        asm.emitText(inst.toString());
    }

    private void emitNASMInst(NASMOp.opType op, NASMAddr opr1, NASMAddr opr2) {
        emitNASMInst(new NASMInst(op,opr1,opr2));
    }

    private void emitNASMInst(XIRInst.opType op, XIRInstAddr opr1, XIRInstAddr opr2) {
        System.out.println("NASMGen:emitXIRInst:"+op+' '+opr1+' '+opr2);
        switch (op) {
            case op_div: {
                if (opr2 == null) break;
                if (opr2.isConst()){
                    int t = opr2.getConst();
                    if (t == 0) {
                        System.out.println("NASMGen:error:div zero");
                        return;
                    }
                    if (isExp2(t)){
                        int i = 0;
                        while ((t >> i)!=1) ++i;
                        System.out.println("NASMGen:div:exp2 "+i);
                        if (i!=0){
                            emitNASMInst(XIRInst.opType.op_shr,opr1,XIRInstAddr.newImmAddr(i,0));
                        }
                        return;
                    }
                    XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                    emitNASMInst(XIRInst.opType.op_mov,tmp_addr,opr2);
                    opr2 = tmp_addr;
                }
//                if (lastDivOpr1 == opr1 && lastDivOpr2 == opr2) {
//
//                } else
                    {
                    emitNASMInst(XIRInst.opType.op_mov,XIRInstAddr.newRegAddr(-4),opr1);
                    emitNASMInst(NASMOp.opType.CQO,null,null);
                    emitNASMInst(XIRInst.opType.op_div,opr2,null);
                }
                emitNASMInst(XIRInst.opType.op_mov,opr1,XIRInstAddr.newRegAddr(-4));
                lastDivOpr1 = opr1;
                lastDivOpr2 = opr2;
                return;
            }
            case op_mod: {
                if (opr2 == null) break;
                if (opr2.isConst()){
                    int t = opr2.getConst();
                    if (t == 0) {
                        System.out.println("NASMGen:error:mod zero");
                        return;
                    }
                    if (isExp2(t)){
                        int i = 0;
                        while ((t >> i)!=1) ++i;
                        System.out.println("NASMGen:mod:exp2 "+i);
                        if (i==0){
                            emitNASMInst(XIRInst.opType.op_mov,opr1,XIRInstAddr.newImmAddr(0,0));
                        } else {
                            emitNASMInst(XIRInst.opType.op_and,opr1,XIRInstAddr.newImmAddr((1<<i)-1,0));
                        }
                        return;
                    }
                    XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                    emitNASMInst(XIRInst.opType.op_mov,tmp_addr,opr2);
                    opr2 = tmp_addr;
                }
//                if (lastDivOpr1 == opr1 && lastDivOpr2 == opr2) {
//
//                } else
                    {
                    emitNASMInst(XIRInst.opType.op_mov,XIRInstAddr.newRegAddr(-4),opr1);
                    emitNASMInst(NASMOp.opType.CQO,null,null);
                    emitNASMInst(XIRInst.opType.op_div,opr2,null);
                }
                emitNASMInst(XIRInst.opType.op_mov,opr1,XIRInstAddr.newRegAddr(-5));
                lastDivOpr1 = opr1;
                lastDivOpr2 = opr2;
                return;
            }
            case op_mult: {
                if (opr2.isConst()){
                    int t = opr2.getConst();
                    if (t == 0) {
                        emitNASMInst(XIRInst.opType.op_mov,opr1,XIRInstAddr.newImmAddr(0,0));
                        return;
                    }
                    if (isExp2(t)){
                        int i = 0;
                        while ((t >> i)!=1) ++i;
                        System.out.println("NASMGen:mult:exp2 "+i);
                        if (i<3){
                            for (int j = 0;j < i;++j){
                                emitNASMInst(XIRInst.opType.op_add,opr1,opr1);
                            }
                            return;
                        } else {
                            emitNASMInst(XIRInst.opType.op_shl,opr1,XIRInstAddr.newImmAddr(i,0));
                        }
                    }
                }
                if (opr1.type != XIRInstAddr.addrType.a_reg) {
                    XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                    emitNASMInst(XIRInst.opType.op_mov,tmp_addr,opr1);
                    emitNASMInst(XIRInst.opType.op_mult,tmp_addr,opr2);
                    emitNASMInst(XIRInst.opType.op_mov,opr1,tmp_addr);
                    return;
                }
                break;
            }
            case op_not:
            case op_neg: {
                emitNASMInst(XIRInst.opType.op_mov,opr1,opr2);
                opr2 = null;
                break;
            }
        }
        if (opr1 != null && opr2 != null
                && (opr1.type == XIRInstAddr.addrType.a_mem || opr1.type == XIRInstAddr.addrType.a_static)
                && (opr2.type == XIRInstAddr.addrType.a_mem || opr2.type == XIRInstAddr.addrType.a_static)) {
            XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
            emitNASMInst(XIRInst.opType.op_mov, tmp_addr, opr2);
            emitNASMInst(op,opr1,tmp_addr);
            return;
        }
        NASMWordType wt1, wt2;
        switch (op) {
            case op_lea:
                wt1 = NASMWordType.QWORD;
                wt2 = NASMWordType.WORD;
                break;
            default:
                wt1 = NASMWordType.QWORD;
                wt2 = NASMWordType.QWORD;
        }
        NASMAddr a2 = getNASMAddr(opr2,wt2);
        NASMAddr a1 = getNASMAddr(opr1,wt1);
        if (op == XIRInst.opType.op_mov && Objects.equals(a1, a2)) return;
        switch (op){
            case op_wpara:
                if (!isWParam) {
                    isWParam = true;
                    int t = Integer.min(opr2.lit1,6);
                    for (int i=0;i<t;++i) {
                        curSavedReg.push(paramReg[i]);
                    }
                    pushSavedReg();
                }
                if (opr2.lit1 < 6){
//                    curSavedReg.push(paramReg[opr2.lit1]);
//                    emitNASMInst(NASMOp.opType.PUSH,new NASMRegAddr(new NASMReg(paramReg[opr2.lit1],NASMWordType.QWORD)),null);
                    emitNASMInst(NASMOp.opType.MOV,new NASMRegAddr(new NASMReg(paramReg[opr2.lit1],NASMWordType.QWORD)),a1);
                } else {
                    emitNASMInst(NASMOp.opType.PUSH,a1,null);
                    paramStackOffset += NASMWordType.getWordSize(wt1);
                }
                break;
            case op_rpara: {
                int para_idx = opr2.lit1;
                if (para_idx >= 6) {
                    opr1.lit4 = 8+(para_idx-5)*8;
                } else {
                    emitNASMInst(NASMOp.opType.MOV, a1, new NASMRegAddr(new NASMReg(paramReg[para_idx], NASMWordType.QWORD)));
                }
                break;
            }
            case op_ret: {
                if (curStackOffset != 0) {
                    emitNASMInst(new NASMInst(NASMOp.opType.ADD, new NASMRegAddr(new NASMReg(20, NASMWordType.QWORD)), new NASMImmAddr(curStackOffset)));
                }
                emitNASMInst(new NASMInst(NASMOp.opType.POP, new NASMRegAddr(new NASMReg(21,NASMWordType.QWORD)), null));
                emitNASMInst(new NASMInst(getNASMOp(op), a1, a2));
                break;
            }
            case op_shl: {
                if (opr2.type != XIRInstAddr.addrType.a_imm){
                    emitNASMInst(NASMOp.opType.MOV,new NASMRegAddr(new NASMReg(18,NASMWordType.QWORD)),a2);
                    emitNASMInst(NASMOp.opType.SAL,a1,new NASMRegAddr(new NASMReg(18,NASMWordType.BYTE)));
                } else emitNASMInst(new NASMInst(getNASMOp(op),a1,a2));
                return;
            }
            case op_shr: {
                if (opr2.type != XIRInstAddr.addrType.a_imm){
                    emitNASMInst(NASMOp.opType.MOV,new NASMRegAddr(new NASMReg(18,NASMWordType.QWORD)),a2);
                    emitNASMInst(NASMOp.opType.SAR,a1,new NASMRegAddr(new NASMReg(18,NASMWordType.BYTE)));
                } else emitNASMInst(new NASMInst(getNASMOp(op),a1,a2));
                return;
            }
            default:
                emitNASMInst(new NASMInst(getNASMOp(op),a1,a2));
                if (op==XIRInst.opType.op_call){
                    if (paramStackOffset > 0){
                        emitNASMInst(NASMOp.opType.ADD,new NASMRegAddr(new NASMReg(20,NASMWordType.QWORD)),new NASMImmAddr(paramStackOffset));
                        paramStackOffset = 0;
                    }
                    popSavedReg();
                    isWParam = false;
                }
        }
    }

    private void visitXIRInst(XIRInst inst){
//        System.out.println("DBGinst:"+inst);
        if (inst.oprList.size()==3) {
            XIRInstAddr opr1 = inst.oprList.get(0);
            XIRInstAddr opr2 = inst.oprList.get(1);
            XIRInstAddr opr3 = inst.oprList.get(2);
            if (opr1.equals(opr2)) {
                emitNASMInst(inst.op,opr1,opr3);
            } else if (opr1.equals(opr3)){
                if (XIRInst.isOpCommute(inst.op)){
                    emitNASMInst(inst.op,opr1,opr2);
                } else {
                    XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                    emitNASMInst(XIRInst.opType.op_mov,tmp_addr,opr2);
                    emitNASMInst(inst.op,tmp_addr,opr3);
                    emitNASMInst(XIRInst.opType.op_mov,opr1,tmp_addr);
                }
            } else {
//                if (inst.op == XIRInst.opType.op_add
//                        && opr2.type == XIRInstAddr.addrType.a_reg
//                        && opr3.type == XIRInstAddr.addrType.a_reg) {
//                    emitNASMInst(XIRInst.opType.op_lea,opr1,XIRInstAddr.newMemAddr(opr2,opr3,1,0));
//                    return;
//                }
                emitNASMInst(XIRInst.opType.op_mov,opr1,opr2);
                emitNASMInst(inst.op,opr1,opr3);
            }
        } else if (inst.oprList.size()==2) {
            XIRInstAddr opr1 = inst.oprList.get(0);
            XIRInstAddr opr2 = inst.oprList.get(1);
            emitNASMInst(inst.op,opr1,opr2);
        } else if (inst.oprList.size()==1) {
            XIRInstAddr opr1 = inst.oprList.get(0);
            emitNASMInst(inst.op,opr1,null);
        } else {
            emitNASMInst(inst.op,null,null);
        }
        lastOp = inst.op;
//        curAvailReg = 0;
    }
}

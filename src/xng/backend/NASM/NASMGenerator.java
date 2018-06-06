package xng.backend.NASM;

import xng.XIR.*;
import xng.backend.ASMGenerator;

import java.util.*;

public class NASMGenerator {

    private Vector<NASMInst> instList = new Vector<>();

//    private HashMap<Integer,NASMReg> regMap = new HashMap<>();
    private int[] availReg = {17,12,13,14,15};
    private int[] tempReg = {10,11};
    private int[] tempMap = {0,0};
    private int[] paramReg = {23,22,19,18,8,9};

    private Stack<Integer> curCalleeSavedReg = new Stack<>();
    private Stack<Integer> curCallerSavedReg = new Stack<>();
    private int[] calleeSavedReg = {17,12,13,14,15,21};
    private int[] callerSavedReg = {16,18,19,8,9,10,11,22,23};

    private int curAvailReg = 0;
    private int curTempReg = 0;
    private int curStackOffset = 0;
    private int paramStackOffset = 0;
    private XIRProcInfo curProcInfo = null;
    private HashSet<Integer> visitFlag = new HashSet<>();
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
        asm.defSection(ASMGenerator.sectType.DATA);
        cfg.dataList.stream().filter(i -> i.dType != XIRData.dataType.d_res).forEach(this::visitXIRData);
        asm.emitLine();
        asm.defSection(ASMGenerator.sectType.BSS);
        cfg.dataList.stream().filter(i -> i.dType == XIRData.dataType.d_res).forEach(this::visitXIRData);
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
        if (++curAvailReg>=availReg.length) curAvailReg -= availReg.length;
        return availReg[curAvailReg];
    }

    private int getExp2Sup(int t){
        int r = 1;
        while ((r<<=1)<t);
        return r;
    }

    private void pushCallerSavedReg() {
        for (int i:curCallerSavedReg) {
            emitNASMInst(NASMOp.opType.PUSH,new NASMRegAddr(new NASMReg(i,NASMWordType.QWORD)),null);
        }
    }

    private void popCallerSavedReg() {
        while (!curCallerSavedReg.empty()) {
            int t = curCallerSavedReg.pop();
            emitNASMInst(NASMOp.opType.POP,new NASMRegAddr(new NASMReg(t,NASMWordType.QWORD)),null);
        }
    }

    private void pushCalleeSavedReg() {
        for (int i:curCalleeSavedReg) {
            emitNASMInst(NASMOp.opType.PUSH,new NASMRegAddr(new NASMReg(i,NASMWordType.QWORD)),null);
        }
    }

    private void popCalleeSavedReg() {
        while (!curCalleeSavedReg.empty()) {
            int t = curCalleeSavedReg.pop();
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
            if (curProcInfo.isCallee && curProcInfo.paramCount<=6) {
                for (int t : calleeSavedReg) {
                    curCalleeSavedReg.add(t);
                }
            } else curCalleeSavedReg.add(21);
//            curCalleeSavedReg.add(21);
            pushCalleeSavedReg();
//            emitNASMInst(NASMOp.opType.PUSH,new NASMRegAddr(new NASMReg(21,NASMWordType.QWORD)),null);
            emitNASMInst(NASMOp.opType.MOV,new NASMRegAddr(new NASMReg(21,NASMWordType.QWORD)),
                    new NASMRegAddr(new NASMReg(20,NASMWordType.QWORD)));
            int stsz = curProcInfo.stackSize;
            curStackOffset = stsz > 8 ? Integer.max(getExp2Sup(stsz),32) : 0;
//            curStackOffset = 32;
            if (curStackOffset != 0){
                emitNASMInst(NASMOp.opType.SUB,new NASMRegAddr(new NASMReg(20,NASMWordType.QWORD)),
                        new NASMImmAddr(curStackOffset));
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

    private NASMReg getNASMReg(XIRInstAddr opr, NASMWordType wt) {
        int num = opr.lit1;
        switch (num){
            case 0:
                return null;
            case -1:
                return new NASMReg(21,NASMWordType.QWORD);
            case -2: {
                return new NASMReg(16,wt);
            }
            case -3:
                return new NASMReg(20,NASMWordType.QWORD);
            case -4:
                return new NASMReg(16,wt);
            case -5:
                return new NASMReg(19,wt);
            case -6:
                return new NASMReg(availReg[opr.lit2],wt);
        }
        for (int i=0;i<tempMap.length;++i) {
            if (tempMap[i]==num) {
                return new NASMReg(tempReg[i],wt);
            }
        }
        if (curTempReg==tempReg.length) curTempReg = 0;
        tempMap[curTempReg] = num;
        return new NASMReg(tempReg[curTempReg++],wt);
//        if (regMap.containsKey(num)) {
//            NASMReg reg = regMap.get(num);
//            if (reg.wt != wt) {
//                NASMReg nr = new NASMReg(16,reg.wt);
//                emitNASMInst(NASMOp.opType.MOV,new NASMRegAddr(nr),new NASMRegAddr(reg));
//                emitNASMInst(NASMOp.opType.CDQE,null,null);
//                reg = nr;
//                reg.wt = NASMWordType.QWORD;
//            }
//            return reg;
//        }
//        NASMReg reg = new NASMReg(getAvailReg(),wt);
//        regMap.put(num,reg);
//        return reg;
    }

    private NASMAddr getNASMAddr(XIRInstAddr opr, NASMWordType wt){
        if (opr == null) return null;
        switch (opr.type){
            case a_imm: {
                return new NASMImmAddr(opr.lit1);
            }
            case a_reg:{
                return new NASMRegAddr(getNASMReg(opr, wt));
            }
            case a_mem:{
                NASMReg base = null;
                NASMReg offset = null;
                int val = opr.lit4;
                if (opr.addr1==null) {
                    base = new NASMReg(21,NASMWordType.QWORD);
                    offset = null;
                } else {
                    System.out.println("idx mem:"+opr.addr1+' '+opr.addr2);
                    switch (opr.addr1.type){
                        case a_static: {
                            base = new NASMReg(opr.addr1.str);
                            break;
                        }
                        case a_reg:
                            base = getNASMReg(opr.addr1,NASMWordType.QWORD);
                            break;
                        case a_imm:
                            base = null;
                            val += opr.addr1.lit1;
                            break;
                    }
                    switch (opr.addr2.type){
                        case a_reg:
                            offset = getNASMReg(opr.addr2,NASMWordType.QWORD);
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
                return new NASMLabelAddr(opr.str);
            }
            case a_static:{
                return new NASMLabelAddr(opr.str);
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
            case op_eq:
            case op_ne:
            case op_ge:
            case op_gt:
            case op_le:
            case op_lt: {
                if (opr1.type == XIRInstAddr.addrType.a_imm) {
                    emitNASMInst(op,opr2,opr1);
                    return;
                }
                break;
            }
            case op_div: {
                if (opr2 == null) break;
                emitNASMInst(XIRInst.opType.op_mov,XIRInstAddr.newRegAddr(-4),opr1);
                emitNASMInst(NASMOp.opType.CQO,null,null);
                emitNASMInst(XIRInst.opType.op_div,opr2,null);
                emitNASMInst(XIRInst.opType.op_mov,opr1,XIRInstAddr.newRegAddr(-4));
                return;
            }
            case op_mod: {
                if (opr2 == null) break;
                emitNASMInst(XIRInst.opType.op_mov,XIRInstAddr.newRegAddr(-4),opr1);
                emitNASMInst(NASMOp.opType.CQO,null,null);
                emitNASMInst(XIRInst.opType.op_div,opr2,null);
                emitNASMInst(XIRInst.opType.op_mov,opr1,XIRInstAddr.newRegAddr(-5));
                return;
            }
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
//                    for (int t: availReg) {
//                        curCallerSavedReg.push(t);
//                    }
//                    curCallerSavedReg.push(10);
//                    curCallerSavedReg.push(11);
//                    pushCallerSavedReg();
                }
                if (opr2.lit1 < 6){
                    emitNASMInst(NASMOp.opType.MOV,new NASMRegAddr(new NASMReg(paramReg[opr2.lit1],NASMWordType.QWORD)),a1);
                } else {
                    emitNASMInst(NASMOp.opType.PUSH,a1,null);
                    paramStackOffset += NASMWordType.getWordSize(wt1);
                }
                break;
            case op_rpara: {
                int para_idx = opr2.lit1;
                if (para_idx >= 6) {
                    if (opr1.type == XIRInstAddr.addrType.a_reg) {
                        emitNASMInst(XIRInst.opType.op_mov,opr1,new XIRInstAddr(XIRInstAddr.addrType.a_mem,-1,0,0,8+(para_idx-5)*8));
                    } else opr1.lit4 = 8+(para_idx-5)*8;
                } else {
                    emitNASMInst(NASMOp.opType.MOV, a1, new NASMRegAddr(new NASMReg(paramReg[para_idx], NASMWordType.QWORD)));
                }
                break;
            }
            case op_ret: {
                if (curStackOffset != 0) {
                    emitNASMInst(new NASMInst(NASMOp.opType.ADD, new NASMRegAddr(new NASMReg(20, NASMWordType.QWORD)), new NASMImmAddr(curStackOffset)));
                }
                popCalleeSavedReg();
//                emitNASMInst(new NASMInst(NASMOp.opType.POP, new NASMRegAddr(new NASMReg(21,NASMWordType.QWORD)), null));
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
//                    popCallerSavedReg();
                    isWParam = false;
                }
        }
    }

    private void visitXIRInst(XIRInst inst){
//        System.out.println("DBGinst:"+inst);
        if (inst.oprList.size()==2) {
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

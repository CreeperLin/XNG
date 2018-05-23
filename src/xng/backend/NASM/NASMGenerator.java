package xng.backend.NASM;

import xng.XIR.*;
import xng.backend.ASMGenerator;

import java.util.*;

public class NASMGenerator {

    private Vector<NASMInst> instList = new Vector<>();

    private HashMap<Integer,NASMReg> regMap = new HashMap<>();
    private int[] availReg = {17,18,19,1,2,3,4,5,6,7,16};
    private int[] paramReg = {22,23,19,18,8,9};
    private int curAvailReg = 0;
    private int curParamReg = 0;
    private HashSet<Integer> visitFlag = new HashSet<>();
//    private XCFGNode nextJumpNode = null;

    private XCFG cfg;
    private ASMGenerator asm;

    private XIRInst.opType lastOp = XIRInst.opType.op_none;

    public NASMGenerator(ASMGenerator _asm, XCFG _cfg){
        asm = _asm;
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        cfg.globalNodes.stream().map(globalNode -> globalNode.name).forEach(asm::defGlobal);
        cfg.dataList.stream().map(data -> data.name).forEach(asm::defGlobal);
        asm.emitLine();
        cfg.globalNodes.forEach(this::visitXCFGNode);
        asm.emitLine();
//        cfg.dataList.forEach(this::visitXIRData);
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
                sb.append("dw\t");
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
                sb.append(data.strval);
                break;
            }
        }
        asm.emitLine(true,sb.toString());
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        System.out.println("visit:"+node);
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
            visitXCFGNode(xcfgNode);
        }
    }

    private NASMReg getNASMReg(int num, NASMWordType wt) {
        switch (num){
            case 0:
                return null;
            case -1:
                return new NASMReg(NASMReg.regType.RBP);
            case -2: {
                if (curAvailReg>0) {
                    System.out.println("NASMGen:func return reg:"+curAvailReg);
                    return new NASMReg(availReg[curAvailReg++],wt);
                }
                return new NASMReg(16,wt);
            }
            case -3:
                return new NASMReg(NASMReg.regType.RSP);
//            case -4:
        }
        if (regMap.containsKey(num)) return regMap.get(num);
        NASMReg reg = new NASMReg(availReg[curAvailReg++],wt);
        regMap.put(num,reg);
        return reg;
    }

    private NASMAddr getNASMAddr(XIRInstAddr opr){
        if (opr == null) return null;
        switch (opr.type){
            case a_imm: {
                return new NASMImmAddr(opr.lit1);
            }
            case a_reg:{
                return new NASMRegAddr(getNASMReg(opr.lit1,NASMWordType.DWORD));
            }
            case a_mem:{
                NASMWordType wt = NASMWordType.DWORD;
                NASMReg base = null;
                NASMReg offset = null;
                int val = opr.lit4;
                if (opr.addr1==null) {
                    base = getNASMReg(opr.lit1,NASMWordType.DWORD);
                    offset = getNASMReg(opr.lit2,NASMWordType.DWORD);
                } else {
                    switch (opr.addr1.type){
                        case a_label:
                        case a_stack:
                            break;
                        case a_static: {
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            emitNASMInst(XIRInst.opType.op_lea,tmp,opr.addr1);
                            base = getNASMReg(tmp.lit1,NASMWordType.DWORD);
                            break;
                        }
                        case a_mem:{
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            emitNASMInst(XIRInst.opType.op_mov,tmp,opr.addr1);
                            base = getNASMReg(tmp.lit1,NASMWordType.DWORD);
                            break;
                        }
                        case a_reg:
                            base = getNASMReg(opr.addr1.lit1,NASMWordType.DWORD);
                            break;
                        case a_imm:
                            base = null;
                            val += opr.addr1.lit1;
                            break;
                    }
                    switch (opr.addr2.type){
                        case a_label:
                        case a_stack:
                            break;
                        case a_static: {
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            emitNASMInst(XIRInst.opType.op_lea,tmp,opr.addr2);
                            offset = getNASMReg(tmp.lit1,NASMWordType.DWORD);
                            break;
                        }
                        case a_mem:{
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            emitNASMInst(XIRInst.opType.op_lea,tmp,opr.addr2);
                            offset = getNASMReg(tmp.lit1,NASMWordType.DWORD);
                            break;
                        }
                        case a_reg:
                            offset = getNASMReg(opr.addr2.lit1,NASMWordType.DWORD);
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
                return new NASMMemAddr(NASMWordType.DWORD,new NASMReg(opr.str),null,0,0);
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
            case op_none:
                return NASMOp.opType._NONE;
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
            case op_mod:
            case op_not:
                return NASMOp.opType.NOT;
            case op_pos:
                return NASMOp.opType._NONE;
            case op_push:
                return NASMOp.opType.PUSH;
            case op_ret:
                return NASMOp.opType.RET;
            case op_shl:
                return NASMOp.opType.SHL;
            case op_shr:
                return NASMOp.opType.SHR;
            case op_sub:
                return NASMOp.opType.SUB;
            case op_int:
                return NASMOp.opType.INT;
            case op_pop:
                return NASMOp.opType.POP;
        }
        System.out.println("NASMGen:error");
        return NASMOp.opType._NONE;
    }

    private void emitNASMInst(XIRInst.opType op, XIRInstAddr opr1, XIRInstAddr opr2) {
        NASMAddr a2 = getNASMAddr(opr2);
        NASMAddr a1 = getNASMAddr(opr1);
        if (op == XIRInst.opType.op_mov && a1.equals(a2)) return;
        NASMInst inst;
        switch (op){
            case op_wpara:
                if (curParamReg < 6){
                    inst = new NASMInst(NASMOp.opType.MOV,new NASMRegAddr(new NASMReg(paramReg[curParamReg++],NASMWordType.DWORD)),a1);
                } else {
                    inst = new NASMInst(NASMOp.opType.PUSH,a1,null);
                    ++curParamReg;
                }
                break;
            case op_rpara: {
                int para_idx = opr2.lit1;
                if (para_idx >= 6) {
                    inst = new NASMInst(NASMOp.opType.POP, a1, null);
                } else {
                    inst = new NASMInst(NASMOp.opType.MOV, a1, new NASMRegAddr(new NASMReg(paramReg[para_idx], NASMWordType.DWORD)));
                }
                break;
            }
            case op_ret: {
                NASMInst pop_inst = new NASMInst(NASMOp.opType.POP, new NASMRegAddr(NASMReg.regType.RBP), null);
                asm.emitText(pop_inst.toString());
                inst = new NASMInst(getNASMOp(op), a1, a2);
                break;
            }
            default:
                inst = new NASMInst(getNASMOp(op),a1,a2);
        }
        System.out.println("NASMGen:emitInst:"+inst);
        instList.add(inst);
        asm.emitText(inst.toString());
    }

    private void visitXIRInst(XIRInst inst){
        if (inst.oprList.size()==3){
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
                emitNASMInst(XIRInst.opType.op_mov,opr1,opr2);
                emitNASMInst(inst.op,opr1,opr3);
            }
        } else if (inst.oprList.size()==2) {
            XIRInstAddr opr1 = inst.oprList.get(0);
            XIRInstAddr opr2 = inst.oprList.get(1);
            if (opr1.type == XIRInstAddr.addrType.a_mem && opr2.type == XIRInstAddr.addrType.a_mem){
                XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                emitNASMInst(XIRInst.opType.op_mov,tmp_addr,opr1);
                emitNASMInst(inst.op,opr2,tmp_addr);
            } else {
                emitNASMInst(inst.op,opr1,opr2);
            }
        } else if (inst.oprList.size()==1) {
            XIRInstAddr opr1 = inst.oprList.get(0);
            emitNASMInst(inst.op,opr1,null);
        } else {
            emitNASMInst(inst.op,null,null);
        }
        lastOp = inst.op;
        curAvailReg = (inst.op == XIRInst.opType.op_call) ? 1 : 0;
        curParamReg = (inst.op == XIRInst.opType.op_call) ? 0 : curParamReg;
    }
}

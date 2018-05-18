package xng.backend.NASM;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;
import xng.backend.ASMGenerator;

import java.util.*;

public class NASMGenerator {

    private Vector<NASMInst> instList = new Vector<>();

    private HashMap<Integer,NASMReg> regMap = new HashMap<>();
    private Vector<NASMReg> availReg = new Vector<>();
    private int curAvailReg = 0;
    private HashSet<Integer> visitFlag = new HashSet<>();

    private XCFG cfg;
    private ASMGenerator asm;

    private XIRInst.opType lastOp = XIRInst.opType.op_none;

    public NASMGenerator(ASMGenerator _asm, XCFG _cfg){
        asm = _asm;
        cfg = _cfg;
        availReg.add(new NASMReg(NASMReg.regType.RAX));
        availReg.add(new NASMReg(NASMReg.regType.RBX));
        availReg.add(new NASMReg(NASMReg.regType.RCX));
        availReg.add(new NASMReg(NASMReg.regType.RDX));
        availReg.add(new NASMReg(NASMReg.regType.R8));
        availReg.add(new NASMReg(NASMReg.regType.R9));
        availReg.add(new NASMReg(NASMReg.regType.R10));
        availReg.add(new NASMReg(NASMReg.regType.R11));
        availReg.add(new NASMReg(NASMReg.regType.R12));
        availReg.add(new NASMReg(NASMReg.regType.R13));
        availReg.add(new NASMReg(NASMReg.regType.R14));
        availReg.add(new NASMReg(NASMReg.regType.R15));
//        availReg.add(new NASMRegASMReg.regType.EAX);
        visitXCFG();
    }

    private void visitXCFG(){
        cfg.globalNodes.stream().map(globalNode -> globalNode.name).forEach(asm::defGlobal);
        asm.emitLine();
        for (XCFGNode node : cfg.globalNodes) {
            XIRInst i1 = new XIRInst(XIRInst.opType.op_mov);
            i1.oprList.add(XIRInstAddr.newRegAddr(-1));
            i1.oprList.add(XIRInstAddr.newRegAddr(-3));
            node.instList.insertElementAt(i1,0);
            XIRInst i2 = new XIRInst(XIRInst.opType.op_push);
            i2.oprList.add(XIRInstAddr.newRegAddr(-1));
            node.instList.insertElementAt(i2,0);
            visitXCFGNode(node);
        }
    }

    private void appendXCFGNode(XCFGNode node){
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        node.instList.forEach(this::visitXIRInst);
        if (node.nextNode.size()==2){
            appendXCFGNode(node.nextNode.get(1));
//            visitXCFGNode(node.nextNode.get(0));
        } else if (node.nextNode.size()==1){
            emitNASMInst(XIRInst.opType.op_jmp,XIRInstAddr.newJumpAddr(node.nextNode.get(0)),null);
//            visitXCFGNode(node.nextNode.get(0));
        } else {
            System.out.println("NASMGen:end");
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        System.out.println("visit:"+node.nodeID);
        if (visitFlag.contains(node.nodeID)) return;
        asm.defLabel(getLabel(node));
        appendXCFGNode(node);
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private NASMReg getNASMReg(int num) {
        switch (num){
            case 0:
                return null;
            case -1:
                return new NASMReg(NASMReg.regType.RBP);
            case -2: {
                if (curAvailReg>0) {
                    System.out.println("NASMGen:func return reg:"+curAvailReg);
                    return availReg.get(curAvailReg++);
                }
                return new NASMReg(NASMReg.regType.RAX);
            }
            case -3:
                return new NASMReg(NASMReg.regType.RSP);
        }
        if (regMap.containsKey(num)) return regMap.get(num);
        NASMReg reg = availReg.get(curAvailReg++);
        regMap.put(num,reg);
        return reg;
    }

    private String getLabel(XCFGNode node){
        if (node.name!=null) return node.name;
        return "_L"+Integer.toHexString(node.nodeID);
    }

    private NASMAddr getNASMAddr(XIRInstAddr opr){
        if (opr == null) return null;
        switch (opr.type){
            case a_imm: {
                return new NASMImmAddr(opr.lit1);
            }
            case a_reg:{
                return new NASMRegAddr(getNASMReg(opr.lit1));
            }
            case a_mem:{
                NASMMemAddr.wordType wt = NASMMemAddr.wordType.WORD;
                NASMReg base = getNASMReg(opr.lit1);
                NASMReg offset = getNASMReg(opr.lit2);
                return new NASMMemAddr(wt,base,offset,opr.lit3,opr.lit4);
            }
            case a_label:{
                return new NASMLabelAddr(getLabel(cfg.getNode(opr.lit1)));
            }
        }
        return null;
    }

    private NASMOp.opType getNASMOp(XIRInst.opType op){
        switch (op){
            case op_mov:
                return NASMOp.opType.MOV;
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
        NASMInst inst = new NASMInst(getNASMOp(op),a1,a2);
        System.out.println("NASMGen:emitInst:"+inst);
        instList.add(inst);
        asm.emitText(inst.toString());
    }

    private void emitNASMInst(NASMInst inst){
        System.out.println("NASMGen:emitInst:"+inst);
        instList.add(inst);
        asm.emitText(inst.toString());
    }

    private void visitXIRInst(XIRInst inst){
        curAvailReg = 0;
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
            emitNASMInst(inst.op,opr1,opr2);
        } else if (inst.oprList.size()==1) {
            XIRInstAddr opr1 = inst.oprList.get(0);
            emitNASMInst(inst.op,opr1,null);
        } else {
            emitNASMInst(inst.op,null,null);
        }
        lastOp = inst.op;

    }
}

package xng.opt;

import xng.XIR.*;

import java.util.HashSet;
import java.util.Vector;

public class DataFlowAnalyzer {
    private XCFG cfg;
    private HashSet<Integer> visitFlag = new HashSet<>();
    private boolean modified = true;

    public DataFlowAnalyzer(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitProc(XIRProcInfo proc) {

    }

    private void visitXCFG(){
        System.out.println("Data Analysis begin");
        for (XIRProcInfo i : cfg.Proc) {
            modified = true;
            System.out.println("Proc:"+i.entryNode.name);
            while (modified) {
                modified = false;
                visitXCFGNode(i.exitNode);
                System.out.println("mod:"+modified);
                visitFlag.clear();
            }
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("analyzing data:"+node.nodeID);
        Vector<XIRInst> instList = node.instList;
        XIRInst last = null;
        HashSet<Integer> outState = new HashSet<>();
        HashSet<Integer> inState = new HashSet<>();
        for (int i = instList.size()-1; i >= 0; i--) {
            XIRInst inst = instList.get(i);
            System.out.println("Data:Inst:"+inst);
            InstInfo info = inst.info;
            if (last==null) {
                for (XCFGNode n : node.nextNode) {
                    outState.addAll(n.instList.get(0).info.LiveInState);
//                    System.out.println("DBG:"+outState);
                }
            } else outState.addAll(last.info.LiveInState);
            last = inst;
            int defReg = 0;
            HashSet<Integer> usedReg = new HashSet<>();
            int reg1 = 0, reg2 = 0;
            if (inst.oprList.size()>0&&inst.oprList.get(0).type==XIRInstAddr.addrType.a_reg) {
                reg1 = inst.oprList.get(0).lit1;
                if (reg1<0) reg1 = 0;
            }
            if (inst.oprList.size()>1&&inst.oprList.get(1).type==XIRInstAddr.addrType.a_reg) {
                reg2 = inst.oprList.get(1).lit1;
                if (reg2<0) reg2 = 0;
            }
            switch (inst.op) {
                case op_mov:
                    defReg = reg1;
                    usedReg.add(reg2);
                    break;
                case op_wpara:
                case op_push:
                    usedReg.add(reg1);
                    break;
                case op_pop:
                case op_rpara:
                    defReg = reg1;
                    break;
                case op_inc:
                case op_dec:
                    defReg = reg1;
                    usedReg.add(reg1);
                    break;
                case op_eq:
                case op_ne:
                case op_lt:
                case op_ge:
                case op_gt:
                case op_le:
                    usedReg.add(reg1);
                    usedReg.add(reg2);
                    break;
                case op_ret:
                    break;
                case op_not:
                case op_neg:
                case op_pos:
                    defReg = reg1;
                    usedReg.add(reg1);
                    break;
                case op_add:
                case op_sub:
                case op_mult:
                case op_or:
                case op_xor:
                case op_and:
                case op_shl:
                case op_shr:
                case op_mod:
                case op_div:
                    defReg = reg1;
                    usedReg.add(reg1);
                    usedReg.add(reg2);
                    break;
            }
            for (XIRInstAddr addr:inst.oprList) {
                if (addr.type == XIRInstAddr.addrType.a_mem) {
                    if (addr.addr1 != null && addr.addr1.type == XIRInstAddr.addrType.a_reg && addr.addr1.lit1>0)
                        usedReg.add(addr.addr1.lit1);
                    if (addr.addr2 != null && addr.addr2.type == XIRInstAddr.addrType.a_reg && addr.addr2.lit1>0)
                        usedReg.add(addr.addr2.lit1);
                }
            }
            inState.addAll(outState);
            inState.remove(defReg);
            usedReg.remove(0);
            inState.addAll(usedReg);
            info.defReg = defReg;
            if (!inState.equals(info.LiveInState) || !outState.equals(info.LiveOutState)) {
                modified = true;
                info.useReg.clear();
                info.LiveInState.clear();
                info.LiveOutState.clear();
                info.useReg.addAll(usedReg);
                info.LiveOutState.addAll(outState);
                info.LiveInState.addAll(inState);
            }
            System.out.println("Data:def:"+defReg+" used:"+usedReg+" upd:"+info);
            outState.clear();
            inState.clear();
            usedReg.clear();
        }
        node.prevNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){

    }
}

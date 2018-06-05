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

    private void visitXCFG(){
        System.out.println("Data Analysis begin");
        for (XIRProcInfo i : cfg.Proc) {
            while (modified) {
                modified = false;
                visitXCFGNode(i.exitNode);
            }
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("back analyzing "+node.nodeID);
        Vector<XIRInst> instList = node.instList;
        XIRInst last = null;
        HashSet<Integer> outState = new HashSet<>();
        HashSet<Integer> inState = new HashSet<>();
        for (int i = instList.size()-1; i >= 0; i--) {
            XIRInst inst = instList.get(i);
            InstInfo info = inst.info;
            if (last==null) {
                for (XCFGNode n : node.nextNode) {
                    outState.addAll(n.instList.get(0).info.LiveInState);
                }
                last = inst;
            } else outState.addAll(last.info.LiveInState);
            int defReg = 0;
            Vector<Integer> usedReg = new Vector<>();
            switch (inst.op) {
                case op_mov:
                    defReg = inst.oprList.get(0).lit1;
                    usedReg.add(inst.oprList.get(1).lit1);
                    break;
                case op_wpara:
                case op_push:
                    usedReg.add(inst.oprList.get(1).lit1);
                    break;
                case op_pop:
                case op_rpara:
                case op_inc:
                case op_dec:
                    defReg = inst.oprList.get(0).lit1;
//                    usedReg.add(inst.oprList.get(1).lit1);
                    break;
                case op_eq:
                case op_ne:
                case op_lt:
                case op_ge:
                case op_gt:
                case op_le:
                    defReg = inst.oprList.get(0).lit1;
                    usedReg.add(inst.oprList.get(1).lit1);
                    usedReg.add(inst.oprList.get(2).lit1);
                    break;
                case op_ret:
                    break;
                case op_not:
                case op_neg:
                case op_pos:
                    defReg = inst.oprList.get(0).lit1;
                    usedReg.add(inst.oprList.get(1).lit1);
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
                    defReg = inst.oprList.get(0).lit1;
                    usedReg.add(inst.oprList.get(1).lit1);
                    usedReg.add(inst.oprList.get(2).lit1);
                    break;
            }
            inState.addAll(usedReg);
            inState.addAll(outState);
            if (defReg!=0) inState.remove(defReg);
            if (!inState.equals(info.LiveInState) || !outState.equals(info.LiveOutState)) {
                modified = true;
            }
            info.LiveInState.clear();
            info.LiveOutState.clear();
            info.LiveOutState.addAll(outState);
            info.LiveInState.addAll(inState);
        }
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){

    }
}

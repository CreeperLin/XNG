package xng.backend.NASM;

import xng.XIR.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

public class NASMAdapter {

    private XCFG cfg;
    private int curStackPt = 8;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public NASMAdapter(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("NASM Adaptation begin");
        for (XIRProcInfo i : cfg.Proc) {
            visitXCFGNode(i.entryNode);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("adapting:"+node.nodeID);
        Vector<XIRInst> newList = new Vector<>();
        for (XIRInst inst : node.instList) {
            if (inst.oprList.size() == 3) {
                XIRInstAddr opr1 = inst.oprList.get(0);
                XIRInstAddr opr2 = inst.oprList.get(1);
                XIRInstAddr opr3 = inst.oprList.get(2);
                if (opr1.equals(opr2)) {
                    visitXIRInst(newList, inst.op, opr1, opr3);
                } else if (opr1.equals(opr3)) {
                    if (XIRInst.isOpCommute(inst.op)) {
                        visitXIRInst(newList, inst.op, opr1, opr3);
                    } else {
                        XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                        visitXIRInst(newList, XIRInst.opType.op_mov, tmp_addr, opr2);
                        visitXIRInst(newList, inst.op, tmp_addr, opr3);
                        visitXIRInst(newList, XIRInst.opType.op_mov, opr1, tmp_addr);
                    }
                } else {
                    visitXIRInst(newList, XIRInst.opType.op_mov, opr1, opr2);
                    visitXIRInst(newList, inst.op, opr1, opr3);
                }
            } else if (inst.oprList.size()==2) {
                XIRInstAddr opr1 = inst.oprList.get(0);
                XIRInstAddr opr2 = inst.oprList.get(1);
                visitXIRInst(newList,inst.op,opr1,opr2);
            } else if (inst.oprList.size()==1) {
                XIRInstAddr opr1 = inst.oprList.get(0);
                visitXIRInst(newList, inst.op,opr1,null);
            } else {
                visitXIRInst(newList, inst.op,null,null);
            }
        }
        node.instList = newList;
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private boolean isExp2(int t){
        return (t>0) && (t & (t-1)) == 0;
    }

    private XIRInstAddr visitXIRInstAddr(Vector<XIRInst> list, XIRInstAddr opr) {
        if (opr==null) return null;
        switch (opr.type){
            case a_mem:{
                if (opr.addr1==null) {
                    return opr;
                } else {
                    XIRInstAddr addr1, addr2;
                    System.out.println("idx mem:"+opr.addr1+' '+opr.addr2);
                    switch (opr.addr1.type){
                        case a_mem:{
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            visitXIRInst(list, XIRInst.opType.op_mov,tmp,opr.addr1);
                            addr1 = tmp;
                            break;
                        }
                        default:
                            addr1 = opr.addr1;
                    }
                    switch (opr.addr2.type){
                        case a_static: {
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            visitXIRInst(list,XIRInst.opType.op_mov,tmp,opr.addr2);
                            addr2 = tmp;
                            break;
                        }
                        case a_mem: {
                            XIRInstAddr tmp = XIRInstAddr.newRegAddr();
                            visitXIRInst(list,XIRInst.opType.op_mov,tmp,opr.addr2);
                            addr2 = tmp;
                            break;
                        }
                        default:
                            addr2 = opr.addr2;
                    }
                    XIRInstAddr t = new XIRInstAddr(opr.type,opr.lit1,opr.lit2,opr.lit3,opr.lit4);
                    t.addr1 = addr1;
                    t.addr2 = addr2;
                    return t;
                }
            }
            default:
                return opr;
        }
    }

    private void visitXIRInst(Vector<XIRInst> list, XIRInst.opType op, XIRInstAddr opr1, XIRInstAddr opr2){
        switch (op) {
            case op_div: {
                if (opr2 == null) break;
                if (opr2.isConst()){
                    int t = opr2.getConst();
                    if (t == 0) {
                        System.out.println("NASMAdapt:error:div zero");
                        return;
                    }
                    if (isExp2(t)){
                        int i = 0;
                        while ((t >> i)!=1) ++i;
                        System.out.println("NASMAdapt:div:exp2 "+i);
                        if (i!=0){
                            visitXIRInst(list, XIRInst.opType.op_shr,opr1,XIRInstAddr.newImmAddr(i,0));
                        }
                        return;
                    }
                    XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                    visitXIRInst(list, XIRInst.opType.op_mov,tmp_addr,opr2);
                    opr2 = tmp_addr;
                }
                break;
            }
            case op_mod: {
                if (opr2 == null) break;
                if (opr2.isConst()){
                    int t = opr2.getConst();
                    if (t == 0) {
                        System.out.println("NASMAdapt:error:mod zero");
                        return;
                    }
                    if (isExp2(t)){
                        int i = 0;
                        while ((t >> i)!=1) ++i;
                        System.out.println("NASMAdapt:mod:exp2 "+i);
                        if (i==0){
                            visitXIRInst(list,XIRInst.opType.op_mov,opr1,XIRInstAddr.newImmAddr(0,0));
                        } else {
                            visitXIRInst(list,XIRInst.opType.op_and,opr1,XIRInstAddr.newImmAddr((1<<i)-1,0));
                        }
                        return;
                    }
                    XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                    visitXIRInst(list, XIRInst.opType.op_mov,tmp_addr,opr2);
                    opr2 = tmp_addr;
                }
                break;
            }
            case op_mult: {
                if (opr2.isConst()){
                    int t = opr2.getConst();
                    if (t == 0) {
                        visitXIRInst(list,XIRInst.opType.op_mov,opr1,XIRInstAddr.newImmAddr(0,0));
                        return;
                    }
                    if (isExp2(t)){
                        int i = 0;
                        while ((t >> i)!=1) ++i;
                        System.out.println("NASMAdapt:mult:exp2 "+i);
                        if (i<3){
                            for (int j = 0;j < i;++j){
                                visitXIRInst(list,XIRInst.opType.op_add,opr1,opr1);
                            }
                            return;
                        } else {
                            visitXIRInst(list,XIRInst.opType.op_shl,opr1,XIRInstAddr.newImmAddr(i,0));
                        }
                    }
                }
                if (opr1.type != XIRInstAddr.addrType.a_reg) {
                    XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                    visitXIRInst(list,XIRInst.opType.op_mov,tmp_addr,opr1);
                    visitXIRInst(list,XIRInst.opType.op_mult,tmp_addr,opr2);
                    visitXIRInst(list,XIRInst.opType.op_mov,opr1,tmp_addr);
                    return;
                }
                break;
            }
            case op_not:
            case op_neg: {
                if (opr2!=null) {
                    visitXIRInst(list,XIRInst.opType.op_mov,opr1,opr2);
                    opr2 = null;
                }
                break;
            }
        }
        if (opr1 != null && opr2 != null
                && (opr1.type == XIRInstAddr.addrType.a_mem || opr1.type == XIRInstAddr.addrType.a_static)
                && (opr2.type == XIRInstAddr.addrType.a_mem || opr2.type == XIRInstAddr.addrType.a_static)) {
            XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
            visitXIRInst(list,XIRInst.opType.op_mov, tmp_addr, opr2);
            visitXIRInst(list,op,opr1,tmp_addr);
            return;
        }
        XIRInstAddr nopr2 = visitXIRInstAddr(list, opr2);
        XIRInstAddr nopr1 = visitXIRInstAddr(list, opr1);
        if (op == XIRInst.opType.op_mov && Objects.equals(nopr1,nopr2)) return;
        list.add(new XIRInst(op,nopr1,nopr2));
    }
}

package xng.XIR;

import xng.opt.InstInfo;

import java.util.Vector;

public class XIRInst {
    public enum opType{
        op_none,op_call,op_add,
        op_sub,op_pos,op_mult,op_div,
        op_mod,op_not,op_neg,op_and,op_or,
        op_xor,op_shl,op_shr,op_mov,op_push,op_pop,op_wpara,op_rpara,
        op_eq,op_ne,op_le,op_ge,op_dec,op_inc,op_jcc,op_jmp,op_lea,
        op_phi,op_gt,op_lt,op_ret,op_int,op_decl;

        public static boolean isCompare(opType t) {
            switch (t) {
                case op_eq:
                case op_ne:
                case op_ge:
                case op_gt:
                case op_le:
                case op_lt:
                    return true;
            }
            return false;
        }
    }

    public opType op;
    public Vector<XIRInstAddr> oprList;
    public InstInfo info = new InstInfo();

    public static boolean isOpCommute(opType _op) {
        switch (_op){
            case op_add:
            case op_mult:
            case op_and:
            case op_eq:
            case op_ne:
            case op_or:
            case op_xor:
                return true;
            default:
                return false;
        }
    }

    public void copy(XIRInst inst) {
        op = inst.op;
        oprList = inst.oprList;
        info = inst.info;
    }

    public XIRInst(opType _op){
        op = _op;
        oprList = new Vector<>();
    }

    public XIRInst(opType _op, Vector<XIRInstAddr> _opr){
        op = _op;
        oprList = _opr;
    }

    public XIRInst(opType _op, XIRInstAddr opr1, XIRInstAddr opr2){
        op = _op;
        oprList = new Vector<>();
        if (opr1 != null) oprList.add(opr1);
        if (opr2 != null) oprList.add(opr2);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(op.toString().toUpperCase());
        str.append(' ').append(info).append(' ');
        oprList.forEach(str::append);
        return str.toString();
    }
}

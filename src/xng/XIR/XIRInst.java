package xng.XIR;

import java.util.Vector;

public class XIRInst {
    public enum opType{
        op_none,op_call,op_add,
        op_sub,op_pos,op_mult,op_div,
        op_mod,op_not,op_neg,op_and,op_or,
        op_xor,op_shl,op_shr,op_mov,
        op_eq,op_ne,op_le,op_ge,op_dec,op_inc,op_jmp,op_jcc,
        op_gt,op_lt,op_ret,op_syscall,
    };
    public opType op;
    public Vector<XIRInstAddr> oprList;

    public XIRInst(opType _op){
        op = _op;
        oprList = new Vector<>();
    }

    public XIRInst(opType _op, Vector<XIRInstAddr> _opr){
        op = _op;
        oprList = _opr;
    }
}

package xng.opt;

import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;

public class VarInfo {

    public enum valType{
        v_const, v_undef, v_indef
    }

    public valType type;

    public int constValue;
    public int readCount = 0;
    public int writeCount = 0;

    public VarInfo() {
        type = valType.v_undef;
        constValue = 0;
    }

    public int getRefCount() {
        return readCount + writeCount;
    }

    public static void typePropagate(VarInfo ans, VarInfo opr1, VarInfo opr2) {
        if (ans == null) return;
        boolean isConst = true;
        if (ans.type == valType.v_const) ans.type = valType.v_indef;
        if (ans.type == valType.v_indef) return;
        if (opr1 != null) isConst = (opr1.type == valType.v_const);
        if (opr2 != null) isConst &= (opr2.type == valType.v_const);
        ans.type = isConst ? valType.v_const : valType.v_undef;
    }

    public static void constPropagate(XIRInst.opType op,VarInfo inf1,VarInfo inf2,VarInfo inf3) {
        if (inf1==null) return;
        typePropagate(inf1,inf2,inf3);
        if (inf1.type == valType.v_indef) return;
        int const1 = inf1.constValue;
        int const2 = inf2 == null ? 0 : inf2.constValue;
        int const3 = inf3 == null ? 0 : inf3.constValue;
        int ans = 0;
        switch (op) {
            case op_add:
                ans = const1 + const2;
                break;
            case op_sub:
                ans = const1 - const2;
                break;
            case op_mult:
                ans = const1 * const2;
                break;
            case op_div:
                ans = const2 == 0 ? 0 : const1 / const2;
                break;
            case op_inc:
                ans = const1 + 1;
                break;
            case op_dec:
                ans = const1 - 1;
                break;
            case op_mod:
                ans = const2 == 0 ? 0 : const1 % const2;
                break;
            case op_eq:
                ans = (const1 == const2) ? 1 : 0;
                break;
            case op_ne:
                ans = (const1 == const2) ? 0 : 1;
                break;
            case op_and:
                ans = const1 & const2;
                break;
            case op_or:
                ans = const1 | const2;
                break;
            case op_not:
                ans = ~const1;
                break;
            case op_ge:
                ans = (const1 >= const2) ? 1 : 0;
                break;
            case op_gt:
                ans = (const1 > const2) ? 1 : 0;
                break;
            case op_le:
                ans = (const1 <= const2) ? 1 : 0;
                break;
            case op_neg:
                ans = -const1;
                break;
            case op_mov:
                ans = const1;
                break;
            case op_xor:
                ans = const1 ^ const2;
                break;
            case op_shl:
                ans = const1 << const2;
                break;
            case op_shr:
                ans = const1 >> const2;
                break;
            case op_pos:
                ans = const1;
                break;
            case op_lt:
                ans = (const1 < const2) ? 1 : 0;
                break;
        }
        inf1.constValue = ans;
    }

    @Override
    public String toString() {
        return "[" + ((type==valType.v_const)? constValue : "*") + ',' + readCount + ',' + writeCount + "]";
    }
}

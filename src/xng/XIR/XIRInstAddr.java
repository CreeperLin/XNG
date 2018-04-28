package xng.XIR;

public class XIRInstAddr {
    public enum addrType{
        a_imm,a_reg,a_mem
    }
    public addrType type;
    public int lit1;
    public int lit2;

    public XIRInstAddr(addrType _t, int _l1, int _l2){
        type = _t;
        lit1 = _l1;
        lit2 = _l2;
    }

    static int regCount = 0;
    public static XIRInstAddr newRegAddr(){
        return new XIRInstAddr(addrType.a_reg,++regCount,0);
    }
}

package xng.backend.NASM;

public class NASMInst {

    public NASMOp op;
    public NASMAddr opr1;
    public NASMAddr opr2;

    public NASMInst(NASMOp.opType _op, NASMAddr _op1, NASMAddr _op2){
        op = new NASMOp(_op);
        opr1 = _op1;
        opr2 = _op2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(op);
        if (opr1!=null) sb.append('\t').append(opr1);
        if (opr2!=null) sb.append(", ").append(opr2);
        return sb.toString();
    }
}

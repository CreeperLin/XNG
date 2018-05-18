package xng.backend.NASM;

public class NASMImmAddr extends NASMAddr {

    private int val;

    NASMImmAddr(int _v){
        val = _v;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }
}

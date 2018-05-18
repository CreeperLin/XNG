package xng.backend.NASM;

public class NASMRegAddr extends NASMAddr {

    private NASMReg reg;

    NASMRegAddr(NASMReg.regType _r){
        reg = new NASMReg(_r);
    }

    NASMRegAddr(NASMReg _r){
        reg = _r;
    }

    @Override
    public String toString() {
        return reg.toString();
    }
}
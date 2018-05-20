package xng.backend.NASM;

public class NASMMemAddr extends NASMAddr {

    private NASMWordType wtype;
    private NASMReg base;
    private NASMReg offset;
    private int scale;
    private int num;

    NASMMemAddr(NASMWordType _w, NASMReg _b, NASMReg _o, int _s, int _n){
        wtype = _w;
        base = _b;
        offset = _o;
        scale = _s;
        num = _n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (wtype != NASMWordType.WORD) sb.append(wtype.toString().toLowerCase()).append(' ');
        sb.append('[');
        if (base!=null) sb.append(base);
        if (offset!=null) sb.append("+").append(offset);
        if (scale!=0) sb.append('*').append(scale);
        if (num>0) sb.append("+").append(0).append(Integer.toHexString(num).toUpperCase()).append('H');
        if (num<0) sb.append('-').append(0).append(Integer.toHexString(-num).toUpperCase()).append('H');
        sb.append(']');
        return sb.toString();
    }
}

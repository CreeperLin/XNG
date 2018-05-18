package xng.backend.NASM;

public class NASMMemAddr extends NASMAddr {

    public enum wordType{
        WORD,DWORD,QWORD,
    }

    private wordType wtype;
    private NASMReg base;
    private NASMReg offset;
    private int scale;
    private int num;

    NASMMemAddr(wordType _w, NASMReg _b, NASMReg _o, int _s, int _n){
        wtype = _w;
        base = _b;
        offset = _o;
        scale = _s;
        num = _n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (wtype != wordType.WORD) sb.append(wtype.toString()).append(' ');
        sb.append('[');
        if (base!=null) sb.append(base);
        if (offset!=null) sb.append("+").append(offset);
        if (scale!=0) sb.append('*').append(scale);
        if (num>0) sb.append("+");
        if (num!=0) sb.append(num);
        sb.append(']');
        return sb.toString();
    }
}

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

    private String getHex(int t) {
        String str = Integer.toHexString(t).toUpperCase();
        char c = str.charAt(0);
        if (c>'0' && c<'9') return str;
        return '0'+str;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (wtype != NASMWordType.WORD) sb.append(wtype.toString().toLowerCase()).append(' ');
        sb.append('[');
        boolean opr = false;
        if (base!=null) {sb.append(base); opr = true;}
        if (offset!=null && opr) sb.append("+");
        if (offset!=null) {sb.append(offset); if (scale!=0 && scale !=1) sb.append('*').append(scale); opr = true;}
        if (num>0) sb.append(opr ? "+" : "").append(getHex(num)).append('H');
        if (num<0) sb.append('-').append(getHex(-num)).append('H');
        sb.append(']');
        return sb.toString();
    }
}

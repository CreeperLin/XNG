package xng.XIR;

public class XIRData {
    public enum dataType {
        d_str, d_num, d_res
    }

    public dataType dType;

    public String name;
    public int size;

    public String strval;
    public int val;

    public XIRData(String _n, int _v, boolean isInit) {
        name = _n;
        if (isInit) {
            val = _v;
            dType = dataType.d_num;
        } else {
            size = _v;
            dType = dataType.d_res;
        }
    }

    public XIRData(String _n, String _v) {
        name = _n;
        dType = dataType.d_str;
        strval = _v;
    }
}

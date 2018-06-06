package xng.opt;

import xng.XIR.XIRInstAddr;

import java.util.HashSet;

public class RegInfo {

    public HashSet<Integer> toNodes = new HashSet<>();
    public int color = 0;
    public boolean enabled = true;
    public VarInfo varInfo;

    public RegInfo(int t) {
        varInfo = XIRInstAddr.regList.get(t-1).info;
    }

    public int getDeg() {
        return toNodes.size();
    }
}

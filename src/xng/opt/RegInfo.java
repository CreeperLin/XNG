package xng.opt;

import java.util.HashSet;

public class RegInfo {

    public HashSet<Integer> toNodes = new HashSet<>();
    public int color = 0;
    public boolean enabled = true;
    public VarInfo varInfo;

    public int getDeg() {
        return toNodes.size();
    }
}

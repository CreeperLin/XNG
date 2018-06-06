package xng.opt;

import java.util.HashSet;

public class RegInfo {

    public HashSet<Integer> toNodes = new HashSet<>();
    public int deg = 0;
    public int color = 0;
    public boolean enabled = true;

    public int getDeg() {
        return toNodes.size();
    }
}

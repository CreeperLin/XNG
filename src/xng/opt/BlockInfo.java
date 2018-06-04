package xng.opt;

import java.util.HashSet;

public class BlockInfo {
    
    public HashSet<Integer> LiveInState = new HashSet<>();
    public HashSet<Integer> LiveOutState = new HashSet<>();
}

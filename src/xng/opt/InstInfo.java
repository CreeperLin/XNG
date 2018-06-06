package xng.opt;

import java.util.HashSet;

public class InstInfo {

    public HashSet<Integer> LiveInState = new HashSet<>();
    public HashSet<Integer> LiveOutState = new HashSet<>();
    public int defReg = 0;
    public HashSet<Integer> useReg = new HashSet<>();

    public InstInfo() {
        LiveOutState.clear();
        LiveInState.clear();
    }

    @Override
    public String toString() {
        if (LiveInState.isEmpty()&&LiveOutState.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("[In:");
        for (Integer integer : LiveInState) {
            sb.append(integer).append(' ');
        }
        sb.append("Out:");
        for (Integer integer : LiveOutState) {
            sb.append(integer).append(' ');
        }
        sb.append("D:").append(defReg);
        sb.append(" U:");
        for (Integer integer : useReg) {
            sb.append(integer).append(' ');
        }
        sb.append("]");
        return sb.toString();
    }
}

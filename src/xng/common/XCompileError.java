package xng.common;

import xng.frontend.AST.XASTBaseNode;

import java.util.HashMap;
import java.util.Vector;

public class XCompileError {
    public enum ceType{
        ce_default,
        ce_invalid_constructor,
        ce_invalid_type,
        ce_redef,
        ce_nodecl,
        ce_outofloop,
        ce_type,
        ce_lvalue,

        cw_noreturn,
    }
    public int errorCount = 0;
    private int warningCount = 0;
    HashMap<ceType,String> errDesc = new HashMap<>();
    private Vector<String> srcLines;
    private Vector<String> msgList = new Vector<>();

    public XCompileError(Vector<String> sl){
        srcLines = sl;
    }

    public void add(ceType type, String msg, XASTBaseNode node){
        add(type,msg,node,true);
    }

    public void add(ceType type, String msg, XASTBaseNode node, boolean isError) {
        StringBuilder m = new StringBuilder();
        String line = srcLines.elementAt(node.pos.startLine-1);
        m.append(node.pos.toString()).append(isError?" error: ":" warning: ").append(type.toString()).append(":").append(msg).append('\n').append(line).append('\n');
        for (int i=0;i<node.pos.startColumn;++i){
            m.append(((line.charAt(i) == '\t') ? '\t' : ' '));
        }
        m.append('^');
        for (int i = node.pos.startColumn+1;i<node.pos.endColumn;++i){
            m.append('~');
        }
        msgList.add(m.toString());
        if (isError) ++errorCount;
        else ++warningCount;
        System.out.println(m.toString());
    }
    public void print(){
        msgList.forEach(System.out::println);
        System.out.println("XNG:"+ errorCount+" error(s), "+warningCount+" warning(s)");
    }
}

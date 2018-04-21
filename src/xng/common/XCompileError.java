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
    }
    HashMap<ceType,String> errDesc = new HashMap<>();
    Vector<String> srcLines;
    public Vector<String> errorList = new Vector<>();

    public XCompileError(Vector<String> sl){
        srcLines = sl;
    }
    public void add(ceType type, String msg, XASTBaseNode node)
    {
        StringBuilder m = new StringBuilder();
        String line = srcLines.elementAt(node.pos.startLine-1);
        m.append(node.pos.toString()).append(" error: ").append(type.toString()).append(":").append(msg).append('\n').append(line).append('\n');
        for (int i=0;i<node.pos.startColumn;++i){
            m.append(((line.charAt(i) == '\t') ? '\t' : ' '));
        }
        m.append('^');
        for (int i = node.pos.startColumn+1;i<node.pos.endColumn;++i){
            m.append('~');
        }
        errorList.add(m.toString());
        System.out.println(m.toString());
    }
    public void print(){

    }
}

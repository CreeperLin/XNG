package xng.common;

public class XException extends Exception {
    public enum exType{
        param_error,

        compile_error,
        compile_warning,
    }

    public XException(exType type,String msg){
        super(msg);
        this.printStackTrace();
        System.err.println("XNG:"+type.toString()+":"+msg);
    }
}

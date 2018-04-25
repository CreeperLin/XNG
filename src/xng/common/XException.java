package xng.common;

import xng.wrapper.XParameter;

public class XException extends Exception {
    public enum exType{
        param_error,

        compile_error,
        compile_warning,
    }

    public XException(exType type,String msg){
//        super(msg);
        System.err.println("XNG:"+type.toString()+":"+msg);
        if (XParameter.verbose > 2) this.printStackTrace();
    }
}

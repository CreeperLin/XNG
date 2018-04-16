package xng.wrapper;

import xng.common.XException;

import java.util.Vector;

public class XParameter {

    Vector<String> srcPath = new Vector<>();

    boolean isEnableSemanticCheck;
    boolean isEnableIR;
    boolean isEnableOptimization;
    boolean isEnableAssembly;

    XParameter(String[] args) throws XException {
        isEnableSemanticCheck = true;
        for (String i:args){
            if (i.toCharArray()[0] != '-'){
                srcPath.add(i);
            } else {
                if (i.equals("--semantic")){
                    isEnableIR = false;
                } else if (i.startsWith("-O")){
                    switch(i.charAt(2)) {
                        case '0':
                            isEnableOptimization = false;
                            break;
                        case '1':
                            break;
                        default:
                            throw new XException(XException.exType.param_error,"invalid parameter");
                    }
                }
            }
        }
        if (srcPath.size()<1) throw new XException(XException.exType.param_error,"unspecified source file path");

        isEnableAssembly = isEnableIR && isEnableAssembly;
        isEnableIR = isEnableSemanticCheck && isEnableIR;
//        isEnableOptimization = true;

    }

}

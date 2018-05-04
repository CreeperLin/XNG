package xng.wrapper;

import xng.common.XException;

import java.util.Vector;

import static java.lang.System.out;

public class XParameter {

    Vector<String> srcPath = new Vector<>();

    public static boolean isEnableSemanticCheck = true;
    public static boolean isEnableIR = true;
    public static boolean isEnableOptimization = true;
    public static boolean isEnableAssembly = true;
    public static int verbose = 0;

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
                } else if (i.startsWith("-v")){
                    for (char t:i.toCharArray()){
                        if (t=='v'){
                            ++verbose;
                        }
                    }
                }
            }
        }
        if (srcPath.size()<1) throw new XException(XException.exType.param_error,"unspecified source file path");

        isEnableAssembly = isEnableIR && isEnableAssembly;
        isEnableIR = isEnableSemanticCheck && isEnableIR;
//        isEnableOptimization = true;
        out.println("XParam:semantic check:" + isEnableSemanticCheck);
        out.println("XParam:IR gen:" + isEnableIR);
        out.println("XParam:opt:" + isEnableOptimization);
        out.println("XParam:assembly:" + isEnableAssembly);
        out.println("XParam:verbose level:" + verbose);
        out.println("src filename:" + srcPath);
    }

}

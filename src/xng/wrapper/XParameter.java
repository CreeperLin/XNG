package xng.wrapper;

import xng.common.XException;

import java.util.Vector;

import static java.lang.System.out;

public class XParameter {

    static Vector<String> srcPath = new Vector<>();
    static String outPath = null;

    static boolean isEnableSemanticCheck = true;
    static boolean isEnableIR = true;
    static boolean isEnableOptimization = true;
    static boolean isEnableAssembly = true;
    public static boolean isWarningError = false;
    public static boolean isWarningAll = false;
    public static int verbose = 0;

    static void accept(String[] args) throws XException {
        isEnableSemanticCheck = true;
        for (int i=0;i<args.length;++i){
            if (args[i].toCharArray()[0] != '-'){
                srcPath.add(args[i]);
            } else {
                if (args[i].equals("--semantic")){
                    isEnableIR = false;
                } else if (args[i].startsWith("-O")){
                    switch(args[i].charAt(2)) {
                        case '0':
                            isEnableOptimization = false;
                            break;
                        case '1':
                            break;
                        default:
                            throw new XException(XException.exType.param_error,"invalid parameter");
                    }
                } else if (args[i].startsWith("-v")){
                    for (char t:args[i].toCharArray()){
                        if (t=='v'){
                            ++verbose;
                        }
                    }
                } else if (args[i].startsWith("-W")){
                    switch (args[i]) {
                        case "-Wall":
                            isWarningAll = true;
                            break;
                        case "-Werror":
                            isWarningError = true;
                            break;
                    }
                } else if (args[i].startsWith("-o")){
                    if (outPath!=null){
                        throw new XException(XException.exType.param_error,"multiple output file not supported");
                    } else if (i+1<args.length) {
                        outPath = args[++i];
                    }
                }
            }
        }
        if (srcPath.size()<1) throw new XException(XException.exType.param_error,"unspecified source file path");
        if (outPath==null) outPath="a.out";

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

package xng.backend;

import java.io.BufferedWriter;
import java.io.IOException;

public class ASMGenerator {

    enum sectType{
        TEXT,DATA,BSS,NONE
    }
    private sectType curSection = sectType.NONE;
    private java.io.BufferedWriter bw;
    public boolean isError = false;

    public ASMGenerator(BufferedWriter _bw){
        bw = _bw;
        outputHeader();
    }

    private void outputHeader(){
        emitLine(false,"default rel");
        emitLine();
    }

    public void emitLine(){
        emitLine(false,null);
    }

    private void emitLine(boolean indent, String str){
        try {
            if (indent) bw.write('\t');
            if (str!=null) bw.write(str);
//            System.out.println("ASMGen:Write:"+str);
            bw.newLine();
        } catch (IOException e){
            e.printStackTrace();
            isError = true;
        }
    }

    private void defSection(sectType sec){
        if (curSection==sec) return;
        emitLine(false,"SECTION ."+sec.toString().toLowerCase());
        curSection = sec;
    }

    public void emitText(String str) {
        defSection(sectType.TEXT);
        emitLine(true, str);
    }

    public void emitData(String str) {
        defSection(sectType.DATA);
        emitLine(true, str);
    }

    public void defLabel(String str) {
        defSection(sectType.TEXT);
        emitLine(false,str+':');
    }

    public void defGlobal(String str){
        emitLine(false,"global "+str);
    }
}

package xng.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class ASMGenerator {

    public enum sectType{
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
        String libPath = "./lib/lib.asm";
        try {
            BufferedReader br = new BufferedReader(new FileReader(libPath));
            String t;
            while((t=br.readLine())!=null){
                bw.write(t);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("lib append failed");
//            e.printStackTrace();
            return;
        }
    }

    public void emitLine(){
        emitLine(false,null);
    }

    public void emitLine(boolean indent, String str){
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

    public void defSection(sectType sec){
        if (curSection==sec) return;
        emitLine(false,"SECTION ."+sec.toString().toLowerCase());
        curSection = sec;
    }

    public void emitText(String str) {
        defSection(sectType.TEXT);
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

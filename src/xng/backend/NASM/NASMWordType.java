package xng.backend.NASM;

public enum NASMWordType {
    BYTE,WORD,DWORD,QWORD;

    public static int getWordSize(NASMWordType wt) {
        switch (wt) {
            case QWORD:
                return 8;
            case WORD:
                return 2;
            case DWORD:
                return 4;
            case BYTE:
                return 1;
        }
        return 0;
    }
}
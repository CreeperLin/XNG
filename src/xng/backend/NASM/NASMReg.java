package xng.backend.NASM;

public class NASMReg{

    public enum regType {
        NONE,REL,
        R8,R9,R10,R11,R12,R13,R14,R15,RAX,RBX,RCX,RDX,RSP,RBP,RSI,RDI,RIP,
        R8D,R9D,R10D,R11D,R12D,R13D,R14D,R15D,EAX,EBX,ECX,EDX,EBP,ESI,EDI,ESP,EIP,
        R8W,R9W,R10W,R11W,R12W,R13W,R14W,R15W,AX,BX,CX,DX,BP,SI,DI,SP,
    }

    private int regId;
    NASMWordType wt;
    private String staticName;

    NASMReg(String str)
    {
        regId = -1;
        staticName = str;
    }

    public NASMReg(regType t) {
        wt = NASMWordType.QWORD;
        regId = getRegId(t);
    }

    NASMReg(int id, NASMWordType _wt){
        regId = id;
        wt = _wt;
    }

    private static int getRegId(NASMReg.regType t){
        switch (t) {
            case R8:
                return 8;
            case R9:
                return 9;
            case R10:
                return 10;
            case R11:
                return 11;
            case R12:
                return 12;
            case R13:
                return 13;
            case R14:
                return 14;
            case R15:
                return 15;
            case RAX:
                return 16;
            case RBX:
                return 17;
            case RCX:
                return 18;
            case RDX:
                return 19;
            case RSP:
                return 20;
            case RBP:
                return 21;
            case RSI:
                return 22;
            case RDI:
                return 23;
            case REL:
                return -1;
        }
        return 0;
    }

    private NASMReg.regType getSpecReg() {
        switch (wt) {
            case WORD:
                return getWORDReg(regId);
            case DWORD:
                return getDWORDReg(regId);
            case QWORD:
                return getQWORDReg(regId);
        }
        return regType.NONE;
    }

    private static regType getWORDReg(int regID){
        switch (regID){
            case 8:
                return regType.R8W;
            case 9:
                return regType.R9W;
            case 10:
                return regType.R10W;
            case 11:
                return regType.R11W;
            case 12:
                return regType.R12W;
            case 13:
                return regType.R13W;
            case 14:
                return regType.R14W;
            case 15:
                return regType.R15W;
            case 16:
                return regType.AX;
            case 17:
                return regType.BX;
            case 18:
                return regType.CX;
            case 19:
                return regType.DX;
            case 20:
                return regType.SP;
            case 21:
                return regType.BP;
            case 22:
                return regType.SI;
            case 23:
                return regType.DI;
        }
        return regType.NONE;
    }

    private static regType getDWORDReg(int regID){
        switch (regID){
            case 8:
                return regType.R8D;
            case 9:
                return regType.R9D;
            case 10:
                return regType.R10D;
            case 11:
                return regType.R11D;
            case 12:
                return regType.R12D;
            case 13:
                return regType.R13D;
            case 14:
                return regType.R14D;
            case 15:
                return regType.R15D;
            case 16:
                return regType.EAX;
            case 17:
                return regType.EBX;
            case 18:
                return regType.ECX;
            case 19:
                return regType.EDX;
            case 20:
                return regType.ESP;
            case 21:
                return regType.EBP;
            case 22:
                return regType.ESI;
            case 23:
                return regType.EDI;
        }
        return regType.NONE;
    }

    private static regType getQWORDReg(int regID){
        switch (regID){
            case 8:
                return regType.R8;
            case 9:
                return regType.R9;
            case 10:
                return regType.R10;
            case 11:
                return regType.R11;
            case 12:
                return regType.R12;
            case 13:
                return regType.R13;
            case 14:
                return regType.R14;
            case 15:
                return regType.R15;
            case 16:
                return regType.RAX;
            case 17:
                return regType.RBX;
            case 18:
                return regType.RCX;
            case 19:
                return regType.RDX;
            case 20:
                return regType.RSP;
            case 21:
                return regType.RBP;
            case 22:
                return regType.RSI;
            case 23:
                return regType.RDI;
        }
        return regType.NONE;
    }

    @Override
    public String toString() {
        if (regId == -1) return "rel "+staticName;
        return getSpecReg().name().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof NASMReg) && regId == ((NASMReg)o).regId && wt == ((NASMReg)o).wt;
    }
}

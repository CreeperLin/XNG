package xng.backend.NASM;

public class NASMReg{

    public enum regType {
        NONE,
        R0,R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13,R14,R15,RAX,RBX,RCX,RDX,RSP,RBP,RSI,RDI,RIP,
        R0D,R1D,R2D,R3D,R4D,R5D,R6D,R7D,R8D,R9D,R10D,R11D,R12D,R13D,R14D,R15D,EAX,EBX,ECX,EDX,EBP,ESI,EDI,ESP,EIP,
        R0W,R1W,R2W,R3W,R4W,R5W,R6W,R7W,R8W,R9W,R10W,R11W,R12W,R13W,R14W,R15W,AX,BX,CX,DX,BP,SI,DI,SP,
    }

    public regType type;

    public NASMReg(regType _t){
        type = _t;
    }

    public NASMReg(int id, NASMWordType wt){
        type = getSpecReg(id,wt);
    }

    public static int getRegId(regType reg){
        switch (reg){
            case R0:
            case R0D:
                return 1;
            case R1:
            case R1D:
                return 2;
        }
        return 0;
    }

    public static NASMReg.regType getSpecReg(int regID, NASMWordType word) {
        switch (word) {
            case WORD:
                return getWORDReg(regID);
            case DWORD:
                return getDWORDReg(regID);
            case QWORD:
                return getQWORDReg(regID);
        }
        return regType.NONE;
    }

    public static regType getWORDReg(int regID){
        switch (regID){
            case 1:
                return regType.R1W;
            case 2:
                return regType.R2W;
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

    public static regType getDWORDReg(int regID){
        switch (regID){
            case 1:
                return regType.R1D;
            case 2:
                return regType.R2D;
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

    public static regType getQWORDReg(int regID){
        switch (regID){
            case 1:
                return regType.R1;
            case 2:
                return regType.R2;
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
        return type.name().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        return type == ((NASMReg)o).type;
    }
}

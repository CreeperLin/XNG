package xng.backend.NASM;

public class NASMReg{

    public enum regType {
        RAX,RBX,RCX,RDX,RBP,RSI,R8,R9,R10,R11,RDI,RSP,R12,R13,R14,R15,RIP,
        EAX,EBX,ECX,EDX,EBP,ESI,E8,E9,E10,E11,EDI,ESP,E12,E13,E14,E15,EIP,
    }

    public regType type;

    public NASMReg(regType _t){
        type = _t;
    }

    @Override
    public String toString() {
        return type.name().toLowerCase();
    }
}

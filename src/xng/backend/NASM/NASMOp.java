package xng.backend.NASM;

import xng.XIR.XIRInst;

public class NASMOp {
    public enum condType {
        A,AE,B,BE,C,E,G,GE,L,LE,NA,NAE,NB,NBE,NC,NE,NG,NGE,NL,NLE,NO,NP,NS,NZ,O,P,PE,PO,S,Z
    }
    public enum opType {
        _NONE, DB, DW, DD, DQ, DT, DO, DY, DZ,
        RESB, RESW, RESD, RESQ, REST, RESO, RESY, RESZ,
        AAA, AAD, AAM, AAS, ADC, ADD, AND, ARPL, BB0_RESET, BB1_RESET,
        BOUND, BSF, BSR, BSWAP, BT, BTC, BTR, BTS, CALL, CBW, CDQ,
        CDQE, CLC, CLD, CLI, CLTS, CMC, CMP, CMPSB, CMPSD, CMPSQ, CMPSW,
        CMPXCHG, CPUID, CPU_READ, CPU_WRITE, CQO, CWD, CWDE,
        DAA, DAS, DEC, DIV, DMINT, EMMS, ENTER, EQU, HLT, IBTS, ICEBP, IDIV,
        IMUL, IN, INC, INCBIN, INSB, INSD, INSW, INT, INTO, INVD, INVPCID,
        INVLPG, IRET, IRETD, IRETQ, IRETW, JCXZ, JECXZ, JRCXZ, JMP, JMPE,
        LAHF, LAR, LDS, LEA, LEAVE, LES, LFENCE, LFS, LGDT, LGS, LIDT, LLDT, LMSW,
        LOADALL, LODSB, LODSD, LODSQ, LODSW, LOOP, LOOPE, LOOPNE, LOOPNZ, LOOPZ,
        LSL, LSS, LTR, MFENCE, MONITOR, MOV, MOVD, MOVQ, MOVSB, MOVSD, MOVSQ, MOVSW,
        MOVSX, MOVSXD, MOVZX, MUL, MWAIT, MWAITX, NEG, NOP, NOT, OR, OUT, OUTSB, OUTSD,
        OUTSW, PACKSSDW, POP, PUSH, PUSHA, PUSHAD, PUSHAW, PXOR, RCL, RCR,
        RDSHR, RDMSR, RDPMC, RDTSC, RDTSCP, RET, RETF, RETN, ROL, ROR, RDM, RSDC, RSLDT,
        RSM, RSTS, SAHF, SAL, SALC, SAR, SBB, SCASB, SFENCE, SGDT, SHL, SHLD, SHR, SHRD,
        SIDT, SLDT, SKINIT, SMI, SMINT, SMSW, STC, STD, STI, STOSB, STR, SUB, SVDC, SVLDT,
        SVTS, SWAPGS, SYSCALL, SYSENTER, SYSEXIT, SYSRET, TEST, UD0, UMOV, VERR, VERW,
        FWAIT, XADD, XBTS, XCHG, XLATB, XLAT, XOR,
        JA, JAE, JB, JBE, JC, JE, JG, JGE, JL, JLE, JNA, JNAE, JNB, JNBE, JNC, JNE, JNG, JNGE,
        JNL, JNLE, JNO, JNP, JNS, JNZ, JO, JP, JPE, JPO, JS, JZ,
        SETA, SETAE, SETB, SETBE, SETC, SETE, SETG, SETGE, SETL, SETLE, SETNA, SETNAE,
        SETNB, SETNBE, SETNC, SETNE, SETNG, SETNGE, SETNL, SETNLE, SETNO, SETNP, SETNS,
        SETNZ, SETO, SETP, SETPE, SETPO, SETS, SETZ,
        CMOVA, CMOVAE, CMOVB, CMOVBE, CMOVC, CMOVE, CMOVG, CMOVGE, CMOVL, CMOVLE, CMOVNA,
        CMOVNAE, CMOVNB, CMOVNBE, CMOVNC, CMOVNE, CMOVNG, CMOVNGE, CMOVNL, CMOVNLE, CMOVNO,
        CMOVNP, CMOVNS, CMOVNZ, CMOVO, CMOVP, CMOVPE, CMOVPO, CMOVS, CMOVZ,
    }

    opType op;

    NASMOp(opType _op){
        op = _op;
    }

    static NASMOp.opType newJccRevOp(XIRInst.opType cmp){
        switch (cmp){
            case op_eq:
                return opType.JNE;
            case op_ne:
                return opType.JE;
            case op_le:
                return opType.JG;
            case op_ge:
                return opType.JL;
            case op_lt:
                return opType.JGE;
            case op_gt:
                return opType.JLE;
        }
        System.out.println("NASMOp:error:"+cmp.toString());
        return opType.JZ;
    }

    static NASMOp.opType newJccOp(XIRInst.opType cmp){
        switch (cmp){
            case op_eq:
                return opType.JE;
            case op_ne:
                return opType.JNE;
            case op_le:
                return opType.JLE;
            case op_ge:
                return opType.JGE;
            case op_lt:
                return opType.JL;
            case op_gt:
                return opType.JG;
        }
        System.out.println("NASMOp:error:"+cmp.toString());
        return opType.JNZ;
    }

    @Override
    public String toString() {
        return op.name().toLowerCase();
    }
}
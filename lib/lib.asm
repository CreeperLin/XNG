global _lib_print
global _lib_println
global _lib_getString
global _lib_getInt
global _lib_toString
global _lib_str_length
global _lib_str_ord
global _lib_str_substring
global _lib_str_parseInt
global _lib_strcat
global _lib_strcmp
global _lib_alloc
global main
extern strcmp
extern memcpy
extern strncpy
extern strlen
extern ungetc
extern _IO_getc
extern stdin
extern __ctype_b_loc
extern malloc
extern puts
extern fputs
extern stdout
SECTION .text
_lib_print:
        mov     rsi, qword [rel stdout]
        jmp     fputs
ALIGN   8
_lib_println:
        jmp     puts
        nop
ALIGN   16
_lib_getString:
        push    r13
        push    r12
        mov     edi, 256
        push    rbp
        push    rbx
        sub     rsp, 8
        call    malloc
        mov     rbp, rax
        call    __ctype_b_loc
        mov     rbx, rax
        nop
L_001:  mov     rdi, qword [rel stdin]
        mov     r12, qword [rbx]
        call    _IO_getc
        movsx   rax, al
        test    byte [r12+rax*2+1H], 20H
        mov     rdx, rax
        lea     rcx, [rax+rax]
        jnz     L_001
        cmp     al, -1
        jz      L_006
        mov     rax, qword [rbx]
        xor     r12d, r12d
        mov     r13, rbp
        movzx   eax, word [rax+rcx]
        test    ah, 20H
        jz      L_003
        jmp     L_006
ALIGN   8
L_002:  mov     rcx, qword [rbx]
        movsx   rax, dl
        add     r13, 1
        test    byte [rcx+rax*2+1H], 20H
        jnz     L_004
L_003:  mov     rdi, qword [rel stdin]
        mov     byte [r13], dl
        add     r12d, 1
        call    _IO_getc
        cmp     al, -1
        mov     edx, eax
        jnz     L_002
L_004:  movsxd  r12, r12d
L_005:  mov     byte [rbp+r12], 0
        add     rsp, 8
        mov     rax, rbp
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        ret
L_006:
        xor     r12d, r12d
        jmp     L_005
ALIGN   16
_lib_getInt:
        push    r12
        push    rbp
        push    rbx
        call    __ctype_b_loc
        mov     rbx, rax
ALIGN   8
L_007:  mov     rdi, qword [rel stdin]
        mov     rbp, qword [rbx]
        call    _IO_getc
        movsx   rdx, al
        test    byte [rbp+rdx*2+1H], 20H
        lea     rcx, [rdx+rdx]
        jnz     L_007
        mov     rdx, qword [rbx]
        cmp     al, 45
        movzx   edx, word [rdx+rcx]
        jz      L_013
        and     dh, 08H
        jnz     L_009
        cmp     al, -1
        jz      L_008
        cmp     al, 43
        jnz     L_014
L_008:  pop     rbx
        xor     eax, eax
        pop     rbp
        pop     r12
        ret
ALIGN   8
L_009:  xor     r12d, r12d
L_010:  xor     ebp, ebp
ALIGN   8
L_011:  mov     rdi, qword [rel stdin]
        lea     edx, [rbp+rbp*4]
        movsx   eax, al
        lea     ebp, [rax+rdx*2-30H]
        call    _IO_getc
        mov     rdx, qword [rbx]
        movsx   rcx, al
        test    byte [rdx+rcx*2+1H], 08H
        jnz     L_011
        mov     eax, ebp
        neg     eax
        test    r12d, r12d
        cmove   eax, ebp
L_012:  pop     rbx
        pop     rbp
        pop     r12
        ret
ALIGN   8
L_013:  and     dh, 08H
        jz      L_008
        mov     r12d, 1
        jmp     L_010
L_014:
        mov     rsi, qword [rel stdin]
        movsx   edi, al
        call    ungetc
        xor     eax, eax
        jmp     L_012
_lib_toString:
        push    rbx
        mov     rbx, rdi
        mov     edi, 256
        call    malloc
        xor     r10d, r10d
        test    rbx, rbx
        mov     r9, rax
        js      L_020
L_015:  movsxd  rcx, r10d
        mov     r8d, r10d
        mov     r11, qword 6666666666666667H
        add     rcx, r9
        mov     rdi, rcx
        jmp     L_017
ALIGN   8
L_016:  mov     r8d, esi
L_017:  mov     rax, rbx
        add     rdi, 1
        lea     esi, [r8+1H]
        imul    r11
        mov     rax, rbx
        sar     rax, 63
        sar     rdx, 2
        sub     rdx, rax
        lea     rax, [rdx+rdx*4]
        add     rax, rax
        sub     rbx, rax
        add     ebx, 48
        mov     byte [rdi-1H], bl
        test    rdx, rdx
        mov     rbx, rdx
        jnz     L_016
        sar     r8d, 1
        movsxd  rax, esi
        cmp     r10d, r8d
        mov     byte [r9+rax], 0
        jg      L_019
        sub     esi, r10d
        sub     r8d, r10d
        movsxd  rsi, esi
        lea     rax, [r9+rsi]
        sub     rsi, 1
        sub     rsi, r8
        lea     rdx, [r9+rsi]
ALIGN   16
L_018:  movzx   esi, byte [rcx]
        movzx   edi, byte [rax-1H]
        sub     rax, 1
        add     rcx, 1
        mov     byte [rcx-1H], dil
        mov     byte [rax], sil
        cmp     rdx, rax
        jnz     L_018
L_019:  mov     rax, r9
        pop     rbx
        ret
L_020:
        neg     rbx
        mov     byte [rax], 45
        mov     r10d, 1
        jmp     L_015
ALIGN   16
_lib_str_length:
        sub     rsp, 8
        call    strlen
        add     rsp, 8
        ret
ALIGN   8
_lib_str_ord:
        movsxd  rsi, esi
        movsx   eax, byte [rdi+rsi]
        ret
ALIGN   16
_lib_str_substring:
        push    r13
        push    r12
        mov     r12, rdi
        push    rbp
        push    rbx
        mov     ebx, edx
        sub     ebx, esi
        mov     ebp, esi
        lea     edi, [rbx+2H]
        sub     rsp, 8
        movsxd  rdi, edi
        call    malloc
        lea     edx, [rbx+1H]
        movsxd  rsi, ebp
        mov     rdi, rax
        add     rsi, r12
        mov     r13, rax
        movsxd  rdx, edx
        call    strncpy
        add     rsp, 8
        mov     rax, r13
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        ret
ALIGN   16
_lib_str_parseInt:
        push    rbx
        mov     rbx, rdi
        call    __ctype_b_loc
        mov     rsi, qword [rax]
        jmp     L_022
ALIGN   8
L_021:  add     rbx, 1
L_022:  movsx   rax, byte [rbx]
        mov     rdx, rax
        movzx   eax, word [rsi+rax*2]
        test    ah, 20H
        jnz     L_021
        cmp     dl, 45
        jz      L_026
        and     ax, 800H
        cmp     dl, 43
        mov     ecx, eax
        jz      L_023
        xor     eax, eax
        test    cx, cx
        jnz     L_023
        pop     rbx
        ret
ALIGN   8
L_023:  xor     edi, edi
        test    cx, cx
        jz      L_027
L_024:  xor     eax, eax
ALIGN   8
L_025:  lea     eax, [rax+rax*4]
        add     rbx, 1
        lea     eax, [rdx+rax*2-30H]
        movsx   edx, byte [rbx]
        movsx   rcx, dl
        test    byte [rsi+rcx*2+1H], 08H
        jnz     L_025
        mov     edx, eax
        cdqe
        neg     edx
        test    edi, edi
        movsxd  rdx, edx
        cmovne  rax, rdx
        pop     rbx
        ret
ALIGN   8
L_026:  test    ah, 08H
        jz      L_027
        mov     edi, 1
        jmp     L_024
L_027:
        xor     eax, eax
        pop     rbx
        ret
_lib_strcat:
        push    r14
        push    r13
        mov     r13, rsi
        push    r12
        push    rbp
        mov     r14, rdi
        push    rbx
        call    strlen
        mov     rdi, r13
        mov     rbx, rax
        call    strlen
        lea     rdi, [rbx+rax+1H]
        mov     rbp, rax
        call    malloc
        mov     rdx, rbx
        mov     r12, rax
        mov     rsi, r14
        mov     rdi, rax
        call    memcpy
        lea     rdi, [r12+rbx]
        lea     rdx, [rbp+1H]
        mov     rsi, r13
        call    memcpy
        pop     rbx
        mov     rax, r12
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        ret
ALIGN   16
_lib_strcmp:
        jmp     strcmp
        nop
ALIGN   16
_lib_alloc:
        push    r15
        push    r14
        mov     r15, rdi
        push    r13
        push    r12
        mov     r14, rsi
        push    rbp
        push    rbx
        sub     rsp, 216
        mov     rax, qword [rsi]
        mov     qword [rsp+18H], rdi
        mov     qword [rsp+20H], rsi
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+0C8H], rax
        call    malloc
        cmp     r15, 1
        mov     qword [rsp+40H], rax
        mov     qword [rax], rbx
        je      L_046
        test    rbx, rbx
        jle     L_046
        lea     r15, [r15-9H]
        lea     r13, [r14+48H]
        mov     qword [rsp+0A0H], 0
        mov     rax, r15
        mov     r15, r13
        mov     r13, rax
L_028:  mov     rax, qword [rsp+20H]
        mov     rax, qword [rax+8H]
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+58H], rax
        call    malloc
        cmp     qword [rsp+18H], 2
        mov     rdx, rax
        mov     qword [rsp+60H], rax
        mov     qword [rdx], rbx
        je      L_045
        cmp     qword [rsp+58H], 0
        jle     L_045
        mov     qword [rsp+0A8H], 0
L_029:  mov     rax, qword [rsp+20H]
        mov     rax, qword [rax+10H]
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+50H], rax
        call    malloc
        cmp     qword [rsp+18H], 3
        mov     rdx, rax
        mov     qword [rsp+68H], rax
        mov     qword [rdx], rbx
        je      L_044
        cmp     qword [rsp+50H], 0
        jle     L_044
        mov     qword [rsp+0B0H], 0
L_030:  mov     rax, qword [rsp+20H]
        mov     rax, qword [rax+18H]
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+70H], rax
        call    malloc
        cmp     qword [rsp+18H], 4
        mov     rdx, rax
        mov     qword [rsp+78H], rax
        mov     qword [rdx], rbx
        je      L_043
        cmp     qword [rsp+70H], 0
        jle     L_043
        mov     qword [rsp+0B8H], 0
L_031:  mov     rax, qword [rsp+20H]
        mov     rax, qword [rax+20H]
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+80H], rax
        call    malloc
        cmp     qword [rsp+18H], 5
        mov     rdx, rax
        mov     qword [rsp+88H], rax
        mov     qword [rdx], rbx
        je      L_042
        cmp     qword [rsp+80H], 0
        jle     L_042
        mov     qword [rsp+0C0H], 0
L_032:  mov     rax, qword [rsp+20H]
        mov     rax, qword [rax+28H]
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+90H], rax
        call    malloc
        cmp     qword [rsp+18H], 6
        mov     rdx, rax
        mov     qword [rsp+98H], rax
        mov     qword [rdx], rbx
        je      L_041
        cmp     qword [rsp+90H], 0
        jle     L_041
        mov     qword [rsp+48H], 0
        mov     r14, r15
L_033:  mov     rax, qword [rsp+20H]
        mov     rax, qword [rax+30H]
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+30H], rax
        call    malloc
        cmp     qword [rsp+18H], 7
        mov     rdx, rax
        mov     qword [rsp+38H], rax
        mov     qword [rdx], rbx
        je      L_039
        cmp     qword [rsp+30H], 0
        jle     L_039
        mov     qword [rsp+28H], 0
ALIGN   8
L_034:  mov     rax, qword [rsp+20H]
        mov     rax, qword [rax+38H]
        lea     rdi, [rax*8+8H]
        mov     rbx, rax
        mov     qword [rsp+10H], rax
        call    malloc
        cmp     qword [rsp+18H], 8
        mov     rdx, rax
        mov     qword [rsp+8H], rax
        mov     qword [rdx], rbx
        jz      L_038
        cmp     qword [rsp+10H], 0
        jle     L_038
        xor     r12d, r12d
ALIGN   8
L_035:  mov     rax, qword [rsp+20H]
        mov     rbx, qword [rax+40H]
        lea     rdi, [rbx*8+8H]
        call    malloc
        cmp     qword [rsp+18H], 9
        mov     rbp, rax
        mov     qword [rax], rbx
        jz      L_037
        test    rbx, rbx
        jle     L_037
        xor     r15d, r15d
ALIGN   8
L_036:  mov     rsi, r14
        mov     rdi, r13
        call    _lib_alloc
        mov     qword [rbp+r15*8+8H], rax
        lea     rax, [r15+2H]
        add     r15, 1
        cmp     rbx, rax
        jge     L_036
L_037:  mov     rax, qword [rsp+8H]
        mov     qword [rax+r12*8+8H], rbp
        lea     rax, [r12+1H]
        add     r12, 2
        cmp     qword [rsp+10H], r12
        jl      L_038
        mov     r12, rax
        jmp     L_035
ALIGN   8
L_038:  mov     rax, qword [rsp+28H]
        mov     rdx, qword [rsp+38H]
        mov     rcx, qword [rsp+8H]
        mov     qword [rdx+rax*8+8H], rcx
        lea     rdx, [rax+1H]
        add     rax, 2
        cmp     qword [rsp+30H], rax
        jl      L_039
        mov     qword [rsp+28H], rdx
        jmp     L_034
L_039:  mov     rax, qword [rsp+48H]
        mov     rdx, qword [rsp+98H]
        mov     rcx, qword [rsp+38H]
        mov     qword [rdx+rax*8+8H], rcx
        lea     rdx, [rax+1H]
        add     rax, 2
        cmp     qword [rsp+90H], rax
        jl      L_040
        mov     qword [rsp+48H], rdx
        jmp     L_033
L_040:  mov     r15, r14
L_041:  mov     rax, qword [rsp+0C0H]
        mov     rdx, qword [rsp+88H]
        mov     rcx, qword [rsp+98H]
        mov     qword [rdx+rax*8+8H], rcx
        lea     rdx, [rax+1H]
        add     rax, 2
        cmp     qword [rsp+80H], rax
        jl      L_042
        mov     qword [rsp+0C0H], rdx
        jmp     L_032
L_042:  mov     rax, qword [rsp+0B8H]
        mov     rdx, qword [rsp+78H]
        mov     rcx, qword [rsp+88H]
        mov     qword [rdx+rax*8+8H], rcx
        lea     rdx, [rax+1H]
        add     rax, 2
        cmp     qword [rsp+70H], rax
        jl      L_043
        mov     qword [rsp+0B8H], rdx
        jmp     L_031
L_043:  mov     rax, qword [rsp+0B0H]
        mov     rdx, qword [rsp+68H]
        mov     rcx, qword [rsp+78H]
        mov     qword [rdx+rax*8+8H], rcx
        lea     rdx, [rax+1H]
        add     rax, 2
        cmp     qword [rsp+50H], rax
        jl      L_044
        mov     qword [rsp+0B0H], rdx
        jmp     L_030
L_044:  mov     rax, qword [rsp+0A8H]
        mov     rdx, qword [rsp+60H]
        mov     rcx, qword [rsp+68H]
        mov     qword [rdx+rax*8+8H], rcx
        lea     rdx, [rax+1H]
        add     rax, 2
        cmp     qword [rsp+58H], rax
        jl      L_045
        mov     qword [rsp+0A8H], rdx
        jmp     L_029
L_045:  mov     rax, qword [rsp+0A0H]
        mov     rdx, qword [rsp+40H]
        mov     rcx, qword [rsp+60H]
        mov     qword [rdx+rax*8+8H], rcx
        lea     rdx, [rax+1H]
        add     rax, 2
        cmp     qword [rsp+0C8H], rax
        jl      L_046
        mov     qword [rsp+0A0H], rdx
        jmp     L_028
L_046:
        mov     rax, qword [rsp+40H]
        add     rsp, 216
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        pop     r15
        ret

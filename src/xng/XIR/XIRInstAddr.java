package xng.XIR;

import xng.opt.VarAnalyzer;
import xng.opt.VarInfo;

public class XIRInstAddr {
    public enum addrType{
        a_imm,a_reg,a_mem,a_stack,a_static,a_label
    }
    public addrType type;
    public int lit1; //num, reg1
    public int lit2; //reg2
    public int lit3; //scale
    public int lit4; //num
    public String str = null;
    public XIRInstAddr addr1;
    public XIRInstAddr addr2;
    public VarInfo info = new VarInfo();

    public XIRInstAddr(addrType _t, int _l1, int _l2, int _l3, int _l4){
        type = _t;
        lit1 = _l1;
        lit2 = _l2;
        lit3 = _l3;
        lit4 = _l4;
    }

    static int regCount = 1;
    static int stackCount = 1;

    public boolean equals(XIRInstAddr i) {
        return i != null && this.type == i.type && this.lit1 == i.lit1 && this.lit2 == i.lit2 && this.lit3 == i.lit3 && this.lit4==i.lit4;
    }

    public static XIRInstAddr newStaticAddr(String name, int size){
        System.out.println("XIRInstAddr:new static:"+name);
        XIRInstAddr inst = new XIRInstAddr(addrType.a_static,size,0,0,0);
        inst.str = name;
        return inst;
    }

    public static XIRInstAddr newStaticAddr(String name, String val){
        System.out.println("XIRInstAddr:new static:"+name);
        XIRInstAddr inst = new XIRInstAddr(addrType.a_static,0,0,0,0);
        inst.str = name;
        return inst;
    }

    public static XIRInstAddr newRegAddr(){
        System.out.println("XIRInstAddr:new reg:"+regCount);
        return new XIRInstAddr(addrType.a_reg,regCount++,0,0,0);
    }

    public static XIRInstAddr newRegAddr(int id){
        System.out.println("XIRInstAddr:new reg:"+id);
        return new XIRInstAddr(addrType.a_reg,id,0,0,0);
    }

    public static XIRInstAddr newStackAddr(int size){
        System.out.println("XIRInstAddr:new stack:"+stackCount);
        return new XIRInstAddr(addrType.a_stack,stackCount++,size,0,0);
    }

//    public static XIRInstAddr newStackAddr(int id, int size, int ofs){
//        int c = stackCount++;
//        System.out.println("XIRInstAddr:new stack:"+c+":"+ofs);
//        return new XIRInstAddr(addrType.a_stack,c,size,ofs,id==0?c:id);
//    }

    public static XIRInstAddr newJumpAddr(XCFGNode node){
        XIRInstAddr addr = new XIRInstAddr(addrType.a_label,node.nodeID,0,0,0);
        addr.str = node.name;
        System.out.println("XIRInstAddr:new jump:"+addr.str);
        return addr;
    }

    public static XIRInstAddr newJumpAddr(String label){
        XIRInstAddr addr = new XIRInstAddr(addrType.a_label,0,0,0,0);
        addr.str = label;
        System.out.println("XIRInstAddr:new jump:"+addr.str);
        return addr;
    }


    public static XIRInstAddr newImmAddr(int l1, int l2){
        System.out.println("XIRInstAddr:new imm:"+l1+' '+l2);
        return new XIRInstAddr(addrType.a_imm,l1,l2,0,0);
    }

    public static XIRInstAddr newMemAddr(XIRInstAddr base, XIRInstAddr ofs, int scale, int num){
        System.out.println("XIRInstAddr:new mem:"+base+' '+ofs+' '+scale+' '+num);
        XIRInstAddr inst = new XIRInstAddr(addrType.a_mem,0,0,scale,num);
        inst.addr1 = base;
        inst.addr2 = ofs;
        return inst;
    }

    public boolean isConst(){
        return (type == addrType.a_imm) || (info.type == VarInfo.valType.v_const);
    }

    public int getConst() {
        return (type == addrType.a_imm) ? lit1 : ((info.type == VarInfo.valType.v_const)? info.constValue : 0);
    }

    @Override
    public String toString() {
        switch (type){
            case a_mem:
                if (addr1==null) return "("+type.toString()+' '+lit1 + ' '+ lit2 + ' '+ lit3 +' '+ lit4 +") ";
                return "("+type.toString()+' '+addr1 + ' '+ addr2 + ' '+ lit3 +' '+ lit4 +") ";
            case a_reg:
                return "("+type.toString()+' '+lit1 + ") ";
            case a_imm:
                return "("+type.toString()+' '+lit1 + ") ";
            case a_stack:
                return "("+type.toString()+' '+lit1+' '+ lit2 + ") ";
            case a_static:
                return "("+type.toString()+' ' + str + ' '+lit1+ ") ";
            case a_label:
                return "("+type.toString()+' ' + str+ ")";
        }
        return null;
    }
}

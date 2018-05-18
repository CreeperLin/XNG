package xng.XIR;

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
        return i != null && this.type == i.type && this.lit1 == i.lit1 && this.lit2 == i.lit2;
    }

    public static XIRInstAddr newStaticAddr(String name, int size){
        System.out.println("XIRInstAddr:new static:"+name);
        XIRInstAddr inst = new XIRInstAddr(addrType.a_static,size,0,0,0);
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

    public static XIRInstAddr newJumpAddr(XCFGNode node){
        System.out.println("XIRInstAddr:new jump:"+node.nodeID);
        return new XIRInstAddr(addrType.a_label,node.nodeID,0,0,0);
    }

    public static XIRInstAddr newImmAddr(int l1, int l2){
        System.out.println("XIRInstAddr:new imm:"+l1+' '+l2);
        return new XIRInstAddr(addrType.a_imm,l1,l2,0,0);
    }

    public static XIRInstAddr newMemAddr(XIRInstAddr base, XIRInstAddr ofs){
        System.out.println("XIRInstAddr:new mem:"+base+' '+ofs);
        return new XIRInstAddr(addrType.a_mem,base.lit1,ofs.lit1,0,0);
    }

    public static XIRInstAddr newMemAddr(XIRInstAddr base, XIRInstAddr ofs, int scale, int num){
        System.out.println("XIRInstAddr:new mem:"+base+' '+ofs+' '+scale+' '+num);
        return new XIRInstAddr(addrType.a_mem,base.lit1,ofs.lit1,scale,num);
    }

    public static XIRInstAddr newMemAddr(XIRInstAddr base){
        System.out.println("XIRInstAddr:new mem:"+base);
        return new XIRInstAddr(addrType.a_mem,base.lit1,0,0,0);
    }

    @Override
    public String toString() {
        switch (type){
            case a_mem:
                return "("+type.toString()+' '+lit1 + ' '+ lit2 + ' '+ lit3 +' '+ lit4 +") ";
            case a_reg:
                return "("+type.toString()+' '+lit1 + ") ";
            case a_imm:
                return "("+type.toString()+' '+lit1 + ") ";
            case a_stack:
                return "("+type.toString()+' '+lit1+' '+ lit2 + ") ";
            case a_static:
                return "("+type.toString()+' ' + str + ' '+lit1+ ") ";
            case a_label:
                return "("+type.toString()+' ' + lit1 + ")";
        }
        return null;
    }
}

package xng.frontend.AST;

public enum XASTNodeID {
    cu_root,

    s_none,s_block,s_if,s_for,s_while,
    s_ret,s_cont,s_break,s_vardecl,s_classdecl,
    s_funcdecl,s_plist,s_expr,

    e_none,e_mem,e_idx,e_call,e_add,
    e_sub,e_pos,e_neg,e_mult,e_div,
    e_mod,e_inc_p,e_inc_s,e_dec_p,e_dec_s,
    e_not,e_bneg,e_band,e_bor,e_bxor,
    e_shl,e_shr,e_land,e_lor,e_asgn,
    e_new,e_eq,e_ne,e_le,e_ge,
    e_gt,e_lt,e_list,e_creator,e_prim,

    p_lit_int,p_lit_bool,p_lit_str,p_lit_null,p_this,
    p_id,p_expr,

    t_void,t_int,t_bool,t_str,t_class
}

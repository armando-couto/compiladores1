package util;

public enum Constantes {

    // simbolos
    TS_FP(")"),    // fp
    TS_AP("("),    // ap
    TS_SOMA("+"),    // +
    TS_MENOS("-"),    //-
    TS_MUL("*"),        //*
    TS_DIV("/"),    // '/'
    TS_SM("<"),        // <
    TS_SMI("<="),        // <=
    TS_SN(">"),        // >
    TS_SNI(">="),        // >=
    TS_IGUAL("="),    // =
    TS_IGUALDADE("=="),    // ==
    TS_DIF("~="),    //~=
    TS_VIRG(","),    // ','
    TS_DOISPT(":"),    // :

    //palavras reservadas
    TS_AND("and"),        // and
    TS_OR("or"),        // or
    TS_TRUE("true"),
    TS_FALSE("false"),
    TS_THEN("then"),
    TS_ELSEIF("elseif"),
    TS_ELSE("else"),
    TS_END("end"),
    TS_IF("if"),
    TS_UNTIL("until"),
    TS_REPEAT("repeat"),
    TS_DO("do"),
    TS_WHILE("while"),

    //terminais especificos como nomes e numeros inteiros
    TS_NOME,
    TS_NUMERO,
    TS_COMENTARIO,

    //sao keywords fixas
    TS_MAIN("begin"),
    TS_FUNCTION("function"),
    TS_PRINT("print"),
    TS_SCANNER("scanner"),

    TS_$,        // $, in this case corresponds to '\0'
    TS_INVALID,    // invalid token

    // Non-terminal symbols:
    NTS_INICIO,        // S
    NTS_CHUNK,
    NTS_BLOCK,
    NTS_STAT,
    NTS_G_LIN,        //G'
    NTS_STAT_LIN,    //Stat'
    NTS_VARLIST,
    NTS_VL,
    NTS_VAR,
    NTS_EXPLIST,
    NTS_EXPLIST_LIN,    //Explist'
    NTS_EXP,
    NTS_PREFIXEXP,
    NTS_EQNUM,
    NTS_EQNUM_LIN,        //EqNum'
    NTS_FBINOP,
    NTS_BINOP;

    private String str;

    private Constantes() {
        this.str = "nada";
    }

    private Constantes(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}

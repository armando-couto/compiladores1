package util;


public final class Token implements Comparable<Token> {
    private String valor;
    private Constantes classe;
    private byte posEnd;

    public Token(String valor, Constantes classe) {
        this.valor = valor;
        this.classe = classe;
    }

    public String getValor() {
        return valor;
    }

    public Constantes getClasse() {
        return classe;
    }

    public byte getPosEnd() {
        return posEnd;
    }

    public void setPosEnd(byte posEnd) {
        this.posEnd = posEnd;
    }

    public boolean igual(String tk) {
        if (valor.equals(tk))
            return true;

        return false;
    }

    @Override
    public boolean equals(Object arg) {

        //nao eh instacia do param
        if (!(arg instanceof Token))
            return false;

        Token tk = (Token) arg;

        //eh igual
        if ((tk.valor.equals(valor)) && (tk.classe.equals(classe)))
            return true;

        //conclui q eh diferente
        return false;
    }

    public String toString() {
        return valor + "   -->   " + classe + "\n";
    }

    @Override
    public int compareTo(Token o) {
        return valor.compareTo(o.getValor());
    }

}
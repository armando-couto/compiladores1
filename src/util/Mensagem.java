package util;

public class Mensagem {
    private StringBuilder sb;
    private boolean certo;

    public Mensagem(StringBuilder sb, boolean certo) {
        this.sb = sb;
        this.certo = certo;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public boolean isCerto() {
        return certo;
    }
}

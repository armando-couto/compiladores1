package gerador;

public enum SimbolosAssembly {
    LSP((byte) 0x4F, "endereco"), JMP((byte) 0x5A, "endereco"), LDI((byte) 0x44, "inteiro"),
    ADI((byte) 0x20, "inteiro"), SUI((byte) 0x21, "inteiro"), MUI((byte) 0x22, "inteiro"), DVI((byte) 0x23, "inteiro"),
    LOD((byte) 0x40, "endereco"), STO((byte) 0x41, "endereco"), OUT((byte) 0x58), IN((byte) 0x57), JF((byte) 0x5C, "endereco"),
    EQ((byte) 0x20), NE((byte) 0x21), GT((byte) 0x22), GE((byte) 0x23), LT((byte) 0x24), LE((byte) 0x25), STOP((byte) 0x61);

    private byte hexa;
    private String args;

    private SimbolosAssembly(byte hexa) {
        this(hexa, null);
    }

    private SimbolosAssembly(byte hexa, String args) {
        this.hexa = hexa;
        this.args = args;
    }

    public byte getHexa() {
        return hexa;
    }

    public String getArgs() {
        return args;
    }

}

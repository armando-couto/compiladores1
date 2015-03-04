package gerador;


import java.util.Set;
import java.util.TreeSet;

import util.Constantes;
import util.Token;
import util.Util;
import util.ed.PilhaAdv;
import util.ed.Pilha;

public final class GeradorCodigo {
    private static byte dataStream[] = null;
    private static int quantidadeVariaveis = 0, endInicialInstrucao;
    private static PilhaAdv<Token> tokens;


    public static void executa(Pilha<Token> tokens) {
        executa(tokens, 2048);
    }

    public static void executa(Pilha<Token> tokens, int tamCod) {
        dataStream = new byte[tamCod];

        GeradorCodigo.tokens = (PilhaAdv<Token>) tokens;

        areaDados();

        machineBinary();
    }

    //a aparte de inicializacao do datastream foi feito...
    private static void machineBinary() {
        int pos = 0;
        dataStream[pos++] = SimbolosAssembly.LSP.getHexa();
        dataStream[pos++] = (byte) 0x00;
        dataStream[pos++] = (byte) 10;
        dataStream[pos++] = SimbolosAssembly.JMP.getHexa();

        //diz apartir da aonde come�a a instrucao primeira instrucao de codigo
        byte endVar[] = Util.intToArrayByte(endInicialInstrucao);
        dataStream[pos++] = endVar[1];
        dataStream[pos++] = endVar[0];

        //inicializa os espa�os das variaveis
        for (int i = 0; i < quantidadeVariaveis; i++) {
            dataStream[pos++] = (byte) 0x0;
            dataStream[pos++] = (byte) 0x0;
        }

        Pilha<Byte> pilha = new PilhaAdv<Byte>();

        int state = 0;
        for (Token tk : tokens) {

            switch (state) {

                case 0:

                    switch (tk.getClasse()) {
                        case TS_SCANNER:
                            dataStream[pos++] = SimbolosAssembly.IN.getHexa();
                            state = 1;
                            break;
                        case TS_PRINT:
                            dataStream[pos++] = SimbolosAssembly.OUT.getHexa();
                            state = 1;
                            break;
                        case TS_IF:
                            state = 6;
                            break;
                    }
                    break;

                case 1:
                    switch (tk.getClasse()) {
                        case TS_NOME:
                            dataStream[pos++] = SimbolosAssembly.STO.getHexa();
                            dataStream[pos++] = tk.getPosEnd();
                            dataStream[pos++] = (byte) 0x0;

                            dataStream[pos++] = SimbolosAssembly.LOD.getHexa();
                            dataStream[pos++] = tk.getPosEnd();
                            dataStream[pos++] = (byte) 0x0;

                            state = 0;//volta pro estado = 0
                            break;

                        case TS_NUMERO:
                            dataStream[pos++] = SimbolosAssembly.LDI.getHexa();

                            endVar = Util.intToArrayByte(Integer.parseInt(tk.getValor()));
                            dataStream[pos++] = endVar[1];
                            dataStream[pos++] = endVar[0];

                            state = 0;
                            break;
                    }
                    break;

                case 6:
                    switch (tk.getClasse()) {
                        case TS_NOME:
                            pilha.push(SimbolosAssembly.LOD.getHexa());
                            pilha.push(tk.getPosEnd());
                            pilha.push((byte) 0x0);

                            state = 7;//volta pro estado = 0
                            break;

                        case TS_NUMERO:
                            dataStream[pos++] = SimbolosAssembly.LDI.getHexa();

                            endVar = Util.intToArrayByte(Integer.parseInt(tk.getValor()));
                            pilha.push(endVar[1]);
                            pilha.push(endVar[0]);

                            state = 7;
                            break;
                    }
                    break;

                //sinal
                case 7:
                    switch (tk.getClasse()) {
                        case TS_IGUALDADE:
                            pilha.push(SimbolosAssembly.EQ.getHexa());
                            pilha.push(SimbolosAssembly.JF.getHexa());
                            pilha.push((byte) 0x0);

                            state = 8;

                            break;
                    }
                    break;
            }
        }
    }

    private static void areaDados() {

        Set<Token> quantVar = new TreeSet<Token>();
        quantidadeVariaveis = 0;

        int count = 0;
        for (Token tk : tokens) {
            //var unicas
            if (tk.getClasse() == Constantes.TS_NOME && !quantVar.contains(tk)) {
                count++;
                tk.setPosEnd((byte) (4 + (2 * count)));
                quantVar.add(tk);
            }
        }

        for (Token tk : tokens) {
            for (Token tkUniq : quantVar) {
                if (tk.getClasse() == Constantes.TS_NOME && tk.getValor().equals(tkUniq.getValor())) {
                    tk.setPosEnd(tkUniq.getPosEnd());
                }
            }
        }

        quantidadeVariaveis = quantVar.size();

        endInicialInstrucao = 6 + (2 * quantidadeVariaveis);

    }
}

package principal;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.Mensagem;
import util.Token;
import util.Util;
import util.ed.Pilha;

public final class Controle {
    private static final long serialVersionUID = 5569197692040753259L;

    public static String PATH = System.getProperty("user.dir") + "/src/util/sources/cod1.lua";

    public static void main(String[] args) {
        // chama o arquivo na inicializacao
        try {
            new Controle().processaDados(Util.acessoArquivo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processaDados(String str) {
        Pilha<Token> tokens = AnalisadorLexico.executa(str);
        Mensagem msg = AnalisadorSintatico.executa(tokens);

        System.out.println(msg.getSb().toString());

        if (msg.isCerto()) {
            //ai pergunta se deseja procedir para a geracao de cod
//			GeradorCodigo.executa(tokens);
        }
    }

}
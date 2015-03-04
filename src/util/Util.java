package util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Timer;

import principal.Controle;


public final class Util {

    //nao pode ser instanciado
    private Util() {
    }

    public static String acessoArquivo() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(Controle.PATH));
        StringBuilder sb = new StringBuilder();
        while (br.ready())
            sb.append(br.readLine()).append("@");

        return sb.toString();
    }

    public static String acessoArquivo(File file) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        while (br.ready())
            sb.append(br.readLine()).append("@");

        return sb.toString();
    }

    public static void disparaReqGC() {
        new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.gc();
            }
        }).start();
    }

    private static Dimension tamanhoTela;
    private static Container tela;

    public static void centralizaTela(Container tela) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        tamanhoTela = tk.getScreenSize();//pega o tamanho da tela

        Util.tela = tela;

        tela.setLocation(getWidthCentralizada(), getHeigthCentralizada());
    }

    private static int getWidthCentralizada() {
        return ((tamanhoTela.width - tela.getSize().width) / 2);
    }

    private static int getHeigthCentralizada() {
        return ((tamanhoTela.height - tela.getSize().height) / 2);
    }

    public static byte[] intToArrayByte(int numero) {
        byte hexToByte[] = new byte[2];

        int div = numero / 256;
        int mov = numero % 256;

        hexToByte[0] = (byte) div;
        hexToByte[1] = (byte) mov;

        return hexToByte;
    }
}

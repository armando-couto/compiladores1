package principal;

import java.util.HashMap;
import java.util.Map;

import util.Mensagem;
import util.Constantes;
import util.Token;
import util.ed.PilhaAdv;
import util.ed.Pilha;

public final class AnalisadorSintatico {

    private static Map<Constantes, Map<Constantes, Integer>> tabela = null;
    private static PilhaAdv<Constantes> pilha = null;

    private AnalisadorSintatico() {

    }

    private static void init() {
        tabela = new HashMap<Constantes, Map<Constantes, Integer>>();
        pilha = new PilhaAdv<Constantes>();

        pilha.push(Constantes.TS_$); //$
        pilha.push(Constantes.NTS_INICIO);  //Inicio

        configuraTabela();
    }

    private static void configuraTabela() {
        int it = 1;
        Map<Constantes, Integer> tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_FUNCTION, it++);

        tabela.put(Constantes.NTS_INICIO, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_ELSEIF, it++);
        tabelaTerminal.put(Constantes.TS_ELSE, it++);
        tabelaTerminal.put(Constantes.TS_SCANNER, it++);
        tabelaTerminal.put(Constantes.TS_PRINT, it++);
        tabelaTerminal.put(Constantes.TS_END, it++);
        tabelaTerminal.put(Constantes.TS_IF, it++);
        tabelaTerminal.put(Constantes.TS_UNTIL, it++);
        tabelaTerminal.put(Constantes.TS_REPEAT, it++);
        tabelaTerminal.put(Constantes.TS_WHILE, it++);

        tabela.put(Constantes.NTS_CHUNK, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_ELSEIF, it++);
        tabelaTerminal.put(Constantes.TS_ELSE, it++);
        tabelaTerminal.put(Constantes.TS_SCANNER, it++);
        tabelaTerminal.put(Constantes.TS_PRINT, it++);
        tabelaTerminal.put(Constantes.TS_END, it++);
        tabelaTerminal.put(Constantes.TS_IF, it++);
        tabelaTerminal.put(Constantes.TS_UNTIL, it++);
        tabelaTerminal.put(Constantes.TS_REPEAT, it++);
        tabelaTerminal.put(Constantes.TS_WHILE, it++);

        tabela.put(Constantes.NTS_BLOCK, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_SCANNER, it++);
        tabelaTerminal.put(Constantes.TS_PRINT, it++);
        tabelaTerminal.put(Constantes.TS_IF, it++);
        tabelaTerminal.put(Constantes.TS_REPEAT, it++);
        tabelaTerminal.put(Constantes.TS_WHILE, it++);

        tabela.put(Constantes.NTS_STAT, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_ELSE, it++);
        tabelaTerminal.put(Constantes.TS_END, it++);

        tabela.put(Constantes.NTS_G_LIN, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_ELSEIF, it++);
        tabelaTerminal.put(Constantes.TS_ELSE, it++);
        tabelaTerminal.put(Constantes.TS_END, it++);

        tabela.put(Constantes.NTS_STAT_LIN, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);

        tabela.put(Constantes.NTS_VARLIST, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_VIRG, it++);
        tabelaTerminal.put(Constantes.TS_IGUAL, it++);

        tabela.put(Constantes.NTS_VL, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);

        tabela.put(Constantes.NTS_VAR, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_NUMERO, it++);
        tabelaTerminal.put(Constantes.TS_AP, it++);
        tabelaTerminal.put(Constantes.TS_TRUE, it++);
        tabelaTerminal.put(Constantes.TS_FALSE, it++);

        tabela.put(Constantes.NTS_EXPLIST, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_VIRG, it++);
        tabelaTerminal.put(Constantes.TS_ELSEIF, it++);
        tabelaTerminal.put(Constantes.TS_ELSE, it++);
        tabelaTerminal.put(Constantes.TS_SCANNER, it++);
        tabelaTerminal.put(Constantes.TS_PRINT, it++);
        tabelaTerminal.put(Constantes.TS_END, it++);
        tabelaTerminal.put(Constantes.TS_IF, it++);
        tabelaTerminal.put(Constantes.TS_UNTIL, it++);
        tabelaTerminal.put(Constantes.TS_REPEAT, it++);
        tabelaTerminal.put(Constantes.TS_WHILE, it++);

        tabela.put(Constantes.NTS_EXPLIST_LIN, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_NUMERO, it++);
        tabelaTerminal.put(Constantes.TS_AP, it++);
        tabelaTerminal.put(Constantes.TS_TRUE, it++);
        tabelaTerminal.put(Constantes.TS_FALSE, it++);

        tabela.put(Constantes.NTS_EXP, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_AP, it++);

        tabela.put(Constantes.NTS_PREFIXEXP, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_NUMERO, it++);
        tabelaTerminal.put(Constantes.TS_AP, it++);

        tabela.put(Constantes.NTS_EQNUM, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_DIF, it++);
        tabelaTerminal.put(Constantes.TS_OR, it++);
        tabelaTerminal.put(Constantes.TS_AND, it++);
        tabelaTerminal.put(Constantes.TS_IGUALDADE, it++);
        tabelaTerminal.put(Constantes.TS_SNI, it++);
        tabelaTerminal.put(Constantes.TS_SN, it++);
        tabelaTerminal.put(Constantes.TS_SMI, it++);
        tabelaTerminal.put(Constantes.TS_SM, it++);
        tabelaTerminal.put(Constantes.TS_DIV, it++);
        tabelaTerminal.put(Constantes.TS_MUL, it++);
        tabelaTerminal.put(Constantes.TS_MENOS, it++);
        tabelaTerminal.put(Constantes.TS_SOMA, it++);
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_FP, it++);
        tabelaTerminal.put(Constantes.TS_VIRG, it++);
        tabelaTerminal.put(Constantes.TS_THEN, it++);
        tabelaTerminal.put(Constantes.TS_ELSEIF, it++);
        tabelaTerminal.put(Constantes.TS_ELSE, it++);
        tabelaTerminal.put(Constantes.TS_SCANNER, it++);
        tabelaTerminal.put(Constantes.TS_PRINT, it++);
        tabelaTerminal.put(Constantes.TS_END, it++);
        tabelaTerminal.put(Constantes.TS_IF, it++);
        tabelaTerminal.put(Constantes.TS_UNTIL, it++);
        tabelaTerminal.put(Constantes.TS_REPEAT, it++);
        tabelaTerminal.put(Constantes.TS_DO, it++);
        tabelaTerminal.put(Constantes.TS_WHILE, it++);

        tabela.put(Constantes.NTS_EQNUM_LIN, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_NOME, it++);
        tabelaTerminal.put(Constantes.TS_NUMERO, it++);
        tabelaTerminal.put(Constantes.TS_AP, it++);

        tabela.put(Constantes.NTS_FBINOP, tabelaTerminal);

        tabelaTerminal = new HashMap<Constantes, Integer>();
        tabelaTerminal.put(Constantes.TS_DIF, it++);
        tabelaTerminal.put(Constantes.TS_OR, it++);
        tabelaTerminal.put(Constantes.TS_AND, it++);
        tabelaTerminal.put(Constantes.TS_IGUALDADE, it++);
        tabelaTerminal.put(Constantes.TS_SNI, it++);
        tabelaTerminal.put(Constantes.TS_SN, it++);
        tabelaTerminal.put(Constantes.TS_SMI, it++);
        tabelaTerminal.put(Constantes.TS_SM, it++);
        tabelaTerminal.put(Constantes.TS_DIV, it++);
        tabelaTerminal.put(Constantes.TS_MUL, it++);
        tabelaTerminal.put(Constantes.TS_MENOS, it++);
        tabelaTerminal.put(Constantes.TS_SOMA, it++);

        tabela.put(Constantes.NTS_BINOP, tabelaTerminal);

        //FIM DO CRIACAO DA TABELA
    }

    public static Mensagem executa(Pilha<Token> token) {

        Pilha<Token> tokenTemp = token.clone();//copia a pilha
        init();

        StringBuilder sb = new StringBuilder();
        sb.append("Analisador lexico foi executado com sucesso!").append("\n\n");

        boolean simbolosProblema = false;

        while (!pilha.isEmpty()) {
            Token tm = tokenTemp.peek();
            Constantes peek = pilha.peek();

            if (tm == null) {
                sb.append("\n");
                if (pilha.size() == 1 && pilha.peek() == Constantes.TS_$) {

                    if (simbolosProblema) {
                        sb.append("Analisador Sintatico terminou com alguns erros!").append("\n");
                        sb.append("Existem simbolos que n�o foram reconhecidos pela gramatica").append("\n");
                    } else {
                        sb.append("Analisador Sintatico terminou com sucesso!").append("\n");
                        sb.append("Sobrou na pilha apenas: " + pilha.peek() + ", esse fonte � reconhecido pela gramatica").append("\n");
                    }
                } else {
                    sb.append("Analisador Sintatico terminou com alguns erros!").append("\n");
                    sb.append("Existe " + pilha.size() + " itens na pilha e acabou os tokens para analise").append("\n");
                    simbolosProblema = true;
                }
                break;//sai...
            }

            //quando acha um terminal
            if (tm.getClasse() == peek) {
                sb.append("Simbolo reconhecido: '" + tm.getValor() + "' | " + tm.getClasse()).append("\n");
                pilha.pop();

                //anda com o prox elemento a ser reconhecido!
                tokenTemp.pop();

            } else {

                //obtem o simbolo do analisador lexico
                Constantes simbol = tm.getClasse();

                Map<Constantes, Integer> tableNonTerminal = tabela.get(peek);

                Integer numRegraTabela = 666;
                if (tableNonTerminal != null) {
                    //codigo def na associacao entre os elementos
                    numRegraTabela = tableNonTerminal.get(simbol);

                    //ainda nao foi encontrado!
                    if (numRegraTabela == null)
                        numRegraTabela = 666;

                }

                //operacao default de pop da pilha
                pilha.pop();

                switch (numRegraTabela) {
                    //Inicio -> function main ap fp doispt Chunk end (k)
                    case 1:

                        pilha.push(Constantes.TS_END);
                        pilha.push(Constantes.NTS_CHUNK);
                        pilha.push(Constantes.TS_DOISPT);
                        pilha.push(Constantes.TS_FP);
                        pilha.push(Constantes.TS_AP);
                        pilha.push(Constantes.TS_MAIN);
                        pilha.push(Constantes.TS_FUNCTION);

                        break;

                    // Chunk -> Stat Chunk (k)
                    case 2:
                    case 5:
                    case 6:
                    case 8:
                    case 10:
                    case 11:
                        pilha.push(Constantes.NTS_CHUNK);
                        pilha.push(Constantes.NTS_STAT);

                        break;

                    //quando um n�o terminal ponta para um '&epsilon'
                    case 3:
                    case 4:
                    case 7:
                    case 9:
                    case 29:
                    case 31:
                    case 32:
                    case 35:
                    case 42:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 74:
                    case 75:
                    case 76:
                    case 77:
                    case 78:
                    case 79:
                    case 80:
                    case 81:
                    case 82:
                    case 83:
                    case 84:
                    case 85:
                    case 86:
                    case 87:
                        //operacao vazia mesmo
                        break;

                    //Block -> Chunk (k)
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:

                        pilha.push(Constantes.NTS_CHUNK);
                        break;

                    //Stat -> Varlist igual Explist (k)
                    case 22:

                        pilha.push(Constantes.NTS_EXPLIST);
                        pilha.push(Constantes.TS_IGUAL);
                        pilha.push(Constantes.NTS_VARLIST);

                        break;

                    //Stat -> scanner ap name fp
                    case 23:
                        pilha.push(Constantes.TS_FP);
                        pilha.push(Constantes.TS_NOME);
                        pilha.push(Constantes.TS_AP);
                        pilha.push(Constantes.TS_SCANNER);

                        break;

                    //Stat -> print ap name fp
                    case 24:
                        pilha.push(Constantes.TS_FP);
                        pilha.push(Constantes.TS_NOME);
                        pilha.push(Constantes.TS_AP);
                        pilha.push(Constantes.TS_PRINT);
                        break;

                    //	Stat -> if Exp then Block Stat' G' end (k)
                    case 25:

                        pilha.push(Constantes.TS_END);
                        pilha.push(Constantes.NTS_G_LIN);
                        pilha.push(Constantes.NTS_STAT_LIN);
                        pilha.push(Constantes.NTS_BLOCK);
                        pilha.push(Constantes.TS_THEN);
                        pilha.push(Constantes.NTS_EXP);
                        pilha.push(Constantes.TS_IF);

                        break;

                    //Stat -> repeat Block until Exp (k)
                    case 26:

                        pilha.push(Constantes.NTS_EXP);
                        pilha.push(Constantes.TS_UNTIL);
                        pilha.push(Constantes.NTS_BLOCK);
                        pilha.push(Constantes.TS_REPEAT);

                        break;

                    //Stat -> while Exp do Block end (k)
                    case 27:
                        pilha.push(Constantes.TS_END);
                        pilha.push(Constantes.NTS_BLOCK);
                        pilha.push(Constantes.TS_DO);
                        pilha.push(Constantes.NTS_EXP);
                        pilha.push(Constantes.TS_WHILE);

                        break;

                    //G' -> else Block (k)
                    case 28:
                        pilha.push(Constantes.NTS_BLOCK);
                        pilha.push(Constantes.TS_ELSE);

                        break;

                    //Stat' -> elseif Exp then Block Stat' (k)
                    case 30:
                        pilha.push(Constantes.NTS_STAT_LIN);
                        pilha.push(Constantes.NTS_BLOCK);
                        pilha.push(Constantes.TS_THEN);
                        pilha.push(Constantes.NTS_EXP);
                        pilha.push(Constantes.TS_ELSEIF);

                        break;

                    //Varlist -> Var VL (k)
                    case 33:
                        pilha.push(Constantes.NTS_VL);
                        pilha.push(Constantes.NTS_VAR);

                        break;

                    //VL -> virg Var VL (k)
                    case 34:
                        pilha.push(Constantes.NTS_VL);
                        pilha.push(Constantes.NTS_VAR);
                        pilha.push(Constantes.TS_VIRG);
                        break;

                    //Var -> name (k)
                    case 36:
                        pilha.push(Constantes.TS_NOME);
                        break;

                    //Explist -> Exp Explist' (k)
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                        pilha.push(Constantes.NTS_EXPLIST_LIN);
                        pilha.push(Constantes.NTS_EXP);
                        break;

                    //Explist' -> virg Exp Explist' (k)
                    case 43:
                        pilha.push(Constantes.NTS_EXPLIST_LIN);
                        pilha.push(Constantes.NTS_EXP);
                        pilha.push(Constantes.TS_VIRG);
                        break;

                    //Exp -> EqNum (k)
                    case 53:
                    case 54:
                    case 55:
                        pilha.push(Constantes.NTS_EQNUM);
                        break;

                    //Exp -> true (k)
                    case 56:
                        pilha.push(Constantes.TS_TRUE);
                        break;

                    //Exp -> false
                    case 57:
                        pilha.push(Constantes.TS_FALSE);
                        break;

                    //Prefix_exp -> ap EqNum fp
                    case 58:
                        pilha.push(Constantes.TS_FP);
                        pilha.push(Constantes.NTS_EQNUM);
                        pilha.push(Constantes.TS_AP);
                        break;

                    //EqNum -> name EqNum'
                    case 59:
                        pilha.push(Constantes.NTS_EQNUM_LIN);
                        pilha.push(Constantes.TS_NOME);
                        break;

                    //EqNum -> num EqNum'
                    case 60:
                        pilha.push(Constantes.NTS_EQNUM_LIN);
                        pilha.push(Constantes.TS_NUMERO);
                        break;

                    //EqNum -> Prefix_exp EqNum'
                    case 61:
                        pilha.push(Constantes.NTS_EQNUM_LIN);
                        pilha.push(Constantes.NTS_PREFIXEXP);
                        break;

                    //EqNum' -> Binop FBinop
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                    case 71:
                    case 72:
                    case 73:
                        pilha.push(Constantes.NTS_FBINOP);
                        pilha.push(Constantes.NTS_BINOP);
                        break;

                    //FBinop -> name EqNum'
                    case 88:
                        pilha.push(Constantes.NTS_EQNUM_LIN);
                        pilha.push(Constantes.TS_NOME);
                        break;

                    //FBinop -> num EqNum'
                    case 89:
                        pilha.push(Constantes.NTS_EQNUM_LIN);
                        pilha.push(Constantes.TS_NUMERO);
                        break;

                    //FBinop -> Prefix_exp EqNum'
                    case 90:
                        pilha.push(Constantes.NTS_EQNUM_LIN);
                        pilha.push(Constantes.NTS_PREFIXEXP);
                        break;

                    //Binop -> dif
                    case 91:
                        pilha.push(Constantes.TS_DIF);
                        break;

                    //Binop -> or
                    case 92:
                        pilha.push(Constantes.TS_OR);
                        break;

                    //Binop -> and
                    case 93:
                        pilha.push(Constantes.TS_AND);
                        break;

                    //Binop -> igualdade
                    case 94:
                        pilha.push(Constantes.TS_IGUALDADE);
                        break;

                    //Binop -> sni
                    case 95:
                        pilha.push(Constantes.TS_SNI);
                        break;

                    //Binop -> sn
                    case 96:
                        pilha.push(Constantes.TS_SN);
                        break;

                    //Binop -> smi
                    case 97:
                        pilha.push(Constantes.TS_SMI);
                        break;

                    //Binop -> sm
                    case 98:
                        pilha.push(Constantes.TS_SM);
                        break;

                    //Binop -> div
                    case 99:
                        pilha.push(Constantes.TS_DIV);
                        break;

                    //Binop -> mul
                    case 100:
                        pilha.push(Constantes.TS_MUL);
                        break;

                    //Binop -> menos
                    case 101:
                        pilha.push(Constantes.TS_MENOS);
                        break;

                    //Binop -> mais
                    case 102:
                        pilha.push(Constantes.TS_SOMA);
                        break;

                    //quando n�o tah na intersec��o da tabela ou seja os espa�os vazios!
                    default:
                        sb.append("Simbolo n�o reconhecido: " + tm.getValor()).append("\n");
                        simbolosProblema = true;
                        tokenTemp.pop();//muda o token

                        break;

                }
            }
        }

        return new Mensagem(sb, !simbolosProblema);
    }
}

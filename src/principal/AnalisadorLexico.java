package principal;

import java.util.Arrays;
import java.util.List;

import util.Constantes;
import util.Token;
import util.ed.PilhaAdv;
import util.ed.Pilha;

public final class AnalisadorLexico {
    private static int estado;
    private static boolean retornoSemVazio = true;

    private static PilhaAdv<Token> tokens = null;

    private static List<String> keywords = Arrays.asList(new String[]{
            "and", "break", "do", "else", "elseif",
            "end", "false", "for", "function", "if",
            "in", "local", "nil", "not", "or",
            "repeat", "return", "then", "true", "until",
            "while", "main", "print", "scanner"
    });

    public static Pilha<Token> executa(String entrada) {
        estado = 1;
        tokens = new PilhaAdv<Token>();

        StringBuilder montaToken = new StringBuilder();

        for (Character ch : entrada.toCharArray()) {
            //caso o reconhecimento n�o seje de um vazio!
            //entao reexecuta o automato
            do {
                retornoSemVazio = false;//seta para sair do loop, ao reconhecer o item capturado atual, n�o for vazio
                //re-entra no automato apartir do estado 1 para iniciar o reconhecimento - AFD transdutor!

                switch (estado) {
                    //primeiro estado! de onde todos saem
                    case 1:
                        if (isPalavra(ch)) {
                            estado = 2;
                            montaToken.append(ch);
                        } else {
                            switch (ch) {
                                case '0':
                                    estado = 3;
                                    montaToken.append(ch);
                                    break;//quando for 0.0234234

                                case '-':
                                    estado = 20;
                                    montaToken.append(ch);
                                    break;//para comentario!

                                case '/':
                                case '*':
                                case '+':
                                    estado = 22;
                                    montaToken.append(ch);
                                    break;//simbolos

                                //simbolos
                                case '.':
                                    estado = 30;
                                    montaToken.append(ch);
                                    break;

                                //simbolos
                                case '#':
                                case '^':
                                case '%':
                                    estado = 32;
                                    montaToken.append(ch);
                                    break;

                                //operadores relacionais
                                case '=':
                                case '~':
                                case '<':
                                case '>':
                                    estado = 40;
                                    montaToken.append(ch);
                                    break;//operadores relacionais

                                //outros simbolos
                                case '(':
                                case ')':
                                case '{':
                                case '}':
                                case ';':
                                case ':':
                                case ',':
                                    estado = 32;
                                    montaToken.append(ch);
                                    break;

                                //classe dos literias formado por aspasDupla
                                case '"':
                                    estado = 55;
                                    montaToken.append(ch);
                                    break;

                                // refazer
                                case '[':
                                case ']':
                                    estado = 70;
                                    montaToken.append(ch);
                                    break;
                            }

                            //literal com aspasSimples
                            if (ch == "'".charAt(0)) {
                                estado = 50;
                                montaToken.append(ch);
                            }

                            //[1..9] = numero comum
                            if (ch != '0' && Character.isDigit(ch)) {
                                estado = 9;
                                montaToken.append(ch);
                            }
                        }
                        break;

                    //reconhece identificadores ou palavra reservada
                    case 2:
                        //saiu do estado 1 para reconhecer os indentificadores
                        if (isPalavra(ch) || Character.isDigit(ch) || ch == '_') {
                            estado = 2;
                            montaToken.append(ch);
                        } else {
                            //quebrou a regra, manda reconhecer
                            if (!keywords.contains(montaToken.toString())) {
                                reconhecido(montaToken, Constantes.TS_NOME, ch);
                            } else {
                                reconhecido(montaToken, "palavra reservada", ch);
                            }
                        }
                        break;
                    //end identificadores ou palavra reservada

                    //reconhece os numeros!
                    case 3:

                        // caso de bug ex: 0a.cs | 0a = identificador, . = simbolo, cs = identificador
                        switch (ch) {
                            case '.':
                                estado = 4;
                                montaToken.append(ch);
                                break;
                            case 'x':
                                estado = 6;
                                montaToken.append(ch);
                                break;
                            default:
                                reconhecido(montaToken, Constantes.TS_NUMERO, ch);
                                break;//reconheceu apenas o '0'
                        }
                        break;

                    case 4:

                        if (ch == 'e' || ch == 'E') {
                            estado = 5;
                            montaToken.append(ch);
                        } else if (Character.isDigit(ch)) {
                            estado = 4;
                            montaToken.append(ch);
                        } else {
                            reconhecido(montaToken, Constantes.TS_INVALID, ch);
                        }
                        break;

                    case 5:

                        if (ch == '-' || Character.isDigit(ch)) {
                            montaToken.append(ch);
                            estado = 8;
                        }
                        break;

                    case 8:
                        if (Character.isDigit(ch)) {
                            montaToken.append(ch);
                            estado = 8;
                        } else {
                            reconhecido(montaToken, Constantes.TS_INVALID, ch);
                        }
                        break;

                    //hexadecimal
                    case 6:
                        if (isHexDecimal(ch)) {
                            montaToken.append(ch);
                            estado = 6;
                        } else
                            reconhecido(montaToken, Constantes.TS_INVALID, ch);

                        break;

                    case 9:
                        if (ch == '.') {
                            estado = 4;
                            montaToken.append(ch);
                        } else {
                            if (Character.isDigit(ch)) {
                                estado = 9;
                                montaToken.append(ch);
                            } else {
                                if (ch == 'e' || ch == 'E') {
                                    estado = 5;
                                    montaToken.append(ch);
                                } else {
                                    reconhecido(montaToken, Constantes.TS_NUMERO, ch);
                                }
                            }
                        }
                        break;

                    //end do reconhecimento dos numeros!!!

                    //para comentario!
                    //tem que tratar de BUG ex: -0xaa44fF
                    case 20:
                        if (ch == '-') {
                            estado = 21;
                            montaToken.append(ch);
                        } else {
                            reconhecido(montaToken, "operador binario", ch);
                        }
                        break;

                    case 21:
                        //detecta tudo no final da linha!
                        if (ch != '@') {
                            estado = 21;
                            montaToken.append(ch);
                        } else {
                            reconhecido(montaToken, Constantes.TS_COMENTARIO, ch);
                        }
                        break;
                    //end do comentario

                    //operadores binarios
                    case 22:
                        reconhecido(montaToken, "operador binario", ch);
                        break;
                    //end operadores binarios


                    //pq eh estado final de simbolo .
                    case 30:
                        if (ch == '.') {
                            estado = 31;
                            montaToken.append(ch);
                        } else {
                            reconhecido(montaToken, "simbolo", ch);
                        }

                        break;

                    //..
                    case 31:
                        if (ch == '.') {
                            estado = 32;
                            montaToken.append(ch);
                        } else
                            reconhecido(montaToken, "simbolo", ch);
                        break;

                    //...
                    case 32:
                        reconhecido(montaToken, "simbolo", ch);
                        break;

                    //=,~,<,>
                    case 40:
                        if (ch == '=') {
                            estado = 41;
                            montaToken.append(ch);
                        } else {
                            reconhecido(montaToken, "operadores relacionais", ch);
                        }
                        break;

                    //==,~=,<=,>=
                    case 41:
                        reconhecido(montaToken, "operadores relacionais", ch);
                        break;

                    //literal com aspas simples
                    case 50:
                        if (ch == "'".charAt(0)) {
                            estado = 51;
                            montaToken.append(ch);
                        } else {
                            //fica no loop
                            if (ch != '@' && ch != ' ') {
                                estado = 50;
                                montaToken.append(ch);
                            }
                        }
                        break;

                    case 51:
                        if (ch == "'".charAt(0)) {
                            if (ch != '@' && ch != ' ') {
                                estado = 51;
                                montaToken.append(ch);
                            }
                        } else {
                            reconhecido(montaToken, "literal", ch);
                        }
                        break;

                    //literais com aspas duplas
                    case 53:
                        if (ch == '"') {
                            estado = 53;
                            montaToken.append(ch);
                        } else {
                            reconhecido(montaToken, "literal", ch);
                        }
                        break;

                    //literal com aspas duplas
                    case 55:
                        if (ch == '"') {
                            estado = 53;
                            montaToken.append(ch);
                        } else {
                            if (ch != '@' && ch != ' ') {
                                estado = 55;
                                montaToken.append(ch);
                            }
                        }
                        break;

                    case 70:
                        if (ch == '=') {
                            estado = 70;
                            montaToken.append(ch);
                        } else {
                            if (ch == '[') {
                                estado = 71;
                                montaToken.append(ch);
                            } else {
                                reconhecido(montaToken, "simbolo", ch);//reconhece apenas '['
                            }
                        }
                        break;

                    case 71:
                        if (ch == ']') {
                            estado = 72;
                            montaToken.append(ch);
                        } else {
                            //ignora o escape
                            if (ch != '@' && ch != ' ') {
                                estado = 71;//fica em loop com todos os literais
                                montaToken.append(ch);
                            }
                        }
                        break;

                    case 72:
                        if (ch == '=') {
                            estado = 72;
                            montaToken.append(ch);
                        } else {
                            if (ch == ']') {
                                estado = 73;
                                montaToken.append(ch);
                            }
                        }
                        break;

                    case 73:
                        reconhecido(montaToken, "literal", ch);
                        break;


                }
            } while (retornoSemVazio);
        }

        return tokens;
    }

    private static void reconhecido(StringBuilder temp, String tipo, char ch) {

        String str = temp.toString();
        boolean valido = false;

        for (Constantes simb : Constantes.values()) {
            if (simb.getStr().equalsIgnoreCase(str)) {

                //faz o reconhecimento
                reconhecido(temp, simb, ch);
                valido = true;
                break;
            }
        }

        //nao eh valido
        if (!valido)
            reconhecido(temp, Constantes.TS_INVALID, ch);


    }

    private static void reconhecido(StringBuilder temp, Constantes tipo, char ch) {
        //se o estado atual for vazio ou algo que nao eh reconhecido na linguagem
        //reconhece e limpa o temp, senao add o item atual
        if (ch == ' ') {
            retornoSemVazio = false;//nao precisa reconhecer um caractere vazio
        } else {
            retornoSemVazio = true;//cont no do e while
        }

        tokens.addLast(new Token(temp.toString(), tipo));
        //reconhece.append(temp).append("   -->  ").append(tipo).append("\n");
        temp.delete(0, temp.length());
        estado = 1;
    }

    private static boolean isHexDecimal(Character ch) {
        if (Character.isDigit(ch) || (ch >= 65 && ch <= 70) || (ch >= 97 && ch <= 102)) {
            return true;
        }
        return false;
    }

    //reconhecer palavra
    private static boolean isPalavra(Character ch) {
        if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
            return true;
        }
        return false;
    }
}

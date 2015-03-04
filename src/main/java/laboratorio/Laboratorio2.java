/**
 *
 */
public class Laboratorio2 {

    public static void main(String[] args) {

        String cadeia = "0x0ABC";
        Integer estado = 0;

        for (int i = 0; i < cadeia.length(); i++) {

            char caractere = cadeia.charAt(i);
            switch (estado) {
                case 0:
                    if (caractere == '.') {
                        estado = 3;
                    } else if (caractere == '+' || caractere == '-') {
                        estado = 2;
                    } else if (caractere == '1') {
                        estado = 1;
                    } else if (caractere == '0') {
                        estado = 5;
                    } else if (isOcta(caractere)) {
                        estado = 12;
                    } else if (Character.isDigit(caractere)) {
                        estado = 4;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 1:
                    if (caractere == '0' || caractere == '1') {
                        estado = 1;
                    } else if (isOcta(caractere)) {
                        estado = 12;
                    } else if (caractere == '.') {
                        estado = 11;
                    } else if (Character.isDigit(caractere)) {
                        estado = 4;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 2:
                    if (caractere == '.') {
                        estado = 10;
                    } else if (Character.isDigit(caractere)) {
                        estado = 4;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 3:
                    if (Character.isDigit(caractere)) {
                        estado = 3;
                    } else if (caractere == 'E') {
                        estado = 7;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 4:
                    if (Character.isDigit(caractere)) {
                        estado = 4;
                    } else if (caractere == 'E') {
                        estado = 7;
                    } else if (caractere == '.') {
                        estado = 10;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 5:
                    if (caractere == '0' || caractere == '1') {
                        estado = 1;
                    } else if (isOcta(caractere)) {
                        estado = 12;
                    } else if (caractere == 'x') {
                        estado = 6;
                    } else if (caractere == '.') {
                        estado = 11;
                    } else if (Character.isDigit(caractere)) {
                        estado = 4;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 6:
                    if (Character.isDigit(caractere) || caractere == 'A' || caractere == 'B' || caractere == 'C' || caractere == 'D' || caractere == 'E' || caractere == 'F') {
                        estado = 13;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 7:
                    if (caractere == '+' || caractere == '-') {
                        estado = 8;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 8:
                    if (Character.isDigit(caractere)) {
                        estado = 9;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 9:
                    if (Character.isDigit(caractere)) {
                        estado = 9;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 10:
                    if (Character.isDigit(caractere)) {
                        estado = 3;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 11:
                    if (Character.isDigit(caractere)) {
                        estado = 3;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 12:
                    if (isOcta(caractere)) {
                        estado = 12;
                    } else if (Character.isDigit(caractere)) {
                        estado = 10;
                    } else if (caractere == '.') {
                        estado = 10;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
                case 13:
                    if (Character.isDigit(caractere) || caractere == 'A' || caractere == 'B' || caractere == 'C' || caractere == 'D' || caractere == 'E' || caractere == 'F') {
                        estado = 13;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada");
                    }
                    break;
            }
        }

        if (estado == 1) {
            System.out.println("Cadeia bem formada - n�mero bin�rio");
        } else if (estado == 3 || estado == 9) {
            System.out.println("Cadeia bem formada - n�mero ponto flutuante");
        } else if (estado == 4) {
            System.out.println("Cadeia bem formada - n�mero inteiro");
        } else if (estado == 13) {
            System.out.println("Cadeia bem formada - n�mero hexadecimal");
        } else if (estado == 12) {
            System.out.println("Cadeia bem formada - n�mero octal");
        } else {
            System.out.println("A cadeia n�o est� bem formada");
        }
    }

    public static boolean isOcta(char caractere) {

        if (caractere == '0' || caractere == '1' || caractere == '2' || caractere == '3' || caractere == '4' || caractere == '5' || caractere == '6' || caractere == '7') {
            return true;
        }

        return false;
    }
}

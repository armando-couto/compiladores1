
public class Laboratorio1 {
    /**
     * Verifica se a cadeia � formada somente por letras e numero e inicia com uma letra
     *
     * @param args
     */
    public static void main(String[] args) {

        String cadeia = "@";
        Integer estado = 1;

        for (int i = 0; i < cadeia.length(); i++) {

            char caractere = cadeia.charAt(i);
            switch (estado) {
                case 1:
                    if (Character.isLetter(caractere)) {
                        estado = 2;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada - Falhou no estado 1");
                    }
                    break;
                case 2:
                    if (Character.isLetter(caractere) || Character.isDigit(caractere)) {
                        estado = 2;
                    } else {
                        throw new RuntimeException("A cadeia n�o est� bem formada - Falhou no estado 2");
                    }
                    break;
            }
        }

        if (estado != 2) {
            throw new RuntimeException("A cadeia n�o est� bem formada");
        }

        System.out.println("Cadeia bem formada");
    }

}

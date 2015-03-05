package laboratorio;

public class Laboratorio1 {

    public static void main(String[] args) {

//        String cadeia = "DFD@";
        String cadeia = "@";
        Integer estado = 1;

        for (int i = 0; i < cadeia.length(); i++) {

            char caractere = cadeia.charAt(i);
            switch (estado) {
                case 1:
                    if (Character.isLetter(caractere)) {
                        estado = 2;
                    } else {
                        throw new RuntimeException("A cadeia não está bem formada - Falhou no estado 1");
                    }
                    break;
                case 2:
                    if (Character.isLetter(caractere) || Character.isDigit(caractere)) {
                        estado = 2;
                    } else {
                        throw new RuntimeException("A cadeia não está bem formada - Falhou no estado 2");
                    }
                    break;
            }
        }

        if (estado != 2) {
            throw new RuntimeException("A cadeia não está bem formada");
        }

        System.out.println("Cadeia bem formada");
    }
}

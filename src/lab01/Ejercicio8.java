package lab01;

public class Ejercicio8 {

    public static void main(String[] args) {
        String textoOriginal = 
                "Bien puede el mundo entero conjurarse\r\n"
                + "contra mi dulce amor y mi ternura,\r\n"
                + "y el odio infame y tiranía dura\r\n"
                + "de todo su rigor contra mí armarse.\r\n"
                + "Bien puede el tiempo rápido cebarse\r\n"
                + "en la gracia y primor de su hermosura,\r\n"
                + "para que cual si fuese llama impura\r\n"
                + "pueda el fuego de amor en mí acabarse.\r\n"
                + "Bien puede en fin la suerte vacilante,\r\n"
                + "que eleva, abate, ensalza y atropella,\r\n"
                + "alzarme o abatirme en un instante;\r\n"
                + "Que el mundo, al tiempo y a mi varia estrella,\r\n"
                + "más fino cada vez y más constante,\r\n"
                + "les diré: «Silvia es mía y yo soy de ella».\r\n";
        String textoFinal = modificarTexto(textoOriginal);
        System.out.println(textoFinal);
        int longitudTextoFinal = textoFinal.length();
        System.out.println("La longitud del texto resultante es: " + longitudTextoFinal);
    }

    public static String modificarTexto(String textoOriginal) {
        StringBuilder textoFinal = new StringBuilder();
        int contadorCaracteres = 0;
        
        for (int i = 0; i < textoOriginal.length(); i++) {
            char caracter = textoOriginal.charAt(i);
            textoFinal.append(caracter);
            contadorCaracteres++;
            // Insertando la cadena "EPIS" cada 15 caracteres
            if (contadorCaracteres % 15 == 0) {
                textoFinal.append("EPIS");
                contadorCaracteres += 4; // Se añade 4 caracteres por la cadena "EPIS"
            }
        }
        
        // Con esta función se asegura que el texto tenga un número de caracteres múltiplo de 7
        int caracteresFaltantes = 7 - (contadorCaracteres % 7);
        if (caracteresFaltantes != 7) {
            for (int i = 0; i < caracteresFaltantes; i++) {
                textoFinal.append("Z");
            }
        }
        
        return textoFinal.toString();
    }
}

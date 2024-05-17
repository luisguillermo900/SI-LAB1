package lab01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Ejercicio6 {

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

        guardarResultadoEnArchivo(textoFinal);

        Map<Character, Integer> tablaFrecuencias = frecuencias("PRACTICA1_PRE.TXT");
        System.out.println("Tabla de frecuencias:");
        for (Map.Entry<Character, Integer> entry : tablaFrecuencias.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Map<Character, Integer> top5Frecuencias = encontrarTop5Frecuencias(tablaFrecuencias);
        System.out.println("\nCinco caracteres más frecuentes:");
        for (Map.Entry<Character, Integer> entry : top5Frecuencias.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Map<String, List<Integer>> trigramas = encontrarTrigramas(textoFinal);
        //imprimirTrigramas(trigramas);
        calcularDistancias(trigramas);
    }

    public static String modificarTexto(String textoOriginal) {
        String textoSinCaracteres = eliminarCaracteres(textoOriginal);
        String textoSinTildes = eliminarTildes(textoSinCaracteres);
        String textoEnMayusculas = textoSinTildes.toUpperCase();
        String textoFinal = textoEnMayusculas
                .replace("A", "U").replace("a", "u")
                .replace("H", "T").replace("h", "t")
                .replace("Ñ", "E").replace("ñ", "e")
                .replace("K", "L").replace("k", "l")
                .replace("V", "F").replace("v", "f")
                .replace("W", "B").replace("w", "b")
                .replace("Z", "Y").replace("z", "y")
                .replace("R", "P").replace("r", "p");

        return textoFinal;
    }

    public static String eliminarTildes(String texto) {
        return texto.replace("á", "a").replace("Á", "A")
                .replace("é", "e").replace("É", "E")
                .replace("í", "i").replace("Í", "I")
                .replace("ó", "o").replace("Ó", "O")
                .replace("ú", "u").replace("Ú", "U")
                .replace("ñ", "n").replace("Ñ", "N");
    }

    public static String eliminarCaracteres(String texto) {
        String caracteresEliminar = ".,;:!¡¿?\"()[]{}<>_+-=*/&%$#@^«» ";
        for (char caracter : caracteresEliminar.toCharArray()) {
            texto = texto.replace(Character.toString(caracter), "");
        }
        return texto;
    }

    public static void guardarResultadoEnArchivo(String texto) {
        try {
            FileWriter writer = new FileWriter("PRACTICA1_PRE.TXT");
            writer.write(texto);
            writer.close();
            System.out.println("El resultado se ha guardado en el archivo 'PRACTICA1_PRE.TXT'");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar el resultado en el archivo.");
            e.printStackTrace();
        }
    }

    public static Map<Character, Integer> frecuencias(String archivo) {
        Map<Character, Integer> tablaFrecuencias = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            StringBuilder texto = new StringBuilder();
            while ((linea = br.readLine()) != null) {
                texto.append(linea);
            }
            String textoCompleto = texto.toString();
            for (char letra = 'A'; letra <= 'Z'; letra++) {
                int frecuencia = contarCaracteres(textoCompleto, letra);
                tablaFrecuencias.put(letra, frecuencia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tablaFrecuencias;
    }

    public static int contarCaracteres(String texto, char caracter) {
        int count = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == caracter) {
                count++;
            }
        }
        return count;
    }

    public static Map<Character, Integer> encontrarTop5Frecuencias(Map<Character, Integer> tablaFrecuencias) {
        return tablaFrecuencias.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, List<Integer>> encontrarTrigramas(String texto) {
        Map<String, List<Integer>> trigramas = new HashMap<>();

        for (int i = 0; i < texto.length() - 2; i++) {
            // Obteneniendo tres caracteres consecutivos
            char char1 = texto.charAt(i);
            char char2 = texto.charAt(i + 1);
            char char3 = texto.charAt(i + 2);

            // Verificando si los tres caracteres son letras
            if (Character.isLetter(char1) && Character.isLetter(char2) && Character.isLetter(char3)) {
                String trigrama = String.valueOf(char1) + char2 + char3;
                
                if (!trigramas.containsKey(trigrama
                		)) {
                    trigramas.put(trigrama, new ArrayList<>());
                }
                trigramas.get(trigrama).add(i);
            }
        }

        return trigramas;
    }

    public static void imprimirTrigramas(Map<String, List<Integer>> trigramas) {
        for (Map.Entry<String, List<Integer>> entry : trigramas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void calcularDistancias(Map<String, List<Integer>> trigramas) {
        for (Map.Entry<String, List<Integer>> entry : trigramas.entrySet()) {
            String trigrama = entry.getKey();
            List<Integer> posiciones = entry.getValue();

            if (posiciones.size() > 1) {
                System.out.println("Distancias para el trigrama '" + trigrama + "':");
                for (int i = 1; i < posiciones.size(); i++) {
                    int distancia = posiciones.get(i) - posiciones.get(i - 1);
                    System.out.println("Posiciones " + posiciones.get(i - 1) + " y " + posiciones.get(i) + ": " + distancia + " caracteres.");
                }
            }
        }
    }
}

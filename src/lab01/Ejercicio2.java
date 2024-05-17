package lab01;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	}
	public static String modificarTexto(String textoOriginal) {
		
		String textoSinTildes = eliminarTildes(textoOriginal);
        String textoFinal = textoSinTildes
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

}

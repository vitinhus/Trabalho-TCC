package com.example.apresentacao;

public class Base64 {

    public static String codificarBase64(String texto) {
        return android.util.Base64.encodeToString(texto.getBytes(), android.util.Base64.DEFAULT) // utilizando os metodos da classe Base64 para codificar a variavel texto
                .replaceAll("(\\n|\\r)", ""); // metodo replaceall serve para removermos os caracteres que nao queremos na codificaca
        // neste caso tiremos o //n - quebra de linha e o //r retorno de linha
    }
    public static String decodificarBase64(String textoCodificado){
        // fizemos diferente nesse metodo pois o decode nao converte em string
        // entao adaptamos o codigo para fazer ele retornar uma string
        return new String ( android.util.Base64.decode(textoCodificado, android.util.Base64.DEFAULT) );

    }
}



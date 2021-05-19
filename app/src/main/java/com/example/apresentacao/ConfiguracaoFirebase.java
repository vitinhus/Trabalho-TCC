package com.example.apresentacao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;
    //criamos o atributo static pois independente da instancia sera o MESMO atributo

    private static DatabaseReference firebase;



    //criando metodo para retornar a instancia do firebaseAuth, perimitindo cadastro de usuarios
    public static FirebaseAuth getFirebaseAutenticacao(){

        // verificando se a instancia ja foi pega
        if(autenticacao == null ) {
            autenticacao = FirebaseAuth.getInstance(); //pegando a instancia

        }
        return autenticacao;
    }

    //criando metodo para rerotnar a instancia do FirebaseDatabase
    // permitindo salvar os dados no banco de dados
    public static DatabaseReference getFirebase(){
        if(firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }
}

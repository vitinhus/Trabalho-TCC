package com.example.apresentacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class CadastroLogin extends AppCompatActivity {

    private EditText campoEmailLogin, campoSenhaLogin;
    private Button botaoEntrarLogin;
    private Usuario usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);

        campoEmailLogin = findViewById(R.id.CampoEmailLogin);
        campoSenhaLogin = findViewById(R.id.CampoSenhaLogin);
        botaoEntrarLogin = findViewById(R.id.BotaoEntrarLogin);

        botaoEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmailLogin.getText().toString();
                String textoSenha = campoSenhaLogin.getText().toString();

                //Validar se o Email e senha foram digitados

                if( !textoEmail.isEmpty() ){
                    if( !textoSenha.isEmpty() ){

                        //Apos verificar se os campos foram preenchidos corretamente
                        // vamos autenticar o usuario
                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin();


                    }else{
                        Toast.makeText(getApplicationContext(),"Preencha a Senha !",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Preencha o Email !",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void validarLogin(){

        // recuperando a instancia de autenticacao do firebase
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        // metodo para pegar os dados que foram utilizados no cadastro do usuario
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()

        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // verificando se a autenticacao deu certo
                if( task.isSuccessful() ){
                    Toast.makeText(getApplicationContext(),"Sucesso ao fazer login !",Toast.LENGTH_LONG).show();
                    abrirTelaPrincipal();
                }else{

                    //pegando Exceptions do link https://firebase.google.com/docs/reference/android/com/google/firebase/auth/package-summary?authuser=0
                    // para verificar se o usuario digitou o email ou a senha errada

                    String excessao = "";
                    try {
                        throw task.getException();
                    }catch ( FirebaseAuthInvalidUserException e){ //excessao para verificar se o usuario existe no banco
                        excessao = "Usuario Nao Cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){  //excessao para ver se o email e senha estao corretos
                        excessao = "Email ou Senha Incorretos";
                    }catch (Exception e) {
                        excessao = "Erro ao Logar Na Conta !";
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(),excessao,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal(){
        Intent proximaTela = new Intent(getApplicationContext(), Cliente.class);
        startActivity(proximaTela);
    }

   public void cadastro(View view){
       Intent proximaTela = new Intent(this, Cadastre_se.class);
       startActivity(proximaTela);
   }

}
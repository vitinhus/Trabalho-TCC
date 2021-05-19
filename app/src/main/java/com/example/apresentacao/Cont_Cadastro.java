package com.example.apresentacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Cont_Cadastro extends AppCompatActivity {

    private EditText campoEmail, campoSenha, campoNome, campoCelular, campoBairro;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private RadioGroup campoEscolha;
    private RadioButton radioCliente, radioProf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont__cadastro);
        campoEmail = findViewById(R.id.CampoEmail);
        campoSenha = findViewById(R.id.CampoSenha);
        campoNome = findViewById(R.id.CampoNome);
        campoCelular = findViewById(R.id.CampoCelular);
        campoBairro = findViewById(R.id.CampoBairro);

        botaoCadastrar = findViewById(R.id.BotaoEntrarLogin);
        campoEscolha = findViewById(R.id.RadioCampoEscolha);
        radioCliente = findViewById(R.id.radioClienteCadastro);
        radioProf = findViewById(R.id.radioProfCadastro);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                String textoNome = campoNome.getText().toString();
                String textoCelular = campoCelular.getText().toString();
                String textoBairro = campoBairro.getText().toString();

                if( !textoEmail.isEmpty() ){
                        if( !textoSenha.isEmpty() ){

                                usuario = new Usuario();
                                usuario.setEmail(textoEmail);
                                usuario.setSenha(textoSenha);
                                usuario.setNome(textoNome);
                                usuario.setCelular(textoCelular);
                                usuario.setBairro(textoBairro);

                                if (radioCliente.isChecked()) {
                                    cadastrarCliente();
                                } else {
                                    cadastrarProfissional();
                                }
                            } else{
                        Toast.makeText(getApplicationContext(),"Preencha O campo Email",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Preencha O campo Senha",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cadastrarCliente() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao(); // pegando a instancia da classe criada

        //criando usuario
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha() // passando o email e senha como parametro para criar o usuario
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {  //validacao com addoncompletlistener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() ){
                    String idUsuario = Base64.codificarBase64(usuario.getEmail() );
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvarComoCliente();
                    Toast.makeText(getApplicationContext(),"Sucesso Ao Cadastrar Cliente",Toast.LENGTH_LONG).show();
                }else {

                    String excessao = "";
                    try {
                        throw task.getException();  // tem que utilizar o throw para utilizar essas excessoes
                    }catch  ( FirebaseAuthWeakPasswordException e ){
                        excessao = "Digite uma senha mais forte !"; //excessao de senha fraca

                    }catch ( FirebaseAuthInvalidCredentialsException e ){
                        excessao = "Digite um email Valido"; //excessao de email invalido

                    }catch (FirebaseAuthUserCollisionException e){
                        excessao = "Esta conta ja foi cadastrada"; //excessao de conta ja cadastrada

                    }catch (Exception e){
                        excessao = "Erro ao cadastrar Cliente" + e.getMessage(); //excessao generica
                        e.printStackTrace(); // printar a excessao no log
                    }
                    Toast.makeText(getApplicationContext(),excessao,Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void cadastrarProfissional() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao(); // pegando a instancia da classe criada

        //criando usuario
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha() // passando o email e senha como parametro para criar o usuario
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {  //validacao com addoncompletlistener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() ){
                    String idUsuario = Base64.codificarBase64(usuario.getEmail() );
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvarComoProfissional();
                    Toast.makeText(getApplicationContext(),"Sucesso Ao Cadastrar Profissional",Toast.LENGTH_LONG).show();
                }else {

                    String excessao = "";
                    try {
                        throw task.getException();  // tem que utilizar o throw para utilizar essas excessoes
                    }catch  ( FirebaseAuthWeakPasswordException e ){
                        excessao = "Digite uma senha mais forte !"; //excessao de senha fraca

                    }catch ( FirebaseAuthInvalidCredentialsException e ){
                        excessao = "Digite um email Valido"; //excessao de email invalido

                    }catch (FirebaseAuthUserCollisionException e){
                        excessao = "Esta conta ja foi cadastrada"; //excessao de conta ja cadastrada

                    }catch (Exception e){
                        excessao = "Erro ao cadastrar Profissional" + e.getMessage(); //excessao generica
                        e.printStackTrace(); // printar a excessao no log
                    }
                    Toast.makeText(getApplicationContext(),excessao,Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    /*public void continuarCadastro(View view){

        if(radioCliente.isChecked()) {
            Intent proximaTela = new Intent(this, Cliente.class);
            startActivity(proximaTela);
        } else if(radioProfissional.isChecked()){
            Intent proximaTela = new Intent(this, Profissional.class);
            startActivity(proximaTela);
        }
    }*/
}
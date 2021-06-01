package com.example.apresentacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Cadastre_se extends AppCompatActivity  {

    private EditText campoEmail, campoSenha;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private RadioGroup grupoCadastro;
    private RadioButton radioCliente, radioProfissional;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre_se);

        campoEmail = findViewById(R.id.CampoEmail);
        campoSenha = findViewById(R.id.CampoSenha);

        botaoCadastrar = findViewById(R.id.botaoCadastrar);

        radioCliente = findViewById(R.id.radioButtonCliente);
        radioProfissional = findViewById(R.id.radioButtonProffional);



        grupoCadastro = findViewById(R.id.GrupoCadastro);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if( !textoEmail.isEmpty() ){
                    if( !textoSenha.isEmpty() ){

                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        cadastrar();


                    } else{
                        Toast.makeText(getApplicationContext(),"Preencha O campo Email",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Preencha O campo Senha",Toast.LENGTH_LONG).show();
                }
            }
        });


        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        grupoCadastro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonCliente) {

                    dialog.setTitle("Opção Cliente Selecionada");
                    dialog.setMessage("Continar Cadastro como Cliente?");

                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent proximaTela = new Intent(getApplicationContext(), Cont_Cadastro.class);
                            startActivity(proximaTela);
                        }
                    });

                    dialog.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialog.create();
                    dialog.show();

                } else if (i == R.id.radioButtonProffional){

                    dialog.setTitle("Opção Profissional Selecionada");
                    dialog.setMessage("Continar Cadastro como Profissional?");

                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent proximaTelaProf = new Intent(getApplicationContext(), Cont_Cadastro_Prof.class);
                            startActivity(proximaTelaProf);
                        }
                    });

                    dialog.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.create();
                    dialog.show();
                }

            }
        });
    }

    public void cadastrar() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao(); // pegando a instancia da classe criada

        //criando usuario
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha() // passando o email e senha como parametro para criar o usuario
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {  //validacao com addoncompletlistener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() ){
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


}


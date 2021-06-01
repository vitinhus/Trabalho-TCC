package com.example.apresentacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Cont_Cadastro_Prof extends AppCompatActivity {

    private EditText campoNome, campoCelular, campoProfissao;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont__cadastro);

        campoNome = findViewById(R.id.CampoNome);
        campoCelular = findViewById(R.id.CampoCelular);
        campoProfissao = findViewById(R.id.CampoEndereco);


        botaoCadastrar = findViewById(R.id.BotaoEntrarLogin);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                                                  String textoNome = campoNome.getText().toString();
                                                  String textoCelular = campoCelular.getText().toString();
                                                  String textoProfissao = campoProfissao.getText().toString();


                                                  if (!textoNome.isEmpty()) {
                                                      if (!textoCelular.isEmpty()) {
                                                          if (!textoProfissao.isEmpty()) {


                                                                  usuario = new Usuario();
                                                                  usuario.setNome(textoNome);
                                                                  usuario.setCelular(textoCelular);
                                                                  usuario.setEndereco(textoProfissao);



                                                          } else {
                                                              Toast.makeText(getApplicationContext(), "Preencha O campo Celular", Toast.LENGTH_LONG).show();
                                                          }
                                                      } else {
                                                          Toast.makeText(getApplicationContext(), "Preencha O campo Bairro", Toast.LENGTH_LONG).show();
                                                      }
                                                  } else {
                                                      Toast.makeText(getApplicationContext(), "Preencha O campo Nome", Toast.LENGTH_LONG).show();
                                                  }
                                              }
                                          }
        );

    }

    public void abrirTelaCliente(){
        Intent proximaTela = new Intent(getApplicationContext(), Cliente.class);
        startActivity(proximaTela);
    }



}
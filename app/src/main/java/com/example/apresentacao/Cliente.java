package com.example.apresentacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.internal.Storage;

public class Cliente extends AppCompatActivity {

    private Storage imagem;
    private TextView nome;
    private TextView descricao;
    private String profissaoBuscada;
    private String areaBuscada;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        nome = findViewById(R.id.campoNome);
        descricao = findViewById(R.id.campoDescricao);
        profissaoBuscada = findViewById(R.id.campoBuscarProfissao).toString();
        areaBuscada = findViewById(R.id.campoBuscarArea).toString();


    }

}
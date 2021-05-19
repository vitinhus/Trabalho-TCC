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

    private RadioGroup grupoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre_se);

        grupoCadastro = findViewById(R.id.GrupoCadastro);


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

                } else {

                    dialog.setTitle("Opção Profissional Selecionada");
                    dialog.setMessage("Continar Cadastro como Profissional?");

                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent proximaTela = new Intent(getApplicationContext(), Profissional.class);
                            startActivity(proximaTela);
                        }
                    });

                    dialog.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                }

                dialog.create();
                dialog.show();

            }
        });
    }
}


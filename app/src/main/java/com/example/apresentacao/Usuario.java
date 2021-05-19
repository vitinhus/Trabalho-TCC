package com.example.apresentacao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {

    private String IdUsuario;
    private String email;
    private String senha;
    private String nome;
    private String celular;
    private String bairro;

    public void salvarComoCliente(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
        firebase.child("Cliente")
                .child(this.IdUsuario)
                .setValue(this);
    }

    public void salvarComoProfissional(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
        firebase.child("Profissional")
                .child(this.IdUsuario)
                .setValue(this);
    }

    public Usuario() {
    }

    @Exclude
    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

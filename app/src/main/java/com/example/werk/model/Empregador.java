package com.example.werk.model;

import java.util.ArrayList;

public class Empregador {
    public Empregador() {
    }

    public String getUuid() {
        return uuid;
    }

    private String idFoto;
    private String uuid;
    private String genero;
    private String telefone;
    private String dataNascimento;
    private String nome;
    private String senha;
    private String endereco;
    private String email;
    private String cpf;
    private int avaliacao;
    private ArrayList<Trabalho> trabalhos;

    public Empregador(String uuid, int avaliacao, String cpf, String email, String genero, String telefone, String dataNascimento, String nome, String senha, String endereco, ArrayList<Trabalho> trabalhos) {
        this.uuid = uuid;
        this.avaliacao = avaliacao;
        this.cpf = cpf;
        this.email = email;
        this.genero = genero;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.trabalhos = trabalhos;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ArrayList<Trabalho> getTrabalhos() {
        return trabalhos;
    }

    public void setTrabalhos(ArrayList<Trabalho> trabalhos) {
        this.trabalhos = trabalhos;
    }

    public void adicionaTrabalho(Trabalho trabalho){
        this.trabalhos.add(trabalho);
    }

    public int getAvaliacao(){
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao){
        this.avaliacao = avaliacao;
    }

    public void atualizarAvaliacao(int avaliacao){
        this.avaliacao = (this.avaliacao + avaliacao)/2;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Empregador{" +
                "nome='" + nome + '\'' +
                ", CPF='" + cpf + '\'' +
                ", e-mail='" + email + '\'' +
                ", genero='" + genero + '\'' +
                ", telefone='" + telefone + '\'' +
                ", data de nascimento='" + dataNascimento + '\'' +
                ", senha='" + senha + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
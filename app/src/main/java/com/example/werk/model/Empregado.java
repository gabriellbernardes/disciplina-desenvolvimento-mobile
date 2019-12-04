package com.example.werk.model;


import java.util.ArrayList;

public class Empregado{

    private String idFoto;
    private String uuid;
    private String genero;
    private String telefone;
    private String dataNascimento;
    private String nome;
    private String senha;
    private String endereco;
    private String email;
    private int avaliacao;
    private ArrayList<Trabalho> trabalhos;

    public Empregado(String uuid, String email, String genero, String telefone, String dataNascimento, String nome, String senha, String endereco, int avaliacao, ArrayList<Trabalho> trabalhos) {
        this.uuid = uuid;
        this.email = email;
        this.genero = genero;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.avaliacao = avaliacao;
        this.trabalhos = trabalhos;
    }

    public Empregado() {
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }


    public String getUuid() {
        return uuid;
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
        return "Empregado{" +
                "nome='" + nome + '\'' +
                ", e-mail='" + email + '\'' +
                ", genero='" + genero + '\'' +
                ", telefone='" + telefone + '\'' +
                ", data de nascimento='" + dataNascimento + '\'' +
                ", senha='" + senha + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
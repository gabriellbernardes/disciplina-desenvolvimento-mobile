package com.example.werk.model;

public class Contato {
    private static int contadorId = 0;

    private String email;
    private String telefone;
    private String id;
    private String idfoto;
    private String ultimamensagem;
    private long tempo;


    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public Contato(){

    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Contato.contadorId = contadorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(String idfoto) {
        this.idfoto = idfoto;
    }

    public String getUltimamensagem() {
        return ultimamensagem;
    }

    public void setUltimamensagem(String ultimamensagem) {
        this.ultimamensagem = ultimamensagem;
    }
}
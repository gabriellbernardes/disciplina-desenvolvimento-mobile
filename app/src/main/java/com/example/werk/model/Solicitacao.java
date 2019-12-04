package com.example.werk.model;

public class Solicitacao {
    private String uuid;
    private String id;
    private Empregado empregado;
    private Empregador empregador;
    private int status;
    private Trabalho trabalho;

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public Solicitacao() {
    }

    public Solicitacao(String uuid, String id, Empregador empregador, int status, Trabalho trabalho) {
        this.uuid = uuid;
        this.id = id;
        this.empregador = empregador;
        this.status = status;
        this.trabalho = trabalho;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public Empregador getEmpregador() {
        return empregador;
    }

    public void setEmpregador(Empregador empregador) {
        this.empregador = empregador;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "id="+id+
                ", empregado=" + empregado +
                ", empregador=" + empregador +
                ", status=" + status +
                ", trabalho=" + trabalho +
                '}';
    }


}

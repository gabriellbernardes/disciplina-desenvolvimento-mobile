package com.example.werk.model;

public class Menssagem {
    private String mensagem;
    private long timestamp;
    private String fomId;
    private String toId;

    public Menssagem(String mensagem, long timestamp, String fomId, String toId) {
        this.mensagem = mensagem;
        this.timestamp = timestamp;
        this.fomId = fomId;
        this.toId = toId;
    }
    public Menssagem(){}

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public long getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFomId() {
        return fomId;
    }

    public void setFomId(String fomId) {
        this.fomId = fomId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}

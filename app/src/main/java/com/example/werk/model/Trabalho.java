package com.example.werk.model;

public class Trabalho{
    public String getUuid() {
        return uuid;
    }

    public Trabalho() {
    }

    private String uuid;
    private String id;
    private String tipo;
    private String pagamento;
    private String descricao;
    private String contato;
    private String endereco;
    private int status;

    public Trabalho(String uuid, String id, String tipo, String pagamento, String descricao, String contato, String endereco){
        this.uuid = uuid;
        this.id = id;
        this.tipo = tipo;
        this.pagamento = pagamento;
        this.descricao = descricao;
        this.contato = contato;
        this.endereco = endereco;
        this.status = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Trabalho{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", pagamento='" + pagamento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", contato='" + contato + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}

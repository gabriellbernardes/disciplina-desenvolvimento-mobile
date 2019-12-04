package com.example.werk.data;

import com.example.werk.model.Trabalho;

import java.util.ArrayList;

public class TrabalhoDBMemory implements TrabalhoDAO{

    private static ArrayList<Trabalho> listaTrabalhos;
    private static TrabalhoDBMemory trabalhoDAO;

    private TrabalhoDBMemory(){

        listaTrabalhos = new ArrayList<Trabalho>();

//        Contato c1 = new Contato("contato 01", "telefone 01", "endereco 01");
//        Contato c2 = new Contato("contato 02", "telefone 02", "endereco 02");
//        Contato c3 = new Contato("contato 03", "telefone 03", "endereco 03");
//        Contato c4 = new Contato("contato 04", "telefone 04", "endereco 04");
//        Contato c5 = new Contato("contato 05", "telefone 05", "endereco 05");
//
//        listaContatos.add(c1);
//        listaContatos.add(c2);
//        listaContatos.add(c3);
//        listaContatos.add(c4);
//        listaContatos.add(c5);

    }

    public void aceitarTrabalho(Trabalho trabalho) {
       trabalho.setStatus(1);
       editTrabalho(trabalho);
    }
    public void addTrabalho(Trabalho trabalho) {
        listaTrabalhos.add(trabalho);
    }

    public void editTrabalho(Trabalho trabalho){

        for(Trabalho trabalhoMemoria: listaTrabalhos){

            if(trabalhoMemoria.getId().equals(trabalho.getId())){
                trabalhoMemoria.setTipo(trabalho.getTipo());
                trabalhoMemoria.setPagamento(trabalho.getPagamento());
                trabalhoMemoria.setDescricao(trabalho.getDescricao());
                trabalhoMemoria.setContato(trabalho.getContato());
                trabalhoMemoria.setEndereco(trabalho.getEndereco());
                trabalhoMemoria.setStatus(trabalho.getStatus());
            }
        }
    }

    @Override
    public void deleteTrabalho(String trabalhoId) {

        for(int i = 0; i < listaTrabalhos.size(); i++){
            if(listaTrabalhos.get(i).getId().equals(trabalhoId)){
                listaTrabalhos.remove(i);
                break;
            }
        }

    }

    @Override
    public Trabalho getTrabalho(String trabalhoId) {

        for(Trabalho trabalhoMemoria: listaTrabalhos){
            if(trabalhoMemoria.getId().equals(trabalhoId)){
                return trabalhoMemoria;
            }
        }

        return null;

    }


    @Override
    public ArrayList<Trabalho> getListaTrabalhos() {
        return listaTrabalhos;
    }

    public static TrabalhoDAO getInstance(){
        if(trabalhoDAO == null){
            trabalhoDAO = new TrabalhoDBMemory();
        }

        return trabalhoDAO;
    }
}


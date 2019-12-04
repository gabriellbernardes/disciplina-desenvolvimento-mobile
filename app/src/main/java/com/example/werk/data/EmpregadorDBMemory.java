package com.example.werk.data;

import com.example.werk.model.Empregador;

import java.util.ArrayList;

public class EmpregadorDBMemory implements EmpregadorDAO{

    private static ArrayList<Empregador> listaEmpregadores;
    private static EmpregadorDBMemory empregadorDAO;

    private EmpregadorDBMemory(){

        listaEmpregadores = new ArrayList<Empregador>();

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

    public void addEmpregador(Empregador empregador) {
        listaEmpregadores.add(empregador);
    }

    public void editEmpregador(Empregador empregador){

        for(Empregador empregadorMemoria: listaEmpregadores){

            if(empregadorMemoria.getEmail().equals(empregador.getEmail())){
                empregadorMemoria.setNome(empregador.getNome());
                empregadorMemoria.setTelefone(empregador.getTelefone()); //TODO MUDAR O CPF PODE GERAR ALGUM PROBLEMA
                empregadorMemoria.setEndereco(empregador.getEndereco());
                empregadorMemoria.setGenero(empregador.getGenero());
                empregadorMemoria.setDataNascimento(empregador.getDataNascimento());
            }
        }
    }

    @Override
    public void deleteEmpregador(String empregadorEmail) {

        for(int i = 0; i < listaEmpregadores.size(); i++){
            if(listaEmpregadores.get(i).getEmail().equals(empregadorEmail)){
                listaEmpregadores.remove(i);
                break;
            }
        }

    }

    @Override
    public Empregador getEmpregador(String empregadorEmail) {

        for(Empregador empregadorMemoria: listaEmpregadores){
            if(empregadorMemoria.getEmail().equals(empregadorEmail)){
                return empregadorMemoria;
            }
        }

        return null;

    }

    @Override
    public Empregador login(String empregadorEmail, String empregadorSenha){

        for(Empregador empregadorMemoria: listaEmpregadores){
            if(empregadorMemoria.getEmail().equals(empregadorEmail) && empregadorMemoria.getSenha().equals(empregadorSenha)){
                return empregadorMemoria;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Empregador> getListaEmpregadores() {
        return listaEmpregadores;
    }

    public static EmpregadorDAO getInstance(){
        if(empregadorDAO == null){
            empregadorDAO = new EmpregadorDBMemory();
        }

        return empregadorDAO;
    }
}


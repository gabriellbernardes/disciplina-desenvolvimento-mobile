package com.example.werk.data;

import com.example.werk.model.Empregado;

import java.util.ArrayList;

public class EmpregadoDBMemory implements EmpregadoDAO{

    private static ArrayList<Empregado> listaEmpregados;
    private static EmpregadoDBMemory empregadoDAO;

    private EmpregadoDBMemory(){

        listaEmpregados = new ArrayList<Empregado>();

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

    public void addEmpregado(Empregado empregado) {
        listaEmpregados.add(empregado);
    }


    public void editEmpregado(Empregado empregado){

        for(Empregado empregadoMemoria: listaEmpregados){

            if(empregadoMemoria.getEmail().equals(empregado.getEmail())){
                empregadoMemoria.setNome(empregado.getNome());
                empregadoMemoria.setTelefone(empregado.getTelefone()); //TODO MUDAR O CPF PODE GERAR ALGUM PROBLEMA
                empregadoMemoria.setEndereco(empregado.getEndereco());
                empregadoMemoria.setGenero(empregado.getGenero());
                empregadoMemoria.setDataNascimento(empregado.getDataNascimento());
            }
        }
    }

    @Override
    public void deleteEmpregado(String empregadoEmail) {

        for(int i = 0; i < listaEmpregados.size(); i++){
            if(listaEmpregados.get(i).getEmail().equals(empregadoEmail)){
                listaEmpregados.remove(i);
                break;
            }
        }

    }

    @Override
    public Empregado getEmpregado(String empregadoEmail) {

        for(Empregado empregadoMemoria: listaEmpregados){
            if(empregadoMemoria.getEmail().equals(empregadoEmail)){
                return empregadoMemoria;
            }
        }

        return null;

    }

    @Override
    public Empregado login(String empregadoEmail, String empregadoSenha){

        for(Empregado empregadoMemoria: listaEmpregados){
            if(empregadoMemoria.getEmail().equals(empregadoEmail) && empregadoMemoria.getSenha().equals(empregadoSenha)){
                return empregadoMemoria;
            }
        }

        return null;
    }


    @Override
    public ArrayList<Empregado> getListaEmpregados() {
        return listaEmpregados;
    }

    public static EmpregadoDAO getInstance(){
        if(empregadoDAO == null){
            empregadoDAO = new EmpregadoDBMemory();
        }


        return empregadoDAO;
    }
}


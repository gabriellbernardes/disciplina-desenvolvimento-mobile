package com.example.werk.data;

import com.example.werk.model.Empregador;

import java.util.ArrayList;

public interface EmpregadorDAO {
    public void addEmpregador(Empregador empregador);
    public void editEmpregador(Empregador empregador);
    public void deleteEmpregador(Empregador empregador);
    public Empregador getEmpregador(String empregadorEmail);
    public Empregador login(String empregadorEmail, String empregadorSenha);
    public ArrayList<Empregador> getListaEmpregadores();
}
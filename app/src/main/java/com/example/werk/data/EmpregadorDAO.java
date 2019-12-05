package com.example.werk.data;

import com.example.werk.model.Empregador;

import java.util.ArrayList;

public interface EmpregadorDAO {
    void addEmpregador(Empregador empregador);
    void editEmpregador(Empregador empregador);
    void deleteEmpregador(Empregador empregador);
    Empregador getEmpregador(String empregadorEmail);
    Empregador login(String empregadorEmail, String empregadorSenha);
    ArrayList<Empregador> getListaEmpregadores();
}
package com.example.werk.data;

import com.example.werk.model.Empregado;

import java.util.ArrayList;

public interface EmpregadoDAO {
    void addEmpregado(Empregado empregado);
    void editEmpregado(Empregado empregado);
    void deleteEmpregado(Empregado empregado);
    Empregado getEmpregado(String empregadoEmail);
    Empregado login(String empregadoEmail, String empregadoSenha);
    ArrayList<Empregado> getListaEmpregados();
}
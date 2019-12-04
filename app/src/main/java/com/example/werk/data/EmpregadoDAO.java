package com.example.werk.data;

import com.example.werk.model.Empregado;

import java.util.ArrayList;

public interface EmpregadoDAO {
    public void addEmpregado(Empregado empregado);
    public void editEmpregado(Empregado empregado);
    public void deleteEmpregado(String empregadoEmail);
    public Empregado getEmpregado(String empregadoEmail);
    public Empregado login(String empregadoEmail, String empregadoSenha);
    public ArrayList<Empregado> getListaEmpregados();
}
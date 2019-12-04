package com.example.werk.data;

import com.example.werk.model.Trabalho;

import java.util.ArrayList;

public interface TrabalhoDAO {
    public void addTrabalho(Trabalho trabalho);
    public void editTrabalho(Trabalho trabalho);
    public void deleteTrabalho(String trabalhoId);
    public Trabalho getTrabalho(String trabalhoId);
    public ArrayList<Trabalho> getListaTrabalhos();
    public void aceitarTrabalho(Trabalho trabalho);
}
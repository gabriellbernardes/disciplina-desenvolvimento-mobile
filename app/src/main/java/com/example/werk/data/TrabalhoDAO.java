package com.example.werk.data;

import com.example.werk.model.Trabalho;

import java.util.ArrayList;

public interface TrabalhoDAO {
    void addTrabalho(Trabalho trabalho);
    void editTrabalho(Trabalho trabalho);
    void deleteTrabalho(String trabalhoId);
    Trabalho getTrabalho(String trabalhoId);
    ArrayList<Trabalho> getListaTrabalhos();
    void aceitarTrabalho(Trabalho trabalho);
}
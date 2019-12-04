package com.example.werk.data;

import com.example.werk.model.Solicitacao;

import java.util.ArrayList;

public interface SolicitacaoDAO {
    public void addSolicitacao(Solicitacao solicitacao);
    public void editSolicitacao(Solicitacao solicitacao);
    public void deleteSolicitacao(String solicitacaoID);
    public Solicitacao getSolicitacao(String solicitacaoID);
    public ArrayList<Solicitacao> getListaSolicitacoes();
    public void aceitarSolicitacao(Solicitacao solicitacao);
}
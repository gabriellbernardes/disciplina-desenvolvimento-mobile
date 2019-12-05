package com.example.werk.data;

import com.example.werk.model.Solicitacao;

import java.util.ArrayList;

public interface SolicitacaoDAO {
    void addSolicitacao(Solicitacao solicitacao);
    void editSolicitacao(Solicitacao solicitacao);
    void deleteSolicitacao(String solicitacaoID);
    Solicitacao getSolicitacao(String solicitacaoID);
    ArrayList<Solicitacao> getListaSolicitacoes();
    void aceitarSolicitacao(Solicitacao solicitacao);
}
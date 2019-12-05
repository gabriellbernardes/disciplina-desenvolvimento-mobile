package com.example.werk.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.werk.model.Solicitacao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SolicitacaoDBMemory implements SolicitacaoDAO{

    private static ArrayList<Solicitacao> listaSolicitacoes;
    private static SolicitacaoDBMemory solicitacaoDAO;

    private SolicitacaoDBMemory(){

        listaSolicitacoes = new ArrayList<Solicitacao>();
        
    }

    public void aceitarSolicitacao(Solicitacao solicitacao) {
        solicitacao.setStatus(1);
        editSolicitacao(solicitacao);
    }
    //public void addSolicitacao(Solicitacao solicitacao) {
    //    listaSolicitacoes.add(solicitacao);
    //}

    public void editSolicitacao(Solicitacao solicitacao){

        for(Solicitacao solicitacaoMemoria: listaSolicitacoes){

            if(solicitacaoMemoria.getId().equals(solicitacao.getId())){
                solicitacaoMemoria.setEmpregado(solicitacao.getEmpregado());
                solicitacaoMemoria.setEmpregador(solicitacao.getEmpregador());
                solicitacaoMemoria.setStatus(solicitacao.getStatus());
                solicitacaoMemoria.setTrabalho(solicitacao.getTrabalho());
            }
        }
    }
    public void addSolicitacao(Solicitacao solicitacao) {
         Log.d("Teste", solicitacao.getUuid());

        FirebaseFirestore.getInstance().collection("solicitacoes").document(solicitacao.getUuid()).set(solicitacao)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Teste", "Sucesso");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Teste", e.getMessage(), e);
            }
        });
    }


        @Override
    public void deleteSolicitacao(String solicitacaoId) {

        for(int i = 0; i < listaSolicitacoes.size(); i++){
            if(listaSolicitacoes.get(i).getId().equals(solicitacaoId)){
                listaSolicitacoes.remove(i);
                break;
            }
        }

    }

    @Override
    public Solicitacao getSolicitacao(String solicitacaoId) {

        for(Solicitacao solicitacaoMemoria: listaSolicitacoes){
            if(solicitacaoMemoria.getId().equals(solicitacaoId)){
                return solicitacaoMemoria;
            }
        }

        return null;

    }


    @Override
    public ArrayList<Solicitacao> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public static SolicitacaoDAO getInstance(){
        if(solicitacaoDAO == null){
            solicitacaoDAO = new SolicitacaoDBMemory();
        }

        return solicitacaoDAO;
    }

}


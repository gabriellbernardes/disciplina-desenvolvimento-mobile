package com.example.werk.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.werk.model.Empregador;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class EmpregadorFireBase implements  EmpregadorDAO {
    @Override
    public void addEmpregador(Empregador empregador) {

        FirebaseFirestore.getInstance().collection("empregadores")
                .document(empregador.getUuid())
                .set(empregador)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("teste", "sucess");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("teste", e.getMessage(), e);

                    }
                });
    }

    @Override
    public void editEmpregador(Empregador empregador) {
        FirebaseFirestore.getInstance().collection("empregadores")
                .document(empregador.getUuid())
                .update("nome", empregador.getNome(),
                        "genero", empregador.getGenero(),
                        "telefone", empregador.getTelefone(),
                        "dataNascimento", empregador.getDataNascimento(),
                        "endereco", empregador.getEndereco()

                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("teste", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("teste", e.getMessage(), e);

                    }
                });


    }

    @Override
    public void deleteEmpregador(Empregador empregador) {
        FirebaseFirestore.getInstance().collection("empregadres")
                .document(empregador.getUuid())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("teste", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("teste", e.getMessage(), e);

                    }
                });
    }

    @Override
    public Empregador getEmpregador(String empregadorEmail) {
        return null;
    }

    @Override
    public Empregador login(String empregadorEmail, String empregadorSenha) {
        return null;
    }

    @Override
    public ArrayList<Empregador> getListaEmpregadores() {
        return null;
    }
}

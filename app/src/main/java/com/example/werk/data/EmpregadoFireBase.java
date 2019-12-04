package com.example.werk.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.werk.model.Empregado;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class EmpregadoFireBase implements EmpregadoDAO {
    @Override
    public void addEmpregado(Empregado empregado) {
        FirebaseFirestore.getInstance().collection("empregados")
                .document(empregado.getUuid())
                .set(empregado)
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
    public void editEmpregado(Empregado empregado) {
        FirebaseFirestore.getInstance().collection("empregados")
                .document(empregado.getUuid())
                .update("nome", empregado.getNome(),
                        "genero", empregado.getGenero(),
                        "telefone", empregado.getTelefone(),
                        "dataNascimento", empregado.getDataNascimento(),
                        "endereco", empregado.getEndereco()

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
    public void deleteEmpregado(Empregado empregado) {

        FirebaseFirestore.getInstance().collection("empregados")
                .document(empregado.getUuid())
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
    public Empregado getEmpregado(String empregadoEmail) {
        return null;
    }

    @Override
    public Empregado login(String empregadoEmail, String empregadoSenha) {
        return null;
    }

    @Override
    public ArrayList<Empregado> getListaEmpregados() {
        return null;
    }
}

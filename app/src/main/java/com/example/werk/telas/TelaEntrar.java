package com.example.werk.telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.werk.R;
import com.example.werk.data.EmpregadoDAO;
import com.example.werk.data.EmpregadorDAO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaEntrar extends AppCompatActivity {
    EditText email;
    EditText senha;
    EmpregadorDAO empregadorDAO;
    EmpregadoDAO empregadoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_entrar);

        email = findViewById(R.id.username);
        senha = findViewById(R.id.password);
    }


    public void login(View view) {
        String emailLogin = email.getText().toString();
        String senhaLogin = senha.getText().toString();
        if(emailLogin == null|| emailLogin.isEmpty() || senhaLogin == null || senhaLogin.isEmpty()|| !emailLogin.contains("@") || !emailLogin.contains(".com")){

            Toast.makeText(view.getContext(), "Preencha os campos corretamentes", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailLogin, senhaLogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("teste", task.getResult().getUser().getUid());
                        Intent intent = new Intent(TelaEntrar.this, MenuInfoActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("teste", e.getMessage());
                        return;
                    }
                });


    }


}


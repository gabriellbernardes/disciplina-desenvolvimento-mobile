package com.example.werk.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.werk.R;
import com.example.werk.telas.TelaCadastro;
import com.example.werk.telas.TelaEntrar;
import com.example.werk.telas.VisitanteActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OnClickButtonEntrar(View view){
        Intent entrar = new Intent(this, TelaEntrar.class);
        startActivity(entrar);

        }

    public void OnClickButtonCadastrar(View view){
        Intent cadastrar = new Intent(this, TelaCadastro.class);
        startActivity(cadastrar);

    }

    public void OnClickButtonVisitar(View view){
        Intent visitar = new Intent(this, VisitanteActivity.class);
        startActivity(visitar);
    }


}

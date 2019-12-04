package com.example.werk.telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.werk.R;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;

public class VisitanteActivity extends AppCompatActivity {
    Empregado empregado;
    Empregador empregador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitante);
        if(getIntent().getStringExtra("tipoUsuario").equals("Empregado")){
            Empregado empregado = (Empregado) getIntent().getSerializableExtra("usuario");
        }else if(getIntent().getStringExtra("tipoUsuario").equals("Empregador")){
            Empregador empregador = (Empregador) getIntent().getSerializableExtra("usuario");
        }
//        HomeFragmento hf = new HomeFragmento();
//
//
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.containerVisitante, hf);
//        ft.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visitante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //escolhas de opções a oartir do menu
        switch (item.getItemId()){
            case R.id.logar:
                Intent logar = new Intent(this, TelaCadastro.class);
                startActivity(logar);
                break;

            case R.id.pesquisar:
                OnClickButtonPesquisar(item.getActionView());
                break;
        }


        return super .onOptionsItemSelected(item);

    }

    private void OnClickButtonPesquisar(View actionView) {

    }



    public void setFragment (Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerVisitante, fragment);
        ft.commit();

    }
}
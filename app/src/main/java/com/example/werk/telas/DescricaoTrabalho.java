package com.example.werk.telas;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.werk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DescricaoTrabalho extends AppCompatActivity {
    private FloatingActionButton confrima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_trabalho);
        confrima = findViewById(R.id.confirma);

        confrima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pedido enviado para o Empregador, Aguarde a resposta!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public void voltar(View view) {
        finish();

    }
}

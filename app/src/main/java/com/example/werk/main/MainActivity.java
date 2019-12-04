package com.example.werk.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.werk.R;
import com.example.werk.data.EmpregadoDAO;
import com.example.werk.data.EmpregadoDBMemory;
import com.example.werk.data.EmpregadorDAO;
import com.example.werk.data.EmpregadorDBMemory;
import com.example.werk.data.SolicitacaoDAO;
import com.example.werk.data.SolicitacaoDBMemory;
import com.example.werk.data.TrabalhoDAO;
import com.example.werk.data.TrabalhoDBMemory;
import com.example.werk.telas.TelaCadastro;
import com.example.werk.telas.TelaEntrar;
import com.example.werk.telas.VisitanteActivity;

public class MainActivity extends AppCompatActivity {
    EmpregadorDAO empregadorDAO;

    TrabalhoDAO trabalhoDAO;
    EmpregadoDAO empregadoDAO;
    SolicitacaoDAO solicitacaoDAO;
    //Empregador empregador = new Empregador(0,"190238091","diogo","Masculino","098098","098098","Diogo","diogo", "safmaso", new ArrayList<Trabalho>());
    //Empregado empregado = new Empregado(uuid, "pedro", "Masculino","2809842","9283098","pedro","pedro","asjndlkaj",0,new ArrayList<Trabalho>());
    //Trabalho trabalho = new Trabalho("1", "igidjj","424","lfhsilah","231231","sdsada");
    //Trabalho trabalho2 = new Trabalho("2", "igidjj","424","lfhsilah","231231","sdsada");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        empregadorDAO =  EmpregadorDBMemory.getInstance();
        empregadoDAO = EmpregadoDBMemory.getInstance();
        trabalhoDAO = TrabalhoDBMemory.getInstance();
        solicitacaoDAO = SolicitacaoDBMemory.getInstance();
      //  empregadorDAO.addEmpregador(empregador);
        //empregadoDAO.addEmpregado(empregado);
       // trabalhoDAO.addTrabalho(trabalho);
   //     Solicitacao solicitacao = new Solicitacao(String.valueOf("1"), empregador, 1, trabalho);
        //solicitacao.setEmpregado(empregado);
   //     Solicitacao solicitacao1 = new Solicitacao(String.valueOf("2"), empregador, 0, trabalho2);
   //     solicitacaoDAO.addSolicitacao(solicitacao);
   //     solicitacaoDAO.addSolicitacao(solicitacao1);

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

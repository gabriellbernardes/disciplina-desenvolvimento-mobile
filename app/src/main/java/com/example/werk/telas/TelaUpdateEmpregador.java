package com.example.werk.telas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.werk.R;
import com.example.werk.data.EmpregadorDAO;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;

public class TelaUpdateEmpregador extends AppCompatActivity {
    RadioGroup rgSexo;
    RadioButton rbSexo;
    EditText nome;
    EditText endereco;
    EditText telefone;
    EditText dataNascimento;
    String generoEmpregador = "Masculino";
    EmpregadorDAO empregadorDAO;
    Empregado empregado;
    Empregador empregador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_empregado);
//        empregadorDAO = EmpregadorDBMemory.getInstance();
        if(getIntent().getStringExtra("tipoUsuario").equals("Empregado")){
            empregado = (Empregado) getIntent().getSerializableExtra("usuario");
        }else if(getIntent().getStringExtra("tipoUsuario").equals("Empregador")){
            empregador = (Empregador) getIntent().getSerializableExtra("usuario");
        }

        rgSexo = findViewById(R.id.generoEmp2);
        nome = findViewById(R.id.nome1Text);
        endereco = findViewById(R.id.endereco1Text);
        telefone = findViewById(R.id.contato1Text);
        dataNascimento = findViewById(R.id.nascimento2Text);
        rgSexo = findViewById(R.id.generoEmp2);
    }

    public void atualizar(View view){
        rbSexo = findViewById(rgSexo.getCheckedRadioButtonId());
        generoEmpregador = rbSexo.getText().toString();
        String nomeEmpregador = nome.getText().toString();
        String enderecoEmpregador = endereco.getText().toString();
        String telefoneEmpregador = telefone.getText().toString();
        String nascimentoEmpregador = dataNascimento.getText().toString();

        //Empregador empregador1 = new Empregador(empregador.getAvaliacao(), empregador.getCpf(), empregador.getEmail(), generoEmpregador, telefoneEmpregador, nascimentoEmpregador, nomeEmpregador, empregador.getSenha(), enderecoEmpregador, empregador.getTrabalhos());

       // empregadorDAO.editEmpregador(empregador1);
        //getIntent().putExtra("usuario", empregado);
        if(empregadorDAO != null){
            Empregador empregador2 = empregadorDAO.getEmpregador(empregado.getEmail());
            Toast.makeText(this, empregador2.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}


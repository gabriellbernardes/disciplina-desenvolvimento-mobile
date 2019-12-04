package com.example.werk.telas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.werk.R;
import com.example.werk.data.EmpregadoDAO;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;

public class TelaUpdateEmpregado extends AppCompatActivity {
    RadioGroup rgSexo;
    RadioButton rbSexo;
    EditText nome;
    EditText endereco;
    EditText telefone;
    EditText dataNascimento;
    String generoEmpregado = "Masculino";
    EmpregadoDAO empregadoDAO;
    Empregado empregado;
    Empregador empregador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_empregado);
//        empregadoDAO = EmpregadoDBMemory.getInstance();
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
        generoEmpregado = rbSexo.getText().toString();
        String nomeEmpregado = nome.getText().toString();
        String enderecoEmpregado = endereco.getText().toString();
        String telefoneEmpregado = telefone.getText().toString();
        String nascimentoEmpregado = dataNascimento.getText().toString();

        //Empregado empregado1 = new Empregado(uuid, empregado.getEmail(), generoEmpregado, telefoneEmpregado, nascimentoEmpregado, nomeEmpregado, empregado.getSenha(), enderecoEmpregado, empregado.getAvaliacao(), empregado.getTrabalhos());

        //empregadoDAO.editEmpregado(empregado1);
        //getIntent().putExtra("usuario", empregado);
        if(empregadoDAO != null){
            Empregado empregado2 = empregadoDAO.getEmpregado(empregado.getEmail());
            Toast.makeText(this, empregado2.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}


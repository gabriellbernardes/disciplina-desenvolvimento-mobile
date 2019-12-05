package com.example.werk.ui.update;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.werk.R;
import com.example.werk.data.EmpregadoDAO;
import com.example.werk.data.EmpregadorDAO;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;

public class UpdateFragmento extends Fragment {
    private RadioGroup rgSexo;
    RadioButton rbSexo;
    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText dataNascimento;
    private String generoEmpregado = "Masculino";
    private String generoEmpregador = "Masculino";
    private EmpregadoDAO empregadoDAO;
    private EmpregadorDAO empregadorDAO;
    private Empregado empregado;
    private Empregador empregador;
    private UpdateViewModel updateViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        empregadoDAO = EmpregadoDBMemory.getInstance();
//        empregadorDAO = EmpregadorDBMemory.getInstance();
        View root = null;
        if(getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregado")) {
            updateViewModel = ViewModelProviders.of(this).get(UpdateViewModel.class);
            root = inflater.inflate(R.layout.activity_update_empregado, container, false);
        }else if(getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregador")) {
            updateViewModel = ViewModelProviders.of(this).get(UpdateViewModel.class);
            root = inflater.inflate(R.layout.activy_update_empregador, container, false);
        }
        rgSexo = root.findViewById(R.id.generoEmp2);
        nome = root.findViewById(R.id.nome1Text);
        endereco = root.findViewById(R.id.endereco1Text);
        telefone = root.findViewById(R.id.contato1Text);
        dataNascimento = root.findViewById(R.id.nascimento2Text);
        rgSexo = root.findViewById(R.id.generoEmp2);
        final RadioGroup rgSexo = root.findViewById(R.id.generoEmp2);
        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbSexo = group.findViewById(checkedId);
                generoEmpregador = rbSexo.getText().toString();
                generoEmpregado = rbSexo.getText().toString();
            }
        });
        Button button = root.findViewById(R.id.cadastrar);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregado")) {
                    empregado = (Empregado) getActivity().getIntent().getSerializableExtra("usuario");


                    String nomeEmpregado = nome.getText().toString();
                    String enderecoEmpregado = endereco.getText().toString();
                    String telefoneEmpregado = telefone.getText().toString();
                    String nascimentoEmpregado = dataNascimento.getText().toString();

                    //Empregado empregado1 = new Empregado(uuid, empregado.getEmail(), generoEmpregado, telefoneEmpregado, nascimentoEmpregado, nomeEmpregado, empregado.getSenha(), enderecoEmpregado, empregado.getAvaliacao(), empregado.getTrabalhos());

                    //empregadoDAO.editEmpregado(empregado1);
                  //  getActivity().getIntent().putExtra("usuario", empregado);
                    if (empregadoDAO != null) {
                        Empregado empregado2 = empregadoDAO.getEmpregado(empregado.getEmail());
                        //Toast.makeText(this, empregado2.toString(), Toast.LENGTH_SHORT).show();
                    }
                }else if(getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregador")){
                    empregador = (Empregador) getActivity().getIntent().getSerializableExtra("usuario");

                    String nomeEmpregador = nome.getText().toString();
                    String enderecoEmpregador = endereco.getText().toString();
                    String telefoneEmpregador = telefone.getText().toString();
                    String nascimentoEmpregador = dataNascimento.getText().toString();

                   // Empregador empregador1 = new Empregador(empregador.getAvaliacao(),empregador.getCpf(), empregador.getEmail(), generoEmpregador, telefoneEmpregador, nascimentoEmpregador, nomeEmpregador, empregador.getSenha(), enderecoEmpregador, empregado.getTrabalhos());

                   // empregadorDAO.editEmpregador(empregador1);
                   // getActivity().getIntent().putExtra("usuario", empregador);
                    if (empregadorDAO != null) {
                        Empregador empregador2 = empregadorDAO.getEmpregador(empregador.getEmail());
                        //Toast.makeText(root.getContext(), empregado2.toString(), Toast.LENGTH_SHORT).show();
                    }
        }


        }
        });
        return root;
    }
}
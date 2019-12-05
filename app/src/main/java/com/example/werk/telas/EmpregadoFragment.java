package com.example.werk.telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.werk.R;
import com.example.werk.data.EmpregadoDAO;
import com.example.werk.data.EmpregadoFireBase;
import com.example.werk.model.Empregado;
import com.example.werk.model.Trabalho;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;


public class EmpregadoFragment extends Fragment {
    private RadioGroup rgSexo;
    RadioButton rbSexo;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText endereco;
    private EditText telefone;
    private EditText dataNascimento;
    private String generoEmpregado = "Masculino";
    private String uuid;
    EmpregadoDAO empDAO = new EmpregadoFireBase();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_empregado, container, false);

        rgSexo = view.findViewById(R.id.generoEmp2);
        nome = view.findViewById(R.id.nome1Text);
        email = view.findViewById(R.id.email1Text);
        senha = view.findViewById(R.id.senha2Text);
        endereco = view.findViewById(R.id.endereco1Text);
        telefone = view.findViewById(R.id.contato1Text);
        dataNascimento = view.findViewById(R.id.nascimento2Text);

        final RadioGroup rgSexo = view.findViewById(R.id.generoEmp2);
        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbSexo = group.findViewById(checkedId);
                generoEmpregado = rbSexo.getText().toString();
            }
        });

        Button button = view.findViewById(R.id.cadastrar);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String nomeEmpregado = nome.getText().toString();
                final String emailEmpregado = email.getText().toString();
                final String enderecoEmpregado = endereco.getText().toString();
                final String senhaEmpregado = senha.getText().toString();
                final String telefoneEmpregado = telefone.getText().toString();
                final String nascimentoEmpregado = dataNascimento.getText().toString();
                if (nomeEmpregado == null || nomeEmpregado.isEmpty() ||
                        emailEmpregado == null || emailEmpregado.isEmpty() || !emailEmpregado.contains("@") || !emailEmpregado.contains(".com") ||
                        senhaEmpregado == null || senhaEmpregado.isEmpty()
                        || senhaEmpregado.length() < 6) {


                    if (senhaEmpregado.length() < 6) {
                        Toast.makeText(v.getContext(), "senha deve ter pelo menos 6 caracteres", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(v.getContext(), "Preencha os campos corretamentes", Toast.LENGTH_SHORT).show();
                    return;
                }


                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEmpregado, senhaEmpregado)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    uuid = FirebaseAuth.getInstance().getUid();
                                    Empregado empregado = new Empregado(uuid, emailEmpregado, generoEmpregado,
                                            telefoneEmpregado, nascimentoEmpregado, nomeEmpregado,
                                            senhaEmpregado, enderecoEmpregado,-1, new ArrayList<Trabalho>());

                                    empDAO.addEmpregado(empregado);
                                    Intent i = new Intent(view.getContext(), MenuInfoActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    Toast.makeText(view.getContext(), "Cadastrado", Toast.LENGTH_SHORT).show();
                                    startActivity(i);

                                }else{
                                    Toast.makeText(view.getContext(), "Erro ao Cadastrar", Toast.LENGTH_SHORT).show();

                                }


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Teste", e.getMessage());
                    }
                });
            }
        });

        return view;
    }


}

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
import com.example.werk.data.EmpregadorDAO;
import com.example.werk.data.EmpregadorFireBase;
import com.example.werk.model.Empregador;
import com.example.werk.model.Trabalho;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class EmpregadorFragment extends Fragment {
    private RadioGroup rgSexo;
    RadioButton rbSexo;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText endereco;
    private EditText telefone;
    private EditText dataNascimento;
    private EditText cpf;
    private String uuid;
    private String generoEmpregador = "Masculino";
    private EmpregadorDAO empDAO = new EmpregadorFireBase();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_empregador, container, false);

        rgSexo = view.findViewById(R.id.generoEmp2);
        nome = view.findViewById(R.id.tipoTrabalho1Text);
        email = view.findViewById(R.id.descricaoTrabalho1Text);
        senha = view.findViewById(R.id.senha2Text);
        endereco = view.findViewById(R.id.endereco1Text);
        telefone = view.findViewById(R.id.contato1Text);
        dataNascimento = view.findViewById(R.id.nascimento2Text);
        cpf = view.findViewById(R.id.pagamento1Text);

        final RadioGroup rgSexo = (RadioGroup) view.findViewById(R.id.generoEmp2);

        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbSexo = (RadioButton) group.findViewById(checkedId);
                generoEmpregador = rbSexo.getText().toString();
            }
        });

        Button button = view.findViewById(R.id.cadastrar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String nomeEmpregador = nome.getText().toString();
                final String emailEmpregador = email.getText().toString();
                final String enderecoEmpregador = endereco.getText().toString();
                final String senhaEmpregador = senha.getText().toString();
                final String telefoneEmpregador = telefone.getText().toString();
                final String nascimentoEmpregador = dataNascimento.getText().toString();
                final String cpfEmpregador = cpf.getText().toString();

                if (nomeEmpregador == null || nomeEmpregador.isEmpty() ||
                        emailEmpregador == null || emailEmpregador.isEmpty() || !emailEmpregador.contains("@") || !emailEmpregador.contains(".com") ||
                        senhaEmpregador == null || senhaEmpregador.isEmpty() ||
                        cpfEmpregador.isEmpty() || cpfEmpregador == null || cpfEmpregador.length() != 11
                        || senhaEmpregador.length() < 6) {

                    if (senhaEmpregador.length() < 6) {
                        Toast.makeText(v.getContext(), "senha deve ter pelo menos 6 caracteres", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (cpfEmpregador.length() != 11) {
                        Toast.makeText(v.getContext(), "Cpf Invalido", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Toast.makeText(v.getContext(), "Preencha os campos corretamentes", Toast.LENGTH_SHORT).show();
                    return;
                }



                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEmpregador, senhaEmpregador)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    uuid = FirebaseAuth.getInstance().getUid();
                                    Empregador empregador = new Empregador(uuid, -1, cpfEmpregador, emailEmpregador, generoEmpregador
                                            , telefoneEmpregador, nascimentoEmpregador, nomeEmpregador, senhaEmpregador, enderecoEmpregador
                                            , new ArrayList<Trabalho>());

                                    empDAO.addEmpregador(empregador);
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


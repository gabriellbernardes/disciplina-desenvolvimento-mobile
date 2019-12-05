package com.example.werk.telas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.werk.R;
import com.example.werk.data.EmpregadoDAO;
import com.example.werk.data.EmpregadorDAO;
import com.example.werk.data.SolicitacaoDAO;
import com.example.werk.data.SolicitacaoDBMemory;
import com.example.werk.data.TrabalhoDAO;
import com.example.werk.data.TrabalhoDBMemory;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;
import com.example.werk.model.Solicitacao;
import com.example.werk.model.Trabalho;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class TelaCadastroTrabalho extends AppCompatActivity{

    EditText tipo;
    EditText pagamento;
    EditText descricao;
    EditText contato;
    EditText endereco;
    TrabalhoDAO trabalhoDAO;
    EmpregadorDAO empregadorDAO;
    EmpregadoDAO empregadoDAO;
    Empregado empregado;
    Empregador empregador;
    Solicitacao solicitacao;
    Empregador empregadorSolicitacao;
    int id = 0;
    ArrayAdapter adapter;
    ListView listViewTrabalhos;
    SolicitacaoDAO solicitacaoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_trabalho);

        trabalhoDAO = TrabalhoDBMemory.getInstance();
//        empregadorDAO = EmpregadorDBMemory.getInstance();
        tipo = findViewById(R.id.tipoTrabalho1Text);
//        empregadoDAO = EmpregadoDBMemory.getInstance();
        solicitacaoDAO = SolicitacaoDBMemory.getInstance();
        pagamento = findViewById(R.id.pagamento1Text);
        descricao = findViewById(R.id.descricaoTrabalho1Text);
        contato = findViewById(R.id.contato1Text);
        endereco = findViewById(R.id.endereco1Text);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, trabalhoDAO.getListaTrabalhos());

        //if(this.getIntent().getStringExtra("tipoUsuario").equals("Empregado")){
        //    empregado = (Empregado) getIntent().getSerializableExtra("usuario");
        //}else if(this.getIntent().getStringExtra("tipoUsuario").equals("Empregador")){
        //    empregador = (Empregador) getIntent().getSerializableExtra("usuario");
        //}
    }


    public void adicionarTrabalho(View view){
        String tipoTrabalho = tipo.getText().toString();
        String pagamentoTrabalho = pagamento.getText().toString();
        String descricaoTrabalho = descricao.getText().toString();
        String enderecoTrabalho = endereco.getText().toString();
        String contatoTrabalho = contato.getText().toString();
        id = trabalhoDAO.getListaTrabalhos().size()+1;
        String idTrabalho = String.valueOf(id);
        //FirebaseFirestore.getInstance().collection("trabalhos").document().getId();
        final Trabalho trabalho = new Trabalho(FirebaseFirestore.getInstance().collection("trabalhos").document().getId(),idTrabalho, tipoTrabalho, pagamentoTrabalho, descricaoTrabalho, contatoTrabalho, enderecoTrabalho);
        FirebaseFirestore.getInstance().collection("trabalhos").add(trabalho)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("Teste", documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Teste", e.getMessage());
            }
        });
            FirebaseFirestore.getInstance().collection("empregadores").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.e("Teste", e.getMessage(), e);
                        return;
                    }
                    List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot doc : docs){
                        empregador = doc.toObject(Empregador.class);
                        if(empregador.getUuid().equals(FirebaseAuth.getInstance().getUid())){
                            //empregadorr[0] = empregador;
                            solicitacao = new Solicitacao(FirebaseFirestore.getInstance().collection("solicitacoes").document().getId(), String.valueOf(solicitacaoDAO.getListaSolicitacoes().size()+1), empregador, 0, trabalho);
                            solicitacaoDAO.addSolicitacao(solicitacao);
                            //
                            Log.d("Teste", empregador.toString());
                        }

                    }
                }
            });
        //Toast.makeText(TelaCadastroTrabalho.this,empregadorr[0].toString(), Toast.LENGTH_SHORT).show();

        //trabalhoDAO.addTrabalho(trabalho);
        //solicitacao.setEmpregador(empregadorr[0]);
        //solicitacaoDAO.addSolicitacao(solicitacao);
        //empregador.adicionaTrabalho(trabalho);
        //empregadorDAO.editEmpregador(empregador);
        //FirebaseFirestore.getInstance().collection("solicitacoes").document().getId();

       // if(trabalhoDAO.getListaTrabalhos()!= null){
       //     Toast.makeText(this, "lista", Toast.LENGTH_SHORT).show();
       // }
       // if(trabalhoDAO.getTrabalho(idTrabalho)!= null){
       //     Toast.makeText(this,  trabalhoDAO.getTrabalho(idTrabalho).toString(), Toast.LENGTH_SHORT).show();
       //      Trabalho trabalho1 = trabalhoDAO.getTrabalho(String.valueOf(id));
       //     Toast.makeText(this, trabalho1.toString(), Toast.LENGTH_SHORT).show();
       //
       //        }
        adapter.notifyDataSetChanged();
    }

}

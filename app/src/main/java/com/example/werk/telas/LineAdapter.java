package com.example.werk.telas;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werk.R;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;
import com.example.werk.model.Solicitacao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {
    private Empregado empregado;
    private Empregador empregador;
    private final List<Solicitacao> mUsers;
    private String tipoTela;

    public String getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(String tipoTela) {
        this.tipoTela = tipoTela;
    }


    public LineAdapter(ArrayList users) {
        mUsers = users;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }


    private void updateItem(int position) {
        final Solicitacao userModel = mUsers.get(position);
        userModel.setStatus(userModel.getStatus()+1);

            FirebaseFirestore.getInstance().collection("empregados").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.e("Teste", e.getMessage(), e);
                        return;
                    }
                    List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot doc : docs) {
                        empregado = doc.toObject(Empregado.class);
                        if(FirebaseAuth.getInstance().getUid().equals(empregado.getUuid()) && userModel.getEmpregado() == null) {
                            if (userModel.getEmpregado() == null) {
                                userModel.setEmpregado(empregado);
                                FirebaseFirestore.getInstance().collection("solicitacoes").document(userModel.getUuid()).update("status", userModel.getStatus(), "empregado", empregado).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Teste", "DocumentSnapshot successfully written!");
                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("Teste", "Error writing document", e);
                                            }
                                        });
                                Log.d("Teste", empregado.toString());
                            }
                        }else{
                            FirebaseFirestore.getInstance().collection("solicitacoes").document(userModel.getUuid()).update("status", userModel.getStatus());
                    }
                }
            }
            });
            //document(FirebaseAuth.getInstance().getUid());

        notifyDataSetChanged();
        //notifyItemChanged(position);
    }

    private void removerItem(int position) {
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
    }
    @Override
    public void onBindViewHolder(final LineHolder holder, final int position) {
        holder.title.setText(mUsers.get(position).toString());
        holder.title.setText(String.format(Locale.getDefault(), "Tipo de trabalho: %s \n" +
                        "Pagamento: R$%s \n" +
                        "Empregador: %s",
                mUsers.get(position).getTrabalho().getTipo(),
                mUsers.get(position).getTrabalho().getPagamento(),
                mUsers.get(position).getEmpregador().getNome()
                ));

            FirebaseFirestore.getInstance().collection("empregadores").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.e("Teste", e.getMessage(), e);
                        return;
                    }
                    List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot doc : docs) {
                        empregador = doc.toObject(Empregador.class);
                        if(FirebaseAuth.getInstance().getUid().equals(empregador.getUuid())){
                            if(tipoTela.equals("Vagas")){
                                holder.deleteButton.setVisibility(View.GONE);
                                holder.moreButton.setVisibility(View.GONE);
                            }else if(!(mUsers.get(position).getEmpregador().getUuid().equals(empregador.getUuid()))){
                                holder.deleteButton.setVisibility(View.GONE);
                                holder.moreButton.setVisibility(View.GONE);
                            }else if(mUsers.get(position).getStatus()!=1){
                                holder.moreButton.setVisibility(View.GONE);
                                holder.deleteButton.setVisibility(View.VISIBLE);
                            }else{
                                holder.moreButton.setVisibility(View.VISIBLE);
                                holder.deleteButton.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            });
        FirebaseFirestore.getInstance().collection("empregados").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("Teste", e.getMessage(), e);
                    return;
                }
                List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot doc : docs) {
                    empregado = doc.toObject(Empregado.class);
                    if(FirebaseAuth.getInstance().getUid().equals(empregado.getUuid())) {
                        if (mUsers.get(position).getEmpregado() != null) {
                            if ((mUsers.get(position).getEmpregado().getUuid().equals(empregado.getUuid()))) {
                                if (mUsers.get(position).getStatus() >= 1) {
                                    holder.deleteButton.setVisibility(View.GONE);
                                    holder.moreButton.setVisibility(View.GONE);
                                } else {
                                    holder.deleteButton.setVisibility(View.VISIBLE);
                                    holder.moreButton.setVisibility(View.GONE);
                                }
                            }else if (mUsers.get(position).getEmpregado() == null) {
                                holder.moreButton.setVisibility(View.VISIBLE);
                                holder.deleteButton.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }
        });
        holder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem(position);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               removerItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

}

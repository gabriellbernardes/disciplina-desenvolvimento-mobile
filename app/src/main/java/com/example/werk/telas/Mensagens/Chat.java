package com.example.werk.telas.Mensagens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werk.R;
import com.example.werk.model.Contato;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;
import com.example.werk.model.Menssagem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.List;

public class Chat extends AppCompatActivity {

    private GroupAdapter adapter;
    Empregado empregado;
    Empregador empregador;
    EditText editText;
    String tipo = "empregado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if (tipo.contains("empregador")) {
            empregado = getIntent().getExtras().getParcelable("empregado");
            getSupportActionBar().setTitle(empregado.getNome());
        }else{
            empregador = getIntent().getExtras().getParcelable("empregador");
            getSupportActionBar().setTitle(empregador.getNome());
        }

        RecyclerView rv = findViewById(R.id.chatR);

        editText = findViewById(R.id.chatText);
        ImageButton btChat = findViewById(R.id.send);

        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMenssagem();

            }
        });

        adapter = new GroupAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        mensagens();
    }

    private void mensagens(){
        if(tipo.contains("empregador")){
            FirebaseFirestore.getInstance().collection("empregadores")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            empregador = documentSnapshot.toObject(Empregador.class);
                            fechMensagens();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }else{
            FirebaseFirestore.getInstance().collection("empregados")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            empregado = documentSnapshot.toObject(Empregado.class);
                            fechMensagens();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

    private void fechMensagens() {
        if(empregador != null && tipo.contains("empregador")){
            String toID = empregado.getUuid();
            String fromId = empregador.getUuid();

            FirebaseFirestore.getInstance().collection("conversas")
                    .document(fromId)
                    .collection(toID)
//                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                            List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();

                            if(documentChanges != null){
                                for (DocumentChange doc: documentChanges) {
                                    if(doc.getType() == DocumentChange.Type.ADDED){

                                        Menssagem m = doc.getDocument().toObject(Menssagem.class);

                                        adapter.add(new MessagemItem(m));
                                    }
                                }
                            }
                        }
                    });
        }else if(empregado != null && tipo.contains("empregado")){
            String toID = empregador.getUuid();
            String fromId = empregado.getUuid();

            FirebaseFirestore.getInstance().collection("conversas")
                    .document(fromId)
                    .collection(toID)
//                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                            List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();

                            if (documentChanges != null) {
                                for (DocumentChange doc : documentChanges) {
                                    if (doc.getType() == DocumentChange.Type.ADDED) {

                                        Menssagem m = doc.getDocument().toObject(Menssagem.class);

                                        adapter.add(new MessagemItem(m));
                                    }
                                }

                            }
                        }
                    });
        }
    }

    private void enviarMenssagem() {


        if(tipo.contains("empregador")) {
            String text = editText.getText().toString();
            editText.setText(null);

            final String fomId = FirebaseAuth.getInstance().getUid();
            final String toId = empregado.getUuid();


            long time = System.currentTimeMillis();

            final Menssagem menssagem = new Menssagem();
            menssagem.setFomId(fomId);
            menssagem.setToId(toId);
            menssagem.setTimeStamp(time);
            menssagem.setMensagem(text);

            if (!menssagem.getMensagem().isEmpty()) {

                FirebaseFirestore.getInstance().collection("conversas")
                        .document(fomId)
                        .collection(toId)
                        .add(menssagem)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Contato c = new Contato();
                                c.setId(toId);
                                c.setEmail(empregado.getNome());
                                c.setIdfoto(empregado.getIdFoto());
                                c.setTempo(menssagem.getTimeStamp());
                                c.setUltimamensagem(menssagem.getMensagem());
                                FirebaseFirestore.getInstance()
                                        .collection("ultima-mensagem")
                                        .document(toId)
                                        .collection("empregado").document(fomId)
                                        .set(c);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("test", e.getMessage(), e);
                    }
                });

                FirebaseFirestore.getInstance().collection("conversas")
                        .document(toId)
                        .collection(fomId)
                        .add(menssagem)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Contato c = new Contato();
                                c.setId(toId);
                                c.setEmail(empregado.getNome());
                                c.setIdfoto(empregado.getIdFoto());
                                c.setTempo(menssagem.getTimeStamp());
                                c.setUltimamensagem(menssagem.getMensagem());

                                FirebaseFirestore.getInstance()
                                        .collection("ultima-mensagem")
                                        .document(fomId)
                                        .collection("empregador").document(toId)
                                        .set(c);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("test", e.getMessage(), e);
                    }
                });
            }
        }else{
            String text = editText.getText().toString();
            editText.setText(null);

            final String fomId = FirebaseAuth.getInstance().getUid();
            final String toId = empregador.getUuid();


            long time = System.currentTimeMillis();

            final Menssagem menssagem = new Menssagem();
            menssagem.setFomId(fomId);
            menssagem.setToId(toId);
            menssagem.setTimeStamp(time);
            menssagem.setMensagem(text);

            if (!menssagem.getMensagem().isEmpty()) {

                FirebaseFirestore.getInstance().collection("conversas")
                        .document(fomId)
                        .collection(toId)
                        .add(menssagem)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Contato c = new Contato();
                                c.setId(toId);
                                c.setEmail(empregador.getNome());
                                c.setIdfoto(empregador.getIdFoto());
                                c.setTempo(menssagem.getTimeStamp());
                                c.setUltimamensagem(menssagem.getMensagem());
                                FirebaseFirestore.getInstance()
                                        .collection("ultima-mensagem")
                                        .document(toId)
                                        .collection("empregado").document(fomId)
                                        .set(c);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("test", e.getMessage(), e);
                    }
                });

                FirebaseFirestore.getInstance().collection("conversas")
                        .document(toId)
                        .collection(fomId)
                        .add(menssagem)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Contato c = new Contato();
                                c.setId(toId);
                                c.setEmail(empregador.getNome());
                                c.setIdfoto(empregador.getIdFoto());
                                c.setTempo(menssagem.getTimeStamp());
                                c.setUltimamensagem(menssagem.getMensagem());

                                FirebaseFirestore.getInstance()
                                        .collection("ultima-mensagem")
                                        .document(fomId)
                                        .collection("empregador").document(toId)
                                        .set(c);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("test", e.getMessage(), e);
                    }
                });
            }
        }
    }


    private class MessagemItem extends Item<ViewHolder> {
        private final Menssagem menssagem;

        private MessagemItem( Menssagem menssagem) {
            this.menssagem = menssagem;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView nome = viewHolder.itemView.findViewById(R.id.nameItem);
            TextView texto = viewHolder.itemView.findViewById(R.id.text_item);
            ImageView image = viewHolder.itemView.findViewById(R.id.avatar);

            texto.setText(menssagem.getMensagem());
//            Picasso.get().load(user.getProfileUrl()).into(image);
        }

        @Override
        public int getLayout() {
            boolean a =(menssagem.getFomId().equals(FirebaseAuth.getInstance().getUid()));
            return a?R.layout.me_item_mensagem : R.layout.to_item_mensagem;
        }
    }
}




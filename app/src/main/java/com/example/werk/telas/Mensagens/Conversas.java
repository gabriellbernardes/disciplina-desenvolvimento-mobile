package com.example.werk.telas.Mensagens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werk.R;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.ViewHolder;

import java.util.List;

public class Conversas extends AppCompatActivity {
    private GroupAdapter adapter;
    private int verifica = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversas);

        RecyclerView rv = findViewById(R.id.recycle);
        final String id = FirebaseAuth.getInstance().getUid();
        adapter = new GroupAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        if(FirebaseFirestore.getInstance().collection("empregadores").document(id).get() != null){
            verifica = 1;
        }
        else if(FirebaseFirestore.getInstance().collection("empregados").document(id).get() != null){
            verifica = 2;

        }else{
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT);
        }
            adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent i = new Intent(Conversas.this, Chat.class);
                if(verifica == 1){
                    ConversaItem2 conversa = (ConversaItem2) item;
                    i.putExtra("empregador", conversa.empregador);
                    i.putExtra("tipo", "empregador");
                    startActivity(i);
                }else if(verifica == 2){
                    ConversaItem1 conversa = (ConversaItem1) item;
                    i.putExtra("empregado", conversa.empregado);
                    i.putExtra("tipo", "empregado");
                    startActivity(i);
                }
            }
        });


        fechUser(verifica);
    }

    private void fechUser(int verifica) {
        switch (verifica){
            case 1:
                FirebaseFirestore.getInstance().collection("empregadores")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                if(e != null){
                                    Log.e("teste", e.getMessage(), e);
                                    return;
                                }
                                List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot doc : docs){
                                    Empregador user = doc.toObject(Empregador.class);
                                    adapter.add(new ConversaItem2(user));
                                }
                            }
                        });
                break;
            case 2:
                FirebaseFirestore.getInstance().collection("empregados")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                if(e != null){
                                    Log.e("teste", e.getMessage(), e);
                                    return;
                                }
                                List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot doc : docs){
                                    Empregado user = doc.toObject(Empregado.class);
                                    adapter.add(new ConversaItem1(user));
                                }
                            }
                        });
                break;
        }
    }



    private class ConversaItem1 extends Item<ViewHolder> {
        private final Empregado empregado;
        private ConversaItem1(Empregado empregado){
            this.empregado = empregado;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView txtUsername = viewHolder.itemView.findViewById(R.id.nom_conversa);
            TextView txtEmail = viewHolder.itemView.findViewById(R.id.ultima_conversa);
            ImageView image = viewHolder.itemView.findViewById(R.id.foto_conversa);

            txtUsername.setText(empregado.getNome());
            txtEmail.setText(empregado.getEmail());
//            Picasso.get()
//                    .load(empregado.getIdFoto())
//                    .into(image);

        }

        @Override
        public int getLayout() {
            return R.layout.item_conversa;
        }
    }

    private class ConversaItem2 extends Item<ViewHolder> {
        private final Empregador empregador;
        private ConversaItem2(Empregador empregador){
            this.empregador = empregador;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView txtUsername = viewHolder.itemView.findViewById(R.id.nom_conversa);
            ImageView image = viewHolder.itemView.findViewById(R.id.foto_conversa);
            TextView txtEmail = viewHolder.itemView.findViewById(R.id.ultima_conversa);

            txtUsername.setText(empregador.getNome());
            txtEmail.setText(empregador.getEmail());

//            Picasso.get()
//                    .load(empregador.getIdFoto())
//                    .into(image);

        }

        @Override
        public int getLayout() {
            return R.layout.item_conversa;
        }
    }

}
//TODO colocar as fotos depois
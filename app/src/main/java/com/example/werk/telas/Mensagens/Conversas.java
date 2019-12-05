package com.example.werk.telas.Mensagens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werk.R;
import com.example.werk.model.Empregador;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversas);

        RecyclerView rv = findViewById(R.id.recycle);
        adapter = new GroupAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

            adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent i = new Intent(Conversas.this, Chat.class);
                ConversaItem conversa = (ConversaItem) item;
                Log.i("teste", conversa.empregador.getUuid());
                i.putExtra("empregador", conversa.empregador);

                startActivity(i);
            }
        });


        fechUser();
    }

    private void fechUser() {
//        switch (verifica){
//            case 1:
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
                                    Empregador user = doc.toObject(Empregador.class);
                                    adapter.add(new ConversaItem(user));
                                }
                            }
                        });

    }


//
    private class ConversaItem extends Item<ViewHolder> {
        private final Empregador empregador;
        private ConversaItem(Empregador empregador){
            this.empregador = empregador;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView txtUsername = viewHolder.itemView.findViewById(R.id.nom_conversa);
            TextView txtEmail = viewHolder.itemView.findViewById(R.id.ultima_conversa);
            ImageView image = viewHolder.itemView.findViewById(R.id.foto_conversa);

            txtEmail.setText(empregador.getEmail());
            txtUsername.setText(empregador.getNome());
//            Picasso.get()
//                    .load(empregado.getIdFoto())
//                    .into(image);

        }

        @Override
        public int getLayout() {
            return R.layout.item_conversa;
        }
    }


}
//TODO colocar as fotos depois
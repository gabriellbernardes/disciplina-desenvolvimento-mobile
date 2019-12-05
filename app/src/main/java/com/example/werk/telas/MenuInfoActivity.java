package com.example.werk.telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.werk.R;
import com.example.werk.main.MainActivity;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;
import com.example.werk.telas.Mensagens.Conversas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MenuInfoActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    NavigationView navigationView;
    Empregado empregado;
    Empregador empregador;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verifyAuthentication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Adicionar novo Trabalho", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                transf(view);

            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.config)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void transf(View v) {
        final Intent intent = new Intent(this, TelaCadastroTrabalho.class);

        if (empregado != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else if (empregador != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private void verifyAuthentication() {

        if (FirebaseAuth.getInstance().getUid() == null) {
            Intent intent = new Intent(MenuInfoActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            if((FirebaseFirestore.getInstance().collection("empregados").document(FirebaseAuth.getInstance().getUid()))!= null){
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
                                fab.hide();
                                Log.d("Teste", empregado.toString());
                            }
                        }
                    }
                });
                //document(FirebaseAuth.getInstance().getUid());
            }else if((FirebaseFirestore.getInstance().collection("empregadores").document(FirebaseAuth.getInstance().getUid()))!= null){
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
                            Log.d("Teste", empregador.getUuid());
                            if(FirebaseAuth.getInstance().getUid().equals(empregador.getUuid())){
                                fab.hide();
                                Log.d("Teste", empregador.toString());
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deslogar:
                FirebaseAuth.getInstance().signOut();
                verifyAuthentication();
                break;
            case R.id.mensagens:
                Intent i = new Intent(this, Conversas.class);
                startActivity(i);
                break;

        }
        ;
        return super.onOptionsItemSelected(item);
    }
}




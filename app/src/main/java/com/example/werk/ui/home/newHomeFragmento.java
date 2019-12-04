/*package com.example.werk.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.werk.R;
import com.example.werk.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class HomeFragmento extends Fragment {
    private HomeViewModel homeViewModel;
    private FloatingActionButton like;
    private FloatingActionButton unlike;
    private ImageView imagem;
    private int imagemAtual;
    private TextView textView;
    private int[] imagens ={R.drawable.abelhinha, R.drawable.calcamento, R.drawable.pista, R.drawable.refrigeracao};


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.activity_home, container, false);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getContext(), this.getActivity().getSupportFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        viewPager = (ViewPager) root.findViewById(R.id.view_pager);
        //ViewPagerAdapter adapter = new ViewPagerAdapter(this.getActivity().getSupportFragmentManager());
        //viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*
        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("About"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager =(ViewPager)root.findViewById(R.id.viewpager);
        TabsAdapter tabsAdapter = new TabsAdapter(this.getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //View root = inflater.inflate(R.layout.fragment_home, container, true);

        return root;
    }






}*/



/*package com.example.werk.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.werk.R;
import com.example.werk.telas.DescricaoTrabalho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class Home2Fragmento  extends Fragment {
    private HomeViewModel homeViewModel;
    private FloatingActionButton like;
    private FloatingActionButton unlike;
    private ImageView imagem;
    private int imagemAtual;
    private TextView textView;
    private int[] imagens = {R.drawable.abelhinha, R.drawable.calcamento, R.drawable.pista, R.drawable.refrigeracao};


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().

                observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        textView.setText(s);
                    }
                });

        like = root.findViewById(R.id.like);
        unlike = root.findViewById(R.id.deslike);
        imagem = root.findViewById(R.id.trabalhos);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Entrando no Trabalho", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                imagemAtual = imagens[random.nextInt(imagens.length)];
                imagem.setImageResource(imagemAtual);

                switch (imagemAtual) {
                    case R.drawable.abelhinha:
                        textView.setText("Capinar o canteiro da pista");

                        break;
                    case R.drawable.calcamento:
                        textView.setText("Manutenção de calçamento");

                        break;
                    case R.drawable.pista:
                        textView.setText("Construção de Rodovia");

                        break;
                    case R.drawable.refrigeracao:
                        textView.setText("Concerto de ar-condicionado");

                        break;
                }

                Snackbar.make(view, "Proximo Trabalho", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaDescricao = new Intent(getActivity()
                        , DescricaoTrabalho.class);
                startActivity(telaDescricao);

            }
        });


        imagem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent telaDescricao = new Intent(getActivity()
                                , DescricaoTrabalho.class);
                        startActivity(telaDescricao);

                    }
                }
        );


        return root;
    }
}*/
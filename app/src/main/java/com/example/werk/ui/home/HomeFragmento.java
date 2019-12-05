package com.example.werk.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.werk.R;
import com.example.werk.telas.TelaTrabalhos;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmento extends Fragment {

        /*@Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }*/

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fixtures_new_tabs,container, false);
            // Setting ViewPager for each Tabs
            ViewPager viewPager = view.findViewById(R.id.view_pager);
            setupViewPager(viewPager);
            // Set Tabs inside Toolbar
            TabLayout tabs = view.findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);

            //if(this.getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregado")) {
            //}

            return view;

        }


        // Add Fragments to Tabs
        private void setupViewPager(ViewPager viewPager) {

            //if(this.getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregado")) {
            //    //Toast.makeText(this.getActivity(),"EMPREGADO", Toast.LENGTH_SHORT).show();
            //    Empregado empregado = (Empregado) this.getActivity().getIntent().getSerializableExtra("usuario");
            //}else if(this.getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregador")) {
            //    //Toast.makeText(this.getActivity(), "EMPREGADOR", Toast.LENGTH_SHORT).show();
            //    Empregador empregador = (Empregador) this.getActivity().getIntent().getSerializableExtra("usuario");
            //}
            Adapter adapter = new Adapter(getChildFragmentManager());
            adapter.addFragment(new Home2Fragmento(), "Vagas");
            adapter.addFragment(new TelaTrabalhos(), "Solicitações");
            viewPager.setAdapter(adapter);



        }

        static class Adapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

            public Adapter(FragmentManager manager) {
                super(manager);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            public void addFragment(Fragment fragment, String title) {
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitleList.get(position);
            }
        }



    }
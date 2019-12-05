package com.example.werk.telas;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.werk.R;
import com.example.werk.data.SolicitacaoDAO;
import com.example.werk.data.SolicitacaoDBMemory;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;
import com.example.werk.model.Solicitacao;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
        private ArrayList<String> list = new ArrayList<String>();
        private Context context;
        String tipoUsuario;
        Intent intent;
        char id;
    SolicitacaoDAO solicitacaoDAO;
    String s;
    char status;
    int posicao ;
    Empregador empregador;
    Empregado empregado;
    public MyCustomAdapter(ArrayList<String> list, Context context, String x, Intent i) {
            this.list = list;
            this.context = context;
            this.tipoUsuario = x;
            this.intent = i;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            //return list.get(pos).getId();
            return 0;
            //just return 0 if your list items do not have an Id variable.
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.new_layout, null);
                }
            //Toast.makeText(this.context, (intent.getStringExtra("tipoUsuario")), Toast.LENGTH_SHORT).show();
            if(intent.getStringExtra("tipoUsuario").equals("Empregador")){
               empregador = ((Empregador) (intent.getSerializableExtra("usuario")));
            }else if(intent.getStringExtra("tipoUsuario").equals("Empregado")){
                empregado = ((Empregado) (intent.getSerializableExtra("usuario")));
            }
            //getView(position,convertView,parent);
            //ctivity a = view.getContext().startActivity(view.);
            //Intent intent = Intent.getIntent();
            //Handle TextView and display string from your list
            TextView listItemText = view.findViewById(R.id.list_item_string);
            listItemText.setText(list.get(position));
            //Toast.makeText(view.getContext(), String.valueOf(list.get(position)), Toast.LENGTH_SHORT).show();
            s = list.get(position);

            final int index = list.get(position).indexOf("id=");
            //Toast.makeText(view.getContext(), String.valueOf(s.charAt(index)), Toast.LENGTH_SHORT).show();
            //String k = s.charAt(index+3);

            id = s.charAt(index);
           // Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
           // Toast.makeText(view.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
            posicao = list.get(position).indexOf("status=");
           // s[posicao] = list.get()
            //s.charAt(posicao+1);
            //split("=*,");
            //= list.get(posicao);
          //  s = s.replaceAll("[^0-9]", "");
           // int indice = s[posicao].indexOf("id=");
            Log.e("teste" , (s.charAt(posicao+7))+"");
            status = s.charAt(posicao+7);

         // Toast.makeText(view.getContext(),  String.valueOf(status), Toast.LENGTH_SHORT).show();
          //  posicao = s.;


            //Handle buttons and add onClickListeners
            Button deleteBtn = view.findViewById(R.id.delete_btn);

            Button addBtn = view.findViewById(R.id.add_btn);
            //Toast.makeText(view.getContext(), tipoUsuario, Toast.LENGTH_SHORT).show();
                if(tipoUsuario.equals("Empregador_Solicitacoes")){
                    deleteBtn.setVisibility(View.VISIBLE);
                    //Toast.makeText(view.getContext(), s[posicao], Toast.LENGTH_SHORT).show();
                   if((status == '0')|| (status == '2')|| (status == '3')) {
                       addBtn.setVisibility(View.GONE);
                   }else{
                       addBtn.setVisibility(View.VISIBLE);
                   }
                  //  }

            }else if(tipoUsuario.equals("Empregado_Solicitacoes")){
                    addBtn.setVisibility(View.GONE);
                    deleteBtn.setVisibility(View.GONE);

                }else if(tipoUsuario.equals("Empregador_Vagas")){
                    addBtn.setVisibility(View.GONE);
                    deleteBtn.setVisibility(View.GONE);
                }else if(tipoUsuario.equals("Empregado_Vagas")){
                    addBtn.setVisibility(View.VISIBLE);
                    deleteBtn.setVisibility(View.GONE);
                }
                  //  deleteBtn.setVisibility(View.GONE);

            deleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something

                    if(tipoUsuario.equals("Empregador_Solicitacoes")){
                        final int index = list.get(position).indexOf("id=");
                        id = list.get(position).charAt(index+3);
                        //Toast.makeText(v.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                        solicitacaoDAO = SolicitacaoDBMemory.getInstance();
                        solicitacaoDAO.deleteSolicitacao(String.valueOf(id));
                        list.remove(position); //or some other task

                    }else{
                        list.remove(position); //or some other task
                    }


                    notifyDataSetChanged();
                }
            });
            addBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(tipoUsuario.equals("Empregado_Vagas")){
                        //Toast.makeText(view.getContext(), s[posicao], Toast.LENGTH_SHORT).show();
                            //1
                            //Toast.makeText(v.getContext(), id, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(v.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();

                       // Toast.makeText(v.getContext(), String.valueOf(list.get(position)), Toast.LENGTH_SHORT).show();
                        final int index = list.get(position).indexOf("id=");
                        id = list.get(position).charAt(index+3);
                        //Toast.makeText(v.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                            solicitacaoDAO = SolicitacaoDBMemory.getInstance();
                            Solicitacao solicitacao1 = solicitacaoDAO.getSolicitacao(String.valueOf(id));
                        Log.e("id",String.valueOf(id));
                            //Toast.makeText(v.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                            solicitacao1.setStatus(1);
                            solicitacao1.setEmpregado(empregado);
                            //Toast.makeText(v.getContext(), solicitacao1.toString(), Toast.LENGTH_SHORT).show();
                            solicitacaoDAO.editSolicitacao(solicitacao1);

                      //  }
                    }else if(tipoUsuario.equals("Empregador_Solicitacoes")){

                        //Toast.makeText(view.getContext(), s[posicao], Toast.LENGTH_SHORT).show();
                        //1
                        //Toast.makeText(v.getContext(), id, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(v.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();

                        // Toast.makeText(v.getContext(), String.valueOf(list.get(position)), Toast.LENGTH_SHORT).show();
                        final int index = list.get(position).indexOf("id=");
                        id = list.get(position).charAt(index+3);
                        //Toast.makeText(v.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                        solicitacaoDAO = SolicitacaoDBMemory.getInstance();
                        Solicitacao solicitacao1 = solicitacaoDAO.getSolicitacao(String.valueOf(id));
                        Log.e("id",String.valueOf(id));
                        //Toast.makeText(v.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                        solicitacao1.setStatus(2);
                        //Toast.makeText(v.getContext(), solicitacao1.toString(), Toast.LENGTH_SHORT).show();
                        solicitacaoDAO.editSolicitacao(solicitacao1);
                    }
                    notifyDataSetChanged();
                }
            });

            return view;
        }
    }

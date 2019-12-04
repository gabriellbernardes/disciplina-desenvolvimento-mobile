package com.example.werk.telas;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.werk.data.SolicitacaoDAO;
import com.example.werk.data.TrabalhoDAO;
import com.example.werk.model.Empregado;
import com.example.werk.model.Empregador;

public class TelaTrabalhos extends Fragment {

    int selected;
    int idSelected;
    //ArrayList<Contato> listaContatos;
    ArrayAdapter adapter;
    MyCustomAdapter adapter1;
    ListView listViewTrabalhos;
    Empregador empregador;
    Empregado empregado;
    TrabalhoDAO trabalhoDAO;
    SolicitacaoDAO solicitacaoDAO;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.lista_solicitacoes, container, false);
//        if(this.getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregador")) {
//            //Toast.makeText(this.getActivity(), (String) (this.getActivity().getIntent().getStringExtra("tipoUsuario")), Toast.LENGTH_SHORT).show();
//            empregador = (Empregador) this.getActivity().getIntent().getSerializableExtra("usuario");
//            solicitacaoDAO = SolicitacaoDBMemory.getInstance();
//        trabalhoDAO = TrabalhoDBMemory.getInstance();
//        // contatoDAO = ContatoDBFirebase.getInstance();
//
//        selected = -1;
//        idSelected = -1;
//
//        //listaContatos = new ArrayList<Contato>();
//                ArrayList<String> list = new ArrayList<String>();
//                for(Solicitacao solicitacao: solicitacaoDAO.getListaSolicitacoes()){
//                    if(solicitacao.getEmpregador().getEmail().equals(empregador.getEmail())){
//                        list.add(solicitacao.toString());
//                    }
//                    //Toast.makeText(this.getActivity(), solicitacao.getTrabalho().toString(), Toast.LENGTH_SHORT).show();
//                }
//        //Toast.makeText(this.getActivity(), String.valueOf(trabalhoDAO.getListaTrabalhos().size()), Toast.LENGTH_SHORT).show();
//                //generate list
//                //instantiate custom adapter
//                adapter1 = new MyCustomAdapter(list, this.getActivity(), "Empregador_Solicitacoes", this.getActivity().getIntent());
//                //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//        //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//        listViewTrabalhos = (ListView) root.findViewById(R.id.listViewTrabalhos);
//        listViewTrabalhos.setAdapter(new MyCustomAdapter((ArrayList<String>) list, this.getActivity(), "Empregador_Solicitacoes", this.getActivity().getIntent()) );
//        }else if(this.getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregado")) {
//
//            //Toast.makeText(this.getActivity(), (String) (this.getActivity().getIntent().getStringExtra("tipoUsuario")), Toast.LENGTH_SHORT).show();
//            empregado = (Empregado) this.getActivity().getIntent().getSerializableExtra("usuario");
//            solicitacaoDAO = SolicitacaoDBMemory.getInstance();
//            trabalhoDAO = TrabalhoDBMemory.getInstance();
//            // contatoDAO = ContatoDBFirebase.getInstance();
//
//            selected = -1;
//            idSelected = -1;
//
//            //listaContatos = new ArrayList<Contato>();
//            ArrayList<String> list = new ArrayList<String>();
//            for(Solicitacao solicitacao: solicitacaoDAO.getListaSolicitacoes()){
//                if(solicitacao.getEmpregado()!=null) {
//                    if (solicitacao.getEmpregado().getEmail().equals(empregado.getEmail())) {
//                        list.add(solicitacao.toString());
//                    }
//                }
//                //Toast.makeText(this.getActivity(), solicitacao.getTrabalho().toString(), Toast.LENGTH_SHORT).show();
//            }
//            //Toast.makeText(this.getActivity(), String.valueOf(trabalhoDAO.getListaTrabalhos().size()), Toast.LENGTH_SHORT).show();
//            //generate list
//            //instantiate custom adapter
//            adapter1 = new MyCustomAdapter(list, this.getActivity(), "Empregado_Solicitacoes", this.getActivity().getIntent());
//            //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//            //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//            listViewTrabalhos = (ListView) root.findViewById(R.id.listViewTrabalhos);
//            listViewTrabalhos.setAdapter(new MyCustomAdapter((ArrayList<String>) list, this.getActivity(), "Empregado_Solicitacoes", this.getActivity().getIntent()) );
//        }
//                //handle listview and assign adapter
//                //ListView lView = (ListView) root.findViewById(R.id.my_listview);
//              //  lView.setAdapter(adapter);
//
//        //adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, trabalhoDAO.getListaTrabalhos());
//
//        //listViewTrabalhos = (ListView) root.findViewById(R.id.listViewTrabalhos);
//        //listViewTrabalhos.setAdapter(adapter1);
//        //listViewTrabalhos.setSelector(android.R.color.holo_blue_light);
///*
//        listViewTrabalhos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                // Toast.makeText(this, "" + trabalhoDAO.getListaTrabalhos().get( position ).toString(), Toast.LENGTH_SHORT).show();
//                //Buscando um contato pelo id
//                idSelected = Integer.parseInt(trabalhoDAO.getListaTrabalhos().get(position).getId());
//                selected = position;
//            }
//        });
//  */      return root;
//    }
//
//    @Override
//    public void onStart(){
//        if(this.getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregador")) {
//            //Toast.makeText(this.getActivity(), (String) (this.getActivity().getIntent().getStringExtra("tipoUsuario")), Toast.LENGTH_SHORT).show();
//            empregador = (Empregador) this.getActivity().getIntent().getSerializableExtra("usuario");
//            solicitacaoDAO = SolicitacaoDBMemory.getInstance();
//            trabalhoDAO = TrabalhoDBMemory.getInstance();
//            // contatoDAO = ContatoDBFirebase.getInstance();
//
//            selected = -1;
//            idSelected = -1;
//
//            //listaContatos = new ArrayList<Contato>();
//            ArrayList<String> list = new ArrayList<String>();
//            for (Solicitacao solicitacao : solicitacaoDAO.getListaSolicitacoes()) {
//                if(solicitacao.getEmpregador().getEmail().equals(empregador.getEmail())){
//                    list.add(solicitacao.toString());
//                }
//
//                //Toast.makeText(this.getActivity(), solicitacao.getTrabalho().toString(), Toast.LENGTH_SHORT).show();
//            }
//            adapter1 = new MyCustomAdapter(list, this.getActivity(), "Empregador_Solicitacoes", this.getActivity().getIntent());
//            //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//            //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//            listViewTrabalhos.setAdapter(new MyCustomAdapter((ArrayList<String>) list, this.getActivity(), "Empregador_Solicitacoes",this.getActivity().getIntent()));
//        }else if(this.getActivity().getIntent().getStringExtra("tipoUsuario").equals("Empregado")){
//           Empregado usuario = (Empregado) (this.getActivity().getIntent().getSerializableExtra("usuario"));
//            //Toast.makeText(this.getActivity(),usuario.toString(), Toast.LENGTH_SHORT).show();
//            Empregado empregado = (Empregado) this.getActivity().getIntent().getSerializableExtra("usuario");
//            //Toast.makeText(this.getActivity(), (String) (this.getActivity().getIntent().getStringExtra("tipoUsuario")), Toast.LENGTH_SHORT).show();
//            empregado = (Empregado) this.getActivity().getIntent().getSerializableExtra("usuario");
//            solicitacaoDAO = SolicitacaoDBMemory.getInstance();
//            trabalhoDAO = TrabalhoDBMemory.getInstance();
//            // contatoDAO = ContatoDBFirebase.getInstance();
//
//            selected = -1;
//            idSelected = -1;
//
//            //listaContatos = new ArrayList<Contato>();
//            ArrayList<String> list = new ArrayList<String>();
//            for (Solicitacao solicitacao : solicitacaoDAO.getListaSolicitacoes()) {
//                if(solicitacao.getEmpregado()!=null) {
//                    if (solicitacao.getEmpregado().getEmail().equals(empregado.getEmail())) {
//                        list.add(solicitacao.toString());
//                    }
//                }
//                //Toast.makeText(this.getActivity(), solicitacao.getTrabalho().toString(), Toast.LENGTH_SHORT).show();
//            }
//            adapter1 = new MyCustomAdapter(list, this.getActivity(), "Empregado_Solicitacoes", this.getActivity().getIntent());
//            //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//            //List list1 = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
//            listViewTrabalhos.setAdapter(new MyCustomAdapter((ArrayList<String>) list, this.getActivity(), "Empregado_Solicitacoes", this.getActivity().getIntent()));
//
//        }
//     /*   trabalhoDAO = TrabalhoDBMemory.getInstance();
//        ArrayList<String> list = new ArrayList<String>();
//        for(Trabalho trabalho: trabalhoDAO.getListaTrabalhos()) {
//            list.add(trabalho.toString());
//            Toast.makeText(this.getActivity(), trabalho.toString(), Toast.LENGTH_SHORT).show();
//        }
//        adapter1 = new MyCustomAdapter(list, this.getActivity());
//        listViewTrabalhos.setAdapter(new MyCustomAdapter((ArrayList<String>) list, this.getActivity()) );
//       */
//        adapter1.notifyDataSetChanged();
//        super.onStart();
//
//    }
//}
//   /* Fragment frg = null;
//      frg = this;
//      final FragmentTransaction ft = this.getActivity().getSupportFragmentManager().beginTransaction();
//      ft.detach(frg);
//      ft.attach(frg);
//      ft.commit();
//*/
//    /*
//        public void updateListaContatos( Agenda agenda ){
//
//            progressBar.setVisibility( View.INVISIBLE );
//
//            Contato[] lista = agenda.getListaTelefone();
//            for( Contato c: lista ) contatoDAO.getListaContato().add( c );
//
//            adapter.notifyDataSetChanged();
//        }
//
//
//
//        private void apagarItemLista(){
//
//            if( contatoDAO.getListaContato().size() > 0 ){
//                contatoDAO.deleteContato( idSelected );
//                adapter.notifyDataSetChanged();;
//            } else {
//                selected = -1;
//            }
//
//        }
//    */
//  /*  public void clicarAdicionar() {
//        Intent intent = new Intent(this, TelaCadastroTrabalho.class);
//        startActivityForResult(intent, SyncStateContract.Constants.);
//    }
//
//   */
//
///*
//    public void clicarEditar(){
//
//        Intent intent = new Intent( this, ContactActivity.class );
//        //Intent intent2 = new Intent( this, "br.ufc.quixada.dadm.variastelas.ContactActivity" );
//
//        Contato contato = contatoDAO.getListaContato().get( selected );
//
//        intent.putExtra( "id", contato.getId() );
////        intent.putExtra( "nome", contato.getNome() );
////        intent.putExtra( "telefone", contato.getTelefone() );
////        intent.putExtra( "endereco", contato.getEndereco() );
//
//        startActivityForResult( intent, Constants.REQUEST_EDIT );
//    }
//
//
//    @Override
//    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data ) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if( requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD ){
//
//            adapter.notifyDataSetChanged();
//
//        } else if( requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD ){
//
////          String nome = ( String )data.getExtras().get( "nome" );
////          String telefone = ( String )data.getExtras().get( "telefone" );
////          String endereco = ( String )data.getExtras().get( "endereco" );
////          int idEditar = (int)data.getExtras().get( "id" );
////
////          for( Contato contato: contatoDAO.getListaContato() ){
////
////              if( contato.getId() == idEditar ){
////                  contato.setNome( nome );
////                  contato.setEndereco( endereco );
////                  contato.setTelefone( telefone );
////              }
////          }
//
//            adapter.notifyDataSetChanged();
//
//        } //Retorno da tela de contatos com um conteudo para ser adicionado
//        //Na segunda tela, o usuario clicou no botão ADD
//        else if( resultCode == Constants.RESULT_CANCEL ){
//            Toast.makeText( this,"Cancelado",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//*/
//
//
//
//
//
//
//
}
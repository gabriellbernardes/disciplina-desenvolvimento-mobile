package com.example.werk.telas;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.werk.R;

public class TelaCadastro extends AppCompatActivity {
    Fragment empregado;
    Fragment empregador;
    RadioGroup rgTipoConta;
    RadioButton rbTipoConta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        rgTipoConta =  findViewById(R.id.tipoConta);
        empregado = new EmpregadoFragment();
        empregador = new EmpregadorFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, empregado);
        ft.commit();


    }

    public void onRadioButtonClicked(View view) {

        rbTipoConta = (RadioButton) findViewById( rgTipoConta.getCheckedRadioButtonId() );

        Toast.makeText(this, "Tipo cadastro: "+ rbTipoConta.getText().toString(), Toast.LENGTH_SHORT).show();

        switch (rbTipoConta.getId()){
            case R.id.empregado:
                setFragment(empregado);
                break;

            case R.id.empregador:
                setFragment(empregador);
                break;
        }


    }
    public void setFragment (Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();

    }
}

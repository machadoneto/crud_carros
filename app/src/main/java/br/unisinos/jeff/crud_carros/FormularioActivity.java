package br.unisinos.jeff.crud_carros;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import br.unisinos.jeff.crud_carros.dao.CarroDAO;
import br.unisinos.jeff.crud_carros.modelo.Carro;

/**
 * Created by Jeff on 07/07/2016.
 */
public class FormularioActivity extends FragmentActivity {

    private FormularioHelper helper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        final AutoCompleteTextView campo_modelo = (AutoCompleteTextView) findViewById(R.id.modelo);
        final Spinner campo_marca = (Spinner) findViewById(R.id.spi_marca);

        helper = new FormularioHelper(this);

        final Carro carroParaSerAlterado = (Carro) getIntent().getSerializableExtra("carroSelecionado");
        final Button botao = (Button) findViewById(R.id.botao);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marca, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campo_marca.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.modelo, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campo_modelo.setAdapter(adapter2);

        if(carroParaSerAlterado != null){
            helper.colocaCarroNoFormulario(carroParaSerAlterado);
            botao.setText("Alterar");
        }

        botao.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Carro carro = helper.pegaCarroDoFormulario();
                CarroDAO dao = new CarroDAO(FormularioActivity.this);

                if (carroParaSerAlterado != null){
                    carro.setId(carroParaSerAlterado.getId());
                    dao.atualizar(carro);
                }else{
                    dao.insere(carro);
                }
                dao.close();
                finish();
            }
        });
    }

}

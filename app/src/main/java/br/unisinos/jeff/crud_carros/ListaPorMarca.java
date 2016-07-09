package br.unisinos.jeff.crud_carros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import br.unisinos.jeff.crud_carros.adapter.CarroAdapter;
import br.unisinos.jeff.crud_carros.dao.CarroDAO;
import br.unisinos.jeff.crud_carros.modelo.Carro;

/**
 * Created by Jeff on 09/07/2016.
 */
public class ListaPorMarca extends AppCompatActivity {

    ListView lista;
    private Carro carro;
    String marca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_marca);

        final Spinner campo_marca = (Spinner) findViewById(R.id.spi_marca);
        Button botao = (Button) findViewById(R.id.bt_buscamarca);

        lista = (ListView) findViewById(R.id.listamarca);
        registerForContextMenu(lista);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marca, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campo_marca.setAdapter(adapter);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                marca = campo_marca.getSelectedItem().toString();

                carregaListaPorMarca();

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int posicao,
                                            long id) {

                        Carro carroParaSerAlterado = (Carro) adapter.getItemAtPosition(posicao);

                        Intent irParaOFormulario = new Intent(ListaPorMarca.this, FormularioActivity.class);
                        irParaOFormulario.putExtra("carroSelecionado", carroParaSerAlterado);

                        startActivity(irParaOFormulario);
                    }
                });

                lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view,
                                                   int posicao, long id) {
                        carro = (Carro) adapter.getItemAtPosition(posicao);

                        return false;
                    }
                });

            }
        });
    }

    private void carregaListaPorMarca() {
        CarroDAO dao = new CarroDAO(this);
        List<Carro> carros = dao.getListaMarca(marca);
        CarroAdapter adapter = new CarroAdapter(carros, this);
        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.menu_lista_carros, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.novo:
                Intent irParaFormulario = new Intent(this, FormularioActivity.class);
                startActivity(irParaFormulario);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                CarroDAO dao = new CarroDAO(ListaPorMarca.this);
                dao.deletar(carro);
                dao.close();
                carregaListaPorMarca();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
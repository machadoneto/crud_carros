package br.unisinos.jeff.crud_carros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import br.unisinos.jeff.crud_carros.adapter.CarroAdapter;
import br.unisinos.jeff.crud_carros.dao.CarroDAO;
import br.unisinos.jeff.crud_carros.modelo.Carro;

/**
 * Created by Jeff on 09/07/2016.
 */
public class ListaPorPlaca extends AppCompatActivity {

    ListView lista;
    private Carro carro;
    String placa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_placa);

        final EditText campo_placa = (EditText) findViewById(R.id.tx_placa);
        Button botao = (Button) findViewById(R.id.bt_buscaplaca);

        lista = (ListView) findViewById(R.id.listaplaca);
        registerForContextMenu(lista);



        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                placa = campo_placa.getText().toString();

                carregaListaPorPlaca();

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int posicao,
                                            long id) {

                        Carro carroParaSerAlterado = (Carro) adapter.getItemAtPosition(posicao);

                        Intent irParaOFormulario = new Intent(ListaPorPlaca.this, FormularioActivity.class);
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

    private void carregaListaPorPlaca() {
        CarroDAO dao = new CarroDAO(this);
        List<Carro> carros = dao.getListaPlaca(placa);
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
                CarroDAO dao = new CarroDAO(ListaPorPlaca.this);
                dao.deletar(carro);
                dao.close();
                carregaListaPorPlaca();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }
}

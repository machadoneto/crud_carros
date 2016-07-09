package br.unisinos.jeff.crud_carros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.unisinos.jeff.crud_carros.adapter.CarroAdapter;
import br.unisinos.jeff.crud_carros.dao.CarroDAO;
import br.unisinos.jeff.crud_carros.modelo.Carro;

/**
 * Created by Jeff on 08/07/2016.
 */
public class ListaTodos extends AppCompatActivity {

    ListView lista;
    private Carro carro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_todos);

        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao,
                                    long id) {

                Carro carroParaSerAlterado = (Carro) adapter.getItemAtPosition(posicao);

                Intent irParaOFormulario = new Intent(ListaTodos.this, FormularioActivity.class);
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

    @Override
    protected void onResume(){
        super.onResume();

        carregaLista();
    }

    private void carregaLista() {
        CarroDAO dao = new CarroDAO(this);
        List<Carro> carros = dao.getLista();
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
                CarroDAO dao = new CarroDAO(ListaTodos.this);
                dao.deletar(carro);
                dao.close();
                carregaLista();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
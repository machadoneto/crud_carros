package br.unisinos.jeff.crud_carros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lista_carros, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.novo:
                Intent irParaFormulario = new Intent(this, FormularioActivity.class);
                startActivity(irParaFormulario);
                break;
            case R.id.novo2:
                Intent irParaFormulario2 = new Intent(this, FormularioActivity.class);
                startActivity(irParaFormulario2);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ListaTodos(View v){
        Intent it = new Intent(getApplicationContext(), ListaTodos.class);
        startActivity(it);
    }

    public void ListaTodosPorAno(View v){
        Intent it = new Intent(getApplicationContext(), ListaPorAno.class);
        startActivity(it);
    }

    public void ListaTodosPorMarca(View v){
        Intent it = new Intent(getApplicationContext(), ListaPorMarca.class);
        startActivity(it);
    }

    public void ListaTodosPorModelo(View v){
        Intent it = new Intent(getApplicationContext(), ListaPorModelo.class);
        startActivity(it);
    }
    public void ListaTodosPorPlaca(View v){
        Intent it = new Intent(getApplicationContext(), ListaPorPlaca.class);
        startActivity(it);
    }


}

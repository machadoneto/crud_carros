package br.unisinos.jeff.crud_carros.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.unisinos.jeff.crud_carros.R;
import br.unisinos.jeff.crud_carros.modelo.Carro;

public class CarroAdapter extends BaseAdapter {


    private List<Carro> carros;
    private Activity activity;

    public CarroAdapter(List<Carro> carros, Activity activity) {
        this.carros = carros;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int position) {
         return carros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Carro carro = carros.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.item, null);

        TextView marca = (TextView) linha.findViewById(R.id.tv_marca);
        marca.setText(carro.getMarca());
        TextView modelo = (TextView) linha.findViewById(R.id.tv_modelo);
        modelo.setText(carro.getModelo());
        TextView placa = (TextView) linha.findViewById(R.id.tv_placa);
        placa.setText(carro.getPlaca());
        TextView ano = (TextView) linha.findViewById(R.id.tv_ano);
        ano.setText(carro.getAno());

        return linha;
    }
}

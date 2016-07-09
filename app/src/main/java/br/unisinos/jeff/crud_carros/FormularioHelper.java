package br.unisinos.jeff.crud_carros;

import android.widget.EditText;
import android.widget.Spinner;

import br.unisinos.jeff.crud_carros.modelo.Carro;

public class FormularioHelper {

	private Spinner campoMarca;
	private EditText campoModelo, campoPlaca, campoAno;
	private Carro carro;

	public FormularioHelper(FormularioActivity activity){

		campoMarca = (Spinner) activity.findViewById(R.id.spi_marca);
		campoModelo = (EditText) activity.findViewById(R.id.modelo);
		campoPlaca = (EditText) activity.findViewById(R.id.placa);
		campoAno = (EditText) activity.findViewById(R.id.ano);

		carro = new Carro();

	}

	public Carro pegaCarroDoFormulario(){
		
        String marca = campoMarca.getSelectedItem().toString();
        String modelo = campoModelo.getText().toString();
        String placa = campoPlaca.getText().toString();
        String ano = campoAno.getText().toString();
 
        carro.setMarca(marca);
		carro.setModelo(modelo);
		carro.setPlaca(placa);
		carro.setAno(ano);

        return carro;
	}

	public void colocaCarroNoFormulario(Carro carroParaSerAlterado) {
		carro = carroParaSerAlterado;

		campoMarca.setSelected(Boolean.parseBoolean(carroParaSerAlterado.getMarca()));
		campoModelo.setText(carroParaSerAlterado.getModelo());
		campoPlaca.setText(carroParaSerAlterado.getPlaca());
		campoAno.setText(carroParaSerAlterado.getAno());

	}
}
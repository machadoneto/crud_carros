package br.unisinos.jeff.crud_carros.modelo;

import java.io.Serializable;

public class Carro implements Serializable {

	private Long id;
	private String marca;
	private String modelo;
	private String placa;
	private String ano;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Carro{" +
				"id=" + id +
				", marca='" + marca + '\'' +
				", modelo='" + modelo + '\'' +
				", placa='" + placa + '\'' +
				", ano=" + ano +
				'}';
	}
}

package br.unisinos.jeff.crud_carros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.unisinos.jeff.crud_carros.modelo.Carro;

public class CarroDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "NomeDoBanco";
	private static final int VERSAO = 1;
	private static final String TABELA = "Carros";

	public CarroDAO(Context ctx) {
	 super(ctx, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + " ("
				+ "id INTEGER PRIMARY KEY, "
				+ "marca TEXT, "
				+ "modelo TEXT, "
				+ "placa TEXT UNIQUE, "
				+ "ano INT"
				+ ");";
		
		database.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		database.execSQL(sql);
		onCreate(database);
	}

	public void insere(Carro carro) {
		ContentValues cv = new ContentValues();
		cv.put("marca", carro.getMarca());
		cv.put("modelo", carro.getModelo());
		cv.put("placa", carro.getPlaca());
		cv.put("ano", carro.getAno());

		
		getWritableDatabase().insert(TABELA, null, cv);
	}

	public List<Carro> getLista() {
		List<Carro> carros = new ArrayList<Carro>();
		
		String sql = "SELECT * from " + TABELA + ";";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		
		while (c.moveToNext()) {
			Carro carro = new Carro();
			carro.setId(c.getLong(c.getColumnIndex("id")));
			carro.setMarca(c.getString(c.getColumnIndex("marca")));
			carro.setModelo(c.getString(c.getColumnIndex("modelo")));
			carro.setPlaca(c.getString(c.getColumnIndex("placa")));
			carro.setAno(c.getString(c.getColumnIndex("ano")));
		
			carros.add(carro);
		}
		
		return carros;
	}

	public List<Carro> getListaAno(String ano) {
		List<Carro> carros = new ArrayList<Carro>();

		String sql = "SELECT * from " + TABELA + " WHERE ano='" + ano + "';";
		Cursor c = getReadableDatabase().rawQuery(sql, null);

		while (c.moveToNext()) {
			Carro carro = new Carro();
			carro.setId(c.getLong(c.getColumnIndex("id")));
			carro.setMarca(c.getString(c.getColumnIndex("marca")));
			carro.setModelo(c.getString(c.getColumnIndex("modelo")));
			carro.setPlaca(c.getString(c.getColumnIndex("placa")));
			carro.setAno(c.getString(c.getColumnIndex("ano")));

			carros.add(carro);
		}

		return carros;
	}

	public List<Carro> getListaPlaca(String placa) {
		List<Carro> carros = new ArrayList<Carro>();

		String sql = "SELECT * from " + TABELA + " WHERE placa='" + placa + "';";
		Cursor c = getReadableDatabase().rawQuery(sql, null);

		while (c.moveToNext()) {
			Carro carro = new Carro();
			carro.setId(c.getLong(c.getColumnIndex("id")));
			carro.setMarca(c.getString(c.getColumnIndex("marca")));
			carro.setModelo(c.getString(c.getColumnIndex("modelo")));
			carro.setPlaca(c.getString(c.getColumnIndex("placa")));
			carro.setAno(c.getString(c.getColumnIndex("ano")));

			carros.add(carro);
		}

		return carros;
	}

	public List<Carro> getListaModelo(String modelo) {
		List<Carro> carros = new ArrayList<Carro>();

		String sql = "SELECT * from " + TABELA + " WHERE modelo='" + modelo + "';";
		Cursor c = getReadableDatabase().rawQuery(sql, null);

		while (c.moveToNext()) {
			Carro carro = new Carro();
			carro.setId(c.getLong(c.getColumnIndex("id")));
			carro.setMarca(c.getString(c.getColumnIndex("marca")));
			carro.setModelo(c.getString(c.getColumnIndex("modelo")));
			carro.setPlaca(c.getString(c.getColumnIndex("placa")));
			carro.setAno(c.getString(c.getColumnIndex("ano")));

			carros.add(carro);
		}

		return carros;
	}

	public List<Carro> getListaMarca(String marca) {
		List<Carro> carros = new ArrayList<Carro>();

		String sql = "SELECT * from " + TABELA + " WHERE marca='" + marca + "';";
		Cursor c = getReadableDatabase().rawQuery(sql, null);

		while (c.moveToNext()) {
			Carro carro = new Carro();
			carro.setId(c.getLong(c.getColumnIndex("id")));
			carro.setMarca(c.getString(c.getColumnIndex("marca")));
			carro.setModelo(c.getString(c.getColumnIndex("modelo")));
			carro.setPlaca(c.getString(c.getColumnIndex("placa")));
			carro.setAno(c.getString(c.getColumnIndex("ano")));

			carros.add(carro);
		}

		return carros;
	}

	public void deletar(Carro carro) {
		String[] args = {carro.getId().toString()};
		getWritableDatabase().delete("Carros", "id=?", args);
		
	}

	public void atualizar(Carro carro) {
		ContentValues cv = new ContentValues();
		cv.put("marca", carro.getMarca());
		cv.put("modelo", carro.getModelo());
		cv.put("placa", carro.getPlaca());
		cv.put("ano", carro.getAno());
		
		String[] args = {carro.getId().toString()};
		getWritableDatabase().update("Carros", cv, "id=?", args);
	}
}

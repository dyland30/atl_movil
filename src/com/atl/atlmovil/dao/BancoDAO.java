package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.Banco;

public class BancoDAO {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	private String[] allColumns = { "codigoBanco","nombreBanco"};

	public BancoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Banco> obtenerTodos(){
		 List<Banco> ls = new ArrayList<Banco>();
		 Cursor cursor = database.query(Banco.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Banco ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Banco ent){
		 long id = ent.getCodigoBanco();
		 database.delete(Banco.class.getSimpleName(),"codigoBanco = "+id,null);
		 
	 }
	 
	 public Banco crear(int codigoBanco, String nombreBanco){
		
		 Banco ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoBanco", codigoBanco);
		 values.put("nombreBanco", nombreBanco);
		 
		 long insertId = database.insert(Banco.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Banco actualizar(Banco ent){
		 Banco nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoBanco", ent.getCodigoBanco());
		 values.put("nombreBanco", ent.getNombreBanco());
		 
		 database.update(Banco.class.getSimpleName(), values, " codigoBanco = "+ent.getCodigoBanco(), null);
		 nuevo=buscarPorID(ent.getCodigoBanco());
		 
		 return nuevo;
	 }
	 public Banco buscarPorID(long id){
		 Banco ent = null;
		 Cursor cursor = database.query(Banco.class.getSimpleName(), allColumns, " codigoBanco = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			
		 }
		 cursor.close();	 
		 return ent;
	 }
	 
	 private Banco cursorToEnt(Cursor cursor) {
		    Banco ent = null;
		    if(cursor!=null ){
		    	ent = new Banco();
		    	ent.setCodigoBanco(cursor.getInt(0));
		    	ent.setNombreBanco(cursor.getString(1));
		    }
		    
		    return ent;
	 }
}

package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TipoVisitaDAO {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoTipoVisita","descripcionTipoVisita"};

	public TipoVisitaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<TipoVisita> obtenerTodos(){
		 List<TipoVisita> ls = new ArrayList<TipoVisita>();
		 Cursor cursor = database.query(TipoVisita.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 TipoVisita tv = cursorToEnt(cursor);
			 ls.add(tv);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(TipoVisita tv){
		 long id = tv.getCodigoTipoVisita();
		 database.delete(TipoVisita.class.getSimpleName(),"codigoTipoVisita = "+id,null);
		 
	 }
	 
	 public TipoVisita crear(long codigoTipoVisita, String descripcionTipoVisita){
		 TipoVisita us = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoTipoVisita", codigoTipoVisita);
		 values.put("descripcionTipoVisita", descripcionTipoVisita);
		 long insertId = database.insert(TipoVisita.class.getSimpleName(), null, values);
		 
		 us = buscarPorID(insertId);
		
		 return us;
	 }
	 
	 public TipoVisita actualizar(TipoVisita tv){
		 TipoVisita nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("descripcionTipoVisita", tv.getCodigoTipoVisita());
		 
		 database.update(TipoVisita.class.getSimpleName(), values, " codigoTipoVisita = "+tv.getCodigoTipoVisita(), null);
		 
		 nuevo=buscarPorID(tv.getCodigoTipoVisita());
		 
		 return nuevo;
	 }
	 public TipoVisita buscarPorID(long id){
		 TipoVisita ent = null;
		 Cursor cursor = database.query(TipoVisita.class.getSimpleName(), allColumns, " codigoTipoVisita = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private TipoVisita cursorToEnt(Cursor cursor) {
		    TipoVisita ent = null;
		    if(cursor!=null ){
		    	ent = new TipoVisita();
		    	ent.setCodigoTipoVisita(cursor.getLong(0));
			    ent.setDescripcionTipoVisita(cursor.getString(1)); 	
		    }
		    
		    return ent;
	 }

}

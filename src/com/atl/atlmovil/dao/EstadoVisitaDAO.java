package com.atl.atlmovil.dao;


import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class EstadoVisitaDAO {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoEstadoVisita","descripcionEstadoVisita"};

	public EstadoVisitaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<EstadoVisita> obtenerTodos(){
		 List<EstadoVisita> ls = new ArrayList<EstadoVisita>();
		 Cursor cursor = database.query(EstadoVisita.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 EstadoVisita tv = cursorToEnt(cursor);
			 ls.add(tv);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(EstadoVisita tv){
		 long id = tv.getCodigoEstadoVisita();
		 database.delete(EstadoVisita.class.getSimpleName(),"codigoEstadoVisita = "+id,null);
		 
	 }
	 
	 public EstadoVisita crear(long codigoEstadoVisita, String descripcionEstadoVisita){
		 EstadoVisita us = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoEstadoVisita", codigoEstadoVisita);
		 values.put("descripcionEstadoVisita", descripcionEstadoVisita);
		 long insertId = database.insert(EstadoVisita.class.getSimpleName(), null, values);
		 
		 us = buscarPorID(insertId);
		
		 return us;
	 }
	 
	 public EstadoVisita actualizar(EstadoVisita tv){
		 EstadoVisita nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("descripcionEstadoVisita", tv.getCodigoEstadoVisita());
		 
		 database.update(EstadoVisita.class.getSimpleName(), values, " codigoEstadoVisita = "+tv.getCodigoEstadoVisita(), null);
		 
		 nuevo=buscarPorID(tv.getCodigoEstadoVisita());
		 
		 return nuevo;
	 }
	 public EstadoVisita buscarPorID(long id){
		 EstadoVisita ent = null;
		 Cursor cursor = database.query(EstadoVisita.class.getSimpleName(), allColumns, " codigoEstadoVisita = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private EstadoVisita cursorToEnt(Cursor cursor) {
		    EstadoVisita ent = null;
		    if(cursor!=null ){
		    	ent = new EstadoVisita();
		    	ent.setCodigoEstadoVisita(cursor.getLong(0));
			    ent.setDescripcionEstadoVisita(cursor.getString(1)); 	
		    }
		    
		    return ent;
	 }


}

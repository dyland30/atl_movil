package com.atl.atlmovil.dao;
import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class GrupoDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoGrupo","nombreGrupo", "titularGrupo"};

	public GrupoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Grupo> obtenerTodos(){
		 List<Grupo> ls = new ArrayList<Grupo>();
		 Cursor cursor = database.query(Grupo.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Grupo tv = cursorToEnt(cursor);
			 ls.add(tv);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Grupo tv){
		 long id = tv.getCodigoGrupo();
		 database.delete(Grupo.class.getSimpleName(),"codigoGrupo = "+id,null);
		 
	 }
	 
	 public Grupo crear(long codigoGrupo, String nombreGrupo, String titularGrupo){
		 Grupo us = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoGrupo", codigoGrupo);
		 values.put("nombreGrupo", nombreGrupo);
		 values.put("titularGrupo", titularGrupo);
		 long insertId = database.insert(Grupo.class.getSimpleName(), null, values);
		 
		 us = buscarPorID(insertId);
		
		 return us;
	 }
	 
	 public Grupo actualizar(Grupo ent){
		 Grupo nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("nombreGrupo", ent.getNombreGrupo());
		 values.put("titularGrupo", ent.getTitularGrupo());
		 
		 database.update(Grupo.class.getSimpleName(), values, " codigoGrupo = "+ent.getCodigoGrupo(), null);
		 
		 nuevo=buscarPorID(ent.getCodigoGrupo());
		 
		 return nuevo;
	 }
	 public Grupo buscarPorID(long id){
		 Grupo ent = null;
		 Cursor cursor = database.query(Grupo.class.getSimpleName(), allColumns, " codigoGrupo = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Grupo cursorToEnt(Cursor cursor) {
		    Grupo ent = null;
		    if(cursor!=null ){
		    	ent = new Grupo();
		    	ent.setCodigoGrupo(cursor.getLong(0));
			    ent.setNombreGrupo(cursor.getString(1));
			    ent.setTitularGrupo(cursor.getString(2));
			    
		    }
		    
		    return ent;
	 }

}

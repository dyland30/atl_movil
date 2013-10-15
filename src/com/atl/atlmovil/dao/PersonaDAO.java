package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class PersonaDAO {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoPersona","codigoTipoDocumento","nombrePersona","direccionPersona","documentoPersona", "tipoPersona"};

	public PersonaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Persona> obtenerTodos(){
		 List<Persona> ls = new ArrayList<Persona>();
		 Cursor cursor = database.query(Persona.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Persona ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Persona tv){
		 long id = tv.getCodigoPersona();
		 database.delete(Persona.class.getSimpleName(),"codigoPersona = "+id,null);
		 
	 }
	 
	 public Persona crear(long codigoPersona, long  codigoTipoDocumento, String nombrePersona, 
			 String direccionPersona, String documentoPersona, String tipoPersona){
		 Persona ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoPersona", codigoPersona);
		 values.put("codigoTipoDocumento", codigoTipoDocumento);
		 values.put("nombrePersona", nombrePersona);
		 values.put("direccionPersona", direccionPersona);
		 values.put("documentoPersona", documentoPersona);
		 values.put("tipoPersona", tipoPersona);
		 
		 long insertId = database.insert(Persona.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Persona actualizar(Persona ent){
		 Persona nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoTipoDocumento", ent.getCodigoTipoDocumento());
		 values.put("nombrePersona", ent.getNombrePersona());
		 values.put("direccionPersona", ent.getDireccionPersona());
		 values.put("documentoPersona", ent.getDocumentoPersona());
		 values.put("tipoPersona", ent.getTipoPersona());
		 
		 database.update(Persona.class.getSimpleName(), values, " codigoPersona = "+ent.getCodigoPersona(), null);
		 
		 nuevo=buscarPorID(ent.getCodigoPersona());
		 
		 return nuevo;
	 }
	 public Persona buscarPorID(long id){
		 Persona ent = null;
		 Cursor cursor = database.query(Persona.class.getSimpleName(), allColumns, " codigoPersona = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Persona cursorToEnt(Cursor cursor) {
		    Persona ent = null;
		    if(cursor!=null ){
		    	ent = new Persona();
		    	ent.setCodigoPersona(cursor.getLong(0));
		    	ent.setCodigoTipoDocumento(cursor.getLong(1));
			    ent.setNombrePersona(cursor.getString(2));
			    ent.setDireccionPersona(cursor.getString(3));
			    ent.setDocumentoPersona(cursor.getString(4));
			    ent.setTipoPersona(cursor.getString(5));
		    }
		    
		    return ent;
	 }

}

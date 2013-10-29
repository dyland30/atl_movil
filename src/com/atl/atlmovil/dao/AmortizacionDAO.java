package com.atl.atlmovil.dao;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.Amortizacion;

public class AmortizacionDAO {
	

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {"id","idCobranza","codigoDocumentoPago","importeAmortizacion","anotacionAmortizacion"};

	public AmortizacionDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Amortizacion> obtenerTodos(){
		 List<Amortizacion> ls = new ArrayList<Amortizacion>();
		 Cursor cursor = database.query(Amortizacion.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			// Amortizacion ent = cursorToEnt(cursor);
			// ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Amortizacion ent){
		 long id = ent.getId();
		 database.delete(Amortizacion.class.getSimpleName(),"id = "+id,null);
		 
	 }
	
	 public Amortizacion crear(long id,long idCobranza,long codigoDocumentoPago,double importeAmortizacion,String anotacionAmortizacion){
		
		 Amortizacion ent = null;
		 ContentValues values = new ContentValues();
		 values.put("id", id);
		 values.put("idCobranza", idCobranza);
		 values.put("codigoDocumentoPago", codigoDocumentoPago);
		 values.put("importeAmortizacion", importeAmortizacion);
		 values.put("anotacionAmortizacion", anotacionAmortizacion);
		 
		 long insertId = database.insert(Amortizacion.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Amortizacion actualizar(Amortizacion ent){
		 
		 Amortizacion nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("idCobranza", ent.getIdCobranza());
		 values.put("codigoDocumentoPago", ent.getCodigoDocumentoPago());
		 values.put("importeAmortizacion", ent.getImporteAmortizacion());
		 values.put("anotacionAmortizacion", ent.getAnotacionAmortizacion());
		 
		 database.update(Amortizacion.class.getSimpleName(), values, " id = "+ent.getId(), null);
		 nuevo=buscarPorID(ent.getId());
		 
		 return nuevo;
	 }
	 public Amortizacion buscarPorID(long id){
		 Amortizacion ent = null;
		 Cursor cursor = database.query(Amortizacion.class.getSimpleName(), allColumns, " id = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Amortizacion cursorToEnt(Cursor cursor) {
		
		    Amortizacion ent = null;
		    if(cursor!=null ){
		    	ent = new Amortizacion();
		    	ent.setId(cursor.getLong(0));
		    	ent.setIdCobranza(cursor.getLong(1));
		    	ent.setCodigoDocumentoPago(cursor.getInt(2));
		    	ent.setImporteAmortizacion(cursor.getDouble(3));
		    	ent.setAnotacionAmortizacion(cursor.getString(4));
		    	
		    	
		    }
		    
		    return ent;
	 }

}

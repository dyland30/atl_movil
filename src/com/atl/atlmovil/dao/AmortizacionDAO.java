package com.atl.atlmovil.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.Amortizacion;

public class AmortizacionDAO {
	/*
	 *  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			" INTEGER,  INTEGER,  NUMERIC,  TEXT
	 * 
	 * */

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
	 /*
	 public Amortizacion crear(){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Amortizacion ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoAmortizacion", codigoAmortizacion);
		 values.put("codigoMedioPago", codigoMedioPago);
		 values.put("importeAmortizacion", importeAmortizacion);
		 values.put("fechaAmortizacion", dateFormat.format(fechaAmortizacion));
		 
		 
		 long insertId = database.insert(Amortizacion.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Amortizacion actualizar(Amortizacion ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Amortizacion nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoAmortizacion", ent.getCodigoAmortizacion());
		 values.put("codigoMedioPago",  ent.getCodigoMedioPago());
		 values.put("importeAmortizacion", ent.getImporteAmortizacion());
		 values.put("fechaAmortizacion", dateFormat.format( ent.getFechaAmortizacion()));
		 
		 database.update(Amortizacion.class.getSimpleName(), values, " id = "+ent.getId(), null);
		 nuevo=buscarPorID(ent.getCodigoAmortizacion());
		 
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
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Amortizacion ent = null;
		    if(cursor!=null ){
		    	ent = new Amortizacion();
		    	ent.setId(cursor.getLong(0));
		    	ent.setCodigoAmortizacion(cursor.getLong(1));
		    	ent.setCodigoMedioPago(cursor.getInt(2));
		    	ent.setImporteAmortizacion(cursor.getDouble(3));
		    	
		    	try {
					ent.setFechaAmortizacion(dateFormat.parse(cursor.getString(4)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaAmortizacion(new Date(1900,1,1));
					
				}
		    	
		    }
		    
		    return ent;
	 }
*/
}

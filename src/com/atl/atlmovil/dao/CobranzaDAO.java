package com.atl.atlmovil.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.Cobranza;

@SuppressLint("SimpleDateFormat")
public class CobranzaDAO {
   
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "id","codigoCobranza","codigoMedioPago","importeCobranza", "fechaCobranza","estaSincronizado", "estadoCobranza", "codigoVisita"};

	public CobranzaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Cobranza> obtenerTodos(){
		 List<Cobranza> ls = new ArrayList<Cobranza>();
		 Cursor cursor = database.query(Cobranza.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Cobranza ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<Cobranza> buscarPorVisita(long codigoVisita){
		 List<Cobranza> ls = new ArrayList<Cobranza>();
		 Cursor cursor = database.query(Cobranza.class.getSimpleName(), allColumns, " codigoVisita = '"+codigoVisita+"'",null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Cobranza ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Cobranza ent){
		 long id = ent.getId();
		 database.delete(Cobranza.class.getSimpleName(),"id = "+id,null);
		 
	 }
	 
	 public Cobranza crear(long codigoCobranza, long codigoMedioPago, double importeCobranza, Date fechaCobranza, Boolean estaSincronizado,
			 String estadoCobranza, long codigoVisita){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Cobranza ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoCobranza", codigoCobranza);
		 values.put("codigoMedioPago", codigoMedioPago);
		 values.put("importeCobranza", importeCobranza);
		 values.put("fechaCobranza", dateFormat.format(fechaCobranza));
		 values.put("estaSincronizado", estaSincronizado);
		 values.put("estadoCobranza", estadoCobranza);
		 values.put("codigoVisita", codigoVisita);
		 
		 long insertId = database.insert(Cobranza.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 
	 public Cobranza crear(Cobranza ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Cobranza nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoCobranza", ent.getCodigoCobranza());
		 values.put("codigoMedioPago",  ent.getCodigoMedioPago());
		 values.put("importeCobranza", ent.getImporteCobranza());
		 values.put("fechaCobranza", dateFormat.format( ent.getFechaCObranza()));
		 values.put("estaSincronizado", ent.getEstaSincronizado());
		 values.put("estadoCobranza", ent.getEstadoCobranza());
		 values.put("codigoVisita", ent.getCodigoVisita());
		 
		 long insertId = database.insert(Cobranza.class.getSimpleName(), null, values);
		 nuevo=buscarPorID(insertId);
		 
		 return nuevo;
	 }
	 
	 
	 public Cobranza actualizar(Cobranza ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Cobranza nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoCobranza", ent.getCodigoCobranza());
		 values.put("codigoMedioPago",  ent.getCodigoMedioPago());
		 values.put("importeCobranza", ent.getImporteCobranza());
		 values.put("fechaCobranza", dateFormat.format( ent.getFechaCObranza()));
		 values.put("estaSincronizado", ent.getEstaSincronizado());
		 values.put("estadoCobranza", ent.getEstadoCobranza());
		 values.put("codigoVisita", ent.getCodigoVisita());
		 database.update(Cobranza.class.getSimpleName(), values, " id = "+ent.getId(), null);
		 nuevo=buscarPorID(ent.getId());
		 
		 return nuevo;
	 }
	 public Cobranza buscarPorID(long id){
		 Cobranza ent = null;
		 Cursor cursor = database.query(Cobranza.class.getSimpleName(), allColumns, " id = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Cobranza cursorToEnt(Cursor cursor) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Cobranza ent = null;
		    if(cursor!=null ){
		    	ent = new Cobranza();
		    	ent.setId(cursor.getLong(0));
		    	ent.setCodigoCobranza(cursor.getLong(1));
		    	ent.setCodigoMedioPago(cursor.getInt(2));
		    	ent.setImporteCobranza(cursor.getDouble(3));
		    	
		    	try {
					ent.setFechaCObranza(dateFormat.parse(cursor.getString(4)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaCObranza(new Date(1900,1,1));
					
				}
		    	
		    	Boolean estaSincronizado = cursor.getInt(5)==1 ?true : false;
		    	ent.setEstaSincronizado(estaSincronizado);
		    	ent.setEstadoCobranza(cursor.getString(6));
		    	ent.setCodigoVisita(cursor.getLong(7));
		    	
		    }
		    
		    return ent;
	 }

}

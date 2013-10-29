package com.atl.atlmovil.dao;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.MedioPago;

public class MedioPagoDAO {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	private String[] allColumns = { "codigoMedioPago","descripcionMedioPago"};

	public MedioPagoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<MedioPago> obtenerTodos(){
		 List<MedioPago> ls = new ArrayList<MedioPago>();
		 Cursor cursor = database.query(MedioPago.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 MedioPago ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(MedioPago ent){
		 long id = ent.getCodigoMedioPago();
		 database.delete(MedioPago.class.getSimpleName(),"codigoMedioPago = "+id,null);
		 
	 }
	 
	 public MedioPago crear(long codigoMedioPago, String descripcionMedioPago){
		
		 MedioPago ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoMedioPago", codigoMedioPago);
		 values.put("descripcionMedioPago", descripcionMedioPago);
		 
		 long insertId = database.insert(MedioPago.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public MedioPago actualizar(MedioPago ent){
		 MedioPago nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoMedioPago", ent.getCodigoMedioPago());
		 values.put("descripcionMedioPago", ent.getDescripcionMedioPago());
		 
		 database.update(MedioPago.class.getSimpleName(), values, " codigoMedioPago = "+ent.getCodigoMedioPago(), null);
		 nuevo=buscarPorID(ent.getCodigoMedioPago());
		 
		 return nuevo;
	 }
	 public MedioPago buscarPorID(long id){
		 MedioPago ent = null;
		 Cursor cursor = database.query(MedioPago.class.getSimpleName(), allColumns, " codigoMedioPago = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private MedioPago cursorToEnt(Cursor cursor) {
		    MedioPago ent = null;
		    if(cursor!=null ){
		    	ent = new MedioPago();
		    	ent.setCodigoMedioPago(cursor.getLong(0));
		    	ent.setDescripcionMedioPago(cursor.getString(1));
		    }
		    
		    return ent;
	 }
	

}

package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FormaPagoDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoFormaPago","descripcionFormaPago"};

	public FormaPagoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<FormaPago> obtenerTodos(){
		 List<FormaPago> ls = new ArrayList<FormaPago>();
		 Cursor cursor = database.query(FormaPago.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 FormaPago ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(FormaPago ent){
		 long id = ent.getCodigoFormaPago();
		 database.delete(FormaPago.class.getSimpleName(),"codigoFormaPago = "+id,null);
		 
	 }
	 
	 public FormaPago crear(long codigoFormaPago, String descripcionFormaPago){
		 FormaPago ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoFormaPago", codigoFormaPago);
		 values.put("descripcionFormaPago", descripcionFormaPago);
		 
		 long insertId = database.insert(FormaPago.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public FormaPago actualizar(FormaPago ent){
		 FormaPago nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoFormaPago", ent.getCodigoFormaPago());
		 values.put("descripcionFormaPago", ent.getDescripcionFormaPago());
		 database.update(FormaPago.class.getSimpleName(), values, " codigoFormaPago = "+ent.getCodigoFormaPago(), null);
		 nuevo=buscarPorID(ent.getCodigoFormaPago());
		 
		 return nuevo;
	 }
	 public FormaPago buscarPorID(long id){
		 FormaPago ent = null;
		 Cursor cursor = database.query(FormaPago.class.getSimpleName(), allColumns, " codigoFormaPago = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			 		 
		 }
		 cursor.close();
		 return ent;
	 }
	 
	 private FormaPago cursorToEnt(Cursor cursor) {
		    FormaPago ent = null;
		    if(cursor!=null ){
		    	ent = new FormaPago();
		    	ent.setCodigoFormaPago(cursor.getInt(0));
		    	ent.setDescripcionFormaPago(cursor.getString(1));
		    	
		    }
		    
		    return ent;
	 }
	
}

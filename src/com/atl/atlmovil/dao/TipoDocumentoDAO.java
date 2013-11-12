package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.TipoDocumento;

public class TipoDocumentoDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoTipoDocumento", "nombreTipoDocumento ","siglaTipoDocumento" };

	public TipoDocumentoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<TipoDocumento> obtenerTodos(){
		 List<TipoDocumento> ls = new ArrayList<TipoDocumento>();
		 Cursor cursor = database.query(TipoDocumento.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 TipoDocumento tv = cursorToEnt(cursor);
			 ls.add(tv);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(TipoDocumento tv){
		 long id = tv.getCodigoTipoDocumento();
		 database.delete(TipoDocumento.class.getSimpleName(),"codigoTipoDocumento = "+id,null);
		 
	 }
	 
	 public TipoDocumento crear(long codigoTipoDocumento, String nombreTipoDocumento, String siglaTipoDocumento){
		 TipoDocumento us = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoTipoDocumento", codigoTipoDocumento);
		 values.put("nombreTipoDocumento", nombreTipoDocumento);
		 values.put("siglaTipoDocumento", siglaTipoDocumento);
		 long insertId = database.insert(TipoDocumento.class.getSimpleName(), null, values);
		 
		 us = buscarPorID(insertId);
		
		 return us;
	 }
	 
	 public TipoDocumento actualizar(TipoDocumento ent){
		 TipoDocumento nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("nombreTipoDocumento", ent.getNombreTipoDocumento());
		 values.put("siglaTipoDocumento", ent.getSiglaTipoDocumento());
		 
		 database.update(TipoDocumento.class.getSimpleName(), values, " codigoTipoDocumento = "+ent.getCodigoTipoDocumento(), null);
		 
		 nuevo=buscarPorID(ent.getCodigoTipoDocumento());
		 
		 return nuevo;
	 }
	 public TipoDocumento buscarPorID(long id){
		 TipoDocumento ent = null;
		 Cursor cursor = database.query(TipoDocumento.class.getSimpleName(), allColumns, " codigoTipoDocumento = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			 		 
		 }
		 cursor.close();
		 return ent;
	 }
	 
	 private TipoDocumento cursorToEnt(Cursor cursor) {
		    TipoDocumento ent = null;
		    if(cursor!=null ){
		    	ent = new TipoDocumento();
		    	ent.setCodigoTipoDocumento(cursor.getLong(0));
			    ent.setNombreTipoDocumento(cursor.getString(1));
			    ent.setSiglaTipoDocumento(cursor.getString(2));
			    
		    }
		    
		    return ent;
	 }
}

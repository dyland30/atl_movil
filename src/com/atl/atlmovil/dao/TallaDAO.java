package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class TallaDAO {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoProducto","numeroTalla","stockDisponibleTalla"};

	public TallaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Talla> obtenerTodos(){
		 List<Talla> ls = new ArrayList<Talla>();
		 Cursor cursor = database.query(Talla.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Talla ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Talla ent){
		 long id = ent.getCodigoProducto();
		 int idTalla = ent.getNumeroTalla();
		 database.delete(Talla.class.getSimpleName(),"numeroTalla = "+idTalla+" and codigoProducto = "+id,null);
		 
	 }
	 
	 public Talla crear(long codigoProducto, int numeroTalla, int stockDisponibleTalla){
		 Talla ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoProducto", codigoProducto);
		 values.put("numeroTalla", numeroTalla);
		 values.put("stockDisponibleTalla", stockDisponibleTalla);
		 
		 
		 database.insert(Talla.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(codigoProducto,numeroTalla);
		
		 return ent;
	 }
	 
	 public Talla actualizar(Talla ent){
		 Talla nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoProducto", ent.getCodigoProducto());
		 values.put("numeroTalla", ent.getNumeroTalla());
		 values.put("stockDisponibleTalla", ent.getStockDisponibleTalla());
		 
		 
		 database.update(Talla.class.getSimpleName(), values, "numeroTalla = "+ent.getNumeroTalla()+" and codigoProducto = "+ent.getCodigoProducto(), null);
		 nuevo=buscarPorID(ent.getCodigoProducto(), ent.getNumeroTalla());
		 
		 return nuevo;
	 }
	 public Talla buscarPorID(long codigoProducto, int numeroTalla){
		 Talla ent = null;
		 Cursor cursor = database.query(Talla.class.getSimpleName(), allColumns, "numeroTalla = "+numeroTalla+" and codigoProducto = "+codigoProducto,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Talla cursorToEnt(Cursor cursor) {
		    Talla ent = null;
		    if(cursor!=null ){
		    	ent = new Talla();
		    	ent.setCodigoProducto(cursor.getInt(0));
		    	ent.setNumeroTalla(cursor.getInt(1));
		    	ent.setStockDisponibleTalla(cursor.getInt(2));
		    }
		    
		    return ent;
	 }

}

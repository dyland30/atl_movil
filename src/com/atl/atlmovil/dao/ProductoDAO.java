package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ProductoDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoProducto","calidadProducto","colorProducto","descripcionProducto","materialProducto",
			"precioProducto","sexoProducto"};

	public ProductoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Producto> obtenerTodos(){
		 List<Producto> ls = new ArrayList<Producto>();
		 Cursor cursor = database.query(Producto.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Producto ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Producto ent){
		 long id = ent.getCodigoProducto();
		 database.delete(Producto.class.getSimpleName(),"codigoProducto = "+id,null);
		 
	 }
	 
	 public Producto crear(long codigoProducto, String calidadProducto, String colorProducto,String descripcionProducto,
			 String materialProducto, Double precioProducto, String sexoProducto){
		 Producto ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoProducto", codigoProducto);
		 values.put("calidadProducto", calidadProducto);
		 values.put("colorProducto", colorProducto);
		 values.put("descripcionProducto", descripcionProducto);
		 values.put("materialProducto", materialProducto);
		 values.put("precioProducto", precioProducto);
		 values.put("sexoProducto", sexoProducto);
		 
		 
		 long insertId = database.insert(Producto.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Producto actualizar(Producto ent){
		 Producto nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("calidadProducto", ent.getCalidadProducto());
		 values.put("colorProducto", ent.getColorProducto());
		 values.put("descripcionProducto", ent.getDescripcionProducto());
		 values.put("materialProducto", ent.getMaterialProducto());
		 values.put("precioProducto", ent.getPrecioProducto());
		 values.put("sexoProducto", ent.getSexoProducto());
		 
		 database.update(Producto.class.getSimpleName(), values, " codigoProducto = "+ent.getCodigoProducto(), null);
		 nuevo=buscarPorID(ent.getCodigoProducto());
		 
		 return nuevo;
	 }
	 public Producto buscarPorID(long id){
		 Producto ent = null;
		 Cursor cursor = database.query(Producto.class.getSimpleName(), allColumns, " codigoProducto = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Producto cursorToEnt(Cursor cursor) {
		    Producto ent = null;
		    if(cursor!=null ){
		    	ent = new Producto();
		    	ent.setCodigoProducto(cursor.getInt(0));
		    	ent.setCalidadProducto(cursor.getString(1));
		    	ent.setColorProducto(cursor.getString(2));
		    	ent.setDescripcionProducto(cursor.getString(3));
		    	ent.setMaterialProducto(cursor.getString(4));
		    	ent.setSexoProducto(cursor.getString(5));
		    	
		    }
		    
		    return ent;
	 }
}

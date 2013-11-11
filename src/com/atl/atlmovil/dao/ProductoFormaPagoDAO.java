package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class ProductoFormaPagoDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoFormaPago","codigoProducto","precio"};

	public ProductoFormaPagoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<ProductoFormaPago> obtenerTodos(){
		 List<ProductoFormaPago> ls = new ArrayList<ProductoFormaPago>();
		 Cursor cursor = database.query(ProductoFormaPago.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 ProductoFormaPago ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(ProductoFormaPago ent){
		 long id = ent.getCodigoFormaPago();
		 long codProducto = ent.getCodigoProducto();
		 database.delete(ProductoFormaPago.class.getSimpleName(),"codigoFormaPago = "+id+" and codigoProducto ="+codProducto,null);
		 
	 }
	 
	 public ProductoFormaPago crear(long codigoFormaPago, long codigoProducto, Double precio){
		 ProductoFormaPago ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoFormaPago", codigoFormaPago);
		 values.put("codigoProducto", codigoProducto);
		 values.put("precio", precio);
		 
		 
		 database.insert(ProductoFormaPago.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(codigoFormaPago, codigoProducto);
		
		 return ent;
	 }
	 
	 public ProductoFormaPago actualizar(ProductoFormaPago ent){
		 ProductoFormaPago nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("precio", ent.getPrecio());
		 database.update(ProductoFormaPago.class.getSimpleName(), values, " codigoFormaPago = "+ent.getCodigoFormaPago()+" and codigoProducto ="+ent.getCodigoProducto(), null);
		 nuevo=buscarPorID(ent.getCodigoFormaPago(), ent.getCodigoProducto());
		 
		 return nuevo;
	 }
	 public ProductoFormaPago buscarPorID(long codigoFormaPago, long codProducto){
		 ProductoFormaPago ent = null;
		 Cursor cursor = database.query(ProductoFormaPago.class.getSimpleName(), allColumns, " codigoFormaPago = "+codigoFormaPago+" and codigoProducto ="+codProducto,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			 		 
		 }
		 cursor.close();
		 return ent;
	 }
	 
	 private ProductoFormaPago cursorToEnt(Cursor cursor) {
		    ProductoFormaPago ent = null;
		    if(cursor!=null ){
		    	ent = new ProductoFormaPago();
		    	ent.setCodigoFormaPago(cursor.getInt(0));
		    	ent.setCodigoProducto(cursor.getInt(1));
		    	ent.setPrecio(cursor.getDouble(2));
		    	
		    }
		    
		    return ent;
	 }
}

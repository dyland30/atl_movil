package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DetallePedidoDAO {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "idPedido","codigoProducto","precioUnitario"};

	public DetallePedidoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<DetallePedido> obtenerTodos(){
		 List<DetallePedido> ls = new ArrayList<DetallePedido>();
		 Cursor cursor = database.query(DetallePedido.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 DetallePedido ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<DetallePedido> buscarPorPedido(long idPedido){
		 List<DetallePedido> ls = new ArrayList<DetallePedido>();
		 Cursor cursor = database.query(DetallePedido.class.getSimpleName(), allColumns, "idPedido = "+idPedido,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 DetallePedido ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(DetallePedido ent){
		 long id = ent.getIdPedido();
		 long codProducto = ent.getCodigoProducto();
		 database.delete(DetallePedido.class.getSimpleName(),"idPedido = "+id+" and codigoProducto ="+codProducto,null);
		 //eliminar tallaPedido
		 database.delete(TallaPedido.class.getSimpleName(), "idPedido ="+id +" and codigoProducto = "+codProducto, null);
		 
	 }
	 
	 public DetallePedido crear(long idPedido, long codigoProducto, Double precioUnitario){
		 DetallePedido ent = null;
		 ContentValues values = new ContentValues();
		 values.put("idPedido", idPedido);
		 values.put("codigoProducto", codigoProducto);
		 values.put("precioUnitario", precioUnitario);
		 
		 
		 database.insert(DetallePedido.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(idPedido, codigoProducto);
		
		 return ent;
	 }
	 
	 public DetallePedido actualizar(DetallePedido ent){
		 DetallePedido nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("precioUnitario", ent.getPrecioUnitario());
		 database.update(DetallePedido.class.getSimpleName(), values, " idPedido = "+ent.getIdPedido()+" and codigoProducto ="+ent.getCodigoProducto(), null);
		 nuevo=buscarPorID(ent.getIdPedido(), ent.getCodigoProducto());
		 
		 return nuevo;
	 }
	 public DetallePedido buscarPorID(long idPedido, long codProducto){
		 DetallePedido ent = null;
		 Cursor cursor = database.query(DetallePedido.class.getSimpleName(), allColumns, " idPedido = "+idPedido+" and codigoProducto ="+codProducto,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 
	 
	 private DetallePedido cursorToEnt(Cursor cursor) {
		    DetallePedido ent = null;
		    if(cursor!=null && cursor.getCount()>0){
		    	ent = new DetallePedido();
		    	ent.setIdPedido(cursor.getInt(0));
		    	ent.setCodigoProducto(cursor.getInt(1));
		    	ent.setPrecioUnitario(cursor.getDouble(2));
		    	
		    }
		    
		    return ent;
	 }
}

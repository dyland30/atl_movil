package com.atl.atlmovil.dao;


import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class TallaPedidoDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "idPedido","codigoProducto","numeroTalla","cantidad"};

	public TallaPedidoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<TallaPedido> obtenerTodos(){
		 List<TallaPedido> ls = new ArrayList<TallaPedido>();
		 Cursor cursor = database.query(TallaPedido.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 TallaPedido ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(TallaPedido ent){
		 long id = ent.getIdPedido();
		 long codProducto = ent.getCodigoProducto();
		 long nroTalla = ent.getNumeroTalla();
		 database.delete(TallaPedido.class.getSimpleName(),"idPedido = "+id+" and codigoProducto ="+codProducto+ " and numeroTalla = "+nroTalla,null);
		 
	 }
	 
	 public TallaPedido crear(long idPedido, long codigoProducto, long numeroTalla, int cantidad){
		 TallaPedido ent = null;
		 ContentValues values = new ContentValues();
		 values.put("idPedido", idPedido);
		 values.put("codigoProducto", codigoProducto);
		 values.put("numeroTalla", numeroTalla);
		 
		 values.put("cantidad", cantidad);
		 
		 
		 database.insert(TallaPedido.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(idPedido, codigoProducto, numeroTalla);
		
		 return ent;
	 }
	 
	 public TallaPedido actualizar(TallaPedido ent){
		 TallaPedido nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("cantidad", ent.getCantidad());
		 database.update(TallaPedido.class.getSimpleName(), values, "idPedido = "+ent.getIdPedido()+" and codigoProducto ="+ent.getCodigoProducto()+ " and numeroTalla = "+ent.getNumeroTalla(), null);
		 nuevo=buscarPorID(ent.getIdPedido(), ent.getCodigoProducto(), ent.getNumeroTalla());
		 
		 return nuevo;
	 }
	 public TallaPedido buscarPorID(long idPedido, long codProducto, long numeroTalla){
		 TallaPedido ent = null;
		 Cursor cursor = database.query(TallaPedido.class.getSimpleName(), allColumns, " idPedido = "+idPedido+" and codigoProducto ="+codProducto+ " and numeroTalla = "+numeroTalla,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private TallaPedido cursorToEnt(Cursor cursor) {
		    TallaPedido ent = null;
		    if(cursor!=null ){
		    	ent = new TallaPedido();
		    	ent.setIdPedido(cursor.getInt(0));
		    	ent.setCodigoProducto(cursor.getInt(1));
		    	ent.setNumeroTalla(cursor.getInt(2));
		    	ent.setCantidad(cursor.getInt(3));
		    }
		    
		    return ent;
	 }
}

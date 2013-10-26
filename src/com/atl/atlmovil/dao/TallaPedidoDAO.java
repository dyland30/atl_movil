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
	private Context contexto;
	
	public TallaPedidoDAO(Context context){
		contexto = context;
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
	 
	 public List<TallaPedido> buscarPorPedidoProducto(long idPedido, long codProducto){
		 
		 List<TallaPedido> ls = new ArrayList<TallaPedido>();
		 Cursor cursor = database.query(TallaPedido.class.getSimpleName(), allColumns, "idPedido = "+idPedido+" AND codigoProducto = "+codProducto,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 TallaPedido ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public int obtenerCantidadTallas(long idPedido, long codigoProducto){
		 int suma =0;		
		 Cursor cursor = database.rawQuery("SELECT SUM(cantidad) as suma FROM TallaPedido WHERE idPedido = ? AND codigoProducto = ?", new String[] {idPedido+"", codigoProducto+""});;
		 cursor.moveToFirst();
		 suma = cursor.getInt(0);
		 cursor.close();
		 return suma;
	 }
	 
	 public int obtenerCantidadPorPedido(long idPedido){
		 int suma =0;		
		 Cursor cursor = database.rawQuery("SELECT SUM(cantidad) as suma FROM TallaPedido WHERE idPedido = ? ", new String[] {idPedido+""});;
		 cursor.moveToFirst();
		 suma = cursor.getInt(0);
		 cursor.close();
		 return suma;
	 }
	 
	 public void eliminar(TallaPedido ent){
		 long id = ent.getIdPedido();
		 long codProducto = ent.getCodigoProducto();
		 long nroTalla = ent.getNumeroTalla();
		 database.delete(TallaPedido.class.getSimpleName(),"idPedido = "+id+" and codigoProducto ="+codProducto+ " and numeroTalla = "+nroTalla,null);
		 
	 }
	 
	 public TallaPedido crear(long idPedido, long codigoProducto, long numeroTalla, int cantidad) throws Exception{
		 TallaPedido ent = null;
		 ContentValues values = new ContentValues();
		 values.put("idPedido", idPedido);
		 values.put("codigoProducto", codigoProducto);
		 values.put("numeroTalla", numeroTalla);
		 
		 values.put("cantidad", cantidad);
		 
		 
		 actualizarStock(idPedido, codigoProducto, numeroTalla, cantidad, "salida");
		 database.insert(TallaPedido.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(idPedido, codigoProducto, numeroTalla);
		 // actualizar stock
		 // validar cantidad de stock disponible
		 
		 
		 return ent;
	 }
	 
	 public void actualizarStock(long idPedido, long codigoProducto, long numeroTalla, int cantidad, String tipo) throws Exception{
		 //tipo -> ingreso, salida
		 if(cantidad>0){
			 int stockactual =0;		
			 TallaDAO tDao= new TallaDAO(contexto);
			 tDao.open();
			 Talla t =tDao.buscarPorID(codigoProducto, (int)numeroTalla);
			 tDao.close();
			 if(t!=null){
				 stockactual = t.getStockDisponibleTalla();
			
				 Boolean aceptaRetencion=false;
				 PedidoDAO pdao = new PedidoDAO(contexto);
				 pdao.open();
				 Pedido ped = pdao.buscarPorID(idPedido);
				 pdao.close();
				 if(ped!=null){
					 aceptaRetencion = ped.getAceptaRetencionPedido();
				 }
				 if(tipo.equals("salida")){
					 
					 stockactual-= cantidad;
					 
					 //validar stock 
					 if(stockactual<=0){
						 if(!aceptaRetencion){
							 throw new Exception("No hay suficiente Stock, el pedido no acepta ingresar retenido");
						 } else{
							 stockactual = 0;
						 }
					 }
					 
				 } else{
					 stockactual += cantidad;
				 }
				 
				 t.setStockDisponibleTalla(stockactual);
				 tDao.open();
				 tDao.actualizar(t);
				 tDao.close();
			 }
			 
		 }
		 
	 }
	 
	 public TallaPedido crear(TallaPedido tPed) throws Exception{
		 TallaPedido ent = null;
		 ContentValues values = new ContentValues();
		 values.put("idPedido", tPed.getIdPedido());
		 values.put("codigoProducto", tPed.getCodigoProducto());
		 values.put("numeroTalla", tPed.getNumeroTalla());
		 
		 values.put("cantidad", tPed.getCantidad());
		 
		 
		 actualizarStock(tPed.getIdPedido(), tPed.getCodigoProducto(), tPed.getNumeroTalla(), tPed.getCantidad(), "salida");
		 database.insert(TallaPedido.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(tPed.getIdPedido(), tPed.getCodigoProducto(), tPed.getNumeroTalla());
		 		
		 return ent;
	 }
	 
	 
	 public TallaPedido actualizar(TallaPedido ent) throws Exception{
		 TallaPedido nuevo = null;
		 TallaPedido old = null;
		 int cantidad = ent.getCantidad();
		 old = nuevo=buscarPorID(ent.getIdPedido(), ent.getCodigoProducto(), ent.getNumeroTalla());
		 int cantOld = old.getCantidad();
		 String tipoMovimiento = "salida";
		 int dif = cantidad-cantOld;
		 if(dif<0){
			 tipoMovimiento = "ingreso";
			 dif = (-1)*dif;
		 }
		 ContentValues values = new ContentValues();
		 values.put("cantidad", ent.getCantidad());
		 
		 actualizarStock(ent.getIdPedido(), ent.getCodigoProducto(), ent.getNumeroTalla(), dif, tipoMovimiento);
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

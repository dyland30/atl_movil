package com.atl.atlmovil.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class PedidoDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "id","codigoPedido","codigoFormaPago","codigoVisita","aceptaRetencionPedido","direccionDeEnvio",
			"empresaTransporte","estadoPedido","estaRetenidoPedido","estaSincronizado","fechaIngresoPedido","importePedido",
			"instruccionesEspeciales","lineaReservadaPedido", "codigoEmpresaCarga"};

	public PedidoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Pedido> obtenerTodos(){
		 List<Pedido> ls = new ArrayList<Pedido>();
		 Cursor cursor = database.query(Pedido.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Pedido ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Pedido ent){
		 long id = ent.getCodigoPedido();
		 database.delete(Pedido.class.getSimpleName(),"codigoPedido = "+id,null);
		 
	 }
	 
	 public Pedido crear(long codigoPedido, long codigoFormaPago, long codigoVisita, Boolean aceptaRetencionPedido, String direccionDeEnvio,
			 String empresaTransporte, String estadoPedido, Boolean estaRetenidoPedido, Boolean estaSincronizado,String fechaIngresoPedido, Double importePedido,
			 String instruccionesEspeciales, Double lineaReservadaPedido, long codigoEmpresaCarga){
		 Pedido ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoPedido", codigoPedido);
		 values.put("codigoFormaPago", codigoFormaPago);
		 values.put("codigoVisita", codigoVisita);
		 values.put("aceptaRetencionPedido", aceptaRetencionPedido);
		 values.put("direccionDeEnvio", direccionDeEnvio);
		 values.put("empresaTransporte", empresaTransporte);
		 values.put("estadoPedido", estadoPedido);
		 values.put("estaRetenidoPedido", estaRetenidoPedido);
		 values.put("estaSincronizado", estaSincronizado);
		 values.put("fechaIngresoPedido", fechaIngresoPedido);
		 values.put("importePedido", importePedido);
		 values.put("instruccionesEspeciales", instruccionesEspeciales);
		 values.put("lineaReservadaPedido", lineaReservadaPedido);
		 values.put("codigoEmpresaCarga", codigoEmpresaCarga);
		 long insertId = database.insert(Pedido.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Pedido crear(Pedido ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 ContentValues values = new ContentValues();
		 values.put("codigoPedido", ent.getCodigoPedido());
		 values.put("codigoFormaPago", ent.getCodigoFormaPago());
		 values.put("codigoVisita", ent.getCodigoVisita());
		 values.put("aceptaRetencionPedido", ent.getAceptaRetencionPedido());
		 values.put("direccionDeEnvio", ent.getDireccionDeEnvio());
		 values.put("empresaTransporte", ent.getEmpresaTransporte());
		 values.put("estadoPedido", ent.getEstadoPedido());
		 values.put("estaRetenidoPedido", ent.getEstaRetenidoPedido());
		 values.put("estaSincronizado", ent.getEstaSincronizado());
		 values.put("fechaIngresoPedido", dateFormat.format(ent.getFechaIngresoPedido()));
		 values.put("importePedido", ent.getImportePedido());
		 values.put("instruccionesEspeciales", ent.getInstruccionesEspeciales());
		 values.put("lineaReservadaPedido", ent.getLineaReservadaPedido());
		 values.put("codigoEmpresaCarga", ent.getCodigoEmpresaCarga());
		 
		 long insertId = database.insert(Pedido.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Pedido actualizar(Pedido ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Pedido nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoPedido", ent.getCodigoPedido());
		 values.put("codigoFormaPago", ent.getCodigoFormaPago());
		 values.put("codigoVisita", ent.getCodigoVisita());
		 values.put("aceptaRetencionPedido", ent.getAceptaRetencionPedido());
		 values.put("direccionDeEnvio", ent.getDireccionDeEnvio());
		 values.put("empresaTransporte", ent.getEmpresaTransporte());
		 values.put("estadoPedido", ent.getEstadoPedido());
		 values.put("estaRetenidoPedido", ent.getEstaRetenidoPedido());
		 values.put("estaSincronizado", ent.getEstaSincronizado());
		 values.put("fechaIngresoPedido", dateFormat.format(ent.getFechaIngresoPedido()));
		 values.put("importePedido", ent.getImportePedido());
		 values.put("instruccionesEspeciales", ent.getInstruccionesEspeciales());
		 values.put("lineaReservadaPedido", ent.getLineaReservadaPedido());
		 values.put("codigoEmpresaCarga", ent.getCodigoEmpresaCarga());
		 database.update(Pedido.class.getSimpleName(), values, " id = "+ent.getId(), null);
		 nuevo=buscarPorID(ent.getId());
		 
		 return nuevo;
	 }
	 public Pedido buscarPorID(long id){
		 Pedido ent = null;
		 Cursor cursor = database.query(Pedido.class.getSimpleName(), allColumns, " id = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Pedido cursorToEnt(Cursor cursor) {
		 	
		 /*
		  * 
		  * 
		  * "id","codigoPedido","codigoFormaPago","codigoVisita","aceptaRetencionPedido","direccionDeEnvio",
			"empresaTransporte","estadoPedido","estaRetenidoPedido","estaSincronizado","fechaIngresoPedido","importePedido",
			"instruccionesEspeciales","lineaReservadaPedido", "codigoEmpresaCarga"
		  * 
		  * */
		 
		    Pedido ent = null;
		    if(cursor!=null ){
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    	
		    	ent = new Pedido();
		    	ent.setId(cursor.getLong(0));
		    	ent.setCodigoPedido(cursor.getLong(1));
		    	ent.setCodigoFormaPago(cursor.getLong(2));
		    	ent.setCodigoVisita(cursor.getLong(3));
		    	Boolean aceptaRet = cursor.getInt(4) == 1 ? true : false;
		    	
		    	ent.setAceptaRetencionPedido(aceptaRet);
		    	ent.setDireccionDeEnvio(cursor.getString(5));
		    	ent.setEmpresaTransporte(cursor.getString(6));
		    	ent.setEstadoPedido(cursor.getString(7));
		    	Boolean estaRet = cursor.getInt(8) == 1 ? true : false;
		    	ent.setEstaRetenidoPedido(estaRet);
		    	Boolean estaSinc = cursor.getInt(9)==1?true:false;
		    	ent.setEstaSincronizado(estaSinc);
		    	
		    	try {
					ent.setFechaIngresoPedido(dateFormat.parse(cursor.getString(10)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaIngresoPedido(new Date(1900,1,1));
					
				}
		    	
		    	ent.setImportePedido(cursor.getDouble(11));
		    	ent.setInstruccionesEspeciales(cursor.getString(12));
		    	ent.setLineaReservadaPedido(cursor.getDouble(13));
		    	ent.setCodigoEmpresaCarga(cursor.getLong(14));
		    	
		    	
		    }
		    
		    return ent;
	 }
	
}

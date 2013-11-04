package com.atl.atlmovil.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.Deposito;

@SuppressLint("SimpleDateFormat")
public class DepositoDAO {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "id",
			"codigoDeposito", "codigoCobranza","codigoMedioPago", "bancoDeposito","clienteDeposito", 
			"voucherDeposito", "agenciaDeposito","terminalDeposito", "estadoDeposito ", "fechaDeposito", 
			"importeDeposito"};

	public DepositoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Deposito> obtenerTodos(){
		 List<Deposito> ls = new ArrayList<Deposito>();
		 Cursor cursor = database.query(Deposito.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Deposito ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<Deposito> buscarPorCliente(long codigoCliente ){
		 List<Deposito> ls = new ArrayList<Deposito>();
		 Cursor cursor = database.query(Deposito.class.getSimpleName(), allColumns, " clienteDeposito = "+codigoCliente,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Deposito ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Deposito ent){
		 long id = ent.getId();
		 database.delete(Deposito.class.getSimpleName(),"id = "+id,null);
		 
	 }
	 
	 public Deposito crear(long id,long codigoDeposito, long codigoCobranza,long codigoMedioPago, int bancoDeposito,
			 long clienteDeposito,long voucherDeposito, String agenciaDeposito,String terminalDeposito,
			 Boolean estadoDeposito, Date fechaDeposito, Double importeDeposito){

		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Deposito ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoDeposito", codigoDeposito);
		 values.put("codigoCobranza", codigoCobranza);
		 values.put("codigoMedioPago", codigoMedioPago);
		 values.put("bancoDeposito", bancoDeposito);
		 values.put("clienteDeposito", clienteDeposito);
		 values.put("voucherDeposito", voucherDeposito);
		 values.put("agenciaDeposito", agenciaDeposito);
		 values.put("terminalDeposito", terminalDeposito);
		 values.put("estadoDeposito", estadoDeposito);		 
		 values.put("fechaDeposito", dateFormat.format(fechaDeposito));
		 values.put("importeDeposito", importeDeposito); 
		 long insertId = database.insert(Deposito.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Deposito actualizar(Deposito ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Deposito nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoDeposito", ent.getCodigoDeposito());
		 values.put("codigoCobranza", ent.getCodigoCobranza());
		 values.put("codigoMedioPago", ent.getCodigoMedioPago());
		 values.put("bancoDeposito", ent.getBancoDeposito());
		 values.put("clienteDeposito", ent.getClienteDeposito());
		 values.put("voucherDeposito", ent.getVoucherDeposito());
		 values.put("agenciaDeposito", ent.getAgenciaDeposito());
		 values.put("terminalDeposito", ent.getTerminalDeposito());
		 values.put("estadoDeposito", ent.getEstadoDeposito());		 
		 values.put("fechaDeposito", dateFormat.format(ent.getFechaDeposito()));
		 values.put("importeDeposito", ent.getImporteDeposito()); 
		 
		 database.update(Deposito.class.getSimpleName(), values, " id = "+ent.getId(), null);
		 nuevo=buscarPorID(ent.getId());
		 
		 return nuevo;
	 }
	 public Deposito buscarPorID(long id){
		 Deposito ent = null;
		 Cursor cursor = database.query(Deposito.class.getSimpleName(), allColumns, " id = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Deposito cursorToEnt(Cursor cursor) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Deposito ent = null;
		    if(cursor!=null ){
		    	ent = new Deposito();
		    	ent.setId(cursor.getLong(0));
		    	ent.setCodigoDeposito(cursor.getLong(1));
		    	ent.setCodigoCobranza(cursor.getLong(2));
		    	ent.setCodigoMedioPago(cursor.getLong(3));
		    	ent.setBancoDeposito(cursor.getInt(4));
		    	ent.setClienteDeposito(cursor.getLong(5));
		    	ent.setVoucherDeposito(cursor.getLong(6));
		    	ent.setAgenciaDeposito(cursor.getString(7));
		    	ent.setTerminalDeposito(cursor.getString(8));
		    	Boolean estadoDeposito = cursor.getInt(9)==1 ? true : false;
		    	ent.setEstadoDeposito(estadoDeposito);
		    	try {
					ent.setFechaDeposito(dateFormat.parse(cursor.getString(10)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					ent.setFechaDeposito(new Date(1900,1,1));
				}
		    	
		    	ent.setImporteDeposito(cursor.getDouble(11));		    	
		    	try {
					ent.setFechaDeposito(dateFormat.parse(cursor.getString(4)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaDeposito(new Date(1900,1,1));
					
				}
		    	
		    }
		    
		    return ent;
	 }


}

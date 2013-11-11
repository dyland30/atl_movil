package com.atl.atlmovil.dao;
import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.*;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class ClienteDAO {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoCliente","codigoEmpleado","codigoGrupo","codigoPersona","direccionEntregaCliente",
			"representanteCliente","celularCliente", };

	public ClienteDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Cliente> obtenerTodos(){
		 List<Cliente> ls = new ArrayList<Cliente>();
		 Cursor cursor = database.query(Cliente.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Cliente ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Cliente ent){
		 long id = ent.getCodigoCliente();
		 database.delete(Cliente.class.getSimpleName(),"codigoCliente = "+id,null);
		 
	 }
	 
	 public Cliente crear(long codigoCliente, long codigoEmpleado, long codigoGrupo, long codigoPersona, String direccionEntregaCliente,
			 String representanteCliente, String celularCliente){
		 Cliente ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoCliente", codigoCliente);
		 values.put("codigoGrupo", codigoGrupo);
		 values.put("codigoPersona", codigoPersona);
		 values.put("direccionEntregaCliente", direccionEntregaCliente);
		 values.put("representanteCliente", representanteCliente);
		 values.put("celularCliente", celularCliente);
		 
		 long insertId = database.insert(Cliente.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Cliente actualizar(Cliente ent){
		 Cliente nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoGrupo", ent.getCodigoGrupo());
		 values.put("codigoPersona", ent.getCodigoPersona());
		 values.put("direccionEntregaCliente", ent.getDireccionEntregaCliente());
		 values.put("representanteCliente", ent.getRepresentanteCliente());
		 values.put("celularCliente", ent.getCelularCliente());
		 database.update(Cliente.class.getSimpleName(), values, " codigoCliente = "+ent.getCodigoCliente(), null);
		 nuevo=buscarPorID(ent.getCodigoCliente());
		 
		 return nuevo;
	 }
	 public Cliente buscarPorID(long id){
		 Cliente ent = null;
		 
		 Cursor cursor = database.query(Cliente.class.getSimpleName(), allColumns, " codigoCliente = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			
		 }
		 cursor.close();	 
		 return ent;
	 }
	 
	 private Cliente cursorToEnt(Cursor cursor) {
		    Cliente ent = null;
		    if(cursor!=null ){
		    	ent = new Cliente();
		    	ent.setCodigoCliente(cursor.getLong(0));
		    	ent.setCodigoEmpleado(cursor.getLong(1));
		    	ent.setCodigoGrupo(cursor.getLong(2));
		    	ent.setCodigoPersona(cursor.getLong(3));
		    	ent.setDireccionEntregaCliente(cursor.getString(4));
		    	ent.setRepresentanteCliente(cursor.getString(5));
		    	
		    }
		    
		    return ent;
	 }
}

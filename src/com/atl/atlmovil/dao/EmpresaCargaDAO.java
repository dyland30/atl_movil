package com.atl.atlmovil.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.EmpresaCarga;

public class EmpresaCargaDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoEmpresaCarga", "nombreEmpresaCarga",
			"direccionEmpresaCarga","horarioEmpresaCarga","rucEmpresaCarga"};
	

	public EmpresaCargaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<EmpresaCarga> obtenerTodos(){
		 List<EmpresaCarga> ls = new ArrayList<EmpresaCarga>();
		 Cursor cursor = database.query(EmpresaCarga.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 EmpresaCarga tv = cursorToEnt(cursor);
			 ls.add(tv);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<EmpresaCarga> buscarPorNombre(String nombre){
		 List<EmpresaCarga> ls = new ArrayList<EmpresaCarga>();
		 Cursor cursor = database.query(EmpresaCarga.class.getSimpleName(), allColumns, " nombreEmpresaCarga like '%"+nombre+"%' or rucEmpresaCarga like '%"+nombre+"%'",null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 EmpresaCarga tv = cursorToEnt(cursor);
			 ls.add(tv);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 
	 
	 public void eliminar(EmpresaCarga tv){
		 long id = tv.getCodigoEmpresaCarga();
		 database.delete(EmpresaCarga.class.getSimpleName(),"codigoEmpresaCarga = "+id,null);
		 
	 }
	 
	 public EmpresaCarga crear(long codigoEmpresaCarga, String nombreEmpresaCarga,
			 String direccionEmpresaCarga, String horarioEmpresaCarga, String rucEmpresaCarga){
		 EmpresaCarga us = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoEmpresaCarga", codigoEmpresaCarga);
		 values.put("nombreEmpresaCarga", nombreEmpresaCarga);
		 values.put("direccionEmpresaCarga", direccionEmpresaCarga);
		 values.put("horarioEmpresaCarga", horarioEmpresaCarga);
		 values.put("rucEmpresaCarga", rucEmpresaCarga);
		 
		 long insertId = database.insert(EmpresaCarga.class.getSimpleName(), null, values);
		 
		 us = buscarPorID(insertId);
		
		 return us;
	 }
	 
	 public EmpresaCarga actualizar(EmpresaCarga ent){
		 EmpresaCarga nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("nombreEmpresaCarga", ent.getNombreEmpresaCarga());
		 values.put("direccionEmpresaCarga", ent.getDireccionEmpresaCarga());
		 values.put("horarioEmpresaCarga", ent.getHorarioEmpresaCarga());
		 values.put("rucEmpresaCarga", ent.getRucEmpresaCarga());
		 
		 database.update(EmpresaCarga.class.getSimpleName(), values, " codigoEmpresaCarga = "+ent.getCodigoEmpresaCarga(), null);
		 
		 nuevo=buscarPorID(ent.getCodigoEmpresaCarga());
		 
		 return nuevo;
	 }
	 public EmpresaCarga buscarPorID(long id){
		 EmpresaCarga ent = null;
		 Cursor cursor = database.query(EmpresaCarga.class.getSimpleName(), allColumns, " codigoEmpresaCarga = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			 		 
		 }
		 cursor.close();
		 return ent;
	 }
	 
	 private EmpresaCarga cursorToEnt(Cursor cursor) {
		    EmpresaCarga ent = null;
		    if(cursor!=null ){
		    	ent = new EmpresaCarga();
		    	ent.setCodigoEmpresaCarga(cursor.getInt(0));
			    ent.setNombreEmpresaCarga(cursor.getString(1));
			    ent.setDireccionEmpresaCarga(cursor.getString(2));
			    ent.setHorarioEmpresaCarga(cursor.getString(3));
			    ent.setRucEmpresaCarga(cursor.getString(4));
			    
		    }
		    
		    return ent;
	 }
	
}

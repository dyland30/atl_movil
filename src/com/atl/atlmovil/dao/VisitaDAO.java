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

public class VisitaDAO {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoVisita","codigoCliente","codigoEmpleado","codigoEstadoVisita","codigoTipoVisita","fechaVisita","horaInicioVisita","horaFinalVisita"};

	public VisitaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Visita> obtenerTodos(){
		 List<Visita> ls = new ArrayList<Visita>();
		 Cursor cursor = database.query(Visita.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Visita vi = cursorToEnt(cursor);
			 ls.add(vi);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<Visita> obtenerVisitasEstadoTipo(long codTipoVisita, long codEstadoVisita){
		 List<Visita> ls = new ArrayList<Visita>();
		 Cursor cursor = database.query(Visita.class.getSimpleName(), allColumns, " codigoEstadoVisita = '"+codEstadoVisita+"' and codigoTipoVisita = '"+codTipoVisita+"'",null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Visita vi = cursorToEnt(cursor);
			 ls.add(vi);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
		 
		 
	 }
	 
	 public void eliminar(Visita vi){
		 long id = vi.getCodigoVisita();
		 database.delete(Visita.class.getSimpleName(),"codigoVisita = "+id,null);
		 
	 }
	 
	 public Visita crear(long codigoVisita, long codigoEmpleado, long codigoEstadoVisita, long codigoTipoVisita, String fechaVisita,
			 			String horaInicioVisita, String horaFinalVisita){
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Visita vi = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoVisita", codigoVisita);
		 values.put("codigoEmpleado", codigoEmpleado);
		 values.put("codigoEstadoVisita",codigoEstadoVisita);
		 values.put("codigoTipoVisita",codigoTipoVisita);
		 values.put("fechaVisita",  fechaVisita);
		 values.put("horaInicioVisita",  horaInicioVisita);
		 values.put("horaFinalVisita",  horaFinalVisita);
		 
		 long insertId = database.insert(Visita.class.getSimpleName(), null, values);
		 
		 vi = buscarPorID(insertId);
		
		 return vi;
	 }
	 
	 public Visita actualizar(Visita vi){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Visita viNew = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoEmpleado", vi.getCodigoEmpleado());
		 values.put("codigoEstadoVisita",vi.getCodigoEstadoVisita());
		 values.put("codigoTipoVisita",vi.getCodigoTipoVisita());
		 values.put("fechaVisita",  dateFormat.format(vi.getFechaVisita()));
		 values.put("horaInicioVisita",  dateFormat.format(vi.getHoraInicioVisita()));
		 values.put("horaFinalVisita",  dateFormat.format(vi.getHoraFinalVisita()));
		 
		 
		 database.update(Visita.class.getSimpleName(), values, " codigoVisita = "+vi.getCodigoVisita(), null);
		 
		 viNew=buscarPorID(vi.getCodigoVisita());
		 
		 return viNew;
	 }
	 
	 public Boolean existeVisitaActiva(){
		 Visita vi = null;
		 Boolean existe=false;
		 Cursor cursor = database.query(Visita.class.getSimpleName(), allColumns, " codigoEstadoVisita = '"+2+"'",null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 vi = cursorToEnt(cursor); 
		 }
		 
		 if(vi!=null){
			existe = true; 
		 }
		 return existe;
		 
	 }
	 
	 public Visita buscarPorID(long id){
		 Visita vi = null;
		 Cursor cursor = database.query(Visita.class.getSimpleName(), allColumns, " codigoVisita = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 vi = cursorToEnt(cursor);
		 cursor.close();		 
		 return vi;
	 }
	 
	
	 
	 private Visita cursorToEnt(Cursor cursor) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 
		    Visita ent = null;
		    if(cursor!=null ){
		    	ent = new Visita();
		    	ent.setCodigoVisita(cursor.getLong(0));
		    	ent.setCodigoCliente(cursor.getLong(1));
		    	ent.setCodigoEmpleado(cursor.getLong(2));
		    	ent.setCodigoEstadoVisita(cursor.getLong(3));
		    	ent.setCodigoTipoVisita(cursor.getLong(4));
		    	try {
					ent.setFechaVisita(dateFormat.parse(cursor.getString(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaVisita(new Date(1900,01,01));
					
				}
		    	try {
					ent.setHoraInicioVisita(dateFormat.parse(cursor.getString(6)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setHoraInicioVisita(new Date(1900,01,01));
				}
		    	try {
					ent.setHoraFinalVisita(dateFormat.parse(cursor.getString(7)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setHoraFinalVisita(new Date(1900,01,01));
				}
		    	
		    }
		    
		    return ent;
	 }

}

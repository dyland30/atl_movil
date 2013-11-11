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


public class EmpleadoDAO {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "codigoEmpleado", "codigoPersona", "areaEmpleado", "cargoEmpleado", 
			"fechaCeseEmpleado", "fechaIngresoEmpleado", "jefeEmpleado"};

	public EmpleadoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Empleado> obtenerTodos(){
		 List<Empleado> ls = new ArrayList<Empleado>();
		 Cursor cursor = database.query(Empleado.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Empleado ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Empleado ent){
		 long id = ent.getCodigoEmpleado();
		 database.delete(Empleado.class.getSimpleName(),"codigoEmpleado = "+id,null);
		 
	 }
	 
	 public Empleado crear(long codigoEmpleado, long codigoPersona, String areaEmpleado, String cargoEmpleado, 
				Date fechaCeseEmpleado, Date fechaIngresoEmpleado, long jefeEmpleado){
		 Empleado ent = null;
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 ContentValues values = new ContentValues();
		 values.put("codigoEmpleado", codigoEmpleado);
		 values.put("codigoPersona", codigoPersona);
		 values.put("areaEmpleado", areaEmpleado);
		 values.put("cargoEmpleado", cargoEmpleado);
		 values.put("fechaCeseEmpleado", dateFormat.format(fechaCeseEmpleado));
		 values.put("fechaIngresoEmpleado", dateFormat.format(fechaIngresoEmpleado));
		 values.put("jefeEmpleado", jefeEmpleado);
		 
		 long insertId = database.insert(Empleado.class.getSimpleName(), null, values);
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Empleado actualizar(Empleado ent){
		 Empleado nuevo = null;
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 ContentValues values = new ContentValues();
		 values.put("codigoPersona", ent.getCodigoPersona());
		 values.put("areaEmpleado", ent.getAreaEmpleado());
		 values.put("cargoEmpleado", ent.getCargoEmpleado());
		 values.put("fechaCeseEmpleado", dateFormat.format(ent.getFechaCeseEmpleado()));
		 values.put("fechaIngresoEmpleado", dateFormat.format(ent.getFechaIngresoEmpleado()));
		 values.put("jefeEmpleado", ent.getJefeEmpleado());
		 database.update(Empleado.class.getSimpleName(), values, " codigoEmpleado = "+ent.getCodigoEmpleado(), null);
		 nuevo=buscarPorID(ent.getCodigoEmpleado());
		 
		 return nuevo;
	 }
	 public Empleado buscarPorID(long id){
		 Empleado ent = null;
		 Cursor cursor = database.query(Empleado.class.getSimpleName(), allColumns, " codigoEmpleado = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			 		 
		 }
		 cursor.close();
		 return ent;
	 }
	 
	 private Empleado cursorToEnt(Cursor cursor) {
		    Empleado ent = null;
		    /*
		     * "codigoEmpleado", "codigoPersona", "areaEmpleado", "cargoEmpleado", 
			"fechaCeseEmpleado", "fechaIngresoEmpleado", "jefeEmpleado"
		     * */
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    if(cursor!=null ){
		    	ent = new Empleado();
		    	ent.setCodigoEmpleado(cursor.getLong(0));
		    	ent.setCodigoPersona(cursor.getLong(1));
		    	ent.setAreaEmpleado(cursor.getString(2));
		    	ent.setCargoEmpleado(cursor.getString(3));
		    	
		    	try {
					ent.setFechaCeseEmpleado(dateFormat.parse(cursor.getString(4)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaCeseEmpleado(new Date(1900,1,1));
					
				}
		    	try {
					ent.setFechaIngresoEmpleado(dateFormat.parse(cursor.getString(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaIngresoEmpleado(new Date(1900,1,1));
					
				}
		    	ent.setJefeEmpleado(cursor.getLong(5));
		    	
		    }
		    
		    return ent;
	 }
}

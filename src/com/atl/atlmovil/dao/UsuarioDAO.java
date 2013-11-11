package com.atl.atlmovil.dao;
import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.Usuario;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "id","login","clave","nombres","apellidos","dni"};

	public UsuarioDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Usuario> obtenerTodos(){
		 List<Usuario> ls = new ArrayList<Usuario>();
		 Cursor cursor = database.query(Usuario.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Usuario us = cursorToEnt(cursor);
			 ls.add(us);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Usuario us){
		 long id = us.getId();
		 database.delete(Usuario.class.getSimpleName(),"id = "+id,null);
		 
	 }
	 
	 public Usuario crear(String login, String clave, String nombres, String apellidos, String dni){
		 Usuario us = null;
		 ContentValues values = new ContentValues();
		 values.put("login", login);
		 values.put("clave", clave);
		 values.put("nombres", nombres);
		 values.put("apellidos", apellidos);
		 values.put("dni", dni);
		 long insertId = database.insert(Usuario.class.getSimpleName(), null, values);
		 
		 us = buscarPorID(insertId);
		
		 return us;
	 }
	 
	 public Usuario actualizar(Usuario us){
		 Usuario usNew = null;
		 ContentValues values = new ContentValues();
		 values.put("login", us.getLogin());
		 values.put("clave", us.getClave());
		 values.put("nombres", us.getNombres());
		 values.put("apellidos", us.getApellidos());
		 values.put("dni", us.getDni());
		 
		 database.update(Usuario.class.getSimpleName(), values, " id = "+us.getId(), null);
		 
		 usNew=buscarPorID(us.getId());
		 
		 return usNew;
	 }
	 public Usuario buscarPorID(long id){
		 Usuario us = null;
		 Cursor cursor = database.query(Usuario.class.getSimpleName(), allColumns, " id = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 us = cursorToEnt(cursor);
		 }
		 
		 cursor.close();		 
		 return us;
	 }
	 
	 public Usuario buscarPorLogin(String login){
		 Usuario us = null;
		 Cursor cursor = database.query(Usuario.class.getSimpleName(), allColumns, " login = '"+login+"'",null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 us = cursorToEnt(cursor);
		 }
		 cursor.close();		 
		 return us;
	 }
	 
	 
	 private Usuario cursorToEnt(Cursor cursor) {
		    Usuario ent = null;
		    if(cursor!=null ){
		    	ent = new Usuario();
		    	ent.setId(cursor.getInt(0));
			    ent.setLogin(cursor.getString(1));
			    ent.setClave(cursor.getString(2));
			    ent.setNombres(cursor.getString(3));
			    ent.setApellidos(cursor.getString(4));
			    ent.setDni(cursor.getString(5));
		    	
		    }
		    
		    return ent;
	 }
}

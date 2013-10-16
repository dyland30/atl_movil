package com.owlsoft.encnotes;

import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class UsuarioDAO {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_USUARIO_ID,
			MySQLiteHelper.COLUMN_USUARIO_LOGIN,
			MySQLiteHelper.COLUMN_USUARIO_FIRSTNAME,
			MySQLiteHelper.COLUMN_USUARIO_LASTNAME,
			MySQLiteHelper.COLUMN_USUARIO_PASSWORD,
			MySQLiteHelper.COLUMN_USUARIO_LLAVE,
			MySQLiteHelper.COLUMN_USUARIO_PREGUNTA,
			MySQLiteHelper.COLUMN_USUARIO_RESPUESTA,
			MySQLiteHelper.COLUMN_USUARIO_FCH_CREACION,
			MySQLiteHelper.COLUMN_USUARIO_FCH_MODIFICACION};
	
	public UsuarioDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	public void close() {
		 dbHelper.close();
	}
	
	
	public void eliminar(Usuario us){
		 long id = us.getId();
		 database.delete(MySQLiteHelper.TABLE_USUARIO,MySQLiteHelper.COLUMN_USUARIO_ID+" = "+id,null);
		 
	}
	
	public Usuario crear(Usuario us){
		 SimpleDateFormat formato = new SimpleDateFormat(Configuracion.FORMATOFECHA, Locale.US);
		 Date fecha = new Date();
		 
		 us.setFchCreacion(formato.format(fecha));
		 us.setFchModificacion(formato.format(fecha));
		 
		 ContentValues values = new ContentValues();
		 values.put(MySQLiteHelper.COLUMN_USUARIO_LOGIN, us.getLogin());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_FIRSTNAME, us.getFirstname());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_LASTNAME, us.getLastname());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_PASSWORD, us.getPassword());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_LLAVE, us.getLlave());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_PREGUNTA, us.getPregunta()); 
		 values.put(MySQLiteHelper.COLUMN_USUARIO_RESPUESTA, us.getRespuesta());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_FCH_CREACION, us.getFchCreacion()); 
		 values.put(MySQLiteHelper.COLUMN_USUARIO_FCH_MODIFICACION, us.getFchModificacion());
		 
		 long insertId = database.insert(MySQLiteHelper.TABLE_USUARIO, null, values);
		 
		 us = obtenerUsuarioId(insertId);
		
		 return us;
	 }
	
	public Usuario actualizar(Usuario us){
		 Usuario usNew = null;
		 SimpleDateFormat formato = new SimpleDateFormat(Configuracion.FORMATOFECHA, Locale.US);
		 Date fecha = new Date();
		 
		 us.setFchModificacion(formato.format(fecha));
		 ContentValues values = new ContentValues();
		 values.put(MySQLiteHelper.COLUMN_USUARIO_LOGIN, us.getLogin());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_FIRSTNAME, us.getFirstname());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_LASTNAME, us.getLastname());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_PASSWORD, us.getPassword());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_LLAVE, us.getLlave());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_PREGUNTA, us.getPregunta()); 
		 values.put(MySQLiteHelper.COLUMN_USUARIO_RESPUESTA, us.getRespuesta());
		 values.put(MySQLiteHelper.COLUMN_USUARIO_FCH_CREACION, us.getFchCreacion()); 
		 values.put(MySQLiteHelper.COLUMN_USUARIO_FCH_MODIFICACION, us.getFchModificacion());	
		 
		 database.update(MySQLiteHelper.TABLE_USUARIO, values, MySQLiteHelper.COLUMN_USUARIO_ID +" = "+us.getId(), null);
		 
		 usNew=obtenerUsuarioId(us.getId());
		 
		 return usNew;
	 }
	
	
	public Usuario obtenerUsuarioId(long id){
		 Usuario us = null;
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_USUARIO, allColumns, MySQLiteHelper.COLUMN_USUARIO_ID +" = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 us = cursorToUsuario(cursor);
		 cursor.close();
		 
		 return us;
		 
	 }
	 public List<Usuario> obtenerUsuarioLogin(String login){
		 List<Usuario> ls = new ArrayList<Usuario>();
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_USUARIO, allColumns, MySQLiteHelper.COLUMN_USUARIO_LOGIN +" like %"+login+"%",null,null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Usuario us = cursorToUsuario(cursor);
			 ls.add(us);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	
	
	private Usuario cursorToUsuario(Cursor cursor) {
		Usuario us = new Usuario();
	    us.setId(cursor.getLong(0));
	    us.setLogin(cursor.getString(1));
	    us.setFirstname(cursor.getString(2));
	    us.setLastname(cursor.getString(3));
	    us.setPassword(cursor.getString(4));
	    us.setLlave(cursor.getString(5));
	    us.setPregunta(cursor.getString(6));
	    us.setRespuesta(cursor.getString(7));
	    us.setFchCreacion(cursor.getString(8));
	    us.setFchModificacion(cursor.getString(9));
	    return us;
 }
	

}

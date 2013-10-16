package com.owlsoft.encnotes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class NotaDAO {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_NOTA_ID,
	      MySQLiteHelper.COLUMN_NOTA_TITULO, MySQLiteHelper.COLUMN_NOTA_TEXTO, MySQLiteHelper.COLUMN_NOTA_COD_USUARIO,
	      MySQLiteHelper.COLUMN_NOTA_LLAVE,MySQLiteHelper.COLUMN_NOTA_FLG_ENCRIPTADO, MySQLiteHelper.COLUMN_NOTA_FCH_CREACION, 
	      MySQLiteHelper.COLUMN_NOTA_FCH_MODIFICACION };

	public NotaDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 /*
	  * 
	  *  public Comment createComment(String comment) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
    long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
        values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Comment newComment = cursorToComment(cursor);
    cursor.close();
    return newComment;
    
    
    public void deleteComment(Comment comment) {
    long id = comment.getId();
    System.out.println("Comment deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
        + " = " + id, null);
        
    public List<Comment> getAllComments() {
    List<Comment> comments = new ArrayList<Comment>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Comment comment = cursorToComment(cursor);
      comments.add(comment);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return comments;
  }    
        
  }
    
  }*/
	 
	 public List<Nota> obtenerTodas(){
		 List<Nota> ls = new ArrayList<Nota>();
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTAS, allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Nota nota = cursorToNota(cursor);
			 ls.add(nota);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Nota nota){
		 long id = nota.getId();
		 database.delete(MySQLiteHelper.TABLE_NOTAS,MySQLiteHelper.COLUMN_NOTA_ID+" = "+id,null);
		 
	 }
	 
	 public Nota crear(Nota nota){
		 SimpleDateFormat formato = new SimpleDateFormat(Configuracion.FORMATOFECHA, Locale.US);
		 Date fecha = new Date();
		 
		 nota.setFchCreacion(formato.format(fecha));
		 nota.setFchModificacion(formato.format(fecha));
		 
		 ContentValues values = new ContentValues();
		 values.put(MySQLiteHelper.COLUMN_NOTA_TITULO, nota.getTitulo());
		 values.put(MySQLiteHelper.COLUMN_NOTA_TEXTO, nota.getTexto());
		 values.put(MySQLiteHelper.COLUMN_NOTA_COD_USUARIO, nota.getCodUsuario());
		 values.put(MySQLiteHelper.COLUMN_NOTA_FLG_ENCRIPTADO, nota.getFlgEncriptado());
		 values.put(MySQLiteHelper.COLUMN_NOTA_LLAVE, nota.getLlave());
		 values.put(MySQLiteHelper.COLUMN_NOTA_FCH_CREACION, nota.getFchCreacion()); 
		 values.put(MySQLiteHelper.COLUMN_NOTA_FCH_MODIFICACION, nota.getFchModificacion());		 
		 
		 long insertId = database.insert(MySQLiteHelper.TABLE_NOTAS, null, values);
		 
		 nota = obtenerNotaId(insertId);
		
		 return nota;
	 }
	 
	 public Nota actualizar(Nota nota){
		 Nota notaNew = null;
		 SimpleDateFormat formato = new SimpleDateFormat(Configuracion.FORMATOFECHA, Locale.US);
		 Date fecha = new Date();
		 
		 nota.setFchModificacion(formato.format(fecha));
		 ContentValues values = new ContentValues();
		 values.put(MySQLiteHelper.COLUMN_NOTA_TITULO, nota.getTitulo());
		 values.put(MySQLiteHelper.COLUMN_NOTA_TEXTO, nota.getTexto());
		 values.put(MySQLiteHelper.COLUMN_NOTA_COD_USUARIO, nota.getCodUsuario());
		 values.put(MySQLiteHelper.COLUMN_NOTA_FLG_ENCRIPTADO, nota.getFlgEncriptado());
		 values.put(MySQLiteHelper.COLUMN_NOTA_LLAVE, nota.getLlave());
		 values.put(MySQLiteHelper.COLUMN_NOTA_FCH_CREACION, nota.getFchCreacion()); 
		 values.put(MySQLiteHelper.COLUMN_NOTA_FCH_MODIFICACION, nota.getFchModificacion());	
		 
		 database.update(MySQLiteHelper.TABLE_NOTAS, values, MySQLiteHelper.COLUMN_NOTA_ID +" = "+nota.getId(), null);
		 
		 notaNew=obtenerNotaId(nota.getId());
		 
		 return notaNew;
	 }
	 public Nota obtenerNotaId(long id){
		 Nota nota = null;
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTAS, allColumns, MySQLiteHelper.COLUMN_NOTA_ID +" = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 nota = cursorToNota(cursor);
		 cursor.close();
		 
		 return nota;
		 
	 }
	 
	 public List<Nota> obtenerNotasTitulo(String titulo){
		 List<Nota> ls = new ArrayList<Nota>();
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTAS, allColumns, MySQLiteHelper.COLUMN_NOTA_TITULO +" like %"+titulo+"%",null,null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Nota nota = cursorToNota(cursor);
			 ls.add(nota);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 
	 private Nota cursorToNota(Cursor cursor) {
		    Nota nota = new Nota();
		    nota.setId(cursor.getLong(0));
		    nota.setTitulo(cursor.getString(1));
		    nota.setTexto(cursor.getString(2));
		    nota.setCodUsuario(cursor.getLong(3));
		    nota.setLlave(cursor.getString(4));
		    nota.setFlgEncriptado(cursor.getString(5));
		    nota.setFchCreacion(cursor.getString(6));
		    nota.setFchModificacion(cursor.getString(7));
		    return nota;
	 }	
}

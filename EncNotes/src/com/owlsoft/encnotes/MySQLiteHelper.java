package com.owlsoft.encnotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_NOTAS = "Nota";
	public static final String COLUMN_NOTA_ID = "_id";
	public static final String COLUMN_NOTA_TITULO = "titulo";
	public static final String COLUMN_NOTA_TEXTO = "texto";
	public static final String COLUMN_NOTA_FLG_ENCRIPTADO = "flgEncriptado";
	public static final String COLUMN_NOTA_COD_USUARIO = "codUsuario";
	public static final String COLUMN_NOTA_FCH_CREACION = "fchCreacion";
	public static final String COLUMN_NOTA_FCH_MODIFICACION = "fchModificacion";
	
	public static final String COLUMN_NOTA_LLAVE = "llave";
	
	public static final String TABLE_USUARIO = "usuario";
	public static final String COLUMN_USUARIO_ID = "_id";
	public static final String COLUMN_USUARIO_LOGIN = "login";
	public static final String COLUMN_USUARIO_FIRSTNAME = "firstname";
	public static final String COLUMN_USUARIO_LASTNAME = "lastname";
	public static final String COLUMN_USUARIO_PASSWORD = "password";
	public static final String COLUMN_USUARIO_LLAVE = "llave";
	public static final String COLUMN_USUARIO_PREGUNTA = "pregunta";
	public static final String COLUMN_USUARIO_RESPUESTA = "respuesta";
	public static final String COLUMN_USUARIO_FCH_CREACION = "fchCreacion";
	public static final String COLUMN_USUARIO_FCH_MODIFICACION = "fchModificacion";
	
	
	
	private static final String DATABASE_NAME = "encNotes.db";
	private static final int DATABASE_VERSION = 1;

	

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// crear String de creacion de BD
		
		String CrearBaseDatos =  "create table " + TABLE_NOTAS
				+ "(" + COLUMN_NOTA_ID + " integer primary key autoincrement, "
				+ COLUMN_NOTA_TITULO + " text not null, " + COLUMN_NOTA_TEXTO +
				" text, "+COLUMN_NOTA_FLG_ENCRIPTADO+" text, "+	COLUMN_NOTA_COD_USUARIO+" text, "+
				COLUMN_NOTA_FCH_CREACION+" text, "+COLUMN_NOTA_FCH_MODIFICACION+" text, "+
				COLUMN_NOTA_LLAVE+" text); ";
		
		CrearBaseDatos = CrearBaseDatos + "create table " + TABLE_USUARIO
				+ "(" + COLUMN_USUARIO_ID + " integer primary key autoincrement, "
				+ COLUMN_USUARIO_LOGIN + " text not null, " + COLUMN_USUARIO_FIRSTNAME +
				" text, "+COLUMN_USUARIO_LASTNAME+" text, "+	COLUMN_USUARIO_PASSWORD+" text, "+
				COLUMN_USUARIO_LLAVE+" text, "+COLUMN_USUARIO_PREGUNTA+" text, "+
				COLUMN_USUARIO_FCH_CREACION+" text, "+COLUMN_USUARIO_FCH_MODIFICACION+" text); ";
		
		db.execSQL(CrearBaseDatos);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
		onCreate(db);

	}

}

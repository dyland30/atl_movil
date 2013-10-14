package com.atl.atlmovil.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TB_USUARIO = "Usuario";
	
	private static final String C_TB_USUARIO = "CREATE TABLE Usuario " +
			"(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , login TEXT UNIQUE, " +
			"clave TEXT, nombres TEXT, apellidos TEXT, dni TEXT);";
	
	private static final String INS_TB_USUARIO = "insert into Usuario (login,clave,nombres,apellidos,dni)  values('demo','demo','demo','demo','12345678');";
	private static final String DATABASE_NAME = "atlmovil.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = C_TB_USUARIO;

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		db.execSQL(INS_TB_USUARIO);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TB_USUARIO);
		onCreate(db);

	}

}
package com.atl.atlmovil.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TB_USUARIO = "Usuario";
	public static final String TB_VISITA = "Visita";
	public static final String TB_TIPO_VISITA = "TipoVisita";
	public static final String TB_ESTADO_VISITA = "EstadoVisita";
	public static final String TB_GRUPO = "Grupo";
	public static final String TB_CLIENTE = "Cliente";
	public static final String TB_EMPLEADO = "Empleado";
	public static final String TB_LINEA_CREDITO = "LineaCredito";
	public static final String TB_PERSONA = "Persona";
	public static final String TB_TIPO_DOCUMENTO = "TipoDocumento";
	
	
	private static final String C_TB_USUARIO = " CREATE TABLE Usuario " +
			"(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , login TEXT UNIQUE, " +
			"clave TEXT, nombres TEXT, apellidos TEXT, dni TEXT);";
	
	private static final String C_TB_VISITA = " CREATE TABLE Visita (codigoVisita INTEGER PRIMARY KEY NOT NULL, " +
			"codigoCliente INTEGER, codigoEmpleado INTEGER, codigoEstadoVisita INTEGER, codigoTipoVIsita INTEGER," +
			"fechaVisita DATETIME, horaInicioVisita DATETIME, horaFinalVisita DATETIME);";
	
	private static final String C_TB_TIPO_VISITA = " CREATE TABLE TipoVisita (codigoTipoVisita INTEGER PRIMARY KEY NOT NULL, " +
			"descripcionTipoVisita TEXT);";
	
	private static final String C_TB_ESTADO_VISITA = " CREATE TABLE EstadoVisita (codigoEstadoVisita INTEGER PRIMARY KEY NOT NULL, " +
			"descripcionEstadoVisita TEXT);";
	
	private static final String C_TB_GRUPO = " CREATE TABLE Grupo (codigoGrupo INTEGER PRIMARY KEY NOT NULL, " +
			"nombreGrupo TEXT, titularGrupo TEXT);";
	
	private static final String C_TB_CLIENTE = " CREATE TABLE Cliente (codigoCliente INTEGER PRIMARY KEY NOT NULL, " +
			"codigoEmpleado INTEGER, codigoGrupo INTEGER, codigoPersona INTEGER, direccionEntregaCliente TEXT, representanteCliente TEXT, " +
			"celularCliente TEXT);";
	
	private static final String C_TB_EMPLEADO = " CREATE TABLE Empleado (codigoEmpleado INTEGER PRIMARY KEY NOT NULL, " +
			"codigoPersona INTEGER, areaEmpleado TEXT, cargoEmpleado TEXT, fechaCeseEmpleado DATETIME, fechaIngresoEmpleado DATETIME, jefeEmpleado INTEGER);";
	
	private static final String C_TB_LINEA_CREDITO = " CREATE TABLE LineaCredito (codigoLineaCredito INTEGER PRIMARY KEY NOT NULL, " +
			"codigoGrupo INTEGER, bloqueoLineaCredito INTEGER, fechaAsignacionLineaCredito DATETIME, fechaVencimientoLineaCredito DATETIME," +
			"importeComprometidoLineaCredito NUMERIC, importeLineaCredito NUMERIC, importeUtilizadoLineaCredito NUMERIC);";
	
	private static final String C_TB_PERSONA = " CREATE TABLE Persona (codigoPersona INTEGER PRIMARY KEY NOT NULL, " +
			"codigoTipoDocumento INTEGER, nombrePersona TEXT, direccionPersona TEXT, tipoPersona TEXT);";
	
	
	private static final String C_TB_TIPO_DOCUMENTO = " CREATE TABLE TipoDocumento (codigoTipoDocumento INTEGER PRIMARY KEY NOT NULL, " +
			"nombreTipoDocumento TEXT, siglaTipoDocumento TEXT);";
	
	
	private static final String INS_TB_USUARIO = "insert into Usuario (login,clave,nombres,apellidos,dni)  values('demo','demo','demo'," +
			"'demo','12345678');";
	
	private static final String INS_TB_TIPO_VISITA = "insert into TipoVisita(codigoTipoVisita, descripcionTipoVisita) values ('1','Presencial');" +
													 "insert into TipoVisita(codigoTipoVisita, descripcionTipoVisita) values ('3','Telefónica');";
	
	private static final String INS_TB_ESTADO_VISITA = "insert into TipoVisita(codigoEstadoVisita, descripcionEstadoVisita) values ('1','Programada');" +
													   "insert into TipoVisita(codigoEstadoVisita, descripcionEstadoVisita) values ('2','En Visita'); " +
													   "insert into TipoVisita(codigoEstadoVisita, descripcionEstadoVisita) values ('3','Atendida');" +
													   "insert into TipoVisita(codigoEstadoVisita, descripcionEstadoVisita) values ('4','Anulada');";

			
	
	private static final String DATABASE_NAME = "atlmovil.db";
	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_CREATE = C_TB_USUARIO+C_TB_VISITA +C_TB_TIPO_VISITA+C_TB_ESTADO_VISITA+C_TB_GRUPO+C_TB_CLIENTE+C_TB_EMPLEADO+
			C_TB_LINEA_CREDITO+C_TB_PERSONA+C_TB_TIPO_DOCUMENTO;

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
		db.execSQL("DROP TABLE IF EXISTS " + TB_VISITA);
		db.execSQL("DROP TABLE IF EXISTS " + TB_TIPO_VISITA);
		db.execSQL("DROP TABLE IF EXISTS " + TB_ESTADO_VISITA);
		db.execSQL("DROP TABLE IF EXISTS " + TB_GRUPO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_CLIENTE);
		db.execSQL("DROP TABLE IF EXISTS " + TB_EMPLEADO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_LINEA_CREDITO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_PERSONA);
		db.execSQL("DROP TABLE IF EXISTS " + TB_TIPO_DOCUMENTO);
		onCreate(db);

	}

}
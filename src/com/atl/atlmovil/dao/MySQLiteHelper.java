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
	//-----------------------------------------------------------------
	
	public static final String TB_PRODUCTO = "Producto";
	public static final String TB_TALLA = "Talla";
	public static final String TB_PEDIDO = "Pedido";
	public static final String TB_FORMA_PAGO = "FormaPago";
	public static final String TB_DETALLE_PEDIDO = "DetallePedido";
	public static final String TB_TALLA_PEDIDO = "TallaPedido";
	public static final String TB_PRODUCTO_FORMA_PAGO = "ProductoFormaPago";
	//------------------------------------------------------------------
	
	
	
	private static final String C_TB_USUARIO = " CREATE TABLE Usuario " +
			"(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , login TEXT UNIQUE, " +
			"clave TEXT, nombres TEXT, apellidos TEXT, dni TEXT) ; ";
	
	private static final String C_TB_VISITA = " CREATE TABLE Visita (codigoVisita INTEGER PRIMARY KEY NOT NULL, " +
			"codigoCliente INTEGER, codigoEmpleado INTEGER, codigoEstadoVisita INTEGER, codigoTipoVIsita INTEGER," +
			"fechaVisita DATETIME, horaInicioVisita DATETIME, horaFinalVisita DATETIME) ; ";
	
	private static final String C_TB_TIPO_VISITA = " CREATE TABLE TipoVisita (codigoTipoVisita INTEGER PRIMARY KEY NOT NULL, " +
			"descripcionTipoVisita TEXT) ; ";
	
	private static final String C_TB_ESTADO_VISITA = " CREATE TABLE EstadoVisita (codigoEstadoVisita INTEGER PRIMARY KEY NOT NULL, " +
			"descripcionEstadoVisita TEXT) ; ";
	
	private static final String C_TB_GRUPO = " CREATE TABLE Grupo (codigoGrupo INTEGER PRIMARY KEY NOT NULL, " +
			"nombreGrupo TEXT, titularGrupo TEXT) ; ";
	
	private static final String C_TB_CLIENTE = " CREATE TABLE Cliente (codigoCliente INTEGER PRIMARY KEY NOT NULL, " +
			"codigoEmpleado INTEGER, codigoGrupo INTEGER, codigoPersona INTEGER, direccionEntregaCliente TEXT, representanteCliente TEXT, " +
			"celularCliente TEXT) ; ";
	
	private static final String C_TB_EMPLEADO = " CREATE TABLE Empleado (codigoEmpleado INTEGER PRIMARY KEY NOT NULL, " +
			"codigoPersona INTEGER, areaEmpleado TEXT, cargoEmpleado TEXT, fechaCeseEmpleado DATETIME, fechaIngresoEmpleado DATETIME, jefeEmpleado INTEGER) ; ";
	
	private static final String C_TB_LINEA_CREDITO = " CREATE TABLE LineaCredito (codigoLineaCredito INTEGER PRIMARY KEY NOT NULL, " +
			"codigoGrupo INTEGER, bloqueoLineaCredito INTEGER, fechaAsignacionLineaCredito DATETIME, fechaVencimientoLineaCredito DATETIME," +
			"importeComprometidoLineaCredito NUMERIC, importeLineaCredito NUMERIC, importeUtilizadoLineaCredito NUMERIC) ; ";
	
	private static final String C_TB_PERSONA = " CREATE TABLE Persona (codigoPersona INTEGER PRIMARY KEY NOT NULL, " +
			"codigoTipoDocumento INTEGER, nombrePersona TEXT, direccionPersona TEXT, documentoPersona TEXT, tipoPersona TEXT) ; ";
	
	
	private static final String C_TB_TIPO_DOCUMENTO = " CREATE TABLE TipoDocumento (codigoTipoDocumento INTEGER PRIMARY KEY NOT NULL, " +
			"nombreTipoDocumento TEXT, siglaTipoDocumento TEXT) ; ";
	
	
	private static final String INS_TB_USUARIO = "insert into Usuario (login,clave,nombres,apellidos,dni)  values('demo','demo','demo'," +
			"'demo','12345678');";
	
	
	private static final String C_TB_PRODUCTO = "CREATE TABLE Producto (codigoProducto INTEGER PRIMARY KEY NOT NULL, " +
			"calidadProducto TEXT, colorProducto TEXT, descripcionProducto TEXT, materialProducto TEXT, precioPRoducto NUMERIC, sexoProducto TEXT) ;";
	
	private static final String C_TB_TALLA = "CREATE TABLE Talla (codigoProducto INTEGER  NOT NULL, numeroTalla INTEGER NOT NULL," +
			"stockDisponibleTalla INTEGER, PRIMARY KEY(codigoProducto,numeroTalla))  ;";
	
	private static final String C_TB_PEDIDO = "CREATE TABLE Pedido (id INTEGER PRIMARY KEY NOT NULL, codigoPedido INTEGER, codigoVisita INTEGER, " +
			"codigoFormaPago INTEGER, aceptaRetencionPedido INTEGER, direccionDeEnvio TEXT, empresaTransporte TEXT, estadoPedido TEXT, estaRetenidoPedido INTEGER," +
			"estaSincronizado INTEGER, fechaIngresoPedido DATETIME, importePedido NUMERIC, instruccionesEspeciales TEXT, lineaReservadaPedido NUMERIC)  ;";
	
	private static final String C_TB_FORMA_PAGO  = " CREATE TABLE FormaPago (codigoFormaPago INTEGER PRIMARY KEY NOT NULL, " +
			"descripcionFormaPago TEXT) ; ";
	private static final String C_TB_DETALLE_PEDIDO  = " CREATE TABLE DetallePedido (codigoPedido INTEGER NOT NULL, " +
			"codigoProducto INTEGER NOT NULL, precioUnitario NUMERIC, PRIMARY KEY(codigoPedido, codigoProducto)) ; ";

	private static final String C_TB_TALLA_PEDIDO = " CREATE TABLE TallaPedido (codigoPedido INTEGER NOT NULL, " +
			"codigoProducto INTEGER NOT NULL, numeroTalla INTEGER NOT NULL, cantidad INTEGER, PRIMARY KEY(codigoPedido,codigoProducto,numeroTalla)) ; ";

	
	private static final String C_TB_PRODUCTO_FORMA_PAGO  = " CREATE TABLE ProductoFormaPago (codigoFormaPago INTEGER NOT NULL, " +
			"codigoProducto INTEGER, precio NUMERIC, PRIMARY KEY(codigoFormaPago,codigoProducto)) ; ";
	
	private static final String DATABASE_NAME = "atlmovil.db";
	private static final int DATABASE_VERSION = 9;

	private static final String DATABASE_CREATE = C_TB_USUARIO+C_TB_VISITA +C_TB_TIPO_VISITA+C_TB_ESTADO_VISITA+C_TB_GRUPO+C_TB_CLIENTE+C_TB_EMPLEADO+
			C_TB_LINEA_CREDITO+C_TB_PERSONA+C_TB_TIPO_DOCUMENTO ;

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Log.w("info",DATABASE_CREATE);
		//db.execSQL(DATABASE_CREATE);
		db.execSQL(C_TB_USUARIO);
		db.execSQL(C_TB_VISITA);
		db.execSQL(C_TB_TIPO_VISITA);
		db.execSQL(C_TB_ESTADO_VISITA);
		db.execSQL(C_TB_GRUPO);
		db.execSQL(C_TB_CLIENTE);
		db.execSQL(C_TB_EMPLEADO);
		db.execSQL(C_TB_LINEA_CREDITO);
		db.execSQL(C_TB_PERSONA);
		db.execSQL(C_TB_TIPO_DOCUMENTO);
		
		db.execSQL(C_TB_PRODUCTO);
		db.execSQL(C_TB_TALLA);
		db.execSQL(C_TB_PEDIDO);
		db.execSQL(C_TB_FORMA_PAGO);
		db.execSQL(C_TB_DETALLE_PEDIDO);
		db.execSQL(C_TB_TALLA_PEDIDO);
		db.execSQL(C_TB_PRODUCTO_FORMA_PAGO);
		
		
		
		
		Log.w("info",DATABASE_CREATE);
		db.execSQL(INS_TB_USUARIO);
		//Log.w("info",INS_TB_TIPO_VISITA);
		db.execSQL(" insert into TipoVisita (codigoTipoVisita, descripcionTipoVisita) values ('1','Presencial'); ");
		db.execSQL(" insert into TipoVisita (codigoTipoVisita, descripcionTipoVisita) values ('2','Telefónica');");
		
		//Log.w("info",INS_TB_ESTADO_VISITA);
		db.execSQL("insert into EstadoVisita (codigoEstadoVisita, descripcionEstadoVisita) values ('1','Programada'); ");
		db.execSQL("insert into EstadoVisita (codigoEstadoVisita, descripcionEstadoVisita) values ('2','En Visita'); ");
		db.execSQL("insert into EstadoVisita (codigoEstadoVisita, descripcionEstadoVisita) values ('3','Atendida') ; ");
		db.execSQL("insert into EstadoVisita (codigoEstadoVisita, descripcionEstadoVisita) values ('4','Anulada'); ");
		
		//tipos de documento
		db.execSQL("insert into TipoDocumento (codigoTipoDocumento, nombreTipoDocumento, siglaTipoDocumento) values ('1','Documento Nacional de Identidad','DNI'); ");
		db.execSQL("insert into TipoDocumento (codigoTipoDocumento, nombreTipoDocumento, siglaTipoDocumento) values ('2','Pasaporte','PASAPORTE'); ");
		db.execSQL("insert into TipoDocumento (codigoTipoDocumento, nombreTipoDocumento, siglaTipoDocumento) values ('3','Carnet de Extranjeria','CE'); ");

		//Personas
		db.execSQL("insert into Persona (codigoPersona, codigoTipoDocumento,nombrePersona, direccionPersona,documentoPersona, tipoPersona) values ('1','1','Ramirez Lesly','Tomas Marsano 457 - SURCO', '47896574', 'NATURAL'); ");
		db.execSQL("insert into Persona (codigoPersona, codigoTipoDocumento,nombrePersona, direccionPersona,documentoPersona, tipoPersona) values ('2','1','Marco Flores','Universitaria 1474 - SAN MIGUEL','12359874', 'NATURAL'); ");
		db.execSQL("insert into Persona (codigoPersona, codigoTipoDocumento,nombrePersona, direccionPersona,documentoPersona, tipoPersona) values ('3','1','Marlon Guadalupe','Universitaria 1111 - SAN MIGUEL','237896587', 'NATURAL'); ");
		//Empleados
		db.execSQL("insert into Empleado (codigoEmpleado,codigoPersona, areaEmpleado,cargoEmpleado, fechaCeseEmpleado, fechaIngresoEmpleado, jefeEmpleado) values ('1','3','LIMA-CENTRO','VENDEDOR','2100-12-31 00:00', '2005-01-01 00:00','1'); ");
		
		// Grupos de Clientes
		db.execSQL("insert into Grupo (codigoGrupo, nombreGrupo, titularGrupo) values ('1','Grupo A', 'Ramirez Lesly'); ");
		
		//Cliente 
		db.execSQL("insert into Cliente (codigoCliente, codigoGrupo, codigoEmpleado, codigoPersona, direccionEntregaCliente, representanteCliente) " +
				"values ('1','1', '1','1','Tomas Marsano 457 - SURCO', 'Ramirez Lesly'); ");
		
		db.execSQL("insert into Cliente (codigoCliente, codigoGrupo, codigoEmpleado, codigoPersona, direccionEntregaCliente, representanteCliente) " +
				"values ('2','1', '1','2','Universitaria 1474 - SAN MIGUEL', 'Marco Flores'); ");
		
		db.execSQL("insert into Visita (codigoVisita,codigoCliente, codigoEmpleado, codigoEstadoVisita,codigoTipoVisita, fechaVisita, horaInicioVIsita, horaFinalVisita) " +
				"values ('1','1','1','1','1','2013-10-16 00:00', '2013-10-16 10:00', '2013-10-16 11:00'); ");
		db.execSQL("insert into Visita (codigoVisita,codigoCliente, codigoEmpleado, codigoEstadoVisita,codigoTipoVisita, fechaVisita, horaInicioVIsita, horaFinalVisita) " +
				"values ('2','2','1','1','1','2013-10-16 00:00', '2013-10-16 13:00', '2013-10-16 14:00'); ");
		db.execSQL("insert into Visita (codigoVisita,codigoCliente, codigoEmpleado, codigoEstadoVisita,codigoTipoVisita, fechaVisita, horaInicioVIsita, horaFinalVisita) " +
				"values ('3','1','1','1','1','2013-10-17 00:00', '2013-10-17 13:00', '2013-10-16 14:00'); ");
		
		
		
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
		
		db.execSQL("DROP TABLE IF EXISTS " + TB_PRODUCTO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_TALLA);
		db.execSQL("DROP TABLE IF EXISTS " + TB_PEDIDO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_FORMA_PAGO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_DETALLE_PEDIDO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_TALLA_PEDIDO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_PRODUCTO_FORMA_PAGO);
		
		
		onCreate(db);

	}

}
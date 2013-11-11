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
	public static final String TB_EMPRESA_CARGA = "EmpresaCarga";
	public static final String TB_PRODUCTO_TALLA = "ProductoTalla";
	
	// COBRANZA
	public static final String TB_COBRANZA = "Cobranza";
	public static final String TB_DOCUMENTO_PAGO = "DocumentoPago";
	public static final String TB_AMORTIZACION = "Amortizacion";
	public static final String TB_MEDIO_PAGO = "MedioPago";
	public static final String TB_BANCO = "Banco";
	public static final String TB_DEPOSITO = "Deposito";
	public static final String TB_VOUCHER = "Voucher";

	
	//------------------------------------------------------------------
	private static final int DATABASE_VERSION = 25;
	private static final String DATABASE_NAME = "atlmovil.db";
	
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
	
	
	//private static final String INS_TB_USUARIO = "insert into Usuario (login,clave,nombres,apellidos,dni)  values('demo','demo','demo'," +
	//		"'demo','12345678');";
	
	
	private static final String C_TB_PRODUCTO = "CREATE TABLE Producto (codigoProducto INTEGER PRIMARY KEY NOT NULL, " +
			"calidadProducto TEXT, colorProducto TEXT, descripcionProducto TEXT, materialProducto TEXT, precioProducto NUMERIC, sexoProducto TEXT) ;";
	
	private static final String C_TB_TALLA = "CREATE TABLE Talla (codigoProducto INTEGER  NOT NULL, numeroTalla INTEGER NOT NULL," +
			"stockDisponibleTalla INTEGER, PRIMARY KEY(codigoProducto,numeroTalla))  ;";
	
	private static final String C_TB_PEDIDO = "CREATE TABLE Pedido (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, codigoPedido INTEGER, codigoVisita INTEGER, " +
			"codigoFormaPago INTEGER, aceptaRetencionPedido INTEGER, direccionDeEnvio TEXT, empresaTransporte TEXT, estadoPedido TEXT, estaRetenidoPedido INTEGER," +
			"estaSincronizado INTEGER, fechaIngresoPedido DATETIME, importePedido NUMERIC, instruccionesEspeciales TEXT, lineaReservadaPedido NUMERIC,codigoEmpresaCarga INTEGER)  ;";
	
	private static final String C_TB_FORMA_PAGO  = " CREATE TABLE FormaPago (codigoFormaPago INTEGER PRIMARY KEY NOT NULL, " +
			"descripcionFormaPago TEXT) ; ";
	private static final String C_TB_DETALLE_PEDIDO  = " CREATE TABLE DetallePedido (idPedido INTEGER NOT NULL, " +
			"codigoProducto INTEGER NOT NULL, precioUnitario NUMERIC, PRIMARY KEY(idPedido, codigoProducto)) ; ";

	private static final String C_TB_TALLA_PEDIDO = " CREATE TABLE TallaPedido (idPedido INTEGER NOT NULL, " +
			"codigoProducto INTEGER NOT NULL, numeroTalla INTEGER NOT NULL, cantidad INTEGER, PRIMARY KEY(idPedido,codigoProducto,numeroTalla)) ; ";

	
	private static final String C_TB_PRODUCTO_FORMA_PAGO  = " CREATE TABLE ProductoFormaPago (codigoFormaPago INTEGER NOT NULL, " +
			"codigoProducto INTEGER, precio NUMERIC, PRIMARY KEY(codigoFormaPago,codigoProducto)) ; ";
	
	private static final String C_TB_EMPRESA_CARGA = " CREATE TABLE EmpresaCarga (codigoEmpresaCarga INTEGER PRIMARY KEY NOT NULL, " +
			"nombreEmpresaCarga TEXT, direccionEmpresaCarga TEXT, horarioEmpresaCarga TEXT, rucEmpresaCarga TEXT ) ; ";
	
	
	
	private static final String C_TB_COBRANZA = "CREATE TABLE Cobranza (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"codigoCobranza INTEGER, codigoMedioPago INTEGER, importeCobranza NUMERIC, fechaCobranza DATETIME, estaSincronizado INTEGER, " +
			"estadoCobranza TEXT, codigoVisita INTEGER,importePendiente NUMERIC, esAutoDistribuido INTEGER); ";
	
	private static final String C_TB_DOCUMENTO_PAGO = "CREATE TABLE DocumentoPago (codigoDocumentoPago INTEGER PRIMARY KEY NOT NULL, " +
			"fechaEmisionDocumentoPago DATETIME, fechaVencimientoDocumentoPago DATETIME, importeAmortizadoDocumentoPago NUMERIC," +
			"importeDescontadoDocumentoPago NUMERIC, importeIgvDocumentoPago NUMERIC, importeOriginalDocumentoPago NUMERIC," +
			"importePendienteDocumentoPago NUMERIC, plazoDocumentoPago INTEGER, tipoDocumentoPago TEXT, ReferenciaDocumentoPago TEXT, codigoCliente INTEGER) ; ";
	
	
	private static final String C_TB_AMORTIZACION = "CREATE TABLE Amortizacion (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"idCobranza INTEGER, codigoDocumentoPago INTEGER, importeAmortizacion NUMERIC, anotacionAmortizacion TEXT) ; ";
	
	private static final String C_TB_MEDIO_PAGO = "CREATE TABLE MedioPago (codigoMedioPago INTEGER PRIMARY KEY NOT NULL," +
			"descripcionMedioPago TEXT) ; ";
	
	private static final String C_TB_BANCO = "CREATE TABLE Banco (codigoBanco INTEGER PRIMARY KEY NOT NULL," +
			"nombreBanco TEXT) ; ";
	
	private static final String C_TB_DEPOSITO = "CREATE TABLE Deposito (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"codigoDeposito INTEGER, codigoCobranza INTEGER,codigoMedioPago INTEGER, bancoDeposito INTEGER, clienteDeposito INTEGER, " +
			"voucherDeposito INTEGER, agenciaDeposito TEXT,terminalDeposito TEXT, estadoDeposito INTEGER, fechaDeposito DATETIME, " +
			"importeDeposito NUMERIC ) ; ";
	
	private static final String C_TB_VOUCHER= "CREATE TABLE Voucher (codigoVoucher INTEGER PRIMARY KEY NOT NULL," +
			"bancoVoucher INTEGER, clienteVoucher INTEGER, agenciaVoucher TEXT, estadoVoucher INTEGER, fechaVoucher DATETIME," +
			"importeVoucher NUMERIC, medioVoucher TEXT, terminalVoucher TEXT) ; ";
	
	private static final String DATABASE_CREATE = C_TB_USUARIO+C_TB_VISITA +C_TB_TIPO_VISITA+C_TB_ESTADO_VISITA+C_TB_GRUPO+C_TB_CLIENTE+C_TB_EMPLEADO+
			C_TB_LINEA_CREDITO+C_TB_PERSONA+C_TB_TIPO_DOCUMENTO+C_TB_EMPRESA_CARGA ;

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
		db.execSQL(C_TB_EMPRESA_CARGA);
		
		
		db.execSQL(C_TB_COBRANZA);
		db.execSQL(C_TB_DOCUMENTO_PAGO);
		db.execSQL(C_TB_AMORTIZACION);
		db.execSQL(C_TB_MEDIO_PAGO);
		db.execSQL(C_TB_BANCO);
		db.execSQL(C_TB_DEPOSITO);
		db.execSQL(C_TB_VOUCHER);
		
		
		Log.w("info",DATABASE_CREATE);
		//db.execSQL(INS_TB_USUARIO);
		//Log.w("info",INS_TB_TIPO_VISITA);
		/*
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
		 */
		//Personas
		/*
		db.execSQL("insert into Persona (codigoPersona, codigoTipoDocumento,nombrePersona, direccionPersona,documentoPersona, tipoPersona) values ('1','1','Juan Jimenez','Tomas Marsano 457 - SURCO', '47896574', 'NATURAL'); ");
		db.execSQL("insert into Persona (codigoPersona, codigoTipoDocumento,nombrePersona, direccionPersona,documentoPersona, tipoPersona) values ('2','1','Marco Flores','Universitaria 1474 - SAN MIGUEL','12359874', 'NATURAL'); ");
		db.execSQL("insert into Persona (codigoPersona, codigoTipoDocumento,nombrePersona, direccionPersona,documentoPersona, tipoPersona) values ('3','1','Marlon Guadalupe','Universitaria 1111 - SAN MIGUEL','237896587', 'NATURAL'); ");
		*/
		//Empleados
		//db.execSQL("insert into Empleado (codigoEmpleado,codigoPersona, areaEmpleado,cargoEmpleado, fechaCeseEmpleado, fechaIngresoEmpleado, jefeEmpleado) values ('1','3','LIMA-CENTRO','VENDEDOR','2100-12-31 00:00', '2005-01-01 00:00','1'); ");
		
		// Grupos de Clientes
		//db.execSQL("insert into Grupo (codigoGrupo, nombreGrupo, titularGrupo) values ('1','Grupo A', 'Ramirez Lesly'); ");
		
		//Cliente 
		/*
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
		*/
		// forma de pago
		/*
		db.execSQL("insert into FormaPago (codigoFormaPago, descripcionFormaPago) values ('1','Factura 90'); ");
		db.execSQL("insert into FormaPago (codigoFormaPago, descripcionFormaPago) values ('2','Contado'); ");
		db.execSQL("insert into FormaPago (codigoFormaPago, descripcionFormaPago) values ('3','Adelantado'); ");
		*/
		/*
		// empresa de carga
		db.execSQL("insert into EmpresaCarga (codigoEmpresaCarga, nombreEmpresaCarga, direccionEmpresaCarga, horarioEmpresaCarga, rucEmpresaCarga)" +
				" values ('1','Transportes Marin SRL','Bausate y Mesa 467, La Victoria', 'Lu Do 24 hrs', '10478965780'); ");
		db.execSQL("insert into EmpresaCarga (codigoEmpresaCarga, nombreEmpresaCarga, direccionEmpresaCarga, horarioEmpresaCarga, rucEmpresaCarga)" +
				" values ('2','Transportes Martinez SRL','Alfredo Mendiola 1247, Los Olivos', 'Lu Do 24 hrs', '25478965780'); ");
		// Pedidos
		// codigo de pedido es 0 porque no esta asignado por el sistema central
		db.execSQL("insert into Pedido (codigoPedido, codigoVisita , " +
			"codigoFormaPago, aceptaRetencionPedido, direccionDeEnvio, empresaTransporte, estadoPedido, estaRetenidoPedido," +
			"estaSincronizado, fechaIngresoPedido, importePedido, instruccionesEspeciales, lineaReservadaPedido,codigoEmpresaCarga) " +
			"values ('0','1','1','1','Tomas Marsano 457 - SURCO','Martinez','R','0','0','2013-10-16 10:00','5200','Ref: ovalo higuereta','0','2'); ");
		
		db.execSQL("insert into Pedido (codigoPedido, codigoVisita , " +
				"codigoFormaPago, aceptaRetencionPedido, direccionDeEnvio, empresaTransporte, estadoPedido, estaRetenidoPedido," +
				"estaSincronizado, fechaIngresoPedido, importePedido, instruccionesEspeciales, lineaReservadaPedido,codigoEmpresaCarga) " +
				"values ('0','1','1','1','Tomas Marsano 457 - SURCO','Martinez','R','0','0','2013-10-18 10:00','4700','Ref: ovalo higuereta','0','2'); ");
		
		db.execSQL("insert into Pedido (codigoPedido, codigoVisita , " +
				"codigoFormaPago, aceptaRetencionPedido, direccionDeEnvio, empresaTransporte, estadoPedido, estaRetenidoPedido," +
				"estaSincronizado, fechaIngresoPedido, importePedido, instruccionesEspeciales, lineaReservadaPedido,codigoEmpresaCarga) " +
				"values ('0','1','1','1','Tomas Marsano 457 - SURCO','Martinez','R','0','0','2013-10-20 10:00','7000','Ref: ovalo higuereta','0','2'); ");
		
		*/
		// Productos
		/*
		db.execSQL("insert into Producto (codigoProducto,calidadProducto, colorProducto, descripcionProducto," +
				" materialProducto, precioProducto, sexoProducto) " +
				"values ('1','Buena','Negro','Zapato A', 'Cuero','89','Masculino'); ");
		
		db.execSQL("insert into Producto (codigoProducto,calidadProducto, colorProducto, descripcionProducto," +
				" materialProducto, precioProducto, sexoProducto) " +
				"values ('2','Buena','Negro','Zapato B', 'Cuero','100','Femenino'); ");
		
		db.execSQL("insert into Producto (codigoProducto,calidadProducto, colorProducto, descripcionProducto," +
				" materialProducto, precioProducto, sexoProducto) " +
				"values ('3','Buena','Negro','Zapato C', 'Cuero','120','Masculino'); ");
		*/
		// Producto Forma Pago
		/*
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('1','1','92'); ");
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('1','2','105'); ");
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('1','3','125'); ");
		
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('2','1','89'); ");
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('2','2','100'); ");
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('2','3','120'); ");
		
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('3','1','85'); ");
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('3','2','95'); ");
		db.execSQL("insert into ProductoFormaPago (codigoFormaPago,codigoProducto, precio) " +
				"values ('3','3','115'); ");
		*/
		// Talla
		/*
		db.execSQL("insert into Talla (codigoProducto, numeroTalla,stockDisponibleTalla) " +
				"values('1','39',20)");
		db.execSQL("insert into Talla (codigoProducto, numeroTalla,stockDisponibleTalla) " +
				"values('1','41',15)");
		db.execSQL("insert into Talla (codigoProducto, numeroTalla,stockDisponibleTalla) " +
				"values('2','39',20)");
		db.execSQL("insert into Talla (codigoProducto, numeroTalla,stockDisponibleTalla) " +
				"values('2','40',10)");
		db.execSQL("insert into Talla (codigoProducto, numeroTalla,stockDisponibleTalla) " +
				"values('3','38',5)");
		db.execSQL("insert into Talla (codigoProducto, numeroTalla,stockDisponibleTalla) " +
				"values('3','40',10)");
		
		*/
		/*
		// Detalles de Pedidos
		db.execSQL("insert into DetallePedido (idPedido,codigoProducto, precioUnitario) " +
				"values ('1','1','150'); ");
		db.execSQL("insert into DetallePedido (idPedido,codigoProducto, precioUnitario) " +
				"values ('1','2','200'); ");
		db.execSQL("insert into DetallePedido (idPedido,codigoProducto, precioUnitario) " +
				"values ('1','3','120'); ");
		
		// Tallas Pedidos
		db.execSQL("insert into TallaPedido (idPedido , codigoProducto, numeroTalla, cantidad) " +
				"values ('1','1','39','5'); ");
		db.execSQL("insert into TallaPedido (idPedido , codigoProducto, numeroTalla, cantidad) " +
				"values ('1','1','41','5'); ");
		db.execSQL("insert into TallaPedido (idPedido , codigoProducto, numeroTalla, cantidad) " +
				"values ('1','2','39','5'); ");
		db.execSQL("insert into TallaPedido (idPedido , codigoProducto, numeroTalla, cantidad) " +
				"values ('1','2','40','5'); ");
		db.execSQL("insert into TallaPedido (idPedido , codigoProducto, numeroTalla, cantidad) " +
				"values ('1','3','38','2'); ");
		db.execSQL("insert into TallaPedido (idPedido , codigoProducto, numeroTalla, cantidad) " +
				"values ('1','3','40','5'); ");
		*/
		//Medios de Pago
		// INTEGER PRIMARY KEY NOT NULL," +
		//" TEXT
		/*
		db.execSQL("insert into MedioPago (codigoMedioPago , descripcionMedioPago) " +
				"values ('1','Voucher'); ");
		db.execSQL("insert into MedioPago (codigoMedioPago , descripcionMedioPago) " +
				"values ('2','Efectivo'); ");
		db.execSQL("insert into MedioPago (codigoMedioPago , descripcionMedioPago) " +
				"values ('3','Cheque'); ");
		
		*/
		/*
		// Cobranzas
		// "","","","", "","", ""
		db.execSQL("insert into Cobranza (id , codigoCobranza, codigoMedioPago, importeCobranza, fechaCobranza, estaSincronizado, estadoCobranza, codigoVisita, importePendiente,esAutoDistribuido ) " +
				"values ('1','0','1','500.00','2013-10-30 00:00','0','Registrado', '1', '500.00','0'); ");
		
		db.execSQL("insert into Cobranza (id , codigoCobranza, codigoMedioPago, importeCobranza, fechaCobranza, estaSincronizado, estadoCobranza, codigoVisita, importePendiente,esAutoDistribuido ) " +
				"values ('2','0','1','450.00','2013-10-30 00:00','0','Registrado','1','450.00','0'); ");
		
		db.execSQL("insert into Cobranza (id , codigoCobranza, codigoMedioPago, importeCobranza, fechaCobranza, estaSincronizado, estadoCobranza, codigoVisita, importePendiente,esAutoDistribuido ) " +
				"values ('3','0','1','600.00','2013-10-30 00:00','0','Registrado','1','600.00','0'); ");
		
		*/
		/*
		//Documentos de pago
		//cliente 1
		db.execSQL("insert into DocumentoPago (codigoDocumentoPago , fechaEmisionDocumentoPago, fechaVencimientoDocumentoPago, importeAmortizadoDocumentoPago, " +
				"importeDescontadoDocumentoPago, importeIgvDocumentoPago, importeOriginalDocumentoPago, importePendienteDocumentoPago, plazoDocumentoPago, tipoDocumentoPago, ReferenciaDocumentoPago, codigoCliente  ) " +
				"values ('1','2013-10-30 00:00','2013-12-31 00:00', '0','0','100.80','560.00','560.00','60','FACTURA','F001-000000432','1'); ");
		db.execSQL("insert into DocumentoPago (codigoDocumentoPago , fechaEmisionDocumentoPago, fechaVencimientoDocumentoPago, importeAmortizadoDocumentoPago, " +
				"importeDescontadoDocumentoPago, importeIgvDocumentoPago, importeOriginalDocumentoPago, importePendienteDocumentoPago, plazoDocumentoPago, tipoDocumentoPago, ReferenciaDocumentoPago, codigoCliente  ) " +
				"values ('2','2013-10-30 00:00','2013-12-31 00:00', '0','0','288.00','1600.00','800.00','60','FACTURA','F001-000000433','1'); ");
		db.execSQL("insert into DocumentoPago (codigoDocumentoPago , fechaEmisionDocumentoPago, fechaVencimientoDocumentoPago, importeAmortizadoDocumentoPago, " +
				"importeDescontadoDocumentoPago, importeIgvDocumentoPago, importeOriginalDocumentoPago, importePendienteDocumentoPago, plazoDocumentoPago, tipoDocumentoPago, ReferenciaDocumentoPago, codigoCliente  ) " +
				"values ('3','2013-10-30 00:00','2013-12-31 00:00', '0','0','414.00','2300.00','1000.00','60','FACTURA','F001-000000434','1'); ");
		
		//cliente 2
		db.execSQL("insert into DocumentoPago (codigoDocumentoPago , fechaEmisionDocumentoPago, fechaVencimientoDocumentoPago, importeAmortizadoDocumentoPago, " +
				"importeDescontadoDocumentoPago, importeIgvDocumentoPago, importeOriginalDocumentoPago, importePendienteDocumentoPago, plazoDocumentoPago, tipoDocumentoPago, ReferenciaDocumentoPago, codigoCliente  ) " +
				"values ('4','2013-10-30 00:00','2013-12-31 00:00', '0','0','162.00','900.00','900.00','60','FACTURA','F001-00001234','2'); ");
		db.execSQL("insert into DocumentoPago (codigoDocumentoPago , fechaEmisionDocumentoPago, fechaVencimientoDocumentoPago, importeAmortizadoDocumentoPago, " +
				"importeDescontadoDocumentoPago, importeIgvDocumentoPago, importeOriginalDocumentoPago, importePendienteDocumentoPago, plazoDocumentoPago, tipoDocumentoPago, ReferenciaDocumentoPago, codigoCliente  ) " +
				"values ('5','2013-10-30 00:00','2013-12-31 00:00', '0','0','288.00','1600.00','1600.00','60','FACTURA','F001-00001235','2'); ");
		db.execSQL("insert into DocumentoPago (codigoDocumentoPago , fechaEmisionDocumentoPago, fechaVencimientoDocumentoPago, importeAmortizadoDocumentoPago, " +
				"importeDescontadoDocumentoPago, importeIgvDocumentoPago, importeOriginalDocumentoPago, importePendienteDocumentoPago, plazoDocumentoPago, tipoDocumentoPago, ReferenciaDocumentoPago, codigoCliente  ) " +
				"values ('6','2013-10-30 00:00','2013-12-31 00:00', '0','0','414.00','2300.00','1000.00','60','FACTURA','F001-00001236','2'); ");
		*/
		// amortizaciones
		/*
		 * id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"idCobranza INTEGER, codigoDocumentoPago INTEGER, importeAmortizacion NUMERIC, anotacionAmortizacion TEXT
		 * 
		 * */
		/*
		db.execSQL("insert into Amortizacion (id , idCobranza, codigoDocumentoPago, importeAmortizacion, anotacionAmortizacion) " +
				"values ('1','1','1','200','pago en efectivo'); ");
		db.execSQL("insert into Amortizacion (id , idCobranza, codigoDocumentoPago, importeAmortizacion, anotacionAmortizacion) " +
				"values ('2','1','2','100','pago en efectivo'); ");
		db.execSQL("insert into Amortizacion (id , idCobranza, codigoDocumentoPago, importeAmortizacion, anotacionAmortizacion) " +
				"values ('3','1','3','200','pago en efectivo'); ");
		*/
		//Bancos
		/*
		 * codigoBanco INTEGER PRIMARY KEY NOT NULL," +
			"nombreBanco TEXT
		 * 
		 * */
		/*
		db.execSQL("insert into Banco (codigoBanco , nombreBanco) " +
				"values ('1','INTERBANK'); ");
		db.execSQL("insert into Banco (codigoBanco , nombreBanco) " +
				"values ('2','BBVA'); ");
		db.execSQL("insert into Banco (codigoBanco , nombreBanco) " +
				"values ('3','FINANCIERO'); ");
		*/
		//depositos
		/*
		 * id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"codigoDeposito INTEGER, codigoCobranza INTEGER,codigoMedioPago INTEGER, bancoDeposito INTEGER, clienteDeposito INTEGER, " +
			"voucherDeposito INTEGER, agenciaDeposito TEXT,terminalDeposito TEXT, estadoDeposito INTEGER, fechaDeposito DATETIME, " +
			"importeDeposito NUMERIC 
		 * 
		 * 
		 * */
		/*
		db.execSQL("insert into Deposito ( codigoDeposito, codigoCobranza, codigoMedioPago, bancoDeposito,clienteDeposito, voucherDeposito,agenciaDeposito,terminalDeposito," +
				" estadoDeposito, fechaDeposito, importeDeposito  ) " +
				"values ('1', '1','1','1','1','1','San Miguel', 'T01', '1', '2013-11-04 00:00', '300'); ");
		
		db.execSQL("insert into Deposito ( codigoDeposito, codigoCobranza, codigoMedioPago, bancoDeposito,clienteDeposito, voucherDeposito,agenciaDeposito,terminalDeposito," +
				" estadoDeposito, fechaDeposito, importeDeposito  ) " +
				"values ('2', '1','1','1','1','1','Santa Anita', 'T02', '1', '2013-11-04 00:00', '150'); ");
		
		db.execSQL("insert into Deposito ( codigoDeposito, codigoCobranza, codigoMedioPago, bancoDeposito,clienteDeposito, voucherDeposito,agenciaDeposito,terminalDeposito," +
				" estadoDeposito, fechaDeposito, importeDeposito  ) " +
				"values ('3', '2','1','1','1','1','Surco', 'T03', '1', '2013-11-04 00:00', '200'); ");
				
				*/
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
		db.execSQL("DROP TABLE IF EXISTS " + TB_EMPRESA_CARGA);
		
		db.execSQL("DROP TABLE IF EXISTS " + TB_COBRANZA);
		db.execSQL("DROP TABLE IF EXISTS " + TB_DOCUMENTO_PAGO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_AMORTIZACION);
		db.execSQL("DROP TABLE IF EXISTS " + TB_MEDIO_PAGO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_BANCO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_DEPOSITO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_VOUCHER);
		
		onCreate(db);

	}

}
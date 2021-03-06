USE [ATL]
GO

/****** Object:  Table [dbo].[TIPO_DOCUMENTO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[TIPO_DOCUMENTO] ON
INSERT INTO [dbo].[TIPO_DOCUMENTO] (CODIGO_TIPO_DOCUMENTO, SIGLA_TIPO_DOCUMENTO, NOMBRE_TIPO_DOCUMENTO)	
VALUES
( 1, N'DNI', N'Documento Nacional de Identidad'),
( 2, N'RUC', N'Registro Único de Contribuyente'),
( 3, N'PAS', N'Pasaporte'                      );
SET IDENTITY_INSERT [dbo].[TIPO_DOCUMENTO] OFF


/****** Object:  Table [dbo].[PERSONA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[PERSONA] ON
INSERT INTO [dbo].[PERSONA] (CODIGO_PERSONA, TIPO_PERSONA, CODIGO_TIPO_DOCUMENTO, DOCUMENTO_PERSONA, NOMBRE_PERSONA, DIRECCION_PERSONA)
VALUES
(  1, N'NAT', 1, N'12123355',    N'Jorge Peschiera Cassinelli',                 N'Los Álamos 915, Surco'        ),
(  2, N'NAT', 1, N'12389764',    N'Juan Carlos Calle',                          N'Las Begonias 1747, San Isidro'),
(  3, N'NAT', 1, N'08186315',    N'Antonio Carlos Baxerías Valdez de la Torre', N'Mi casa'                      ),
(  4, N'NAT', 1, N'80077450',    N'Raúl Fernández',                             N'U.S.A.'                       ),
(  5, N'NAT', 1, N'84758914',    N'Jeffry Farfán',                              N'Schalke 04'                   ),
(  6, N'JUR', 2, N'10081863151', N'Bax2win S.A.C.',                             N'Mi empresa'                   ),
(  7, N'NAT', 1, N'90807040',    N'Claudio Pizarro',                            N'Munich'                       ),
(  8, N'NAT', 1, N'66997788',    N'Paolo Guerrero',                             N'Sao Paulo'                    ),
(  9, N'NAT', 3, N'MNO-77-919',  N'Sergio Markarian',                           N'La Videna'                    ),
( 10, N'JUR', 2, N'20017989711', N'Transportes El Rápido',                      N'Jr. Inclán 319, Surquillo'    );
SET IDENTITY_INSERT [dbo].[PERSONA] OFF


/****** Object:  Table [dbo].[EMPLEADO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[EMPLEADO] ON
INSERT INTO [dbo].[EMPLEADO] (CODIGO_EMPLEADO, FECHA_INGRESO_EMPLEADO, AREA_EMPLEADO, CARGO_EMPLEADO, CORREO_EMPLEADO, FECHA_CESE_EMPLEADO, JEFE_EMPLEADO, CODIGO_PERSONA)
VALUES
( 1, '20080303', N'G.General', N'G.General', NULL,                      NULL, NULL, 1),
( 2, '20080310', N'G.Ventas',  N'G.Ventas',  N'abaxerias@terra.com.pe', NULL, 1,    2),
( 3, '20090105', N'G.Ventas',  N'Vendedor',  N'acbvt1@gmail.com',       NULL, 2,    3),
( 4, '20090105', N'G.Ventas',  N'Vendedor',  N'baxerias@terra.com.pe',  NULL, 2,    4),
( 5, '20090105', N'G.Ventas',  N'Vendedor',  N'u201014038@upc.edu.pe',  NULL, 2,    5);
SET IDENTITY_INSERT [dbo].[EMPLEADO] OFF


/****** Object:  Table [dbo].[USUARIO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[USUARIO] ON
INSERT INTO [dbo].[USUARIO] (CODIGO_USUARIO, CODIGO_LOGIN_USUARIO, PASSWORD_USUARIO, FECHA_ULTIMO_INGRESO_USUARIO, FECHA_ULTIMO_CAMBIO_USUARIO, CLAVES_ERRADAS_USUARIO, ESTADO_USUARIO, CODIGO_EMPLEADO)
VALUES
( 1, N'ATLJORPES', N'ATLJORPES', NULL, NULL, 0, N'Activo',    1),
( 2, N'ATLJUACAR', N'ATLJUACAR', NULL, NULL, 0, N'Activo',    2),
( 3, N'ATLANTBAX', N'ATLANTBAX', NULL, NULL, 0, N'Activo',    3),
( 4, N'ATLRAUFER', N'ATLRAUFER', NULL, NULL, 0, N'Activo',    4),
( 5, N'ATLJEFFAR', N'ATLJEFFAR', NULL, NULL, 0, N'Bloqueado', 5);
SET IDENTITY_INSERT [dbo].[USUARIO] OFF



/****** Object:  Table [dbo].[GRUPO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[GRUPO] ON
INSERT INTO [dbo].[GRUPO] (CODIGO_GRUPO, NOMBRE_GRUPO)
VALUES
( 1, N'Grupo Juan Pérez'      ),
( 2, N'Grupo Mustafá Camel'   ),
( 3, N'Grupo González Fanning'),
( 4, N'Grupo Rodriguez Banda' ),
( 5, N'Grupo Cassinelli'      ),
( 6, N'Grupo López de Romaña' ),
( 7, N'Grupo UPC'             ),
( 8, N'Grupo Navarro Correas' );
SET IDENTITY_INSERT [dbo].[GRUPO] OFF


/****** Object:  Table [dbo].[LINEA_CREDITO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[LINEA_CREDITO] ON
INSERT INTO [dbo].[LINEA_CREDITO] (CODIGO_LINEA_CREDITO, IMPORTE_LINEA_CREDITO, IMPORTE_UTILIZADO_LINEA_CREDITO, IMPORTE_COMPROMETIDO_LINEA_CREDITO, FECHA_ASIGNACION_LINEA_CREDITO, FECHA_VENCIMIENTO_LINEA_CREDITO, BLOQUEO_LINEA_CREDITO, CODIGO_GRUPO)
VALUES
( 1, 100000.00,  34845.00,     0.00, '20130116', '20140115',                  0, 1),
( 2, 900000.00, 118800.00, 10000.00, '20120511', '20131031',                  1, 2),
( 3, 500000.00,      0.00, 50000.00, '20121212', '20141231',                  0, 3),
( 4, 200000.00,   2000.00,     0.00, '20130101', '20141231',                  1, 4),
( 5, 150000.00,  10000.00,     0.00, '20111215', DATEADD(DAY,-20, GETDATE()), 0, 5),
( 6, 350000.00,  40000.00,     0.00, '20130617', '20141231',                  0, 6),
( 7, 400000.00,  10000.00,     0.00, '20130918', '20141231',                  0, 7),
( 8, 700000.00, 250000.00,     0.00, '20130301', '20141231',                  0, 8);
SET IDENTITY_INSERT [dbo].[LINEA_CREDITO] OFF


/****** Object:  Table [dbo].[CLIENTE]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[CLIENTE] ON
INSERT INTO [dbo].[CLIENTE] (CODIGO_CLIENTE, REPRESENTANTE_CLIENTE, DIRECCION_ENTREGA_CLIENTE, CELULAR_CLIENTE, CORREO_CLIENTE, CODIGO_GRUPO, TITULAR_GRUPO_CLIENTE, CODIGO_PERSONA, CODIGO_EMPLEADO)
VALUES
( 1, N'Juan Pérez',            N'Av. De la Conquista 789, Santiago de Surco', 992789405, N'acbvt1@gmail.com',       1, 1, 6, 3),
( 2, N'Perico de los Palotes', N'Húsares de Junín 131, Jesús María',          978345666, N'baxerias@terra.com.pe',  1, 0, 7, 3),
( 3, N'Mustafá Camel',         N'Av. Del Corregidor 1147, La Molina',         987654321, N'abaxerias@terra.com.pe', 2, 1, 8, 4),
( 4, N'Chespirito',            N'Av. De la Conquista 147, La Molina',         987677721, N'u201014038@upc.edu.pe',  3, 1, 9, 5);
SET IDENTITY_INSERT [dbo].[CLIENTE] OFF


/****** Object:  Table [dbo].[SALDO_CUENTA]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[SALDO_CUENTA] (CODIGO_CLIENTE, IMPORTE_SALDO_CUENTA, IMPORTE_COMPROMETIDO_SALDO_CUENTA, IMPORTE_RESERVADO_SALDO_CUENTA)
VALUES
( 1, 100000.00,     0.00,     0.00),
( 2, 200000.00,     0.00, 34620.00),
( 3,  15000.00, 15000.00,     0.00),
( 4,      0.00,     0.00,     0.00);


/****** Object:  Table [dbo].[TIPO_VISITA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[TIPO_VISITA] ON
INSERT INTO [dbo].[TIPO_VISITA] (CODIGO_TIPO_VISITA, DESCRIPCION_TIPO_VISITA)
VALUES
( 1, N'Programada'),
( 2, N'Adicionada'),
( 3, N'Telefónica');
SET IDENTITY_INSERT [dbo].[TIPO_VISITA] OFF


/****** Object:  Table [dbo].[ESTADO_VISITA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[ESTADO_VISITA] ON
INSERT INTO [dbo].[ESTADO_VISITA] (CODIGO_ESTADO_VISITA, DESCRIPCION_ESTADO_VISITA)
VALUES
( 1, N'Programada'),
( 2, N'En visita'),
( 3, N'Atendida'),
( 4, N'Desprogramada'),
( 5, N'No efectuada');
SET IDENTITY_INSERT [dbo].[ESTADO_VISITA] OFF


/****** Object:  Table [dbo].[VISITA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[VISITA] ON
INSERT INTO [dbo].[VISITA] (CODIGO_VISITA, FECHA_VISITA, HORA_INICIO_VISITA, HORA_FINAL_VISITA, CODIGO_TIPO_VISITA, CODIGO_EMPLEADO, CODIGO_CLIENTE, CODIGO_ESTADO_VISITA)
VALUES
(  1, DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -2, GETDATE()), 1, 3, 1, 3),
(  2, DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -2, GETDATE()), 1, 3, 2, 3),
(  3, DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -2, GETDATE()), 2, 4, 3, 3),
(  4, DATEADD(DAY, -2, GETDATE()), NULL,                        NULL,                        3, 5, 4, 5),
(  5, DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), 2, 3, 1, 3),
(  6, DATEADD(DAY, -1, GETDATE()), NULL,                        NULL,                        3, 3, 2, 4),
(  7, DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), 1, 4, 3, 3),
(  8, DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), 1, 5, 4, 3),
(  9, GETDATE(),                   NULL,                        NULL,                        1, 3, 1, 1),
( 10, GETDATE(),                   NULL,                        NULL,                        1, 3, 2, 1),
( 11, GETDATE(),                   NULL,                        NULL,                        1, 4, 3, 1),
( 12, GETDATE(),                   NULL,                        NULL,                        1, 5, 4, 4),
( 13, DATEADD(DAY, +1, GETDATE()), NULL,                        NULL,                        1, 3, 1, 1),
( 14, DATEADD(DAY, +1, GETDATE()), NULL,                        NULL,                        1, 3, 2, 1),
( 15, DATEADD(DAY, +1, GETDATE()), NULL,                        NULL,                        1, 4, 3, 1),
( 16, DATEADD(DAY, +1, GETDATE()), NULL,                        NULL,                        1, 5, 4, 1),
( 17, DATEADD(DAY, +2, GETDATE()), NULL,                        NULL,                        1, 3, 1, 1),
( 18, DATEADD(DAY, +2, GETDATE()), NULL,                        NULL,                        1, 3, 2, 1),
( 19, DATEADD(DAY, +2, GETDATE()), NULL,                        NULL,                        1, 4, 3, 1),
( 20, DATEADD(DAY, +2, GETDATE()), NULL,                        NULL,                        1, 5, 4, 1);
SET IDENTITY_INSERT [dbo].[VISITA] OFF


/****** Object:  Table [dbo].[PRODUCTO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[PRODUCTO] ON
INSERT INTO [dbo].[PRODUCTO] (CODIGO_PRODUCTO, DESCRIPCION_PRODUCTO, COLOR_PRODUCTO, SEXO_PRODUCTO, MATERIAL_PRODUCTO, CALIDAD_PRODUCTO)
VALUES
( 1, N'Zapatillas casuales',   N'Marrón',      'M', N'Tela',  'A'),
( 2, N'Zapatillas casuales',   N'Azul',        'M', N'Tela',  'A'),
( 3, N'Zapatillas casuales',   N'Negro',       'M', N'Tela',  'A'),
( 4, N'Zapatillas de fulbito', N'Negro',       'M', N'Cuero', 'A'),
( 5, N'Zapatillas de fulbito', N'Blanco',      'M', N'Cuero', 'A'),
( 6, N'Zapatillas de fulbito', N'Rojo',        'M', N'Cuero', 'A'),
( 7, N'Sneakers My Love',      N'Rosado',      'F', N'Tela',  'A'),
( 8, N'Sneakers My Love',      N'Verde limón', 'F', N'Tela',  'A'),
( 9, N'Sneakers My Love',      N'Anaranjado',  'F', N'Tela',  'A');
SET IDENTITY_INSERT [dbo].[PRODUCTO] OFF


/****** Object:  Table [dbo].[TALLA]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[TALLA] (CODIGO_PRODUCTO, NUMERO_TALLA, STOCK_EXISTENTE_TALLA, STOCK_DESPACHO_TALLA, STOCK_COMPROMETIDO_TALLA, STOCK_DISPONIBLE_TALLA, STOCK_TRAMITE_TALLA, STOCK_SEMANA_TALLA)
VALUES
( 1, 38, 10000, 0, 0, 10000, 0, 0),
( 1, 39, 10000, 0, 0, 10000, 0, 0),
( 1, 40, 10000, 0, 0, 10000, 0, 0),
( 1, 41, 10000, 0, 0, 10000, 0, 0),
( 2, 38, 10000, 0, 0, 10000, 0, 0),
( 2, 39, 10000, 0, 0, 10000, 0, 0),
( 2, 40, 10000, 0, 0, 10000, 0, 0),
( 2, 41, 10000, 0, 0, 10000, 0, 0),
( 3, 38, 10000, 0, 0, 10000, 0, 0),
( 3, 39, 10000, 0, 0, 10000, 0, 0),
( 3, 40, 10000, 0, 0, 10000, 0, 0),
( 3, 41, 10000, 0, 0, 10000, 0, 0),
( 4, 38, 10000, 0, 0, 10000, 0, 0),
( 4, 39, 10000, 0, 0, 10000, 0, 0),
( 4, 40, 10000, 0, 0, 10000, 0, 0),
( 4, 41, 10000, 0, 0, 10000, 0, 0),
( 5, 38, 10000, 0, 0, 10000, 0, 0),
( 5, 39, 10000, 0, 0, 10000, 0, 0),
( 5, 40, 10000, 0, 0, 10000, 0, 0),
( 5, 41, 10000, 0, 0, 10000, 0, 0),
( 6, 38, 10000, 0, 0, 10000, 0, 0),
( 6, 39, 10000, 0, 0, 10000, 0, 0),
( 6, 40, 10000, 0, 0, 10000, 0, 0),
( 6, 41, 10000, 0, 0, 10000, 0, 0),
( 4, 31, 10000, 0, 0, 10000, 0, 0),
( 7, 32, 10000, 0, 0, 10000, 0, 0),
( 7, 33, 10000, 0, 0, 10000, 0, 0),
( 7, 34, 10000, 0, 0, 10000, 0, 0),
( 8, 31, 10000, 0, 0, 10000, 0, 0),
( 8, 32, 10000, 0, 0, 10000, 0, 0),
( 8, 33, 10000, 0, 0, 10000, 0, 0),
( 8, 34, 10000, 0, 0, 10000, 0, 0),
( 9, 31, 10000, 0, 0, 10000, 0, 0),
( 9, 32, 10000, 0, 0, 10000, 0, 0),
( 9, 33, 10000, 0, 0, 10000, 0, 0),
( 9, 34, 10000, 0, 0, 10000, 0, 0);


/****** Object:  Table [dbo].[MODALIDAD_PAGO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[MODALIDAD_PAGO] ON
INSERT INTO [dbo].[MODALIDAD_PAGO] (CODIGO_MODALIDAD_PAGO, SIGLA_MODALIDAD_PAGO, DESCRIPCION_MODALIDAD_PAGO, PLAZO_MODALIDAD_PAGO)
VALUES
( 1, 'E00', N'Factura adelantada', 0),
( 2, 'F30', N'Factura a 30 días', 30),
( 3, 'F60', N'Factura a 60 días', 60),
( 4, 'F90', N'Factura a 90 días', 90),
( 5, 'L30', N'Letra a 30 días',   30),
( 6, 'L60', N'Letra a 60 días',   60),
( 7, 'L90', N'Letra a 90 días',   90);
SET IDENTITY_INSERT [dbo].[MODALIDAD_PAGO] OFF


/****** Object:  Table [dbo].[PRECIO_PRODUCTO]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[PRECIO_PRODUCTO] (CODIGO_PRODUCTO, CODIGO_MODALIDAD_PAGO, IMPORTE_PRECIO_PRODUCTO)
VALUES
( 1, 1,  50.00),
( 1, 2,  51.00),
( 1, 3,  52.00),
( 1, 4,  53.00),
( 1, 5,  50.50),
( 1, 6,  51.50),
( 1, 7,  52.50),
( 2, 1,  50.00),
( 2, 2,  51.00),
( 2, 3,  52.00),
( 2, 4,  53.00),
( 2, 5,  50.50),
( 2, 6,  51.50),
( 2, 7,  52.50),
( 3, 1,  50.00),
( 3, 2,  51.00),
( 3, 3,  52.00),
( 3, 4,  53.00),
( 3, 5,  50.50),
( 3, 6,  51.50),
( 3, 7,  52.50),
( 4, 1,  97.00),
( 4, 2,  98.00),
( 4, 3,  99.00),
( 4, 4, 100.00),
( 4, 5,  97.50),
( 4, 6,  98.50),
( 4, 7,  99.50),
( 5, 1,  97.00),
( 5, 2,  98.00),
( 5, 3,  99.00),
( 5, 4, 100.00),
( 5, 5,  97.50),
( 5, 6,  98.50),
( 5, 7,  99.50),
( 6, 1,  97.00),
( 6, 2,  98.00),
( 6, 3,  99.00),
( 6, 4, 100.00),
( 6, 5,  97.50),
( 6, 6,  98.50),
( 6, 7,  99.50),
( 7, 1,  57.50),
( 7, 2,  58.50),
( 7, 3,  59.50),
( 7, 4,  60.50),
( 7, 5,  58.00),
( 7, 6,  59.00),
( 7, 7,  60.00),
( 8, 1,  57.50),
( 8, 2,  58.50),
( 8, 3,  59.50),
( 8, 4,  60.50),
( 8, 5,  58.00),
( 8, 6,  59.00),
( 8, 7,  60.00),
( 9, 1,  57.50),
( 9, 2,  58.50),
( 9, 3,  59.50),
( 9, 4,  60.50),
( 9, 5,  58.00),
( 9, 6,  59.00),
( 9, 7,  60.00);


/****** Object:  Table [dbo].[EMPRESA_CARGA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[EMPRESA_CARGA] ON
INSERT INTO [dbo].[EMPRESA_CARGA] (CODIGO_EMPRESA_CARGA, NOMBRE_EMPRESSA_CARGA, DIRECCION_EMPRESA_CARGA, HORARIO_EMPRESA_CARGA, RUC_EMPRESA_CARGA)
VALUES
( 1, N'Morales Moralitos', N'Su local',                          N'L-V: 09:00 a 23:00; S: 08:00 a 13:00', N'20123456789'),
( 2, N'TEPSA',             N'Av. Javier Prado 1199, San Isidro', N'L-S: 08:00 a 17:00',                   N'20987654321'),
( 3, N'Cruz del Sur',      N'Av. La Avenida 1234',               N'L-D: 00:00 a 24:00',                   N'20445566778'),
( 4, N'OLTURSA',           N'Av. Central 321, San Isidro',       N'L-D: 09:00 a 16:00',                   N'20998877665');
SET IDENTITY_INSERT [dbo].[EMPRESA_CARGA] OFF


/****** Object:  Table [dbo].[TIPO_TRANSACCION]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[TIPO_TRANSACCION] ON
INSERT INTO [dbo].[TIPO_TRANSACCION] (CODIGO_TIPO_TRANSACCION, DESCRIPCION_TIPO_TRANSACCION)
VALUES
( 1, N'Pedido'),
( 2, N'Cobranza'),
( 3, N'Giro de letra'),
( 4, N'Solicitud de ampliación de línea de crédito');
SET IDENTITY_INSERT [dbo].[TIPO_TRANSACCION] OFF


/****** Object:  Table [dbo].[TRANSACCION]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[TRANSACCION] ON
INSERT INTO [dbo].[TRANSACCION] (CODIGO_TRANSACCION, CODIGO_TIPO_TRANSACCION, FECHA_TRANSACCION, PERSONA_REGISTRO_TRANSACCION, CODIGO_VISITA)
VALUES
(  1, 1, DATEADD(DAY, -2, GETDATE()), 3, 1),
(  2, 3, DATEADD(DAY, -2, GETDATE()), 3, 1),
(  3, 1, DATEADD(DAY, -2, GETDATE()), 3, 2),
(  4, 2, DATEADD(DAY, -2, GETDATE()), 3, 2),
(  5, 1, DATEADD(DAY, -2, GETDATE()), 4, 3),
(  6, 2, DATEADD(DAY, -2, GETDATE()), 4, 3),
(  7, 1, DATEADD(DAY, -1, GETDATE()), 3, 5),
(  8, 2, DATEADD(DAY, -1, GETDATE()), 3, 5),
(  9, 1, DATEADD(DAY, -1, GETDATE()), 4, 7),
( 10, 2, DATEADD(DAY, -1, GETDATE()), 4, 7),
( 11, 1, DATEADD(DAY, -1, GETDATE()), 5, 8),
( 13, 2, DATEADD(DAY, -1, GETDATE()), 5, 8);
SET IDENTITY_INSERT [dbo].[TRANSACCION] OFF


/****** Object:  Table [dbo].[PEDIDO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[PEDIDO] ON
INSERT INTO [dbo].[PEDIDO] (CODIGO_PEDIDO, FECHA_INGRESO_PEDIDO, LIMITE_RETENCION_PEDIDO, IMPORTE_PEDIDO, CODIGO_MODALIDAD_PAGO, PAGO_CONTADO_PEDIDO, INSTRUCCIONES_PEDIDO, CODIGO_EMPRESA_CARGA, IMPORTE_RESERVADO_PEDIDO, ACEPTA_RETENCION_PEDIDO, ESTA_RETENIDO_PEDIDO, FALTA_IMPORTE_PEDIDO, FALTA_STOCK_PEDIDO, ESTADO_PEDIDO, FECHA_ANULACION_PEDIDO, CODIGO_TRANSACCION, CODIGO_MOVIL_PEDIDO)
VALUES
( 1, DATEADD(DAY, -2, GETDATE()), 5,  34845.00, 5, 0, N'Proteger con envoltura plástica gruesa', 4,        0.00, 1, 0, 0, 0, N'Aprobado', NULL,  1, NULL),
( 2, DATEADD(DAY, -2, GETDATE()), 5,  34620.00, 1, 1, NULL,                                      NULL,     0.00, 1, 0, 0, 0, N'Aprobado', NULL,  3, NULL),
( 3, DATEADD(DAY, -2, GETDATE()), 5, 118800.00, 4, 0, N'Reforzar las cajas con zunchos',         3,        0.00, 1, 0, 0, 0, N'Aprobado', NULL,  5, NULL),
( 4, DATEADD(DAY, -1, GETDATE()), 5,  52762.50, 5, 0, N'Avisar una hora antes de la entrega',    4,    10000.00, 1, 1, 1, 1, N'Retenido', NULL,  7, NULL),
( 5, DATEADD(DAY, -1, GETDATE()), 5,  23370.00, 1, 1, NULL,                                      NULL, 15000.00, 1, 1, 1, 1, N'Retenido', NULL,  9, NULL),
( 6, DATEADD(DAY, -1, GETDATE()), 5, 143850.00, 4, 0, N'Engregar entre 16:00 y 21:00 horas',     3,    50000.00, 1, 1, 1, 1, N'Retenido', NULL, 11, NULL);
SET IDENTITY_INSERT [dbo].[PEDIDO] OFF


/****** Object:  Table [dbo].[PRODUCTO_PEDIDO]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[PRODUCTO_PEDIDO] (CODIGO_PEDIDO, CODIGO_PRODUCTO, PRECIO_PRODUCTO_PEDIDO)
VALUES
( 1, 1,  50.50),
( 1, 2,  50.50),
( 1, 3,  50.50),
( 2, 1,  50.00),
( 2, 3,  50.00),
( 3, 1,  53.00),
( 3, 2,  53.00),
( 4, 2,  50.50),
( 4, 3,  50.50),
( 4, 4,  97.50),
( 5, 2,  50.00),
( 5, 5,  97.00),
( 5, 6,  97.00),
( 6, 1,  53.00),
( 6, 3,  53.00),
( 6, 4, 100.00);


/****** Object:  Table [dbo].[TALLA_PEDIDA]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[TALLA_PEDIDA] (CODIGO_PEDIDO, CODIGO_PRODUCTO, NUMERO_TALLA, CANTIDAD_PEDIDA_TALLA_PEDIDA, CANTIDAD_SEPARADA_TALLA_PEDIDA)
VALUES
( 1, 1, 38, 100, 100),
( 1, 1, 39, 100, 100),
( 1, 1, 40, 100, 100),
( 1, 2, 39,  50,  50),
( 1, 2, 40,  50,  50),
( 1, 2, 41,  50,  50),
( 1, 3, 38,  80,  80),
( 1, 3, 39,  80,  80),
( 1, 3, 40,  80,  80),
( 2, 1, 38, 100, 100),
( 2, 1, 39, 100, 100),
( 2, 1, 40, 100, 100),
( 2, 3, 38,  50,  50),
( 2, 3, 39,  50,  50),
( 2, 3, 40,  50,  50),
( 3, 1, 38, 200, 200),
( 3, 1, 39, 200, 200),
( 3, 1, 40, 200, 200),
( 3, 2, 39, 500, 500),
( 3, 2, 40, 500, 500),
( 3, 2, 41, 500, 500),
( 4, 2, 39, 200,   0),
( 4, 2, 40, 200,   0),
( 4, 2, 41, 200,   0),
( 4, 3, 38, 100,   0),
( 4, 3, 39, 100,   0),
( 4, 3, 40, 100,   0),
( 4, 4, 39,  25,   0),
( 4, 4, 40,  25,   0),
( 4, 4, 41,  25,   0),
( 5, 2, 39,  20,   0),
( 5, 2, 40,  20,   0),
( 5, 2, 41,  20,   0),
( 5, 5, 39,  40,   0),
( 5, 5, 40,  40,   0),
( 5, 5, 41,  40,   0),
( 5, 6, 38,  30,   0),
( 5, 6, 39,  30,   0),
( 5, 6, 40,  30,   0),
( 6, 1, 38,  60,   0),
( 6, 1, 39,  60,   0),
( 6, 1, 40,  60,   0),
( 6, 3, 38,  90,   0),
( 6, 3, 39,  90,   0),
( 6, 3, 40,  90,   0),
( 6, 4, 39, 400,   0),
( 6, 4, 40, 400,   0),
( 6, 4, 41, 400,   0);


/****** Object:  Table [dbo].[TRANSPORTISTA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[TRANSPORTISTA] ON
INSERT INTO [dbo].[TRANSPORTISTA] (CODIGO_TRANSPORTISTA, ZONA_COBERTURA_TRANSPORTISTA, CODIGO_PERSONA)
VALUES
( 1, N'Todo Lima Metropolitana', 10);
SET IDENTITY_INSERT [dbo].[TRANSPORTISTA] OFF


/****** Object:  Table [dbo].[ORDEN_DESPACHO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[ORDEN_DESPACHO] ON
INSERT INTO [dbo].[ORDEN_DESPACHO] (CODIGO_ORDEN_DESPACHO, CODIGO_PEDIDO, ESTADO_ORDEN_DESPACHO, FECHA_INICIO_ORDEN_DESPACHO, FECHA_ENVIO_ORDEN_DESPACHO, FECHA_ENTREGA_ORDEN_DESPACHO, CODIGO_TRANSPORTISTA)
VALUES
( 1, 1, N'Entregada', DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), 1),
( 2, 2, N'Entregada', DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), 1),
( 3, 3, N'Entregada', DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, -1, GETDATE()), 1);
SET IDENTITY_INSERT [dbo].[ORDEN_DESPACHO] OFF


/****** Object:  Table [dbo].[PRODUCTO_ORDENADO]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[PRODUCTO_ORDENADO] (CODIGO_ORDEN_DESPACHO, CODIGO_PRODUCTO, PRECIO_PRODUCTO_ORDENADO)
VALUES
( 1, 1, 50.50),
( 1, 2, 50.50),
( 1, 3, 50.50),
( 2, 1, 50.00),
( 2, 3, 50.00),
( 3, 1, 53.00),
( 3, 2, 53.00);


/****** Object:  Table [dbo].[TALLA_ORDENADA]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[TALLA_ORDENADA] (CODIGO_ORDEN_DESPACHO, CODIGO_PRODUCTO, NUMERO_TALLA, CANTIDAD_ORDENADA_TALLA_ORDENADA, CANTIDAD_DESPACHADA_TALLA_ORDENADA)
VALUES
( 1, 1, 38, 100, 100),
( 1, 1, 39, 100, 100),
( 1, 1, 40, 100, 100),
( 1, 2, 39,  50,  50),
( 1, 2, 40,  50,  50),
( 1, 2, 41,  50,  50),
( 1, 3, 38,  80,  80),
( 1, 3, 39,  80,  80),
( 1, 3, 40,  80,  80),
( 2, 1, 38, 100, 100),
( 2, 1, 39, 100, 100),
( 2, 1, 40, 100, 100),
( 2, 3, 38,  50,  50),
( 2, 3, 39,  50,  50),
( 2, 3, 40,  50,  50),
( 3, 1, 38, 200, 200),
( 3, 1, 39, 200, 200),
( 3, 1, 40, 200, 200),
( 3, 2, 39, 500, 500),
( 3, 2, 40, 500, 500),
( 3, 2, 41, 500, 500);


/****** Object:  Table [dbo].[DOCUMENTO_PAGO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[DOCUMENTO_PAGO] ON
INSERT INTO [dbo].[DOCUMENTO_PAGO] (CODIGO_DOCUMENTO_PAGO, FECHA_EMISION_DOCUMENTO_PAGO, TIPO_DOCUMENTO_PAGO, PLAZO_DOCUMENTO_PAGO, FECHA_VENCIMIENTO_DOCUMENTO_PAGO, IMPORTE_ORIGINAL_DOCUMENTO_PAGO, IMPORTE_AMORTIZADO_DOCUMENTO_PAGO, IMPORTE_DESCONTADO_DOCUMENTO_PAGO, IMPORTE_PENDIENTE_DOCUMENTO_PAGO, IMPORTE_IGV_DOCUMENTO_PAGO, CODIGO_CLIENTE)
VALUES
( 1, DATEADD(DAY, -1, GETDATE()), 'L', 30, DATEADD(DAY, -1+30, GETDATE()),  34845.00, 0.00, 0.00,  34845.00,  5315.34, 1),
( 2, DATEADD(DAY, -1, GETDATE()), 'E',  0, DATEADD(DAY, -1,    GETDATE()),  34620.00, 0.00, 0.00,  34620.00,  5281.02, 2),
( 3, DATEADD(DAY, -1, GETDATE()), 'F', 90, DATEADD(DAY, -1+90, GETDATE()), 118800.00, 0.00, 0.00, 118800.00, 18122.03, 3);
SET IDENTITY_INSERT [dbo].[DOCUMENTO_PAGO] OFF


/****** Object:  Table [dbo].[BANCO]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[BANCO] (CODIGO_BANCO, NOMBRE_BANCO)
VALUES
( 1, N'Banco de Crédito del Perú'),
( 2, N'Banco Continental');


/****** Object:  Table [dbo].[GIRO_LETRA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[GIRO_LETRA] ON
INSERT INTO [dbo].[GIRO_LETRA] (CODIGO_GIRO_LETRA, CODIGO_TRANSACCION)
VALUES
( 1, 2);
SET IDENTITY_INSERT [dbo].[GIRO_LETRA] OFF


/****** Object:  Table [dbo].[LETRA]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[LETRA] (CODIGO_LETRA, NUMERO_LETRA, TASA_COMPENSATORIA_LETRA, TASA_MORATORIA_LETRA, BANCO_COBRADOR_LETRA, CODIGO_UNICO_BANCO_LETRA, CODIGO_DOCUMENTO_PAGO)
VALUES
( 1, 1, 30.000000, 70.000000, 1, 987654321, 1);


/****** Object:  Table [dbo].[FACTURA]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[FACTURA] (CODIGO_FACTURA, SERIE_FACTURA, NUMERO_FACTURA, CODIGO_DOCUMENTO_PAGO)
VALUES
( 1, N'A00', 1, 2),
( 2, N'A00', 2, 3);


/****** Object:  Table [dbo].[MEDIO_PAGO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[MEDIO_PAGO] ON
INSERT INTO [dbo].[MEDIO_PAGO] (CODIGO_MEDIO_PAGO, DESCRIPCION_MEDIO_PAGO)
VALUES
( 1, N'Efectivo'),
( 2, N'Voucher'),
( 3, N'Cheque misma plaza'),
( 4, N'Cheque otra plaza'),
( 5, N'Cheque exterior'),
( 6, N'Nota de crédito'),
( 7, N'Orden de cobro'),
( 8, N'Letra');
SET IDENTITY_INSERT [dbo].[MEDIO_PAGO] OFF


/****** Object:  Table [dbo].[COBRANZA]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[COBRANZA] ON
INSERT INTO [dbo].[COBRANZA] (CODIGO_COBRANZA, FECHA_COBRANZA, IMPORTE_COBRANZA, CODIGO_TRANSACCION, CODIGO_MEDIO_PAGO, CODIGO_MOVIL_COBRANZA)
VALUES
( 1, DATEADD(DAY, -2, GETDATE()), 400.00, 4, 1, N'7777');
SET IDENTITY_INSERT [dbo].[COBRANZA] OFF


/****** Object:  Table [dbo].[AMORTIZACION]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[AMORTIZACION] ON
INSERT INTO [dbo].[AMORTIZACION] (CODIGO_AMORTIZACION, IMPORTE_AMORTIZACION, ANOTACION_AMORTIZACION, CODIGO_COBRANZA, CODIGO_DOCUMENTO_PAGO)
VALUES
( 1, 400.00, N'Ningún comentario', 1, 2);
SET IDENTITY_INSERT [dbo].[AMORTIZACION] OFF


/****** Object:  Table [dbo].[DEPOSITO]    Script Date: 11/15/2013 17:39:46 ******/
SET IDENTITY_INSERT [dbo].[DEPOSITO] ON
INSERT INTO [dbo].[DEPOSITO] (CODIGO_DEPOSITO, FECHA_DEPOSITO, BANCO_DEPOSITO, AGENCIA_DEPOSITO, TERMINAL_DEPOSITO, VOUCHER_DEPOSITO, CODIGO_MEDIO_PAGO, IMPORTE_DEPOSITO, CODIGO_COBRANZA, CLIENTE_DEPOSITO, ESTADO_DEPOSITO, CODIGO_MOVIL_DEPOSITO)
VALUES
( 1, DATEADD(DAY, -2, GETDATE()), 1, N'CAMANA', N'44', 12345678, 1, 400.00, 1, 1, 0, N'99999');
SET IDENTITY_INSERT [dbo].[DEPOSITO] OFF


/****** Object:  Table [dbo].[CHEQUE_DEPOSITADO]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[VOUCHER]    Script Date: 11/15/2013 17:39:46 ******/


/****** Object:  Table [dbo].[PARAMETRO]    Script Date: 11/15/2013 17:39:46 ******/
INSERT INTO [dbo].[PARAMETRO] (CODIGO_PARAMETRO, DESCRIPCION_PARAMETRO, TIPO_PARAMETRO, VALOR_PARAMETRO, UNIDAD_PARAMETRO)
VALUES
( 1, N'Periodo de retención de pedidos', N'Entero', N'5', N'Días');






/****** Object:  Table [dbo].[EVENTO]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[PERFIL]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[OPCION]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[PERFIL_OPCION]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[PARAMETRO_EVENTO]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[PROVEEDOR]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[ALERTA]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[ORDEN_COMPRA]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[USUARIO_PERFIL]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[PRODUCTO_COMPRADO]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[TALLA_COMPRADA]    Script Date: 11/15/2013 17:39:46 ******/
/****** Object:  Table [dbo].[SOLICITUD_AMPLIACION]    Script Date: 11/15/2013 17:39:46 ******/

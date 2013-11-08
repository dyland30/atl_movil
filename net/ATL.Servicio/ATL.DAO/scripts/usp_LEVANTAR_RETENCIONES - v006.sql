USE [ATL]
GO

/****** Object:  StoredProcedure [dbo].[usp_LEVANTAR_RETENCIONES]    Script Date: 09/15/2013 20:15:53 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_LEVANTAR_RETENCIONES]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_LEVANTAR_RETENCIONES]
GO

USE [ATL]
GO

/****** Object:  StoredProcedure [dbo].[usp_LEVANTAR_RETENCIONES]    Script Date: 09/15/2013 20:15:53 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[usp_LEVANTAR_RETENCIONES]

AS
	DECLARE @TRUE bit;
	DECLARE @FALSE bit;
	SET @TRUE = 1;
	SET @FALSE = 0;

	DECLARE @RETCODE_PROCESO					int;
	DECLARE @SALDO_A_UTILIZAR					decimal (18,2);
	DECLARE @FECHA_HOY							date;
	DECLARE @SIGUE_FALTANDO_STOCK_PEDIDO		bit;
	DECLARE @NUEVA_CANTIDAD_A_SEPARAR			bigint;

	DECLARE @codigoCliente						bigint;
	DECLARE @importeSaldoCuenta					decimal (18,2);
	DECLARE @importeComprometidoSaldoCuenta		decimal (18,2);
	DECLARE @saldoCuentaEfectivo				decimal (18,2);

	DECLARE @codigoLineaCredito					bigint;
	DECLARE @importeLineaCredito				decimal (18,2);
	DECLARE @importeUtilizadoLineaCredito		decimal (18,2);
	DECLARE @importeComprometidoLineaCredito	decimal (18,2);
	DECLARE @saldoLineaCredito					decimal (18,2);
	
	DECLARE @codigoPedido						bigint;
	DECLARE @fechaIngresoPedido					date;
	DECLARE @limiteRetencionPedido				int;
	DECLARE @importePedido						decimal (18,2);
	DECLARE @pagoContadoPedido					bit;
	DECLARE @importeReservadoPedido				decimal (18,2);
	DECLARE @estaRetenidoPedido					bit;
	DECLARE @faltaImportePedido					bit;
	DECLARE @faltaStockPedido					bit;
	DECLARE @estadoPedido						char(10);
	DECLARE @fechaAnulacionPedido				date;
	
	DECLARE @pedidoRetenido						CURSOR;
	DECLARE @saldoLinea							CURSOR;
	DECLARE @saldoCuenta						CURSOR;
	DECLARE @tallaPedida						CURSOR;
	DECLARE @direccionCorreo					CURSOR;
	
	DECLARE @codigoProducto						bigint;
	DECLARE @numeroTalla						smallint;
	DECLARE @cantidadPedidaTallaPedida			bigint;
	DECLARE @cantidadSeparadaTallaPedida		bigint;
	DECLARE @codigoOrdenDespacho				bigint;
	DECLARE @stockComprometidoTalla				bigint;
	DECLARE @stockDisponibleTalla				bigint;
	
	DECLARE @correoCliente						nvarchar(50);
	DECLARE @correoVendedor						nvarchar(50);
	DECLARE @destinatarioCorreo					nvarchar(50);
	DECLARE @copiaOcultaCorreo					nvarchar(50);
	DECLARE @cuerpoCorreo						nvarchar(2000);
	DECLARE @asuntoCorreo						nvarchar(150);
	
	SET NOCOUNT ON;
	SET @RETCODE_PROCESO = 0;
	SET @FECHA_HOY = GETDATE();
	SET @saldoLinea = CURSOR FOR SELECT 1;
	SET @saldoCuenta = CURSOR FOR SELECT 1;
	SET @tallaPedida = CURSOR FOR SELECT 1;
	SET @direccionCorreo = CURSOR FOR SELECT 1;

	
	SET @pedidoRetenido = CURSOR FOR
		SELECT A.CODIGO_PEDIDO, A.FECHA_INGRESO_PEDIDO, A.LIMITE_RETENCION_PEDIDO, A.IMPORTE_PEDIDO,
		       A.PAGO_CONTADO_PEDIDO, A.IMPORTE_RESERVADO_PEDIDO, A.ESTA_RETENIDO_PEDIDO, 
		       A.FALTA_IMPORTE_PEDIDO, A.FALTA_STOCK_PEDIDO, A.ESTADO_PEDIDO, A.FECHA_ANULACION_PEDIDO
		FROM [dbo].[PEDIDO] AS A
		WHERE A.ESTA_RETENIDO_PEDIDO = @TRUE
		ORDER BY FECHA_INGRESO_PEDIDO, CODIGO_PEDIDO
		FOR UPDATE OF A.IMPORTE_RESERVADO_PEDIDO, A.ESTA_RETENIDO_PEDIDO, A.FALTA_IMPORTE_PEDIDO,
		              A.FALTA_STOCK_PEDIDO, A.ESTADO_PEDIDO, A.FECHA_ANULACION_PEDIDO;
	
	OPEN @pedidoRetenido;
	FETCH NEXT
	FROM @pedidoRetenido INTO @codigoPedido, @fechaIngresoPedido, @limiteRetencionPedido, @importePedido,
	                          @pagoContadoPedido, @importeReservadoPedido, @estaRetenidoPedido,
			                  @faltaImportePedido, @faltaStockPedido, @estadoPedido, @fechaAnulacionPedido;
	WHILE @@FETCH_STATUS = 0
		BEGIN
			--DETERMINA SI SE DISPONE DE SUFICIENTE IMPORTE, EN SALDO EN CUENTA O LÍNEA DE CRÉDITO SEGÚN CORRESPONDA 
			IF @faltaImportePedido = @TRUE
				BEGIN
					IF @pagoContadoPedido = @FALSE
						--SE DEBE PAGAR CONTRA LÍNEA DE CRÉDITO; DETERMINA SI SE DISPONE DE SUFICIENTE LINEA DE CREDITO					
						BEGIN
							SET @saldoLinea = CURSOR FOR
								SELECT A.CODIGO_LINEA_CREDITO, A.IMPORTE_LINEA_CREDITO, A.IMPORTE_UTILIZADO_LINEA_CREDITO, A.IMPORTE_COMPROMETIDO_LINEA_CREDITO
								FROM [dbo].[LINEA_CREDITO] AS A 
								WHERE A.CODIGO_GRUPO = (SELECT E.CODIGO_GRUPO 
														FROM PEDIDO AS B INNER JOIN TRANSACCION AS C ON B.CODIGO_TRANSACCION = C.CODIGO_TRANSACCION
											  							 INNER JOIN VISITA      AS D ON C.CODIGO_VISITA = D.CODIGO_VISITA
																		 INNER JOIN CLIENTE     AS E ON D.CODIGO_CLIENTE = E.CODIGO_CLIENTE
														WHERE B.CODIGO_PEDIDO = @codigoPedido)
								AND A.BLOQUEO_LINEA_CREDITO = @FALSE
								AND @FECHA_HOY >= A.FECHA_ASIGNACION_LINEA_CREDITO
								AND @FECHA_HOY <= A.FECHA_VENCIMIENTO_LINEA_CREDITO
								FOR UPDATE OF A.IMPORTE_UTILIZADO_LINEA_CREDITO, A.IMPORTE_COMPROMETIDO_LINEA_CREDITO;
							OPEN @saldoLinea;
							FETCH NEXT
							FROM @saldoLinea INTO @codigoLineaCredito, @importeLineaCredito, @importeUtilizadoLineaCredito, @importeComprometidoLineaCredito;
							IF @@FETCH_STATUS = 0
								BEGIN
									SET @SALDO_A_UTILIZAR = 0.00;
									SET @saldoLineaCredito = @importeLineaCredito - @importeUtilizadoLineaCredito - @importeComprometidoLineaCredito;
									IF @saldoLineaCredito > 0
										BEGIN
											IF @saldoLineaCredito >= (@importePedido - @importeReservadoPedido)
												SET @SALDO_A_UTILIZAR = (@importePedido - @importeReservadoPedido);
											ELSE
												SET @SALDO_A_UTILIZAR = @saldoLineaCredito;
										END;
									ELSE
										BEGIN
											SET @SALDO_A_UTILIZAR = 0;
										END;
									IF @SALDO_A_UTILIZAR > 0
										BEGIN
											UPDATE LINEA_CREDITO
											SET @importeComprometidoLineaCredito = IMPORTE_COMPROMETIDO_LINEA_CREDITO += @SALDO_A_UTILIZAR
											WHERE CURRENT OF @saldoLinea;
											UPDATE PEDIDO
											SET @importeReservadoPedido = IMPORTE_RESERVADO_PEDIDO += @SALDO_A_UTILIZAR,
												@faltaImportePedido = FALTA_IMPORTE_PEDIDO = CASE WHEN @importePedido = @importeReservadoPedido THEN @FALSE ELSE @TRUE END
											WHERE CURRENT OF @pedidoRetenido;
										END;
								END;
							CLOSE @saldoLinea;
						END;
						-- HASTA AQUI SE CONCLUYÓ LA VERIFICACIÓN DE LÍNEA DE CREDITO					
					ELSE
						--SE DEBE PAGAR CONTRA SALDO EN CUENTA; DETERMINA SI SE DISPONE DE SUFICIENTE SALDO EN CUENTA
						BEGIN
							SET @saldoCuenta = CURSOR FOR
								SELECT A.CODIGO_CLIENTE, A.IMPORTE_SALDO_CUENTA, A.IMPORTE_COMPROMETIDO_SALDO_CUENTA
								FROM [dbo].[SALDO_CUENTA] AS A 
								WHERE A.CODIGO_CLIENTE = (SELECT D.CODIGO_CLIENTE 
														  FROM PEDIDO AS B INNER JOIN TRANSACCION  AS C ON B.CODIGO_TRANSACCION = C.CODIGO_TRANSACCION
										  				  				   INNER JOIN VISITA       AS D ON C.CODIGO_VISITA = D.CODIGO_VISITA
														  WHERE B.CODIGO_PEDIDO = @codigoPedido)
								FOR UPDATE OF A.IMPORTE_SALDO_CUENTA, A.IMPORTE_COMPROMETIDO_SALDO_CUENTA;
							OPEN @saldoCuenta;
							FETCH NEXT
							FROM @saldoCuenta INTO @codigoCliente, @importeSaldoCuenta, @importeComprometidoSaldoCuenta;
							IF @@FETCH_STATUS = 0
								BEGIN
									SET @SALDO_A_UTILIZAR = 0.00;
									SET @saldoCuentaEfectivo = @importeSaldoCuenta - @importeComprometidoSaldoCuenta;
									IF @saldoCuentaEfectivo > 0
										BEGIN
											IF @saldoCuentaEfectivo >= (@importePedido - @importeReservadoPedido)
												SET @SALDO_A_UTILIZAR = (@importePedido - @importeReservadoPedido);
											ELSE
												SET @SALDO_A_UTILIZAR = @saldoCuentaEfectivo;
										END;
									ELSE
										BEGIN
											SET @SALDO_A_UTILIZAR = 0;
										END;
									IF @SALDO_A_UTILIZAR > 0
										BEGIN
											UPDATE SALDO_CUENTA
											SET @importeComprometidoSaldoCuenta = IMPORTE_COMPROMETIDO_SALDO_CUENTA += @SALDO_A_UTILIZAR
											WHERE CURRENT OF @saldoCuenta;
											UPDATE PEDIDO
											SET @importeReservadoPedido = IMPORTE_RESERVADO_PEDIDO += @SALDO_A_UTILIZAR,
												@faltaImportePedido = FALTA_IMPORTE_PEDIDO = CASE WHEN @importePedido = @importeReservadoPedido THEN @FALSE ELSE @TRUE END
											WHERE CURRENT OF @pedidoRetenido;
										END;
								END;
							CLOSE @saldoCuenta;				
						END;
						-- HASTA AQUI SE CONCLUYÓ LA VERIFICACIÓN DE SALDO EN CUENTA
				END;
				-- HASTA AQUÍ SE VERIFICÓ LA DISPONIBILIDAD DE IMPORTE EN SALDO o LÍNEA DE CRÉDITO


			-- DETERMINA SI SE DISPONE DE SUFICIENTE STOCK
			SET @SIGUE_FALTANDO_STOCK_PEDIDO = @FALSE;
			IF @faltaStockPedido = @TRUE
				BEGIN
					SET @tallaPedida = CURSOR FOR
						SELECT A.CODIGO_PRODUCTO, A.NUMERO_TALLA, A.CANTIDAD_PEDIDA_TALLA_PEDIDA, A.CANTIDAD_SEPARADA_TALLA_PEDIDA,
							   B.STOCK_COMPROMETIDO_TALLA, B.STOCK_DISPONIBLE_TALLA
						FROM [dbo].[TALLA_PEDIDA] AS A 
						INNER JOIN [dbo].[TALLA]  AS B ON A.CODIGO_PRODUCTO = B.CODIGO_PRODUCTO AND A.NUMERO_TALLA = B.NUMERO_TALLA
						WHERE A.CODIGO_PEDIDO = @codigoPedido
						AND   A.CANTIDAD_PEDIDA_TALLA_PEDIDA > A.CANTIDAD_SEPARADA_TALLA_PEDIDA
						AND	  B.STOCK_DISPONIBLE_TALLA > 0
						FOR UPDATE OF A.CANTIDAD_SEPARADA_TALLA_PEDIDA, B.STOCK_COMPROMETIDO_TALLA, B.STOCK_DISPONIBLE_TALLA;
					OPEN @tallaPedida;
					FETCH NEXT
					FROM @tallaPedida INTO @codigoProducto, @numeroTalla, @cantidadPedidaTallaPedida, @cantidadSeparadaTallaPedida,
										   @stockComprometidoTalla, @stockDisponibleTalla;
					WHILE @@FETCH_STATUS = 0
						BEGIN
							IF @stockDisponibleTalla > @cantidadPedidaTallaPedida - @cantidadSeparadaTallaPedida
								SET @NUEVA_CANTIDAD_A_SEPARAR = @cantidadPedidaTallaPedida - @cantidadSeparadaTallaPedida;
							ELSE
								SET @NUEVA_CANTIDAD_A_SEPARAR = @stockDisponibleTalla;
							UPDATE [dbo].[TALLA]
							SET @stockDisponibleTalla = STOCK_DISPONIBLE_TALLA -= @NUEVA_CANTIDAD_A_SEPARAR,
								@stockComprometidoTalla = STOCK_COMPROMETIDO_TALLA += @NUEVA_CANTIDAD_A_SEPARAR
							WHERE CURRENT OF @tallaPedida;
							UPDATE [dbo].[TALLA_PEDIDA]
							SET @cantidadSeparadaTallaPedida = CANTIDAD_SEPARADA_TALLA_PEDIDA += @NUEVA_CANTIDAD_A_SEPARAR
							WHERE CURRENT OF @tallaPedida;
							IF @cantidadPedidaTallaPedida > @cantidadSeparadaTallaPedida SET @SIGUE_FALTANDO_STOCK_PEDIDO = @TRUE;							
							FETCH NEXT
							FROM @tallaPedida INTO @codigoProducto, @numeroTalla, @cantidadPedidaTallaPedida, @cantidadSeparadaTallaPedida,
												   @stockComprometidoTalla, @stockDisponibleTalla;
						END;
					CLOSE @tallaPedida;
					IF @SIGUE_FALTANDO_STOCK_PEDIDO = @FALSE
						BEGIN
							UPDATE PEDIDO
							SET @faltaStockPedido = FALTA_STOCK_PEDIDO = @FALSE
							WHERE CURRENT OF @pedidoRetenido;
						END;
				END;
			-- HASTA AQUI SE CONCLUYÓ LA VERIFICACIÖN DE STOCKS
			
			-- CARGA DIRECCIONES DE CORREO ELECTRÓNICO PARA CORREOS DE LIBERACIÓN O ANULACIÓN DE PEDIDO
			SET @direccionCorreo = CURSOR FOR
				SELECT D.CORREO_CLIENTE, E.CORREO_EMPLEADO
				FROM       [dbo].[PEDIDO]      AS A
				INNER JOIN [dbo].[TRANSACCION] AS B ON A.CODIGO_TRANSACCION = B.CODIGO_TRANSACCION
				INNER JOIN [dbo].[VISITA]      AS C ON B.CODIGO_VISITA = C.CODIGO_VISITA
				INNER JOIN [dbo].[CLIENTE]     AS D ON C.CODIGO_CLIENTE = D.CODIGO_CLIENTE
				INNER JOIN [dbo].[EMPLEADO]    AS E ON D.CODIGO_EMPLEADO = E.CODIGO_EMPLEADO
				WHERE A.CODIGO_PEDIDO = @codigoPedido;
				OPEN @direccionCorreo;
				FETCH NEXT
				FROM @direccionCorreo INTO @correoCliente, @correoVendedor;
				CLOSE @direccionCorreo;
			SET @destinatarioCorreo = ISNULL(@correoCliente, '');
			SET @copiaOcultaCorreo = ISNULL(@correoVendedor, '');
							
			--SI PEDIDO CUMPLIO SUS REQUISITOS SE APRUEBA Y GENERA ORDEN DE DESPACHO
			IF (@faltaImportePedido = @FALSE AND @faltaStockPedido = @FALSE)
				BEGIN
					UPDATE PEDIDO
					SET @estaRetenidoPedido = ESTA_RETENIDO_PEDIDO = @FALSE,
					    @estadoPedido = ESTADO_PEDIDO = 'Aprobado'
					WHERE CURRENT OF @pedidoRetenido;
				--	GENERACIÓN DE ORDEN DE DESPACHO; como parte de ello, se pasa datos importe Comprometido a importe
				--  utilizado en la línea de crédito o saldo en cuenta y actualiza despachado, disponibles y comprometido de stocks
					INSERT INTO [dbo].[ORDEN_DESPACHO] (CODIGO_PEDIDO, ESTADO_ORDEN_DESPACHO)
					VALUES (@codigoPedido, 'Aprobada'); 
					SET @codigoOrdenDespacho = @@IDENTITY;
					INSERT INTO [dbo].[PRODUCTO_ORDENADO]
					SELECT @codigoOrdenDespacho, CODIGO_PRODUCTO, PRECIO_PRODUCTO_PEDIDO
					FROM [dbo].[PRODUCTO_PEDIDO]
					WHERE CODIGO_PEDIDO = @codigoPedido;
					INSERT INTO [dbo].[TALLA_ORDENADA]
					SELECT @codigoOrdenDespacho, CODIGO_PRODUCTO, NUMERO_TALLA, CANTIDAD_PEDIDA_TALLA_PEDIDA, NULL
					FROM [dbo].[TALLA_PEDIDA]
					WHERE CODIGO_PEDIDO = @codigoPedido;
					
					IF @pagoContadoPedido = @TRUE
						BEGIN
							UPDATE [dbo].[SALDO_CUENTA]
							SET IMPORTE_SALDO_CUENTA -= @importeReservadoPedido,
								IMPORTE_COMPROMETIDO_SALDO_CUENTA -= @importeReservadoPedido
							WHERE CODIGO_CLIENTE = @codigoCliente;
						END;
					ELSE
						BEGIN
							UPDATE [dbo].[LINEA_CREDITO]
							SET IMPORTE_UTILIZADO_LINEA_CREDITO += @importeReservadoPedido,
								IMPORTE_COMPROMETIDO_LINEA_CREDITO -= @importeReservadoPedido
							WHERE CODIGO_LINEA_CREDITO = @codigoLineaCredito;
						END;
					
					UPDATE [dbo].[TALLA]
					SET STOCK_DESPACHO_TALLA += B.CANTIDAD_SEPARADA_TALLA_PEDIDA,
						STOCK_COMPROMETIDO_TALLA -= B.CANTIDAD_SEPARADA_TALLA_PEDIDA
					FROM [dbo].[TALLA] AS A INNER JOIN [dbo].[TALLA_PEDIDA] AS B ON A.CODIGO_PRODUCTO = B.CODIGO_PRODUCTO AND A.NUMERO_TALLA = B.NUMERO_TALLA
					WHERE B.CODIGO_PEDIDO = @codigoPedido; 
				--	EMITIR CORREO DE AVISO DE LIBERACIÓN AL VENDEDOR Y AL CLIENTE
					SET @destinatarioCorreo = @correoCliente;
					SET @copiaOcultaCorreo = @correoVendedor;
					SET @cuerpoCorreo = 'Estimado Cliente:<BR><BR>Informamos a usted que su pedido de código ' + CAST(@codigoPedido AS nvarchar(15)) +
						                ' ha pasado al proceso de despacho.<BR><BR>Saludos cordiales,<BR><BR>Calzado Atlas S.A.';   
					SET @asuntoCorreo = 'Despacho del pedido ' + CAST(@codigoPedido AS nvarchar(15)) ;
					EXEC msdb.dbo.sp_send_dbmail
						@profile_name = 'Speedy',
						@recipients = @destinatarioCorreo,
						@blind_copy_recipients = @copiaOcultaCorreo,
						@body_format = 'HTML',
						@body = @cuerpoCorreo,
						@subject = @asuntoCorreo;
				END;
			ELSE
				--SI PEDIDO NO CUMPLIO SUS REQUISITOS DENTRO DE PLAZO LIMITE ESTABLECIDO, SE ANULA EL PEDIDO y libera linea y stocks comprometidos
				BEGIN
					IF (DATEDIFF (DAY, @fechaIngresoPedido, @FECHA_HOY) > @limiteRetencionPedido) AND @estaRetenidoPedido = @TRUE
						BEGIN
							IF @pagoContadoPedido = @TRUE
								BEGIN
									UPDATE SALDO_CUENTA
									SET @importeComprometidoSaldoCuenta = IMPORTE_COMPROMETIDO_SALDO_CUENTA -= @importeReservadoPedido
									WHERE CODIGO_CLIENTE = @codigoCliente;
								END;
							ELSE
								BEGIN
									UPDATE LINEA_CREDITO
									SET @importeComprometidoLineaCredito = IMPORTE_COMPROMETIDO_LINEA_CREDITO -= @importeReservadoPedido
									WHERE CODIGO_LINEA_CREDITO = @codigoLineaCredito;
								END;

							UPDATE PEDIDO
							SET @estaRetenidoPedido = ESTA_RETENIDO_PEDIDO = @FALSE,
								@estadoPedido = ESTADO_PEDIDO = 'Anulado',
								@fechaAnulacionPedido = FECHA_ANULACION_PEDIDO = @FECHA_HOY,
								@importeReservadoPedido = IMPORTE_RESERVADO_PEDIDO = 0
							WHERE CURRENT OF @pedidoRetenido;					
						--	Descargar datos de stock comprometido en los stocks
							UPDATE [dbo].[TALLA]
							SET STOCK_COMPROMETIDO_TALLA -= B.CANTIDAD_SEPARADA_TALLA_PEDIDA,
								STOCK_DISPONIBLE_TALLA += B.CANTIDAD_SEPARADA_TALLA_PEDIDA
							FROM [dbo].[TALLA] AS A INNER JOIN [dbo].[TALLA_PEDIDA] AS B ON A.CODIGO_PRODUCTO = B.CODIGO_PRODUCTO AND A.NUMERO_TALLA = B.NUMERO_TALLA
							WHERE B.CODIGO_PEDIDO = @codigoPedido; 
							UPDATE [dbo].[TALLA_PEDIDA]
							SET CANTIDAD_SEPARADA_TALLA_PEDIDA = 0
							WHERE CODIGO_PEDIDO = @codigoPedido; 
						--	EMITIR CORREO DE AVISO DE ANULACIÓN AL VENDEDOR Y AL CLIENTE
							SET @destinatarioCorreo = @correoCliente;
							SET @copiaOcultaCorreo = @correoVendedor;
							SET @cuerpoCorreo = 'Estimado Cliente:<BR><BR>Informamos a usted que su pedido de código ' + CAST(@codigoPedido AS nvarchar(15)) + 
							                    ' ha sido anulado debido a que no se dieron las condiciones necesarias para aprobarlo dentro del plazo de ' + 
							                    ' espera convenido.<BR><BR>Atentamente,<BR><BR>Calzado Atlas S.A.';   
							SET @asuntoCorreo = 'Anulación del pedido ' + CAST(@codigoPedido AS nvarchar(15)) ;
							EXEC msdb.dbo.sp_send_dbmail
								@profile_name = 'Speedy',
								@recipients = @destinatarioCorreo,
								@blind_copy_recipients = @copiaOcultaCorreo,
								@body_format = 'HTML',
								@body = @cuerpoCorreo,
								@subject = @asuntoCorreo;
						END;
				END;
			
			FETCH NEXT
			FROM @pedidoRetenido INTO @codigoPedido, @fechaIngresoPedido, @limiteRetencionPedido, @importePedido,
				                      @pagoContadoPedido, @importeReservadoPedido, @estaRetenidoPedido,
						              @faltaImportePedido, @faltaStockPedido, @estadoPedido, @fechaAnulacionPedido;
		END;
	CLOSE @pedidoRetenido;
	
 	DEALLOCATE @saldoLinea;
 	DEALLOCATE @saldoCuenta;
 	DEALLOCATE @tallaPedida;
	DEALLOCATE @pedidoRetenido;
	DEALLOCATE @direccionCorreo;
	
	SET @RETCODE_PROCESO = @@ERROR;
	RETURN @RETCODE_PROCESO;

GO



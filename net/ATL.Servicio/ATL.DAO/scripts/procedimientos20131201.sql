USE [ATL]
GO
/****** Object:  StoredProcedure [dbo].[USP_PROCESAR_PEDIDO]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[USP_PROCESAR_PEDIDO]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[USP_PROCESAR_PEDIDO]
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_ins]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_ins]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_talla_pedido_ins]
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_talla_pedido_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_sel_codigo]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_sel_codigo]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_talla_pedido_sel_codigo]
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_update]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_update]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_talla_pedido_update]
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_ins]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_ins]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_amortizacion_ins]
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_amortizacion_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_sel_codigo]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_sel_codigo]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_amortizacion_sel_codigo]
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_update]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_update]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_amortizacion_update]
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_ins]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_ins]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_detalle_pedido_ins]
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_detalle_pedido_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_sel_codigo]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_sel_codigo]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_detalle_pedido_sel_codigo]
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_update]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_update]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_detalle_pedido_update]
GO
/****** Object:  StoredProcedure [dbo].[usp_cobranza_ins]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cobranza_ins]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_cobranza_ins]
GO
/****** Object:  StoredProcedure [dbo].[usp_cobranza_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cobranza_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_cobranza_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_cobranza_upd]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cobranza_upd]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_cobranza_upd]
GO
/****** Object:  StoredProcedure [dbo].[USP_PEDIDO_INS]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[USP_PEDIDO_INS]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[USP_PEDIDO_INS]
GO
/****** Object:  StoredProcedure [dbo].[usp_pedido_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_pedido_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_pedido_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_pedido_sel_por_id]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_pedido_sel_por_id]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_pedido_sel_por_id]
GO
/****** Object:  StoredProcedure [dbo].[usp_pedido_update]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_pedido_update]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_pedido_update]
GO
/****** Object:  StoredProcedure [dbo].[usp_documento_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_documento_pago_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_documento_pago_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_actualizar_estado_visita]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_actualizar_estado_visita]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_actualizar_estado_visita]
GO
/****** Object:  StoredProcedure [dbo].[usp_visita_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_visita_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_visita_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_usuario_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_usuario_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_usuario_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_ins_cliente]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_ins_cliente]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_ins_cliente]
GO
/****** Object:  StoredProcedure [dbo].[usp_buscar_id_cliente]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_buscar_id_cliente]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_buscar_id_cliente]
GO
/****** Object:  StoredProcedure [dbo].[usp_cliente_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cliente_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_cliente_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_act_cliente]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_act_cliente]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_act_cliente]
GO
/****** Object:  StoredProcedure [dbo].[usp_empleado_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_empleado_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_empleado_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_producto_forma_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_producto_forma_pago_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_producto_forma_pago_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_persona_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_persona_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_persona_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_talla_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_sel_por_id]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_sel_por_id]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_talla_sel_por_id]
GO
/****** Object:  StoredProcedure [dbo].[usp_empresa_carga_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_empresa_carga_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_empresa_carga_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_estado_visita_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_estado_visita_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_estado_visita_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_forma_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_forma_pago_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_forma_pago_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_grupo_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_grupo_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_grupo_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_banco_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_banco_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_banco_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_tipo_visita_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_tipo_visita_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_tipo_visita_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_tipodocumento_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_tipodocumento_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_tipodocumento_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_producto_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_producto_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_producto_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_medio_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_medio_pago_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_medio_pago_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_medio_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_medio_pago_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_medio_pago_sel]
as
begin
SELECT [CODIGO_MEDIO_PAGO] codigoMedioPago
      ,[DESCRIPCION_MEDIO_PAGO] descripcionMedioPago
  FROM [MEDIO_PAGO]
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_producto_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_producto_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_producto_sel]
as
begin
SELECT [CODIGO_PRODUCTO] codigoProducto
      ,[DESCRIPCION_PRODUCTO] descripcionProducto
      ,[COLOR_PRODUCTO] colorProducto
      ,[SEXO_PRODUCTO] sexoProducto
      ,[MATERIAL_PRODUCTO] materialProducto
      ,[CALIDAD_PRODUCTO] calidadProducto
  FROM [PRODUCTO]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_tipodocumento_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_tipodocumento_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_tipodocumento_sel]
as
begin
SELECT [CODIGO_TIPO_DOCUMENTO] codigoTipoDocumento
      ,[SIGLA_TIPO_DOCUMENTO] siglaTipoDocumento
      ,[NOMBRE_TIPO_DOCUMENTO] nombreTipoDocumento
  FROM [TIPO_DOCUMENTO]

end' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_tipo_visita_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_tipo_visita_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_tipo_visita_sel]
as
begin
SELECT [CODIGO_TIPO_VISITA] codigoTipoVisita
      ,[DESCRIPCION_TIPO_VISITA] descripcionTipoVisita
  FROM [TIPO_VISITA]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_banco_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_banco_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_banco_sel]
as
begin
SELECT [CODIGO_BANCO] codigoBanco
      ,[NOMBRE_BANCO] nombreBanco
  FROM [BANCO]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_grupo_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_grupo_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_grupo_sel]
as
begin

SELECT [CODIGO_GRUPO] codigoGrupo
      ,[NOMBRE_GRUPO] nombreGrupo
  FROM [GRUPO]
  
end

' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_forma_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_forma_pago_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_forma_pago_sel]
as 
begin
SELECT [CODIGO_MODALIDAD_PAGO] codigoFormaPago
      ,[DESCRIPCION_MODALIDAD_PAGO] descripcionFormaPago
  FROM [MODALIDAD_PAGO]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_estado_visita_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_estado_visita_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_estado_visita_sel]
as
begin
SELECT [CODIGO_ESTADO_VISITA]codigoEstadoVisita
      ,[DESCRIPCION_ESTADO_VISITA] descripcionEstadoVisita
  FROM [ESTADO_VISITA]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_empresa_carga_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_empresa_carga_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_empresa_carga_sel]
as
begin
SELECT [CODIGO_EMPRESA_CARGA] codigoEmpresaCarga
      ,[NOMBRE_EMPRESSA_CARGA] nombreEmpresaCarga
      ,[DIRECCION_EMPRESA_CARGA] direccionEmpresaCarga
      ,[HORARIO_EMPRESA_CARGA] horarioEmpresaCarga
      ,[RUC_EMPRESA_CARGA] rucEmpresaCarga
  FROM [EMPRESA_CARGA]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_sel_por_id]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_sel_por_id]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_talla_sel_por_id]

@codigoProducto bigint,
@numeroTalla bigint
as
begin
SELECT [CODIGO_PRODUCTO] codigoProducto
      ,[NUMERO_TALLA] numeroTalla
      ,[STOCK_DISPONIBLE_TALLA] stockDisponibleTalla
  FROM [TALLA]
  WHERE CODIGO_PRODUCTO = @codigoProducto and NUMERO_TALLA= @numeroTalla
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_talla_sel]
as
begin
SELECT [CODIGO_PRODUCTO] codigoProducto
      ,[NUMERO_TALLA] numeroTalla
      ,[STOCK_DISPONIBLE_TALLA] stockDisponibleTalla
  FROM [TALLA]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_persona_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_persona_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'--codigoPersona, codigoTipoDocumento,nombrePersona, 
--direccionPersona,documentoPersona, tipoPersona

create proc [dbo].[usp_persona_sel]
as
begin
SELECT [CODIGO_PERSONA] codigoPersona
      ,[TIPO_PERSONA] tipoPersona
      ,[CODIGO_TIPO_DOCUMENTO] codigoTipoDocumento
      ,[DOCUMENTO_PERSONA] documentoPersona
      ,[NOMBRE_PERSONA] nombrePersona
      ,[DIRECCION_PERSONA] direccionPersona
FROM [PERSONA]
end

' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_producto_forma_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_producto_forma_pago_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_producto_forma_pago_sel]
as
begin
SELECT [CODIGO_PRODUCTO] codigoProducto
      ,[CODIGO_MODALIDAD_PAGO] codigoFormaPago
      ,[IMPORTE_PRECIO_PRODUCTO] precio
  FROM [PRECIO_PRODUCTO]
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_empleado_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_empleado_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_empleado_sel]
as 
begin
SELECT [CODIGO_EMPLEADO] codigoEmpleado
      ,convert(varchar,[FECHA_INGRESO_EMPLEADO],21) strfechaIngresoEmpleado
      ,[AREA_EMPLEADO] areaEmpleado
      ,[CARGO_EMPLEADO] cargoEmpleado
      ,convert(varchar,[FECHA_CESE_EMPLEADO],21) strfechaCeseEmpleado
      ,[JEFE_EMPLEADO] jefeEmpleado
      ,[CODIGO_PERSONA] codigoPersona
  FROM [EMPLEADO]
end

' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_act_cliente]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_act_cliente]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROC [dbo].[usp_act_cliente]
 @CODIGO_CLIENTE bigint
,@REPRESENTANTE_CLIENTE nvarchar(50)
,@DIRECCION_ENTREGA_CLIENTE nvarchar(50)
,@CELULAR_CLIENTE bigint
,@CORREO_CLIENTE nvarchar(50)
,@CODIGO_GRUPO bigint
,@TITULAR_GRUPO_CLIENTE bit
,@CODIGO_PERSONA bigint
,@CODIGO_EMPLEADO bigint

AS
BEGIN

UPDATE [CLIENTE]
   SET [REPRESENTANTE_CLIENTE] = @REPRESENTANTE_CLIENTE
      ,[DIRECCION_ENTREGA_CLIENTE] = @DIRECCION_ENTREGA_CLIENTE
      ,[CELULAR_CLIENTE] = @CELULAR_CLIENTE
      ,[CORREO_CLIENTE] = @CORREO_CLIENTE
      ,[CODIGO_GRUPO] = @CODIGO_GRUPO
      ,[TITULAR_GRUPO_CLIENTE] = @TITULAR_GRUPO_CLIENTE
      ,[CODIGO_PERSONA] =@CODIGO_PERSONA
      ,[CODIGO_EMPLEADO] = @CODIGO_EMPLEADO
 WHERE [CODIGO_CLIENTE] = @CODIGO_CLIENTE

END


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_cliente_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cliente_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_cliente_sel]
as
begin
SELECT [CODIGO_CLIENTE] codigoCliente
      ,[REPRESENTANTE_CLIENTE] representanteCliente
      ,[DIRECCION_ENTREGA_CLIENTE] direccionEntregaCliente
      ,[CELULAR_CLIENTE] celularCliente
      ,[CODIGO_GRUPO] codigoGrupo
      ,[CODIGO_PERSONA] codigoPersona
      ,[CODIGO_EMPLEADO] codigoEmpleado
  FROM [CLIENTE]
end

' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_buscar_id_cliente]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_buscar_id_cliente]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROC [dbo].[usp_buscar_id_cliente]
 @CODIGO_CLIENTE bigint

AS
BEGIN

SELECT [CODIGO_CLIENTE]
      ,[REPRESENTANTE_CLIENTE]
      ,[DIRECCION_ENTREGA_CLIENTE]
      ,[CELULAR_CLIENTE]
      ,[CORREO_CLIENTE]
      ,[CODIGO_GRUPO]
      ,[TITULAR_GRUPO_CLIENTE]
      ,[CODIGO_PERSONA]
      ,[CODIGO_EMPLEADO]
  FROM [CLIENTE]
WHERE [CODIGO_CLIENTE] = @CODIGO_CLIENTE



END


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_ins_cliente]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_ins_cliente]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROC [dbo].[usp_ins_cliente]
 @REPRESENTANTE_CLIENTE nvarchar(50)
,@DIRECCION_ENTREGA_CLIENTE nvarchar(50)
,@CELULAR_CLIENTE bigint
,@CORREO_CLIENTE nvarchar(50)
,@CODIGO_GRUPO bigint
,@TITULAR_GRUPO_CLIENTE bit
,@CODIGO_PERSONA bigint
,@CODIGO_EMPLEADO bigint

AS
BEGIN
INSERT INTO [CLIENTE]
           ([REPRESENTANTE_CLIENTE]
           ,[DIRECCION_ENTREGA_CLIENTE]
           ,[CELULAR_CLIENTE]
           ,[CORREO_CLIENTE]
           ,[CODIGO_GRUPO]
           ,[TITULAR_GRUPO_CLIENTE]
           ,[CODIGO_PERSONA]
           ,[CODIGO_EMPLEADO])
     VALUES
           ( @REPRESENTANTE_CLIENTE 
		,@DIRECCION_ENTREGA_CLIENTE 
		,@CELULAR_CLIENTE 
		,@CORREO_CLIENTE
		,@CODIGO_GRUPO 
		,@TITULAR_GRUPO_CLIENTE 
		,@CODIGO_PERSONA 
		,@CODIGO_EMPLEADO 
           
           )
END


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_usuario_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_usuario_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROC [dbo].[usp_usuario_sel]
AS
BEGIN
SELECT [CODIGO_USUARIO] id
      ,[CODIGO_LOGIN_USUARIO] as ''login''
      ,[PASSWORD_USUARIO] clave
      ,[CODIGO_EMPLEADO] as codigoEmpleado
  FROM [USUARIO]
END


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_visita_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_visita_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_visita_sel]
as
begin
SELECT [CODIGO_VISITA] codigoVisita
      ,convert(varchar,[FECHA_VISITA],20) strfechaVisita
      ,convert(varchar,[HORA_INICIO_VISITA],20) strhoraInicioVisita
      ,convert(varchar,[HORA_FINAL_VISITA],20) strhoraFinalVisita
      ,[CODIGO_TIPO_VISITA] codigoTipoVisita
      ,[CODIGO_EMPLEADO] codigoEmpleado
      ,[CODIGO_CLIENTE] codigoCliente
      ,[CODIGO_ESTADO_VISITA] codigoEstadoVisita
  FROM [VISITA]
end' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_actualizar_estado_visita]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_actualizar_estado_visita]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_actualizar_estado_visita]
@codigo_visita bigint,
@codigo_estado_visita bigint,
@hora_inicio_visita datetime = null,
@hora_fin_visita datetime = null
as
begin
update VISITA
set CODIGO_ESTADO_VISITA = @codigo_estado_visita,
HORA_INICIO_VISITA = @hora_inicio_visita,
HORA_FINAL_VISITA = @hora_fin_visita
where CODIGO_VISITA = @codigo_visita
end' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_documento_pago_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_documento_pago_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROC [dbo].[usp_documento_pago_sel]
as
begin
SELECT [CODIGO_DOCUMENTO_PAGO] codigoDocumentoPago
      ,convert(varchar,[FECHA_EMISION_DOCUMENTO_PAGO],21) strfechaEmisionDocumentoPago
      ,CASE UPPER([TIPO_DOCUMENTO_PAGO]) WHEN ''F'' THEN ''FACTURA'' ELSE ''LETRA'' END  tipoDocumentoPago
      ,[PLAZO_DOCUMENTO_PAGO] plazoDocumentoPago
      ,convert(varchar,[FECHA_VENCIMIENTO_DOCUMENTO_PAGO],21) strfechaVencimientoDocumentoPago
      ,[IMPORTE_ORIGINAL_DOCUMENTO_PAGO] importeOriginalDocumentoPago
      ,[IMPORTE_AMORTIZADO_DOCUMENTO_PAGO] importeAmortizadoDocumentoPago
      ,[IMPORTE_DESCONTADO_DOCUMENTO_PAGO] importeDescontadoDocumentoPago
      ,[IMPORTE_PENDIENTE_DOCUMENTO_PAGO] importePendienteDocumentoPago
      ,[IMPORTE_IGV_DOCUMENTO_PAGO] importeIgvDocumentoPago
      ,[CODIGO_CLIENTE] codigoCliente
      ,ISNULL((CASE UPPER([TIPO_DOCUMENTO_PAGO]) WHEN ''F'' THEN (SELECT TOP 1 [SERIE_FACTURA]+RIGHT(''-00000000''+cast([NUMERO_FACTURA] as varchar),10) FROM [FACTURA]
WHERE [CODIGO_DOCUMENTO_PAGO] = d.CODIGO_DOCUMENTO_PAGO)  
ELSE (SELECT TOP 1 RIGHT(''00000000''+cast([NUMERO_LETRA]as varchar),9) FROM [LETRA]
WHERE [CODIGO_DOCUMENTO_PAGO] = d.CODIGO_DOCUMENTO_PAGO)  END),'' '') as ReferenciaDocumentoPago
FROM [DOCUMENTO_PAGO] as d
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_pedido_update]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_pedido_update]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'
CREATE proc [dbo].[usp_pedido_update]
 @CODIGO_PEDIDO bigint
,@IMPORTE_PEDIDO decimal(18,2)
,@CODIGO_MODALIDAD_PAGO bigint
,@INSTRUCCIONES_PEDIDO nvarchar(500)
,@CODIGO_EMPRESA_CARGA bigint
,@ACEPTA_RETENCION_PEDIDO bit
,@ESTA_RETENIDO_PEDIDO bit
,@FALTA_IMPORTE_PEDIDO bit
,@FALTA_STOCK_PEDIDO bit
,@ESTADO_PEDIDO char(10)
,@CODIGO_MOVIL_PEDIDO nvarchar(50)
as
begin
UPDATE [PEDIDO]
   SET [IMPORTE_PEDIDO] = @IMPORTE_PEDIDO
      ,[CODIGO_MODALIDAD_PAGO] = @CODIGO_MODALIDAD_PAGO
      ,[INSTRUCCIONES_PEDIDO] = @INSTRUCCIONES_PEDIDO
      ,[CODIGO_EMPRESA_CARGA] = @CODIGO_EMPRESA_CARGA
      ,[ACEPTA_RETENCION_PEDIDO] = @ACEPTA_RETENCION_PEDIDO
      ,[ESTA_RETENIDO_PEDIDO] = @ESTA_RETENIDO_PEDIDO
      ,[FALTA_IMPORTE_PEDIDO] = @FALTA_IMPORTE_PEDIDO
      ,[FALTA_STOCK_PEDIDO] = @FALTA_STOCK_PEDIDO
      ,[ESTADO_PEDIDO] = @ESTADO_PEDIDO
      ,CODIGO_MOVIL_PEDIDO = @CODIGO_MOVIL_PEDIDO
 WHERE CODIGO_PEDIDO = @CODIGO_PEDIDO
 
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_pedido_sel_por_id]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_pedido_sel_por_id]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'
CREATE PROC [dbo].[usp_pedido_sel_por_id]
@CODIGO_PEDIDO bigint
as
BEGIN
SELECT [CODIGO_PEDIDO] codigoPedido
      ,convert(varchar,[FECHA_INGRESO_PEDIDO],20)  strfechaIngresoPedido
      ,[IMPORTE_PEDIDO] importePedido
      ,[CODIGO_MODALIDAD_PAGO] codigoFormaPago
      ,IMPORTE_RESERVADO_PEDIDO lineaReservadaPedido
      ,[INSTRUCCIONES_PEDIDO]instruccionesEspeciales
      ,[CODIGO_EMPRESA_CARGA] codigoEmpresaCarga
      ,[ACEPTA_RETENCION_PEDIDO] aceptaRetencionPedido
      ,[ESTA_RETENIDO_PEDIDO] estaRetenidoPedido
      ,[ESTADO_PEDIDO] estadoPedido
      ,T.CODIGO_VISITA codigoVisita
      ,P.CODIGO_MOVIL_PEDIDO codigoMovil
  FROM [PEDIDO] P
  INNER JOIN TRANSACCION T
  ON p.CODIGO_TRANSACCION = T.CODIGO_TRANSACCION
  WHERE [CODIGO_PEDIDO] = @CODIGO_PEDIDO
END
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_pedido_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_pedido_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'
CREATE PROC [dbo].[usp_pedido_sel]
as
BEGIN
SELECT [CODIGO_PEDIDO] codigoPedido
      ,convert(varchar,[FECHA_INGRESO_PEDIDO],20)  strfechaIngresoPedido
      ,[IMPORTE_PEDIDO] importePedido
      ,[CODIGO_MODALIDAD_PAGO] codigoFormaPago
      ,IMPORTE_RESERVADO_PEDIDO lineaReservadaPedido
      ,[INSTRUCCIONES_PEDIDO]instruccionesEspeciales
      ,[CODIGO_EMPRESA_CARGA] codigoEmpresaCarga
      ,[ACEPTA_RETENCION_PEDIDO] aceptaRetencionPedido
      ,[ESTA_RETENIDO_PEDIDO] estaRetenidoPedido
      ,[ESTADO_PEDIDO] estadoPedido
      ,T.CODIGO_VISITA codigoVisita
      ,P.CODIGO_MOVIL_PEDIDO codigoMovil
  FROM [PEDIDO] P
  INNER JOIN TRANSACCION T
  ON p.CODIGO_TRANSACCION = T.CODIGO_TRANSACCION  
END
' 
END
GO
/****** Object:  StoredProcedure [dbo].[USP_PEDIDO_INS]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[USP_PEDIDO_INS]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'
CREATE PROC [dbo].[USP_PEDIDO_INS]

			@FECHA_INGRESO_PEDIDO date
           ,@IMPORTE_PEDIDO decimal(18,2)
           ,@CODIGO_MODALIDAD_PAGO bigint
           ,@INSTRUCCIONES_PEDIDO nvarchar(500)
           ,@CODIGO_EMPRESA_CARGA  bigint
           ,@IMPORTE_RESERVADO_PEDIDO decimal(18,2)
           ,@ACEPTA_RETENCION_PEDIDO bit
           ,@ESTA_RETENIDO_PEDIDO bit
           ,@FALTA_IMPORTE_PEDIDO bit
           ,@FALTA_STOCK_PEDIDO bit
           ,@ESTADO_PEDIDO char(10)
           ,@CODIGO_VISITA bigint
           ,@CODIGO_MOVIL_PEDIDO nvarchar(50)

AS
			
BEGIN			
DECLARE @CODIGO_TRANSACCION bigint
DECLARE @LIMITE_RETENCION_PEDIDO int
DECLARE @PAGO_CONTADO_PEDIDO bit
DECLARE @FECHA_ANULACION_PEDIDO date
DECLARE @CODIGO_TIPO_TRANSACCION bigint

set @CODIGO_TIPO_TRANSACCION = 1
-- obtener limite de retencion de pedido
SELECT @LIMITE_RETENCION_PEDIDO =cast(rtrim(VALOR_PARAMETRO) as int) 
FROM PARAMETRO 
WHERE CODIGO_PARAMETRO =1

set @PAGO_CONTADO_PEDIDO = 0;
set @FECHA_ANULACION_PEDIDO = NULL

DECLARE @PERSONA_REGISTRO_TRANSACCION bigint

--solucion temporal, luego tiene que venir el codigo del empleado en el pedido


SELECT TOP 1 @PERSONA_REGISTRO_TRANSACCION = CODIGO_EMPLEADO
FROM EMPLEADO
--insertar transaccion

INSERT INTO [TRANSACCION]
           ([CODIGO_TIPO_TRANSACCION]
           ,[FECHA_TRANSACCION]
           ,[PERSONA_REGISTRO_TRANSACCION]
           ,[CODIGO_VISITA])
     VALUES
           (@CODIGO_TIPO_TRANSACCION
           ,@FECHA_INGRESO_PEDIDO
           ,@PERSONA_REGISTRO_TRANSACCION
           ,@CODIGO_VISITA)
           
SELECT @CODIGO_TRANSACCION=SCOPE_IDENTITY()


INSERT INTO [PEDIDO]
           ([FECHA_INGRESO_PEDIDO]
           ,[LIMITE_RETENCION_PEDIDO]
           ,[IMPORTE_PEDIDO]
           ,[CODIGO_MODALIDAD_PAGO]
           ,[PAGO_CONTADO_PEDIDO]
           ,[INSTRUCCIONES_PEDIDO]
           ,[CODIGO_EMPRESA_CARGA]
           ,[IMPORTE_RESERVADO_PEDIDO]
           ,[ACEPTA_RETENCION_PEDIDO]
           ,[ESTA_RETENIDO_PEDIDO]
           ,[FALTA_IMPORTE_PEDIDO]
           ,[FALTA_STOCK_PEDIDO]
           ,[ESTADO_PEDIDO]
           ,[FECHA_ANULACION_PEDIDO]
           ,[CODIGO_TRANSACCION]
           ,CODIGO_MOVIL_PEDIDO
           )
     VALUES
           (@FECHA_INGRESO_PEDIDO
           ,@LIMITE_RETENCION_PEDIDO
           ,@IMPORTE_PEDIDO
           ,@CODIGO_MODALIDAD_PAGO
           ,@PAGO_CONTADO_PEDIDO
           ,@INSTRUCCIONES_PEDIDO
           ,@CODIGO_EMPRESA_CARGA
           ,@IMPORTE_RESERVADO_PEDIDO
           ,@ACEPTA_RETENCION_PEDIDO
           ,@ESTA_RETENIDO_PEDIDO
           ,@FALTA_IMPORTE_PEDIDO
           ,@FALTA_STOCK_PEDIDO
           ,@ESTADO_PEDIDO
           ,@FECHA_ANULACION_PEDIDO
           ,@CODIGO_TRANSACCION
           ,@CODIGO_MOVIL_PEDIDO)

SELECT SCOPE_IDENTITY() as codigoPedido

END
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_cobranza_upd]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cobranza_upd]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_cobranza_upd]
@CODIGO_COBRANZA bigint,
@FECHA_COBRANZA date,
@IMPORTE_COBRANZA decimal,
@CODIGO_MEDIO_PAGO bigint,
@CODIGO_MOVIL_COBRANZA nvarchar(50)

as
begin
UPDATE [COBRANZA]
   SET [FECHA_COBRANZA] = @FECHA_COBRANZA
      ,[IMPORTE_COBRANZA] = @IMPORTE_COBRANZA
      ,[CODIGO_MEDIO_PAGO] = @CODIGO_MEDIO_PAGO
      ,[CODIGO_MOVIL_COBRANZA] = @CODIGO_MOVIL_COBRANZA
 WHERE CODIGO_COBRANZA = @CODIGO_COBRANZA
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_cobranza_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cobranza_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROC [dbo].[usp_cobranza_sel]

as
begin
SELECT [CODIGO_COBRANZA] codigoCobranza
      ,convert(varchar,[FECHA_COBRANZA],20) strfechaCobranza
      ,[IMPORTE_COBRANZA] importeCobranza
      ,[CODIGO_MEDIO_PAGO] codigoMedioPago
      ,[CODIGO_MOVIL_COBRANZA] codigoMovil
      ,t.CODIGO_VISITA codigoVisita
  FROM [COBRANZA] c  inner join [TRANSACCION] t
  on c.CODIGO_TRANSACCION = t.CODIGO_TRANSACCION
  
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_cobranza_ins]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cobranza_ins]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_cobranza_ins]
			@FECHA_COBRANZA date
           ,@IMPORTE_COBRANZA decimal(18,2)
           ,@CODIGO_MEDIO_PAGO bigint
           ,@CODIGO_MOVIL_COBRANZA nvarchar(50)
           ,@CODIGO_VISITA BIGINT
as
begin
DECLARE @CODIGO_TRANSACCION AS bigint
DECLARE @CODIGO_TIPO_TRANSACCION bigint

set @CODIGO_TIPO_TRANSACCION = 1

DECLARE @PERSONA_REGISTRO_TRANSACCION bigint
--solucion temporal, luego tiene que venir el codigo del empleado en el pedido
SELECT TOP 1 @PERSONA_REGISTRO_TRANSACCION = CODIGO_EMPLEADO
FROM EMPLEADO


INSERT INTO [TRANSACCION]
           ([CODIGO_TIPO_TRANSACCION]
           ,[FECHA_TRANSACCION]
           ,[PERSONA_REGISTRO_TRANSACCION]
           ,[CODIGO_VISITA])
     VALUES
           (@CODIGO_TIPO_TRANSACCION
           ,@FECHA_COBRANZA
           ,@PERSONA_REGISTRO_TRANSACCION
           ,@CODIGO_VISITA)
           
SELECT @CODIGO_TRANSACCION=SCOPE_IDENTITY()

INSERT INTO [COBRANZA]
           ([FECHA_COBRANZA]
           ,[IMPORTE_COBRANZA]
           ,[CODIGO_TRANSACCION]
           ,[CODIGO_MEDIO_PAGO]
           ,[CODIGO_MOVIL_COBRANZA])
     VALUES
           (@FECHA_COBRANZA
           ,@IMPORTE_COBRANZA
           ,@CODIGO_TRANSACCION
           ,@CODIGO_MEDIO_PAGO
           ,@CODIGO_MOVIL_COBRANZA)
           
SELECT SCOPE_IDENTITY() as codigoCobranza           
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_update]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_update]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_detalle_pedido_update]
@CODIGO_PEDIDO bigint
,@CODIGO_PRODUCTO bigint
,@PRECIO_PRODUCTO_PEDIDO decimal(18,2)
as
begin
UPDATE [PRODUCTO_PEDIDO]
SET [PRECIO_PRODUCTO_PEDIDO] = @PRECIO_PRODUCTO_PEDIDO
WHERE CODIGO_PEDIDO = @CODIGO_PEDIDO and CODIGO_PRODUCTO = @CODIGO_PRODUCTO
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_sel_codigo]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_sel_codigo]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'
CREATE proc [dbo].[usp_detalle_pedido_sel_codigo]
  @CODIGO_PEDIDO  bigint
 ,@CODIGO_PRODUCTO bigint
as
begin
SELECT [CODIGO_PEDIDO] codigoPedido
      ,[CODIGO_PRODUCTO] codigoProducto
      ,[PRECIO_PRODUCTO_PEDIDO] precioUnitario
  FROM [PRODUCTO_PEDIDO]
  WHERE [CODIGO_PEDIDO] = @CODIGO_PEDIDO AND [CODIGO_PRODUCTO] = @CODIGO_PRODUCTO
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'
CREATE proc [dbo].[usp_detalle_pedido_sel]
  @CODIGO_PEDIDO  bigint
as
begin
SELECT [CODIGO_PEDIDO] codigoPedido
      ,[CODIGO_PRODUCTO] codigoProducto
      ,[PRECIO_PRODUCTO_PEDIDO] precioUnitario
  FROM [PRODUCTO_PEDIDO]
  WHERE [CODIGO_PEDIDO] = @CODIGO_PEDIDO 
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_detalle_pedido_ins]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_detalle_pedido_ins]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'
CREATE PROC [dbo].[usp_detalle_pedido_ins]

@CODIGO_PEDIDO bigint
,@CODIGO_PRODUCTO bigint
,@PRECIO_PRODUCTO_PEDIDO decimal(18,2)

AS
BEGIN
INSERT INTO [PRODUCTO_PEDIDO]
           ([CODIGO_PEDIDO]
           ,[CODIGO_PRODUCTO]
           ,[PRECIO_PRODUCTO_PEDIDO])
     VALUES
           (@CODIGO_PEDIDO
           ,@CODIGO_PRODUCTO
           ,@PRECIO_PRODUCTO_PEDIDO)
END


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_update]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_update]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_amortizacion_update]
@CODIGO_AMORTIZACION bigint,
@CODIGO_COBRANZA bigint,
@IMPORTE_AMORTIZACION decimal(18,2),
@CODIGO_DOCUMENTO_PAGO bigint,
@ANOTACION_AMORTIZACION nvarchar(max)
as
begin
UPDATE [AMORTIZACION]
   SET [IMPORTE_AMORTIZACION] = @IMPORTE_AMORTIZACION
      ,[ANOTACION_AMORTIZACION] = @ANOTACION_AMORTIZACION
      ,[CODIGO_COBRANZA] = @CODIGO_COBRANZA
      ,[CODIGO_DOCUMENTO_PAGO] = @CODIGO_DOCUMENTO_PAGO
 WHERE CODIGO_AMORTIZACION = @CODIGO_AMORTIZACION
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_sel_codigo]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_sel_codigo]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_amortizacion_sel_codigo]
@CODIGO_COBRANZA bigint,
@CODIGO_AMORTIZACION bigint
as
begin
SELECT [CODIGO_AMORTIZACION] codigoAmortizacion
      ,[IMPORTE_AMORTIZACION] importeAmortizacion
      ,[ANOTACION_AMORTIZACION] anotacionAmortizacion
      ,[CODIGO_COBRANZA] codigoCobranza
      ,[CODIGO_DOCUMENTO_PAGO] codigoDocumentoPago
FROM [AMORTIZACION]
WHERE   CODIGO_COBRANZA = @CODIGO_COBRANZA
AND CODIGO_AMORTIZACION = @CODIGO_AMORTIZACION
end
' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_amortizacion_sel]
@CODIGO_COBRANZA bigint
as
begin
SELECT [CODIGO_AMORTIZACION] codigoAmortizacion
      ,[IMPORTE_AMORTIZACION] importeAmortizacion
      ,[ANOTACION_AMORTIZACION] anotacionAmortizacion
      ,[CODIGO_COBRANZA] codigoCobranza
      ,[CODIGO_DOCUMENTO_PAGO] codigoDocumentoPago
FROM [AMORTIZACION]
WHERE   CODIGO_COBRANZA = @CODIGO_COBRANZA
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_amortizacion_ins]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_amortizacion_ins]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_amortizacion_ins]
@CODIGO_COBRANZA bigint,
@IMPORTE_AMORTIZACION decimal(18,2),
@ANOTACION_AMORTIZACION nvarchar(max),
@CODIGO_DOCUMENTO_PAGO bigint
as
begin
INSERT INTO [AMORTIZACION]
           ([IMPORTE_AMORTIZACION]
           ,[ANOTACION_AMORTIZACION]
           ,[CODIGO_COBRANZA]
           ,[CODIGO_DOCUMENTO_PAGO])
     VALUES
           (@IMPORTE_AMORTIZACION
           ,@ANOTACION_AMORTIZACION
           ,@CODIGO_COBRANZA
           ,@CODIGO_DOCUMENTO_PAGO)
           
SELECT SCOPE_IDENTITY() as codigoAmortizacion

end

' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_update]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_update]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'create proc [dbo].[usp_talla_pedido_update]
			@CODIGO_PEDIDO bigint
           ,@CODIGO_PRODUCTO bigint
           ,@NUMERO_TALLA smallint
           ,@CANTIDAD_PEDIDA_TALLA_PEDIDA bigint
as 
begin
UPDATE [TALLA_PEDIDA]
   SET [CANTIDAD_PEDIDA_TALLA_PEDIDA] = @CANTIDAD_PEDIDA_TALLA_PEDIDA
 WHERE [CODIGO_PEDIDO] =@CODIGO_PEDIDO and [CODIGO_PRODUCTO] =@CODIGO_PRODUCTO 
 and [NUMERO_TALLA] =@NUMERO_TALLA
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_sel_codigo]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_sel_codigo]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_talla_pedido_sel_codigo]
@CODIGO_PEDIDO bigint
,@CODIGO_PRODUCTO bigint
,@NUMERO_TALLA smallint

as
begin
SELECT [CODIGO_PEDIDO] codigoPedido
      ,[CODIGO_PRODUCTO] codigoProducto
      ,[NUMERO_TALLA] numeroTalla
      ,[CANTIDAD_PEDIDA_TALLA_PEDIDA] cantidad
  FROM [TALLA_PEDIDA]
  WHERE [CODIGO_PEDIDO] = @CODIGO_PEDIDO AND [CODIGO_PRODUCTO] = @CODIGO_PRODUCTO AND [NUMERO_TALLA] = @NUMERO_TALLA
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_sel]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_sel]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE proc [dbo].[usp_talla_pedido_sel]
@CODIGO_PEDIDO bigint
,@CODIGO_PRODUCTO bigint


as
begin
SELECT [CODIGO_PEDIDO] codigoPedido
      ,[CODIGO_PRODUCTO] codigoProducto
      ,[NUMERO_TALLA] numeroTalla
      ,[CANTIDAD_PEDIDA_TALLA_PEDIDA] cantidad
  FROM [TALLA_PEDIDA]
  WHERE [CODIGO_PEDIDO] = @CODIGO_PEDIDO AND [CODIGO_PRODUCTO] = @CODIGO_PRODUCTO 
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_pedido_ins]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_pedido_ins]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROC [dbo].[usp_talla_pedido_ins]
		    @CODIGO_PEDIDO bigint
           ,@CODIGO_PRODUCTO bigint
           ,@NUMERO_TALLA smallint
           ,@CANTIDAD_PEDIDA_TALLA_PEDIDA bigint
           ,@CANTIDAD_SEPARADA_TALLA_PEDIDA bigint
AS
BEGIN
INSERT INTO [TALLA_PEDIDA]
           ([CODIGO_PEDIDO]
           ,[CODIGO_PRODUCTO]
           ,[NUMERO_TALLA]
           ,[CANTIDAD_PEDIDA_TALLA_PEDIDA]
           ,[CANTIDAD_SEPARADA_TALLA_PEDIDA])
     VALUES
           (@CODIGO_PEDIDO
           ,@CODIGO_PRODUCTO
           ,@NUMERO_TALLA
           ,@CANTIDAD_PEDIDA_TALLA_PEDIDA
           ,@CANTIDAD_SEPARADA_TALLA_PEDIDA)
end


' 
END
GO
/****** Object:  StoredProcedure [dbo].[USP_PROCESAR_PEDIDO]    Script Date: 12/01/2013 17:37:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[USP_PROCESAR_PEDIDO]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'

CREATE PROCEDURE [dbo].[USP_PROCESAR_PEDIDO]
@cod_pedido bigint
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
	DECLARE @estadoPedido						nvarchar(10);
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
		WHERE A.ESTA_RETENIDO_PEDIDO = @TRUE AND A.CODIGO_PEDIDO = @cod_pedido
		
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
			SET @destinatarioCorreo = ISNULL(@correoCliente, '''');
			SET @copiaOcultaCorreo = ISNULL(@correoVendedor, '''');
							
			--SI PEDIDO CUMPLIO SUS REQUISITOS SE APRUEBA Y GENERA ORDEN DE DESPACHO
			IF (@faltaImportePedido = @FALSE AND @faltaStockPedido = @FALSE)
				BEGIN
					UPDATE PEDIDO
					SET @estaRetenidoPedido = ESTA_RETENIDO_PEDIDO = @FALSE,
					    @estadoPedido = ESTADO_PEDIDO = ''Aprobado''
					WHERE CURRENT OF @pedidoRetenido;
				--	GENERACIÓN DE ORDEN DE DESPACHO; como parte de ello, se pasa datos importe Comprometido a importe
				--  utilizado en la línea de crédito o saldo en cuenta y actualiza despachado, disponibles y comprometido de stocks
					INSERT INTO [dbo].[ORDEN_DESPACHO] (CODIGO_PEDIDO, ESTADO_ORDEN_DESPACHO)
					VALUES (@codigoPedido, ''Aprobada''); 
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
					SET @cuerpoCorreo = ''Estimado Cliente:<BR><BR>Informamos a usted que su pedido de código '' + CAST(@codigoPedido AS nvarchar(15)) +
						                '' ha pasado al proceso de despacho.<BR><BR>Saludos cordiales,<BR><BR>Calzado Atlas S.A.'';   
					SET @asuntoCorreo = ''Despacho del pedido '' + CAST(@codigoPedido AS nvarchar(15)) ;
					EXEC msdb.dbo.sp_send_dbmail
						@profile_name = ''Speedy'',
						@recipients = @destinatarioCorreo,
						@blind_copy_recipients = @copiaOcultaCorreo,
						@body_format = ''HTML'',
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
								@estadoPedido = ESTADO_PEDIDO = ''Anulado'',
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
							SET @cuerpoCorreo = ''Estimado Cliente:<BR><BR>Informamos a usted que su pedido de código '' + CAST(@codigoPedido AS nvarchar(15)) + 
							                    '' ha sido anulado debido a que no se dieron las condiciones necesarias para aprobarlo dentro del plazo de '' + 
							                    '' espera convenido.<BR><BR>Atentamente,<BR><BR>Calzado Atlas S.A.'';   
							SET @asuntoCorreo = ''Anulación del pedido '' + CAST(@codigoPedido AS nvarchar(15)) ;
							EXEC msdb.dbo.sp_send_dbmail
								@profile_name = ''Speedy'',
								@recipients = @destinatarioCorreo,
								@blind_copy_recipients = @copiaOcultaCorreo,
								@body_format = ''HTML'',
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

' 
END
GO

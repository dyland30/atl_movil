USE [ATL]
GO
/****** Object:  StoredProcedure [dbo].[usp_visita_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_visita_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_visita_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_ins_cliente]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_ins_cliente]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_ins_cliente]
GO
/****** Object:  StoredProcedure [dbo].[usp_usuario_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_usuario_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_usuario_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_act_cliente]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_act_cliente]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_act_cliente]
GO
/****** Object:  StoredProcedure [dbo].[usp_buscar_id_cliente]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_buscar_id_cliente]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_buscar_id_cliente]
GO
/****** Object:  StoredProcedure [dbo].[usp_cliente_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_cliente_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_cliente_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_empleado_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_empleado_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_empleado_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_talla_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_talla_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_talla_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_persona_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_persona_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_persona_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_producto_forma_pago_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_producto_forma_pago_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_producto_forma_pago_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_empresa_carga_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_empresa_carga_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_empresa_carga_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_estado_visita_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_estado_visita_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_estado_visita_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_forma_pago_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_forma_pago_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_forma_pago_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_grupo_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_grupo_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_grupo_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_producto_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_producto_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_producto_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_tipo_visita_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_tipo_visita_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_tipo_visita_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_tipodocumento_sel]    Script Date: 11/12/2013 00:06:17 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_tipodocumento_sel]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[usp_tipodocumento_sel]
GO
/****** Object:  StoredProcedure [dbo].[usp_tipodocumento_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_tipo_visita_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_producto_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_grupo_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_forma_pago_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_estado_visita_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_empresa_carga_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_producto_forma_pago_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_persona_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_talla_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_empleado_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_cliente_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_buscar_id_cliente]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_act_cliente]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_usuario_sel]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_ins_cliente]    Script Date: 11/12/2013 00:06:17 ******/
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
/****** Object:  StoredProcedure [dbo].[usp_visita_sel]    Script Date: 11/12/2013 00:06:17 ******/
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

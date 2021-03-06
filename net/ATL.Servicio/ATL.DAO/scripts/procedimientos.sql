USE [ATL]
GO
/****** Object:  StoredProcedure [dbo].[usp_tipodocumento_sel]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[usp_tipodocumento_sel]
as
begin
SELECT [CODIGO_TIPO_DOCUMENTO]
      ,[SIGLA_TIPO_DOCUMENTO]
      ,[NOMBRE_TIPO_DOCUMENTO]
  FROM [TIPO_DOCUMENTO]

end
GO
/****** Object:  StoredProcedure [dbo].[usp_grupo_sel]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[usp_grupo_sel]
as
begin

SELECT [CODIGO_GRUPO] codigoGrupo
      ,[NOMBRE_GRUPO] nombreGrupo
  FROM [GRUPO]
  
end
GO
/****** Object:  StoredProcedure [dbo].[usp_persona_sel]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--codigoPersona, codigoTipoDocumento,nombrePersona, 
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
GO
/****** Object:  StoredProcedure [dbo].[usp_empleado_sel]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[usp_empleado_sel]
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
GO
/****** Object:  StoredProcedure [dbo].[usp_cliente_sel]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[usp_cliente_sel]
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
GO
/****** Object:  StoredProcedure [dbo].[usp_buscar_id_cliente]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[usp_buscar_id_cliente]
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
GO
/****** Object:  StoredProcedure [dbo].[usp_act_cliente]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[usp_act_cliente]
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
GO
/****** Object:  StoredProcedure [dbo].[usp_usuario_sel]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[usp_usuario_sel]
AS
BEGIN
SELECT [CODIGO_USUARIO] id
      ,[CODIGO_LOGIN_USUARIO] as 'login'
      ,[PASSWORD_USUARIO] clave
      ,[CODIGO_EMPLEADO] as codigoEmpleado
  FROM [USUARIO]
END
GO
/****** Object:  StoredProcedure [dbo].[usp_ins_cliente]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[usp_ins_cliente]
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
GO
/****** Object:  StoredProcedure [dbo].[usp_visita_sel]    Script Date: 11/10/2013 12:57:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[usp_visita_sel]
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
end
GO

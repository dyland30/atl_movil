package com.atl.atlmovil.entidades;

public class Persona {
private long codigoPersona;
private String tipoPersona;
private long codigoTipoDocumento;
private String nombrePersona;
private String documentoPersona;
private String direccionPersona;
public long getCodigoPersona() {
	return codigoPersona;
}
public void setCodigoPersona(long codigoPersona) {
	this.codigoPersona = codigoPersona;
}
public String getTipoPersona() {
	return tipoPersona;
}
public void setTipoPersona(String tipoPersona) {
	this.tipoPersona = tipoPersona;
}
public long getCodigoTipoDocumento() {
	return codigoTipoDocumento;
}
public void setCodigoTipoDocumento(long codigoTipoDocumento) {
	this.codigoTipoDocumento = codigoTipoDocumento;
}
public String getDocumentoPersona() {
	return documentoPersona;
}
public void setDocumentoPersona(String documentoPersona) {
	this.documentoPersona = documentoPersona;
}
public String getDireccionPersona() {
	return direccionPersona;
}
public void setDireccionPersona(String direccionPersona) {
	this.direccionPersona = direccionPersona;
}
public String getNombrePersona() {
	return nombrePersona;
}
public void setNombrePersona(String nombrePersona) {
	this.nombrePersona = nombrePersona;
}

}

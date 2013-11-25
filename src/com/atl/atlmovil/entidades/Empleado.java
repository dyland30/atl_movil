package com.atl.atlmovil.entidades;

import java.util.Date;

public class Empleado {
private long codigoEmpleado;
private Date fechaIngresoEmpleado;
private String areaEmpleado;
private String cargoEmpleado;
private Date fechaCeseEmpleado;
private long jefeEmpleado;
private long codigoPersona;
private String strfechaIngresoEmpleado;
private String strfechaCeseEmpleado;

private Persona persona;


public long getCodigoEmpleado() {
	return codigoEmpleado;
}
public void setCodigoEmpleado(long codigoEmpleado) {
	this.codigoEmpleado = codigoEmpleado;
}
public Date getFechaIngresoEmpleado() {
	return fechaIngresoEmpleado;
}
public void setFechaIngresoEmpleado(Date fechaIngresoEmpleado) {
	this.fechaIngresoEmpleado = fechaIngresoEmpleado;
}
public String getAreaEmpleado() {
	return areaEmpleado;
}
public void setAreaEmpleado(String areaEmpleado) {
	this.areaEmpleado = areaEmpleado;
}
public String getCargoEmpleado() {
	return cargoEmpleado;
}
public void setCargoEmpleado(String cargoEmpleado) {
	this.cargoEmpleado = cargoEmpleado;
}
public Date getFechaCeseEmpleado() {
	return fechaCeseEmpleado;
}
public void setFechaCeseEmpleado(Date fechaCeseEmpleado) {
	this.fechaCeseEmpleado = fechaCeseEmpleado;
}
public long getJefeEmpleado() {
	return jefeEmpleado;
}
public void setJefeEmpleado(long jefeEmpleado) {
	this.jefeEmpleado = jefeEmpleado;
}
public long getCodigoPersona() {
	return codigoPersona;
}
public void setCodigoPersona(long codigoPersona) {
	this.codigoPersona = codigoPersona;
}
public String getStrfechaIngresoEmpleado() {
	return strfechaIngresoEmpleado;
}
public void setStrfechaIngresoEmpleado(String strfechaIngresoEmpleado) {
	this.strfechaIngresoEmpleado = strfechaIngresoEmpleado;
}
public String getStrfechaCeseEmpleado() {
	return strfechaCeseEmpleado;
}
public void setStrfechaCeseEmpleado(String strfechaCeseEmpleado) {
	this.strfechaCeseEmpleado = strfechaCeseEmpleado;
}
public Persona getPersona() {
	return persona;
}
public void setPersona(Persona persona) {
	this.persona = persona;
}

}

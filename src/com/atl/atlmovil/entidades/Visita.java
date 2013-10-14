package com.atl.atlmovil.entidades;
import java.util.Date;
public class Visita {
private long codigoVisita;
private Date fechaVisita;
private Date horaInicioVisita;
private Date horaFinalVisita;
private long codigoTipoVisita;
private long codigoEmpleado;
private long codigoCliente;
private long codigoEstadoVisita;
public long getCodigoVisita() {
	return codigoVisita;
}
public void setCodigoVisita(long codigoVisita) {
	this.codigoVisita = codigoVisita;
}
public Date getFechaVisita() {
	return fechaVisita;
}
public void setFechaVisita(Date fechaVisita) {
	this.fechaVisita = fechaVisita;
}
public Date getHoraInicioVisita() {
	return horaInicioVisita;
}
public void setHoraInicioVisita(Date horaInicioVisita) {
	this.horaInicioVisita = horaInicioVisita;
}
public Date getHoraFinalVisita() {
	return horaFinalVisita;
}
public void setHoraFinalVisita(Date horaFinalVisita) {
	this.horaFinalVisita = horaFinalVisita;
}
public long getCodigoTipoVisita() {
	return codigoTipoVisita;
}
public void setCodigoTipoVisita(long codigoTipoVisita) {
	this.codigoTipoVisita = codigoTipoVisita;
}
public long getCodigoEmpleado() {
	return codigoEmpleado;
}
public void setCodigoEmpleado(long codigoEmpleado) {
	this.codigoEmpleado = codigoEmpleado;
}
public long getCodigoCliente() {
	return codigoCliente;
}
public void setCodigoCliente(long codigoCliente) {
	this.codigoCliente = codigoCliente;
}
public long getCodigoEstadoVisita() {
	return codigoEstadoVisita;
}
public void setCodigoEstadoVisita(long codigoEstadoVisita) {
	this.codigoEstadoVisita = codigoEstadoVisita;
}

}

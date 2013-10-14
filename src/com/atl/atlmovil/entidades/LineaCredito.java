package com.atl.atlmovil.entidades;

import java.util.Date;

public class LineaCredito {
private long codigoLineaCredito;
private double importeLineaCredito;
private double importeUtilizadoLineaCredito;
private double importeComprometidoLineaCredito;
private Date fechaAsignacionLineaCredito;
private Date fechaVencimientoLineaCredito;
private Boolean bloqueoLineaCredito;
private long codigoGrupo;
public long getCodigoLineaCredito() {
	return codigoLineaCredito;
}
public void setCodigoLineaCredito(long codigoLineaCredito) {
	this.codigoLineaCredito = codigoLineaCredito;
}
public double getImporteLineaCredito() {
	return importeLineaCredito;
}
public void setImporteLineaCredito(double importeLineaCredito) {
	this.importeLineaCredito = importeLineaCredito;
}
public double getImporteUtilizadoLineaCredito() {
	return importeUtilizadoLineaCredito;
}
public void setImporteUtilizadoLineaCredito(double importeUtilizadoLineaCredito) {
	this.importeUtilizadoLineaCredito = importeUtilizadoLineaCredito;
}
public double getImporteComprometidoLineaCredito() {
	return importeComprometidoLineaCredito;
}
public void setImporteComprometidoLineaCredito(
		double importeComprometidoLineaCredito) {
	this.importeComprometidoLineaCredito = importeComprometidoLineaCredito;
}
public Date getFechaAsignacionLineaCredito() {
	return fechaAsignacionLineaCredito;
}
public void setFechaAsignacionLineaCredito(Date fechaAsignacionLineaCredito) {
	this.fechaAsignacionLineaCredito = fechaAsignacionLineaCredito;
}
public Date getFechaVencimientoLineaCredito() {
	return fechaVencimientoLineaCredito;
}
public void setFechaVencimientoLineaCredito(Date fechaVencimientoLineaCredito) {
	this.fechaVencimientoLineaCredito = fechaVencimientoLineaCredito;
}
public Boolean getBloqueoLineaCredito() {
	return bloqueoLineaCredito;
}
public void setBloqueoLineaCredito(Boolean bloqueoLineaCredito) {
	this.bloqueoLineaCredito = bloqueoLineaCredito;
}
public long getCodigoGrupo() {
	return codigoGrupo;
}
public void setCodigoGrupo(long codigoGrupo) {
	this.codigoGrupo = codigoGrupo;
}

}

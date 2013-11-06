package com.atl.atlmovil.entidades;

import java.util.Date;

public class Cobranza {
	private long id;
	private long codigoCobranza;
	private long codigoMedioPago;
	private double importeCobranza;
	private double importePendiente; // importe que sera utilizado para distribuir la cobranza
	private Date fechaCObranza;
	private Boolean estaSincronizado; // 1 -> si, 0 --> no
	private String estadoCobranza; 
	private long codigoVisita;
	private Boolean esAutoDistribuido;  // 1 -> si, 0 --> no
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCodigoCobranza() {
		return codigoCobranza;
	}
	public void setCodigoCobranza(long codigoCobranza) {
		this.codigoCobranza = codigoCobranza;
	}
	public long getCodigoMedioPago() {
		return codigoMedioPago;
	}
	public void setCodigoMedioPago(long codigoMedioPago) {
		this.codigoMedioPago = codigoMedioPago;
	}
	public double getImporteCobranza() {
		return importeCobranza;
	}
	public void setImporteCobranza(double importeCobranza) {
		this.importeCobranza = importeCobranza;
	}
	public Date getFechaCObranza() {
		return fechaCObranza;
	}
	public void setFechaCObranza(Date fechaCObranza) {
		this.fechaCObranza = fechaCObranza;
	}
	public Boolean getEstaSincronizado() {
		return estaSincronizado;
	}
	public void setEstaSincronizado(Boolean estaSincronizado) {
		this.estaSincronizado = estaSincronizado;
	}
	public String getEstadoCobranza() {
		return estadoCobranza;
	}
	public void setEstadoCobranza(String estadoCobranza) {
		this.estadoCobranza = estadoCobranza;
	}
	public long getCodigoVisita() {
		return codigoVisita;
	}
	public void setCodigoVisita(long codigoVisita) {
		this.codigoVisita = codigoVisita;
	}
	public double getImportePendiente() {
		return importePendiente;
	}
	public void setImportePendiente(double importePendiente) {
		this.importePendiente = importePendiente;
	}
	public Boolean getEsAutoDistribuido() {
		return esAutoDistribuido;
	}
	public void setEsAutoDistribuido(Boolean esAutoDistribuido) {
		this.esAutoDistribuido = esAutoDistribuido;
	}
	
	
}

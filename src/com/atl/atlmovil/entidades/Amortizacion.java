package com.atl.atlmovil.entidades;

public class Amortizacion {
	private long id;
	private long idCobranza;
	private long codigoDocumentoPago;
	private double importeAmortizacion;
	private String anotacionAmortizacion;
	private long codigoAmortizacion;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdCobranza() {
		return idCobranza;
	}
	public void setIdCobranza(long idCobranza) {
		this.idCobranza = idCobranza;
	}
	public long getCodigoDocumentoPago() {
		return codigoDocumentoPago;
	}
	public void setCodigoDocumentoPago(long codigoDocumentoPago) {
		this.codigoDocumentoPago = codigoDocumentoPago;
	}
	public double getImporteAmortizacion() {
		return importeAmortizacion;
	}
	public void setImporteAmortizacion(double importeAmortizacion) {
		this.importeAmortizacion = importeAmortizacion;
	}
	public String getAnotacionAmortizacion() {
		return anotacionAmortizacion;
	}
	public void setAnotacionAmortizacion(String anotacionAmortizacion) {
		this.anotacionAmortizacion = anotacionAmortizacion;
	}
	public long getCodigoAmortizacion() {
		return codigoAmortizacion;
	}
	public void setCodigoAmortizacion(long codigoAmortizacion) {
		this.codigoAmortizacion = codigoAmortizacion;
	}
	
}

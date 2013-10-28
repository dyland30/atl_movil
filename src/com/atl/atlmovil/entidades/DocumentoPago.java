package com.atl.atlmovil.entidades;

import java.util.Date;

public class DocumentoPago {
	private long codigoDocumentoPago;
	private Date fechaEmisionDocumentoPago;
	private String tipoDocumentoPago;
	private int plazoDocumentoPago;
	private Date fechaVencimientoDocumentoPago;
	private double importeOriginalDocumentoPago;
	private double importeAmortizadoDocumentoPago;
	private double importeDescontadoDocumentoPago;
	private double importePendienteDocumentoPago;
	private double importeIgvDocumentoPago;
	private String referenciaDocumentoPago;
	
	
	public long getCodigoDocumentoPago() {
		return codigoDocumentoPago;
	}
	public void setCodigoDocumentoPago(long codigoDocumentoPago) {
		this.codigoDocumentoPago = codigoDocumentoPago;
	}
	public Date getFechaEmisionDocumentoPago() {
		return fechaEmisionDocumentoPago;
	}
	public void setFechaEmisionDocumentoPago(Date fechaEmisionDocumentoPago) {
		this.fechaEmisionDocumentoPago = fechaEmisionDocumentoPago;
	}
	public String getTipoDocumentoPago() {
		return tipoDocumentoPago;
	}
	public void setTipoDocumentoPago(String tipoDocumentoPago) {
		this.tipoDocumentoPago = tipoDocumentoPago;
	}
	public int getPlazoDocumentoPago() {
		return plazoDocumentoPago;
	}
	public void setPlazoDocumentoPago(int plazoDocumentoPago) {
		this.plazoDocumentoPago = plazoDocumentoPago;
	}
	public Date getFechaVencimientoDocumentoPago() {
		return fechaVencimientoDocumentoPago;
	}
	public void setFechaVencimientoDocumentoPago(
			Date fechaVencimientoDocumentoPago) {
		this.fechaVencimientoDocumentoPago = fechaVencimientoDocumentoPago;
	}
	public double getImporteOriginalDocumentoPago() {
		return importeOriginalDocumentoPago;
	}
	public void setImporteOriginalDocumentoPago(double importeOriginalDocumentoPago) {
		this.importeOriginalDocumentoPago = importeOriginalDocumentoPago;
	}
	public double getImporteAmortizadoDocumentoPago() {
		return importeAmortizadoDocumentoPago;
	}
	public void setImporteAmortizadoDocumentoPago(
			double importeAmortizadoDocumentoPago) {
		this.importeAmortizadoDocumentoPago = importeAmortizadoDocumentoPago;
	}
	public double getImporteDescontadoDocumentoPago() {
		return importeDescontadoDocumentoPago;
	}
	public void setImporteDescontadoDocumentoPago(
			double importeDescontadoDocumentoPago) {
		this.importeDescontadoDocumentoPago = importeDescontadoDocumentoPago;
	}
	public double getImportePendienteDocumentoPago() {
		return importePendienteDocumentoPago;
	}
	public void setImportePendienteDocumentoPago(
			double importePendienteDocumentoPago) {
		this.importePendienteDocumentoPago = importePendienteDocumentoPago;
	}
	public double getImporteIgvDocumentoPago() {
		return importeIgvDocumentoPago;
	}
	public void setImporteIgvDocumentoPago(double importeIgvDocumentoPago) {
		this.importeIgvDocumentoPago = importeIgvDocumentoPago;
	}
	public String getReferenciaDocumentoPago() {
		return referenciaDocumentoPago;
	}
	public void setReferenciaDocumentoPago(String referenciaDocumentoPago) {
		this.referenciaDocumentoPago = referenciaDocumentoPago;
	}
}

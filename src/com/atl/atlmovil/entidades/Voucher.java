package com.atl.atlmovil.entidades;

import java.util.Date;

public class Voucher {
	private long codigoVoucher;
	private Date fechaVoucher;
	private int bancoVoucher;
	private String agenciaVoucher;
	private String terminalVoucher;
	private String medioVoucher;
	private double importeVoucher;
	private long clienteVoucher;
	private Boolean estadoVoucher;
	
	
	public long getCodigoVoucher() {
		return codigoVoucher;
	}
	public void setCodigoVoucher(long codigoVoucher) {
		this.codigoVoucher = codigoVoucher;
	}
	public Date getFechaVoucher() {
		return fechaVoucher;
	}
	public void setFechaVoucher(Date fechaVoucher) {
		this.fechaVoucher = fechaVoucher;
	}
	public int getBancoVoucher() {
		return bancoVoucher;
	}
	public void setBancoVoucher(int bancoVoucher) {
		this.bancoVoucher = bancoVoucher;
	}
	public String getAgenciaVoucher() {
		return agenciaVoucher;
	}
	public void setAgenciaVoucher(String agenciaVoucher) {
		this.agenciaVoucher = agenciaVoucher;
	}
	public String getTerminalVoucher() {
		return terminalVoucher;
	}
	public void setTerminalVoucher(String terminalVoucher) {
		this.terminalVoucher = terminalVoucher;
	}
	public String getMedioVoucher() {
		return medioVoucher;
	}
	public void setMedioVoucher(String medioVoucher) {
		this.medioVoucher = medioVoucher;
	}
	public double getImporteVoucher() {
		return importeVoucher;
	}
	public void setImporteVoucher(double importeVoucher) {
		this.importeVoucher = importeVoucher;
	}
	public long getClienteVoucher() {
		return clienteVoucher;
	}
	public void setClienteVoucher(long clienteVoucher) {
		this.clienteVoucher = clienteVoucher;
	}
	public Boolean getEstadoVoucher() {
		return estadoVoucher;
	}
	public void setEstadoVoucher(Boolean estadoVoucher) {
		this.estadoVoucher = estadoVoucher;
	}

}

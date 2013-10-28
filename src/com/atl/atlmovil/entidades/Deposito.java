package com.atl.atlmovil.entidades;

import java.util.Date;

public class Deposito {
	private long id;
	private long codigoDeposito;
	private long codigoMedioPago;
	private long codigoCobranza;
	private long voucherDeposito;
	private long clienteDeposito;
	private int bancoDeposito;
	private Date fechaDeposito;
	private double importeDeposito;
	private String agenciaDeposito;
	private String terminalDeposito;
	private Boolean estadoDeposito;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCodigoDeposito() {
		return codigoDeposito;
	}
	public void setCodigoDeposito(long codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}
	public long getCodigoMedioPago() {
		return codigoMedioPago;
	}
	public void setCodigoMedioPago(long codigoMedioPago) {
		this.codigoMedioPago = codigoMedioPago;
	}
	public long getCodigoCobranza() {
		return codigoCobranza;
	}
	public void setCodigoCobranza(long codigoCobranza) {
		this.codigoCobranza = codigoCobranza;
	}
	public long getVoucherDeposito() {
		return voucherDeposito;
	}
	public void setVoucherDeposito(long voucherDeposito) {
		this.voucherDeposito = voucherDeposito;
	}
	public long getClienteDeposito() {
		return clienteDeposito;
	}
	public void setClienteDeposito(long clienteDeposito) {
		this.clienteDeposito = clienteDeposito;
	}
	public int getBancoDeposito() {
		return bancoDeposito;
	}
	public void setBancoDeposito(int bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}
	public Date getFechaDeposito() {
		return fechaDeposito;
	}
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	public double getImporteDeposito() {
		return importeDeposito;
	}
	public void setImporteDeposito(double importeDeposito) {
		this.importeDeposito = importeDeposito;
	}
	public String getAgenciaDeposito() {
		return agenciaDeposito;
	}
	public void setAgenciaDeposito(String agenciaDeposito) {
		this.agenciaDeposito = agenciaDeposito;
	}
	public String getTerminalDeposito() {
		return terminalDeposito;
	}
	public void setTerminalDeposito(String terminalDeposito) {
		this.terminalDeposito = terminalDeposito;
	}
	public Boolean getEstadoDeposito() {
		return estadoDeposito;
	}
	public void setEstadoDeposito(Boolean estadoDeposito) {
		this.estadoDeposito = estadoDeposito;
	}
	
}

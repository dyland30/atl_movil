package com.atl.atlmovil.entidades;

import java.util.Date;
import java.util.List;

public class Pedido {
private long id;// id interno
private long codigoPedido;
private long codigoVisita; // con esto ya se tiene el codigo de cliente
private long codigoFormaPago;
private long codigoEmpresaCarga;
private Date fechaIngresoPedido;
private Double importePedido;
private Double lineaReservadaPedido;
private Boolean aceptaRetencionPedido;
private Boolean estaRetenidoPedido;
private String estadoPedido; // A -> Activo, N-> Anulado, R -> Registrado, 
private Boolean estaSincronizado; //1 -> sincronizado, 0-> pendiente de sincronizacion
private String direccionDeEnvio;
private String empresaTransporte;
private String instruccionesEspeciales;
private String strfechaIngresoPedido;
private String codigoMovil;

private List<DetallePedido> detalles;


public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getCodigoPedido() {
	return codigoPedido;
}
public void setCodigoPedido(long codigoPedido) {
	this.codigoPedido = codigoPedido;
}
public long getCodigoVisita() {
	return codigoVisita;
}
public void setCodigoVisita(long codigoVisita) {
	this.codigoVisita = codigoVisita;
}
public long getCodigoFormaPago() {
	return codigoFormaPago;
}
public void setCodigoFormaPago(long codigoFormaPago) {
	this.codigoFormaPago = codigoFormaPago;
}
public Date getFechaIngresoPedido() {
	return fechaIngresoPedido;
}
public void setFechaIngresoPedido(Date fechaIngresoPedido) {
	this.fechaIngresoPedido = fechaIngresoPedido;
}
public Double getImportePedido() {
	return importePedido;
}
public void setImportePedido(Double importePedido) {
	this.importePedido = importePedido;
}
public Double getLineaReservadaPedido() {
	return lineaReservadaPedido;
}
public void setLineaReservadaPedido(Double lineaReservadaPedido) {
	this.lineaReservadaPedido = lineaReservadaPedido;
}
public Boolean getAceptaRetencionPedido() {
	return aceptaRetencionPedido;
}
public void setAceptaRetencionPedido(Boolean aceptaRetencionPedido) {
	this.aceptaRetencionPedido = aceptaRetencionPedido;
}
public Boolean getEstaRetenidoPedido() {
	return estaRetenidoPedido;
}
public void setEstaRetenidoPedido(Boolean estaRetenidoPedido) {
	this.estaRetenidoPedido = estaRetenidoPedido;
}
public String getEstadoPedido() {
	return estadoPedido;
}
public void setEstadoPedido(String estadoPedido) {
	this.estadoPedido = estadoPedido;
}
public Boolean getEstaSincronizado() {
	return estaSincronizado;
}
public void setEstaSincronizado(Boolean estaSincronizado) {
	this.estaSincronizado = estaSincronizado;
}
public String getDireccionDeEnvio() {
	return direccionDeEnvio;
}
public void setDireccionDeEnvio(String direccionDeEnvio) {
	this.direccionDeEnvio = direccionDeEnvio;
}
public String getEmpresaTransporte() {
	return empresaTransporte;
}
public void setEmpresaTransporte(String empresaTransporte) {
	this.empresaTransporte = empresaTransporte;
}
public String getInstruccionesEspeciales() {
	return instruccionesEspeciales;
}
public void setInstruccionesEspeciales(String instruccionesEspeciales) {
	this.instruccionesEspeciales = instruccionesEspeciales;
}
public long getCodigoEmpresaCarga() {
	return codigoEmpresaCarga;
}
public void setCodigoEmpresaCarga(long codigoEmpresaCarga) {
	this.codigoEmpresaCarga = codigoEmpresaCarga;
}
public List<DetallePedido> getDetalles() {
	return detalles;
}
public void setDetalles(List<DetallePedido> detalles) {
	this.detalles = detalles;
}
public String getStrfechaIngresoPedido() {
	return strfechaIngresoPedido;
}
public void setStrfechaIngresoPedido(String strfechaIngresoPedido) {
	this.strfechaIngresoPedido = strfechaIngresoPedido;
}
public String getCodigoMovil() {
	return codigoMovil;
}
public void setCodigoMovil(String codigoMovil) {
	this.codigoMovil = codigoMovil;
}

}

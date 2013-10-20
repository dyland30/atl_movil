package com.atl.atlmovil.entidades;

public class TallaPedido {
	private long codigoProducto;
	private long idPedido;
	private int numeroTalla;
	private int cantidad;
	public long getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	public int getNumeroTalla() {
		return numeroTalla;
	}
	public void setNumeroTalla(int numeroTalla) {
		this.numeroTalla = numeroTalla;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}

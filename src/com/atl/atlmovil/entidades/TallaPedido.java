package com.atl.atlmovil.entidades;

public class TallaPedido {
	private long codigoProducto;
	private long codigoPedido;
	private int numeroTalla;
	private int cantidad;
	public long getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public long getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(long codigoPedido) {
		this.codigoPedido = codigoPedido;
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

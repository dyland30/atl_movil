package com.atl.atlmovil.entidades;

import java.util.List;

public class DetallePedido {
	private long idPedido;
	private long codigoProducto;
	private double precioUnitario;
	private List<TallaPedido> tallas;
	
	
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	public long getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public List<TallaPedido> getTallas() {
		return tallas;
	}
	public void setTallas(List<TallaPedido> tallas) {
		this.tallas = tallas;
	}
	
	
}

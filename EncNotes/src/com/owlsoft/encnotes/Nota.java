package com.owlsoft.encnotes;

public class Nota {
	private long id;
	private String titulo;
	private String texto;
	private String flgEncriptado;
	private long codUsuario;
	private String llave;
	private String fchCreacion;
	private String fchModificacion;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getFlgEncriptado() {
		return flgEncriptado;
	}
	public void setFlgEncriptado(String flgEncriptado) {
		this.flgEncriptado = flgEncriptado;
	}
	public long getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(long codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getFchCreacion() {
		return fchCreacion;
	}
	public void setFchCreacion(String fchCreacion) {
		this.fchCreacion = fchCreacion;
	}
	public String getFchModificacion() {
		return fchModificacion;
	}
	public void setFchModificacion(String fchModificacion) {
		this.fchModificacion = fchModificacion;
	}
	
	@Override
	public String toString(){
		
		return titulo;
	}
	public String getLlave() {
		return llave;
	}
	public void setLlave(String llave) {
		this.llave = llave;
	}
	
	
	
	
}

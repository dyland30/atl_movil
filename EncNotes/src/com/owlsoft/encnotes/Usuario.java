package com.owlsoft.encnotes;

public class Usuario {
	private long id;
	private String login;
	private String firstname;
	private String lastname;
	private String password;
	private String llave;
	private String pregunta;
	private String respuesta;
	private String fchCreacion;
	private String fchModificacion;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLlave() {
		return llave;
	}
	public void setLlave(String llave) {
		this.llave = llave;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
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
	
}

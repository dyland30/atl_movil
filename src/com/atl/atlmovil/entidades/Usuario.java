package com.atl.atlmovil.entidades;

public class Usuario {
	private long id;
	private String login;
	private String clave;
	private String nombres;
	private String apellidos;
	private String dni;
	private long codigoEmpleado;
	private Empleado empleado;
	
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public long getCodigoEmpleado() {
		return codigoEmpleado;
	}
	public void setCodigoEmpleado(long codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}

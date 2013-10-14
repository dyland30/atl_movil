package com.atl.atlmovil;

import com.atl.atlmovil.entidades.Usuario;

import android.app.Application;

public class AtlApp extends Application{
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

package com.atl.atlmovi.util;

import java.util.List;

import com.atl.atlmovil.dao.UsuarioDAO;
import com.atl.atlmovil.entidades.Usuario;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;



public class ServicioSync extends Service{

	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    //TODO do something useful
		// hilo separado
		Thread t = new Thread(){
			public void run(){
			
				ejecutarProceso();
				
			}
			};
			t.start();
		
	    return Service.START_STICKY;
	  }
	
	public void ejecutarProceso(){
		Sincronizador sinc = new Sincronizador();
		while(true){ // bucle infinito
			String ping = sinc.ping();
			//bucle infinito
			//Log.i("ping ",ping);
			// hay conexion con el sistema
			//obtener usuarios del web service e insertarlos en la bd local
			if(ping.equals("OK")){
				pullUsuarios();
				
			}
			
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void pullUsuarios(){
		UsuarioDAO udao = new UsuarioDAO(this);
		try{
			udao.open();
			Sincronizador sinc = new Sincronizador();
			List<Usuario> ls = sinc.obtenerUsuarios();
			if(ls!=null){
				for(Usuario u : ls){
					//guardar usuario en base de datos si aun no existe
					Usuario uold = udao.buscarPorLogin(u.getLogin());
					if(uold==null){
						//insertamos en bd
						udao.crear(u.getLogin(), u.getClave(), u.getNombres(), u.getApellidos(), u.getDni());
						Log.i("usuario insertado", u.getLogin());
					}
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("error", ex.getMessage());
		} finally{
			udao.close();	
		}		
}
	

	
}

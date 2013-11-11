package com.atl.atlmovi.util;

import java.text.SimpleDateFormat;
import java.util.List;

import com.atl.atlmovil.dao.*;
import com.atl.atlmovil.entidades.*;

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
			
			//Log.i("ping ",ping);
			// hay conexion con el sistema
			//obtener usuarios del web service e insertarlos en la bd local
			if(ping.equals("OK")){
				//push
				//pull
				
				pullUsuarios();
				pullPersonas();
				pullGrupos();
				pullClientes();
				pullEmpleados();
				pullVisitas();
				
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
			Log.w("pullUsuarios", ex.getMessage());
		} finally{
			udao.close();	
		}		
	}
	
	public void pullPersonas(){
		PersonaDAO dao = new PersonaDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Persona> ls = sinc.obtenerPersonas();
			if(ls!=null){
				for(Persona p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					Persona old = dao.buscarPorID(p.getCodigoPersona());
					if(old==null){
						//insertamos en bd
						dao.crear(p);
						Log.i("persona insertada", p.getNombrePersona());
						
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullPersonas", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}
	
	public void pullGrupos(){
		GrupoDAO dao = new GrupoDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Grupo> ls = sinc.obtenerGrupos();
			if(ls!=null){
				for(Grupo p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					Grupo old = dao.buscarPorID(p.getCodigoGrupo());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoGrupo(), p.getNombreGrupo(), p.getTitularGrupo());
						
						Log.i("grupo insertado", p.getNombreGrupo());
						
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullGrupos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullClientes(){
		ClienteDAO dao = new ClienteDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Cliente> ls = sinc.obtenerClientes();
			if(ls!=null){
				for(Cliente p : ls){
					//Log.i("cliente nuevo", "cod: "+p.getCodigoCliente()+" "+p.getCelularCliente());
					Cliente old = dao.buscarPorID(p.getCodigoCliente());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoCliente(), p.getCodigoEmpleado(),p.getCodigoGrupo(),
								p.getCodigoPersona(), p.getDireccionEntregaCliente(),
								p.getRepresentanteCliente(), p.getCelularCliente());
						Log.i("cliente insertado", "cod: "+p.getCodigoCliente());
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullCliente", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullEmpleados(){
		EmpleadoDAO dao = new EmpleadoDAO(this);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Empleado> ls = sinc.obtenerEmpleados();
			
			if(ls!=null){
				for(Empleado p : ls){
				//	Log.i("empleado ", "cod: "+p.getCodigoEmpleado());
					Empleado old = dao.buscarPorID(p.getCodigoEmpleado());
					if(old==null){
						//insertamos en bd
						p.setFechaCeseEmpleado(dateFormat.parse(p.getStrfechaCeseEmpleado()));
						p.setFechaIngresoEmpleado(dateFormat.parse(p.getStrfechaIngresoEmpleado()));
						
						dao.crear(p.getCodigoEmpleado(), p.getCodigoPersona(), p.getAreaEmpleado(), p.getCargoEmpleado(),
								p.getFechaCeseEmpleado(), p.getFechaIngresoEmpleado(), p.getJefeEmpleado());
						Log.i("empleado insertado", "cod: "+p.getCodigoEmpleado());
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullEmpleado", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullVisitas(){
		VisitaDAO dao = new VisitaDAO(this);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormatHora = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Visita> ls = sinc.obtenerVisitas();
			
			if(ls!=null){
				for(Visita v : ls){
					//Log.i("visita ", "cod: "+v.getCodigoVisita());
					Visita old = dao.buscarPorID(v.getCodigoVisita());
					if(old==null){
						//insertamos en bd
						
						if(v.getStrfechaVisita()!=null && v.getStrfechaVisita().length()>0 && !v.getStrfechaVisita().equals("null")) 
							v.setFechaVisita(dateFormat.parse(v.getStrfechaVisita()));
						else
							v.setFechaVisita(dateFormat.parse("1900-01-01"));
						if(v.getStrhoraInicioVisita()!=null && v.getStrhoraInicioVisita().length()>0 && !v.getStrhoraInicioVisita().equals("null"))
							v.setHoraInicioVisita(dateFormatHora.parse(v.getStrhoraInicioVisita()));
						else
							v.setHoraInicioVisita(dateFormatHora.parse("1900-01-01 00:00"));
						
						if(v.getStrhoraFinalVisita()!=null && v.getStrhoraFinalVisita().length()>0 && !v.getStrhoraFinalVisita().equals("null")) 
							v.setHoraFinalVisita(dateFormatHora.parse(v.getStrhoraFinalVisita()));
						else
							v.setHoraFinalVisita(dateFormatHora.parse("1900-01-01 00:00"));
						
						dao.crear(v);
						Log.i("Visita Insertada ", "cod: "+v.getCodigoVisita());
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullVisita", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}


}
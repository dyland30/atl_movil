package com.atl.atlmovi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
				//Log.i("TEST",sinc.echo("!!!!!.....PROBANDO....!!!!!!!!"));
				pullTipoDocumentos();
				pullPersonas();
				pullEmpleados();
				pullUsuarios();
				pullGrupos();
				pullClientes();
				pullTipoVisitas();
				pullEstadoVisitas();
				pullVisitas();
				pullEmpresaCargas();
				pullFormaPagos();
				pullTallas();
				pullProductos();
				pullProductosFormaPago();
				pullDocumentosPago();
				pullMedioPago();
				pullBancos();
				
				pushPedidos();
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
			Log.w("pullUsuarios","err: "+ ex.getMessage());
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
	
	public void pullTipoDocumentos(){
		TipoDocumentoDAO dao = new TipoDocumentoDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<TipoDocumento> ls = sinc.obtenerTiposDocumento();
			if(ls!=null){
				for(TipoDocumento p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					TipoDocumento old = dao.buscarPorID(p.getCodigoTipoDocumento());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoTipoDocumento(), p.getNombreTipoDocumento(), p.getSiglaTipoDocumento());
						
						Log.i("tipo documento insertado", p.getNombreTipoDocumento());
						
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullTipoVisitas(){
		TipoVisitaDAO dao = new TipoVisitaDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<TipoVisita> ls = sinc.obtenerTiposVisita();
			if(ls!=null){
				for(TipoVisita p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					TipoVisita old = dao.buscarPorID(p.getCodigoTipoVisita());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoTipoVisita(), p.getDescripcionTipoVisita());
						
						Log.i("tipo visita insertada", p.getDescripcionTipoVisita());
						
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullEstadoVisitas(){
		EstadoVisitaDAO dao = new EstadoVisitaDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<EstadoVisita> ls = sinc.obtenerEstadosVisita();
			if(ls!=null){
				for(EstadoVisita p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					EstadoVisita old = dao.buscarPorID(p.getCodigoEstadoVisita());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoEstadoVisita(), p.getDescripcionEstadoVisita());
						
						Log.i("Estado visita insertado", p.getDescripcionEstadoVisita());
						
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullEmpresaCargas(){
		EmpresaCargaDAO dao = new EmpresaCargaDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<EmpresaCarga> ls = sinc.obtenerEmpresaCargas();
			if(ls!=null){
				for(EmpresaCarga p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					EmpresaCarga old = dao.buscarPorID(p.getCodigoEmpresaCarga());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoEmpresaCarga(), p.getNombreEmpresaCarga(), p.getDireccionEmpresaCarga(),
								p.getHorarioEmpresaCarga(), p.getRucEmpresaCarga());
						
						Log.i("Empresa Carga insertado", p.getNombreEmpresaCarga());
						
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullFormaPagos(){
		FormaPagoDAO dao = new FormaPagoDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<FormaPago> ls = sinc.obtenerFormasPago();
			if(ls!=null){
				for(FormaPago p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					FormaPago old = dao.buscarPorID(p.getCodigoFormaPago());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoFormaPago(), p.getDescripcionFormaPago());
						
						Log.i("Forma Pago insertada", p.getDescripcionFormaPago());
						
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullTallas(){
		TallaDAO dao = new TallaDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Talla> ls = sinc.obtenerTallas();
			if(ls!=null){
				for(Talla p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					Talla old = dao.buscarPorID(p.getCodigoProducto(), p.getNumeroTalla());
					
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoProducto(), p.getNumeroTalla(), p.getStockDisponibleTalla());
						
						Log.i("Talla insertada", "Prod: "+p.getCodigoProducto()+" Talla: "+p.getNumeroTalla());
						
					} else{
						//actualizar solo si ha cambiado el stock
						if(p.getStockDisponibleTalla()!= old.getStockDisponibleTalla()){
							dao.actualizar(p);
							Log.i("Talla actualizada", "Prod: "+p.getCodigoProducto()+" Talla: "+p.getNumeroTalla()+" Stock: "+p.getStockDisponibleTalla());
							
						}
						
						
					}
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullProductos(){
		ProductoDAO dao = new ProductoDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Producto> ls = sinc.obtenerProductos();
			if(ls!=null){
				for(Producto p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					Producto old = dao.buscarPorID(p.getCodigoProducto());
					
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoProducto(), p.getCalidadProducto(), p.getColorProducto(),
								p.getDescripcionProducto(), p.getMaterialProducto(), 
								p.getPrecioProducto(), p.getSexoProducto());
						
						Log.i("Producto insertado", p.getCodigoProducto()+" "+p.getDescripcionProducto());		
					} 
				}
					
				}
				
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}

	public void pullProductosFormaPago(){
		ProductoFormaPagoDAO dao = new ProductoFormaPagoDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<ProductoFormaPago> ls = sinc.obtenerProductoFormaPagos();
			if(ls!=null){
				for(ProductoFormaPago p : ls){
					//guardar usuario en base de datos si aun no existe
					//Log.i("p", p.getCodigoPersona()+" "+  p.getNombrePersona());
					ProductoFormaPago old = dao.buscarPorID(p.getCodigoFormaPago(), p.getCodigoProducto());
					
					
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoFormaPago(), p.getCodigoProducto(), p.getPrecio());
						
						Log.i("Precio Producto Insertado", "Prod: "+p.getCodigoProducto()+" M. Pago: "+p.getCodigoFormaPago()+" Precio: "+p.getPrecio());
						
					} else{
						//actualizar solo si ha cambiado el stock
						if(Math.abs(p.getPrecio()-old.getPrecio())>0.1){
							dao.actualizar(p);
							Log.i("Precio Producto Actualizado", "Prod: "+p.getCodigoProducto()+" M. Pago: "+p.getCodigoFormaPago()+" Precio act: "+p.getPrecio()+" Precio ant: "+old.getPrecio());
							
						}
						
						
					}
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullTipoDocumentos", "err "+ ex.getMessage());
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
						
						if(p.getStrfechaCeseEmpleado()!=null && p.getStrfechaCeseEmpleado().length()>0 && !p.getStrfechaCeseEmpleado().equals("null")) 
							p.setFechaCeseEmpleado(dateFormat.parse(p.getStrfechaCeseEmpleado()));
						else
							p.setFechaCeseEmpleado(dateFormat.parse("1900-01-01"));						
						
						if(p.getStrfechaIngresoEmpleado()!=null && p.getStrfechaIngresoEmpleado().length()>0 && !p.getStrfechaIngresoEmpleado().equals("null"))
							p.setFechaIngresoEmpleado(dateFormat.parse(p.getStrfechaIngresoEmpleado()));
						else
							p.setFechaIngresoEmpleado(dateFormat.parse("1900-01-01"));
							
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
	
	public void pullDocumentosPago(){
		DocumentoPagoDAO dao = new DocumentoPagoDAO(this);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<DocumentoPago> ls = sinc.obtenerDocumentosPago();
			
			if(ls!=null){
				for(DocumentoPago p : ls){
				//	Log.i("empleado ", "cod: "+p.getCodigoEmpleado());
					DocumentoPago old = dao.buscarPorID(p.getCodigoDocumentoPago());
					if(old==null){
						//insertamos en bd
						
						if(p.getStrfechaEmisionDocumentoPago()!=null && p.getStrfechaEmisionDocumentoPago().length()>0 && !p.getStrfechaEmisionDocumentoPago().equals("null")) 
							p.setFechaEmisionDocumentoPago(dateFormat.parse(p.getStrfechaEmisionDocumentoPago()));
						else
							p.setFechaEmisionDocumentoPago(dateFormat.parse("1900-01-01"));						
						
						if(p.getStrfechaVencimientoDocumentoPago()!=null && p.getStrfechaVencimientoDocumentoPago().length()>0 && !p.getStrfechaVencimientoDocumentoPago().equals("null"))
							p.setFechaVencimientoDocumentoPago(dateFormat.parse(p.getStrfechaVencimientoDocumentoPago()));
						else
							p.setFechaVencimientoDocumentoPago(dateFormat.parse("1900-01-01"));
							
						dao.crear(p.getCodigoDocumentoPago(), p.getFechaEmisionDocumentoPago(), p.getFechaVencimientoDocumentoPago(), 
								p.getImporteAmortizadoDocumentoPago(), p.getImporteDescontadoDocumentoPago(), p.getImporteIgvDocumentoPago(), 
								p.getImporteOriginalDocumentoPago(), p.getImportePendienteDocumentoPago(), p.getPlazoDocumentoPago(), p.getTipoDocumentoPago(), 
								p.getReferenciaDocumentoPago(), p.getCodigoCliente());
						
						
						Log.i("documento pago insertado", "cod: "+p.getCodigoDocumentoPago());
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
	

	public void pullBancos(){
		BancoDAO dao = new BancoDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<Banco> ls = sinc.obtenerBanco();
			if(ls!=null){
				for(Banco p : ls){
					//Log.i("cliente nuevo", "cod: "+p.getCodigoCliente()+" "+p.getCelularCliente());
					Banco old = dao.buscarPorID(p.getCodigoBanco());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoBanco(), p.getNombreBanco());
						Log.i("Banco insertado", " "+p.getNombreBanco());
					} 
					
				}
				
			}
			
		} catch(Exception ex){
			Log.w("pullBanco", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();	
		}		
	}
	
	
	public void pullMedioPago(){
		MedioPagoDAO dao = new MedioPagoDAO(this);
		try{
			dao.open();
			Sincronizador sinc = new Sincronizador();
			List<MedioPago> ls = sinc.obtenerMedioPagos();
			if(ls!=null){
				for(MedioPago p : ls){
					//Log.i("cliente nuevo", "cod: "+p.getCodigoCliente()+" "+p.getCelularCliente());
					MedioPago old = dao.buscarPorID(p.getCodigoMedioPago());
					if(old==null){
						//insertamos en bd
						dao.crear(p.getCodigoMedioPago(), p.getDescripcionMedioPago());
						Log.i("Medio Pago Insertado", " "+p.getDescripcionMedioPago());
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
	
	public void pushPedidos(){
		PedidoDAO dao = new PedidoDAO(this);
		DetallePedidoDAO dpDao = new DetallePedidoDAO(this);
		TallaPedidoDAO tpDao = new TallaPedidoDAO(this);
		try{
			dao.open();
			dpDao.open();
			tpDao.open();
			Sincronizador sinc = new Sincronizador();
			List<Pedido> ls = dao.obtenerTodos();
			//test
			for(Pedido p: ls){
				//cargar detalles
				List<DetallePedido> lsDetalles = new ArrayList<DetallePedido>();
				//cargar tallas
				for(DetallePedido dp : dpDao.buscarPorPedido(p.getId())){
					dp.setTallas(tpDao.buscarPorPedidoProducto(dp.getIdPedido(), dp.getCodigoProducto()));
					lsDetalles.add(dp);
				}
				p.setDetalles(lsDetalles);
				
				Log.i("test pedido", sinc.registrarPedido(p));
			}
			
		} catch(Exception ex){
			Log.w("pushPedido", "err "+ ex.getMessage());
			//ex.printStackTrace();
		} finally{
			dao.close();
			dpDao.close();
			tpDao.close();
		}		
	}
	
	
	

}

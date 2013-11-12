package com.atl.atlmovi.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.atl.atlmovil.entidades.*;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Sincronizador {
	
	private static final String NAMESPACE = "http://atl.com/";
	
	private static String URL="http://192.168.0.12:82/AtlServicio/Servicios.asmx";
	//
	private static final String METHOD_OBTENERUSUARIOS = "obtenerUsuarios";
	private static final String METHOD_OBTENERPERSONAS = "obtenerPersonas";
	private static final String METHOD_OBTENERCLIENTES = "obtenerClientes";
	private static final String METHOD_OBTENERGRUPOS = "obtenerGrupos";
	private static final String METHOD_OBTENEREMPLEADOS = "obtenerEmpleados";
	private static final String METHOD_OBTENERVISITAS = "obtenerVisitas";
	private static final String METHOD_OBTENERTIPODOCUMENTOS = "obtenerTipoDocumentos";
	private static final String METHOD_OBTENERTIPOVISITAS = "obtenerTipoVisitas";
	private static final String METHOD_OBTENERESTADOVISITAS = "obtenerEstadoVisitas";
	private static final String METHOD_OBTENEREMPRESACARGA = "obtenerEmpresaCargas";
	private static final String METHOD_OBTENERFORMAPAGO = "obtenerFormaPagos";
	private static final String METHOD_OBTENERTALLAS = "obtenerTallas";
	private static final String METHOD_OBTENERPRODUCTOS = "obtenerProductos";
	private static final String METHOD_OBTENERPRODUCTOSFORMAPAGO = "obtenerProductoFormaPagos";
	
	private static final String METHOD_PING = "ping";
	private static final String SOAP_OBTENER_USUARIOS =NAMESPACE+METHOD_OBTENERUSUARIOS;
	private static final String SOAP_OBTENER_PERSONAS =NAMESPACE+METHOD_OBTENERPERSONAS;
	private static final String SOAP_OBTENER_CLIENTES =NAMESPACE+METHOD_OBTENERCLIENTES;
	private static final String SOAP_OBTENER_GRUPOS  =NAMESPACE+METHOD_OBTENERGRUPOS;
	private static final String SOAP_OBTENER_EMPLEADOS  =NAMESPACE+METHOD_OBTENEREMPLEADOS;
	private static final String SOAP_OBTENER_VISITAS  =NAMESPACE+METHOD_OBTENERVISITAS;
	
	private static final String SOAP_OBTENER_TIPO_DOCUMENTOS =NAMESPACE+METHOD_OBTENERTIPODOCUMENTOS;
	private static final String SOAP_OBTENER_TIPO_VISITAS  =NAMESPACE+METHOD_OBTENERTIPOVISITAS;
	private static final String SOAP_OBTENER_ESTADO_VISITAS  =NAMESPACE+METHOD_OBTENERESTADOVISITAS;
	private static final String SOAP_OBTENER_EMPRESA_CARGA  =NAMESPACE+METHOD_OBTENEREMPRESACARGA;
	private static final String SOAP_OBTENER_FORMA_PAGO  =NAMESPACE+METHOD_OBTENERFORMAPAGO;
	private static final String SOAP_OBTENER_TALLAS  =NAMESPACE+METHOD_OBTENERTALLAS;
	private static final String SOAP_OBTENER_PRODUCTOS  =NAMESPACE+METHOD_OBTENERPRODUCTOS;
	private static final String SOAP_OBTENER_PRODUCTOS_FORMA_PAGO  =NAMESPACE+METHOD_OBTENERPRODUCTOSFORMAPAGO;
	
		
	private static final String SOAP_PING =NAMESPACE+METHOD_PING;

	private SoapObject request=null;
	private SoapSerializationEnvelope envelope=null;
	private SoapPrimitive  resultsRequestSOAP=null;
	
	Gson gson ;
	public String ping(){
		String ping ="";
		request = new SoapObject(NAMESPACE, METHOD_PING);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE transporte = new HttpTransportSE(URL);
		
		try {	
			//Hace la llamada al ws
			transporte.call(SOAP_PING, envelope);
			
			//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
			//de la peticion
			resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
			ping = resultsRequestSOAP.toString();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ping = "";
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			ping = "";
		}
		
		return ping;
		
	}
	
	//usuarios
	
	public List<Usuario> obtenerUsuarios(){
		List<Usuario> ls= null;
		request = new SoapObject(NAMESPACE, METHOD_OBTENERUSUARIOS);
		//Se crea un objeto SoapSerializationEnvelope para serealizar la
//		peticion SOAP y permitir viajar el mensaje por la nube
//		el constructor recibe la version de SOAP
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
		//Se envuelve la peticion soap
		envelope.setOutputSoapObject(request);
		
		//Objeto que representa el modelo de transporte
		//Recibe la URL del ws
		HttpTransportSE transporte = new HttpTransportSE(URL);
		
		
		try {	
			//Hace la llamada al ws
			transporte.call(SOAP_OBTENER_USUARIOS, envelope);
			
			//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
			//de la peticion
			resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
			
			String  strJSON = resultsRequestSOAP.toString();
			
			ls =  crearListaUsuarios(strJSON);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ls;
	}
	
	
	public List<Usuario> crearListaUsuarios(String strJson){
		List<Usuario> ls= new ArrayList<Usuario>();
		//se crea el objeto que ayuda deserealizar la cadena JSON
				gson = new Gson();
				
				//Obtenemos el tipo de un ArrayList<AndroidSO>
				Type lstT = new TypeToken< ArrayList<Usuario>>(){}.getType();
				ls = gson.fromJson(strJson, lstT);
		return ls;
	}
	
//personas
	public List<Persona> obtenerPersonas(){
		List<Persona> ls= null;
		request = new SoapObject(NAMESPACE, METHOD_OBTENERPERSONAS);
		//Se crea un objeto SoapSerializationEnvelope para serealizar la
//		peticion SOAP y permitir viajar el mensaje por la nube
//		el constructor recibe la version de SOAP
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
		//Se envuelve la peticion soap
		envelope.setOutputSoapObject(request);
		
		//Objeto que representa el modelo de transporte
		//Recibe la URL del ws
		HttpTransportSE transporte = new HttpTransportSE(URL);
		
		
		try {	
			//Hace la llamada al ws
			transporte.call(SOAP_OBTENER_PERSONAS, envelope);
			
			//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
			//de la peticion
			resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
			
			String  strJSON = resultsRequestSOAP.toString();
			
			ls =  crearListaPersonas(strJSON);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ls;
	}
		
	public List<Persona> crearListaPersonas(String strJson){
		List<Persona> ls= new ArrayList<Persona>();
		//se crea el objeto que ayuda deserealizar la cadena JSON
				gson = new Gson();
				//Obtenemos el tipo de un ArrayList<AndroidSO>
				Type lstT = new TypeToken< ArrayList<Persona>>(){}.getType();
				ls = gson.fromJson(strJson, lstT);
		return ls;
	}
	
//grupos

	public List<Grupo> obtenerGrupos(){
		List<Grupo> ls= null;
		request = new SoapObject(NAMESPACE, METHOD_OBTENERGRUPOS);
		//Se crea un objeto SoapSerializationEnvelope para serealizar la
//		peticion SOAP y permitir viajar el mensaje por la nube
//		el constructor recibe la version de SOAP
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
		//Se envuelve la peticion soap
		envelope.setOutputSoapObject(request);
		
		//Objeto que representa el modelo de transporte
		//Recibe la URL del ws
		HttpTransportSE transporte = new HttpTransportSE(URL);
		try {	
			//Hace la llamada al ws
			transporte.call(SOAP_OBTENER_GRUPOS, envelope);
			
			//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
			//de la peticion
			resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
			
			String  strJSON = resultsRequestSOAP.toString();
			
			ls =  crearListaGrupos(strJSON);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ls;
	}
		
	public List<Grupo> crearListaGrupos(String strJson){
		List<Grupo> ls= new ArrayList<Grupo>();
		//se crea el objeto que ayuda deserealizar la cadena JSON
				gson = new Gson();
				//Obtenemos el tipo de un ArrayList<AndroidSO>
				Type lstT = new TypeToken< ArrayList<Grupo>>(){}.getType();
				ls = gson.fromJson(strJson, lstT);
		return ls;
	}

// clientes
	
	public List<Cliente> obtenerClientes(){
			List<Cliente> ls= null;
			request = new SoapObject(NAMESPACE, METHOD_OBTENERCLIENTES);
			//Se crea un objeto SoapSerializationEnvelope para serealizar la
//			peticion SOAP y permitir viajar el mensaje por la nube
//			el constructor recibe la version de SOAP
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
			//Se envuelve la peticion soap
			envelope.setOutputSoapObject(request);
			
			//Objeto que representa el modelo de transporte
			//Recibe la URL del ws
			HttpTransportSE transporte = new HttpTransportSE(URL);
			try {	
				//Hace la llamada al ws
				transporte.call(SOAP_OBTENER_CLIENTES, envelope);
				
				//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
				//de la peticion
				resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
				
				String  strJSON = resultsRequestSOAP.toString();
				
				ls =  crearListaClientes(strJSON);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return ls;
		}
			
	public List<Cliente> crearListaClientes(String strJson){
			List<Cliente> ls= new ArrayList<Cliente>();
			//se crea el objeto que ayuda deserealizar la cadena JSON
					gson = new Gson();
					//Obtenemos el tipo de un ArrayList<AndroidSO>
					Type lstT = new TypeToken< ArrayList<Cliente>>(){}.getType();
					ls = gson.fromJson(strJson, lstT);
			return ls;
		}

		// empleados
		
	public List<Empleado> obtenerEmpleados(){
				List<Empleado> ls= null;
				request = new SoapObject(NAMESPACE, METHOD_OBTENEREMPLEADOS);
				//Se crea un objeto SoapSerializationEnvelope para serealizar la
//				peticion SOAP y permitir viajar el mensaje por la nube
//				el constructor recibe la version de SOAP
				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
				//Se envuelve la peticion soap
				envelope.setOutputSoapObject(request);
				
				//Objeto que representa el modelo de transporte
				//Recibe la URL del ws
				HttpTransportSE transporte = new HttpTransportSE(URL);
				try {	
					//Hace la llamada al ws
					transporte.call(SOAP_OBTENER_EMPLEADOS, envelope);
					
					//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
					//de la peticion
					resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
					
					String  strJSON = resultsRequestSOAP.toString();
					
					ls =  crearListaEmpleados(strJSON);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return ls;
			}
				
	public List<Empleado> crearListaEmpleados(String strJson){
				List<Empleado> ls= new ArrayList<Empleado>();
				//se crea el objeto que ayuda deserealizar la cadena JSON
						gson = new Gson();
						//Obtenemos el tipo de un ArrayList<AndroidSO>
						Type lstT = new TypeToken< ArrayList<Empleado>>(){}.getType();
						ls = gson.fromJson(strJson, lstT);
				return ls;
			}
		
	//visita
	public List<Visita> obtenerVisitas(){
			List<Visita> ls= null;
			request = new SoapObject(NAMESPACE, METHOD_OBTENERVISITAS);
			//Se crea un objeto SoapSerializationEnvelope para serealizar la
//			peticion SOAP y permitir viajar el mensaje por la nube
//			el constructor recibe la version de SOAP
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
			//Se envuelve la peticion soap
			envelope.setOutputSoapObject(request);
			
			//Objeto que representa el modelo de transporte
			//Recibe la URL del ws
			HttpTransportSE transporte = new HttpTransportSE(URL);
			try {	
				//Hace la llamada al ws
				transporte.call(SOAP_OBTENER_VISITAS, envelope);
				
				//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
				//de la peticion
				resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
				
				String  strJSON = resultsRequestSOAP.toString();
				
				ls =  crearListaVisitas(strJSON);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return ls;
		}
			
	public List<Visita> crearListaVisitas(String strJson){
			List<Visita> ls= new ArrayList<Visita>();
			//se crea el objeto que ayuda deserealizar la cadena JSON
					gson = new Gson();
					//Obtenemos el tipo de un ArrayList<AndroidSO>
					Type lstT = new TypeToken< ArrayList<Visita>>(){}.getType();
					ls = gson.fromJson(strJson, lstT);
			return ls;
		}
	
		
	//Tipos de Documento
	public List<TipoDocumento> obtenerTiposDocumento(){
				List<TipoDocumento> ls= null;
				request = new SoapObject(NAMESPACE, METHOD_OBTENERTIPODOCUMENTOS);
				//Se crea un objeto SoapSerializationEnvelope para serealizar la
//				peticion SOAP y permitir viajar el mensaje por la nube
//				el constructor recibe la version de SOAP
				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
				//Se envuelve la peticion soap
				envelope.setOutputSoapObject(request);
				
				//Objeto que representa el modelo de transporte
				//Recibe la URL del ws
				HttpTransportSE transporte = new HttpTransportSE(URL);
				try {	
					//Hace la llamada al ws
					transporte.call(SOAP_OBTENER_TIPO_DOCUMENTOS, envelope);
					
					//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
					//de la peticion
					resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
					
					String  strJSON = resultsRequestSOAP.toString();
					
					ls =  crearListaTipoDocumentos(strJSON);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return ls;
			}
				
	public List<TipoDocumento> crearListaTipoDocumentos(String strJson){
				List<TipoDocumento> ls= new ArrayList<TipoDocumento>();
				//se crea el objeto que ayuda deserealizar la cadena JSON
						gson = new Gson();
						//Obtenemos el tipo de un ArrayList<AndroidSO>
						Type lstT = new TypeToken< ArrayList<TipoDocumento>>(){}.getType();
						ls = gson.fromJson(strJson, lstT);
				return ls;
			}
		
	
	//Tipos de Visitas
	public List<TipoVisita> obtenerTiposVisita(){
				List<TipoVisita> ls= null;
				request = new SoapObject(NAMESPACE, METHOD_OBTENERTIPOVISITAS);
				//Se crea un objeto SoapSerializationEnvelope para serealizar la
//				peticion SOAP y permitir viajar el mensaje por la nube
//				el constructor recibe la version de SOAP
				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
				//Se envuelve la peticion soap
				envelope.setOutputSoapObject(request);
				
				//Objeto que representa el modelo de transporte
				//Recibe la URL del ws
				HttpTransportSE transporte = new HttpTransportSE(URL);
				try {	
					//Hace la llamada al ws
					transporte.call(SOAP_OBTENER_TIPO_VISITAS, envelope);
					
					//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
					//de la peticion
					resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
					
					String  strJSON = resultsRequestSOAP.toString();
					
					ls =  crearListaTipoVisitas(strJSON);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return ls;
			}
				
	public List<TipoVisita> crearListaTipoVisitas(String strJson){
				List<TipoVisita> ls= new ArrayList<TipoVisita>();
				//se crea el objeto que ayuda deserealizar la cadena JSON
						gson = new Gson();
						//Obtenemos el tipo de un ArrayList<AndroidSO>
						Type lstT = new TypeToken< ArrayList<TipoVisita>>(){}.getType();
						ls = gson.fromJson(strJson, lstT);
				return ls;
			}
		
	
	//Estados de Visitas
	public List<EstadoVisita> obtenerEstadosVisita(){
				List<EstadoVisita> ls= null;
				request = new SoapObject(NAMESPACE, METHOD_OBTENERESTADOVISITAS);
				//Se crea un objeto SoapSerializationEnvelope para serealizar la
//				peticion SOAP y permitir viajar el mensaje por la nube
//				el constructor recibe la version de SOAP
				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
				//Se envuelve la peticion soap
				envelope.setOutputSoapObject(request);
				
				//Objeto que representa el modelo de transporte
				//Recibe la URL del ws
				HttpTransportSE transporte = new HttpTransportSE(URL);
				try {	
					//Hace la llamada al ws
					transporte.call(SOAP_OBTENER_ESTADO_VISITAS, envelope);
					
					//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
					//de la peticion
					resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
					
					String  strJSON = resultsRequestSOAP.toString();
					
					ls =  crearListaEstadoVisitas(strJSON);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return ls;
			}
				
	public List<EstadoVisita> crearListaEstadoVisitas(String strJson){
				List<EstadoVisita> ls= new ArrayList<EstadoVisita>();
				//se crea el objeto que ayuda deserealizar la cadena JSON
						gson = new Gson();
						//Obtenemos el tipo de un ArrayList<AndroidSO>
						Type lstT = new TypeToken< ArrayList<EstadoVisita>>(){}.getType();
						ls = gson.fromJson(strJson, lstT);
				return ls;
			}
		
	//Empresas de Carga
	public List<EmpresaCarga> obtenerEmpresaCargas(){
					List<EmpresaCarga> ls= null;
					request = new SoapObject(NAMESPACE, METHOD_OBTENEREMPRESACARGA);
					//Se crea un objeto SoapSerializationEnvelope para serealizar la
//					peticion SOAP y permitir viajar el mensaje por la nube
//					el constructor recibe la version de SOAP
					envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
					//Se envuelve la peticion soap
					envelope.setOutputSoapObject(request);
					
					//Objeto que representa el modelo de transporte
					//Recibe la URL del ws
					HttpTransportSE transporte = new HttpTransportSE(URL);
					try {	
						//Hace la llamada al ws
						transporte.call(SOAP_OBTENER_EMPRESA_CARGA, envelope);
						
						//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
						//de la peticion
						resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
						
						String  strJSON = resultsRequestSOAP.toString();
						
						ls =  crearListaEmpresaCargas(strJSON);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return ls;
				}
					
	public List<EmpresaCarga> crearListaEmpresaCargas(String strJson){
					List<EmpresaCarga> ls= new ArrayList<EmpresaCarga>();
					//se crea el objeto que ayuda deserealizar la cadena JSON
							gson = new Gson();
							//Obtenemos el tipo de un ArrayList<AndroidSO>
							Type lstT = new TypeToken< ArrayList<EmpresaCarga>>(){}.getType();
							ls = gson.fromJson(strJson, lstT);
					return ls;
				}
			
	//Formas de Pago
	public List<FormaPago> obtenerFormasPago(){
					List<FormaPago> ls= null;
					request = new SoapObject(NAMESPACE, METHOD_OBTENERFORMAPAGO);
					//Se crea un objeto SoapSerializationEnvelope para serealizar la
//					peticion SOAP y permitir viajar el mensaje por la nube
//					el constructor recibe la version de SOAP
					envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
					//Se envuelve la peticion soap
					envelope.setOutputSoapObject(request);
					
					//Objeto que representa el modelo de transporte
					//Recibe la URL del ws
					HttpTransportSE transporte = new HttpTransportSE(URL);
					try {	
						//Hace la llamada al ws
						transporte.call(SOAP_OBTENER_FORMA_PAGO, envelope);
						
						//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
						//de la peticion
						resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
						
						String  strJSON = resultsRequestSOAP.toString();
						
						ls =  crearListaFormaPagos(strJSON);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return ls;
				}
					
	public List<FormaPago> crearListaFormaPagos(String strJson){
					List<FormaPago> ls= new ArrayList<FormaPago>();
					//se crea el objeto que ayuda deserealizar la cadena JSON
							gson = new Gson();
							//Obtenemos el tipo de un ArrayList<AndroidSO>
							Type lstT = new TypeToken< ArrayList<FormaPago>>(){}.getType();
							ls = gson.fromJson(strJson, lstT);
					return ls;
				}
		
	//Obtener Tallas
	public List<Talla> obtenerTallas(){
					List<Talla> ls= null;
					request = new SoapObject(NAMESPACE, METHOD_OBTENERTALLAS);
					//Se crea un objeto SoapSerializationEnvelope para serealizar la
//					peticion SOAP y permitir viajar el mensaje por la nube
//					el constructor recibe la version de SOAP
					envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
					//Se envuelve la peticion soap
					envelope.setOutputSoapObject(request);
					
					//Objeto que representa el modelo de transporte
					//Recibe la URL del ws
					HttpTransportSE transporte = new HttpTransportSE(URL);
					try {	
						//Hace la llamada al ws
						transporte.call(SOAP_OBTENER_TALLAS, envelope);
						
						//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
						//de la peticion
						resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
						
						String  strJSON = resultsRequestSOAP.toString();
						
						ls =  crearListaTallas(strJSON);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return ls;
				}
					
	public List<Talla> crearListaTallas(String strJson){
					List<Talla> ls= new ArrayList<Talla>();
					//se crea el objeto que ayuda deserealizar la cadena JSON
							gson = new Gson();
							//Obtenemos el tipo de un ArrayList<AndroidSO>
							Type lstT = new TypeToken< ArrayList<Talla>>(){}.getType();
							ls = gson.fromJson(strJson, lstT);
					return ls;
				}
			
	//Obtener Productos
	public List<Producto> obtenerProductos(){
					List<Producto> ls= null;
					request = new SoapObject(NAMESPACE, METHOD_OBTENERPRODUCTOS);
					//Se crea un objeto SoapSerializationEnvelope para serealizar la
//					peticion SOAP y permitir viajar el mensaje por la nube
//					el constructor recibe la version de SOAP
					envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
					//Se envuelve la peticion soap
					envelope.setOutputSoapObject(request);
					
					//Objeto que representa el modelo de transporte
					//Recibe la URL del ws
					HttpTransportSE transporte = new HttpTransportSE(URL);
					try {	
						//Hace la llamada al ws
						transporte.call(SOAP_OBTENER_PRODUCTOS, envelope);
						
						//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
						//de la peticion
						resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
						
						String  strJSON = resultsRequestSOAP.toString();
						
						ls =  crearListaProductos(strJSON);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return ls;
				}
					
	public List<Producto> crearListaProductos(String strJson){
					List<Producto> ls= new ArrayList<Producto>();
					//se crea el objeto que ayuda deserealizar la cadena JSON
							gson = new Gson();
							//Obtenemos el tipo de un ArrayList<AndroidSO>
							Type lstT = new TypeToken< ArrayList<Producto>>(){}.getType();
							ls = gson.fromJson(strJson, lstT);
					return ls;
				}
		
	//Obtener Productos Forma pago
	public List<ProductoFormaPago> obtenerProductoFormaPagos(){
					List<ProductoFormaPago> ls= null;
					request = new SoapObject(NAMESPACE, METHOD_OBTENERPRODUCTOSFORMAPAGO);
					//Se crea un objeto SoapSerializationEnvelope para serealizar la
//					peticion SOAP y permitir viajar el mensaje por la nube
//					el constructor recibe la version de SOAP
					envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
					//Se envuelve la peticion soap
					envelope.setOutputSoapObject(request);
					
					//Objeto que representa el modelo de transporte
					//Recibe la URL del ws
					HttpTransportSE transporte = new HttpTransportSE(URL);
					try {	
						//Hace la llamada al ws
						transporte.call(SOAP_OBTENER_PRODUCTOS_FORMA_PAGO, envelope);
						
						//Se crea un objeto SoapPrimitive y se obtiene la respuesta 
						//de la peticion
						resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
						
						String  strJSON = resultsRequestSOAP.toString();
						
						ls =  crearListaProductoFormaPagos(strJSON);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return ls;
				}
					
	public List<ProductoFormaPago> crearListaProductoFormaPagos(String strJson){
					List<ProductoFormaPago> ls= new ArrayList<ProductoFormaPago>();
					//se crea el objeto que ayuda deserealizar la cadena JSON
							gson = new Gson();
							//Obtenemos el tipo de un ArrayList<AndroidSO>
							Type lstT = new TypeToken< ArrayList<ProductoFormaPago>>(){}.getType();
							ls = gson.fromJson(strJson, lstT);
					return ls;
				}
			
}

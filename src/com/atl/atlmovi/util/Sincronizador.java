package com.atl.atlmovi.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.entidades.Usuario;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Sincronizador {
	
	private static final String NAMESPACE = "http://tempuri.org/";
	private static String URL="http://172.24.3.15:82/servicio/Servicios.asmx";
	private static final String METHOD_NAME = "obtenerUsuarios";
	private static final String SOAP_ACTION ="http://tempuri.org/obtenerUsuarios";

	
	private SoapObject request=null;
	private SoapSerializationEnvelope envelope=null;
	private SoapPrimitive  resultsRequestSOAP=null;
	
	Gson gson ;
	
	
	
	public List<Usuario> obtenerUsuarios(){
		List<Usuario> ls= null;
		request = new SoapObject(NAMESPACE, METHOD_NAME);
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
			transporte.call(SOAP_ACTION, envelope);
			
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
	
}
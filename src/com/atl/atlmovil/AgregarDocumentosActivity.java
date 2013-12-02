package com.atl.atlmovil;

import java.text.SimpleDateFormat;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.dao.AmortizacionDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.DocumentoPagoDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Amortizacion;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.DocumentoPago;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class AgregarDocumentosActivity extends Activity implements OnClickListener{

	Amortizacion amortizacion;
	Cobranza cobranza;
	DocumentoPago documento;
	
	String operacion;
	
	AmortizacionDAO amDao;
	CobranzaDAO cobDao;
	DocumentoPagoDAO docDao;
	VisitaDAO viDao;
	
	
	/*
	 * lblNroCobranzaAgregarDocumentos
	 * cmbTipoDocumentoAgregarDoc
	 * txtDocumentoPagoAgregarDoc
	 * btnBuscarDocumentoPago
	 * txtFechaVencimientoAgregarDocumentos
	 * txtImportePendienteAgregarDoc
	 * txtImporteAmortizarAgregarDocs
	 * btnAgregarDocumento
	 * btnCancelarAgregarDocumento
	 * 
	 * */
	
	final int  BUSCAR_DOCUMENTO = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_documentos);
		// registrar botones
		Button btnBuscarDocumentoPago = (Button)findViewById(R.id.btnAgregarDocumentoPago);
		Button btnAgregarDocumento = (Button)findViewById(R.id.btnAgregarDocumento);
		Button btnCancelarAgregarDocumento = (Button)findViewById(R.id.btnCancelarAgregarDocumento);
		
		btnBuscarDocumentoPago.setOnClickListener(this);
		btnAgregarDocumento.setOnClickListener(this);
		btnCancelarAgregarDocumento.setOnClickListener(this);
		
		amDao=new AmortizacionDAO(this);
		cobDao = new CobranzaDAO(this);
		docDao = new DocumentoPagoDAO(this);
		viDao = new VisitaDAO(this);
		abrirConexion();
		
		Intent intent = getIntent();
		long idCobranza =  intent.getLongExtra("idCobranza", 0);
		long idAmortizacion = intent.getLongExtra("idAmortizacion", 0);
		cobranza = cobDao.buscarPorID(idCobranza);		
		operacion = intent.getStringExtra("operacion");
		if(cobranza!=null){
			TextView lblNroCobranza = (TextView)findViewById(R.id.lblNroCobranzaAgregarDocumentos);
			lblNroCobranza.setText("Nro de Cobranza: "+Cadena.formatearNumero("0000000000",(double)cobranza.getId() ));
			
		}
		if(operacion.equals("editar")){
			amortizacion = amDao.buscarPorID(idAmortizacion);
			documento = docDao.buscarPorID(amortizacion.getCodigoDocumentoPago());
			cargarDatos();
			
		}
		
			
	}
	
	private void guardarAmortizacion(){
		try{
			if(documento!=null){
				if(operacion.equals("insertar")){
					amortizacion = new Amortizacion();
					amortizacion.setIdCobranza(cobranza.getId());
					
				}
				EditText txtImporteAmortizar = (EditText)findViewById(R.id.txtImporteAmortizarAgregarDocs);
				
				amortizacion.setCodigoDocumentoPago(documento.getCodigoDocumentoPago());
				amortizacion.setAnotacionAmortizacion("Registrado en disp movil");
				String strImporte = txtImporteAmortizar.getText().toString();
				if(strImporte.trim().equals("")){
					strImporte = "0.0";
					
				}
				double importe = Double.parseDouble(strImporte);
				amortizacion.setImporteAmortizacion(importe);
				
				// validar que el importe sea menor o igual al importe pendiente del documento y que sea mayor que cero
				if(importe>0){
					if(importe<=documento.getImportePendienteDocumentoPago()){
						if(operacion.equals("insertar")){
							amortizacion = amDao.crear(amortizacion);
							
						} else if(operacion.equals("editar")){
							
							amortizacion = amDao.actualizar(amortizacion);
							
						}
						finish();
					} else{
						
						mostrarMensaje("ADVERTENCIA", "El importe no puede ser mayor al importe pendiente del documento");
						
					}
					
				} else{
					
					mostrarMensaje("ADVERTENCIA", "El importe debe ser mayor a cero");
					
				}
				
			}
			else{
				mostrarMensaje("ADVERTENCIA", "Debe seleccionar un documento");
				
			}
			
		} catch(Exception ex){
			mostrarMensaje("ERROR", "No se pudo agregar el documento " + ex.getMessage());
		}
		
	}
	/*
	private void cargarTipoDocumento(){
		//
		String[] estados = 	{"FACTURA","BOLETA","LETRA"};
		Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbTipoDocumentoAgregarDoc);
		ArrayAdapter<String> formaPagoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, estados);
		cmbFormaPago.setAdapter(formaPagoAdapter);
		
		
	}
	*/
	private void cargarDatos(){
		if(amortizacion !=null){
			EditText txtImporteAmortizar = (EditText)findViewById(R.id.txtImporteAmortizarAgregarDocs);
			
			txtImporteAmortizar.setText(Cadena.formatearNumero("0.00", amortizacion.getImporteAmortizacion()));
			
			documento = docDao.buscarPorID(amortizacion.getCodigoDocumentoPago());
			cargarDocumento();
			
		}
		
	}
	
	private void cargarDocumento(){
		if(documento!=null){
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
			EditText txtDocumentoPago = (EditText)findViewById(R.id.txtDocumentoPagoAgregarDoc);
			EditText txtFechaVencimiento = (EditText)findViewById(R.id.txtFechaVencimientoAgregarDocumentos);
			EditText txtImportePendiente = (EditText)findViewById(R.id.txtImportePendienteAgregarDoc);
			EditText txtTipoDocumento = (EditText)findViewById(R.id.txtTipoDocumentoAgregarDoc);
			txtTipoDocumento.setText(documento.getTipoDocumentoPago());
			txtDocumentoPago.setText(documento.getReferenciaDocumentoPago());
			txtFechaVencimiento.setText(dateFormat.format(documento.getFechaVencimientoDocumentoPago()));
			txtImportePendiente.setText(Cadena.formatearNumero("0.00", documento.getImportePendienteDocumentoPago() ));
			
		}
		
	}
	
	private void abrirConexion(){
		amDao.open();
		cobDao.open();
		docDao.open();
		viDao.open();
		
		
	}
	private void cerrarConexion(){
		amDao.close();
		cobDao.close();
		docDao.close();
		viDao.close();
		
		
	}
	
	public void mostrarMensaje( String titulo, String mensaje){
		
		AlertDialog.Builder errorDialog = new AlertDialog.Builder(AgregarDocumentosActivity.this);
		errorDialog.setTitle(titulo);
		errorDialog.setMessage(mensaje);
		errorDialog.setIcon(android.R.drawable.ic_dialog_alert);
		errorDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		errorDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		 menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "MENU PRINCIPAL");
		return true;
	}
	  @Override
      public boolean onOptionsItemSelected(MenuItem item)
      {
          switch(item.getItemId())
          {
              case Menu.FIRST:
                    // do whatever
            	  Log.w("info","Menu principal presionado");
            	  Intent intent = new Intent(this, MenuPrincipalActivity.class);
            	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
            	    startActivity(intent);
            	  
            	  
                    return true;
              default:
                    return super.onOptionsItemSelected(item);
          }
      }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnAgregarDocumentoPago){
			// abrir actividad para buscar documento
			//obtener visita
			if(cobranza!=null){
				Visita vi  = viDao.buscarPorID(cobranza.getCodigoVisita());
				Intent buscarDocumentoIntent = new Intent(AgregarDocumentosActivity.this, BuscarDocumentoPagoActivity.class);
				buscarDocumentoIntent.putExtra("codigoCliente", vi.getCodigoCliente());	
				startActivityForResult(buscarDocumentoIntent,BUSCAR_DOCUMENTO);
			}
			
		}
		if(v.getId()==R.id.btnAgregarDocumento){
			guardarAmortizacion();
			
		}
		if(v.getId()==R.id.btnCancelarAgregarDocumento){
			finish();
			
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  	abrirConexion();
	  switch(requestCode) {
	    case (BUSCAR_DOCUMENTO) : {
	      if (resultCode == Activity.RESULT_OK) {
	        // TODO Extract the data returned from the child Activity.
	    	  long codDocumento =  data.getLongExtra("codigoDocumentoPago", 0);
	    	  documento = docDao.buscarPorID(codDocumento);
	    	 // cargar Datos de Documento
	    	 cargarDocumento();
	      }
	      break;
	    } 
	  }
	  
	  cerrarConexion();
	}
	
	
	@Override
	protected void onResume() {
		abrirConexion();
		cargarDatos();
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
	}

}

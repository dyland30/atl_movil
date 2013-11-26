package com.atl.atlmovil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.MedioPagoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.MedioPago;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
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

public class NuevaCobranzaActivity extends Activity implements OnClickListener{

	Visita visitaActiva;
	Cliente cliente;
	List<MedioPago> lsMedioPago;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	CobranzaDAO cobDao;
	MedioPagoDAO mpDao;
	
	Cobranza cobranza;
	String operacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva_cobranza);
		// registrar botones
		Button btnGuardar = (Button)findViewById(R.id.btnGuardarCobranza);
		Button btnCancelar =(Button)findViewById(R.id.btnCancelarCobranza);
		Button btnDetalleCobranza = (Button)findViewById(R.id.btnDetalleCobranza);
		Button btnAutoDistribuirCobranza = (Button)findViewById(R.id.btnAutoDistribuirCobranza);
		Button btnRegistrarDepositoCobranza = (Button)findViewById(R.id.btnRegistrarDepositoCobranza);
		Spinner cmbEstadoNuevaCobranza = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
		btnGuardar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
		btnDetalleCobranza.setOnClickListener(this);
		btnAutoDistribuirCobranza.setOnClickListener(this);
		btnRegistrarDepositoCobranza.setOnClickListener(this);
		
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		cobDao = new CobranzaDAO(this);
		mpDao = new MedioPagoDAO(this);
		abrirConexion();
		
		Intent intent = getIntent();
		operacion = intent.getStringExtra("operacion");
		obtenerVisitaActiva();
		cargarCmbEstado();
		cargarCmbMedioPago();
		
		
		if(operacion.equals("editar")){
			long idCobranza = intent.getLongExtra("idCobranza", 0);
			cobranza = cobDao.buscarPorID(idCobranza);
			
			//obtener detalles
			cmbEstadoNuevaCobranza.setEnabled(true);
			
			// cargar Datos
			cargarDatos();
		} else{
			//inactivar spinner
			
			cmbEstadoNuevaCobranza.setEnabled(false);
		}
		
	}
	private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.lblVisitaNuevaCobranza);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.lblClienteNuevaCobranza);
			cliente = cliDao.buscarPorID(visitaActiva.getCodigoCliente());
			if(cliente!=null){
				PersonaDAO perDao = new PersonaDAO(this);
				perDao.open();
				Persona per = perDao.buscarPorID(cliente.getCodigoPersona());
				txtCLientePedido.setText("Cliente: "+per.getDocumentoPersona()+" "+ per.getNombrePersona());
				perDao.close();
			}
		}
	}
	
private void cargarCmbEstado(){
		
		String[] estados = 	{"Ingresado","Retenido","Anulado"};
		Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
		ArrayAdapter<String> formaPagoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, estados);
		cmbFormaPago.setAdapter(formaPagoAdapter);
		
	}

private void cargarCmbMedioPago(){
	lsMedioPago = new ArrayList<MedioPago>();
	lsMedioPago = mpDao.obtenerTodos();
	Spinner cmbTipoVisita = (Spinner)findViewById(R.id.cmbMedioPagoNuevaCobranza);
	ArrayAdapter<MedioPago> tipoVisitaAdapter = new ArrayAdapter<MedioPago>(this,
            android.R.layout.simple_spinner_item, lsMedioPago);
	cmbTipoVisita.setAdapter(tipoVisitaAdapter);
	
}

private void cargarDatos(){
	
	/*
	 * @+id/lblNroCobranzaNueva
	 * @+id/lblVisitaNuevaCobranza
	 * @+id/lblClienteNuevaCobranza
	 * @+id/cmbEstadoNuevaCobranza
	 * @+id/txtImporteCobranzaNueva
	 * @+id/cmbMedioPagoNuevaCobranza
	 * @+id/btnCancelarCobranza
	 * @+id/btnGuardarCobranza
	 * @+id/btnDetalleCobranza
	 * @+id/btnAutoDistribuirCobranza
	 * @+id/btnRegistrarDepositoCobranza
	 * */
	if(cobranza!=null){
		//en caso la cobranza haya sido modificada es necesario obtener la ultima de la bd
		cobranza = cobDao.buscarPorID(cobranza.getId());
		Button btnAutoDistribuirCobranza = (Button)findViewById(R.id.btnAutoDistribuirCobranza);
		int cantDetalles = cobDao.obtenerCantidadDetalles(cobranza.getId());
		if(cantDetalles>0){
			
			btnAutoDistribuirCobranza.setEnabled(false);
			
			
		} else {
			
			btnAutoDistribuirCobranza.setEnabled(true);
		}
		
		
		Spinner cmbMedioPago = (Spinner)findViewById(R.id.cmbMedioPagoNuevaCobranza);
		Spinner cmbEstadoNuevaCobranza = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
		EditText txtImporteCobranzaNueva = (EditText)findViewById(R.id.txtImporteCobranzaNueva);
		TextView lblNroCobranzaNueva = (TextView)findViewById(R.id.lblNroCobranzaNueva);
		lblNroCobranzaNueva.setText("Nro Cobranza: "+Cadena.formatearNumero("0000000000",  (double)cobranza.getId()));
		txtImporteCobranzaNueva.setText(cobranza.getImporteCobranza()+"");
		
		// establecer estado seleccionado
		@SuppressWarnings("unchecked")
		ArrayAdapter<String> estadoPedidoAdapter =  (ArrayAdapter<String>)cmbEstadoNuevaCobranza.getAdapter();
		cmbEstadoNuevaCobranza.setSelection(estadoPedidoAdapter.getPosition(cobranza.getEstadoCobranza()));
		
		// establecer forma de pago seleccionado
		@SuppressWarnings( "unchecked")
		ArrayAdapter<MedioPago> medioPagoAdapter = (ArrayAdapter<MedioPago>)cmbMedioPago.getAdapter();
		MedioPago mp = mpDao.buscarPorID(cobranza.getCodigoMedioPago());
		if(mp!=null){
			cmbMedioPago.setSelection(medioPagoAdapter.getPosition(mp));
		}	
	}
}

private void guardarCobranza(){
	try{
		Spinner cmbMedioPago = (Spinner)findViewById(R.id.cmbMedioPagoNuevaCobranza);
		Spinner cmbEstadoNuevaCobranza = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
		EditText txtImporteCobranzaNueva = (EditText)findViewById(R.id.txtImporteCobranzaNueva);
		String strImp = txtImporteCobranzaNueva.getText().toString();
		if(strImp.trim().equals("")) strImp="0.0";
		
		double importe = Double.parseDouble(strImp);
		
		if(operacion.equals("insertar")){
			cobranza = new Cobranza();
			cobranza.setCodigoCobranza(0);
			cobranza.setCodigoVisita(visitaActiva.getCodigoVisita());
			cobranza.setEsAutoDistribuido(false);
		}
		MedioPago mp = (MedioPago)cmbMedioPago.getSelectedItem();
		if(mp!=null){
			cobranza.setCodigoMedioPago(mp.getCodigoMedioPago());
		}			
		cobranza.setEstadoCobranza((String)cmbEstadoNuevaCobranza.getSelectedItem());
		cobranza.setEstaSincronizado(false);
		cobranza.setImporteCobranza(importe);
		
		if(operacion.equals("insertar")){
			cobranza.setFechaCObranza(new Date());	
		}
		// depende de la operacion
		if(operacion.equals("editar")){
			cobranza=cobDao.actualizar(cobranza);
		} else{
			cobranza  = cobDao.crear(cobranza);
			
			
		}
		
	}catch(Exception ex){
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(NuevaCobranzaActivity.this);
		alertDialog.setTitle("ERROR");
		alertDialog.setMessage("Ha ocurrido un error al guardar el pedido: "+ex.getMessage());
		alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		alertDialog.show();
		Log.w("ERROR","Error "+ex.getMessage());
	}
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
	protected void onResume() {
		abrirConexion();
		obtenerVisitaActiva();
		cargarCmbEstado();
		cargarCmbMedioPago();
		cargarDatos();
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
	}
	
	private void abrirConexion(){
		viDao.open();
		cliDao.open();
		cobDao.open();
		mpDao.open();
	}
	
	private void cerrarConexion(){
		 viDao.close();
		 cliDao.close();
		 cobDao.close();
		 mpDao.close();
		}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnGuardarCobranza){
			// guardar y matar actividad
			guardarCobranza();
			finish();
			
		}
		if(v.getId()==R.id.btnDetalleCobranza){
			// guardar e iniciar actividad
			guardarCobranza();
			if(cobranza!=null){
				Intent detalleCobranzaIntent = new Intent(NuevaCobranzaActivity.this, DetalleCobranzaActivity.class);
				detalleCobranzaIntent.putExtra("idCobranza", cobranza.getId());
				startActivity(detalleCobranzaIntent);
			}
		}
		if(v.getId()==R.id.btnRegistrarDepositoCobranza){
			//abrir actividad deposito
			if(cobranza!=null){
				Intent registrarDepositoIntent = new Intent(NuevaCobranzaActivity.this, NuevoDepositoActivity.class);
				registrarDepositoIntent.putExtra("idCobranza", cobranza.getId());
				registrarDepositoIntent.putExtra("operacion", "insertar");
				startActivity(registrarDepositoIntent);
			} else{
				// mostrar mensaje
				mostrarMensaje("ERROR", "Para registrar un depósito primero debe guardar la cobranza");	
			}
		}
		if(v.getId()==R.id.btnAutoDistribuirCobranza){
			guardarCobranza();
			if(cobranza!=null){
				try {
					cobDao.autoDistribuir(cobranza);
					// ir al detalle de la cobranza para mostrar las cobranzas distribuidas
					Intent detalleCobranzaIntent = new Intent(NuevaCobranzaActivity.this, DetalleCobranzaActivity.class);
					detalleCobranzaIntent.putExtra("idCobranza", cobranza.getId());
					startActivity(detalleCobranzaIntent);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					mostrarMensaje("Error", "Ocurrio un error al distribuir la cobranza "+ e.getMessage());
					
				}
			}
		}
		if(v.getId()==R.id.btnCancelarCobranza){
			finish();
			
			
		}
		
	}
	
	public void mostrarMensaje( String titulo, String mensaje){		
		AlertDialog.Builder errorDialog = new AlertDialog.Builder(NuevaCobranzaActivity.this);
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
	

}

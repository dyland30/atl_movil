package com.atl.atlmovil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.EmpresaCargaDAO;
import com.atl.atlmovil.dao.FormaPagoDAO;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.EmpresaCarga;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;
import com.atl.atlmovil.entidades.FormaPago;





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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NuevoPedido extends Activity implements OnClickListener {
	
	Visita visitaActiva;
	Cliente clientePedido;
	Pedido pedido;
	EmpresaCarga empCarga;
	
	PedidoDAO pDao;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	EmpresaCargaDAO empDao;
	List<FormaPago> lsFpago;
	FormaPagoDAO fpDao;
	String operacion;
	final int BUSCAR_EMPRESA_CARGA_ACTIVITY = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_pedido);
		pDao = new PedidoDAO(this);
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		fpDao = new FormaPagoDAO(this);
		empDao = new EmpresaCargaDAO(this);
		abrirConexion();
		// registrar botones
		Button btnGuardar = (Button)findViewById(R.id.btnGuardarPedido);
		Button btnCancelar = (Button)findViewById(R.id.btnCancelarPedido);
		Button btnDetalles = (Button)findViewById(R.id.btnDetallePedido);
		Button btnEmpCarga = (Button)findViewById(R.id.btnBuscarEmpresaTransporte);
		btnGuardar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
		btnDetalles.setOnClickListener(this);
		btnEmpCarga.setOnClickListener(this);
		
		
		obtenerVisitaActiva();
		cargarCmbFormaPago();
		cargarCmbEstadoPedido();
		// obtener operacion (nuevo, modificar)
		Intent intent = getIntent();
		operacion = intent.getStringExtra("operacion");
		
		if(operacion.equals("editar")){
			long idPedido = intent.getLongExtra("idPedido", 0);
			pedido = pDao.buscarPorID(idPedido);
			
			//empresa carga
			empCarga = empDao.buscarPorID(pedido.getCodigoEmpresaCarga());
			// cargar Datos
			cargarDatos();
		} else{
			Spinner cmbEstadoPedido = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
			cmbEstadoPedido.setEnabled(false);
			
		}
		
		//nuevo
		
		
		
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
	
	private void cargarDatos(){
		if(pedido!=null){
			Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbFormaPago);
			Spinner cmbEstadoPedido = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
			CheckBox chbAceptaRetencion = (CheckBox)findViewById(R.id.chbAceptaRetencion);
			EditText txtDireccionEnvio = (EditText)findViewById(R.id.txtDireccionEnvioPedido);
			EditText txtInstrucciones = (EditText)findViewById(R.id.txtInstruccionesPedido);
			TextView lblNumeroPedido = (TextView)findViewById(R.id.lblNumeroNuevoPedido);
			
			lblNumeroPedido.setText("Cod Pedido: "+Cadena.formatearNumero("000000000000", (double)pedido.getId()));
			txtInstrucciones.setText(pedido.getInstruccionesEspeciales());
			txtDireccionEnvio.setText(pedido.getDireccionDeEnvio());
			chbAceptaRetencion.setChecked(pedido.getAceptaRetencionPedido());
			
			// establecer estado seleccionado
			@SuppressWarnings("unchecked")
			ArrayAdapter<String> estadoPedidoAdapter =  (ArrayAdapter<String>)cmbEstadoPedido.getAdapter();
			cmbEstadoPedido.setSelection(estadoPedidoAdapter.getPosition(pedido.getEstadoPedido()));
			
			// establecer forma de pago seleccionado
			@SuppressWarnings( "unchecked")
			ArrayAdapter<FormaPago> formaPagoAdapter = (ArrayAdapter<FormaPago>)cmbFormaPago.getAdapter();
			
			//obtener forma de pago
			FormaPago fpago = fpDao.buscarPorID(pedido.getCodigoFormaPago());
			if(fpago!=null){
				cmbFormaPago.setSelection(formaPagoAdapter.getPosition(fpago));
			}
			
			cargarEmpresaCarga();
			
		}

	}
	
	
	private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.lblNroVisitaNuevoPedido);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.lblClienteNuevoPedido);
			clientePedido = cliDao.buscarPorID(visitaActiva.getCodigoCliente());
			if(clientePedido!=null){
				PersonaDAO perDao = new PersonaDAO(this);
				perDao.open();
				Persona per = perDao.buscarPorID(clientePedido.getCodigoPersona());
				txtCLientePedido.setText("Cliente: "+per.getDocumentoPersona()+" "+ per.getNombrePersona());
				perDao.close();
				
				//establecer direccion de envio
				EditText direccionEnvio = (EditText)findViewById(R.id.txtDireccionEnvioPedido);
				direccionEnvio.setText(clientePedido.getDireccionEntregaCliente());
				
			}
		}
		
	}
	private void cargarCmbFormaPago(){
		lsFpago = new ArrayList<FormaPago>();
		lsFpago = fpDao.obtenerTodos();
		Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbFormaPago);
		ArrayAdapter<FormaPago> formaPagoAdapter = new ArrayAdapter<FormaPago>(this,
                android.R.layout.simple_spinner_item, lsFpago);
		cmbFormaPago.setAdapter(formaPagoAdapter);
		
	}
	private void cargarCmbEstadoPedido(){
		
		String[] estados = 	{"Ingresado","Retenido","Anulado"};
		Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
		ArrayAdapter<String> formaPagoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, estados);
		cmbFormaPago.setAdapter(formaPagoAdapter);
		
	}
	
	
	@Override
	protected void onResume() {
		abrirConexion();
		obtenerVisitaActiva();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
	}
	private void guardarPedido(){
		try{
			Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbFormaPago);
			Spinner cmbEstadoPedido = (Spinner)findViewById(R.id.cmbEstadoNuevaCobranza);
			CheckBox chbAceptaRetencion = (CheckBox)findViewById(R.id.chbAceptaRetencion);
			EditText txtDireccionEnvio = (EditText)findViewById(R.id.txtDireccionEnvioPedido);
			EditText txtInstrucciones = (EditText)findViewById(R.id.txtInstruccionesPedido);
			
			if(operacion.equals("insertar")){
				pedido = new Pedido();
				pedido.setCodigoPedido(0);
				pedido.setCodigoVisita(visitaActiva.getCodigoVisita());
				
			}
			
			
			FormaPago fp = (FormaPago)cmbFormaPago.getSelectedItem();
			if(fp!=null){
				pedido.setCodigoFormaPago(fp.getCodigoFormaPago());
			}			
			pedido.setEstadoPedido((String)cmbEstadoPedido.getSelectedItem());
			pedido.setAceptaRetencionPedido(chbAceptaRetencion.isChecked());
			
			if(empCarga!=null){
				pedido.setCodigoEmpresaCarga(empCarga.getCodigoEmpresaCarga());
				
			}
			
			pedido.setDireccionDeEnvio(txtDireccionEnvio.getText().toString());
			pedido.setInstruccionesEspeciales(txtInstrucciones.getText().toString());
			pedido.setEstaSincronizado(false);
			if(empCarga!=null){
				pedido.setCodigoEmpresaCarga(empCarga.getCodigoEmpresaCarga());
			}
			
			if(operacion.equals("insertar")){
				pedido.setFechaIngresoPedido(new Date());
				pedido.setEstaRetenidoPedido(false);
				
				pedido.setImportePedido(0.0D);
				// por el momento no se usa
				pedido.setLineaReservadaPedido(0.0D);
			}
			
			// depende de la operacion
			if(operacion.equals("editar")){
				pDao.actualizar(pedido);
			} else{
				pedido = pDao.crear(pedido);
			}
			
			
		}catch(Exception ex){
			
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(NuevoPedido.this);
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
	
	public void abrirConexion(){
		pDao.open();
		viDao.open();
		cliDao.open();
		fpDao.open();
		empDao.open();
	}
	public void cerrarConexion(){
		pDao.close();
		viDao.close();
		cliDao.close();
		fpDao.close();
		empDao.close();
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  abrirConexion();
	  
	  switch(requestCode) {
	    case (BUSCAR_EMPRESA_CARGA_ACTIVITY) : {
	      if (resultCode == Activity.RESULT_OK) {
	        // TODO Extract the data returned from the child Activity.
	    	 long codEmpresaCarga =  data.getIntExtra("codigoEmpresaCarga", 0);
	    	 empCarga = empDao.buscarPorID(codEmpresaCarga);
	    	 // cargar Datos de Producto
	    	 cargarEmpresaCarga();
	      }
	      break;
	    } 
	  }
	  
	    cerrarConexion();
		
	}

	public void cargarEmpresaCarga(){
		if(empCarga!=null){
			EditText txtEmpresaCarga = (EditText)findViewById(R.id.txtEmpresaTransportePedido);
			txtEmpresaCarga.setText(empCarga.getNombreEmpresaCarga());
		}
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnGuardarPedido){
			// guardar
			guardarPedido();
			// navegar a la lista de pedidos
			finish();
			
			
		}
		if(v.getId()==R.id.btnCancelarPedido){
			finish();
			
		}
		if(v.getId()==R.id.btnDetallePedido){
			//guardar pedido si no esta guardado
			if(pedido==null || pedido.getId()==0){
				guardarPedido();
				//navegar a la lista de 
				
			}
			
			if(pedido.getId()>0){
				Intent detallePedidoIntent = new Intent(NuevoPedido.this, DetallePedidoActivity.class);
				detallePedidoIntent.putExtra("idPedido", pedido.getId());				
				startActivity(detallePedidoIntent);
				
			}
			
			
		}
		if(v.getId()==R.id.btnBuscarEmpresaTransporte){
			//registrar actividad dialog
			Intent buscarEmpresaCargaIntent = new Intent(NuevoPedido.this, BuscarEmpresaTransporteActivity.class);
			//agregarProductoIntent.putExtra("codigoProducto", det.getIdPedido());
			startActivityForResult(buscarEmpresaCargaIntent,BUSCAR_EMPRESA_CARGA_ACTIVITY );
			
			
		}
		
		
	}
	
}

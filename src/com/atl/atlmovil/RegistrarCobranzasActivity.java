package com.atl.atlmovil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.atl.atlmovil.adapters.CobranzaAdapter;
import com.atl.atlmovil.adapters.PedidoAdapter;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrarCobranzasActivity extends ListActivity implements OnClickListener {

	List<Cobranza> lsCobranza;
	
	Visita visitaActiva;
	Cliente cliente;
	
	CobranzaDAO cobDao;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	CobranzaAdapter adapter;
	
	Date fechaDesde;
	Date fechaHasta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_cobranzas);
		
		//registrar botones
		Button btnNuevaCobranza = (Button)findViewById(R.id.btnNuevaCobranza);
		btnNuevaCobranza.setOnClickListener(this);
		
		
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		cobDao = new CobranzaDAO(this);
		
		// cargar fechas
		
		fechaHasta = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(fechaHasta);
		
	    c.add(Calendar.DATE, -7);
		fechaDesde = c.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		TextView lblFechaDesde = (TextView)findViewById(R.id.lblFechaDesde);
		TextView lblFechaHasta = (TextView)findViewById(R.id.lblFechaHasta);
		lblFechaDesde.setText("Del: "+dateFormat.format(fechaDesde));
		lblFechaHasta.setText("Al: "+dateFormat.format(fechaHasta));
		
		abrirConexion();
		cargarCmbEstado();
		obtenerVisitaActiva();
		cargarListaCobranzas();
		
	}
	
	private void cargarCmbEstado(){
		
		String[] estados = 	{"Ingresado","Retenido","Anulado"};
		Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbEstadoCobranzaFiltro);
		ArrayAdapter<String> formaPagoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, estados);
		cmbFormaPago.setAdapter(formaPagoAdapter);
		
	}
	
	private void abrirConexion(){
		viDao.open();
		cliDao.open();
		cobDao.open();
	}
	
	private void cerrarConexion(){
		 viDao.close();
		 cliDao.close();
		 cobDao.close();
		}

	
	
	@Override
	protected void onResume() {
		abrirConexion();
		cargarListaCobranzas();
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrar_cobranzas, menu);
		return true;
	}
	
	private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.lblVisitaRegistrarCobranzas);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.lblClienteRegistrarCobranzas);
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
		private void cargarListaCobranzas(){
		
		
		if(visitaActiva!=null){
			
			// filtrar por estado, fecha y 
			lsCobranza = cobDao.buscarPorVisita(visitaActiva.getCodigoVisita());
			
			//ListView lvVisitas = (ListView)findViewById(android.R.id.list);
			adapter = new CobranzaAdapter(this, lsCobranza);
			//lvVisitas.setAdapter(adapter);
			setListAdapter(adapter);
		}
		
		
		
	}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.btnNuevaCobranza){
				
				Intent nuevaCobranza = new Intent(RegistrarCobranzasActivity.this, NuevaCobranzaActivity.class);
				nuevaCobranza.putExtra("operacion", "insertar");
				startActivity(nuevaCobranza);
				
			}
		}
	
	

}

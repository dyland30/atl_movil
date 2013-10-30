package com.atl.atlmovil;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.MedioPagoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.MedioPago;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.TipoVisita;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class NuevaCobranzaActivity extends Activity {

	Visita visitaActiva;
	Cliente cliente;
	List<MedioPago> lsMedioPago;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	CobranzaDAO cobDao;
	MedioPagoDAO mpDao;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva_cobranza);
		
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		cobDao = new CobranzaDAO(this);
		mpDao = new MedioPagoDAO(this);
		abrirConexion();
		
		obtenerVisitaActiva();
		cargarCmbEstado();
		cargarCmbMedioPago();
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nueva_cobranza, menu);
		return true;
	}

	@Override
	protected void onResume() {
		abrirConexion();
		obtenerVisitaActiva();
		cargarCmbEstado();
		cargarCmbMedioPago();
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

}

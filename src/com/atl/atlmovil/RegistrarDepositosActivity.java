package com.atl.atlmovil;

import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.adapters.CobranzaAdapter;
import com.atl.atlmovil.adapters.DepositoAdapter;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.DepositoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.Deposito;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;

public class RegistrarDepositosActivity extends ListActivity implements OnClickListener {
	
	List<Deposito> lsDeposito;
	Visita visitaActiva;
	Cliente cliente;
	Cobranza cobranza;
	
	DepositoAdapter adapter;
	
	CobranzaDAO cobDao;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	DepositoDAO depDao;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_depositos);
		registerForContextMenu(this.getListView());
		
		cobDao = new CobranzaDAO(this);
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		depDao = new DepositoDAO(this);
		abrirConexion();
		
		obtenerVisitaActiva();
		
		cargarListaDepositos();
	}
	
	@Override
	protected void onResume() {
		abrirConexion();
		
		obtenerVisitaActiva();
		cargarListaDepositos();
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
		getMenuInflater().inflate(R.menu.registrar_depositos, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

	private void abrirConexion(){
		viDao.open();
		cliDao.open();
		cobDao.open();
		depDao.open();
	}
	
	private void cerrarConexion(){
		 viDao.close();
		 cliDao.close();
		 cobDao.close();
		 depDao.close();
	}

	
	private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.lblVisitaRegistroDeposito);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.lblClienteRegistrarDeposito);
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
	
	private void cargarListaDepositos(){
		
		
		if(visitaActiva!=null){
			// filtrar por estado, fecha y 
			lsDeposito = depDao.buscarPorCliente((visitaActiva.getCodigoCliente()));
			//ListView lvVisitas = (ListView)findViewById(android.R.id.list);
			adapter = new DepositoAdapter(this, lsDeposito);
			//lvVisitas.setAdapter(adapter);
			setListAdapter(adapter);
		}
		
	}
	
	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		
		AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    //obtener la visita seleccionada
	    Deposito dep = (Deposito)adapter.getItem(adapterInfo.position);
	    
	    menu.setHeaderTitle("Deposito Nro: "+Cadena.formatearNumero("0000000000", (double)dep.getCodigoDeposito()));
	    menu.add(0, v.getId(), 0, "EDITAR");  
	    menu.add(0, v.getId(), 1, "REMOVER");
	    menu.add(0, v.getId(), 2, "ANULAR");
	    
	}
	
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		if(item.getTitle().equals("EDITAR")){
			// navegar a actividad con operacion editar
			DepositoAdapter adaptadorInterno = (DepositoAdapter) getListAdapter();
			Deposito dep = (Deposito)adaptadorInterno.getItem(adapterInfo.position);
			
			Intent nuevaCobranza = new Intent(RegistrarDepositosActivity.this, NuevoDepositoActivity.class);
			nuevaCobranza.putExtra("operacion", "editar");
			nuevaCobranza.putExtra("idDeposito", dep.getId());
			startActivity(nuevaCobranza);
			
		}
		if(item.getTitle().equals("REMOVER")){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegistrarDepositosActivity.this);
			alertDialog.setTitle("REMOVER DEPOSITO");
			alertDialog.setMessage("¿Realmente Desea eliminar el Depósito?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//remover cobranza con todos sus detalles
					DepositoAdapter adaptadorInterno = (DepositoAdapter) getListAdapter();
					Deposito dep = (Deposito)adaptadorInterno.getItem(adapterInfo.position);
					
					depDao.eliminar(dep);
					cargarListaDepositos();
					adaptadorInterno.notifyDataSetChanged();
					
				}
				
			});
			
			alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			
			alertDialog.show();
			
		}
		
		return true;
	}
	
	

}

package com.atl.atlmovil;

import java.util.List;

import com.atl.atlmovil.adapters.PedidoAdapter;
import com.atl.atlmovil.adapters.VisitaAdapter;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class RegistrarPedidos extends ListActivity implements OnClickListener  {
	PedidoDAO pDao;
	List<Pedido> lsPedidos;
	PedidoAdapter adapter;
	Visita visitaActiva;
	Cliente clientePedido;
	
	VisitaDAO viDao;
	ClienteDAO cliDao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_pedidos);
		registerForContextMenu(this.getListView());
		// registrar botones
		Button btnNuevo = (Button)findViewById(R.id.btnNuevoPedido);
		btnNuevo.setOnClickListener(this);
		
		pDao = new PedidoDAO(this);
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		pDao.open();
		viDao.open();
		cliDao.open();
		obtenerVisitaActiva();
		cargarListaPedidos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrar_pedidos, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnNuevoPedido){
			Intent nuevoPedidoIntent = new Intent(RegistrarPedidos.this, NuevoPedido.class);
			nuevoPedidoIntent.putExtra("operacion", "insertar");
			startActivity(nuevoPedidoIntent);
			
		}
	}
	
	@Override
	protected void onResume() {
		pDao.open();
		viDao.open();
		cliDao.open();
		obtenerVisitaActiva();
		cargarListaPedidos();
		super.onResume();
	}

	@Override
	protected void onPause() {
		pDao.close();
		viDao.close();
		cliDao.close();
		super.onPause();
	}
	
	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		
		AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    //obtener la visita seleccionada
	    Pedido pe = (Pedido)adapter.getItem(adapterInfo.position);
	    
	    menu.setHeaderTitle("Pedido Nro: "+pe.getId());
	    menu.add(0, v.getId(), 0, "IMPRIMIR");  
	    menu.add(0, v.getId(), 1, "EDITAR PEDIDO");
	    menu.add(0, v.getId(), 2, "ANULAR PEDIDO");
	    
	}
	
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		//@SuppressWarnings("unchecked")
		//ArrayAdapter<Visita> adapter = (ArrayAdapter<Visita>) getListAdapter();
		
		//Visita visitaSeleccionada = (Visita)adapter.getItem(adapterInfo.position);
		
		if(item.getTitle().equals("EDITAR PEDIDO")){
			// 
			@SuppressWarnings("unchecked")
			PedidoAdapter adaptadorInterno = (PedidoAdapter)getListAdapter();
			Pedido pe = (Pedido)adaptadorInterno.getItem(adapterInfo.position);
			if(pe!=null){
				// navegar a nuevo pedido con operacion editar
				Intent nuevoPedidoIntent = new Intent(RegistrarPedidos.this, NuevoPedido.class);
				nuevoPedidoIntent.putExtra("operacion", "editar");
				nuevoPedidoIntent.putExtra("idPedido", pe.getId());
				
				startActivity(nuevoPedidoIntent);
				
			}
			
			
			
		} else {return false;}
		
		return true;
	}
	
	
	private void cargarListaPedidos(){
		
		lsPedidos = pDao.obtenerTodos();
		
		//ListView lvVisitas = (ListView)findViewById(android.R.id.list);
		adapter = new PedidoAdapter(this, lsPedidos);
		//lvVisitas.setAdapter(adapter);
		setListAdapter(adapter);
		
		
	}
	
	private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.txtVisitaPedido);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.txtCLientePedido);
			clientePedido = cliDao.buscarPorID(visitaActiva.getCodigoCliente());
			if(clientePedido!=null){
				PersonaDAO perDao = new PersonaDAO(this);
				perDao.open();
				Persona per = perDao.buscarPorID(clientePedido.getCodigoPersona());
				txtCLientePedido.setText("Cliente: "+per.getDocumentoPersona()+" "+ per.getNombrePersona());
				perDao.close();
			}
		}
		
	}

}

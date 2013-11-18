package com.atl.atlmovil;


import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.adapters.DetallePedidoAdapter;


import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.DetallePedidoDAO;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;
import com.atl.atlmovil.entidades.DetallePedido;
import android.os.Bundle;

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
import android.widget.Button;
import android.widget.TextView;

public class DetallePedidoActivity extends ListActivity implements OnClickListener {

	Visita visitaActiva;
	Cliente clientePedido;
	Pedido pedido;
	DetallePedidoAdapter adapter;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	PedidoDAO pDao;
	DetallePedidoDAO dpDao;
	List<DetallePedido> lsDetallePedido;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_pedido);
		//registrar menu
		registerForContextMenu(this.getListView());
		//registrar boton
		Button btnAgregarProd = (Button)findViewById(R.id.btnAgregarProductoDetalle);
		btnAgregarProd.setOnClickListener(this);
		
		
		viDao= new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		pDao = new PedidoDAO(this);
		dpDao = new DetallePedidoDAO(this);
		pDao.open();
		viDao.open();
		cliDao.open();
		dpDao.open();
		
		Intent intent = getIntent();
		long idPedido =  intent.getLongExtra("idPedido", 0);
		pedido = pDao.buscarPorID(idPedido);
		
		obtenerVisitaActiva();
		cargarListaDetallePedidos();
		
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_pedido, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnAgregarProductoDetalle){
			
			Intent agregarProductoIntent = new Intent(DetallePedidoActivity.this, AgregarProductoActivity.class);
			agregarProductoIntent.putExtra("operacion", "insertar");
			agregarProductoIntent.putExtra("idPedido", pedido.getId());
			agregarProductoIntent.putExtra("codigoProducto", (long)0);
			
			startActivity(agregarProductoIntent);
			
		}
		
		
	}
	
	@Override
	protected void onResume() {
		pDao.open();
		viDao.open();
		cliDao.open();
		dpDao.open();
		obtenerVisitaActiva();
		cargarListaDetallePedidos();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		pDao.close();
		viDao.close();
		cliDao.close();
		dpDao.close();
		super.onPause();
	}
	
	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		
		AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    //obtener la visita seleccionada
	    DetallePedido det = (DetallePedido)adapter.getItem(adapterInfo.position);
	    
	    menu.setHeaderTitle("Cod Producto: "+Cadena.formatearNumero("000000", (double)det.getCodigoProducto()));
	    menu.add(0, v.getId(), 0, "EDITAR");  
	    menu.add(0, v.getId(), 1, "REMOVER");
	    
	}
	
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		
		if(item.getTitle().equals("EDITAR")){
			// navegar a la actividad agregar producto
			//operacion editar
			DetallePedidoAdapter adaptadorInterno = (DetallePedidoAdapter)getListAdapter();
			DetallePedido det = (DetallePedido)adaptadorInterno.getItem(adapterInfo.position);
			
			Intent agregarProductoIntent = new Intent(DetallePedidoActivity.this, AgregarProductoActivity.class);
			agregarProductoIntent.putExtra("operacion", "editar");
			agregarProductoIntent.putExtra("idPedido", det.getIdPedido());
			agregarProductoIntent.putExtra("codigoProducto", det.getCodigoProducto());
			
			
			startActivity(agregarProductoIntent);
			
			
		} else if(item.getTitle().equals("REMOVER")){
			
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetallePedidoActivity.this);
			alertDialog.setTitle("REMOVER PRODUCTO");
			alertDialog.setMessage("¿Realmente Desea Remover el producto del pedido?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				
					DetallePedidoAdapter adaptadorInterno = (DetallePedidoAdapter)getListAdapter();
					DetallePedido det = (DetallePedido)adaptadorInterno.getItem(adapterInfo.position);
					//eliminar el detalle con todas sus tallas 
					dpDao.eliminar(det);
					
					cargarListaDetallePedidos();
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
	
	private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.lblNroVisitaPedidoDetalle);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.lblClientePedidoDetalle);
			clientePedido = cliDao.buscarPorID(visitaActiva.getCodigoCliente());
			if(clientePedido!=null){
				PersonaDAO perDao = new PersonaDAO(this);
				perDao.open();
				Persona per = perDao.buscarPorID(clientePedido.getCodigoPersona());
				txtCLientePedido.setText("Cliente: "+per.getDocumentoPersona()+" "+ per.getNombrePersona());
				perDao.close();
			}
		}
		
		//agregar numero de pedido
		TextView lblNumeroPedido = (TextView)findViewById(R.id.lblNumeroPedidoDetalle);
		lblNumeroPedido.setText("Nro Pedido: "+Cadena.formatearNumero("0000000000", (double)pedido.getId()));
		
		
	}
	
private void cargarListaDetallePedidos(){
		
		lsDetallePedido = dpDao.buscarPorPedido(pedido.getId());
		//ListView lvVisitas = (ListView)findViewById(android.R.id.list);
		adapter = new DetallePedidoAdapter(this, lsDetallePedido);
		//lvVisitas.setAdapter(adapter);
		setListAdapter(adapter);
		
		
	}
	

}

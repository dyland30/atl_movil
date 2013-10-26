package com.atl.atlmovil;

import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.adapters.DetallePedidoAdapter;
import com.atl.atlmovil.adapters.TallaPedidoAdapter;
import com.atl.atlmovil.dao.DetallePedidoDAO;
import com.atl.atlmovil.dao.FormaPagoDAO;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.dao.ProductoFormaPagoDAO;
import com.atl.atlmovil.dao.TallaPedidoDAO;
import com.atl.atlmovil.entidades.DetallePedido;
import com.atl.atlmovil.entidades.FormaPago;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Producto;
import com.atl.atlmovil.entidades.ProductoFormaPago;
import com.atl.atlmovil.entidades.TallaPedido;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.TextView;

public class AgregarProductoActivity extends ListActivity implements OnClickListener {

	Pedido pedido;
	DetallePedido detPed;
	TallaPedidoAdapter adapter;
	String operacion;
	List<TallaPedido> lsTallaPedido;
	
	PedidoDAO pDao;
	TallaPedidoDAO tpDao;
	DetallePedidoDAO detPDao;
	ProductoDAO prodDao;
	ProductoFormaPagoDAO pfpDao;
	long idPedido;
	long codProducto;
	final int BUSCAR_PRODUCTO_ACTIVITY = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_producto);
		
		//registrar menu		
		registerForContextMenu(this.getListView());
		
		//registrar botones
		Button btnBuscarProducto = (Button)findViewById(R.id.btnBuscarProducto);
		btnBuscarProducto.setOnClickListener(this);
		
		Button btnAgregarTalla = (Button)findViewById(R.id.btnAgregarTallaAgregarProd);
		btnAgregarTalla.setOnClickListener(this);
		
		pDao = new PedidoDAO(this);
		tpDao = new TallaPedidoDAO(this);
		detPDao = new DetallePedidoDAO(this);
		prodDao = new ProductoDAO(this);
		pfpDao = new ProductoFormaPagoDAO(this);
		abrirConexion();
		
		Intent intent = getIntent();
		idPedido =  intent.getLongExtra("idPedido", 0);
		codProducto = intent.getLongExtra("codigoProducto",0);
		
		operacion = intent.getStringExtra("operacion");
		pedido = pDao.buscarPorID(idPedido);
		detPed = detPDao.buscarPorID(idPedido, codProducto);
		
		
		cargarProducto();
		cargarDatos();
		cargarListaTallaPedidos();
		
				
	}
	
	private void abrirConexion(){
		pDao.open();
		tpDao.open();
		detPDao.open();
		prodDao.open();
		pfpDao.open();
		
	}
	private void cerrarConexion(){
		pDao.close();
		tpDao.close();
		detPDao.close();
		prodDao.close();
		pfpDao.close();
		
	}
	
	private void cargarDatos(){
		Button btnAgregar = (Button)findViewById(R.id.btnBuscarProducto);
		// llenar datos de cabecera
		if(operacion.equals("editar")){
			//deshabilitar boton 
			
			btnAgregar.setEnabled(false);
			
		} else{
			//habilitar boton
			
			btnAgregar.setEnabled(true);
			
		}
		//obtener precio unitario segun la forma de pago
		
		
		TextView lblNroPedido = (TextView)findViewById(R.id.lblNroPedidoAgregarProducto);
		
		TextView lblPunit = (TextView)findViewById(R.id.lblPunitAgregarProd);
		//TextView lblImporteTotal = (TextView)findViewById(R.id.lblImporteTotalAgregarProducto);
		
		lblNroPedido.setText("Nro Pedido: "+Cadena.formatearNumero("0000000000", (double)idPedido));
		
		if(detPed!=null){
			lblPunit.setText(Cadena.formatearNumero("############.##", (double)detPed.getPrecioUnitario()));
			
		}
		
		
	}
	private void cargarProducto(){
		TextView lblCodProducto= (TextView)findViewById(R.id.lblCodProductoAgregarProd);
		EditText txtProducto = (EditText)findViewById(R.id.txtProductoAgregar);
		lblCodProducto.setText(Cadena.formatearNumero("000000", (double)codProducto));
		// buscar Producto
		Producto prod = prodDao.buscarPorID(codProducto);
		if(prod!=null){
			txtProducto.setText(prod.getDescripcionProducto());
		}
		
		
	}
	
	private void cargarListaTallaPedidos(){

		lsTallaPedido = tpDao.buscarPorPedidoProducto(idPedido, codProducto);
		
		adapter = new TallaPedidoAdapter(this, lsTallaPedido);
		
		setListAdapter(adapter);
		
	}
	
	
	@Override
	protected void onResume() {
		abrirConexion();
		
		cargarProducto();
		cargarDatos();
		cargarListaTallaPedidos();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  abrirConexion();
	  
	  switch(requestCode) {
	    case (BUSCAR_PRODUCTO_ACTIVITY) : {
	      if (resultCode == Activity.RESULT_OK) {
	        // TODO Extract the data returned from the child Activity.
	    	 codProducto =  data.getIntExtra("codigoProducto", 0);
	    	 
	    	 // cargar Datos de Producto
	    	 cargarProducto();
	      }
	      break;
	    } 
	  }
	  
	    cerrarConexion();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_producto, menu);
		return true;
	}

	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		
		AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    //obtener la visita seleccionada
	    TallaPedido tp = (TallaPedido)adapter.getItem(adapterInfo.position);
	    
	    menu.setHeaderTitle("Nro Talla: "+tp.getNumeroTalla());
	    menu.add(0, v.getId(), 0, "EDITAR");  
	    menu.add(0, v.getId(), 1, "REMOVER");
	    
	}
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		
		if(item.getTitle().equals("EDITAR")){
			// navegar a la actividad agregar Talla
			//operacion editar
			TallaPedidoAdapter adaptadorInterno = (TallaPedidoAdapter)getListAdapter();
			TallaPedido tp = (TallaPedido)adaptadorInterno.getItem(adapterInfo.position);
			
			Intent agregarTallaIntent = new Intent(AgregarProductoActivity.this, AgregarTallaActivity.class);
			agregarTallaIntent.putExtra("operacion", "editar");
			agregarTallaIntent.putExtra("idPedido", tp.getIdPedido());
			agregarTallaIntent.putExtra("codigoProducto", tp.getCodigoProducto());
			agregarTallaIntent.putExtra("numeroTalla", tp.getNumeroTalla());
			
			
			startActivity(agregarTallaIntent);
			
			
		} else if(item.getTitle().equals("REMOVER")){
			
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(AgregarProductoActivity.this);
			alertDialog.setTitle("REMOVER TALLA");
			alertDialog.setMessage("�Realmente Desea Remover esta talla del pedido?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					@SuppressWarnings("unchecked")
					TallaPedidoAdapter adaptadorInterno = (TallaPedidoAdapter)getListAdapter();
					TallaPedido det = (TallaPedido)adaptadorInterno.getItem(adapterInfo.position);
					//eliminar el detalle con todas sus tallas 
					tpDao.eliminar(det);
					if(pedido!=null)
						pDao.actualizar(pedido);
					
					adaptadorInterno.notifyDataSetChanged();
					cargarListaTallaPedidos();
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
	
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnBuscarProducto){
			Intent buscarProductoIntent = new Intent(AgregarProductoActivity.this, BuscarProductoActivity.class);
			//agregarProductoIntent.putExtra("codigoProducto", det.getIdPedido());
			startActivityForResult(buscarProductoIntent,BUSCAR_PRODUCTO_ACTIVITY );
			
		}
		if(v.getId()==R.id.btnAgregarTallaAgregarProd){
			// navegar a actividad agregar Talla
			//verificar que exista un producto seleccionado
			if(codProducto>0){
				
				if(operacion.equals("insertar")){
					if(detPed==null){
						try{
							
							//crear detalle
							detPed = new DetallePedido();
							detPed.setCodigoProducto(codProducto);
							detPed.setIdPedido(idPedido);
							//detPed.setPrecioUnitario(precioUnitario);
							//obtener precio unitario de Forma de Pago
							if(pedido!=null){
								ProductoFormaPago pfp = pfpDao.buscarPorID(pedido.getCodigoFormaPago(), codProducto);
								if(pfp!=null){
									detPed.setPrecioUnitario(pfp.getPrecio());
								} else {
									detPed.setPrecioUnitario(0.0D);
									
								}
									
							}
							detPDao.crear(detPed.getIdPedido(), 
									detPed.getCodigoProducto(), detPed.getPrecioUnitario());
							
						}catch(Exception ex){
							Log.w("error", ex.getMessage());
							
						}
						
					}
					
				}
				
				Intent agregarTallaIntent = new Intent(AgregarProductoActivity.this, AgregarTallaActivity.class);
				
				agregarTallaIntent.putExtra("codigoProducto", codProducto);
				agregarTallaIntent.putExtra("idPedido", idPedido);
				agregarTallaIntent.putExtra("operacion", "insertar");
				
				startActivity(agregarTallaIntent);
				
			} else{
				//mostrar mensaje indicando que se debe seleccionar un producto
				
				
			}
			
			
		}
	}

}

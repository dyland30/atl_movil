package com.atl.atlmovil;

import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.adapters.DetallePedidoAdapter;
import com.atl.atlmovil.adapters.TallaPedidoAdapter;
import com.atl.atlmovil.dao.DetallePedidoDAO;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.dao.TallaPedidoDAO;
import com.atl.atlmovil.entidades.DetallePedido;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Producto;
import com.atl.atlmovil.entidades.TallaPedido;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
		
		pDao = new PedidoDAO(this);
		tpDao = new TallaPedidoDAO(this);
		detPDao = new DetallePedidoDAO(this);
		prodDao = new ProductoDAO(this);
		pDao.open();
		tpDao.open();
		detPDao.open();
		prodDao.open();
		
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
	
	private void cargarDatos(){
		// llenar datos de cabecera
		
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
		txtProducto.setText(prod.getDescripcionProducto());
		
	}
	
	private void cargarListaTallaPedidos(){

		lsTallaPedido = tpDao.buscarPorPedidoProducto(idPedido, codProducto);
		
		adapter = new TallaPedidoAdapter(this, lsTallaPedido);
		
		setListAdapter(adapter);
		
	}
	
	
	@Override
	protected void onResume() {
		pDao.open();
		tpDao.open();
		detPDao.open();
		prodDao.open();
		cargarProducto();
		cargarDatos();
		cargarListaTallaPedidos();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		pDao.close();
		tpDao.close();
		detPDao.close();
		prodDao.close();
		
		super.onPause();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  	pDao.open();
		tpDao.open();
		detPDao.open();
		prodDao.open();
	  
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_producto, menu);
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
	}

}

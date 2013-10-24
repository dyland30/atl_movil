package com.atl.atlmovil;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.dao.TallaDAO;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Producto;
import com.atl.atlmovil.entidades.Talla;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgregarTallaActivity extends Activity implements OnClickListener{
	Pedido pedido;
	Producto prod;
	
	int codTalla;
	Talla talla;
	String operacion;
	
	PedidoDAO pdao;
	ProductoDAO proDao;
	TallaDAO tdao;

	
	final int  BUSCAR_TALLA = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_talla);
		pdao = new PedidoDAO(this);
		proDao = new ProductoDAO(this);
		tdao = new TallaDAO(this);
		pdao.open();
		proDao.open();
		tdao.open();
		//registrar boton
		Button btnSeleccionarTalla = (Button)findViewById(R.id.btnSeleccionarTalla);
		btnSeleccionarTalla.setOnClickListener(this);
		
		Intent intent = getIntent();
		long idPedido =  intent.getLongExtra("idPedido", 0);
		long codProducto = intent.getLongExtra("codigoProducto",0);
		
		operacion = intent.getStringExtra("operacion");
		
		pedido = pdao.buscarPorID(idPedido);
		prod = proDao.buscarPorID(codProducto);
		
		cargarDatos();
		
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  	pdao.open();
		proDao.open();
		tdao.open();
	  switch(requestCode) {
	    case (BUSCAR_TALLA) : {
	      if (resultCode == Activity.RESULT_OK) {
	        // TODO Extract the data returned from the child Activity.
	    	  codTalla =  data.getIntExtra("numeroTalla", 0);
	    	  talla = tdao.buscarPorID(prod.getCodigoProducto(), codTalla);
	    	 // cargar Datos de Producto
	    	 cargarTalla();
	      }
	      break;
	    } 
	  }
	  
	  pdao.close();
		proDao.close();
		tdao.close();
	}
	private void cargarDatos(){
		TextView lblAgregarTallaIdPedido = (TextView)findViewById(R.id.lblAgregarTallaIdPedido);
		TextView lblAgregarTallaCodProducto = (TextView)findViewById(R.id.lblAgregarTallaCodProducto);
		if(pedido!=null && prod!=null){
			lblAgregarTallaIdPedido.setText("Nro Pedido: "+Cadena.formatearNumero("0000000000",  (double)pedido.getId()));
			lblAgregarTallaCodProducto.setText(Cadena.formatearNumero("000000", (double)prod.getCodigoProducto())+" "+prod.getDescripcionProducto());
			
			
		}
		
		
	}
	
	private void cargarTalla(){
		EditText txtAgregarTalla =(EditText)findViewById(R.id.txtAgregarTalla);
		
		TextView lblAgregarTallaExistencias =(TextView)findViewById(R.id.lblAgregarTallaExistencias);
		if(talla!=null){
			txtAgregarTalla.setText(talla.getNumeroTalla()+"");
			lblAgregarTallaExistencias.setText(talla.getStockDisponibleTalla()+"");
			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_talla, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnSeleccionarTalla){
			Intent buscarTallaIntent = new Intent(AgregarTallaActivity.this, BuscarTalla.class);
			buscarTallaIntent.putExtra("codigoProducto", prod.getCodigoProducto());
			startActivityForResult(buscarTallaIntent,BUSCAR_TALLA );
			
		} else if(v.getId()==R.id.btnCrearTalla){
			//crear registro de tallaPedido y matar a la actividad
			
		}
		
	}
	
	@Override
	protected void onResume() {
		pdao.open();
		proDao.open();
		tdao.open();
		cargarDatos();
		cargarTalla();
		super.onResume();
	}

	@Override
	protected void onPause() {
		pdao.close();
		proDao.close();
		tdao.close();
		super.onPause();
	}

}

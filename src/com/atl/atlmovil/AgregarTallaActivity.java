package com.atl.atlmovil;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.dao.TallaDAO;
import com.atl.atlmovil.dao.TallaPedidoDAO;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Producto;
import com.atl.atlmovil.entidades.Talla;
import com.atl.atlmovil.entidades.TallaPedido;

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
	TallaPedidoDAO tpDao;

	TallaPedido tallaPed;
	
	final int  BUSCAR_TALLA = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_talla);
		pdao = new PedidoDAO(this);
		proDao = new ProductoDAO(this);
		tdao = new TallaDAO(this);
		tpDao = new TallaPedidoDAO(this);
		proDao.open();
		pdao.open();
		tdao.open();
		tpDao.open();
	
		//registrar boton
		Button btnSeleccionarTalla = (Button)findViewById(R.id.btnSeleccionarTalla);
		btnSeleccionarTalla.setOnClickListener(this);
		
		Button btnAgregar = (Button)findViewById(R.id.btnCrearTalla);
		btnAgregar.setOnClickListener(this);
		
		Button btnCancelar = (Button)findViewById(R.id.btnCancelarTalla);
		btnCancelar.setOnClickListener(this);
		
		Intent intent = getIntent();
		long idPedido =  intent.getLongExtra("idPedido", 0);
		long codProducto = intent.getLongExtra("codigoProducto",0);
		long numeroTalla = intent.getIntExtra("numeroTalla", 0);
		operacion = intent.getStringExtra("operacion");
		
		pedido = pdao.buscarPorID(idPedido);
		prod = proDao.buscarPorID(codProducto);
		
		if(operacion.equals("editar")){
			tallaPed = tpDao.buscarPorID(idPedido, codProducto, numeroTalla);
			talla = tdao.buscarPorID(tallaPed.getCodigoProducto(), tallaPed.getNumeroTalla());
			cargarTalla();
		}
			
		
		cargarDatos();
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  	pdao.open();
		proDao.open();
		tdao.open();
		tpDao.open();
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
		tpDao.close();
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

	private void guardarTalla(){
		try{
			
			if(operacion.equals("insertar")){
				if(prod!=null && pedido!=null && talla !=null){
					tallaPed = new TallaPedido();
					tallaPed.setCodigoProducto((long)prod.getCodigoProducto());
					tallaPed.setIdPedido(pedido.getId());
					tallaPed.setNumeroTalla(talla.getNumeroTalla());
					EditText txtCantidad = (EditText)findViewById(R.id.txtCantidadTalla);
					
					String strcant = txtCantidad.getText().toString();
					
					int cant=0;
					if(strcant!=null && strcant.length()>0){
						cant = Integer.parseInt(strcant);
					}
					if(cant>0){
						tallaPed.setCantidad(cant);
						tpDao.crear(tallaPed);
						pdao.actualizar(pedido);
						finish();
						
					} else{
						mostrarMensaje("ADVERTENCIA", "La cantidad debe ser mayor a cero");
					}
					
					
				}else{
					mostrarMensaje("ADVERTENCIA", "Debe seleccionar una talla");
				}
				
				
			} else if(operacion.equals("editar")){
				if(tallaPed!=null && pedido!=null){
					EditText txtCantidad = (EditText)findViewById(R.id.txtCantidadTalla);
					
					String strcant = txtCantidad.getText().toString();
					int cant=0;
					if(strcant!=null && strcant.length()>0){
						cant = Integer.parseInt(strcant);
						
					}
					if(cant>0){
						tallaPed.setCantidad(cant);
						tpDao.actualizar(tallaPed);
						pdao.actualizar(pedido);
						finish();
					} else{
						mostrarMensaje("ADVERTENCIA", "La cantidad debe ser mayor a cero");
					}
					
				}
				else{
					mostrarMensaje("ADVERTENCIA", "Debe seleccionar una talla");
				}
			}
		} catch(Exception ex){
			// mostrar alerta
			mostrarMensaje("Error", "No se pudo agregar la talla seleccionada:  " +ex.getMessage() );
			
		} 
		
		
	}
	
	
public void mostrarMensaje( String titulo, String mensaje){
		
		AlertDialog.Builder errorDialog = new AlertDialog.Builder(AgregarTallaActivity.this);
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
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnSeleccionarTalla){
			Intent buscarTallaIntent = new Intent(AgregarTallaActivity.this, BuscarTalla.class);
			buscarTallaIntent.putExtra("codigoProducto", prod.getCodigoProducto());
			startActivityForResult(buscarTallaIntent,BUSCAR_TALLA );
			
		} 
		if(v.getId()==R.id.btnCrearTalla){
			//crear registro de tallaPedido y matar a la actividad
			
			guardarTalla();
			
		}
		if(v.getId()==R.id.btnCancelarTalla){
			finish();
		}
		
	}
	
	@Override
	protected void onResume() {
		pdao.open();
		proDao.open();
		tdao.open();
		tpDao.open();
		cargarDatos();
		cargarTalla();
		super.onResume();
	}

	@Override
	protected void onPause() {
		pdao.close();
		proDao.close();
		tdao.close();
		tpDao.close();
		super.onPause();
	}

}

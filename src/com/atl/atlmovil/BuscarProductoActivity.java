package com.atl.atlmovil;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.atl.atlmovil.adapters.ProductoAdapter;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.entidades.*;
public class BuscarProductoActivity extends ListActivity implements OnClickListener {

	List<Producto> lsProducto;
	ProductoDAO pdao;
	
	ProductoAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_producto);
		pdao = new ProductoDAO(this);
		pdao.open();
		cargarListaProductos();
		
		//Registrar evento en textview
		EditText txtNombre = (EditText)findViewById(R.id.txtBuscarProducto);
		txtNombre.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				cargarListaProductos();
				
				
			}
		});
		
		ListView lv = (ListView)findViewById(android.R.id.list);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Producto productoSeleccionado = (Producto)adapter.getItemAtPosition(position);
				//finalizar actividad y enviar codigo de producto
				Intent resultIntent = new Intent();
				resultIntent.putExtra("codigoProducto", productoSeleccionado.getCodigoProducto());
				setResult(Activity.RESULT_OK, resultIntent);
				pdao.close();
				finish();
				
			}
			
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscar_producto, menu);
		return true;
	}
	@Override
	protected void onResume() {
		pdao.open();
		cargarListaProductos();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		pdao.close();
		
		super.onPause();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	private void cargarListaProductos(){

		EditText txtNombre = (EditText)findViewById(R.id.txtBuscarProducto);
		
		lsProducto = pdao.buscarPorNombre(txtNombre.getText().toString());
		
		adapter = new ProductoAdapter(this, lsProducto);
		
		setListAdapter(adapter);
		
	}
	

}

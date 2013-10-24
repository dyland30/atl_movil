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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.atl.atlmovil.adapters.TallaAdapter;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.dao.TallaDAO;
import com.atl.atlmovil.entidades.Producto;
import com.atl.atlmovil.entidades.Talla;


public class BuscarTalla extends ListActivity {
	List<Talla> lsTalla;
	TallaDAO tDao;
	TallaAdapter adapter;
	ProductoDAO pdao;
	Producto prod;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_talla);
		tDao = new TallaDAO(this);
		pdao = new ProductoDAO(this);
		tDao.open();
		pdao.open();
		
		
		//obtener codigo de producto
		Intent intent = getIntent();
		
		int codProducto = intent.getIntExtra("codigoProducto",0);
		prod = pdao.buscarPorID(codProducto);
		TextView txtProd = (TextView)findViewById(R.id.lblCodigoProductoBuscarTalla);
		
		if(prod!=null){
			txtProd.setText("Producto: " + prod.getCodigoProducto()+" "+prod.getDescripcionProducto());
			cargarListaTallas();
		} 
		
		
		//registrar eventos
		EditText txtNumero = (EditText)findViewById(R.id.lblBuscarTalla);
		txtNumero.addTextChangedListener(new TextWatcher() {
			
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
				cargarListaTallas();
				
				
			}
		});
		
		ListView lv = (ListView)findViewById(android.R.id.list);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Talla tallaSeleccionada = (Talla)adapter.getItemAtPosition(position);
				//finalizar actividad y enviar codigo de producto
				Intent resultIntent = new Intent();
				resultIntent.putExtra("numeroTalla", tallaSeleccionada.getNumeroTalla());
				setResult(Activity.RESULT_OK, resultIntent);
				
				finish();
				
			}
			
			
		});
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscar_talla, menu);
		return true;
	}
	
	
	@Override
	protected void onResume() {
		tDao.open();
		pdao.open();
		cargarListaTallas();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		tDao.close();
		pdao.close();
		super.onPause();
	}
	
	private void cargarListaTallas(){
		if(prod!=null){
			EditText txtNumero = (EditText)findViewById(R.id.lblBuscarTalla);
			
			lsTalla = tDao.buscarPorNumero(txtNumero.getText().toString(),  prod.getCodigoProducto());
			
			adapter = new TallaAdapter(this, lsTalla);
			
			setListAdapter(adapter);
			
		}
	
		
	}

}

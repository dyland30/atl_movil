package com.atl.atlmovil;

import java.util.List;

import com.atl.atlmovil.adapters.DocumentoPagoAdapter;
import com.atl.atlmovil.adapters.ProductoAdapter;
import com.atl.atlmovil.dao.DocumentoPagoDAO;
import com.atl.atlmovil.entidades.DocumentoPago;
import com.atl.atlmovil.entidades.Producto;

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
import android.widget.AdapterView.OnItemClickListener;

public class BuscarDocumentoPagoActivity extends ListActivity {
	List<DocumentoPago> lsDocumento;
	DocumentoPagoDAO docDao;
	DocumentoPagoAdapter adapter;
	long codigoCliente;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_documento_pago);
		docDao = new DocumentoPagoDAO(this);
		docDao.open();
		Intent intent = getIntent();
		codigoCliente =  intent.getLongExtra("codigoCliente", 0);
		
		cargarListaDocumentos();
		//registrar evento en textview
		EditText txtNombre = (EditText)findViewById(R.id.txtFiltroBuscarDocumento);
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
				cargarListaDocumentos();
				
				
			}
		});
		
		
		//revistrar evento en list view
		
		ListView lv = (ListView)findViewById(android.R.id.list);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				DocumentoPago documentoSeleccionado = (DocumentoPago)adapter.getItemAtPosition(position);
				//finalizar actividad y enviar codigo de producto
				Intent resultIntent = new Intent();
				resultIntent.putExtra("codigoDocumentoPago", documentoSeleccionado.getCodigoDocumentoPago());
				setResult(Activity.RESULT_OK, resultIntent);
				finish();	
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscar_documento_pago, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		docDao.open();
		cargarListaDocumentos();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		docDao.close();
		
		super.onPause();
	}
	
	private void cargarListaDocumentos(){

		EditText txtFiltro = (EditText)findViewById(R.id.txtFiltroBuscarDocumento);
		// se debe filtrar los documentos pendientes por cliente de la visita activa
		lsDocumento = docDao.buscarPorCliente(codigoCliente, txtFiltro.getText().toString());
		adapter = new DocumentoPagoAdapter(this, lsDocumento);
		setListAdapter(adapter);
		
	}
	
	

}

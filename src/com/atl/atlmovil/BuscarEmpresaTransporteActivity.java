package com.atl.atlmovil;

import java.util.List;

import com.atl.atlmovil.adapters.EmpresaCargaAdapter;
import com.atl.atlmovil.dao.EmpresaCargaDAO;
import com.atl.atlmovil.entidades.EmpresaCarga;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BuscarEmpresaTransporteActivity extends ListActivity implements OnClickListener{
	List<EmpresaCarga> lsEmpresaCarga;
	EmpresaCargaDAO edao;
	
	EmpresaCargaAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_empresa_transporte);
		edao = new EmpresaCargaDAO(this);
		edao.open();
		cargarListaEmpresaCarga();
		
		// registrar evento en textview
		
		EditText txtNombre = (EditText)findViewById(R.id.txtBuscarEmpresaTransporte);
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
				cargarListaEmpresaCarga();
				
				
			}
		});
		
		ListView lv = (ListView)findViewById(android.R.id.list);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				EmpresaCarga empresaSeleccionada = (EmpresaCarga)adapter.getItemAtPosition(position);
				//finalizar actividad y enviar codigo de producto
				Intent resultIntent = new Intent();
				resultIntent.putExtra("codigoEmpresaCarga", empresaSeleccionada.getCodigoEmpresaCarga());
				setResult(Activity.RESULT_OK, resultIntent);
				
				finish();
				
			}
			
			
		});
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscar_empresa_transporte, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		edao.open();
		cargarListaEmpresaCarga();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		edao.close();
		
		super.onPause();
	}

	private void cargarListaEmpresaCarga(){

		EditText txtNombre = (EditText)findViewById(R.id.txtBuscarEmpresaTransporte);
		
		lsEmpresaCarga = edao.buscarPorNombre(txtNombre.getText().toString());
		
		adapter = new EmpresaCargaAdapter(this, lsEmpresaCarga);
		
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}

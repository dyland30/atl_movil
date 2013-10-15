package com.atl.atlmovil;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.dao.EstadoVisitaDAO;
import com.atl.atlmovil.dao.TipoVisitaDAO;
import com.atl.atlmovil.entidades.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ActivarVisitaActivity extends Activity {
	TipoVisitaDAO tvDao;
	EstadoVisitaDAO evDao;
	List<TipoVisita> lstv;
	List<EstadoVisita> lsev;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activar_visita);
		tvDao.open();
		evDao.open();
		// cargar combos
		lstv = new ArrayList<TipoVisita>();
		lsev = new ArrayList<EstadoVisita>();
		
		lstv = tvDao.obtenerTodos();
		lsev= evDao.obtenerTodos();
		
		Spinner cmbTipoVisita = (Spinner)findViewById(R.id.cmbTipoVisita);
		Spinner cmbEstadoVisita = (Spinner)findViewById(R.id.cmbEstadoVisita);
		
		ArrayAdapter<TipoVisita> tipoVisitaAdapter = new ArrayAdapter<TipoVisita>(this,
                android.R.layout.simple_spinner_item, lstv);
		
		ArrayAdapter<EstadoVisita> estadoVisitaAdapter = new ArrayAdapter<EstadoVisita>(this,
                android.R.layout.simple_spinner_item, lsev);
		cmbTipoVisita.setAdapter(tipoVisitaAdapter);
		cmbEstadoVisita.setAdapter(estadoVisitaAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activar_visita, menu);
		return true;
	}
	
	
	@Override
	protected void onResume() {
		tvDao.open();
		evDao.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		tvDao.close();
		evDao.close();
		super.onPause();
	}

}

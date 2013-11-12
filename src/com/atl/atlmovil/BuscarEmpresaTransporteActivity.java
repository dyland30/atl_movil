package com.atl.atlmovil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BuscarEmpresaTransporteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_empresa_transporte);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscar_empresa_transporte, menu);
		return true;
	}

}

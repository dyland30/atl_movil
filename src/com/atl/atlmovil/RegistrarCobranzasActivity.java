package com.atl.atlmovil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RegistrarCobranzasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_cobranzas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrar_cobranzas, menu);
		return true;
	}

}

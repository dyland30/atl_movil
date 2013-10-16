package com.owlsoft.encnotes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActRegistrarUsuario extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_registrar_usuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_registrar_usuario, menu);
		return true;
	}

}

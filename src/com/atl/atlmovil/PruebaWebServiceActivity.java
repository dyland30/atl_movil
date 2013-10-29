package com.atl.atlmovil;

import com.atl.atlmovi.util.RequestManager;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class PruebaWebServiceActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prueba_web_service);
		// registrar boton
		Button btnObtener = (Button)findViewById(R.id.btnWebServiceGet);
		btnObtener.setOnClickListener( this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prueba_web, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnWebServiceGet){
			TextView txtUrl = (TextView)findViewById(R.id.txtWebServiceURL);
			TextView txtResp = (TextView)findViewById(R.id.txtWebServiceRespuesta);
			String url = txtUrl.getText().toString();
			Log.w("info",url);
			RequestManager rq = new RequestManager(txtResp);
			rq.execute(url);
		}
		
	}

	
}

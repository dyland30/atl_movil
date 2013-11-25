package com.atl.atlmovil;

import com.atl.atlmovil.customviews.ReciboCobranzaView;
import com.atl.atlmovil.dao.AmortizacionDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.entidades.Cobranza;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ReporteCobranzaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reporte_cobranza);
		
		Intent intent = getIntent();
		long idCobranza = intent.getLongExtra("idCobranza", 0);
		
		CobranzaDAO cobDao = new CobranzaDAO(this);
		AmortizacionDAO amDao = new AmortizacionDAO(this);
		
		try{
			cobDao.open();
			amDao.open();
			
			Cobranza cobranza = cobDao.buscarPorID(idCobranza);
			if(cobranza!=null)
			cobranza.setAmortizaciones(amDao.buscarPorCobranza(cobranza.getId()));
			
			ReciboCobranzaView reciboCobranzaView = (ReciboCobranzaView)findViewById(R.id.reciboCobranzaView);
			reciboCobranzaView.setCobranza(cobranza);
			
			
		} finally{
			cobDao.close();
			amDao.close();
			
			
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reporte_cobranza, menu);
		return true;
	}

}

package com.atl.atlmovil;

import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Usuario;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipalActivity extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        
        TextView lblUsuario = (TextView)findViewById(R.id.lblUsuario);
        AtlApp app = (AtlApp)getApplicationContext();
        Usuario usr = app.getUsuario();
        if(usr!=null){
        	lblUsuario.setText("Usuario: "+usr.getLogin() +" "+ usr.getNombres()+" "+ usr.getApellidos());
        	
        }
        // registrar eventos
        Button btnActivarVisita = (Button)findViewById(R.id.btnActivarVisitas);
        btnActivarVisita.setOnClickListener(this);
        Button btnRegistrarPedido = (Button)findViewById(R.id.btnRegistrarPedido);
        btnRegistrarPedido.setOnClickListener(this);
        
        Button btnTestWB = (Button)findViewById(R.id.btnTestWebService);
        btnTestWB.setOnClickListener(this);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_principal, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnActivarVisitas){
			Intent activarVisitasIntent = new Intent(MenuPrincipalActivity.this, ActivarVisitaActivity.class);
			
			startActivity(activarVisitasIntent);
			
		}
		
		if(v.getId()==R.id.btnRegistrarPedido){
			// validar que exista una visita activa
			boolean visitaActiva = false;
			VisitaDAO viDao = new VisitaDAO(this);
			viDao.open();
			visitaActiva = viDao.existeVisitaActiva();
			viDao.close();
			if(visitaActiva){
				Intent registrarPedidoIntent = new Intent(MenuPrincipalActivity.this, RegistrarPedidos.class);
				startActivity(registrarPedidoIntent);
				
			} else{
				
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuPrincipalActivity.this);
				alertDialog.setTitle("NO EXISTE VISITA ACTIVA");
				alertDialog.setMessage("Para registrar un pedido debe tener una visita activa");
				alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
				alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				alertDialog.show();
				
			}
			
			
		}
		if(v.getId()==R.id.btnTestWebService){
			Intent testIntent = new Intent(MenuPrincipalActivity.this, PruebaWebServiceActivity.class);
			startActivity(testIntent);
			
		}
		
	}
}

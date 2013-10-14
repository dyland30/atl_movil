package com.atl.atlmovil;

import com.atl.atlmovil.entidades.Usuario;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_principal, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}

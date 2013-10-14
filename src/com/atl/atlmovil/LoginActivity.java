package com.atl.atlmovil;

import com.atl.atlmovil.dao.UsuarioDAO;
import com.atl.atlmovil.entidades.Usuario;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity  implements OnClickListener{

	private UsuarioDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dao = new UsuarioDAO(this);
        // registrar evento boton ingresar
        Button btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
        dao.open();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText txtLogin = (EditText)findViewById(R.id.txtLogin);
		EditText txtClave = (EditText)findViewById(R.id.txtClave);
		TextView lblMensaje = (TextView)findViewById(R.id.lblMensaje);
		
		if(v.getId()==R.id.btnIngresar){
			
			String login = txtLogin.getText().toString();
			String clave = txtClave.getText().toString();
			// validar usuario
			Usuario usr = dao.buscarPorLogin(login);
			if(usr!=null && clave.equals(usr.getClave())){
				// guardar usuario en el contexto de Aplicacion
				AtlApp app = (AtlApp)getApplicationContext();
				app.setUsuario(usr);
				// abrir menu principal
				lblMensaje.setText("usuario correcto");
				
				Intent menuPrincipalIntent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
				//nuevaNotaIntent.putExtra("operacion", "actualizar");
				//nuevaNotaIntent.putExtra("idNota", notaSeleccionada.getId());
				startActivity(menuPrincipalIntent);
				
				
			} else{
				
				lblMensaje.setText(R.string.loginFail);
				
			}
			
		}
	}
	@Override
	protected void onResume() {
		dao.open();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		dao.close();
		super.onPause();
	}
    
}

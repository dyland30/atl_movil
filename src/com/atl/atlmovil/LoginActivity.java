package com.atl.atlmovil;

import com.atl.atlmovi.util.ServicioSync;
import com.atl.atlmovil.dao.EmpleadoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.UsuarioDAO;
import com.atl.atlmovil.entidades.Empleado;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Usuario;








import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity  implements OnClickListener{

	private UsuarioDAO dao;
	EmpleadoDAO empDao;
	PersonaDAO perDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dao = new UsuarioDAO(this);
        empDao= new EmpleadoDAO(this);
        perDao = new PersonaDAO(this);
        // registrar evento boton ingresar
        Button btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
        dao.open();
        empDao.open();
        perDao.open();
        // iniciamos servicio de sincronizacion
        Intent i= new Intent(this, ServicioSync.class);
        this.startService(i); 
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
				if(usr.getCodigoEmpleado()>0){
					// buscar empleado
					
						Empleado emp = empDao.buscarPorID(usr.getCodigoEmpleado());
						
						if(emp!=null){
							// buscar persona
							// establecer persona a empleado
							Persona per = perDao.buscarPorID(emp.getCodigoPersona());
							if(per!=null){
								
								emp.setPersona(per);
								
								usr.setEmpleado(emp);
								
								AtlApp app = (AtlApp)getApplicationContext();
								app.setUsuario(usr);
								// guardar el usuario en preferencias para que el sistema no se caiga
								
								SharedPreferences prefs = this.getSharedPreferences(
									      "com.atl.atlmovil", Context.MODE_PRIVATE);
								Gson gson = new Gson();
								String jsonUser = gson.toJson(usr);
								 
								prefs.edit().putString("usuario", jsonUser).commit();
								
								
								
								
								
								
								
								// abrir menu principal
								
								
								Intent menuPrincipalIntent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
								//nuevaNotaIntent.putExtra("operacion", "actualizar");
								//nuevaNotaIntent.putExtra("idNota", notaSeleccionada.getId());
								startActivity(menuPrincipalIntent);
								
								
							} else{
								lblMensaje.setText("El empleado no tiene asignada una persona");
								
							}
							
							
							
							
						} else{
							
							lblMensaje.setText("El usuario no es un vendedor");
							
						}
						
				}
				
			} else{
				
				lblMensaje.setText(R.string.loginFail);
				
			}
			
		}
	}
	@Override
	protected void onResume() {
		dao.open();
		empDao.open();
		perDao.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		dao.close();
		empDao.close();
		perDao.close();
		super.onPause();
	}
    
}

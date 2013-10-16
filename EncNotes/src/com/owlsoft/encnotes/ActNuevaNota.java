package com.owlsoft.encnotes;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActNuevaNota extends Activity implements OnClickListener {
	private NotaDAO datasource;
	private String operacion;
	private long idNota;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_nueva_nota);
		datasource = new NotaDAO(this);
		datasource.open();

		Intent intent = getIntent();
		operacion = intent.getStringExtra("operacion");
		idNota = 0;
		if (operacion.equals("actualizar")) {
			idNota = intent.getLongExtra("idNota", 0);
			cargarDatos();

		}

		//registrar todos los botones para que respondan a los eventos
		
		Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
		btnGuardar.setOnClickListener(this);
		
		Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
		btnCancelar.setOnClickListener(this);
		
		Button btnEliminar = (Button) findViewById(R.id.btn_ann_eliminar);
		btnEliminar.setOnClickListener(this);
		
		
		

	}

	public void cargarDatos() {
		Nota nota = datasource.obtenerNotaId(idNota);
		if (nota != null) {
			EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
			EditText txtTexto = (EditText) findViewById(R.id.txtTexto);
			txtTitulo.setText(nota.getTitulo());
			txtTexto.setText(nota.getTexto());

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_act_nueva_nota, menu);
		return true;
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
		//Log.w("inf","btnGuardar= "+R.id.btnGuardar);	
		//Log.w("inf","btnCancelar= "+R.id.btnCancelar);	
		//Log.w("inf","btn_ann_eliminarv.id= "+R.id.btn_ann_eliminar);	
		
		//Log.w("inf","v.id= "+v.getId());	
		
			if (v.getId() == R.id.btnGuardar) {

				guardarNota();

			} 
			if(v.getId()==R.id.btnCancelar){
				//Log.w("inf","BOTON CANCELAR");
				this.finish();
				
				
			} 
			if(v.getId()==R.id.btn_ann_eliminar){
				//Log.w("inf","BOTON ELIMINAR");
				//Log.w("inf","nota id "+idNota);
				if(idNota>0){
					//Mostrar alerta
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActNuevaNota.this);
					alertDialog.setTitle(getResources().getString(R.string.strTituloEliminar));
					alertDialog.setMessage(getResources().getString(R.string.strMensajeEliminar));
					alertDialog.setIcon(android.R.drawable.ic_delete);
					
					alertDialog.setPositiveButton(R.string.strSi, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							eliminarNota();
							
						}
					});
					
					alertDialog.setNegativeButton(R.string.strNO, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					
					
					alertDialog.show();
					
				}
				
			}

		} catch (Exception ex) {
			mostrarMensaje(ex.getMessage());
			//Log.e("Error", ex.getMessage());

		}

	}
	
	public void eliminarNota(){
		Nota nota = datasource.obtenerNotaId(idNota);
		Log.w("inf","nota: "+nota.getId()+" descripcion "+nota.getTitulo());
		if(nota!=null){
			datasource.eliminar(nota);
			ActNuevaNota.this.finish();
		}
		
		
	}
	
	public void guardarNota(){
		// guardar nota en base de datos y llamar a la vista principal
		EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
		EditText txtTexto = (EditText) findViewById(R.id.txtTexto);
		String titulo = txtTitulo.getText().toString();
		String texto = txtTexto.getText().toString();
		if (operacion.equals("actualizar")) {
			Nota n = new Nota();
			n.setId(idNota);
			n.setTitulo(titulo);
			n.setTexto(texto);
			n.setLlave("");
			n.setFlgEncriptado("N");
			n.setCodUsuario(1);
			Nota nota = datasource.actualizar(n);
			
			Log.w("inf",
					"se ha actualizado la nota con id " + nota.getId()
							+ " !");
		} else {
			
			Nota nota = new Nota();
			nota.setTitulo(titulo);
			nota.setTexto(texto);
			nota.setLlave("");
			nota.setFlgEncriptado("N");
			nota.setCodUsuario(1);
			nota= datasource.crear(nota);
			//mostrarMensaje("se creo la nota con id "+nota.getId());
			Log.w("inf", "se ha creado la nota con id " + nota.getId()
					+ " !");
		}
		// regresar a la actividad principal
		ActNuevaNota.this.finish();
		
		
	}
	
	
	public void mostrarMensaje(String mensaje){
		
		AlertDialog errorDialog = new AlertDialog.Builder(ActNuevaNota.this).create();
		errorDialog.setTitle(getResources().getString(R.string.strTituloEliminar));
		errorDialog.setMessage(mensaje);
		errorDialog.setIcon(android.R.drawable.ic_dialog_alert);
		errorDialog.setButton(getResources().getString(R.string.strAceptar), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		errorDialog.show();
	}
	
}

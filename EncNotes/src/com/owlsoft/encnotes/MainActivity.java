package com.owlsoft.encnotes;

import java.util.List;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity implements OnClickListener{

	private NotaDAO datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// registrar evento nueva nota
		Button btnNuevo = (Button) findViewById(R.id.btnNuevo);
		btnNuevo.setOnClickListener(this);
		registerForContextMenu(this.getListView());

		datasource = new NotaDAO(this);
		datasource.open();

		cargarLista();
		
		// registrar evento click en la lista
		ListView lvNotas =(ListView)findViewById(android.R.id.list);
		lvNotas.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				
				Nota notaSeleccionada = (Nota)adapter.getItemAtPosition(position);
				//llamar a la actividad para mostrar la nota seleccionada
				Intent nuevaNotaIntent = new Intent(MainActivity.this, ActNuevaNota.class);
				nuevaNotaIntent.putExtra("operacion", "actualizar");
				nuevaNotaIntent.putExtra("idNota", notaSeleccionada.getId());
				startActivity(nuevaNotaIntent);
				
				
			}
		});
		/*
		 * EN ESTE CASO EL EVENTO SE DISPARA AUTOMATICAMENTE
		lvNotas.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View vista,
					int position, long id) {
				// TODO Auto-generated method stub
				//registrar menu contextual
					registerForContextMenu(vista);
		            openContextMenu(vista);
				
				return false;
			}
			
		});
		*/
		EditText txtBuscar = (EditText)findViewById(R.id.txtBuscarNota);
		txtBuscar.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				ListView lv =(ListView)findViewById(android.R.id.list);
				
				@SuppressWarnings("unchecked")
				ArrayAdapter<Nota> adapter = (ArrayAdapter<Nota>)lv.getAdapter();
				adapter.getFilter().filter(s);
				
				//MainActivity.this.ada
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		

	}
	private void cargarLista(){
		List<Nota> lsNotas = datasource.obtenerTodas();
		ArrayAdapter<Nota> adapter = new ArrayAdapter<Nota>(this,
				R.layout.list_item,R.id.txtTituloNota, lsNotas);
		
		// vincular el adaptador de lista con la actividad
		setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		//@SuppressWarnings("unchecked")
		//ArrayAdapter<Nota> adapter = (ArrayAdapter<Nota>) getListAdapter();

		if (v.getId() == R.id.btnNuevo) {
			Intent nuevaNotaIntent = new Intent(this, ActNuevaNota.class);
			nuevaNotaIntent.putExtra("operacion", "insertar");
			startActivity(nuevaNotaIntent);

		}
		/*
		if (v.getId() == R.id.btnEliminar) {
			Log.d("d", "Se eliminara la nota seleccionada");
			if (getListAdapter().getCount() > 0) {
				Nota nota = (Nota) getListAdapter().getItem(0);
				datasource.eliminar(nota);
				adapter.remove(nota);

				adapter.notifyDataSetChanged();

			}

		}
		*/

	}
	
	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
	super.onCreateContextMenu(menu, v, menuInfo);  
	   
	    
	    AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    Nota notaSeleccionada = (Nota)adapter.getItem(adapterInfo.position);
	    	    
	    menu.setHeaderTitle(notaSeleccionada.getTitulo());
	    menu.add(0, v.getId(), 0, getResources().getString(R.string.btnEliminar));  
	    menu.add(0, v.getId(), 1, getResources().getString(R.string.btnEditar));  
	}  
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		//Adapter adapter = getListAdapter();
		@SuppressWarnings("unchecked")
		ArrayAdapter<Nota> adapter = (ArrayAdapter<Nota>) getListAdapter();
		Nota notaSeleccionada = (Nota)adapter.getItem(adapterInfo.position);
		
	        if(item.getTitle().equals(getResources().getString(R.string.btnEliminar))){
	        // Eliminar	
	        	AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				alertDialog.setTitle(getResources().getString(R.string.strTituloEliminar));
				alertDialog.setMessage(getResources().getString(R.string.strMensajeEliminar));
				alertDialog.setIcon(android.R.drawable.ic_delete);
				
				alertDialog.setPositiveButton(R.string.strSi, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						@SuppressWarnings("unchecked")
						ArrayAdapter<Nota> adaptadorInterno = (ArrayAdapter<Nota>) getListAdapter();
						Nota nota = (Nota)adaptadorInterno.getItem(adapterInfo.position);
						datasource.eliminar(nota);
						adaptadorInterno.remove(nota);
						adaptadorInterno.notifyDataSetChanged();
						
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
	    else if(item.getTitle().equals(getResources().getString(R.string.btnEditar))){
	    	
	    	//navegar a la otra actividad
	    	Intent nuevaNotaIntent = new Intent(MainActivity.this, ActNuevaNota.class);
			nuevaNotaIntent.putExtra("operacion", "actualizar");
			nuevaNotaIntent.putExtra("idNota", notaSeleccionada.getId());
			startActivity(nuevaNotaIntent);
	    	
	    }  
	    else {return false;}  
	return true;  
	}
			

	@Override
	protected void onResume() {
		datasource.open();
		cargarLista();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}

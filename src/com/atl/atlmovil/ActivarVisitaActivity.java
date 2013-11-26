package com.atl.atlmovil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atl.atlmovil.adapters.VisitaAdapter;
import com.atl.atlmovil.dao.EstadoVisitaDAO;
import com.atl.atlmovil.dao.TipoVisitaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.*;
import com.google.gson.Gson;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ActivarVisitaActivity extends ListActivity implements OnClickListener {
	TipoVisitaDAO tvDao;
	EstadoVisitaDAO evDao;
	VisitaDAO viDao;
	List<TipoVisita> lstv;
	List<EstadoVisita> lsev;
	List<Visita> lsVisitas;
	VisitaAdapter adapter; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activar_visita);
		
		//registrar la lista con el menu contextual
		registerForContextMenu(this.getListView());
		// registrar boton filtrar
		Button btnFiltrar = (Button)findViewById(R.id.btnFiltrar);
		btnFiltrar.setOnClickListener(this);
		
		tvDao = new TipoVisitaDAO(this);
		evDao = new EstadoVisitaDAO(this);
		viDao = new VisitaDAO(this);
		tvDao.open();
		evDao.open();
		viDao.open();
		// cargar combos
		cargarCmbEstadoVisita();
		cargarCmbTipoVisita();
		
		// obtener la lista de visitas
		// de acuerdo a los filtros seleccionados
		
		cargarListaVisitas();
		
	}
	
	private void cargarListaVisitas(){
		//obtener tipoVisita Seleccionada
		Spinner cmbTipoVisita = (Spinner)findViewById(R.id.cmbTipoVisita);
		Spinner cmbEstadoVisita = (Spinner)findViewById(R.id.cmbEstadoVisita);
		
		//obtener estadoVisita Seleccionada
		TipoVisita tipVi = (TipoVisita)cmbTipoVisita.getSelectedItem();
		EstadoVisita estVi = (EstadoVisita)cmbEstadoVisita.getSelectedItem();
		//obtener usuario logueado
		SharedPreferences prefs = this.getSharedPreferences(
			      "com.atl.atlmovil", Context.MODE_PRIVATE);
		Gson gson = new Gson();
		
		String jsonUsr = prefs.getString("usuario", "");
		Usuario usr = gson.fromJson(jsonUsr, Usuario.class);
		if(usr==null){
			finish();
			
		}
		
		long codTipVi = 0;
		long codEstVi = 0;
		if(tipVi!=null){
			codTipVi = tipVi.getCodigoTipoVisita();
		}
		if(estVi!=null){
			codEstVi = estVi.getCodigoEstadoVisita();
		}
		Log.w("info","tipo visita "+codTipVi);
		Log.w("info","estado visita "+codEstVi);
		
		lsVisitas = viDao.obtenerVisitasEstadoTipo(codTipVi, codEstVi, usr.getCodigoEmpleado());
		
		Log.w("info","cant datos "+lsVisitas.size());
		
		//ListView lvVisitas = (ListView)findViewById(android.R.id.list);
		adapter = new VisitaAdapter(this, lsVisitas);
		//lvVisitas.setAdapter(adapter);
		setListAdapter(adapter);
		
		
	}
	private void cargarCmbTipoVisita(){
		lstv = new ArrayList<TipoVisita>();
		lstv = tvDao.obtenerTodos();
		Spinner cmbTipoVisita = (Spinner)findViewById(R.id.cmbTipoVisita);
		ArrayAdapter<TipoVisita> tipoVisitaAdapter = new ArrayAdapter<TipoVisita>(this,
                android.R.layout.simple_spinner_item, lstv);
		cmbTipoVisita.setAdapter(tipoVisitaAdapter);
		
	}
	private void cargarCmbEstadoVisita(){
		lsev = new ArrayList<EstadoVisita>();
		lsev= evDao.obtenerTodos();
		Spinner cmbEstadoVisita = (Spinner)findViewById(R.id.cmbEstadoVisita);
		ArrayAdapter<EstadoVisita> estadoVisitaAdapter = new ArrayAdapter<EstadoVisita>(this,
                android.R.layout.simple_spinner_item, lsev);
		cmbEstadoVisita.setAdapter(estadoVisitaAdapter);
		
	}

	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		
		AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    //obtener la visita seleccionada
	    Visita vi = (Visita)adapter.getItem(adapterInfo.position);
	    
	    menu.setHeaderTitle("Visita Nro: "+vi.getCodigoVisita());
	    menu.add(0, v.getId(), 0, "INICIAR");  
	    menu.add(0, v.getId(), 1, "TERMINAR");
	    menu.add(0, v.getId(), 2, "DESPROGRAMAR");
	    
	}
	
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		//@SuppressWarnings("unchecked")
		//ArrayAdapter<Visita> adapter = (ArrayAdapter<Visita>) getListAdapter();
		
		//Visita visitaSeleccionada = (Visita)adapter.getItem(adapterInfo.position);
		
		if(item.getTitle().equals("INICIAR")){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivarVisitaActivity.this);
			alertDialog.setTitle("INICIAR VISITA");
			alertDialog.setMessage("¿Realmente Desea Iniciar la Visita?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					VisitaAdapter adaptadorInterno = (VisitaAdapter)getListAdapter();
					Visita vi = (Visita)adaptadorInterno.getItem(adapterInfo.position);
					
					// verificar si la visita se encuentra Iniciada
					if(vi.getCodigoEstadoVisita()==2){
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivarVisitaActivity.this);
						alertDialog.setTitle("INICIAR VISITA");
						alertDialog.setMessage("La visita ya se encuentra Iniciada");
						alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
						alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});
						alertDialog.show();
						
						
					} else{
						//verificar que no haya ninguna otra visita activa
						if(viDao.existeVisitaActiva()){
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivarVisitaActivity.this);
							alertDialog.setTitle("INICIAR VISITA");
							alertDialog.setMessage("No se puede iniciar otra visita, ya existe una visita en proceso");
							alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
							alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							});
							alertDialog.show();
							
						} else{
							//adaptadorInterno.remove(vi);
							vi.setCodigoEstadoVisita(2);
							vi.setHoraInicioVisita(new Date());
							vi =viDao.actualizar(vi);
							
							//adaptadorInterno.add(vi);
							
							adaptadorInterno.notifyDataSetChanged();
							
						}	
					}
				}
			});
			
			alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
        	
			alertDialog.show();
			
		} else if(item.getTitle().equals("TERMINAR")){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivarVisitaActivity.this);
			alertDialog.setTitle("TERMINAR VISITA");
			alertDialog.setMessage("¿Realmente Desea Terminar la Visita?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					VisitaAdapter adaptadorInterno = (VisitaAdapter) getListAdapter();
					
					Visita vi = (Visita)adaptadorInterno.getItem(adapterInfo.position);
					// verificar que la visita se encuentre activa
					if(vi.getCodigoEstadoVisita()==2){
						vi.setCodigoEstadoVisita(3);
						vi.setHoraFinalVisita(new Date());
						vi =viDao.actualizar(vi);
						
						adaptadorInterno.notifyDataSetChanged();
						
					} else{
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivarVisitaActivity.this);
						alertDialog.setTitle("TERMINAR VISITA");
						alertDialog.setMessage("La visita no se encuentra en proceso");
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
			});
			
			alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
        	
			alertDialog.show();
			
			
		} else if(item.getTitle().equals("DESPROGRAMAR")){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivarVisitaActivity.this);
			alertDialog.setTitle("DESPROGRAMAR VISITA");
			alertDialog.setMessage("¿Realmente Desea Desprogramar la Visita?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				
					VisitaAdapter adaptadorInterno = (VisitaAdapter) getListAdapter();
					
					Visita vi = (Visita)adaptadorInterno.getItem(adapterInfo.position);
					// verificar que la visita se encuentre programada
					if(vi.getCodigoEstadoVisita()==1){
						vi.setCodigoEstadoVisita(4);
						vi.setHoraFinalVisita(new Date());
						vi =viDao.actualizar(vi);
						
						adaptadorInterno.notifyDataSetChanged();
						
					} else{
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivarVisitaActivity.this);
						alertDialog.setTitle("DESPROGRAMAR VISITA");
						alertDialog.setMessage("La visita no se encuentra en estado programada");
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
			});
			
			alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
        	
			alertDialog.show();
			
			
		}
		
		else { return false;}
		
		return true;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		 menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "MENU PRINCIPAL");
		return true;
	}
	  @Override
      public boolean onOptionsItemSelected(MenuItem item)
      {
          switch(item.getItemId())
          {
              case Menu.FIRST:
                    // do whatever
            	  Log.w("info","Menu principal presionado");
            	  Intent intent = new Intent(this, MenuPrincipalActivity.class);
            	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
            	    startActivity(intent);
            	  
            	  
                    return true;
              default:
                    return super.onOptionsItemSelected(item);
          }
      }
	
	@Override
	protected void onResume() {
		tvDao.open();
		evDao.open();
		viDao.open();
		cargarListaVisitas();
		super.onResume();
	}

	@Override
	protected void onPause() {
		tvDao.close();
		evDao.close();
		viDao.close();
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnFiltrar){
			cargarListaVisitas();
			
		}
		
		
	}

}

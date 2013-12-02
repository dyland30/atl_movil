package com.atl.atlmovil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.adapters.CobranzaAdapter;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrarCobranzasActivity extends ListActivity implements OnClickListener {

	List<Cobranza> lsCobranza;
	
	Visita visitaActiva;
	Cliente cliente;
	
	CobranzaDAO cobDao;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	CobranzaAdapter adapter;
	
	Date fechaDesde;
	Date fechaHasta;
	
	static final int DATE_DIALOG_DESDE = 100;
	static final int DATE_DIALOG_HASTA = 200;
	
	private int year;
	private int month;
	private int day;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_cobranzas);
		//registrar menu
		registerForContextMenu(this.getListView());
		
		//registrar botones
		Button btnNuevaCobranza = (Button)findViewById(R.id.btnNuevaCobranza);
		btnNuevaCobranza.setOnClickListener(this);
		
		Button btnSelFechaDesde = (Button)findViewById(R.id.btnSelFechaDesde);
		btnSelFechaDesde.setOnClickListener(this);
		
		Button btnSelFechaHasta = (Button)findViewById(R.id.btnSelFechaHasta);
		btnSelFechaHasta.setOnClickListener(this);
		
		Button btnFiltrarRegistroCobranza = (Button)findViewById(R.id.btnFiltrarRegistroCobranza);
		btnFiltrarRegistroCobranza.setOnClickListener(this);
		
		
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		cobDao = new CobranzaDAO(this);
		
		// cargar fechas
		
		fechaHasta = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(fechaHasta);
		
	    c.add(Calendar.DATE, -7);
		fechaDesde = c.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		TextView lblFechaDesde = (TextView)findViewById(R.id.lblFechaDesde);
		TextView lblFechaHasta = (TextView)findViewById(R.id.lblFechaHasta);
		lblFechaDesde.setText("Del: "+dateFormat.format(fechaDesde));
		lblFechaHasta.setText("Al: "+dateFormat.format(fechaHasta));
		
		abrirConexion();
		cargarCmbEstado();
		obtenerVisitaActiva();
		cargarListaCobranzas();
		
	}
	
	private void cargarCmbEstado(){
		
		String[] estados = 	{"Ingresado","Aprobado","Retenido","Anulado"};
		Spinner cmbFormaPago = (Spinner)findViewById(R.id.cmbEstadoCobranzaFiltro);
		ArrayAdapter<String> formaPagoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, estados);
		cmbFormaPago.setAdapter(formaPagoAdapter);
		
	}
	
	private void abrirConexion(){
		viDao.open();
		cliDao.open();
		cobDao.open();
	}
	
	private void cerrarConexion(){
		 viDao.close();
		 cliDao.close();
		 cobDao.close();
		}

	
	
	@Override
	protected void onResume() {
		abrirConexion();
		cargarListaCobranzas();
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
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
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		
		AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    //obtener la visita seleccionada
	    Cobranza cob = (Cobranza)adapter.getItem(adapterInfo.position);
	    
	    
	    menu.setHeaderTitle("Cobranza Nro: "+Cadena.formatearNumero("0000000000", (double)cob.getId()));
	    menu.add(0, v.getId(), 0, "IMPRIMIR");  
	    menu.add(0, v.getId(), 1, "EDITAR");
	    menu.add(0, v.getId(), 2, "REMOVER");
	    
	}
	
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		if(item.getTitle().equals("EDITAR")){
			// navegar a actividad con operacion editar
			CobranzaAdapter adaptadorInterno = (CobranzaAdapter) getListAdapter();
			Cobranza cob = (Cobranza)adaptadorInterno.getItem(adapterInfo.position);
			
			Intent nuevaCobranza = new Intent(RegistrarCobranzasActivity.this, NuevaCobranzaActivity.class);
			nuevaCobranza.putExtra("operacion", "editar");
			nuevaCobranza.putExtra("idCobranza", cob.getId());
			startActivity(nuevaCobranza);
			
		}
		if(item.getTitle().equals("REMOVER")){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegistrarCobranzasActivity.this);
			alertDialog.setTitle("REMOVER COBRANZA");
			alertDialog.setMessage("¿Realmente Desea Eliminar la Cobranza?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//remover cobranza con todos sus detalles
					CobranzaAdapter adaptadorInterno = (CobranzaAdapter) getListAdapter();
					Cobranza cob = (Cobranza)adaptadorInterno.getItem(adapterInfo.position);
					
					cobDao.eliminar(cob);
					cargarListaCobranzas();
					adaptadorInterno.notifyDataSetChanged();
					
					
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
		
		if(item.getTitle().equals("IMPRIMIR")){
			
			CobranzaAdapter adaptadorInterno = (CobranzaAdapter) getListAdapter();
			Cobranza cob = (Cobranza)adaptadorInterno.getItem(adapterInfo.position);
			
			Intent reporteCobranzaIntent = new Intent(RegistrarCobranzasActivity.this, ReporteCobranzaActivity.class);
			reporteCobranzaIntent.putExtra("idCobranza", cob.getId());
			startActivity(reporteCobranzaIntent);
			
		}
		
		return true;
	}
	
	private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.lblVisitaRegistrarCobranzas);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.lblClienteRegistrarCobranzas);
			cliente = cliDao.buscarPorID(visitaActiva.getCodigoCliente());
			if(cliente!=null){
				PersonaDAO perDao = new PersonaDAO(this);
				perDao.open();
				Persona per = perDao.buscarPorID(cliente.getCodigoPersona());
				txtCLientePedido.setText("Cliente: "+per.getDocumentoPersona()+" "+ per.getNombrePersona());
				perDao.close();
			}
		}
		
	}
	
		private void cargarListaCobranzas(){
		
		
		if(visitaActiva!=null && fechaDesde!=null && fechaHasta !=null){
			
			// filtrar por estado, fecha y visita
			Spinner cmbEstadoNuevaCobranza = (Spinner)findViewById(R.id.cmbEstadoCobranzaFiltro);
			
			String estado = (String)cmbEstadoNuevaCobranza.getSelectedItem();
			
			lsCobranza = cobDao.buscarPorVisitaFechaEstado(visitaActiva.getCodigoVisita(),fechaDesde, fechaHasta, estado);
			
			
			//ListView lvVisitas = (ListView)findViewById(android.R.id.list);
			adapter = new CobranzaAdapter(this, lsCobranza);
			//lvVisitas.setAdapter(adapter);
			setListAdapter(adapter);
		}
		
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.btnNuevaCobranza){
				
				Intent nuevaCobranza = new Intent(RegistrarCobranzasActivity.this, NuevaCobranzaActivity.class);
				nuevaCobranza.putExtra("operacion", "insertar");
				startActivity(nuevaCobranza);
				
			}
			if(v.getId()==R.id.btnSelFechaDesde){
				showDialog(DATE_DIALOG_DESDE);
				
			}
			if(v.getId()==R.id.btnSelFechaHasta){
				showDialog(DATE_DIALOG_HASTA);
				
			}
			if(v.getId()==R.id.btnFiltrarRegistroCobranza){
				
				cargarListaCobranzas();
				
			}
		}
	
		@Override
		protected Dialog onCreateDialog(int id) {
			Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
			
			switch (id) {
			case DATE_DIALOG_DESDE:
			   // set date picker as current date
				
				
			   return new DatePickerDialog(this, datePickerListenerDesde, 
	                         year, month,day);
			case DATE_DIALOG_HASTA:
				
				
			   return new DatePickerDialog(this, datePickerListenerHasta, 
	                         year, month,day);
				
			}
			return null;
		}
		
		private DatePickerDialog.OnDateSetListener datePickerListenerDesde    = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
		
			Calendar c = Calendar.getInstance();
			c.set(selectedYear, selectedMonth, selectedDay);
			fechaDesde = c.getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			TextView lblFechaDesde = (TextView)findViewById(R.id.lblFechaDesde);
			lblFechaDesde.setText("Del: "+dateFormat.format(fechaDesde));
		
		}
		};
		
		private DatePickerDialog.OnDateSetListener datePickerListenerHasta    = new DatePickerDialog.OnDateSetListener() {

			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;
				Log.w("year selected",""+year);
				Calendar c = Calendar.getInstance();
				c.set(selectedYear, selectedMonth, selectedDay);
				fechaHasta = c.getTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				TextView lblFechaHasta = (TextView)findViewById(R.id.lblFechaHasta);
				lblFechaHasta.setText("Al: "+dateFormat.format(fechaHasta));
			
			}
			};
		

}

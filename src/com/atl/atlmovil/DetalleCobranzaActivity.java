package com.atl.atlmovil;

import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.adapters.AmortizacionAdapter;
import com.atl.atlmovil.adapters.DetallePedidoAdapter;
import com.atl.atlmovil.dao.AmortizacionDAO;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Amortizacion;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.DetallePedido;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class DetalleCobranzaActivity extends ListActivity implements OnClickListener {

	Visita visitaActiva;
	Cliente cliente;
	List<Amortizacion> lsDetalles;
	Cobranza cobranza;
	AmortizacionAdapter adapter;
	
	VisitaDAO viDao;
	ClienteDAO cliDao;
	CobranzaDAO cobDao;
	AmortizacionDAO amDao;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cobranza);
        //registrar evento 
        Button btnAgregarDocs = (Button)findViewById(R.id.btnAgregarDocumentoPago);
        btnAgregarDocs.setOnClickListener(this);
        registerForContextMenu(this.getListView());
        
        
        viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		cobDao = new CobranzaDAO(this);
		amDao = new AmortizacionDAO(this);
		abrirConexion();
		Intent intent = getIntent();
		long idCobranza = intent.getLongExtra("idCobranza", 0);
		cobranza = cobDao.buscarPorID(idCobranza);
		
		obtenerVisitaActiva();
		cargarListaAmortizaciones();
		
        
    }
    
    private void obtenerVisitaActiva(){
		visitaActiva = viDao.obtenerVisitaActiva();
		if(visitaActiva!=null){
			TextView txtVisitaPedido = (TextView)findViewById(R.id.lblVisitaCobranzaDetalle);
			txtVisitaPedido.setText("Visita Nro: "+visitaActiva.getCodigoVisita());
			TextView txtCLientePedido = (TextView)findViewById(R.id.lblClienteCobranzaDetalle);
			cliente = cliDao.buscarPorID(visitaActiva.getCodigoCliente());
			if(cliente!=null){
				PersonaDAO perDao = new PersonaDAO(this);
				perDao.open();
				Persona per = perDao.buscarPorID(cliente.getCodigoPersona());
				txtCLientePedido.setText("Cliente: "+per.getDocumentoPersona()+" "+ per.getNombrePersona());
				perDao.close();
			}
		}
		if(cobranza!=null){
			TextView lblCobranza = (TextView)findViewById(R.id.lblNumeroCobranzaDetalle);
			lblCobranza.setText("Nro Cobranza: "+Cadena.formatearNumero("0000000000", (double)cobranza.getId()));
		}
	}
    
    private void cargarListaAmortizaciones(){
		
		lsDetalles = amDao.buscarPorCobranza(cobranza.getId());
		//ListView lvVisitas = (ListView)findViewById(android.R.id.list);
		adapter = new AmortizacionAdapter(this, lsDetalles);
		//lvVisitas.setAdapter(adapter);
		setListAdapter(adapter);
		
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detalle_cobranza, menu);
        return true;
    }
    

	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		
		AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    Adapter adapter = getListAdapter();
	    //obtener la visita seleccionada
	    Amortizacion am = (Amortizacion)adapter.getItem(adapterInfo.position);
	    
	    menu.setHeaderTitle("Nro Documento Pago: "+Cadena.formatearNumero("0000000000", (double)am.getCodigoDocumentoPago()));
	    menu.add(0, v.getId(), 0, "EDITAR");  
	    menu.add(0, v.getId(), 1, "REMOVER");
	    
	}

	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		
		if(item.getTitle().equals("EDITAR")){
			// navegar a la actividad agregar producto
			//operacion editar
			AmortizacionAdapter adaptadorInterno = (AmortizacionAdapter)getListAdapter();
			Amortizacion det = (Amortizacion)adaptadorInterno.getItem(adapterInfo.position);
			
			Intent agregarDocumentoIntent = new Intent(DetalleCobranzaActivity.this, AgregarDocumentosActivity.class);
			agregarDocumentoIntent.putExtra("operacion", "editar");
			agregarDocumentoIntent.putExtra("idAmortizacion", det.getId());
			agregarDocumentoIntent.putExtra("idCobranza", det.getIdCobranza());
			startActivity(agregarDocumentoIntent);
			
			
		} else if(item.getTitle().equals("REMOVER")){
			
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetalleCobranzaActivity.this);
			alertDialog.setTitle("REMOVER AMORTIZACION");
			alertDialog.setMessage("¿Realmente Desea Remover el registro?");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					AmortizacionAdapter adaptadorInterno = (AmortizacionAdapter)getListAdapter();
					Amortizacion det = (Amortizacion)adaptadorInterno.getItem(adapterInfo.position);
					//eliminar el detalle con todas sus tallas 
					amDao.eliminar(det);
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
		
		
	return true;
	}
	
	
	@Override
	protected void onResume() {
		abrirConexion();
		obtenerVisitaActiva();
		cargarListaAmortizaciones();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
	}
	private void abrirConexion(){
		viDao.open();
		cliDao.open();
		cobDao.open();
		amDao.open();
	}
	private void cerrarConexion(){
		viDao.close();
		 cliDao.close();
		 cobDao.close();
		 amDao.close();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnAgregarDocumentoPago){
			Intent agregarDocumentoIntent = new Intent(DetalleCobranzaActivity.this, AgregarDocumentosActivity.class);
			agregarDocumentoIntent.putExtra("operacion", "insertar");
			agregarDocumentoIntent.putExtra("idCobranza", cobranza.getId());
			startActivity(agregarDocumentoIntent);
			
		}
	}
}

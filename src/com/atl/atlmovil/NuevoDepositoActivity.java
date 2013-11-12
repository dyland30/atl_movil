package com.atl.atlmovil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.dao.BancoDAO;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.CobranzaDAO;
import com.atl.atlmovil.dao.DepositoDAO;
import com.atl.atlmovil.dao.MedioPagoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Banco;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.Deposito;
import com.atl.atlmovil.entidades.MedioPago;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NuevoDepositoActivity extends Activity implements OnClickListener{

	Deposito deposito;
	Cobranza cobranza;
	Visita visitaActiva;
	Cliente cliente;
	String operacion;
	
	List<MedioPago> lsMedioPago;
	List<Banco> lsBanco;
	VisitaDAO viDao;
	ClienteDAO cliDao;
	CobranzaDAO cobDao;
	MedioPagoDAO mpDao;
	DepositoDAO depDao;
	BancoDAO banDao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_deposito);
		//registrar botones
		/*btnGuardarDeposito
		 * btnCancelarDeposito
		 * */
		Button btnGuardarDeposito = (Button)findViewById(R.id.btnGuardarDeposito);
		Button btnCancelarDeposito = (Button)findViewById(R.id.btnCancelarDeposito);
		btnGuardarDeposito.setOnClickListener(this);
		btnCancelarDeposito.setOnClickListener(this);
		
		
		viDao = new VisitaDAO(this);
		cliDao = new ClienteDAO(this);
		cobDao = new CobranzaDAO(this);
		mpDao = new MedioPagoDAO(this);
		depDao = new DepositoDAO(this);
		banDao = new BancoDAO(this);
		abrirConexion();
		
		Intent intent = getIntent();
		operacion = intent.getStringExtra("operacion");
		long idCobranza = intent.getLongExtra("idCobranza", 0);
		long idDeposito = intent.getLongExtra("idDeposito", 0);
		cobranza = cobDao.buscarPorID(idCobranza);
		deposito = depDao.buscarPorID(idDeposito);
		cargarCmbMedioPago();
		cargarCmbBanco();
		/*
		 * lblNroDeposito
		 * lblNroCobranzaDeposito
		 * lblClienteDeposito
		 * cmbBancoDeposito
		 * txtVoucherDeposito
		 * txtImporteDeposito
		 * cmbMedioPagoDeposito
		 * btnGuardarDeposito
		 * btnCancelarDeposito
		 * */
		
	}
	private void cargarDatos(){
		TextView lblNroDeposito = (TextView)findViewById(R.id.lblNroDeposito);
		TextView lblNroCobranzaDeposito = (TextView)findViewById(R.id.lblNroCobranzaDeposito);
		TextView lblClienteDeposito = (TextView)findViewById(R.id.lblClienteDeposito);
		Spinner cmbBancoDeposito = (Spinner)findViewById(R.id.cmbBancoDeposito);
		Spinner cmbMedioPagoDeposito = (Spinner)findViewById(R.id.cmbMedioPagoDeposito);
		
		EditText txtVoucherDeposito = (EditText)findViewById(R.id.txtVoucherDeposito);
		EditText txtImporteDeposito = (EditText)findViewById(R.id.txtImporteDeposito);
		
		if(cobranza!=null){
			lblNroCobranzaDeposito.setText("Nro Cobranza: "+Cadena.formatearNumero("0000000000", (double)cobranza.getId()));
			visitaActiva = viDao.buscarPorID(cobranza.getCodigoVisita());
			if(visitaActiva!=null){
				cliente = cliDao.buscarPorID(visitaActiva.getCodigoCliente());
				if(cliente!=null){
					PersonaDAO perDao = new PersonaDAO(this);
					perDao.open();
					Persona per = perDao.buscarPorID(cliente.getCodigoCliente());
					lblClienteDeposito.setText(per.getNombrePersona());
					perDao.close();
					
				}
			}
			
			
		}
		if(deposito!=null){
			lblNroDeposito.setText("Nro Depósito: "+ Cadena.formatearNumero("0000000000", (double)deposito.getId()));
			
			//establecer banco seleccionado
			@SuppressWarnings( "unchecked")
			ArrayAdapter<Banco> bancoAdapter = (ArrayAdapter<Banco>)cmbBancoDeposito.getAdapter();
			Banco banco = banDao.buscarPorID(deposito.getBancoDeposito());
			if(banco!=null){
				cmbBancoDeposito.setSelection(bancoAdapter.getPosition(banco));
			}
			
			//establecer medio de pago
			@SuppressWarnings( "unchecked")
			ArrayAdapter<MedioPago> medioPagoAdapter = (ArrayAdapter<MedioPago>)cmbMedioPagoDeposito.getAdapter();
			MedioPago mp = mpDao.buscarPorID(cobranza.getCodigoMedioPago());
			if(mp!=null){
				cmbMedioPagoDeposito.setSelection(medioPagoAdapter.getPosition(mp));
			}
			txtVoucherDeposito.setText(Cadena.formatearNumero("00000", (double)deposito.getVoucherDeposito()));
			txtImporteDeposito.setText(Cadena.formatearNumero("0.00", deposito.getImporteDeposito()));
			
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo_deposito, menu);
		return true;
	}
	
	private void cargarCmbMedioPago(){
		lsMedioPago = new ArrayList<MedioPago>();
		lsMedioPago = mpDao.obtenerTodos();
		Spinner cmbTipoVisita = (Spinner)findViewById(R.id.cmbMedioPagoDeposito);
		ArrayAdapter<MedioPago> tipoVisitaAdapter = new ArrayAdapter<MedioPago>(this,
	            android.R.layout.simple_spinner_item, lsMedioPago);
		cmbTipoVisita.setAdapter(tipoVisitaAdapter);
		
	}
	private void cargarCmbBanco(){
		lsBanco = new ArrayList<Banco>();
		lsBanco = banDao.obtenerTodos();
		Spinner cmbBanco = (Spinner)findViewById(R.id.cmbBancoDeposito);
		ArrayAdapter<Banco> tipoVisitaAdapter = new ArrayAdapter<Banco>(this,
	            android.R.layout.simple_spinner_item, lsBanco);
		cmbBanco.setAdapter(tipoVisitaAdapter);
		
	}
	
	private void guardarDeposito(){
		try{
			Spinner cmbBancoDeposito = (Spinner)findViewById(R.id.cmbBancoDeposito);
			Spinner cmbMedioPagoDeposito = (Spinner)findViewById(R.id.cmbMedioPagoDeposito);
			
			EditText txtVoucherDeposito = (EditText)findViewById(R.id.txtVoucherDeposito);
			EditText txtImporteDeposito = (EditText)findViewById(R.id.txtImporteDeposito);
			
			String strVoucher = txtVoucherDeposito.getText().toString();
			String strImporte = txtImporteDeposito.getText().toString();
			if(strImporte.trim().equals("")){
				strImporte = "0.0";
			}
			if(strVoucher.trim().equals("")){
				strVoucher = "0";
			}
			
			long idVoucher = Long.parseLong(strVoucher);
			double importe = Double.parseDouble(strImporte);
			if(operacion.equals("insertar")){
				deposito = new Deposito();
				deposito.setCodigoDeposito(0);
				deposito.setFechaDeposito(new Date());
				deposito.setCodigoCobranza(cobranza.getId());
				deposito.setClienteDeposito(cliente.getCodigoCliente());
				
			}
			MedioPago mp = (MedioPago)cmbMedioPagoDeposito.getSelectedItem();
			if(mp!=null){
				deposito.setCodigoMedioPago(mp.getCodigoMedioPago());
			}
			Banco ban = (Banco)cmbBancoDeposito.getSelectedItem();
			if(ban!=null){
				deposito.setBancoDeposito(ban.getCodigoBanco());
				
			}
			deposito.setEstadoDeposito(true);
			deposito.setVoucherDeposito(idVoucher);
			deposito.setImporteDeposito(importe);
			if(operacion.equals("editar")){
				deposito = depDao.actualizar(deposito);
				
			} else{
				deposito = depDao.crear(deposito);
				
			}
			
		} catch(Exception ex){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(NuevoDepositoActivity.this);
			alertDialog.setTitle("ERROR");
			alertDialog.setMessage("Ha ocurrido un error al guardar el deposito: "+ex.getMessage());
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			alertDialog.show();
			Log.w("ERROR","Error "+ex.getMessage());
			
		}
		
	}
	
	private void abrirConexion(){
		viDao.open();
		cliDao.open();
		cobDao.open();
		mpDao.open();
		depDao.open();
		banDao.open();
	}
	
	private void cerrarConexion(){
		 viDao.close();
		 cliDao.close();
		 cobDao.close();
		 mpDao.close();
		 depDao.close();
		 banDao.close();
		}

	
	@Override
	protected void onResume() {
		abrirConexion();
		cargarCmbBanco();
		cargarCmbMedioPago();
		cargarDatos();
		super.onResume();
	}

	@Override
	protected void onPause() {
		cerrarConexion();
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnGuardarDeposito){
			guardarDeposito();
			finish();
		}
		if(v.getId()==R.id.btnCancelarDeposito){
			finish();
			
			
		}
	}
	

}

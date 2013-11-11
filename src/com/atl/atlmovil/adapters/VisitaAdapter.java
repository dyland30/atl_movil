package com.atl.atlmovil.adapters;


import java.text.SimpleDateFormat;
import java.util.List;

import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.EstadoVisitaDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.EstadoVisita;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Visita;

import com.atl.atlmovil.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VisitaAdapter extends BaseAdapter{
	private Activity activity;
	private List<Visita> data;
	private static LayoutInflater inflater=null;
	
	public VisitaAdapter(Activity a, List<Visita> d){
		activity = a;
		data = d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		View vi = convertView;
		if(convertView==null)
			vi = inflater.inflate(R.layout.visita_row, null);
		
		TextView txtCliente = (TextView)vi.findViewById(R.id.lblClientePedido);
		TextView txtCodigoVisita = (TextView)vi.findViewById(R.id.txtCodigoVisita);
		TextView txtFechaVisita = (TextView)vi.findViewById(R.id.txtFechaVisita);
		TextView txtEstadoVisita = (TextView)vi.findViewById(R.id.txtEstadoVisita);
		
		//establecer el valor de los campos
		Visita visita = data.get(position);
		Log.w("info", visita.getCodigoVisita()+"");
		// obtener Cliente
		Cliente cli = null;
		EstadoVisita evi = null;
		Persona per = null;
		if(visita.getCodigoCliente()>0){
			ClienteDAO cliDao = new ClienteDAO(activity);
			cliDao.open();
			cli = cliDao.buscarPorID(visita.getCodigoCliente());
			cliDao.close();
			
		}
		//Log.w("info", "cli "+cli.getCodigoCliente()+"");
		// obtener estado de Visita
		if(visita.getCodigoEstadoVisita()>0){
			EstadoVisitaDAO eviDao = new EstadoVisitaDAO(activity);
			eviDao.open();
			evi = eviDao.buscarPorID(visita.getCodigoEstadoVisita());
			eviDao.close();
		}
		Log.w("info", "evi "+evi.getCodigoEstadoVisita()+"");
		// obtener Persona
		if(cli!=null){
			PersonaDAO perDao = new PersonaDAO(activity);
			perDao.open();
			Log.w("codigoPersona",cli.getCodigoPersona()+"");
			
			per = perDao.buscarPorID(cli.getCodigoPersona());
			perDao.close();
		}
		
		//Log.w("info", "per "+per.getCodigoPersona()+"");
		if(per!=null)
			txtCliente.setText(per.getDocumentoPersona()+" "+per.getNombrePersona());
		
		if(evi!=null)
			txtEstadoVisita.setText(evi.getDescripcionEstadoVisita());
		
		txtCodigoVisita.setText(visita.getCodigoVisita()+"");
		if(visita.getFechaVisita()!=null){
			txtFechaVisita.setText(dateFormat.format(visita.getFechaVisita()));
		} else{
			txtFechaVisita.setText("01/01/1900");
			
		}
		
		return vi;
	}

}

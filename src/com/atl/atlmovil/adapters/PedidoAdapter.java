package com.atl.atlmovil.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.Cliente;
import com.atl.atlmovil.entidades.Persona;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Visita;

public class PedidoAdapter extends BaseAdapter{
	private Activity activity;
	private List<Pedido> data;
	private static LayoutInflater inflater=null;
	
	public PedidoAdapter(Activity a, List<Pedido> d){
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
			vi = inflater.inflate(R.layout.pedido_row, null);
		
		TextView txtCliente = (TextView)vi.findViewById(R.id.lblClientePedido);
		TextView txtCodigoPedido = (TextView)vi.findViewById(R.id.lblNumeroPedido);
		TextView txtFechaPedido = (TextView)vi.findViewById(R.id.lblFechaPedido);
		TextView txtEstadoPedido = (TextView)vi.findViewById(R.id.lblEstadoPedido);
		TextView txtImportePedido = (TextView)vi.findViewById(R.id.lblImportePedido);
		
		
		//establecer el valor de los campos
		Pedido ped = data.get(position);
		//Log.w("info", ped.getId()+"");
		// obtener Cliente
		Cliente cli = null;
		Visita visita = null;
		
		Persona per = null;
		
		if(ped.getCodigoVisita()>0){
			VisitaDAO viDao = new VisitaDAO(activity);
			viDao.open();
			visita = viDao.buscarPorID(ped.getCodigoVisita());
			viDao.close();
		}
		
		if(visita!=null){
			ClienteDAO cliDao = new ClienteDAO(activity);
			cliDao.open();
			cli = cliDao.buscarPorID(visita.getCodigoCliente());
			cliDao.close();
		}
		
				
		if(cli!=null){
			PersonaDAO perDao = new PersonaDAO(activity);
			perDao.open();
			per = perDao.buscarPorID(cli.getCodigoPersona());
			perDao.close();
		}
		
		//Log.w("info", "per "+per.getCodigoPersona()+"");
		if(per!=null)
			txtCliente.setText(per.getDocumentoPersona()+" "+per.getNombrePersona());
		
		txtEstadoPedido.setText(ped.getEstadoPedido());
		txtCodigoPedido.setText(Cadena.formatearNumero("0000000000", (double)ped.getId()));
		
		if(ped.getFechaIngresoPedido()!=null){
			txtFechaPedido.setText(dateFormat.format(ped.getFechaIngresoPedido()));
		} else{
			txtFechaPedido.setText("01/01/1900");
			
		}
		txtImportePedido.setText(Cadena.formatearNumero("############.00", ped.getImportePedido()));
		
		
		return vi;
	}
}

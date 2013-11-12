package com.atl.atlmovil.adapters;

import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.entidades.EmpresaCarga;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EmpresaCargaAdapter extends BaseAdapter{

	private Activity activity;
	private List<EmpresaCarga> data;
	private static LayoutInflater inflater=null;
	public EmpresaCargaAdapter(Activity a, List<EmpresaCarga> d){
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
		View vi = convertView;
		if(convertView==null)
			vi = inflater.inflate(R.layout.empresa_carga_row, null);
		TextView lblRowCodigoEmpresaCarga = (TextView)vi.findViewById(R.id.lblCodigoEmpresaCargaRow);
		TextView lblRowDescripcionEmpresaCarga = (TextView)vi.findViewById(R.id.lblDescripcionEmpresaCargaRow);
		
		EmpresaCarga e = data.get(position);
		
		lblRowCodigoEmpresaCarga.setText("Cod: "+Cadena.formatearNumero("00000", (double)e.getCodigoEmpresaCarga()));
		lblRowDescripcionEmpresaCarga.setText("Ruc:"+e.getRucEmpresaCarga()+" "+e.getNombreEmpresaCarga());
		
		
		return vi;
	}

}

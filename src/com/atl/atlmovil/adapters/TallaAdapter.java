package com.atl.atlmovil.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.atl.atlmovil.R;
import com.atl.atlmovil.entidades.Talla;
public class TallaAdapter extends BaseAdapter{
	List<Talla> data;
	private Activity activity;
	private static LayoutInflater inflater=null;
	
	public TallaAdapter(Activity a, List<Talla> d){
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
			vi = inflater.inflate(R.layout.talla_row, null);
		
		TextView lblNumero = (TextView)vi.findViewById(R.id.lblNroTallaRow);
		TextView lblStock = (TextView)vi.findViewById(R.id.lblExistenciasTallaRow);
		
		Talla t = data.get(position);
		if(t!=null){
			lblNumero.setText("Número: "+t.getNumeroTalla());
			lblStock.setText("Existencias: "+t.getStockDisponibleTalla());
			
		}
		
		
		
		return vi;
	}

}

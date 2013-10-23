package com.atl.atlmovil.adapters;

import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.entidades.Producto;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductoAdapter extends BaseAdapter {
	private Activity activity;
	private List<Producto> data;
	private static LayoutInflater inflater=null;
	
	public ProductoAdapter(Activity a, List<Producto> d){
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
			vi = inflater.inflate(R.layout.producto_row, null);
		
		TextView lblRowProductoNombre = (TextView)vi.findViewById(R.id.lblRowProductoNombre);
		TextView lblRowProductoCod = (TextView)vi.findViewById(R.id.lblRowProductoCod);
		TextView lblRowProdCalidad = (TextView)vi.findViewById(R.id.lblRowProdCalidad);
		TextView lblRowProdColor = (TextView)vi.findViewById(R.id.lblRowProdColor);
		TextView lblRowProductoSexo = (TextView)vi.findViewById(R.id.lblRowProductoSexo);
		
		Producto p = data.get(position);
		
		lblRowProductoNombre.setText(p.getDescripcionProducto());
		lblRowProductoCod.setText("COD: "+Cadena.formatearNumero("000000",  (double)p.getCodigoProducto()));
		lblRowProdCalidad.setText(p.getCalidadProducto());
		lblRowProdColor.setText(p.getColorProducto());
		lblRowProductoSexo.setText(p.getSexoProducto());
		
		
		return vi;
	}
	

}

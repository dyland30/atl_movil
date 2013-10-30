package com.atl.atlmovil.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.entidades.Cobranza;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CobranzaAdapter extends BaseAdapter {
	private Activity activity;
    List<Cobranza> data;
    private static LayoutInflater inflater=null;
    public CobranzaAdapter(Activity a, List<Cobranza> d){
    	
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
			vi = inflater.inflate(R.layout.cobranza_row, null);
		
		TextView lblNumeroCobranza = (TextView)vi.findViewById(R.id.lblRowNroCobranza);
		TextView lblFechaCobranza = (TextView)vi.findViewById(R.id.lblRowFechaCobranza);
		TextView lblEstadoCobranza = (TextView)vi.findViewById(R.id.lblRowEstadoCobranza);
		TextView lblImporteCobranza = (TextView)vi.findViewById(R.id.lblRowImporteCobranza);
		
		Cobranza cob = data.get(position);
		lblNumeroCobranza.setText("Nro Cobranza: "+Cadena.formatearNumero("0000000000",  (double)cob.getId()));
		
		if(cob.getFechaCObranza()!=null){
			lblFechaCobranza.setText(dateFormat.format(cob.getFechaCObranza()));
		} else{
			lblFechaCobranza.setText("01/01/1900");
			
		}
		
		lblEstadoCobranza.setText(cob.getEstadoCobranza());
		lblImporteCobranza.setText(Cadena.formatearNumero("##########.00", cob.getImporteCobranza()));
		return vi;
	}
	
	
}

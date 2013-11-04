package com.atl.atlmovil.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.dao.DocumentoPagoDAO;
import com.atl.atlmovil.entidades.Amortizacion;
import com.atl.atlmovil.entidades.DocumentoPago;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AmortizacionAdapter extends BaseAdapter{
	private Activity activity;
	List<Amortizacion> data;
	private static LayoutInflater inflater=null;
	
	public AmortizacionAdapter(Activity a, List<Amortizacion> d){

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
		/*
		 * @+id/lblNroDocumentoPagoRow
		 * @+id/lblTipoDocumentoPagoRow
		 * @+id/lblReferenciaDocumentoPagoRow
		 * @+id/lblFechaVencimientoAmortizacionRow
		 * @+id/lblMedioPagoAmortizacionRow
		 * @+id/lblImporteAmortizacionRow
		 * */
		
		View vi = convertView;
		
		if(convertView==null)
			vi = inflater.inflate(R.layout.amortizacion_row, null);
		
		
		TextView lblNroDocumentoPago = (TextView)vi.findViewById(R.id.lblNroDocumentoPagoRow);
		TextView lblTipoDocumentoPago = (TextView)vi.findViewById(R.id.lblTipoDocumentoPagoRow);
		TextView lblReferenciaDocumentoPago = (TextView)vi.findViewById(R.id.lblReferenciaDocumentoPagoRow);
		TextView lblFechaVencimientoAmortizacion = (TextView)vi.findViewById(R.id.lblFechaVencimientoAmortizacionRow);
		TextView lblImporteAmortizacion = (TextView)vi.findViewById(R.id.lblImporteAmortizacionRow);
		
		
		Amortizacion am = data.get(position);
		lblNroDocumentoPago.setText("Nro Documento: "+Cadena.formatearNumero("0000000000", (double)am.getCodigoDocumentoPago()));
		lblImporteAmortizacion.setText("Imp. Amort: "+Cadena.formatearNumero("#########0.00", am.getImporteAmortizacion()));
		
		//obtener documento de pago
		DocumentoPagoDAO dpDao = new DocumentoPagoDAO(activity);
		dpDao.open();
		DocumentoPago dp = dpDao.buscarPorID(am.getCodigoDocumentoPago());
		if(dp!=null){
			lblTipoDocumentoPago.setText(dp.getTipoDocumentoPago());
			lblReferenciaDocumentoPago.setText(dp.getReferenciaDocumentoPago());
			lblFechaVencimientoAmortizacion.setText(dateFormat.format(dp.getFechaVencimientoDocumentoPago()));
			
		}
		
		dpDao.close();
		
		return vi;
	}
	
	

}

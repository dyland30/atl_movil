package com.atl.atlmovil.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.entidades.DocumentoPago;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class DocumentoPagoAdapter extends BaseAdapter{

	private Activity activity;
	private List<DocumentoPago> data;
	private static LayoutInflater inflater=null;
	
	public DocumentoPagoAdapter(Activity a, List<DocumentoPago> d){
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
		/*
		 * lblNroDocumentoRow
		 * lblTipoDocumentoRow
		 * lblReferenciaRow
		 * lblImporteOriginalRow
		 * lblImportePendienteRow
		 * lblFechaVencimientoDocRow
		 * */
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		View vi = convertView;
		if(convertView==null)
			vi = inflater.inflate(R.layout.documento_pago_row, null);
		
		DocumentoPago dp = data.get(position);
		
		TextView lblNroDocumentoRow = (TextView)vi.findViewById(R.id.lblNroDocumentoRow);
		TextView lblTipoDocumentoRow = (TextView)vi.findViewById(R.id.lblTipoDocumentoRow);
		TextView lblReferenciaRow = (TextView)vi.findViewById(R.id.lblReferenciaRow);
		TextView lblImporteOriginalRow = (TextView)vi.findViewById(R.id.lblImporteOriginalRow);
		TextView lblImportePendienteRow = (TextView)vi.findViewById(R.id.lblImportePendienteRow);
		TextView lblFechaVencimientoDocRow = (TextView)vi.findViewById(R.id.lblFechaVencimientoDocRow);
		
		lblNroDocumentoRow.setText("Nro Doc: "+Cadena.formatearNumero("0000000000", (double)dp.getCodigoDocumentoPago()));
		lblTipoDocumentoRow.setText(dp.getTipoDocumentoPago());
		lblReferenciaRow.setText("Ref: "+dp.getReferenciaDocumentoPago());
		lblImporteOriginalRow.setText("Imp. Original :"+Cadena.formatearNumero("0.00", dp.getImporteOriginalDocumentoPago()));
		lblImportePendienteRow.setText("Imp. Pendiente : "+Cadena.formatearNumero("0.00", dp.getImportePendienteDocumentoPago()));
		lblFechaVencimientoDocRow.setText("Vence : "+ dateFormat.format(dp.getFechaVencimientoDocumentoPago()));
		
		return vi;
	}
	
	

}

package com.atl.atlmovil.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.dao.BancoDAO;
import com.atl.atlmovil.entidades.Banco;
import com.atl.atlmovil.entidades.Deposito;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DepositoAdapter extends BaseAdapter{

	private List<Deposito> data;
	private Activity activity;
	private static LayoutInflater inflater=null;
	
	public DepositoAdapter(Activity a, List<Deposito> d){
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
			vi = inflater.inflate(R.layout.deposito_row, null);
		
		TextView lblNroDepositoRow = (TextView)vi.findViewById(R.id.lblNroDepositoRow);
		TextView lblFechaDepositoRow = (TextView)vi.findViewById(R.id.lblFechaDepositoRow);
		TextView lblNroCobranzaDepositoRow = (TextView)vi.findViewById(R.id.lblNroCobranzaDepositoRow);
		TextView lblBancoRow = (TextView)vi.findViewById(R.id.lblBancoRow);
		TextView lblVoucherRow = (TextView)vi.findViewById(R.id.lblVoucherRow);
		TextView lblImporteRow = (TextView)vi.findViewById(R.id.lblImporteRow);
		
		Deposito dep = data.get(position);
		lblNroDepositoRow.setText("Nro Deposito: "+Cadena.formatearNumero("0000000000", (double)dep.getCodigoDeposito()));
		lblFechaDepositoRow.setText(dateFormat.format(dep.getFechaDeposito()));
		lblNroCobranzaDepositoRow.setText("Cobranza: "+Cadena.formatearNumero("0000000000", (double)dep.getCodigoCobranza()));
		lblVoucherRow.setText("Voucher :"+Cadena.formatearNumero("00000", (double)dep.getVoucherDeposito()));
		
		lblImporteRow.setText("Imp: "+Cadena.formatearNumero("0.00", dep.getImporteDeposito()));
		
		//obtener banco
	
		BancoDAO bancDao = new BancoDAO(activity);
		bancDao.open();
		Banco ban = bancDao.buscarPorID(dep.getBancoDeposito());
		if(ban!=null){
			
			lblBancoRow.setText(ban.getNombreBanco());
			
		}
		bancDao.close();
		
		return vi;
	}
	

}

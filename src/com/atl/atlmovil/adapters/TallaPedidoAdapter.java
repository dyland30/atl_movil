package com.atl.atlmovil.adapters;

import java.util.List;

import com.atl.atlmovil.R;
import com.atl.atlmovil.dao.DetallePedidoDAO;
import com.atl.atlmovil.dao.TallaDAO;
import com.atl.atlmovil.entidades.DetallePedido;
import com.atl.atlmovil.entidades.Talla;
import com.atl.atlmovil.entidades.TallaPedido;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TallaPedidoAdapter extends BaseAdapter{
	private Activity activity;
	List<TallaPedido> data;
	
	
	private static LayoutInflater inflater=null;
	
	public TallaPedidoAdapter(Activity a, List<TallaPedido> d){
		
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
			vi = inflater.inflate(R.layout.talla_pedido_row, null);
		
		TextView lblNumeroTallaPedido = (TextView)vi.findViewById(R.id.lblNumeroTallaPedido) ;
		TextView lblExistenciasTallaPedido = (TextView)vi.findViewById(R.id.lblExistenciasTallaPedido) ;
		TextView lblCantidadTallaPedido = (TextView)vi.findViewById(R.id.lblCantidadTallaPedido) ;
		TextView lblImporteTallaPedido = (TextView)vi.findViewById(R.id.lblImporteTallaPedido) ;
			
		TallaPedido tallaPed = data.get(position);
		
		lblNumeroTallaPedido.setText("Número: "+tallaPed.getNumeroTalla());
		lblCantidadTallaPedido.setText("Cantidad: "+ tallaPed.getCantidad());
		
		//obtener Detalle Pedido
		
		DetallePedido det = null;
		DetallePedidoDAO detDao = new DetallePedidoDAO(activity);
		detDao.open();
		det = detDao.buscarPorID(tallaPed.getIdPedido(), tallaPed.getCodigoProducto());
		
		detDao.close();
		
		
		if(det!=null){
			double importe = 0.0D;
			importe = tallaPed.getCantidad()*det.getPrecioUnitario();
			
			lblImporteTallaPedido.setText("Importe: "+importe);
		}
		
		//obtener talla
		Talla t = null;
		TallaDAO tdao = new TallaDAO(activity);
		tdao.open();
		t = tdao.buscarPorID(tallaPed.getCodigoProducto(), tallaPed.getNumeroTalla());
		tdao.close();
		if(t!=null){
			lblExistenciasTallaPedido.setText("Existencias: "+t.getStockDisponibleTalla());
			
		}
		
		return vi;
	}
	

}

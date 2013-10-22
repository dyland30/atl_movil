package com.atl.atlmovil.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.R;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.dao.ProductoFormaPagoDAO;
import com.atl.atlmovil.dao.TallaPedidoDAO;
import com.atl.atlmovil.entidades.DetallePedido;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.Producto;
import com.atl.atlmovil.entidades.ProductoFormaPago;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DetallePedidoAdapter extends BaseAdapter{
	private Activity activity;
	List<DetallePedido> data;
	
	
	private static LayoutInflater inflater=null;
	
	public DetallePedidoAdapter(Activity a, List<DetallePedido> d){
		
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
		
		TextView lblProductoDetallePedido = (TextView)vi.findViewById(R.id.lblProductoDetallePedido);
		TextView lblCodigoProductoDetallePedido = (TextView)vi.findViewById(R.id.lblCodProductoDetallePedido);
		TextView lblPunitDetallePedido = (TextView)vi.findViewById(R.id.lblPunitDetallePedido);
		TextView lblImporteDetallePedido = (TextView)vi.findViewById(R.id.lblImporteDetallePedido);
		
		DetallePedido dped = data.get(position);
		Producto prod = null;
		Pedido pe = null;
		ProductoFormaPago prodFp=null;
		Double precioUnitario = 0.0D;
		int cantTallas=0;
		//obtener producto
		if(dped.getCodigoProducto()>0){
			ProductoDAO pdao = new ProductoDAO(activity);
			pdao.open();
			prod = pdao.buscarPorID(dped.getCodigoProducto());
			pdao.close();
			
			
		}
		if(prod!=null){
			lblProductoDetallePedido.setText(prod.getDescripcionProducto());
			lblCodigoProductoDetallePedido.setText(Cadena.formatearNumero("000000", (double)prod.getCodigoProducto()));
		}
			
		lblPunitDetallePedido.setText(Cadena.formatearNumero("############.00", dped.getPrecioUnitario()));
		
		//obtener precio por producto y forma de pago
		
		//obtener orden de pedido
		if(dped.getIdPedido()>0){
			PedidoDAO pdao = new PedidoDAO(activity);
			pdao.open();
			pe=pdao.buscarPorID(dped.getIdPedido());
			pdao.close();
		}
		if(pe!=null){
			ProductoFormaPagoDAO pfDao = new ProductoFormaPagoDAO(activity);
			pfDao.open();
			prodFp = pfDao.buscarPorID(pe.getCodigoFormaPago(), dped.getCodigoProducto());	
			pfDao.close();
			
		}
		if(prodFp!=null){
			precioUnitario = prodFp.getPrecio();
			
			TallaPedidoDAO tpDao = new TallaPedidoDAO(activity);
			cantTallas = tpDao.obtenerCantidadTallas(pe.getId(), prod.getCodigoProducto());
			
		}
		
		// calcular monto cantTalla*Punit
		Double importe = cantTallas*precioUnitario;
		lblImporteDetallePedido.setText(Cadena.formatearNumero("############.00", importe));
		
		return vi;
	}
	

}

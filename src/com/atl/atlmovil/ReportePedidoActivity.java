package com.atl.atlmovil;

import java.util.ArrayList;
import java.util.List;

import com.atl.atlmovil.customviews.ReportePedidoView;
import com.atl.atlmovil.dao.DetallePedidoDAO;
import com.atl.atlmovil.dao.PedidoDAO;
import com.atl.atlmovil.dao.ProductoFormaPagoDAO;
import com.atl.atlmovil.dao.TallaPedidoDAO;
import com.atl.atlmovil.entidades.DetallePedido;
import com.atl.atlmovil.entidades.Pedido;
import com.atl.atlmovil.entidades.ProductoFormaPago;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ReportePedidoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reporte_pedido);
		
		Intent intent = getIntent();
		long idPedido = intent.getLongExtra("idPedido", 0);
		PedidoDAO pdao = new PedidoDAO(this);
		DetallePedidoDAO dpDao = new DetallePedidoDAO(this);
		ProductoFormaPagoDAO pfDao = new ProductoFormaPagoDAO(this);
		TallaPedidoDAO tpDao = new TallaPedidoDAO(this);
		try{
			
			dpDao.open();
			pdao.open();
			pfDao.open();
			tpDao.open();
			Pedido ped = pdao.buscarPorID(idPedido);
			if(ped!=null){
				//cargar detalles
				List<DetallePedido> lsDetalles = new ArrayList<DetallePedido>();
				for(DetallePedido dp : dpDao.buscarPorPedido(ped.getId())){
					// precio unitario
					ProductoFormaPago pf = pfDao.buscarPorID(ped.getCodigoFormaPago(), dp.getCodigoProducto());
					if(pf!=null){
						dp.setPrecioUnitario(pf.getPrecio());
					}
					
					dp.setTallas(tpDao.buscarPorPedidoProducto(dp.getIdPedido(), dp.getCodigoProducto()));
					lsDetalles.add(dp);
					
				}
				ped.setDetalles(lsDetalles);
				
				//obtener vista y agregar pedido
				ReportePedidoView reporteView = (ReportePedidoView)findViewById(R.id.reportePedido);
				reporteView.setPedido(ped);
				
				
				
			}
		}
		
		finally{
			
			pdao.close();
			dpDao.close();
			pfDao.close();
			tpDao.close();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reporte_pedido, menu);
		return true;
	}

}

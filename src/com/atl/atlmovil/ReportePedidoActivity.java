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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
		 super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		 menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "MENU PRINCIPAL");
		return true;
	}
	  @Override
      public boolean onOptionsItemSelected(MenuItem item)
      {
          switch(item.getItemId())
          {
              case Menu.FIRST:
                    // do whatever
            	  Log.w("info","Menu principal presionado");
            	  Intent intent = new Intent(this, MenuPrincipalActivity.class);
            	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
            	    startActivity(intent);
            	  
            	  
                    return true;
              default:
                    return super.onOptionsItemSelected(item);
          }
      }

}

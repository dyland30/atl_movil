package com.atl.atlmovil.customviews;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;

import com.atl.atlmovi.util.Cadena;
import com.atl.atlmovil.AbstractBaseView;
import com.atl.atlmovil.R;
import com.atl.atlmovil.dao.ClienteDAO;
import com.atl.atlmovil.dao.EmpleadoDAO;
import com.atl.atlmovil.dao.EmpresaCargaDAO;
import com.atl.atlmovil.dao.FormaPagoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.ProductoDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.*;

public class ReportePedidoView extends AbstractBaseView {

	public static final  int MIN_HEIGHT = 200;
	public static final  int MIN_WIDTH = 200;
	private Pedido pedido;
	private String titulo;
	Paint colorFondo;
	Paint colorTitulo;
	Paint colorLinea;
	Paint colorTexto;
	String fecha;
	ProductoDAO pdao;
	Context contexto;
	Persona persona_cliente;
	Persona persona_empleado;
	EmpresaCarga empresaTransporte;
	FormaPago formaPago;
	ClienteDAO cliDao;
	EmpleadoDAO empDao;
	PersonaDAO perDao;
	EmpresaCargaDAO empCargaDao;
	FormaPagoDAO fpDao;
	VisitaDAO viDao;
	SimpleDateFormat dateFormat;
	public ReportePedidoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		contexto= context;
		init();
	}
	
	public ReportePedidoView(Context context, AttributeSet attrs){
		super(context, attrs);
		
		TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.ReportePedidoView, 0,0);
		titulo = t.getString(R.styleable.ReportePedidoView_titulo);
		if(titulo==null) titulo="PEDIDO NRO: ";
		t.recycle();
		contexto=context;
		init();
		
	}

	public void init(){
		setMinimumHeight(MIN_HEIGHT);
		setMinimumWidth(MIN_WIDTH);
		setSaveEnabled(true);
		colorFondo = new Paint();
		colorTitulo = new Paint();
		colorLinea = new Paint();
		colorTexto = new Paint();
		colorFondo.setColor(getResources().getColor(android.R.color.white));
		colorFondo.setStyle(Paint.Style.FILL);
		colorFondo.setAntiAlias(true);
		
		colorTitulo.setAntiAlias(true);
		colorTitulo.setColor(getResources().getColor(android.R.color.black));
		colorTitulo.setStyle(Paint.Style.FILL);
		colorTitulo.setTextSize(18.0f);
		colorTitulo.setTextAlign(Align.CENTER);
		
		
		colorTexto.setAntiAlias(true);
		colorTexto.setColor(getResources().getColor(android.R.color.black));
		colorTexto.setStyle(Paint.Style.FILL);
		colorTexto.setTextSize(16.0f);
		
		colorLinea.setAntiAlias(true);
		colorLinea.setStrokeWidth(1.0f);
		colorLinea.setColor(getResources().getColor(android.R.color.black));
		colorLinea.setStyle(Paint.Style.STROKE);
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");		
		
		
		pdao = new ProductoDAO(contexto);
		
		cliDao = new ClienteDAO(contexto);
		empDao = new EmpleadoDAO(contexto);
		perDao = new PersonaDAO(contexto);
		empCargaDao = new EmpresaCargaDAO(contexto);
		fpDao = new FormaPagoDAO(contexto);
		viDao = new VisitaDAO(contexto);
		
		
	}
	
	 @Override
	    public void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        int w = this.getWidth();
	    	int h = this.getHeight();
	    	int inicioy = 30;
	    	int iniciox = 10;
	    	
	    	canvas.drawRect(0,0,w,h, colorFondo);
	    	
	    	String razonSocial = "CALZADO ATLAS S.A.";
	    	String ruc = "RUC: 20185788599";
	    	String direccion = "Jr. Cristóbal de peralta Norte 112 - Surco";
	    	
	    	
	    	canvas.drawText(razonSocial, w/2, inicioy, colorTitulo);
	    	canvas.drawText(ruc, w/2, inicioy+20, colorTitulo);
	    	canvas.drawText(direccion, w/2, inicioy+40, colorTitulo);
	    	
	    
	    	
	    	if(pedido!=null){
	    	
	    		titulo  += Cadena.formatearNumero("0000000000", (double)pedido.getId());
	    		try{
	    			
	    			cliDao.open();
	    			empDao.open();
	    			perDao.open();
	    			empCargaDao.open();
	    			fpDao.open();
	    			viDao.open();
	    			//obtener cliente
	    			Visita vi = viDao.buscarPorID(pedido.getCodigoVisita());
	    			if(vi!=null){
	    				Cliente cli = cliDao.buscarPorID(vi.getCodigoCliente());
	    				if(cli!=null){
	    					persona_cliente = perDao.buscarPorID(cli.getCodigoPersona());
	    					
	    					
	    				}
	    				
	    				Empleado emp = empDao.buscarPorID(vi.getCodigoEmpleado());
	    				if(emp!=null){
	    					
	    					persona_empleado = perDao.buscarPorID(emp.getCodigoPersona());
	    					
	    				}
	    				
	    			}
	    			//fecha
	    			fecha = dateFormat.format(pedido.getFechaIngresoPedido());
	    			String strfecha = "Telef: 434-2666 / 434-1969     FECHA: "+fecha;
	    			canvas.drawText(strfecha, iniciox, inicioy+65, colorTexto);
	    			
	    			// obtener empresa de carga
	    			empresaTransporte = empCargaDao.buscarPorID(pedido.getCodigoEmpresaCarga());
	    			
	    			// obtener forma de pago
	    			formaPago = fpDao.buscarPorID(pedido.getCodigoFormaPago());
	    			
	    			
	    			if(persona_empleado!=null)
	    				canvas.drawText("VENDEDOR: "+persona_empleado.getDocumentoPersona()+"-"+persona_empleado.getNombrePersona(), iniciox, inicioy+110, colorTexto);
	    			
	    			if(persona_cliente!=null){
	    				canvas.drawText("CLIENTE: "+persona_cliente.getDocumentoPersona()+"-"+persona_cliente.getNombrePersona(), iniciox, inicioy+130, colorTexto);
	    				canvas.drawText("DIRECION: "+persona_cliente.getDireccionPersona(), iniciox, inicioy+150, colorTexto);
	    				
	    			}
	    			
	    			if(empresaTransporte!=null)
	    				canvas.drawText("EMP. TRANSP.: "+empresaTransporte.getNombreEmpresaCarga(), iniciox, inicioy+170, colorTexto);
	    			
	    			if(formaPago!=null)
	    				canvas.drawText("COND. VENTA: "+formaPago.getDescripcionFormaPago(), iniciox, inicioy+190, colorTexto);
			    	//empresa de transporte
			    	//Forma de Pago
			    	
	    			
	    			
	    			
	    			
	    		} finally{
	    			cliDao.close();
	    			empDao.close();
	    			perDao.close();
	    			empCargaDao.close();
	    			fpDao.close();
	    			viDao.close();
	    			
	    		}
	    		
	    		
	    		
	    		
	    		
	    		// cabecera de detalle
	    		pdao.open();
	    		int cursor = inicioy+260;
	    		
	    		for(DetallePedido det :pedido.getDetalles()){
	    			
	    			if(det.getTallas().size()>0){
	    				
	    				//codigo de producto
		    			canvas.drawText(Cadena.formatearNumero("00000", (double)det.getCodigoProducto()), iniciox, cursor, colorTexto);
		    			//obtener descripcion de producto
		    			Producto prod = pdao.buscarPorID(det.getCodigoProducto());
		    			if(prod!=null)
		    			canvas.drawText(prod.getDescripcionProducto().substring(0, 14).toUpperCase(), iniciox+50, cursor, colorTexto);
		    			
		    			canvas.drawText(Cadena.formatearNumero("0.00", det.getPrecioUnitario()), iniciox+300, cursor, colorTexto);
		    			
		    			for(TallaPedido tp : det.getTallas()){
		    				canvas.drawText(tp.getNumeroTalla()+"", iniciox+180, cursor, colorTexto);
		    				canvas.drawText(tp.getCantidad()+"", iniciox+240, cursor, colorTexto);
		    				
		    				canvas.drawText(Cadena.formatearNumero("0.00", tp.getCantidad()*det.getPrecioUnitario()), iniciox+380, cursor, colorTexto);
		    				
		    				cursor+=25;
		    				
		    				
		    			}
	    				
	    			}	
	    		}
	    		//imprimir pie de pagina
	    		cursor+=10;
	    		canvas.drawLine(iniciox, cursor, w-20, cursor, colorLinea);
	    		cursor+=20;
	    		
	    		canvas.drawText("IMPORTE TOTAL:  S/. "+Cadena.formatearNumero("0.00", (double)pedido.getImportePedido()), iniciox+240, cursor, colorTexto);
	    		cursor+=15;
	    		canvas.drawLine(iniciox, cursor, w-20, cursor, colorLinea);
	    		
	    		cursor+=35;
	    		
	    		canvas.drawText("GRACIAS POR SU COMPRA", w/2, cursor, colorTitulo);
	    		
	    		
	    		if(cursor>h){
					//mostrar scrollbar
					
					
				}
	    		
	    		pdao.close();
	    		
	    	} else{
	    		
	    		titulo = "NO SE HA SELECCIONADO NINGÚN PEDIDO";
	    	}
	    	
	    	//canvas.drawLine(iniciox, inicioy+70, w-20, inicioy+70, colorLinea);
	    	canvas.drawText(titulo, iniciox, inicioy+90, colorTexto);
	    	//canvas.drawLine(iniciox, inicioy+100, w-20, inicioy+100, colorLinea);
	    	
    		
    		canvas.drawLine(iniciox, inicioy+215, w-20, inicioy+215, colorLinea);
    		
    		canvas.drawText("COD", iniciox, inicioy+235, colorTexto);
    		canvas.drawText("DESCRIPCION", iniciox+50, inicioy+235, colorTexto);
    		canvas.drawText("TALLA", iniciox+180, inicioy+235, colorTexto);
    		canvas.drawText("CANT.", iniciox+240, inicioy+235, colorTexto);
    		canvas.drawText("PRECIO", iniciox+300, inicioy+235, colorTexto);
    		canvas.drawText("TOTAL", iniciox+380, inicioy+235, colorTexto);
    		
    		canvas.drawLine(iniciox, inicioy+240, w-20, inicioy+240, colorLinea);
	        
	 }
	
	@Override
	protected int hGetMaximumHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int hGetMaximumWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}

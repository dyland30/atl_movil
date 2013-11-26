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
import com.atl.atlmovil.dao.DocumentoPagoDAO;
import com.atl.atlmovil.dao.EmpleadoDAO;
import com.atl.atlmovil.dao.MedioPagoDAO;
import com.atl.atlmovil.dao.PersonaDAO;
import com.atl.atlmovil.dao.VisitaDAO;
import com.atl.atlmovil.entidades.*;

public class ReciboCobranzaView  extends AbstractBaseView{

	public static final  int MIN_HEIGHT = 200;
	public static final  int MIN_WIDTH = 200;
	Paint colorFondo;
	Paint colorTitulo;
	Paint colorLinea;
	Paint colorTexto;
	private Cobranza cobranza;
	
	Context contexto;
	
	Persona persona_cliente;
	Persona persona_empleado;
	String fecha;
	MedioPago medioPago;
	
	ClienteDAO cliDao;
	EmpleadoDAO empDao;
	VisitaDAO viDao;
	PersonaDAO perDao;
	MedioPagoDAO mpDao;
	DocumentoPagoDAO docDao;
	SimpleDateFormat dateFormat;
	public ReciboCobranzaView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public ReciboCobranzaView(Context context, AttributeSet attrs){
		super(context, attrs);
		
		TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.ReportePedidoView, 0,0);
		
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
		
		
		cliDao = new ClienteDAO(contexto);
		empDao = new EmpleadoDAO(contexto);
		perDao = new PersonaDAO(contexto);
		mpDao = new MedioPagoDAO(contexto);
		viDao = new VisitaDAO(contexto);
		docDao = new DocumentoPagoDAO(contexto);
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
	    	
	    	String titulo = "RECIBO NRO: ";
	    	
	    	canvas.drawText(razonSocial, w/2, inicioy, colorTitulo);
	    	canvas.drawText(ruc, w/2, inicioy+20, colorTitulo);
	    	canvas.drawText(direccion, w/2, inicioy+40, colorTitulo);
	    	
	    	
	    	if(cobranza!=null){
	    		titulo  += Cadena.formatearNumero("0000000000", (double)cobranza.getId());
	    		//obtener empleado, cliente, medio de pago
	    		try{
	    			
	    			cliDao.open();
	    			perDao.open();
	    			viDao.open();
	    			mpDao.open();
	    			empDao.open();
	    			docDao.open();
	    			Visita vi = viDao.buscarPorID(cobranza.getCodigoVisita());
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
	    			
	    			fecha = dateFormat.format(cobranza.getFechaCObranza());
	    			String strfecha = "Telef: 434-2666 / 434-1969     FECHA: "+fecha;
	    			canvas.drawText(strfecha, iniciox, inicioy+65, colorTexto);
	    			
	    			//obtener medio de pago
	    			
	    			medioPago = mpDao.buscarPorID(cobranza.getCodigoMedioPago());
	    			
	    			if(persona_empleado!=null)
	    				canvas.drawText("VENDEDOR: "+persona_empleado.getDocumentoPersona()+"-"+persona_empleado.getNombrePersona(), iniciox, inicioy+110, colorTexto);
	    			
	    			if(persona_cliente!=null){
	    				canvas.drawText("CLIENTE: "+persona_cliente.getDocumentoPersona()+"-"+persona_cliente.getNombrePersona(), iniciox, inicioy+130, colorTexto);
	    				canvas.drawText("DIRECION: "+persona_cliente.getDireccionPersona(), iniciox, inicioy+150, colorTexto);
	    				
	    			}
	    			
	    			if(medioPago!=null)
	    				canvas.drawText("MEDIO PAGO: "+medioPago.getDescripcionMedioPago(), iniciox, inicioy+170, colorTexto);
	    			
	    			

	    			int cursor = inicioy+260;
	    			
	    			for(Amortizacion amort : cobranza.getAmortizaciones()){
	    				
	    				//OBTENER DOCUMENTO
	    				DocumentoPago doc = docDao.buscarPorID(amort.getCodigoDocumentoPago());
	    				if(doc!=null){
	    					canvas.drawText(doc.getTipoDocumentoPago(), iniciox, cursor, colorTexto);
	    					canvas.drawText(doc.getReferenciaDocumentoPago(), iniciox+70, cursor, colorTexto);
	    					canvas.drawText(Cadena.formatearNumero("0.00", doc.getImporteOriginalDocumentoPago()), iniciox+200, cursor, colorTexto);
	    					
	    					canvas.drawText(Cadena.formatearNumero("0.00", amort.getImporteAmortizacion()), iniciox+270, cursor, colorTexto);
	    					
	    					canvas.drawText(Cadena.formatearNumero("0.00", doc.getImportePendienteDocumentoPago()), iniciox+360, cursor, colorTexto);		
	    				}
	    				
	    				
	    				cursor+=25;
	    			}
	    			
	    			// pie de pagina
	    			cursor+=10;
		    		canvas.drawLine(iniciox, cursor, w-20, cursor, colorLinea);
		    		cursor+=20;
		    		canvas.drawText("IMPORTE TOTAL DE COBRANZA :  S/. "+Cadena.formatearNumero("0.00", (double)cobranza.getImporteCobranza()), iniciox, inicioy+195, colorTexto);
		    		//cursor+=10;
		    		//canvas.drawLine(iniciox, cursor, w-20, cursor, colorLinea);
		    		
		    		if(cursor>h){
		    			// mostrar scrollbar
		    			
		    		}
	    			
	    			
	    			
	    			
	    		} finally{
	    			
	    			cliDao.close();
	    			perDao.close();
	    			viDao.close();
	    			mpDao.close();
	    			empDao.close();
	    			docDao.close();
	    			
	    		}
	    		
	    		
	    	} else{
	    		
	    		titulo = "NO SE HA SELECCIONADIO NINGUNA COBRANZA";
	    	}
	    	canvas.drawText(titulo, iniciox, inicioy+90, colorTexto);
	    	canvas.drawLine(iniciox, inicioy+215, w-20, inicioy+215, colorLinea);
	    	
	    	canvas.drawText("DOCUM", iniciox, inicioy+235, colorTexto);
	    	
	    	canvas.drawText("NUMERO", iniciox+70, inicioy+235, colorTexto);
	    	
	    	canvas.drawText("VALOR", iniciox+200, inicioy+235, colorTexto);
	    	
	    	canvas.drawText("IMPORTE", iniciox+270, inicioy+235, colorTexto);
	    	
	    	canvas.drawText("IMP. PEND.", iniciox+360, inicioy+235, colorTexto);
	    	
	    	
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

	public Cobranza getCobranza() {
		return cobranza;
	}

	public void setCobranza(Cobranza cobranza) {
		this.cobranza = cobranza;
	}

}

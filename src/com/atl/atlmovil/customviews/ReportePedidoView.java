package com.atl.atlmovil.customviews;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.atl.atlmovil.AbstractBaseView;
import com.atl.atlmovil.R;
import com.atl.atlmovil.entidades.Pedido;

public class ReportePedidoView extends AbstractBaseView {

	public static final  int MIN_HEIGHT = 200;
	public static final  int MIN_WIDTH = 200;
	private Pedido pedido;
	private String titulo;
	Paint colorFondo;
	Paint colorTitulo;
	Paint colorLinea;
	public ReportePedidoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public ReportePedidoView(Context context, AttributeSet attrs){
		super(context, attrs);
		
		TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.ReportePedidoView, 0,0);
		titulo = t.getString(R.styleable.ReportePedidoView_titulo);
		if(titulo==null) titulo="PEDIDO NRO: ";
		t.recycle();
		init();
		
	}

	public void init(){
		setMinimumHeight(MIN_HEIGHT);
		setMinimumWidth(MIN_WIDTH);
		setSaveEnabled(true);
		colorFondo = new Paint();
		colorTitulo = new Paint();
		colorLinea = new Paint();
		
		colorFondo.setColor(getResources().getColor(android.R.color.white));
		colorFondo.setStyle(Paint.Style.FILL);
		colorFondo.setAntiAlias(true);
		
		colorTitulo.setAntiAlias(true);
		colorTitulo.setColor(getResources().getColor(android.R.color.black));
		colorTitulo.setStyle(Paint.Style.FILL);
		colorTitulo.setTextSize(18.0f);
		
		
		colorLinea.setAntiAlias(true);
		colorLinea.setStrokeWidth(1.0f);
		colorLinea.setColor(getResources().getColor(android.R.color.black));
		colorLinea.setStyle(Paint.Style.STROKE);
		
		
		
	}
	
	 @Override
	    public void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        int w = this.getWidth();
	    	int h = this.getHeight();
	    	int inicioy = 30;
	    	int iniciox = 10;
	    	
	    	canvas.drawRect(0,0,w,h, colorFondo);
	    	
	    	String razonSocial = "RAZON SOCIAL";
	    	String ruc = "RUC";
	    	String direccion = "DIRECCION";
	    	String fecha = "Fecha: 21/11/2013 12:00 PM";
	    	
	    	canvas.drawText(razonSocial, iniciox+w/3, inicioy, colorTitulo);
	    	canvas.drawText(ruc, iniciox+w/3, inicioy+20, colorTitulo);
	    	canvas.drawText(direccion, iniciox+w/3, inicioy+40, colorTitulo);
	    	canvas.drawText(fecha, iniciox, inicioy+65, colorTitulo);
	    	
	    	
	    	canvas.drawLine(iniciox, inicioy+70, w-20, inicioy+70, colorLinea);
	    	canvas.drawText(titulo, iniciox, inicioy+90, colorTitulo);
	    	canvas.drawLine(iniciox, inicioy+100, w-20, inicioy+100, colorLinea);
	    	
	    	
	        
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

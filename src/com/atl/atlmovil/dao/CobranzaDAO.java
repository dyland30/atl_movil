package com.atl.atlmovil.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.atl.atlmovil.entidades.Amortizacion;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.DocumentoPago;
import com.atl.atlmovil.entidades.Visita;

@SuppressLint("SimpleDateFormat")
public class CobranzaDAO {
   
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { "id","codigoCobranza","codigoMedioPago","importeCobranza", "fechaCobranza","estaSincronizado", "estadoCobranza", "codigoVisita", "importePendiente", "esAutoDistribuido"};
	Context contexto;
	
	public CobranzaDAO(Context context){
		contexto = context;
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Cobranza> obtenerTodos(){
		 List<Cobranza> ls = new ArrayList<Cobranza>();
		 Cursor cursor = database.query(Cobranza.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Cobranza ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<Cobranza> buscarPorVisita(long codigoVisita){
		 List<Cobranza> ls = new ArrayList<Cobranza>();
		 Cursor cursor = database.query(Cobranza.class.getSimpleName(), allColumns, " codigoVisita = '"+codigoVisita+"'",null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Cobranza ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<Cobranza> buscarPorVisitaFechaEstado(long codigoVisita, Date fechaInicio, Date fechaFin, String estado){
		 List<Cobranza> ls = new ArrayList<Cobranza>();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 String strFechaInicio = dateFormat.format(fechaInicio);
		 String strFechaFin = dateFormat.format(fechaFin);
		 Cursor cursor = database.query(Cobranza.class.getSimpleName(), allColumns, " codigoVisita = '"+codigoVisita+"' and estadoCobranza = '"+estado+"' and fechaCObranza >= '"+strFechaInicio+"' and fechaCObranza <='"+strFechaFin+"'",null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Cobranza ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Cobranza ent){
		 long id = ent.getId();
		 database.delete(Cobranza.class.getSimpleName(),"id = "+id,null);
		 
	 }
	 
	 public Cobranza crear(long codigoCobranza, long codigoMedioPago, double importeCobranza, Date fechaCobranza, Boolean estaSincronizado,
			 String estadoCobranza, long codigoVisita, double importePendiente, Boolean esAutoDistribuido){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Cobranza ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoCobranza", codigoCobranza);
		 values.put("codigoMedioPago", codigoMedioPago);
		 values.put("importeCobranza", importeCobranza);
		 values.put("fechaCobranza", dateFormat.format(fechaCobranza));
		 values.put("estaSincronizado", estaSincronizado);
		 values.put("estadoCobranza", estadoCobranza);
		 values.put("codigoVisita", codigoVisita);
		 values.put("importePendiente",importePendiente);
		 values.put("esAutoDistribuido",esAutoDistribuido);
		 
		 
		 
		 long insertId = database.insert(Cobranza.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 
	 public Cobranza crear(Cobranza ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Cobranza nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoCobranza", ent.getCodigoCobranza());
		 values.put("codigoMedioPago",  ent.getCodigoMedioPago());
		 values.put("importeCobranza", ent.getImporteCobranza());
		 values.put("fechaCobranza", dateFormat.format( ent.getFechaCObranza()));
		 values.put("estaSincronizado", ent.getEstaSincronizado());
		 values.put("estadoCobranza", ent.getEstadoCobranza());
		 values.put("codigoVisita", ent.getCodigoVisita());
		 values.put("importePendiente",ent.getImporteCobranza());
		 values.put("esAutoDistribuido",ent.getEsAutoDistribuido());
		 long insertId = database.insert(Cobranza.class.getSimpleName(), null, values);
		 nuevo=buscarPorID(insertId);
		 
		 return nuevo;
	 }
	 
	 
	 public Cobranza actualizar(Cobranza ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Cobranza nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoCobranza", ent.getCodigoCobranza());
		 values.put("codigoMedioPago",  ent.getCodigoMedioPago());
		 values.put("importeCobranza", ent.getImporteCobranza());
		 values.put("fechaCobranza", dateFormat.format( ent.getFechaCObranza()));
		 values.put("estaSincronizado", ent.getEstaSincronizado());
		 values.put("estadoCobranza", ent.getEstadoCobranza());
		 values.put("codigoVisita", ent.getCodigoVisita());
		 values.put("importePendiente",ent.getImportePendiente());
		 values.put("esAutoDistribuido",ent.getEsAutoDistribuido());
		 database.update(Cobranza.class.getSimpleName(), values, " id = "+ent.getId(), null);
		 nuevo=buscarPorID(ent.getId());
		 
		 return nuevo;
	 }
	 public void autoDistribuir(Cobranza ent) throws Exception{
		 double monto = 0.0D;
		 if(ent!=null){
			 
			 DocumentoPagoDAO dpDao = new DocumentoPagoDAO(contexto);
			 VisitaDAO viDao = new VisitaDAO(contexto);
			 AmortizacionDAO amDao = new AmortizacionDAO(contexto);
			 
			 try{
				 
				 dpDao.open();
				 viDao.open();
				 amDao.open();
				 //establecemos la cobranza como autodistribuida
				 ent.setEsAutoDistribuido(true);
				 actualizar(ent); 
				 
				 Visita vi = viDao.buscarPorID(ent.getCodigoVisita());
				 //obtener Documentos pendientes
				  monto = ent.getImportePendiente();
				 List<DocumentoPago> lsDocumentos;
				 lsDocumentos =  dpDao.buscarPorCliente(vi.getCodigoCliente(), "");
				 int cont =0;
				 DocumentoPago docPag;
				 while(monto>0 && cont<lsDocumentos.size()){
					 docPag = lsDocumentos.get(cont);
					 if(monto>docPag.getImportePendienteDocumentoPago()){
						 //crear amortizacion por el monto pendiente del documento
						 Amortizacion am = new Amortizacion();
						 am.setCodigoDocumentoPago(docPag.getCodigoDocumentoPago());
						 am.setIdCobranza(ent.getId());
						 am.setImporteAmortizacion(docPag.getImportePendienteDocumentoPago());
						 amDao.crear(am);
						 monto -= docPag.getImportePendienteDocumentoPago();
						 
					 } else {
						 //crear amortizacion por el monto y terminar bucle
						 Amortizacion am = new Amortizacion();
						 am.setCodigoDocumentoPago(docPag.getCodigoDocumentoPago());
						 am.setIdCobranza(ent.getId());
						 am.setImporteAmortizacion(monto);
						 amDao.crear(am);
						 monto = 0.0D;
						 break;
					 }
					 cont++;
				 }
				 
				 ent.setImportePendiente(monto);
				 actualizar(ent); 
				 
			 } catch(Exception ex){
				 throw ex;
				 
				 
			 } finally{
				 dpDao.close();
				 viDao.close();
				 amDao.close();
			 }
			 
		 }
		
	 }
	 
	 public void actualizarImporteCobranza(long idCobranza){
		 //obtener cobranza
		 Cobranza cob = buscarPorID(idCobranza);
		 if(cob!=null && !cob.getEsAutoDistribuido()){
			 double monto = calcularMontoCobranza(idCobranza);
			 ContentValues values = new ContentValues();
			 values.put("importeCobranza",monto);
			 database.update(Cobranza.class.getSimpleName(), values, " id = "+idCobranza, null);
			 
		 }
		 actualizarImportePendiente(idCobranza);
		 
	 }
	 public void actualizarImportePendiente(long idCobranza){
		 //imp pendiente = montoOriginalCobranza - calcularMontoCobranza 
		 Cobranza cob = buscarPorID(idCobranza);
		 if(cob!=null){
			 double monto = calcularMontoCobranza(idCobranza);
			 double pendiente = cob.getImporteCobranza() - monto;
			 ContentValues values = new ContentValues();
			 values.put("importePendiente",pendiente);
			 database.update(Cobranza.class.getSimpleName(), values, " id = "+idCobranza, null);
		 }
	 }
	 public Cobranza buscarPorID(long id){
		 Cobranza ent = null;
		 Cursor cursor = database.query(Cobranza.class.getSimpleName(), allColumns, " id = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			
		 }
		 cursor.close();
		 return ent;
	 }
	 public double calcularMontoCobranza(long idCobranza){
		 double suma =0.0D;		
		 Cursor cursor = database.rawQuery("SELECT sum(importeAmortizacion) FROM Amortizacion " +
		 		"where idCobranza = ? ", new String[] {idCobranza+""});;
		 cursor.moveToFirst();
		 suma = cursor.getDouble(0);
		 cursor.close();
		 return suma; 
	 }
	 
	 public int obtenerCantidadDetalles(long idCobranza){
		 int suma=0;		
		 Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM Amortizacion " +
		 		"where idCobranza = ? ", new String[] {idCobranza+""});;
		 cursor.moveToFirst();
		 suma = cursor.getInt(0);
		 cursor.close();
		 return suma; 
	 }
	 
	 private Cobranza cursorToEnt(Cursor cursor) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Cobranza ent = null;
		    if(cursor!=null ){
		    	ent = new Cobranza();
		    	ent.setId(cursor.getLong(0));
		    	ent.setCodigoCobranza(cursor.getLong(1));
		    	ent.setCodigoMedioPago(cursor.getInt(2));
		    	ent.setImporteCobranza(cursor.getDouble(3));
		    	
		    	try {
					ent.setFechaCObranza(dateFormat.parse(cursor.getString(4)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaCObranza(new Date(1900,1,1));
					
				}
		    	
		    	Boolean estaSincronizado = cursor.getInt(5)==1 ?true : false;
		    	ent.setEstaSincronizado(estaSincronizado);
		    	ent.setEstadoCobranza(cursor.getString(6));
		    	ent.setCodigoVisita(cursor.getLong(7));
		    	ent.setImportePendiente(cursor.getDouble(8));
		    	
		    	Boolean esAutoDistribuido = cursor.getInt(9)==1 ?true : false;
		    	ent.setEsAutoDistribuido(esAutoDistribuido);
		    	
		    	
		    }
		    
		    return ent;
	 }
	 
	 

}

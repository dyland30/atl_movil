package com.atl.atlmovil.dao;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.atl.atlmovil.entidades.Amortizacion;
import com.atl.atlmovil.entidades.Cobranza;
import com.atl.atlmovil.entidades.DocumentoPago;

public class AmortizacionDAO {
	
	Context contexto;
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {"id","idCobranza","codigoDocumentoPago","importeAmortizacion","anotacionAmortizacion","codigoAmortizacion"};

	public AmortizacionDAO(Context context){
		contexto = context;
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Amortizacion> obtenerTodos(){
		 List<Amortizacion> ls = new ArrayList<Amortizacion>();
		 Cursor cursor = database.query(Amortizacion.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			Amortizacion ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public List<Amortizacion> buscarPorCobranza(long idCobranza){
		 List<Amortizacion> ls = new ArrayList<Amortizacion>();
		 Cursor cursor = database.query(Amortizacion.class.getSimpleName(), allColumns, " idCobranza= "+idCobranza,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Amortizacion ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Amortizacion ent){
		 long id = ent.getId();
		 database.delete(Amortizacion.class.getSimpleName(),"id = "+id,null);
		 actualizarImporteCobranza(ent.getIdCobranza());
		 actualizarSaldoDocumento(ent.getCodigoDocumentoPago(), "eliminar", ent.getImporteAmortizacion(), 0);
		 
	 }
	
	 public Amortizacion crear(long idCobranza,long codigoDocumentoPago,double importeAmortizacion,String anotacionAmortizacion, long codigoAmortizacion){
		
		 Amortizacion ent = null;
		 ContentValues values = new ContentValues();
		 //values.put("id", id);
		 values.put("idCobranza", idCobranza);
		 values.put("codigoDocumentoPago", codigoDocumentoPago);
		 values.put("importeAmortizacion", importeAmortizacion);
		 values.put("anotacionAmortizacion", anotacionAmortizacion);
		 values.put("codigoAmortizacion", codigoAmortizacion);
		 
		 long insertId = database.insert(Amortizacion.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		 actualizarImporteCobranza(ent.getIdCobranza());
		 actualizarSaldoDocumento(ent.getCodigoDocumentoPago(), "insertar", ent.getImporteAmortizacion(), 0);
		 return ent;
	 }
	 
	 private void actualizarSaldoDocumento(long codigoDocumento, String operacion, double monto, double montoAnterior){
		 DocumentoPagoDAO docDao = new DocumentoPagoDAO(contexto);
		 DocumentoPago doc;
		 docDao.open();
		 try{
			 doc = docDao.buscarPorID(codigoDocumento);
			 if(operacion.equals("insertar")){
				 //disminuir el monto disponible del documento
				 doc.setImportePendienteDocumentoPago(doc.getImportePendienteDocumentoPago()-monto);
			 }
			 if(operacion.equals("editar")){
				 //aumentar o disminuir el monto disponible del documento
				 doc.setImportePendienteDocumentoPago(doc.getImportePendienteDocumentoPago()-(monto-montoAnterior));
			 }
			 if(operacion.equals("eliminar")){
				 //aumentar el monto disponible del documento
				 doc.setImportePendienteDocumentoPago(doc.getImportePendienteDocumentoPago()+monto);	 
			 }
			 
			 docDao.actualizar(doc);
			 
			 
		 }catch(Exception ex){
			 Log.w("error",ex.getMessage());
			 
		 }finally{
			 docDao.close();
			 
		 }
		 
	 }
	 
	 public Amortizacion crear(Amortizacion ent){
		 
		 Amortizacion nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("idCobranza", ent.getIdCobranza());
		 values.put("codigoDocumentoPago", ent.getCodigoDocumentoPago());
		 values.put("importeAmortizacion", ent.getImporteAmortizacion());
		 values.put("anotacionAmortizacion", ent.getAnotacionAmortizacion());
		 values.put("codigoAmortizacion", ent.getCodigoAmortizacion());
		 
		 long insertId = database.insert(Amortizacion.class.getSimpleName(), null, values);
		 nuevo=buscarPorID(insertId);
		 actualizarImporteCobranza(ent.getIdCobranza());
		 actualizarSaldoDocumento(ent.getCodigoDocumentoPago(), "insertar", ent.getImporteAmortizacion(), 0);
		 return nuevo;
	 }
	 private void actualizarImporteCobranza(long idCobranza){
		 CobranzaDAO cobDao = new CobranzaDAO(contexto);
		 cobDao.open();
		 try{
			 //obtener cobranza y establecer estado no sincronizado
			 
			 Cobranza cob = cobDao.buscarPorID(idCobranza);
			 cob.setEstaSincronizado(false);
			 cobDao.actualizar(cob);
			 cobDao.actualizarImporteCobranza(idCobranza);
		 }
		 catch(Exception ex){
			 Log.w("error", ex.getMessage());
			 
		 }
		 finally{
			 cobDao.close();
		 }
		 
	 }
	 
	 
	 public Amortizacion actualizar(Amortizacion ent){
		 Amortizacion antiguo = null;
		 antiguo = buscarPorID(ent.getId());
		 Amortizacion nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("idCobranza", ent.getIdCobranza());
		 values.put("codigoDocumentoPago", ent.getCodigoDocumentoPago());
		 values.put("importeAmortizacion", ent.getImporteAmortizacion());
		 values.put("anotacionAmortizacion", ent.getAnotacionAmortizacion());
		 values.put("codigoAmortizacion", ent.getCodigoAmortizacion());
		 database.update(Amortizacion.class.getSimpleName(), values, " id = "+ent.getId(), null);
		 nuevo=buscarPorID(ent.getId());
		 actualizarImporteCobranza(nuevo.getIdCobranza());
		 actualizarSaldoDocumento(ent.getCodigoDocumentoPago(), "editar", ent.getImporteAmortizacion(), antiguo.getImporteAmortizacion());
		 
		 return nuevo;
	 }
	 public Amortizacion buscarPorID(long id){
		 Amortizacion ent = null;
		 Cursor cursor = database.query(Amortizacion.class.getSimpleName(), allColumns, " id = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
		 }
		 cursor.close();	
		 
		 return ent;
	 }
	 
	 private Amortizacion cursorToEnt(Cursor cursor) {
		
		    Amortizacion ent = null;
		    if(cursor!=null ){
		    	ent = new Amortizacion();
		    	ent.setId(cursor.getLong(0));
		    	ent.setIdCobranza(cursor.getLong(1));
		    	ent.setCodigoDocumentoPago(cursor.getInt(2));
		    	ent.setImporteAmortizacion(cursor.getDouble(3));
		    	ent.setAnotacionAmortizacion(cursor.getString(4));
		    	ent.setCodigoAmortizacion(cursor.getLong(5));
		    	
		    }
		    
		    return ent;
	 }

}

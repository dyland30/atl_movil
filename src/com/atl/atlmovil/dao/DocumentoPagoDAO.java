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

import com.atl.atlmovil.entidades.DocumentoPago;

@SuppressLint("SimpleDateFormat")
public class DocumentoPagoDAO {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	private String[] allColumns = { "codigoDocumentoPago","fechaEmisionDocumentoPago",
			"fechaVencimientoDocumentoPago","importeAmortizadoDocumentoPago", "importeDescontadoDocumentoPago",
			"importeIgvDocumentoPago","importeOriginalDocumentoPago","importePendienteDocumentoPago",
			"plazoDocumentoPago","tipoDocumentoPago","ReferenciaDocumentoPago,codigoCliente"};

	public DocumentoPagoDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<DocumentoPago> obtenerTodos(){
		 List<DocumentoPago> ls = new ArrayList<DocumentoPago>();
		 Cursor cursor = database.query(DocumentoPago.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 DocumentoPago ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 public List<DocumentoPago> buscarPorCliente(long codigoCliente, String referencia){
		 List<DocumentoPago> ls = new ArrayList<DocumentoPago>();
		 Cursor cursor = database.query(DocumentoPago.class.getSimpleName(), allColumns, " codigoCliente = "+codigoCliente+" and ReferenciaDocumentoPago like '%"+referencia+"%' and importePendienteDocumentoPago >0",null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 DocumentoPago ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 
	 public void eliminar(DocumentoPago ent){
		 long id = ent.getCodigoDocumentoPago();
		 database.delete(DocumentoPago.class.getSimpleName(),"codigoDocumentoPago = "+id,null);
		 
	 }
	 
	 public DocumentoPago crear( long codigoDocumentoPago,Date fechaEmisionDocumentoPago,
				Date fechaVencimientoDocumentoPago, double importeAmortizadoDocumentoPago, 
				double importeDescontadoDocumentoPago, double importeIgvDocumentoPago,
				double importeOriginalDocumentoPago, double importePendienteDocumentoPago,
				int plazoDocumentoPago,String tipoDocumentoPago,String ReferenciaDocumentoPago, long codigoCliente){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 DocumentoPago ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoDocumentoPago", codigoDocumentoPago);
		 values.put("fechaEmisionDocumentoPago", dateFormat.format(fechaEmisionDocumentoPago));
		 values.put("fechaVencimientoDocumentoPago", dateFormat.format(fechaVencimientoDocumentoPago));
		 values.put("importeAmortizadoDocumentoPago", importeAmortizadoDocumentoPago);
		 values.put("importeDescontadoDocumentoPago", importeDescontadoDocumentoPago);
		 values.put("importeIgvDocumentoPago", importeIgvDocumentoPago);
		 values.put("importeOriginalDocumentoPago", importeOriginalDocumentoPago);
		 values.put("importePendienteDocumentoPago", importePendienteDocumentoPago);
		 values.put("plazoDocumentoPago", plazoDocumentoPago);
		 values.put("tipoDocumentoPago", tipoDocumentoPago);
		 values.put("ReferenciaDocumentoPago", ReferenciaDocumentoPago);
		 values.put("codigoCliente", codigoCliente);
		 long insertId = database.insert(DocumentoPago.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public DocumentoPago actualizar(DocumentoPago ent){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 DocumentoPago nuevo = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoDocumentoPago", ent.getCodigoDocumentoPago());
		 values.put("fechaEmisionDocumentoPago", dateFormat.format(ent.getFechaEmisionDocumentoPago()));
		 values.put("fechaVencimientoDocumentoPago", dateFormat.format(ent.getFechaVencimientoDocumentoPago()));
		 values.put("importeAmortizadoDocumentoPago", ent.getImporteAmortizadoDocumentoPago());
		 values.put("importeDescontadoDocumentoPago", ent.getImporteDescontadoDocumentoPago());
		 values.put("importeIgvDocumentoPago", ent.getImporteIgvDocumentoPago());
		 values.put("importeOriginalDocumentoPago", ent.getImporteOriginalDocumentoPago());
		 values.put("importePendienteDocumentoPago", ent.getImportePendienteDocumentoPago());
		 values.put("plazoDocumentoPago", ent.getPlazoDocumentoPago());
		 values.put("tipoDocumentoPago", ent.getTipoDocumentoPago());
		 values.put("ReferenciaDocumentoPago",ent.getReferenciaDocumentoPago());
		 values.put("codigoCliente", ent.getCodigoCliente());
		 database.update(DocumentoPago.class.getSimpleName(), values, " codigoDocumentoPago = "+ent.getCodigoDocumentoPago(), null);
		 nuevo=buscarPorID(ent.getCodigoDocumentoPago());
		 
		 return nuevo;
	 }
	 public DocumentoPago buscarPorID(long id){
		 DocumentoPago ent = null;
		 Cursor cursor = database.query(DocumentoPago.class.getSimpleName(), allColumns, " codigoDocumentoPago = "+id,null,null,null,null);
		 if(cursor!=null && cursor.getCount()>0){
			 cursor.moveToFirst();
			 ent = cursorToEnt(cursor);
			 		 
		 }
		 cursor.close();
		 return ent;
	 }
	 
	 private DocumentoPago cursorToEnt(Cursor cursor) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    DocumentoPago ent = null;
		    if(cursor!=null && cursor.getCount()>0){
		    	ent = new DocumentoPago();
		    	ent.setCodigoDocumentoPago(cursor.getLong(0));
		    	try {
					ent.setFechaEmisionDocumentoPago(dateFormat.parse(cursor.getString(1)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaEmisionDocumentoPago(new Date(1900,1,1));
					
				}
		    	try {
					ent.setFechaVencimientoDocumentoPago(dateFormat.parse(cursor.getString(2)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaVencimientoDocumentoPago(new Date(1900,1,1));
				}
		    	ent.setImporteAmortizadoDocumentoPago(cursor.getDouble(3));
		    	ent.setImporteDescontadoDocumentoPago(cursor.getDouble(4));
		    	ent.setImporteIgvDocumentoPago(cursor.getDouble(5));
		    	ent.setImporteOriginalDocumentoPago(cursor.getDouble(6));
		    	ent.setImportePendienteDocumentoPago(cursor.getDouble(7));
		    	ent.setPlazoDocumentoPago(cursor.getInt(8));
		    	ent.setTipoDocumentoPago(cursor.getString(9));
		    	ent.setReferenciaDocumentoPago(cursor.getString(10));
		    	ent.setCodigoCliente(cursor.getLong(11));
		    	
		    }
		    
		    return ent;
	 }

}

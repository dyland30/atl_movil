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

import com.atl.atlmovil.entidades.Voucher;

@SuppressLint("SimpleDateFormat")
public class VoucherDAO {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	private String[] allColumns = { "codigoVoucher","bancoVoucher", "clienteVoucher","agenciaVoucher","estadoVoucher", 
			"fechaVoucher","importeVoucher", "medioVoucher", "terminalVoucher"};

	public VoucherDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
		
	}
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public List<Voucher> obtenerTodos(){
		 List<Voucher> ls = new ArrayList<Voucher>();
		 Cursor cursor = database.query(Voucher.class.getSimpleName(), allColumns, null,null, null,null,null);
		 cursor.moveToFirst();
		 while(!cursor.isAfterLast()){
			 Voucher ent = cursorToEnt(cursor);
			 ls.add(ent);
			 cursor.moveToNext();
		 }
		 cursor.close();
		 return ls;
	 }
	 
	 public void eliminar(Voucher ent){
		 long id = ent.getCodigoVoucher();
		 database.delete(Voucher.class.getSimpleName(),"codigoVoucher = "+id,null);
		 
	 }
	 
	 public Voucher crear(long codigoVoucher, int bancoVoucher, long clienteVoucher, String agenciaVoucher, Boolean estadoVoucher, 
				Date fechaVoucher, double importeVoucher,  String medioVoucher, String terminalVoucher){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Voucher ent = null;
		 ContentValues values = new ContentValues();
		 values.put("codigoVoucher", codigoVoucher);
		 values.put("bancoVoucher", bancoVoucher);
		 values.put("clienteVoucher", clienteVoucher);
		 values.put("agenciaVoucher", agenciaVoucher);
		 values.put("estadoVoucher", estadoVoucher);
		 values.put("fechaVoucher", dateFormat.format(fechaVoucher));
		 values.put("importeVoucher", importeVoucher);
		 values.put("medioVoucher", medioVoucher);
		 values.put("terminalVoucher", terminalVoucher);
		 
		 long insertId = database.insert(Voucher.class.getSimpleName(), null, values);
		 
		 ent = buscarPorID(insertId);
		
		 return ent;
	 }
	 
	 public Voucher actualizar(Voucher ent){
		 Voucher nuevo = null;
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 ContentValues values = new ContentValues();
		 values.put("codigoVoucher", ent.getCodigoVoucher());
		 values.put("bancoVoucher", ent.getBancoVoucher());
		 values.put("clienteVoucher", ent.getClienteVoucher());
		 values.put("agenciaVoucher", ent.getAgenciaVoucher());
		 values.put("estadoVoucher", ent.getEstadoVoucher());
		 values.put("fechaVoucher", dateFormat.format(ent.getFechaVoucher()));
		 values.put("importeVoucher", ent.getImporteVoucher());
		 values.put("medioVoucher", ent.getMedioVoucher());
		 values.put("terminalVoucher", ent.getTerminalVoucher());
		 
		 database.update(Voucher.class.getSimpleName(), values, " codigoVoucher = "+ent.getCodigoVoucher(), null);
		 nuevo=buscarPorID(ent.getCodigoVoucher());
		 
		 return nuevo;
	 }
	 public Voucher buscarPorID(long id){
		 Voucher ent = null;
		 Cursor cursor = database.query(Voucher.class.getSimpleName(), allColumns, " codigoVoucher = "+id,null,null,null,null);
		 cursor.moveToFirst();
		 ent = cursorToEnt(cursor);
		 cursor.close();		 
		 return ent;
	 }
	 
	 private Voucher cursorToEnt(Cursor cursor) {
		    Voucher ent = null;
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    if(cursor!=null ){
		    	ent = new Voucher();
		    	ent.setCodigoVoucher(cursor.getInt(0));
		    	ent.setBancoVoucher(cursor.getInt(1));
		    	ent.setClienteVoucher(cursor.getLong(2));
		    	ent.setAgenciaVoucher(cursor.getString(3));
		    	Boolean estado = cursor.getInt(4) == 1 ? true : false;
		    	ent.setEstadoVoucher(estado);
		    	try {
					ent.setFechaVoucher(dateFormat.parse(cursor.getString(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ent.setFechaVoucher(new Date(1900,1,1));
				}
		    	
		    	ent.setImporteVoucher(cursor.getDouble(6));
		    	ent.setMedioVoucher(cursor.getString(7));
		    	ent.setTerminalVoucher(cursor.getString(8));
		    }
		    
		    return ent;
	 }
}

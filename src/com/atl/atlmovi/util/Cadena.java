package com.atl.atlmovi.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public  class Cadena {
	
	public static String formatearNumero(String patron, Double numero){
        String output;
        if(patron!=null && patron.length()>0){
            
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator('.');
            otherSymbols.setGroupingSeparator(',');
            DecimalFormat myFormatter = new DecimalFormat(patron,otherSymbols);
            
            
            
            output = myFormatter.format(numero);
        } else{
            output = numero.toString();
        }
        return output;
    
    }

}

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
	public static String join(String[] s, String glue)
	{
	  int k=s.length;
	  if (k==0)
	    return null;
	  StringBuilder out=new StringBuilder();
	  out.append(s[0]);
	  for (int x=1;x<k;++x)
	    out.append(glue).append(s[x]);
	  return out.toString();
	}

}

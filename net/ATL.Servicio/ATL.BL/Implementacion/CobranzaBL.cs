using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.BL.Contrato;
using ATL.Entidad;
using ATL.DAO.Implementacion;
using ATL.DAO.Contrato;

namespace ATL.BL.Implementacion
{
    public class CobranzaBL : ICobranzaBL
    {

        public List<Cobranza> obtenerCobranzas()
        {
            
            ICobranzaDAO dao = new CobranzaDAO();
            IAmortizacionBL amBL = new AmortizacionBL();
            List<Cobranza> cobranzas = EntidadesHelper.ConvertirAEntidades<Cobranza>(dao.obtenerCobranzas());
            List<Cobranza> ls = new List<Cobranza>(); 
            foreach(Cobranza cob in cobranzas){
                //buscar amortizaciones
                List<Amortizacion> amortizaciones = amBL.obtenerAmortizaciones(cob.codigoCobranza);
                cob.amortizaciones = amortizaciones;
                ls.Add(cob);
            }
            return ls;

        }

        public Cobranza registrarCobranza(Cobranza cob)
        {
            if(cob!=null){
                if(cob.amortizaciones!=null && cob.amortizaciones.Count>0){

                    ICobranzaDAO cobDao = new CobranzaDAO();
                    IAmortizacionBL amBL = new AmortizacionBL();
                    if (cob.codigoCobranza > 0)
                    {
                        // actualizar
                        cobDao.actualizarCobranza(cob);
                    }
                    else { 
                        // crear
                        long codCob = cobDao.guardarCobranza(cob);
                        cob.codigoCobranza = codCob;
                    }
                    List<Amortizacion> amortizaciones = new List<Amortizacion>();
                    foreach(Amortizacion am in cob.amortizaciones){

                        am.codigoCobranza = cob.codigoCobranza;
                        if (am.codigoAmortizacion > 0)
                        {
                            // actualizar amortizacion
                            amBL.actualizarAmortizacion(am);
                        }
                        else { 
                            // crear amortizacion
                           am.codigoAmortizacion = amBL.guardarAmortizacion(am);
                        }

                        amortizaciones.Add(am);
                    }
                    cob.amortizaciones = amortizaciones;
                }
         
            }

            return cob;
        }
    }
}

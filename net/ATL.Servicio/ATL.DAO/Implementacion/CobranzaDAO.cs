using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;
using System.Data;
using System.Globalization;

namespace ATL.DAO.Implementacion
{
    public class CobranzaDAO : ICobranzaDAO
    {

        public System.Data.IDataReader obtenerCobranzas()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_cobranza_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }

        public long  guardarCobranza(Entidad.Cobranza cob)
        {
            long codigoCobranza = 0;
            DateTime fecha = DateTime.ParseExact(cob.strfechaCobranza, "yyyy-MM-dd HH:mm", CultureInfo.InvariantCulture);

            DBHelper helper = DBHelper.GetInstance();

         
            DataTable dt = helper.CargarDataTableProc("usp_cobranza_ins", fecha,cob.importeCobranza,cob.codigoMedioPago,cob.codigoMovil, cob.codigoVisita);

            if (dt == null)
            {
                throw helper.getErrorReal();
            }
            DataRow row = dt.Rows[0];
            string strCod = row["codigoCobranza"].ToString();
            codigoCobranza = Int64.Parse(strCod);
            return codigoCobranza;
 
        }

        public void actualizarCobranza(Entidad.Cobranza cob)
        {
            DateTime fecha = DateTime.ParseExact(cob.strfechaCobranza, "yyyy-MM-dd HH:mm", CultureInfo.InvariantCulture);
            DBHelper helper = DBHelper.GetInstance();
            int resp = helper.Ejecute("usp_cobranza_upd",cob.codigoCobranza, fecha, cob.importeCobranza, cob.codigoMedioPago, cob.codigoMovil);
            if(resp==-2){
                throw helper.getErrorReal();
            }


        }
    }
}

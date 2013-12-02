using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;
using System.Data;

namespace ATL.DAO.Implementacion
{
    public class AmortizacionDAO : IAmortizacionDAO
    {

        public System.Data.IDataReader obtenerAmortizaciones(long codigoCobranza)
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_amortizacion_sel", codigoCobranza);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }

        public long guardarAmortizacion(Entidad.Amortizacion am)
        {
            long codigoAmortizacion = 0;
            DBHelper helper = DBHelper.GetInstance();
            DataTable dt = helper.CargarDataTableProc("usp_amortizacion_ins", am.codigoCobranza, am.importeAmortizacion, am.anotacionAmortizacion, am.codigoDocumentoPago );

            if (dt == null)
            {
                throw helper.getErrorReal();
            }
            DataRow row = dt.Rows[0];
            string strCod = row["codigoAmortizacion"].ToString();
            codigoAmortizacion = Int64.Parse(strCod);
            return codigoAmortizacion;
        }

        public void actualizarAmortizacion(Entidad.Amortizacion am)
        {
            DBHelper helper = DBHelper.GetInstance();
            DataTable dt = helper.CargarDataTableProc("usp_amortizacion_update", am.codigoAmortizacion, am.codigoCobranza, am.importeAmortizacion,am.codigoDocumentoPago, am.anotacionAmortizacion);

            if (dt == null)
            {
                throw helper.getErrorReal();
            }
        }

        public System.Data.IDataReader buscarPorID(long codigoCobranza, long codigoAmortizacion)
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_amortizacion_sel_codigo", codigoCobranza, codigoAmortizacion);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }
    }
}

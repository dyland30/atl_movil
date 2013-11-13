using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;
using System.Data;

namespace ATL.DAO.Implementacion
{
    public class DocumentoPagoDAO : IDocumentoPagoDAO
    {

        public IDataReader obtenerDocumentos()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_documento_pago_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }
    }
}

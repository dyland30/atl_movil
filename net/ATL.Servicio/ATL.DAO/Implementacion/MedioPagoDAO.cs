using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;
using System.Data;

namespace ATL.DAO.Implementacion
{
    public class MedioPagoDAO : IMedioPagoDAO
    {

        public IDataReader obtenerMedioPagos()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_medio_pago_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace ATL.DAO
{
    public class FormaPagoDAO
    {
        public static IDataReader obtenerFormaPagos()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_forma_pago_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }
    }
}

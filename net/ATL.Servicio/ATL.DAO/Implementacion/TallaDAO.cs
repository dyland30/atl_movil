using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.DAO.Contrato;

namespace ATL.DAO
{
    public class TallaDAO : ITallaDAO
    {
        public  IDataReader obtenerTallas()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_talla_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }


        public IDataReader buscarPorID(long codigoProducto, long numeroTalla)
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_talla_sel_por_id", codigoProducto, numeroTalla);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }
    }
}

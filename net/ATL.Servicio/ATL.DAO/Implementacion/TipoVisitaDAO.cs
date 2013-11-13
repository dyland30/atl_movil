using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.DAO.Contrato;

namespace ATL.DAO
{
    public class TipoVisitaDAO : ITipoVisitaDAO
    {
        public IDataReader obtenerTipoVisitas()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_tipo_visita_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }

    }
}

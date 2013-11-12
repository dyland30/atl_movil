using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace ATL.DAO
{
    public class TipoDocumentoDAO
    {

        public static IDataReader obtenerTipoDocumentos()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_tipodocumento_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }
    }
}

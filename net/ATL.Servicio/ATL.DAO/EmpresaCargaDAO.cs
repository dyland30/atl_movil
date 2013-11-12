using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace ATL.DAO
{
    public class EmpresaCargaDAO
    {
        public static IDataReader obtenerEmpresaCargas()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_empresa_carga_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }
    }
}

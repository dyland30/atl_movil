using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.DAO.Contrato;

namespace ATL.DAO
{
    public class ProductoDAO : IProductoDAO
    {
        public  IDataReader obtenerProductos()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_producto_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }

    }
}

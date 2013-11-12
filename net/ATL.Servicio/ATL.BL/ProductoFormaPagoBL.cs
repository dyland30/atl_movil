using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class ProductoFormaPagoBL
    {
        public static List<ProductoFormaPago> obtenerProductoFormaPagos()
        {
            List<ProductoFormaPago> ls;
            ls = EntidadesHelper.ConvertirAEntidades<ProductoFormaPago>(ProductoFormaPagoDAO.obtenerProductoFormaPagos());

            return ls;

        }
    }
}

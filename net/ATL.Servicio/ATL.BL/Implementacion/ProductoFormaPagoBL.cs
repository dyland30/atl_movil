using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;
using ATL.BL.Contrato;
using ATL.DAO.Contrato;

namespace ATL.BL
{
    public class ProductoFormaPagoBL : IProductoFormaPagoBL

    {
        public List<ProductoFormaPago> obtenerProductoFormaPagos()
        {
            List<ProductoFormaPago> ls;
            IProductoFormaPagoDAO productoFormaPagoDAO = new ProductoFormaPagoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<ProductoFormaPago>(productoFormaPagoDAO.obtenerProductoFormaPagos());

            return ls;

        }
    }
}

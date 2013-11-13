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
    public class ProductoBL : IProductoBL
    {
        public List<Producto> obtenerProductos()
        {
            List<Producto> ls;
            IProductoDAO productoDAO = new ProductoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Producto>(productoDAO.obtenerProductos());

            return ls;

        }
    }
}

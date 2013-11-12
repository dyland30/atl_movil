using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class ProductoBL
    {
        public static List<Producto> obtenerProductos()
        {
            List<Producto> ls;
            ls = EntidadesHelper.ConvertirAEntidades<Producto>(ProductoDAO.obtenerProductos());

            return ls;

        }
    }
}

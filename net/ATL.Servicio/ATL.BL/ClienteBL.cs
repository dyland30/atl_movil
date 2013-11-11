using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class ClienteBL
    {
        public static List<Cliente> obtenerClientes()
        {
            List<Cliente> ls;
            ls = EntidadesHelper.ConvertirAEntidades<Cliente>(ClienteDAO.obtenerClientes());

            return ls;

        }
    }
}

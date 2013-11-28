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
    public class ClienteBL : IClienteBL
    {
        
        public List<Cliente> obtenerClientes()
        {
            List<Cliente> ls;
            IClienteDAO clienteDAO = new ClienteDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Cliente>(clienteDAO.obtenerClientes());
            
            return ls;
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;

namespace ATL.DAO.Implementacion
{
    public class TallaPedidoDAO : ITallaPedidoDAO
    {
        public Entidad.TallaPedido guardarTallaPedido(Entidad.TallaPedido tallaPedido)
        {
            throw new NotImplementedException();
        }

        public Entidad.TallaPedido buscarPorID(long codigoPedido, long codigoProducto, long numeroTalla)
        {
            throw new NotImplementedException();
        }

        public List<Entidad.TallaPedido> obtenerTallaPedidos(long tallapedido)
        {
            throw new NotImplementedException();
        }
    }
}

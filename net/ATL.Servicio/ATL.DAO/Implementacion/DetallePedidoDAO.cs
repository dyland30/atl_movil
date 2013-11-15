using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;

namespace ATL.DAO.Implementacion
{
    public class DetallePedidoDAO : IDetallePedidoDAO 
    {
        public void guardarDetallePedido(Entidad.DetallePedido detalle)
        {
            throw new NotImplementedException();
        }

        public Entidad.DetallePedido buscarPorID(long codigoPedido, long codigoProducto)
        {
            throw new NotImplementedException();
        }

        public List<Entidad.DetallePedido> obtenerDetallesdePedido(long codigoPedido)
        {
            throw new NotImplementedException();
        }
    }
}

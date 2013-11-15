using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.DAO.Contrato
{
    public interface IDetallePedidoDAO
    {
         void guardarDetallePedido(DetallePedido detalle);
         DetallePedido buscarPorID(long codigoPedido, long codigoProducto);
         List<DetallePedido> obtenerDetallesdePedido(long codigoPedido);
    }
}

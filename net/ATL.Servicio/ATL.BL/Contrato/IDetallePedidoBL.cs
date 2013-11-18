using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.BL.Contrato
{
    public interface IDetallePedidoBL
    {
        void guardarDetallePedido(DetallePedido detalle);
        void actualizarDetallePedido(DetallePedido detalle);
        DetallePedido buscarPorID(long codigoPedido, long codigoProducto);
        List<DetallePedido> obtenerDetallesdePedido(long codigoPedido);

    }
}

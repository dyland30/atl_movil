using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using System.Data;

namespace ATL.DAO.Contrato
{
    public interface IDetallePedidoDAO
    {
         void guardarDetallePedido(DetallePedido detalle);
         void actualizarDetallePedido(DetallePedido detalle);
         IDataReader buscarPorID(long codigoPedido, long codigoProducto);
         IDataReader obtenerDetallesdePedido(long codigoPedido);

    }
}

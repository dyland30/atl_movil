using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.BL.Contrato
{
    public interface ITallaPedidoBL
    {
        void guardarTallaPedido(TallaPedido tallaPedido);
        void actualizarTallaPedido(TallaPedido tallaPedido);
        TallaPedido buscarPorID(long codigoPedido, long codigoProducto, int numeroTalla);
        List<TallaPedido> obtenerTallasPedido(long codigoPedido, long codigoProducto);
    }
}

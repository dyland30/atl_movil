using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.DAO.Contrato
{
    public interface ITallaPedidoDAO
    {
        TallaPedido guardarTallaPedido(TallaPedido tallaPedido);
        TallaPedido buscarPorID(long codigoPedido, long codigoProducto, long numeroTalla);
        List<TallaPedido> obtenerTallaPedidos(long tallapedido);


    }
}

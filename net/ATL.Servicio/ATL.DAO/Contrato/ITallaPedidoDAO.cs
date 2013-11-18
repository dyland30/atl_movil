using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using System.Data;

namespace ATL.DAO.Contrato
{
    public interface ITallaPedidoDAO
    {
        void guardarTallaPedido(TallaPedido tallaPedido);
        void actualizarTallaPedido(TallaPedido tallaPedido);
        IDataReader buscarPorID(long codigoPedido, long codigoProducto, int numeroTalla);
        IDataReader obtenerTallasPedido(long codigoPedido, long codigoProducto);


    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using System.Data;

namespace ATL.DAO.Contrato
{
    public interface IPedidoDAO
    {
         long guardarPedido(Pedido pedido);
         IDataReader buscarPorCodigo(long codigoPedido);
         IDataReader obtenerPedidos();
         void actualizarPedido(Pedido pedido);
         void gestionarRetenciones();

    }
}

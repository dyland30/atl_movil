using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.BL.Contrato
{
    public interface IPedidoBL
    {
        Pedido registrarPedido(Pedido pedido);
        List<Pedido> obtenerPedidos();
        Pedido buscarPorCodigo(long codigoPedido);
        void gestionarRetenciones();
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.BL.Contrato;
using ATL.Entidad;
using ATL.DAO.Contrato;
using ATL.DAO.Implementacion;

namespace ATL.BL.Implementacion
{
    public class PedidoBL : IPedidoBL
    {
        public Pedido registrarPedido(Pedido pedido)
        {
            //verificar que el pedido tenga detalles

            Pedido nuevo = null;
            Boolean flgRetenido = false;
            ITallaBL tallaBL = new TallaBL();
            if(pedido!=null){
                if(pedido.detalles!=null && pedido.detalles.Count>0){
                    // validar stock de detalles, si no hay suficiente stock establecer pedido  como retenido
                    // guardar pedido obtener codigo
                    
                    //agregar detalles al pedido
                    foreach(DetallePedido det in pedido.detalles){
                        if(det.tallas!=null && det.tallas.Count>0){
                            foreach(TallaPedido tp in det.tallas){
                                Talla t = tallaBL.buscarPorID(tp.codigoProducto, tp.numeroTalla);
                                if(tp.cantidad>t.stockDisponibleTalla){
                                    flgRetenido = true;
                                    
                                } 
                            }

                        }
                    }

                    pedido.estaRetenidoPedido = flgRetenido;

                    // actualizar pedido

                }
            
            }

            return pedido;
        }

        public List<Pedido> obtenerPedidos()
        {
            throw new NotImplementedException();
        }

        public Pedido buscarPorCodigo(long codigoPedido)
        {
            throw new NotImplementedException();
        }


        public void gestionarRetenciones()
        {
            IPedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.gestionarRetenciones();

        }
    }
}

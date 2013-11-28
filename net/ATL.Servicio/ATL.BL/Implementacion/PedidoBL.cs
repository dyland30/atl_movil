using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.BL.Contrato;
using ATL.Entidad;
using ATL.DAO.Contrato;
using ATL.DAO.Implementacion;
using System.Data;

namespace ATL.BL.Implementacion
{
    public class PedidoBL : IPedidoBL
    {
        public Pedido registrarPedido(Pedido pedido)
        {
            //verificar que el pedido tenga detalles
            long codigoPedido =0;
            Pedido nuevo = null;
            Boolean flgRetenido = false;
            ITallaBL tallaBL = new TallaBL();
            IPedidoDAO pedidoDAO = new PedidoDAO();
            IDetallePedidoBL detallePedidoBL = new DetallePedidoBL();
            ITallaPedidoBL tallaPedidoBL = new TallaPedidoBL();
            if(pedido!=null){

                try {

                    if (pedido.detalles != null && pedido.detalles.Count > 0)
                    {
                        // guardar pedido, en caso que el pedido venga sin codigo de pedido
                        // si tiene codigo de pedido se debe actualizar los flags y el importe
                        pedido.estaRetenidoPedido = true;
                        pedido.faltaStockPedido = true;
                        pedido.faltaImportePedido = true;

                        if (pedido.codigoPedido > 0)
                        {
                            // actualizar
                            codigoPedido = pedido.codigoPedido;
                            pedidoDAO.actualizarPedido(pedido);
                        }
                        else { 
                            //crear
                            codigoPedido = pedidoDAO.guardarPedido(pedido);
                        }
                        //agregar detalles al pedido
                        foreach (DetallePedido det in pedido.detalles)
                        {
                            if (det.tallas != null && det.tallas.Count > 0)
                            {
                                 det.codigoPedido = codigoPedido;
                                //si el detalle no existe crearlo, caso contrario modificarlo
                                //obtener detalle 
                                 DetallePedido detNuevo = detallePedidoBL.buscarPorID(det.codigoPedido, det.codigoProducto);
                                 if (detNuevo == null)
                                 {
                                     //guardar detalle
                                     detallePedidoBL.guardarDetallePedido(det);
                                 }
                                 else { 
                                    // actualizar detalle
                                     detallePedidoBL.actualizarDetallePedido(det);
                                 }

                                foreach (TallaPedido tp in det.tallas)
                                {
                                    // si la talla ya existe, modificar su cantidad, caso contrario crearla
                                    tp.codigoPedido = codigoPedido;
                                    TallaPedido tpNuevo = tallaPedidoBL.buscarPorID(tp.codigoPedido, tp.codigoProducto, tp.numeroTalla);
                                    if (tpNuevo == null)
                                    {
                                        //crear
                                        tallaPedidoBL.guardarTallaPedido(tp);
                                    }
                                    else { 
                                        //actualizar
                                        tallaPedidoBL.actualizarTallaPedido(tp);
                                    }

                                }

                            }
                        }

                        //ejecutar proceso de retencion

                        pedido.estaRetenidoPedido = flgRetenido;
                        try {
                            gestionarRetenciones();
                        }catch(Exception ex){
                            // no hacer nada si se cae en el proceso de gestionar retenciones
                        }
                        // buscar pedido y verificar estado
                        nuevo = buscarPorCodigo(codigoPedido);

                    }
                
                } catch(Exception ex){
                    return null;
                    
                }
            }

            return nuevo;
        }

        public List<Pedido> obtenerPedidos()
        {
            List<Pedido> ls;
            IPedidoDAO pedidoDao = new PedidoDAO();
            IDetallePedidoBL detallePedidoBL = new DetallePedidoBL();
            ITallaPedidoBL tallaPedidoBL = new TallaPedidoBL();

            ls = EntidadesHelper.ConvertirAEntidades<Pedido>(pedidoDao.obtenerPedidos());
            // cargar detalles
            List<Pedido> nuevaLista= new List<Pedido>();
            foreach(Pedido p in ls){
                // crear detalles
                List<DetallePedido> detalles = detallePedidoBL.obtenerDetallesdePedido(p.codigoPedido);
                List<DetallePedido> nuevosDetalles = new List<DetallePedido>();
                foreach(DetallePedido det in detalles){
                    //obtener tallas
                    List<TallaPedido> tallas = tallaPedidoBL.obtenerTallasPedido(det.codigoPedido, det.codigoProducto);
                    det.tallas = tallas;
                    nuevosDetalles.Add(det);
                }
                p.detalles = detalles;

                nuevaLista.Add(p);
            }
            //cargar tallas

            return nuevaLista;
        }

        public Pedido buscarPorCodigo(long codigoPedido)
        {
            Pedido p = null;
            IPedidoDAO pedidoDao = new PedidoDAO();

            IDataReader reader = pedidoDao.buscarPorCodigo(codigoPedido);
            List<Pedido> ls = EntidadesHelper.ConvertirAEntidades<Pedido>(reader);
            if (ls != null && ls.Count > 0) {
                p = ls.First();
                if(p.codigoPedido<=0){
                    p = null;
                }
            
            }
            return p;

        }


        public void gestionarRetenciones()
        {
            IPedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.gestionarRetenciones();

        }
    }
}

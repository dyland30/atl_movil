using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.BL.Contrato;
using ATL.DAO.Contrato;
using ATL.DAO.Implementacion;
using ATL.Entidad;
using System.Data;

namespace ATL.BL.Implementacion
{
    public class DetallePedidoBL : IDetallePedidoBL
    {


        public void guardarDetallePedido(Entidad.DetallePedido detalle)
        {
            IDetallePedidoDAO detalleDAO = new DetallePedidoDAO();
            detalleDAO.guardarDetallePedido(detalle);

        }

        public void actualizarDetallePedido(Entidad.DetallePedido detalle)
        {
            IDetallePedidoDAO detalleDAO = new DetallePedidoDAO();
            detalleDAO.actualizarDetallePedido(detalle);
        }

        public DetallePedido buscarPorID(long codigoPedido, long codigoProducto)
        {
            DetallePedido det = null;
            IDetallePedidoDAO detalleDAO = new DetallePedidoDAO();
            IDataReader reader = detalleDAO.buscarPorID(codigoPedido, codigoProducto);
            List<DetallePedido> ls = EntidadesHelper.ConvertirAEntidades<DetallePedido>(reader);
            if(ls!=null && ls.Count>0){

                det = ls.First();
                if (det.codigoPedido==null || det.codigoPedido <= 0)
                {
                    det = null;
                }
            }

            return det;

        }

        public List<DetallePedido> obtenerDetallesdePedido(long codigoPedido)
        {
            List<DetallePedido> ls;
            IDetallePedidoDAO detalleDAO = new DetallePedidoDAO();
            IDataReader reader = detalleDAO.obtenerDetallesdePedido(codigoPedido);
            ls = EntidadesHelper.ConvertirAEntidades<DetallePedido>(reader);
            return ls;
        }
    }

}

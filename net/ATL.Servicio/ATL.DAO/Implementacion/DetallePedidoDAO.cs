using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;
using System.Data;

namespace ATL.DAO.Implementacion
{
    public class DetallePedidoDAO : IDetallePedidoDAO 
    {
        public void guardarDetallePedido(Entidad.DetallePedido detalle)
        {

            DBHelper helper = DBHelper.GetInstance();

            int resp = helper.Ejecute("usp_detalle_pedido_ins", detalle.codigoPedido, detalle.codigoProducto, detalle.precioUnitario);
            if (resp == -2)
            {
                throw helper.getErrorReal();
            }       

        }

        public IDataReader buscarPorID(long codigoPedido, long codigoProducto)
        {

            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_detalle_pedido_sel_codigo",codigoPedido,codigoProducto);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;



        }

        public IDataReader obtenerDetallesdePedido(long codigoPedido)
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_detalle_pedido_sel", codigoPedido);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }


        public void actualizarDetallePedido(Entidad.DetallePedido detalle)
        {
            DBHelper helper = DBHelper.GetInstance();

            int resp = helper.Ejecute("usp_detalle_pedido_update", detalle.codigoPedido, detalle.codigoProducto, detalle.precioUnitario);
            if (resp == -2)
            {
                throw helper.getErrorReal();
            }   
        }
    }
}

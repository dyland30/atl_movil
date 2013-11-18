using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;
using System.Data;

namespace ATL.DAO.Implementacion
{
    public class TallaPedidoDAO : ITallaPedidoDAO
    {

        public void guardarTallaPedido(Entidad.TallaPedido t)
        {
            DBHelper helper = DBHelper.GetInstance();

            int resp = helper.Ejecute("usp_talla_pedido_ins", t.codigoPedido, t.codigoProducto, t.numeroTalla, t.cantidad, 0);
            if (resp == -2)
            {
                throw helper.getErrorReal();
            }       



        }

        public void actualizarTallaPedido(Entidad.TallaPedido t)
        {
            DBHelper helper = DBHelper.GetInstance();

            int resp = helper.Ejecute("usp_talla_pedido_update", t.codigoPedido, t.codigoProducto, t.numeroTalla, t.cantidad);
            if (resp == -2)
            {
                throw helper.getErrorReal();
            }      
        }

        public System.Data.IDataReader buscarPorID(long codigoPedido, long codigoProducto, int numeroTalla)
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_talla_pedido_sel_codigo", codigoPedido, codigoProducto, numeroTalla);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }

        public System.Data.IDataReader obtenerTallasPedido(long codigoPedido, long codigoProducto)
        {

            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_talla_pedido_sel", codigoPedido, codigoProducto);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;


        }
    }
}

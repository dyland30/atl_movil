using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.DAO.Contrato;
using System.Globalization;
using System.Data;

namespace ATL.DAO.Implementacion
{
    public class PedidoDAO : IPedidoDAO
    {

        public long guardarPedido(Entidad.Pedido p)
        {
            long codigoPedido=0;
            DateTime fecha =  DateTime.ParseExact(p.strfechaIngresoPedido,"yyyy-MM-dd HH:mm",CultureInfo.InvariantCulture);
            
            DBHelper helper = DBHelper.GetInstance();
            DataTable dt = helper.CargarDataTableProc("USP_PEDIDO_INS",fecha, p.importePedido, p.codigoFormaPago, p.instruccionesEspeciales, 
                p.codigoEmpresaCarga,0.0, p.aceptaRetencionPedido,p.estaRetenidoPedido,0,0,p.estadoPedido,p.codigoVisita);

            if (dt != null)
            {
                throw helper.getErrorReal();
            }


            return codigoPedido;
        }
        public System.Data.IDataReader buscarPorCodigo(long codigoPedido)
        {

            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_pedido_sel_por_id", codigoPedido);
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;
        }

        public System.Data.IDataReader obtenerPedidos()
        {
            throw new NotImplementedException();
        }

        public void actualizarPedido(Entidad.Pedido pedido)
        {
            throw new NotImplementedException();
        }


        public void gestionarRetenciones()
        {
            DBHelper helper = DBHelper.GetInstance();
            int resp = helper.Ejecute("usp_LEVANTAR_RETENCIONES");
            if (resp == -2)
            {
                throw helper.getErrorReal();
            }
            
        }
    }
}

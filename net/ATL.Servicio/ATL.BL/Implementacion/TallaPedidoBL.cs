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
    public class TallaPedidoBL : ITallaPedidoBL
    {

        public void guardarTallaPedido(Entidad.TallaPedido tallaPedido)
        {
            ITallaPedidoDAO tallaPedidoDAO = new TallaPedidoDAO();
            tallaPedidoDAO.guardarTallaPedido(tallaPedido);

        }

        public void actualizarTallaPedido(Entidad.TallaPedido tallaPedido)
        {
            ITallaPedidoDAO tallaPedidoDAO = new TallaPedidoDAO();
            tallaPedidoDAO.actualizarTallaPedido(tallaPedido);
        }

        public Entidad.TallaPedido buscarPorID(long codigoPedido, long codigoProducto, int numeroTalla)
        {

            TallaPedido t = null;
            ITallaPedidoDAO tallaPedidoDao = new TallaPedidoDAO();
            IDataReader reader = tallaPedidoDao.buscarPorID(codigoPedido, codigoProducto, numeroTalla);
            List<TallaPedido> ls = EntidadesHelper.ConvertirAEntidades<TallaPedido>(reader);
            if (ls != null && ls.Count > 0)
            {

                t = ls.First();
                if (t.codigoProducto == null || t.codigoProducto <= 0)
                {
                    t = null;
                }
            }

            return t;

        }

        public List<Entidad.TallaPedido> obtenerTallasPedido(long codigoPedido, long codigoProducto)
        {

            List<TallaPedido> ls;
            ITallaPedidoDAO tallaPedidoDao = new TallaPedidoDAO();
            IDataReader reader = tallaPedidoDao.obtenerTallasPedido(codigoPedido, codigoProducto);
            ls = EntidadesHelper.ConvertirAEntidades<TallaPedido>(reader);
            return ls;

        }
    }
}

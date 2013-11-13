using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;
using ATL.BL.Contrato;
using ATL.DAO.Contrato;

namespace ATL.BL
{
    public class FormaPagoBL : IFormaPagoBL
    {
        public  List<FormaPago> obtenerFormaPagos()
        {
            List<FormaPago> ls;
            IFormaPagoDAO formaPagoDAO = new FormaPagoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<FormaPago>(formaPagoDAO.obtenerFormaPagos());

            return ls;

        }
    }
}

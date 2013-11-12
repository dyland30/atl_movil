using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class FormaPagoBL
    {
        public static List<FormaPago> obtenerFormaPagos()
        {
            List<FormaPago> ls;
            ls = EntidadesHelper.ConvertirAEntidades<FormaPago>(FormaPagoDAO.obtenerFormaPagos());

            return ls;

        }
    }
}

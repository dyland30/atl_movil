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
    public class MedioPagoBL : IMedioPagoBL
    {

        public List<MedioPago> obtenerMedioPagos()
        {
            List<MedioPago> ls;
            IMedioPagoDAO medioPagoDAO = new MedioPagoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<MedioPago>(medioPagoDAO.obtenerMedioPagos());

            return ls;
        }
    }
}

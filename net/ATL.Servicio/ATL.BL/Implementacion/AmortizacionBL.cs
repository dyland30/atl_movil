using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.BL.Contrato;
using ATL.DAO.Contrato;
using ATL.DAO.Implementacion;

namespace ATL.BL.Implementacion
{
    public class AmortizacionBL : IAmortizacionBL
    {

        public List<Entidad.Amortizacion> obtenerAmortizaciones(long codigoCobranza)
        {
            IAmortizacionDAO dao = new AmortizacionDAO();
            return EntidadesHelper.ConvertirAEntidades<Entidad.Amortizacion>(dao.obtenerAmortizaciones(codigoCobranza));

        }

        public long guardarAmortizacion(Entidad.Amortizacion am)
        {
            IAmortizacionDAO dao = new AmortizacionDAO();
            return dao.guardarAmortizacion(am);
        }

        public void actualizarAmortizacion(Entidad.Amortizacion am)
        {
            IAmortizacionDAO dao = new AmortizacionDAO();
            dao.actualizarAmortizacion(am);
        }

        public Entidad.Amortizacion buscarPorID(long codigoCobranza, long codigoAmortizacion)
        {
            IAmortizacionDAO dao = new AmortizacionDAO();
            return EntidadesHelper.ConvertirAEntidades<Entidad.Amortizacion>(dao.buscarPorID(codigoCobranza,codigoAmortizacion)).First();

        }
    }
}

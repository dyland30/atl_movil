using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.BL.Contrato
{
    public interface IAmortizacionBL
    {
        List<Amortizacion> obtenerAmortizaciones(long codigoCobranza);
        long guardarAmortizacion(Amortizacion am);
        void actualizarAmortizacion(Amortizacion am);
        Amortizacion buscarPorID(long codigoCobranza, long codigoAmortizacion);
    }
}

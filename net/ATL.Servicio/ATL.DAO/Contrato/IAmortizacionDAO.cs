using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.Entidad;

namespace ATL.DAO.Contrato
{
    public interface IAmortizacionDAO
    {
        IDataReader obtenerAmortizaciones(long codigoCobranza);
        long guardarAmortizacion(Amortizacion am);
        void actualizarAmortizacion(Amortizacion am);
        IDataReader buscarPorID(long codigoCobranza, long codigoAmortizacion);

    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.Entidad;

namespace ATL.DAO.Contrato
{
    public interface ICobranzaDAO
    {
        IDataReader obtenerCobranzas();
        long guardarCobranza(Cobranza cob);
        void actualizarCobranza(Cobranza cob);

    }
}

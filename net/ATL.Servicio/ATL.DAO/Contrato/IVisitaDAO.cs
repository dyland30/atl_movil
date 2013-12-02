using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.Entidad;

namespace ATL.DAO.Contrato
{
    public interface IVisitaDAO
    {
        IDataReader obtenerVisitas();
        void actualizarEstadoVisita(Visita vi);
    }
}

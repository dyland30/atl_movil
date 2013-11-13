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
    public class EstadoVisitaBL :IEstadoVisitaBL
    {
        public  List<EstadoVisita> obtenerEstadoVisitas()
        {
            List<EstadoVisita> ls;
            IEstadoVisitaDAO estadoVisitaDAO = new EstadoVisitaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<EstadoVisita>(estadoVisitaDAO.obtenerEstadoVisitas());
            return ls;

        }
    }
}

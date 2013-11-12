using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class EstadoVisitaBL
    {
        public static List<EstadoVisita> obtenerEstadoVisitas()
        {
            List<EstadoVisita> ls;
            ls = EntidadesHelper.ConvertirAEntidades<EstadoVisita>(EstadoVisitaDAO.obtenerEstadoVisitas());
            return ls;

        }
    }
}

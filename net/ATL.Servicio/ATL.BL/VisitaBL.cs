using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class VisitaBL
    {
        public static List<Visita> obtenerVisitas()
        {
            List<Visita> ls;
            ls = EntidadesHelper.ConvertirAEntidades<Visita>(VisitaDAO.obtenerVisitas());
            return ls;

        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class TipoVisitaBL
    {
        public static List<TipoVisita> obtenerTipoVisitas()
        {
            List<TipoVisita> ls;
            ls = EntidadesHelper.ConvertirAEntidades<TipoVisita>(TipoVisitaDAO.obtenerTipoVisitas());
            return ls;

        }
    }
}

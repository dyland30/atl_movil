using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class GrupoBL
    {
        public static List<Grupo> obtenerGrupos()
        {
            List<Grupo> ls;
            ls = EntidadesHelper.ConvertirAEntidades<Grupo>(GrupoDAO.obtenerGrupos());

            return ls;

        }
    }
}

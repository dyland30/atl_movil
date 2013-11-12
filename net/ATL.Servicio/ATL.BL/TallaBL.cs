using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class TallaBL
    {
        public static List<Talla> obtenerTallas()
        {
            List<Talla> ls;
            ls = EntidadesHelper.ConvertirAEntidades<Talla>(TallaDAO.obtenerTallas());

            return ls;

        }
    }
}

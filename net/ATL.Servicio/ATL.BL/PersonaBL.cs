using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class PersonaBL
    {
        public static List<Persona> obtenerPersonas()
        {
            List<Persona> ls;
            ls = EntidadesHelper.ConvertirAEntidades<Persona>(PersonaDAO.obtenerPersonas());

            return ls;

        }
    }
}

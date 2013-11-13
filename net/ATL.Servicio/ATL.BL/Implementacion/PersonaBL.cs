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
    public class PersonaBL : IPersonaBL
    {
        public  List<Persona> obtenerPersonas()
        {
            List<Persona> ls;
            IPersonaDAO personaDAO = new PersonaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Persona>(personaDAO.obtenerPersonas());

            return ls;

        }
    }
}

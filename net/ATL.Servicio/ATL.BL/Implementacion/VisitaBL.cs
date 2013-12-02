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
    public class VisitaBL : IVisitaBL
    {
        public List<Visita> obtenerVisitas()
        {
            List<Visita> ls;
            IVisitaDAO visitaDAO = new VisitaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Visita>(visitaDAO.obtenerVisitas());
            return ls;

        }
        public void actualizarEstadoVisita(Visita vi)
        {
            List<Visita> ls;
            IVisitaDAO visitaDAO = new VisitaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Visita>(visitaDAO.obtenerVisitas());
            // obtener visita 

            Visita old = (from v in ls
                         where v.codigoVisita == vi.codigoVisita
                         select v).ToList().First();

            if (old.codigoEstadoVisita != vi.codigoEstadoVisita) {
                visitaDAO.actualizarEstadoVisita(vi);
            }

            
        }
    }
}

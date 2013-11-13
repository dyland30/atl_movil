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
    public class TipoVisitaBL : ITipoVisitaBL
    {
        public  List<TipoVisita> obtenerTipoVisitas()
        {
            List<TipoVisita> ls;
            ITipoVisitaDAO tipoVisitaDAO = new TipoVisitaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<TipoVisita>(tipoVisitaDAO.obtenerTipoVisitas());
            return ls;

        }
    }
}

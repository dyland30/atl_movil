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
    public class GrupoBL :IGrupoBL
    {
        public  List<Grupo> obtenerGrupos()
        {
            List<Grupo> ls;
            IGrupoDAO grupoDAO = new GrupoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Grupo>(grupoDAO.obtenerGrupos());

            return ls;

        }
    }
}

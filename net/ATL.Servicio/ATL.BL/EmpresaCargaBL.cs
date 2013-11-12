using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class EmpresaCargaBL
    {
        public static List<EmpresaCarga> obtenerEmpresaCargas()
        {
            List<EmpresaCarga> ls;
            ls = EntidadesHelper.ConvertirAEntidades<EmpresaCarga>(EmpresaCargaDAO.obtenerEmpresaCargas());

            return ls;

        }
    }
}

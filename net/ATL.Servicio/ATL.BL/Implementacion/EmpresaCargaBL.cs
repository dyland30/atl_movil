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
    public class EmpresaCargaBL : IEmpresaCargaBL
    {
        public  List<EmpresaCarga> obtenerEmpresaCargas()
        {
            List<EmpresaCarga> ls;
            IEmpresaCargaDAO empresaCargaDAO = new EmpresaCargaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<EmpresaCarga>(empresaCargaDAO.obtenerEmpresaCargas());

            return ls;

        }
    }
}

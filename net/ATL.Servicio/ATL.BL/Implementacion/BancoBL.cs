using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.BL.Contrato;
using ATL.Entidad;
using ATL.DAO.Contrato;
using ATL.DAO.Implementacion;

namespace ATL.BL.Implementacion
{
    public class BancoBL : IBancoBL
    {

        public List<Banco> obtenerBancos()
        {
            List<Banco> ls;
            IBancoDAO bancoDAO = new BancoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Banco>(bancoDAO.obtenerBancos());

            return ls;
        }
    }
}

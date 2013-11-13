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
    public class UsuarioBL : IUsuarioBL
    {
        public List<Usuario> obtenerUsuarios() {
            List<Usuario> ls;
            IUsuarioDAO usuarioDAO = new UsuarioDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Usuario>(usuarioDAO.obtenerUsuarios());
            return ls;

        }
    }
}

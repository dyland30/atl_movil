using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class UsuarioBL
    {
        public static List<Usuario> obtenerUsuarios() {
            List<Usuario> ls;
            ls = EntidadesHelper.ConvertirAEntidades<Usuario>(UsuarioDAO.obtenerUsuarios());
            return ls;

        }
    }
}

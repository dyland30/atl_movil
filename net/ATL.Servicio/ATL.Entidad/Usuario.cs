using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ATL.Entidad
{
    public class Usuario
    {
        public Usuario() { 
        
        }

        public Usuario(int _id, String _login, String _clave) {
            id = _id;
            login = _login;
            clave = _clave;
        }
        public long id { get; set; }
        public String login { get; set; }
        public String clave { get; set; }
        public long codigoEmpleado { get; set; }

    }
}
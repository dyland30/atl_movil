using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class Cliente
    {
     
        public long codigoCliente { get; set; }
        public long codigoEmpleado { get; set; }
        public long codigoGrupo { get; set; }
        public long codigoPersona { get; set; }
        public string direccionEntregaCliente { get; set; }
        public string representanteCliente { get; set; }
        public long celularCliente { get; set; }
    }
}

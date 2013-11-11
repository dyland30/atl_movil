using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class Visita
    {
        
        public long codigoVisita { get; set; }
        public string strfechaVisita { get; set; }
        public string strhoraInicioVisita { get; set; }
        public string strhoraFinalVisita { get; set; }
        public long codigoTipoVisita { get; set; }
        public long codigoEmpleado { get; set; }
        public long codigoCliente { get; set; }
        public long codigoEstadoVisita { get; set; }
    }
}

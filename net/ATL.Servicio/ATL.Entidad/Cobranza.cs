using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class Cobranza
    {
        public long id { get; set; }
        public long codigoCobranza { get; set; }
        public long codigoMedioPago { get; set; }
        public decimal importeCobranza { get; set; }
        public String strfechaCobranza { get; set; }
        public String estadoCobranza { get; set; }
        public long codigoVisita { get; set; }
        public String codigoMovil { get; set; }
        public List<Amortizacion> amortizaciones { get; set; }
    }
}

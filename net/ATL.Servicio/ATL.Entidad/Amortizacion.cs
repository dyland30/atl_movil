using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class Amortizacion
    {
        public long id { get; set; }
        public long idCobranza { get; set; }
        public long codigoAmortizacion { get; set; }
        public long codigoCobranza { get; set; }
        public long codigoDocumentoPago { get; set; }
        public decimal importeAmortizacion { get; set; }
        public String anotacionAmortizacion { get; set; }
    }
}

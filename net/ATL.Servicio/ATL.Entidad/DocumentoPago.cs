using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class DocumentoPago
    {
        
        public long codigoDocumentoPago { get; set; }
        public string strfechaEmisionDocumentoPago { get; set; }
        public string strfechaVencimientoDocumentoPago { get; set; }
        public decimal importeAmortizadoDocumentoPago { get; set; }
        public decimal importeDescontadoDocumentoPago { get; set; }
        public decimal importeIgvDocumentoPago { get; set; }
        public decimal importeOriginalDocumentoPago { get; set; }
        public decimal importePendienteDocumentoPago { get; set; }
        public int plazoDocumentoPago { get; set; }
        public string tipoDocumentoPago { get; set; }
        public string ReferenciaDocumentoPago { get; set; }
        public long codigoCliente { get; set; }

    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class TallaPedido
    {
        public long codigoProducto { get; set; }
        public long idPedido { get; set; } //temporal
        public long codigoPedido { get; set; } // central
        public int numeroTalla { get; set; }
        public long cantidad { get; set; }
    }
}

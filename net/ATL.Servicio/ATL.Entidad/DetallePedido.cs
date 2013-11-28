using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class DetallePedido
    {
        public long idPedido { get; set; }
        public long codigoPedido { get; set; }
        public long codigoProducto { get; set; }
        public decimal precioUnitario { get; set; }
        public List<TallaPedido> tallas { get; set; }
	
    }
}

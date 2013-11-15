using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class Pedido
    {
        /*
         11-14 10:24:04.747: I/test pedido(227): {"aceptaRetencionPedido":false,"strfechaIngresoPedido":"2013-20-14 10:20",
         * "lineaReservadaPedido":0.0,"instruccionesEspeciales":"tyhf",
         * "importePedido":0.0,"detalles":[{"tallas":[{"idPedido":1,"codigoProducto":1,"cantidad":4,"numeroTalla":39},
         * {"idPedido":1,"codigoProducto":1,"cantidad":4,"numeroTalla":40}],"idPedido":1,"precioUnitario":0.0,"codigoProducto":1}],
         * "direccionDeEnvio":"Av. De la Conquista 789, Santiago de Surco","estaRetenidoPedido":false,
         * "estaSincronizado":false,"estadoPedido":"Ingresado","fechaIngresoPedido":"Nov 14, 2013 10:20:00 AM","id":1,
         * "codigoVisita":16,"codigoPedido":0,"codigoFormaPago":2,"codigoEmpresaCarga":2}
         */

        public long id { get; set; } //temporal
        public long codigoPedido { get; set; }
        public long codigoVisita { get; set; }
        public long codigoFormaPago { get; set; }
        public long codigoEmpresaCarga { get; set; }
        public Double importePedido { get; set; }
        public Double lineaReservadaPedido { get; set; }
        public Boolean aceptaRetencionPedido { get; set; }
        public Boolean estaRetenidoPedido { get; set; }
        public String estadoPedido { get; set; } // A -> Activo, N-> Anulado, R -> Registrado, 
        public Boolean estaSincronizado { get; set; } //1 -> sincronizado, 0-> pendiente de sincronizacion
        public String direccionDeEnvio { get; set; }
        public String empresaTransporte { get; set; }
        public String instruccionesEspeciales { get; set; }
        public String strfechaIngresoPedido { get; set; }

        public List<DetallePedido> detalles;



    }
}

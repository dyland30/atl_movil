using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.BL.Contrato
{
     public interface ITallaBL
    {
        List<Talla> obtenerTallas();
        Talla buscarPorID(long codigoProducto, long numeroTalla);

    }
}

﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.BL.Contrato
{
    public interface IProductoBL
    {
        List<Producto> obtenerProductos();
    }
}

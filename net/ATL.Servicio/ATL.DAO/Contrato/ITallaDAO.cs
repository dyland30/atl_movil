﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace ATL.DAO.Contrato
{
    public interface ITallaDAO
    {
        IDataReader obtenerTallas();
        IDataReader buscarPorID(long codigoProducto, long numeroTalla);
    }
}

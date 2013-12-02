﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;

namespace ATL.BL.Contrato
{
    public interface ICobranzaBL
    {
        List<Cobranza> obtenerCobranzas();
        Cobranza registrarCobranza(Cobranza cob);
    }
}

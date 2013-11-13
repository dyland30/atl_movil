using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace ATL.DAO.Contrato
{
    public interface IGrupoDAO
    {
        IDataReader obtenerGrupos();
    }
}

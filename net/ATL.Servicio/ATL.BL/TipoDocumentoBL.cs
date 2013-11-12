using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;

namespace ATL.BL
{
    public class TipoDocumentoBL
    {
        public static List<TipoDocumento> obtenerTipoDocumentos()
        {
            List<TipoDocumento> ls;
            ls = EntidadesHelper.ConvertirAEntidades<TipoDocumento>(TipoDocumentoDAO.obtenerTipoDocumentos());
            return ls;

        }
    }
}

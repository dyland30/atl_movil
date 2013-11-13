using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using ATL.DAO;
using ATL.BL.Contrato;
using ATL.DAO.Contrato;

namespace ATL.BL
{
    public class TipoDocumentoBL : ITipoDocumentoBL
    {
        public  List<TipoDocumento> obtenerTipoDocumentos()
        {
            List<TipoDocumento> ls;
            ITipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();

            ls = EntidadesHelper.ConvertirAEntidades<TipoDocumento>(tipoDocumentoDAO.obtenerTipoDocumentos());
            return ls;

        }
    }
}

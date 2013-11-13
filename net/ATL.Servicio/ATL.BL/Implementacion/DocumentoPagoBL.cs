using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.BL.Contrato;
using ATL.Entidad;
using ATL.DAO.Contrato;
using ATL.DAO.Implementacion;

namespace ATL.BL.Implementacion
{
    public class DocumentoPagoBL : IDocumentoPagoBL
    {

        public List<DocumentoPago> obtenerDocumentos()
        {
            List<DocumentoPago> ls;
            IDocumentoPagoDAO DocumentoPagoDAO = new DocumentoPagoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<DocumentoPago>(DocumentoPagoDAO.obtenerDocumentos());
            return ls;
        }
    }
}

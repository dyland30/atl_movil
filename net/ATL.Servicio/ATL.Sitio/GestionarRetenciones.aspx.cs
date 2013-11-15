using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ATL.BL.Contrato;
using ATL.BL.Implementacion;

namespace ATL.Sitio
{
    public partial class GestionarRetenciones : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnRetenciones_Click(object sender, EventArgs e)
        {
            lblIndicador.Text = "Procesando Retenciones ....";
            IPedidoBL pedidoBL = new PedidoBL();

            pedidoBL.gestionarRetenciones();

            lblIndicador.Text = "El Procesamiento de las Retenciones ha concluido satisfactoriamente";

        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Script.Services;
using System.Web.Script.Serialization;
using ATL.Entidad;
using ATL.BL;
using ATL.BL.Contrato;
using ATL.BL.Implementacion;
namespace ATL.Servicio
{
    /// <summary>
    /// Summary description for Service1
    /// </summary>
    [WebService(Namespace = "http://atl.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerUsuarios() {
            try {
                IUsuarioBL usuarioBL = new UsuarioBL();
                
                List<Usuario> ls = usuarioBL.obtenerUsuarios();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            
            }catch(Exception ex){
                return null;
                
            }
            
            
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerPersonas()
        {
            try
            {
                IPersonaBL personaBL = new PersonaBL();
                List<Persona> ls = personaBL.obtenerPersonas();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }catch(Exception ex){
                return null;
                
            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerClientes()
        {
            try{
                IClienteBL clienteBL = new ClienteBL();
                List<Cliente> ls = clienteBL.obtenerClientes();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }


        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerGrupos()
        {
            try{
                IGrupoBL grupoBL = new GrupoBL();
                List<Grupo> ls = grupoBL.obtenerGrupos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerEmpleados()
        {
            try{
                IEmpleadoBL empleadoBL = new EmpleadoBL();
                List<Empleado> ls = empleadoBL.obtenerEmpleados();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }


        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerVisitas()
        {
            try
            {
                IVisitaBL visitaBL = new VisitaBL();
                List<Visita> ls = visitaBL.obtenerVisitas();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerEstadoVisitas()
        {
            try
            {
                IEstadoVisitaBL estadoVisitaBL = new EstadoVisitaBL();
                List<EstadoVisita> ls = estadoVisitaBL.obtenerEstadoVisitas();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerTipoVisitas()
        {
            try
            {
                ITipoVisitaBL tipoVisitaBL = new TipoVisitaBL();
                List<TipoVisita> ls = tipoVisitaBL.obtenerTipoVisitas();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerTipoDocumentos()
        {
            try
            {
                ITipoDocumentoBL tipoDocumentoBL = new TipoDocumentoBL();
                List<TipoDocumento> ls = tipoDocumentoBL.obtenerTipoDocumentos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerFormaPagos()
        {
            try
            {
                IFormaPagoBL formaPagoBL = new FormaPagoBL();
                List<FormaPago> ls = formaPagoBL.obtenerFormaPagos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerProductos()
        {
            try
            {
                IProductoBL productoBL = new ProductoBL();

                List<Producto> ls = productoBL.obtenerProductos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerProductoFormaPagos()
        {
            try
            {
                IProductoFormaPagoBL productoFormaPagoBL = new ProductoFormaPagoBL();
                List<ProductoFormaPago> ls = productoFormaPagoBL.obtenerProductoFormaPagos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerTallas()
        {
            try
            {
                ITallaBL tallaBL = new TallaBL();
                List<Talla> ls = tallaBL.obtenerTallas();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerEmpresaCargas()
        {
            try
            {
                IEmpresaCargaBL empresaCargaBL = new EmpresaCargaBL();
                List<EmpresaCarga> ls = empresaCargaBL.obtenerEmpresaCargas();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerDocumentoPagos()
        {
            try
            {
                IDocumentoPagoBL documentoPagoBL = new DocumentoPagoBL();
                List<DocumentoPago> ls = documentoPagoBL.obtenerDocumentos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }


        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerBancos()
        {
            try
            {
                IBancoBL bancoBL = new BancoBL();
                List<Banco> ls = bancoBL.obtenerBancos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerMedioPagos()
        {
            try
            {
                IMedioPagoBL medipPagoBL = new MedioPagoBL();
                List<MedioPago> ls = medipPagoBL.obtenerMedioPagos();

                JavaScriptSerializer js = new JavaScriptSerializer();
                string retJSON = js.Serialize(ls);
                return retJSON;
            }
            catch (Exception ex)
            {
                return null;

            }
        }


        [WebMethod]
        public String ping()
        {

            String resp = "OK";
            return resp;

        }

        [WebMethod]
        public String echo(String cadena)
        {

            return cadena;

        }


    }
}
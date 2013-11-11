using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Script.Services;
using System.Web.Script.Serialization;
using ATL.Entidad;
using ATL.BL;

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

                List<Usuario> ls = UsuarioBL.obtenerUsuarios();

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
                List<Persona> ls = PersonaBL.obtenerPersonas();

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
                List<Cliente> ls = ClienteBL.obtenerClientes();

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
                List<Grupo> ls = GrupoBL.obtenerGrupos();

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
                List<Empleado> ls = EmpleadoBL.obtenerEmpleados();

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
                List<Visita> ls = VisitaBL.obtenerVisitas();

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



    }
}
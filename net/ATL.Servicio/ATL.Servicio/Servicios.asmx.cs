using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Script.Services;
using System.Web.Script.Serialization;

namespace ATL.Servicio
{
    /// <summary>
    /// Summary description for Service1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public String obtenerUsuarios() {
            List<Usuario> ls = new List<Usuario>();
            ls.Add(new Usuario(1,"admin","admin"));
            ls.Add(new Usuario(2, "dan", "dan"));
            ls.Add(new Usuario(3, "cala", "cala"));
            JavaScriptSerializer js = new JavaScriptSerializer();
            string retJSON = js.Serialize(ls);
            return retJSON;
            
        }
    }
}
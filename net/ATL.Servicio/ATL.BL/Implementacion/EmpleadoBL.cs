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
    public class EmpleadoBL : IEmpleadoBL
    {
        public  List<Empleado> obtenerEmpleados()
        {
            List<Empleado> ls;
            IEmpleadoDAO empleadoDAO = new EmpleadoDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Empleado>(empleadoDAO.obtenerEmpleados());
            return ls;

        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ATL.Entidad
{
    public class Empleado
    {
        /*
         codigoEmpleado INTEGER PRIMARY KEY NOT NULL, " +
			"codigoPersona INTEGER, areaEmpleado TEXT, cargoEmpleado TEXT, 
         * fechaCeseEmpleado DATETIME, fechaIngresoEmpleado DATETIME, jefeEmpleado INTEGER
         */
        public long codigoEmpleado { get; set; }
        public long codigoPersona { get; set; }
        public string areaEmpleado { get; set; }
        public string cargoEmpleado { get; set; }
        public string strfechaCeseEmpleado { get; set; }
        public string strfechaIngresoEmpleado { get; set; }
        public long jefeEmpleado { get; set; }

    }
}

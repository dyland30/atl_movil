using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.DAO.Contrato;
using ATL.Entidad;
using System.Globalization;

namespace ATL.DAO
{
    public class VisitaDAO : IVisitaDAO
    {
        public IDataReader obtenerVisitas()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_visita_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }


        public void actualizarEstadoVisita(Visita vi)
        {
            DBHelper helper = DBHelper.GetInstance();
            Nullable<DateTime> horaInicioVisita =null;
            if (!String.IsNullOrEmpty(vi.strhoraInicioVisita))
            horaInicioVisita=DateTime.ParseExact(vi.strhoraInicioVisita, "yyyy-MM-dd HH:mm", CultureInfo.InvariantCulture);

            Nullable<DateTime> horaFinVisita = null;
            if (!String.IsNullOrEmpty(vi.strhoraFinalVisita))
            horaFinVisita = DateTime.ParseExact(vi.strhoraInicioVisita, "yyyy-MM-dd HH:mm", CultureInfo.InvariantCulture);

            int resp = helper.Ejecute("usp_actualizar_estado_visita", vi.codigoVisita, vi.codigoEstadoVisita, horaInicioVisita, horaFinVisita);
            if(resp==-2){
                throw helper.getErrorReal();
            }

        }
    }
}

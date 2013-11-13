using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ATL.Entidad;
using System.Data;
using ATL.DAO.Contrato;


namespace ATL.DAO
{
    public class PersonaDAO : IPersonaDAO
    {

        public  IDataReader obtenerPersonas()
        {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_persona_sel");
            if (reader == null)
            {
                throw helper.getErrorReal();
            }
            return reader;

        }


        public void guardar(object obj)
        {
            throw new NotImplementedException();
        }

        public void actualizar(object obj)
        {
            throw new NotImplementedException();
        }

        public void eliminar(object obj)
        {
            throw new NotImplementedException();
        }

        public object buscarPorId(long id)
        {
            throw new NotImplementedException();
        }

       
    }
}

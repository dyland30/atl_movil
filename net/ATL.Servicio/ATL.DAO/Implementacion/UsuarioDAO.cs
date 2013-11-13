using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using ATL.DAO.Contrato;

namespace ATL.DAO
{
    public class UsuarioDAO : IUsuarioDAO
    {
        public IDataReader obtenerUsuarios() {
            DBHelper helper = DBHelper.GetInstance();
            IDataReader reader = helper.CargarDataReaderProc("usp_usuario_sel");
            if(reader==null){
                throw helper.getErrorReal();
            }
            return reader;
        
        }

    }
}

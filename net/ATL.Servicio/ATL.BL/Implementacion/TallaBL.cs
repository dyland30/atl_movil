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
    public class TallaBL : ITallaBL
    {
        public  List<Talla> obtenerTallas()
        {
            List<Talla> ls;
            ITallaDAO tallaDAO = new TallaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Talla>(tallaDAO.obtenerTallas());

            return ls;

        }


        public Talla buscarPorID(long codigoProducto, long numeroTalla)
        {
            Talla t = null;
            List<Talla> ls;
            ITallaDAO tallaDAO = new TallaDAO();
            ls = EntidadesHelper.ConvertirAEntidades<Talla>(tallaDAO.buscarPorID(codigoProducto,numeroTalla));
            if(ls!=null && ls.Count>0){
                t = ls.First();
            }
            return t;
        }
    }
}

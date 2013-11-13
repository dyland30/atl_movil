using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace ATL.BL
{
    public class Loger
    {
        private static String _rutaCarpeta;

        public static String RutaCarpeta
        {
            get { return _rutaCarpeta; }
            set { _rutaCarpeta = value; }
        }

        public static void Log(String mensaje, String tipo)
        {
            String archivo = _rutaCarpeta + String.Format("{0}{1}.txt", "LOG_ATL", DateTime.Now.ToString("yyyy-MM-dd")); ;

            if (!File.Exists(archivo))
            {
                // crear
                StreamWriter sw = File.CreateText(archivo);
                sw.Dispose();
            }
            using (StreamWriter sw = File.AppendText(archivo))
            {

                sw.WriteLine(String.Format("{0}", DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss")), tipo);
                sw.WriteLine(mensaje);
                sw.WriteLine("--------------------------------------------------------------------------");

            }

        }
    }
}

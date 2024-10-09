/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recibosc60agia.utils;

/**
 *
 * @author vPalomo
 */
public class Utils {
        public static String rellenaIzq(String texto, String letra, int totalSize){
            String result=texto;
            for(int i=texto.length();i<totalSize;i++){
                result=letra+result;
            }
            return result;
        }
        public static String rellenaDer(String texto, String letra, int totalSize){
            String result=texto;
            for(int i=texto.length();i<totalSize;i++){
                result=result+letra;
            }
            return result;
        }
        public static String rellenaA(String texto, int totalSize){
            return rellenaDer(texto, " ", totalSize);
        }
        public static String rellenaN(String texto, int totalSize){
            return rellenaIzq(texto, "0", totalSize);
        }
        
        /**
        * Dada una fecha en formato MySQL (aaaa-mm-dd) genera la fecha sin guiones aaaammdd
        *
        * @param fecha
        * @return
        */
       public static String fecha(String fecha) {
           if ((fecha == null) || (fecha.equalsIgnoreCase("")) || (fecha.equals("0000-00-00"))) {
               return "";
           }
           return fecha.substring(0, 4) + fecha.substring(5, 7) + fecha.substring(8, 10);
       }
    
}

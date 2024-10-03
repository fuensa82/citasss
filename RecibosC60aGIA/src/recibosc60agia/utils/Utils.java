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
}

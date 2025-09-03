/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citasss.gestores;

import citasss.beans.TrabajadoraBean;
import citasss.utils.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vPalomo
 */
public class GestionTrabajadorasBD {
    
    public static ArrayList<TrabajadoraBean> listaTrabajadores;
    /**
     * Devuelve la lista de trabajadoras
     * @param estado El estado puede ser 1 para activas y 0 para trabajadoras que ya no están o están dadas de baja
     * @return 
     */
    public static ArrayList<TrabajadoraBean> getListaTrabajadoras(int estado) {
        if(listaTrabajadores!=null){
            return listaTrabajadores;
        }
        listaTrabajadores = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idTrabajadora,Nombre,Puesto,activa FROM trabajadoras WHERE activa=?");
            consulta.setInt(1, estado);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                TrabajadoraBean trabajadora = new TrabajadoraBean();
                trabajadora.setIdTrabajadora(resultado.getString(1));
                trabajadora.setNombre(resultado.getString(2));
                trabajadora.setPuesto(resultado.getString(3));
                trabajadora.setActiva(resultado.getInt(4));
                listaTrabajadores.add(trabajadora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return listaTrabajadores;
    }
    /**
     * Dado un id de trabajadora devuelve el nombre
     * @param idTrabajadora
     * @return 
     */
    public static String getNombreTrabajadora(String idTrabajadora){
        if(listaTrabajadores==null){
            getListaTrabajadoras(1);
        }
        for(TrabajadoraBean trabajadora:listaTrabajadores){
            if(trabajadora.getIdTrabajadora().equalsIgnoreCase(idTrabajadora)){
                return trabajadora.getNombre();
            }
        }
        return "";
    }
    
    public static javax.swing.DefaultComboBoxModel getModeloComboTrabajadoras() {
        ArrayList<TrabajadoraBean> lista = getListaTrabajadoras(1);
        String[] trabajadoras = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            trabajadoras[i] = lista.get(i).toString();
        }

        return new javax.swing.DefaultComboBoxModel<>(trabajadoras);
    }

    public static ComboBoxModel<String> getModeloComboTrabajadoras0() {
        ArrayList<TrabajadoraBean> lista = getListaTrabajadoras(1);
        String[] trabajadoras = new String[lista.size()+1];
        trabajadoras[0]="0 - TODAS";
        for (int i = 1; i <= lista.size(); i++) {
            trabajadoras[i] = lista.get(i-1).toString();
        }
        return new javax.swing.DefaultComboBoxModel<>(trabajadoras);
    }
}

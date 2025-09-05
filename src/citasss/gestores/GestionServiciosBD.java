/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citasss.gestores;

import citasss.beans.ServiciosBean;
import citasss.utils.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class GestionServiciosBD {
    private static ArrayList<ServiciosBean> lista;
    
    
    public static ArrayList<ServiciosBean> getListaServicios() {
        if(lista!=null){
            return lista;
        }
        lista = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idServicio,NombreServicio FROM servicios");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                ServiciosBean servicio = new ServiciosBean();
                servicio.setIdServicio(resultado.getString(1));
                servicio.setNombreServicio(resultado.getString(2));
                lista.add(servicio);
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
        return lista;
    }
    /**
     * 
     * @param idServicio
     * @return 
     */
    public static String getNombreServicio(String idServicio){
        if(lista==null){
            getListaServicios();
        }
        for(ServiciosBean servicio:lista){
            if(servicio.getIdServicio().equalsIgnoreCase(idServicio)){
                return servicio.getNombreServicio();
            }
        }
        return "";
    }
}

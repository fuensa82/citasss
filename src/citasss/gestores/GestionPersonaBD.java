/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citasss.gestores;

import citasss.beans.PersonaBean;
import citasss.utils.ConectorBD;
import citasss.utils.FechasUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author vPalomo
 */
public class GestionPersonaBD {

    public static ArrayList<PersonaBean> getListaPersonas(int activas, int idTrabajadora) {
        ArrayList<PersonaBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PersonaBean persona;
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa FROM personas WHERE activa=? ORDER BY Apellidos");

            if (idTrabajadora != 0) {
                consulta = conexion.prepareStatement(
                        "SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa FROM personas WHERE activa=? and idTrabajadora=? ORDER BY Apellidos");
                consulta.setInt(2, idTrabajadora);
            }
            consulta.setInt(1, activas);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                persona = new PersonaBean();
                persona.setIdPersona(resultado.getString(1));
                persona.setDNI(resultado.getString(2));
                persona.setNombre(resultado.getString(3));
                persona.setApellidos(resultado.getString(4));
                persona.setFechaNac(resultado.getString(5));
                persona.setTelefono1(resultado.getString(6));
                persona.setTelefono2(resultado.getString(7));
                persona.setEmail(resultado.getString(8));
                persona.setActiva(resultado.getInt(9));
                result.add(persona);
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
        return result;
    }

    /**
     *
     * @param activas
     * @param idTrabajadora
     * @param filtro
     * @return
     */
    public static ArrayList<PersonaBean> getListaPersonasFiltro(int activas, int idTrabajadora, String filtro) {
        ArrayList<PersonaBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PersonaBean persona;
            PreparedStatement consulta = conexion.prepareStatement("SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa,Observaciones,idTrabajadora FROM personas WHERE Nombre LIKE ? and activa=? and idTrabajadora=? "
                    + "UNION "
                    + "SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa,Observaciones,idTrabajadora FROM personas WHERE Apellidos LIKE ?  and activa=? and idTrabajadora=? "
                    + "UNION "
                    + "SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa,Observaciones,idTrabajadora FROM personas WHERE DNI LIKE ?  and activa=? and idTrabajadora=?");
            consulta.setString(1, "%" + filtro + "%");
            consulta.setString(4, "%" + filtro + "%");
            consulta.setString(7, "%" + filtro + "%");
            consulta.setInt(2, activas);
            consulta.setInt(5, activas);
            consulta.setInt(8, activas);

            consulta.setInt(3, idTrabajadora);
            consulta.setInt(6, idTrabajadora);
            consulta.setInt(9, idTrabajadora);
            System.out.println("idTrabajadora: "+idTrabajadora);
            if (idTrabajadora == 0) {
                //System.out.println("idTrabajadora: "+idTrabajadora);
                consulta = conexion.prepareStatement("SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa,Observaciones,idTrabajadora FROM personas WHERE Nombre LIKE ? and activa=? "
                        + "UNION "
                        + "SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa,Observaciones,idTrabajadora FROM personas WHERE Apellidos LIKE ?  and activa=? "
                        + "UNION "
                        + "SELECT idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,activa,Observaciones,idTrabajadora FROM personas WHERE DNI LIKE ?  and activa=? ");
                consulta.setString(1, "%" + filtro + "%");
                consulta.setString(3, "%" + filtro + "%");
                consulta.setString(5, "%" + filtro + "%");
                consulta.setInt(2, activas);
                consulta.setInt(4, activas);
                consulta.setInt(6, activas);

                
            }
            System.out.println("Consulta: " + consulta);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                persona = new PersonaBean();
                persona.setIdPersona(resultado.getString(1));
                persona.setDNI(resultado.getString(2));
                persona.setNombre(resultado.getString(3));
                persona.setApellidos(resultado.getString(4));
                persona.setFechaNac(resultado.getString(5));
                persona.setTelefono1(resultado.getString(6));
                persona.setTelefono2(resultado.getString(7));
                persona.setEmail(resultado.getString(8));
                persona.setActiva(resultado.getInt(9));
                result.add(persona);
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
        return result;
    }

    public static int crearPersona(PersonaBean persona) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement("INSERT INTO `serviciossocialescitas`.`personas` (`DNI`, `Nombre`, `Apellidos`, `FechaNac`, `Telefono1`, `Telefono2`, `email`, `Observaciones`) VALUES (?, ?, ?, ?, ?,?, ?,?);");

            insert1.setString(1, persona.getDNI());
            insert1.setString(2, persona.getNombre());
            insert1.setString(3, persona.getApellidos());
            insert1.setString(4, FechasUtils.fechaParaMysql(persona.getFechaNac()));
            insert1.setString(5, persona.getTelefono1());
            insert1.setString(6, persona.getTelefono2());
            insert1.setString(7, persona.getEmail());
            insert1.setString(8, persona.getObservaciones());

            int fila = insert1.executeUpdate();
            return fila; //Correcto

        } catch (SQLException | NamingException e) {
            JOptionPane.showMessageDialog(null, "Error en el alta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionCitasBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    /**
     * Se utilizará para el mantenimiento de los datos de una persona
     * @param persona
     * @return 
     */
    public static int guardarPersona(PersonaBean persona) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement("UPDATE `serviciossocialescitas`.`personas` set DNI=?, Nombre=?, Apellidos=?, FechaNac=?, Telefono1=?, Telefono2=?, email=?, Observaciones=? where idPersona=?");

            insert1.setString(1, persona.getDNI());
            insert1.setString(2, persona.getNombre());
            insert1.setString(3, persona.getApellidos());
            insert1.setString(4, FechasUtils.fechaParaMysql(persona.getFechaNac()));
            insert1.setString(5, persona.getTelefono1());
            insert1.setString(6, persona.getTelefono2());
            insert1.setString(7, persona.getEmail());
            insert1.setString(8, persona.getObservaciones());
            insert1.setString(9, persona.getIdPersona());
            

            int fila = insert1.executeUpdate();
            return fila; //Correcto

        } catch (SQLException | NamingException e) {
            JOptionPane.showMessageDialog(null, "Error en la modificación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionCitasBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public static PersonaBean getPersona(String idPersona) {
        PersonaBean persona = null;
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "select idPersona,DNI,Nombre,Apellidos,FechaNac,Telefono1,Telefono2,email,observaciones "
                    + "FROM personas "
                    + "WHERE idPersona=?");

            consulta.setString(1, idPersona);
            System.out.println("Consulta: " + consulta);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                persona = new PersonaBean();
                persona.setIdPersona(idPersona);
                persona.setDNI(resultado.getString(2));
                persona.setNombre(resultado.getString(3));
                persona.setApellidos(resultado.getString(4));
                persona.setFechaNac(FechasUtils.fecha(resultado.getString(5), "/"));
                persona.setTelefono1(resultado.getString(6));
                persona.setTelefono2(resultado.getString(7));
                persona.setEmail(resultado.getString(8));
                persona.setObservaciones(resultado.getString(9));

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
        return persona;
    }

    static int asignarUltimaTrabajadora(String idPersona, String idTrabajadora) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement update = conexion.prepareStatement("UPDATE `serviciossocialescitas`.`personas` SET `idTrabajadora`=? WHERE  `idPersona`=?");
            update.setString(1, idTrabajadora);
            update.setString(2, idPersona);
            System.out.println("SQL " + update);
            int fila = update.executeUpdate();
            return fila; //Correcto

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionCitasBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citasss.gestores;

import citasss.utils.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import citasss.beans.CitaDisponibleBeans;
import citasss.beans.HorarioDisponibleBean;
import citasss.beans.ServiciosBean;
import citasss.utils.FechasUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vPalomo
 */
public class GestionCitasBD {

    /**
     *
     * @param idTrabajadora
     * @param activas 1 para las citas pendientes y 0 para las citas pasadas
     * @return
     */
    public static ArrayList<CitaDisponibleBeans> getListaCitas(String idTrabajadora, int activas) {
        ArrayList<CitaDisponibleBeans> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            CitaDisponibleBeans citaDisp;
            PreparedStatement consulta;

            if (activas == 1) {
                consulta = conexion.prepareStatement("select idCitaDisponible, idTrabajadora, fecha, hora, util FROM citasdisponibles WHERE idTrabajadora=? and fecha >= CURDATE()");
            } else {
                consulta = conexion.prepareStatement("select idCitaDisponible, idTrabajadora, fecha, hora, util FROM citasdisponibles WHERE idTrabajadora=? and fecha < CURDATE()");
            }
            consulta.setString(1, idTrabajadora);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {

                citaDisp = new CitaDisponibleBeans();
                citaDisp.setIdCitaDisponible(resultado.getString(1));
                citaDisp.setIdTrabajadora(resultado.getString(2));
                citaDisp.setFecha(FechasUtils.fecha(resultado.getString(3), "/"));
                citaDisp.setHora(resultado.getString(4));
                citaDisp.setUtil(resultado.getInt(5));
                result.add(citaDisp);
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

    //INSERT INTO `serviciossocialescitas`.`citasdisponibles` (`idTrabajadora`, `fecha`, `hora`) VALUES (2, '2025-10-14', '15:04:22');
    /**
     * Creo el calendario de citas. Es como ir abriendo las citas que
     * posteriormente se van a poder coger
     *
     * @param cita
     * @return
     */
    public static int guardarCitaDisponible(CitaDisponibleBeans cita) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement("INSERT INTO `serviciossocialescitas`.`citasdisponibles` (`idTrabajadora`, `fecha`, `hora`) VALUES (?,?,?)");
            insert1.setString(2, FechasUtils.fechaParaMysql(cita.getFecha()));
            insert1.setString(3, cita.getHora());
            insert1.setString(1, cita.getIdTrabajadora());
            int fila = insert1.executeUpdate();
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

    /**
     * Guarda las observaciones de una cita
     *
     * @param idCita
     * @param observaciones
     * @return
     */
    public static int guardarObservaciones(String idCita, String observaciones) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement("update `serviciossocialescitas`.`citasdisponibles` set observaciones=? where idCitaDisponible=?");
            insert1.setString(1, observaciones);
            insert1.setString(2, idCita);
            int fila = insert1.executeUpdate();
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

    /**
     *
     * @param idCita
     * @param idPersona
     * @param idTrabajadora
     * @param idServicio
     * @return
     */
    public static int asignarCitaDisponible(String idCita, String idPersona, String idTrabajadora, String idServicio) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement("UPDATE serviciossocialescitas.citasdisponibles SET idPersona=?, idServicio=? WHERE  idCitaDisponible=?");
            insert1.setString(1, idPersona);
            insert1.setString(2, idServicio);
            insert1.setString(3, idCita);
            //System.out.println("SQL "+insert1);
            int fila = insert1.executeUpdate();
            if (fila == 1) {
                GestionPersonaBD.asignarUltimaTrabajadora(idPersona, idTrabajadora);
            }
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

    /**
     * Devuelve el horario que tiene configurado el trabajador para poder crear
     * citas disponibles
     *
     * @param idTrabajadora id de la trabajadora que se quiere consultar
     * @return
     */
    public static ArrayList<HorarioDisponibleBean> getListaHorarioCitas(String idTrabajadora) {
        ArrayList<HorarioDisponibleBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            HorarioDisponibleBean horaDisp;
            PreparedStatement consulta = conexion.prepareStatement("SELECT idHora,HorarioAntencion,idTrabajadora FROM horarioatencion WHERE idTrabajadora=?");
            consulta.setString(1, idTrabajadora);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                horaDisp = new HorarioDisponibleBean();
                horaDisp.setIdHora(resultado.getString(1));
                horaDisp.setHorarioAntencion(resultado.getString(2));
                horaDisp.setIdTrabajadora(resultado.getString(3));
                result.add(horaDisp);
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
     * Devuelve una lista con las citas para un día dado y una trabajadora. En
     * la lista aparecen las citas vacias y las cogidas. Para saber si está
     * cogida hay que ver si está relleno el idPersona del objeto
     * CitaDisponibleBean que hay dentro del ArrayList
     *
     * @param idTrabajadora
     * @param fecha en formato dd/mm/aaaa
     * @return
     */
    public static ArrayList<CitaDisponibleBeans> getListaCitasCogidas(String idTrabajadora, String fecha) {
        ArrayList<CitaDisponibleBeans> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            CitaDisponibleBeans cita;
            PreparedStatement consulta = conexion.prepareStatement("SELECT idCitaDisponible,idTrabajadora,fecha,hora,util,idPersona, observaciones, idServicio FROM citasdisponibles "
                    + "WHERE fecha=? and  idTrabajadora=? "
                    + "ORDER BY hora");
            consulta.setString(2, idTrabajadora);
            consulta.setString(1, FechasUtils.fechaParaMysql(fecha));
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                cita = new CitaDisponibleBeans();
                cita.setIdCitaDisponible(resultado.getString(1));
                cita.setIdTrabajadora(idTrabajadora);
                cita.setFecha(resultado.getString(3));
                cita.setHora(resultado.getString(4));
                cita.setUtil(resultado.getInt(5));
                cita.setIdPersona(resultado.getString(6));
                cita.setObservaciones(resultado.getString(7));
                cita.setIdServicio(resultado.getString(8));
                result.add(cita);
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
     * Quita a una persona asignada a una cita
     *
     * @param idCita
     * @return
     */
    public static int vaciarCita(String idCita) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement update = conexion.prepareStatement("UPDATE serviciossocialescitas.citasdisponibles SET idPersona=null, idServicio=null, observaciones='' WHERE idCitaDisponible=?");
            update.setString(1, idCita);
            //System.out.println("SQL "+update);
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

    /**
     * Devuelve listado de todas las citas que ha tenido una persona dada como
     * parámetro
     *
     * @param idPersona
     * @return
     */
    public static ArrayList<CitaDisponibleBeans> getCitasDePersona(String idPersona) {
        ArrayList<CitaDisponibleBeans> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            CitaDisponibleBeans cita;
            PreparedStatement consulta = conexion.prepareStatement("SELECT idCitaDisponible,citasdisponibles.idTrabajadora,fecha,hora,util,citasdisponibles.idPersona,idServicio,citasdisponibles.observaciones FROM citasdisponibles,personas "
                    + "WHERE personas.idPersona=citasdisponibles.idPersona "
                    + "AND personas.idPersona=? "
                    + "ORDER BY fecha desc");
            consulta.setString(1, idPersona);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                cita = new CitaDisponibleBeans();
                cita.setIdCitaDisponible(resultado.getString(1));
                cita.setIdTrabajadora(resultado.getString(2));
                cita.setFecha(resultado.getString(3));
                cita.setHora(resultado.getString(4));
                cita.setUtil(resultado.getInt(5));
                cita.setIdPersona(resultado.getString(6));
                cita.setIdServicio(resultado.getString(7));
                cita.setObservaciones(resultado.getString(8));
                result.add(cita);
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

    public static ArrayList<ServiciosBean> getServicios() {
        ArrayList<ServiciosBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            ServiciosBean servicio;
            PreparedStatement consulta = conexion.prepareStatement("SELECT idServicio, nombreServicio FROM servicios ");
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                servicio = new ServiciosBean();
                servicio.setIdServicio(resultado.getString(1));
                servicio.setNombreServicio(resultado.getString(2));
                result.add(servicio);
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

    public static int borrarCitas(ArrayList<CitaDisponibleBeans> lista) {
        try {
            Connection conexion = null;
            conexion = ConectorBD.getConnection();
            int fila = 0;
            for (CitaDisponibleBeans cita : lista) {
                
                PreparedStatement update = conexion.prepareStatement("DELETE from citasdisponibles WHERE idCitaDisponible=?");
                update.setString(1, cita.getIdCitaDisponible());
                //System.out.println("SQL "+update);
                fila += update.executeUpdate();
            }
            return fila;
        } catch (NamingException ex) {
            Logger.getLogger(GestionCitasBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestionCitasBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}

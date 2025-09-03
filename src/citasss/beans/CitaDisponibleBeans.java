/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citasss.beans;

/**
 *
 * @author vPalomo
 */
public class CitaDisponibleBeans {

    private String idCitaDisponible;
    private String idTrabajadora;
    private String fecha;
    private String hora;
    private int util;
    private PersonaBean persona;

    public void setIdPersona(String idPersona){
        if(persona==null)persona=new PersonaBean(idPersona);
        persona.cargarDatos();
    }
    public PersonaBean getPersona() {
        return persona;
    }

    public void setPersona(PersonaBean persona) {
        this.persona = persona;
    }


    public CitaDisponibleBeans(String idCitaDisponible, String idTrabajadora, String fecha, String hora, int util) {
        this.idCitaDisponible = idCitaDisponible;
        this.idTrabajadora = idTrabajadora;
        this.fecha = fecha;
        this.hora = hora;
        this.util = util;
    }

    public CitaDisponibleBeans() {
    }

    public CitaDisponibleBeans(String idTrabajadora, String fecha, String hora, int util) {
        this.idTrabajadora = idTrabajadora;
        this.fecha = fecha;
        this.hora = hora;
        this.util = util;
    }
    public String getIdCitaDisponible() {
        return idCitaDisponible;
    }

    public void setIdCitaDisponible(String idCitaDisponible) {
        this.idCitaDisponible = idCitaDisponible;
    }

    public String getIdTrabajadora() {
        return idTrabajadora;
    }

    public void setIdTrabajadora(String idTrabajadora) {
        this.idTrabajadora = idTrabajadora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getUtil() {
        return util;
    }

    public void setUtil(int util) {
        this.util = util;
    }

    
}

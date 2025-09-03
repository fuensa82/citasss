/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citasss.beans;


/**
 *
 * @author vPalomo
 */
public class TrabajadoraBean {

    private String idTrabajadora;
    private String nombre;
    private String puesto;
    private int activa;

    public int getActiva() {
        return activa;
    }
    public String toString(){
        return idTrabajadora+" - "+nombre;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public String getIdTrabajadora() {
        return idTrabajadora;
    }

    public void setIdTrabajadora(String idTrabajadora) {
        this.idTrabajadora = idTrabajadora;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    
}

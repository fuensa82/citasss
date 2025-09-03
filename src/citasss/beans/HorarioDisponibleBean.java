/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citasss.beans;

/**
 *
 * @author vPalomo
 */
public class HorarioDisponibleBean {

    private String idHora;
    private String HorarioAntencion;
    private String idTrabajadora;

    public HorarioDisponibleBean() {
    }

    public String getIdHora() {
        return idHora;
    }

    public void setIdHora(String idHora) {
        this.idHora = idHora;
    }

    public String getHorarioAntencion() {
        return HorarioAntencion;
    }

    public HorarioDisponibleBean(String idHora, String HorarioAntencion, String idTrabajadora) {
        this.idHora = idHora;
        this.HorarioAntencion = HorarioAntencion;
        this.idTrabajadora = idTrabajadora;
    }

    public HorarioDisponibleBean(String HorarioAntencion, String idTrabajadora) {
        this.HorarioAntencion = HorarioAntencion;
        this.idTrabajadora = idTrabajadora;
    }

    public void setHorarioAntencion(String HorarioAntencion) {
        this.HorarioAntencion = HorarioAntencion;
    }

    public String getIdTrabajadora() {
        return idTrabajadora;
    }

    public void setIdTrabajadora(String idTrabajadora) {
        this.idTrabajadora = idTrabajadora;
    }

    

    
    
}

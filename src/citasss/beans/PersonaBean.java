/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citasss.beans;

import citasss.gestores.GestionPersonaBD;

/**
 *
 * @author vPalomo
 */
public class PersonaBean {

    private String idPersona;
    private String DNI;
    private String nombre;
    private String apellidos;
    private String fechaNac;
    private String telefono1;
    private String telefono2;
    private String email;
    private int activa;
    private String observaciones;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public boolean cargarDatos(){
        boolean result=false;
        if(idPersona ==null || "".equalsIgnoreCase(idPersona))return result;
        
        PersonaBean persona=GestionPersonaBD.getPersona(idPersona);
        this.nombre=persona.getNombre();
        this.apellidos=persona.getApellidos();
        this.DNI=persona.getDNI();
        this.fechaNac=persona.getFechaNac();
        this.telefono1=persona.getTelefono1();
        this.telefono2=persona.getTelefono2();
        this.email=persona.getEmail();
        
        return true;
    }

    public PersonaBean(String idPersona) {
        this.idPersona = idPersona;
    }
    
    public PersonaBean(String idPersona, String DNI, String nombre, String apellidos, String fechaNac, String telefono1, String telefono2, String email) {
        this.idPersona = idPersona;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
    }

    public PersonaBean(String DNI, String nombre, String apellidos, String fechaNac, String telefono1, String telefono2, String email) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
    }

    public PersonaBean() {
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
}

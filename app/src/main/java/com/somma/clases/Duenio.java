package com.somma.clases;

public class Duenio {
    private static Integer cedula;
    private static String nombre;
    private static String direccion;
    private static Integer telefono;

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        Duenio.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Duenio.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        Duenio.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        Duenio.telefono = telefono;
    }

    public Duenio() {
    }
}

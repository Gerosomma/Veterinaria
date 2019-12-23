package com.somma.clases;

import java.io.Serializable;
import java.util.Date;

public class Mascota implements Serializable {
    private static String codigo;
    private static Duenio duenio;
    private static String nombre;
    private static String fecha_afiliacion;
    private static String especie;
    private static String raza;
    private static Integer edad;
    private static Integer peso;
    private static String pelo;


    public Mascota() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        Mascota.codigo = codigo;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        Mascota.duenio = duenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Mascota.nombre = nombre;
    }

    public String getFecha_afiliacion() {
        return fecha_afiliacion;
    }

    public void setFecha_afiliacion(String fecha_afiliacion) {
        Mascota.fecha_afiliacion = fecha_afiliacion;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        Mascota.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        Mascota.raza = raza;
    }

    public static Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        Mascota.edad = edad;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        Mascota.peso = peso;
    }

    public String getPelo() {
        return pelo;
    }

    public void setPelo(String pelo) {
        Mascota.pelo = pelo;
    }
}

package com.somma.clases;

import java.util.Date;

public class H_Clinica {
    private static Date fecha;
    private static String descripcion;
    private static String sintomas;
    private static String tratamiento_recomendado;
    private static String medicamento_recetado;
    private static String veterinario;

    public H_Clinica() {
    }

    public static Date getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        H_Clinica.fecha = fecha;
    }

    public static String getDescripcion() {
        return descripcion;
    }

    public static void setDescripcion(String descripcion) {
        H_Clinica.descripcion = descripcion;
    }

    public static String getSintomas() {
        return sintomas;
    }

    public static void setSintomas(String sintomas) {
        H_Clinica.sintomas = sintomas;
    }

    public static String getTratamiento_recomendado() {
        return tratamiento_recomendado;
    }

    public static void setTratamiento_recomendado(String tratamiento_recomendado) {
        H_Clinica.tratamiento_recomendado = tratamiento_recomendado;
    }

    public static String getMedicamento_recetado() {
        return medicamento_recetado;
    }

    public static void setMedicamento_recetado(String medicamento_recetado) {
        H_Clinica.medicamento_recetado = medicamento_recetado;
    }

    public static String getVeterinario() {
        return veterinario;
    }

    public static void setVeterinario(String veterinario) {
        H_Clinica.veterinario = veterinario;
    }
}

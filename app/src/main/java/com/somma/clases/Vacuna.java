package com.somma.clases;

import java.util.Date;

public class Vacuna {
    private static Mascota mascota;
    private static Date fecha;
    private static String vacuna;
    private static String dosis;
    private static H_Clinica h_clinica;

    public Vacuna() {
    }

    public static Mascota getMascota() {
        return mascota;
    }

    public static void setMascota(Mascota mascota) {
        Vacuna.mascota = mascota;
    }

    public static Date getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        Vacuna.fecha = fecha;
    }

    public static String getVacuna() {
        return vacuna;
    }

    public static void setVacuna(String vacuna) {
        Vacuna.vacuna = vacuna;
    }

    public static String getDosis() {
        return dosis;
    }

    public static void setDosis(String dosis) {
        Vacuna.dosis = dosis;
    }

    public static H_Clinica getH_clinica() {
        return h_clinica;
    }

    public static void setH_clinica(H_Clinica h_clinica) {
        Vacuna.h_clinica = h_clinica;
    }
}

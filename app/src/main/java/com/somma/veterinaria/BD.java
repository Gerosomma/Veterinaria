package com.somma.veterinaria;

import android.provider.BaseColumns;

public final class BD {
    //base
    public static final String NOMBRE_BASE_DATOS = "BaseVeterinaria.sqlite3";
    public static final int VERSION = 1;

    //tablas
    public static final String DUENIOS = "DUENIOS";
    public static final String MASCOTAS = "MASCOTAS";
    public static final String VACUNAS = "VACUNAS";
    public static final String H_CLINICA = "H_CLINICA";

    //constructor
    private BD() {

    }

    //creaciones de tablas
    public static abstract class Duenios implements BaseColumns {
        //columnas
        public static final String CEDULA = "Cedula";
        public static final String NOMBRE = "Nombre";
        public static final String DIRECCION = "Direccion";
        public static final String TELEFONO = "Telefono";

        public static final String[] COLUMNAS = {_ID, CEDULA, NOMBRE, DIRECCION, TELEFONO};

        public static final String SQL_CREACION_TABLA = new StringBuilder
                ("CREATE TABLE ").append(DUENIOS).append(" (")
                                .append(_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ")
                                .append(CEDULA).append(" INTEGER UNIQUE NOT NULL, ")
                                .append(NOMBRE).append(" TEXT NOT NULL, ")
                                .append(DIRECCION).append(" TEXT NOT NULL, ")
                                .append(TELEFONO).append(" INTEGER NOT NULL);")
                                .toString();
        //eliminar tabla
        public static final String SQL_ELIMINAR_TABLA = new StringBuilder
                ("DROP TABLE IF EXISTS ").append(DUENIOS).append(";").toString();
    }

    public static abstract class Mascotas implements BaseColumns {
        //columnas
        public static final String CODIGO = "Codigo";
        public static final String DUENIO = "Duenio";
        public static final String NOMBRE = "Nombre";
        public static final String FECHA_AFILIACION = "Fecha_Afiliacion";
        public static final String ESPECIE = "Especie";
        public static final String RAZA = "Raza";
        public static final String EDAD = "Edad";
        public static final String PESO = "Peso";
        public static final String PELO = "Pelo";

        public static final String[] COLUMNAS = {
                _ID, CODIGO, DUENIO, NOMBRE, FECHA_AFILIACION, ESPECIE, RAZA, EDAD, PESO, PELO
        };

        public static final String SQL_CREACION_TABLA = new StringBuilder
                ("CREATE TABLE ").append(MASCOTAS).append(" (")
                .append(_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ")
                .append(CODIGO).append(" TEXT NOT NULL UNIQUE, ")
                .append(DUENIO).append(" INTEGER NOT NULL, ")
                .append(NOMBRE).append(" TEXT NOT NULL, ")
                .append(FECHA_AFILIACION).append(" DATETIME NOT NULL, ")
                .append(ESPECIE).append(" TEXT NOT NULL, ")
                .append(RAZA).append(" TEXT NOT NULL, ")
                .append(EDAD).append(" INTEGER NOT NULL, ")
                .append(PESO).append(" INTEGER NOT NULL, ")
                .append(PELO).append(" TEXT NOT NULL);") .toString();
        //eliminar tabla
        public static final String SQL_ELIMINAR_TABLA = new StringBuilder
                ("DROP TABLE IF EXISTS ").append(DUENIOS).append(";").toString();
    }

    public static abstract class Vacunas implements BaseColumns {
        //columnas
        public static final String CODIGO = "Codigo";
        public static final String FECHA = "Fecha";
        public static final String VACUNA = "Vacuna";
        public static final String DOSIS = "Dosis";

        public static final String[] COLUMNAS = {
                _ID, CODIGO, FECHA, VACUNA, DOSIS
        };

        public static final String SQL_CREACION_TABLA = new StringBuilder
                ("CREATE TABLE ").append(VACUNAS).append(" (")
                .append(_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ")
                .append(CODIGO).append(" TEXT NOT NULL, ")
                .append(FECHA).append(" DATETIME NOT NULL, ")
                .append(VACUNA).append(" TEXT NOT NULL, ")
                .append(DOSIS).append(" TEXT NOT NULL,")
                .append(" FOREIGN KEY (").append(CODIGO).append(") REFERENCES ").append(MASCOTAS).append(" (").append(Mascotas.CODIGO).append(") );")
                .toString();

        //eliminar tabla
        public static final String SQL_ELIMINAR_TABLA = new StringBuilder
                ("DROP TABLE IF EXISTS ").append(VACUNAS).append(";").toString();
    }

    public static abstract class Historia_clinica implements BaseColumns {
        //columnas
        public static final String CODIGO = "Codigo";
        public static final String FECHA = "Fecha";
        public static final String DESCRIPCION = "Descripcion";
        public static final String SINTOMAS = "Dosis";
        public static final String TRATAMIENTO_RECOMENDADO = "Tratamiento_Recomendado";
        public static final String MEDICAMENTO_RECETADO = "Medicamento_Recetado";
        public static final String VETERINARIO = "Veterinario";

        public static final String[] COLUMNAS = {
                _ID, CODIGO, FECHA, DESCRIPCION, SINTOMAS, TRATAMIENTO_RECOMENDADO, MEDICAMENTO_RECETADO, VETERINARIO
        };

        public static final String SQL_CREACION_TABLA = new StringBuilder
                ("CREATE TABLE ").append(H_CLINICA).append(" (")
                .append(_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ")
                .append(CODIGO).append(" TEXT NOT NULL, ")
                .append(FECHA).append(" DATETIME NOT NULL, ")
                .append(DESCRIPCION).append(" TEXT NOT NULL, ")
                .append(SINTOMAS).append(" TEXT NOT NULL, ")
                .append(TRATAMIENTO_RECOMENDADO).append(" TEXT NOT NULL, ")
                .append(MEDICAMENTO_RECETADO).append(" TEXT NOT NULL, ")
                .append(VETERINARIO).append(" TEXT NOT NULL,")
                .append(" FOREIGN KEY (").append(CODIGO).append(") REFERENCES ").append(MASCOTAS).append(" (").append(Mascotas.CODIGO).append(") );")
                .toString();
        //eliminar tabla
        public static final String SQL_ELIMINAR_TABLA = new StringBuilder
                ("DROP TABLE IF EXISTS ").append(H_CLINICA).append(";").toString();
    }




}

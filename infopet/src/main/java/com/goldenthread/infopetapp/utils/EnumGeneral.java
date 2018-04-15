package com.goldenthread.infopetapp.utils;


public class EnumGeneral {


    public enum TipoMamifero {

        PERRO(1,"Perro"),
        GATO(2,"Gato");

        private Integer codigo;
        private String descripcion;


        TipoMamifero (Integer codigo, String descripcion){
            this.codigo = codigo;
            this.descripcion = descripcion;
        }

        public Integer getCodigo() {
            return codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }


    public enum Genero {

        MACHO(1,"Macho"),
        HEMBRA(2,"Hembra");

        private Integer codigo;
        private String descripcion;


        Genero (Integer codigo, String descripcion){
            this.codigo = codigo;
            this.descripcion = descripcion;
        }

        public Integer getCodigo() {
            return codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}

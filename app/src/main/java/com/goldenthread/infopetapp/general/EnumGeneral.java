package com.goldenthread.infopetapp.general;

/**
 * Created by Paula on 15/02/2015.
 */
public class EnumGeneral {
    /**
     * Created by Paula on 14/02/2015.
     */
    public static enum TipoMamifero {

        PERRO(1,"Perro"),
        GATO(2,"Gato");

        private Integer codigo;
        private String descripcion;


        private TipoMamifero (Integer codigo, String descripcion){
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


    public static enum Genero {

        MACHO(1,"Macho"),
        HEMBRA(2,"Hembra");

        private Integer codigo;
        private String descripcion;


        private Genero (Integer codigo, String descripcion){
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

package com.goldenthread.infopetapp.utils;

public enum AccionEnum {

    ES_PRIMER_MASCOTA("ES_PRIMER_MASCOTA"),
    ES_SEGUNDA_MASCOTA("ES_SEGUNDA_MASCOTA"),
    EDITAR_MASCOTA("EDITAR_MASCOTA");

    private String codigo;

    AccionEnum (String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }

    static public AccionEnum getAccionFromCodigo(String codigo){
        for(AccionEnum accion : AccionEnum.values()){
            if(accion.getCodigo().equals(codigo)){
                return accion;
            }
        }
        return null;
    }
}

package com.goldenthread.infopetapp.validacion;

import com.goldenthread.infopetapp.general.Constantes;
import com.goldenthread.infopetapp.servicio.MascotaServicio;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {

    private MascotaServicio mascotaServicio;

    public Validacion(MascotaServicio mascotaServicio) {
        this.mascotaServicio = mascotaServicio;
    }

    public static boolean esFormatoTexto(String texto) {
        Pattern p = Pattern.compile(Constantes.REGEX_FORMATO_TEXTO_CON_ACENTOS);
        Matcher m = p.matcher(texto);

        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(Object objeto) {
        if (objeto == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(String texto) {
        if (texto != null) {
            if (texto.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }else{
            return true;
        }
    }

    public static boolean isCollectionEmpty(Collection lista) {
        if(lista != null){
            if(!lista.isEmpty()){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }

    }
}

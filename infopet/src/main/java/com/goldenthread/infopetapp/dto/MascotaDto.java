package com.goldenthread.infopetapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.goldenthread.infopetapp.utils.EnumGeneral;
import com.google.common.base.Objects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

public class MascotaDto extends BaseDto {

    private Integer idMascota;
    private String nombre;
    private EnumGeneral.TipoMamifero tipoMamifero;
    private EnumGeneral.Genero genero;
    private Date fechaNacimiento;

    private String uriFotoPerfil;

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTipoMamifero(EnumGeneral.TipoMamifero tipoMamifero) {
        this.tipoMamifero = tipoMamifero;
    }

    public EnumGeneral.TipoMamifero getTipoMamifero() {
        return tipoMamifero;
    }

    public void setGenero(EnumGeneral.Genero genero) {
        this.genero = genero;
    }

    public EnumGeneral.Genero getGenero() {
        return genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUriFotoPerfil() {
        return uriFotoPerfil;
    }

    public void setUriFotoPerfil(String uriFotoPerfil) {
        this.uriFotoPerfil = uriFotoPerfil;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}

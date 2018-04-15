package com.goldenthread.infopetapp.filtro;

import com.goldenthread.infopetapp.utils.EnumGeneral;

import java.util.Date;


public class BaseFiltro {

    private Integer idMascota;
    private String nombre;
    private EnumGeneral.TipoMamifero tipoMamifero;
    private EnumGeneral.Genero genero;
    private Date fechaNacimiento;
    private String uriFotoPerfil;

    public BaseFiltro() {}

    public BaseFiltro(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EnumGeneral.TipoMamifero getTipoMamifero() {
        return tipoMamifero;
    }

    public void setTipoMamifero(EnumGeneral.TipoMamifero tipoMamifero) {
        this.tipoMamifero = tipoMamifero;
    }

    public EnumGeneral.Genero getGenero() {
        return genero;
    }

    public void setGenero(EnumGeneral.Genero genero) {
        this.genero = genero;
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
}

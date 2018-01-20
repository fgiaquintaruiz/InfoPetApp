package com.goldenthread.infopetapp.persistencia.modelo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "IP_MASCOTA")
public class IpMascota extends Modelo{

    public IpMascota() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="ID_MASCOTA_GEN")
    @Column(name="ID_MASCOTA")
    private Integer idMascota;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="TIPO_MAMIFERO")
    private String tipoMamifero;

    @Column(name="GENERO")
    private String genero;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_NACIMIENTO")
    private Date fechaNacimiento;

    @Column(name="URI_FOTO_PERFIL")
    private String uriFotoPerfil;

    public IpMascota(Integer idMascota, String nombre, String tipoMamifero, String genero, Date fechaNacimiento, String uriFotoPerfil) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.tipoMamifero = tipoMamifero;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.uriFotoPerfil = uriFotoPerfil;

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

    public String getTipoMamifero() {
        return tipoMamifero;
    }

    public void setTipoMamifero(String tipoMamifero) {
        this.tipoMamifero = tipoMamifero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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

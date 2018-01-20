package com.goldenthread.infopetapp.servicio;

import com.goldenthread.infopetapp.general.EnumGeneral;
import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.ui.dto.MascotaDto;
import com.goldenthread.infopetapp.validacion.Validacion;

public class MascotaMapeadorImpl implements MascotaMapeador{

    public IpMascota mapearModelo(MascotaDto dto, IpMascota modelo) {

        if (!Validacion.isNull(dto.getIdMascota())) {
            modelo.setIdMascota(dto.getIdMascota());
        }
        if (!Validacion.isEmpty(dto.getNombre())) {
            modelo.setNombre(dto.getNombre());
        }
        if (!Validacion.isNull(dto.getTipoMamifero())) {
            modelo.setTipoMamifero(dto.getTipoMamifero().name());
        }
        if (!Validacion.isNull(dto.getGenero())) {
            modelo.setGenero(dto.getGenero().name());
        }
        if (!Validacion.isNull(dto.getFechaNacimiento())) {
            modelo.setFechaNacimiento(dto.getFechaNacimiento());
        }
        if (!Validacion.isEmpty(dto.getUriFotoPerfil())) {
            modelo.setUriFotoPerfil(dto.getUriFotoPerfil());
        }
        return modelo;
    }

    public MascotaDto mapearDto(IpMascota modelo, MascotaDto dto) {

        dto.setIdMascota(modelo.getIdMascota());
        dto.setNombre(modelo.getNombre());
        dto.setTipoMamifero(EnumGeneral.TipoMamifero.valueOf(modelo.getTipoMamifero()));
        dto.setGenero(EnumGeneral.Genero.valueOf(modelo.getGenero()));
        if (!Validacion.isNull(modelo.getFechaNacimiento())) {
            dto.setFechaNacimiento(modelo.getFechaNacimiento());
        }
        if (!Validacion.isEmpty(modelo.getUriFotoPerfil())) {
            dto.setUriFotoPerfil(modelo.getUriFotoPerfil());
        }
        return dto;
    }

}

package com.goldenthread.infopetapp.mapeador;

import com.goldenthread.infopetapp.utils.EnumGeneral;
import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.validacion.Validacion;

public class MascotaMapeadorImpl implements MascotaMapeador{

    public IpMascota mapearModelo(MascotaDto dto, IpMascota modelo) {

        modelo.setIdMascota(dto.getIdMascota());
        modelo.setNombre(dto.getNombre());
        modelo.setTipoMamifero(dto.getTipoMamifero().name());
        modelo.setGenero(dto.getGenero().name());
        modelo.setFechaNacimiento(dto.getFechaNacimiento());
        modelo.setUriFotoPerfil(dto.getUriFotoPerfil());
        return modelo;
    }

    public MascotaDto mapearDto(IpMascota modelo, MascotaDto dto) {

        dto.setIdMascota(modelo.getIdMascota());
        dto.setNombre(modelo.getNombre());
        dto.setTipoMamifero(EnumGeneral.TipoMamifero.valueOf(modelo.getTipoMamifero()));
        dto.setGenero(EnumGeneral.Genero.valueOf(modelo.getGenero()));
        dto.setFechaNacimiento(modelo.getFechaNacimiento());
        dto.setUriFotoPerfil(modelo.getUriFotoPerfil());
        return dto;
    }

}

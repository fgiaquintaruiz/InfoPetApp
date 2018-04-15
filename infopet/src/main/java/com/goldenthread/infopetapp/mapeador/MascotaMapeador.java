package com.goldenthread.infopetapp.mapeador;


import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.dto.MascotaDto;

public interface MascotaMapeador {

    public IpMascota mapearModelo(MascotaDto mascotaDto, IpMascota modelo);

    public MascotaDto mapearDto(IpMascota modelo, MascotaDto mascotaDto);
}

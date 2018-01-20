package com.goldenthread.infopetapp.servicio;


import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.ui.dto.MascotaDto;

public interface MascotaMapeador {

    public IpMascota mapearModelo(MascotaDto mascotaDto, IpMascota modelo);

    public MascotaDto mapearDto(IpMascota modelo, MascotaDto mascotaDto);
}

package com.goldenthread.infopetapp.servicio;

import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.filtro.MascotaFiltro;

import java.util.List;

public interface MascotaServicio {

    public void actualziar(MascotaDto mascotaDto) throws Exception;

    public MascotaDto obtener(MascotaFiltro mascotaFiltro) throws Exception;

    public List<MascotaDto> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception;

    public Integer crear(MascotaDto mascota) throws Exception;

    public int borrar(Integer idMascota) throws Exception;
}

package com.goldenthread.infopetapp.servicio;

import com.goldenthread.infopetapp.ui.dto.MascotaDto;
import com.goldenthread.infopetapp.ui.filtro.MascotaFiltro;

import java.util.List;

/**
 * Created by Paula on 22/02/2015.
 */
public interface MascotaServicio {

    public void actualziar(MascotaDto mascotaDto) throws Exception;

    public MascotaDto obtener(MascotaFiltro mascotaFiltro) throws Exception;

    public List<MascotaDto> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception;

    public Integer crear(MascotaDto mascota) throws Exception;
}

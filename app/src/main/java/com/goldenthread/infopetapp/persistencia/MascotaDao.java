package com.goldenthread.infopetapp.persistencia;

import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.ui.filtro.MascotaFiltro;

import java.util.List;

/**
 * Created by Paula on 22/02/2015.
 */
public interface MascotaDao {

    public Integer crear(IpMascota ipMascota) throws Exception;

    public int actualizar(IpMascota ipMascota) throws Exception;

    public IpMascota obtener(MascotaFiltro mascotaFiltro) throws Exception;

    List<IpMascota> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception;
}

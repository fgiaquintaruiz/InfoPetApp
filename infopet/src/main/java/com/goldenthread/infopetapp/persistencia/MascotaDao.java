package com.goldenthread.infopetapp.persistencia;

import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.filtro.MascotaFiltro;

import java.util.List;

public interface MascotaDao {

    public Integer crear(IpMascota ipMascota) throws Exception;

    public int actualizar(IpMascota ipMascota) throws Exception;

    public int borrar(Integer idMascota) throws Exception;

    public IpMascota obtener(MascotaFiltro mascotaFiltro) throws Exception;

    List<IpMascota> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception;
}

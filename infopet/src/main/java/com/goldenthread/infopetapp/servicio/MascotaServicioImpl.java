package com.goldenthread.infopetapp.servicio;

import com.goldenthread.infopetapp.mapeador.MascotaMapeador;
import com.goldenthread.infopetapp.persistencia.MascotaDao;
import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.filtro.MascotaFiltro;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.ArrayList;
import java.util.List;

public class MascotaServicioImpl implements MascotaServicio {

    private MascotaDao mascotaDao;
    private MascotaMapeador mascotaMapeador;

    MascotaServicioImpl(MascotaDao mascotaDao, MascotaMapeador mascotaMapeador) {
        this.mascotaDao = mascotaDao;
        this.mascotaMapeador = mascotaMapeador;
    }

    @Override
    public void actualziar(MascotaDto mascotaDto) throws Exception {
        IpMascota modelo = new IpMascota();
        modelo = mascotaMapeador.mapearModelo(mascotaDto, modelo);
        mascotaDao.actualizar(modelo);
    }

    @Override
    public MascotaDto obtener(MascotaFiltro mascotaFiltro) throws Exception {
        MascotaDto mascotaDto = new MascotaDto();
        IpMascota modelo = mascotaDao.obtener(mascotaFiltro);
        if(!Validacion.isNull(modelo)){
            return mascotaMapeador.mapearDto(modelo, mascotaDto);
        }else{
            return null;
        }
    }

    @Override
    public List<MascotaDto> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception {

        List<IpMascota> listaModelo = mascotaDao.obtenerMascotas(mascotaFiltro);
        List<MascotaDto> listaDto = new ArrayList<>();

        for(IpMascota modelo : listaModelo){
            MascotaDto mascotaDto = new MascotaDto();
            listaDto.add(mascotaMapeador.mapearDto(modelo, mascotaDto));
        }

        return listaDto;
    }

    @Override
    public Integer crear(MascotaDto mascotaDto) throws Exception {
        IpMascota modelo = new IpMascota();
        modelo = mascotaMapeador.mapearModelo(mascotaDto, modelo);
        if(!Validacion.isNull(modelo)){
            return mascotaDao.crear(modelo);
        }else{
            return null;
        }
    }

    @Override
    public int borrar(Integer idMascota) throws Exception {
        return mascotaDao.borrar(idMascota);
    }

}
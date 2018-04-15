package com.goldenthread.infopetapp.servicio;

import com.goldenthread.infopetapp.mapeador.MascotaMapeador;
import com.goldenthread.infopetapp.persistencia.MascotaDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
       library = true,
       complete = false
)
public class MascotaServicioModule {

    @Provides
    @Singleton
    public MascotaServicio provideMascotaServicio(MascotaDao mascotaDao, MascotaMapeador mascotaMapeador) {
        return new MascotaServicioImpl(mascotaDao, mascotaMapeador);
    }
}

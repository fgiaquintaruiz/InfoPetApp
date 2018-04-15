package com.goldenthread.infopetapp.mapeador;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
       library = true
)
public class MascotaMapeadorModule {

    @Provides
    @Singleton
    public MascotaMapeador provideMascotaMapeador() {
        return new MascotaMapeadorImpl();
    }
}

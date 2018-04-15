package com.goldenthread.infopetapp.persistencia;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
       library = true,
        complete = false
)
public class DaoModule {

    @Provides
    @Singleton
    public MascotaDao provideDao(Application contexto) {
        return new MascotaDaoImpl(contexto);
    }
}

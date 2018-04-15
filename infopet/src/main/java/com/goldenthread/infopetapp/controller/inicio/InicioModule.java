package com.goldenthread.infopetapp.controller.inicio;

import com.goldenthread.infopetapp.AppModule;
import com.goldenthread.infopetapp.servicio.MascotaServicio;
import com.goldenthread.infopetapp.controller.base.Presenter;
import com.goldenthread.infopetapp.controller.base.PresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects ={
                InicioActivity.class
        },
        addsTo = AppModule.class,
        library = true,
        complete = false
)
public class InicioModule {

    private MascotaServicio mascotaServicio;
    private InicioView inicioView;

    public InicioModule(InicioView inicioView) {
        this.inicioView = inicioView;
    }

    @Provides
    @Singleton
    public Presenter providePresenter(MascotaServicio mascotaServicio) {
        return new PresenterImpl(mascotaServicio);
    }

}

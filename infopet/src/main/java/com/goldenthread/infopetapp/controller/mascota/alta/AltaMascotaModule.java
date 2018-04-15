package com.goldenthread.infopetapp.controller.mascota.alta;

import com.goldenthread.infopetapp.AppModule;
import com.goldenthread.infopetapp.controller.base.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects ={
                AltaMascotaNombreActivity.class,
                AltaMascotaTipoActivity.class,
                AltaMascotaGeneroActivity.class,
                AltaMascotaFechaNacimientoActivity.class,
                AltaMascotaFotoPerfilActivity.class
        },
        addsTo = AppModule.class,
        library = true,
        complete = false
)
public class AltaMascotaModule {

    private AltaMascotaView view;

    public AltaMascotaModule(AltaMascotaView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public AltaMascotaView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public AltaMascotaPresenter provideAltaMascotaPresenter(AltaMascotaView view, Presenter presenter) {
        return new AltaMascotaPresenterImpl(view, presenter);
    }

}

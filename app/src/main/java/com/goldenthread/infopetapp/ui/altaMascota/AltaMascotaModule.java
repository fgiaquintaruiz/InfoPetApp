package com.goldenthread.infopetapp.ui.altaMascota;

import com.goldenthread.infopetapp.AppModule;
import com.goldenthread.infopetapp.ui.general.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects ={
                AltaMascotaNombreActivity.class,
                AltaMascotaTipoActivity.class,
                AltaMascotaGeneroActivity.class,
                AltaMascotaFotoPerfilActivity.class,
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

package com.goldenthread.infopetapp.controller.mascota.detalle;

import com.goldenthread.infopetapp.AppModule;
import com.goldenthread.infopetapp.controller.base.Presenter;
import com.goldenthread.infopetapp.controller.menuPrincipal.MenuPrincipalActivity;
import com.goldenthread.infopetapp.controller.menuPrincipal.MenuPrincipalPresenter;
import com.goldenthread.infopetapp.controller.menuPrincipal.MenuPrincipalPresenterImpl;
import com.goldenthread.infopetapp.controller.menuPrincipal.MenuPrincipalView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects ={
                DetalleMascotaActivity.class
        },
        addsTo = AppModule.class,
        library = true,
        complete = false
)
public class DetalleMascotaModule {
    private DetalleMascotaView view;

    public DetalleMascotaModule(DetalleMascotaView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public DetalleMascotaView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public DetalleMascotaPresenter provideMenuPrincipalPresenter(DetalleMascotaView view, Presenter presenter) {
        return new DetalleMascotaPresenterImpl(view, presenter);
    }
}

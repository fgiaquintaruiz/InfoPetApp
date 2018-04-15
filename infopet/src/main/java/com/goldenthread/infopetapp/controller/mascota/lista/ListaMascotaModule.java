package com.goldenthread.infopetapp.controller.mascota.lista;

import com.goldenthread.infopetapp.AppModule;
import com.goldenthread.infopetapp.controller.base.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects ={
                ListaMascotaActivity.class
        },
        addsTo = AppModule.class,
        library = true,
        complete = false
)
public class ListaMascotaModule {
    private ListaMascotaView view;

    public ListaMascotaModule(ListaMascotaView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public ListaMascotaView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public ListaMascotaPresenter provideMenuPrincipalPresenter(ListaMascotaView view, Presenter presenter) {
        return new ListaMascotaPresenterImpl(view, presenter);
    }
}

package com.goldenthread.infopetapp.controller.base;

import com.goldenthread.infopetapp.utils.PhotoUtils;
import com.goldenthread.infopetapp.utils.RoundImage;
import com.goldenthread.infopetapp.servicio.MascotaServicio;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        library = true,
        complete = false
)
public class PresenterModule {
    private ActivityView view;

    public PresenterModule() {
    }

    public PresenterModule(ActivityView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public ActivityView provideActivityView() {
        return view;
    }

    @Provides
    @Singleton
    public Presenter providePresenter(ActivityView view, MascotaServicio mascotaServicio, PhotoUtils photoUtils, RoundImage roundImage) {
        return new PresenterImpl(view, mascotaServicio, photoUtils, roundImage);
    }

}

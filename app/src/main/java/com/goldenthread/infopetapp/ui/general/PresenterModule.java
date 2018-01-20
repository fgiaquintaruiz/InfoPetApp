package com.goldenthread.infopetapp.ui.general;

import com.goldenthread.infopetapp.general.PhotoUtils;
import com.goldenthread.infopetapp.general.RoundImage;
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

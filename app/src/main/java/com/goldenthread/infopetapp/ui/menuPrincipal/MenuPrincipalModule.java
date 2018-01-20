package com.goldenthread.infopetapp.ui.menuPrincipal;

import com.goldenthread.infopetapp.AppModule;
import com.goldenthread.infopetapp.ui.general.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects ={
                MenuPrincipalActivity.class
        },
        addsTo = AppModule.class,
        library = true,
        complete = false
)
public class MenuPrincipalModule {
    private MenuPrincipalView view;

    public MenuPrincipalModule(MenuPrincipalView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public MenuPrincipalView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public MenuPrincipalPresenter provideMenuPrincipalPresenter(MenuPrincipalView view, Presenter presenter) {
        return new MenuPrincipalPresenterImpl(view, presenter);
    }
}

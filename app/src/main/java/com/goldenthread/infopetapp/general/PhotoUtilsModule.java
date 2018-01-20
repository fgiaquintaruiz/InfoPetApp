package com.goldenthread.infopetapp.general;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
       library = true,
       complete = false
)
public class PhotoUtilsModule {

    @Provides
    @Singleton
    public PhotoUtils providePhotoUtils(Application contexto) {
        return new PhotoUtilsImpl(contexto);
    }
}

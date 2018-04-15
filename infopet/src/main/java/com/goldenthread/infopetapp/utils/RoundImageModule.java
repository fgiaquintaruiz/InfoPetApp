package com.goldenthread.infopetapp.utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        library = true
)
public class RoundImageModule {

    @Provides
    @Singleton
    public RoundImage provideRoundImage() {
        return new RoundImageImpl(null);
    }
}
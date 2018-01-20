/*
 *
 *  *
 *  *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package com.goldenthread.infopetapp;

import android.app.Application;

import com.goldenthread.infopetapp.general.PhotoUtilsModule;
import com.goldenthread.infopetapp.general.RoundImageModule;
import com.goldenthread.infopetapp.negocio.NegocioModule;
import com.goldenthread.infopetapp.persistencia.DaoModule;
import com.goldenthread.infopetapp.servicio.MascotaMapeadorModule;
import com.goldenthread.infopetapp.servicio.MascotaServicioModule;
import com.goldenthread.infopetapp.ui.general.PresenterModule;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                App.class
        },
       includes = {
               NegocioModule.class,
               MascotaServicioModule.class,
               MascotaMapeadorModule.class,
               DaoModule.class,
               PhotoUtilsModule.class,
               RoundImageModule.class,
               PresenterModule.class
       }
)
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        return app;
    }
}

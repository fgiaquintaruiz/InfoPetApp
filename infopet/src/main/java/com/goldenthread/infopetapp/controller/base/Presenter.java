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

package com.goldenthread.infopetapp.controller.base;

import android.graphics.Bitmap;
import android.net.Uri;

import com.goldenthread.infopetapp.utils.PhotoSetter;
import com.goldenthread.infopetapp.utils.RoundImageImpl;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.filtro.MascotaFiltro;

import java.util.List;


public interface Presenter {

    void actualizar(MascotaDto mascotaDto) throws Exception;

    MascotaDto obtener(MascotaFiltro mascotaFiltro) throws Exception;

    List<MascotaDto> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception;

    void getImage(Uri uri, PhotoSetter photoSetter);

    RoundImageImpl getRoundImage(Bitmap bitmap);

    Integer crear(MascotaDto mascota) throws Exception;

    int borrar(Integer idMascota) throws Exception;
}

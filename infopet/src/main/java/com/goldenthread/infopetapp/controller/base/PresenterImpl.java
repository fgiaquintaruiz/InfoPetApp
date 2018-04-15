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
import com.goldenthread.infopetapp.utils.PhotoUtils;
import com.goldenthread.infopetapp.utils.RoundImage;
import com.goldenthread.infopetapp.utils.RoundImageImpl;
import com.goldenthread.infopetapp.servicio.MascotaServicio;
import com.goldenthread.infopetapp.controller.mascota.alta.OnFinishedListener;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.filtro.MascotaFiltro;

import java.util.List;

public class PresenterImpl implements Presenter, OnFinishedListener {

    private ActivityView view;
    private MascotaServicio mascotaServicio;
    private PhotoUtils photoUtils;
    private RoundImage roundImage;

    public PresenterImpl(ActivityView view, MascotaServicio mascotaServicio, PhotoUtils photoUtils,
                         RoundImage roundImage) {
        this.view = view;
        this.mascotaServicio = mascotaServicio;
        this.photoUtils = photoUtils;
        this.roundImage = roundImage;
    }

    public PresenterImpl(MascotaServicio mascotaServicio) {
        this.mascotaServicio = mascotaServicio;
    }

    @Override
    public void actualizar(MascotaDto mascotaDto) throws Exception {
        mascotaServicio.actualziar(mascotaDto);
    }

    @Override
    public int borrar(Integer idMascota) throws Exception {
        return mascotaServicio.borrar(idMascota);
    }

    @Override
    public MascotaDto obtener(MascotaFiltro mascotaFiltro) throws Exception {
        return mascotaServicio.obtener(mascotaFiltro);
    }

    @Override
    public List<MascotaDto> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception {
        return mascotaServicio.obtenerMascotas(mascotaFiltro);
    }

    @Override
    public void getImage(Uri uri, PhotoSetter photoSetter) {
        photoUtils.getImage(uri, photoSetter);
    }

    @Override
    public RoundImageImpl getRoundImage(Bitmap bitmap) {
        return roundImage.getRoundImage(bitmap);
    }

    @Override
    public Integer crear(MascotaDto mascota) throws Exception{
        return mascotaServicio.crear(mascota);
    }


}

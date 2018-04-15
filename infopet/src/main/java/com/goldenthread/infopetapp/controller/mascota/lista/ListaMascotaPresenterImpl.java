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

package com.goldenthread.infopetapp.controller.mascota.lista;

import android.graphics.Bitmap;
import android.net.Uri;

import com.goldenthread.infopetapp.controller.base.Presenter;
import com.goldenthread.infopetapp.controller.mascota.alta.OnFinishedListener;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.filtro.MascotaFiltro;
import com.goldenthread.infopetapp.utils.PhotoSetter;
import com.goldenthread.infopetapp.utils.RoundImageImpl;

import java.util.List;

public class ListaMascotaPresenterImpl implements ListaMascotaPresenter, OnFinishedListener {

    private ListaMascotaView view;
    private Presenter presenter;

    public ListaMascotaPresenterImpl(ListaMascotaView view, Presenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void actualizar(MascotaDto mascotaDto) throws Exception {
        presenter.actualizar(mascotaDto);
    }

    @Override
    public MascotaDto obtener(MascotaFiltro mascotaFiltro) throws Exception {
        return presenter.obtener(mascotaFiltro);
    }

    @Override
    public List<MascotaDto> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception {
        return presenter.obtenerMascotas(mascotaFiltro);
    }

    @Override
    public void getImage(Uri uri, PhotoSetter photoSetter) {
        presenter.getImage(uri,photoSetter);
    }

    @Override
    public RoundImageImpl getRoundImage(Bitmap bitmap) {
        return presenter.getRoundImage(bitmap);
    }

    @Override
    public Integer crear(MascotaDto mascota) throws Exception {
        return presenter.crear(mascota);
    }

    @Override
    public int borrar(Integer idMascota) throws Exception {
        return presenter.borrar(idMascota);
    }
}

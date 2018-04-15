package com.goldenthread.infopetapp.controller.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.goldenthread.infopetapp.controller.mascota.detalle.DetalleMascotaActivity;
import com.goldenthread.infopetapp.controller.mascota.lista.ListaMascotaActivity;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.controller.mascota.alta.AltaMascotaNombreActivity;
import com.goldenthread.infopetapp.controller.base.BaseActivity;
import com.goldenthread.infopetapp.controller.base.Presenter;
import com.goldenthread.infopetapp.utils.AccionEnum;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class InicioActivity extends BaseActivity implements InicioView {

    @Inject
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {
            List<MascotaDto> listaMascotas = presenter.obtenerMascotas(null);

            if (Validacion.isCollectionEmpty(listaMascotas)) {
//                 si no hay mascotas ir al alta
                Intent ningunaMascota = new Intent(this, AltaMascotaNombreActivity.class);
                ningunaMascota.putExtra(Constantes.ACCION, AccionEnum.ES_PRIMER_MASCOTA.toString());
                startActivity(ningunaMascota);

            } else if(listaMascotas.size() == 1){
                // si hay una mascota
                Intent unaMascota = new Intent(this, DetalleMascotaActivity.class);
                unaMascota.putExtra(Constantes.MASCOTA_DTO, listaMascotas.get(0));
                unaMascota.putExtra(Constantes.ACCION, AccionEnum.ES_PRIMER_MASCOTA.toString());
                startActivity(unaMascota);
            } else {
                // si hay varias mascotas
                Intent listaMascota = new Intent(this, ListaMascotaActivity.class);
                listaMascota.putExtra(Constantes.LIST_MASCOTA_DTO, listaMascotas.toArray());
                startActivity(listaMascota);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public List<Object> getModules() {
        List<Object> list = new ArrayList<>();
        list.add(new InicioModule(this));
        return list;
    }

}
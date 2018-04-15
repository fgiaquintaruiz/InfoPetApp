package com.goldenthread.infopetapp.controller.mascota.alta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.utils.EnumGeneral;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.controller.base.BaseActivity;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AltaMascotaGeneroActivity extends BaseActivity
        implements AltaMascotaView, View.OnClickListener {

    MascotaDto mascota;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_mascota_genero);

        Button btnGeneroMacho = findViewById(R.id.btn_genero_macho);
        Button btnGeneroHembra = findViewById(R.id.btn_genero_hembra);
        TextView mensajeMscota = findViewById(R.id.mensaje_genero_mascota);

        btnGeneroMacho.setOnClickListener(this);
        btnGeneroHembra.setOnClickListener(this);

        mascota = (MascotaDto) this.getIntent().getSerializableExtra(Constantes.MASCOTA_DTO);
        mensajeMscota.setText(String.format(getString(R.string.mensaje_genero_mascota),
                mascota.getTipoMamifero().getDescripcion().toLowerCase(),
                mascota.getNombre()));
    }

    public List<Object> getModules() {
        List<Object> list = new ArrayList<>();
        list.add(new AltaMascotaModule(this));
        return list;
    }

    @Override
    public void onClick(View v) {
        try {
            switch(v.getId()) {
                case R.id.btn_genero_macho:
                    mascota.setGenero(EnumGeneral.Genero.MACHO);
                    break;
                case R.id.btn_genero_hembra:
                    mascota.setGenero(EnumGeneral.Genero.HEMBRA);
                    break;
                default:
            }

            //se pasa a la seguiente pantalla
            Intent fechaNacimiento = new Intent(this, AltaMascotaFechaNacimientoActivity.class);
            fechaNacimiento.putExtra(Constantes.MASCOTA_DTO, mascota);

            // start the next Activity using your prepared Intent
            startActivity(fechaNacimiento);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

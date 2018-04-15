package com.goldenthread.infopetapp.controller.mascota.alta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.controller.base.BaseActivity;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class AltaMascotaFechaNacimientoActivity extends BaseActivity
        implements AltaMascotaView, View.OnClickListener {


    MascotaDto mascota;
    DatePicker fechaNacimiento;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_mascota_fecha_nacimiento);
        fechaNacimiento = findViewById(R.id.fecha_nacimiento);
        TextView mensajeMscota = findViewById(R.id.mensaje_fecha_nacimiento_mascota);
        Button btnContinuar = findViewById(R.id.btn_continuar);

        btnContinuar.setOnClickListener(this);

        mascota = (MascotaDto) this.getIntent().getSerializableExtra(Constantes.MASCOTA_DTO);
        mensajeMscota.setText(String.format(getString(R.string.mensaje_fecha_nacimiento_mascota),
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
//            TODO como se extrae un date picker???
            int day = fechaNacimiento.getDayOfMonth();
            int month = fechaNacimiento.getMonth();
            int year = fechaNacimiento.getYear();
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            mascota.setFechaNacimiento(cal.getTime());

            if(mascota.getIdMascota() != null){
                Log.i(this.getClass().toString(), "mascota actualizada: " + mascota.toString());
                altaMascotaPresenter.actualizar(mascota);
            } else {
                Integer id = altaMascotaPresenter.crear(mascota);
                if(Validacion.isNull(id)){
                    throw new Exception("Error al crear mascota");
                }
                mascota.setIdMascota(id);
            }

            //se pasa a la seguiente pantalla
            Intent fotoPerfil = new Intent(this, AltaMascotaFotoPerfilActivity.class);
            fotoPerfil.putExtra(Constantes.MASCOTA_DTO, mascota);

            // start the next Activity using your prepared Intent
            startActivity(fotoPerfil);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

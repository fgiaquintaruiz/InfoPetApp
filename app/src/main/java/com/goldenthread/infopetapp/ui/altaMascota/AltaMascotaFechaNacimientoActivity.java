package com.goldenthread.infopetapp.ui.altaMascota;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.general.Constantes;
import com.goldenthread.infopetapp.general.EnumGeneral;
import com.goldenthread.infopetapp.ui.dto.MascotaDto;
import com.goldenthread.infopetapp.ui.general.BaseActivity;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class AltaMascotaFechaNacimientoActivity extends BaseActivity
        implements AltaMascotaView, View.OnClickListener {

    DatePicker fechaNacimiento = findViewById(R.id.fecha_nacimiento);
    TextView mensajeMscota = findViewById(R.id.mensaje_fecha_nacimiento_mascota);
    Button btnContinuar = findViewById(R.id.btn_continuar);
    MascotaDto mascota;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_mascota_genero);

        btnContinuar.setOnClickListener(this);

        mascota = (MascotaDto) this.getIntent().getSerializableExtra(Constantes.DTO);
        mensajeMscota.setText(String.format(getString(R.string.mensaje_fecha_nacimiento_mascota),
                mascota.getTipoMamifero().getDescripcion().toLowerCase(),
                mascota.getNombre()));
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AltaMascotaModule(this));
    }

    @Override
    public void onClick(View v) {
        try {
//            TODO como se extrae un date picker???
//            mascota.setFechaNacimiento();

            Integer id = altaMascotaPresenter.crear(mascota);
            if(Validacion.isNull(id)){
                throw new Exception("Error al crear mascota");
            }
            mascota.setIdMascota(id);

            //se pasa a la seguiente pantalla
            Intent PrimeraVezFechaNacimientoRiu = new Intent(this, AltaMascotaFechaNacimientoActivity.class);
            PrimeraVezFechaNacimientoRiu.putExtra(Constantes.DTO, mascota);

            // start the next Activity using your prepared Intent
            startActivity(PrimeraVezFechaNacimientoRiu);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

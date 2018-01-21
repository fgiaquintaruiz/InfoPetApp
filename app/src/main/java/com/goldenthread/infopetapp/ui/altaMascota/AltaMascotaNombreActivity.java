package com.goldenthread.infopetapp.ui.altaMascota;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.general.Constantes;
import com.goldenthread.infopetapp.ui.dto.MascotaDto;
import com.goldenthread.infopetapp.ui.general.BaseActivity;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class AltaMascotaNombreActivity extends BaseActivity
        implements AltaMascotaView, TextView.OnEditorActionListener {

    EditText nombreMascota;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      TODO unificar el alta de mascotas en un activity con varios fragment
        // TODO agregar condicion si ya hay una mascota, ir al menu cargando mascota
        // TODO si hay mas de una, ir a la lista de mascotas
        try {
            List<MascotaDto> listaMascotas = altaMascotaPresenter.obtenerMascotas(null);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.alta_mascota_nombre);


        //Se activa un observador sobre la edicion del campo de texto
        nombreMascota = (EditText) findViewById(R.id.nombre_mascota);
        nombreMascota.setOnEditorActionListener(this);

        //Se muestra el teclado virtual apenas empieza la aplicacion
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AltaMascotaModule(this));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_DONE) {

            String nombreMascotaText = v.getText().toString().trim();

            if (Validacion.isEmpty(nombreMascotaText) == false) {

                if (Validacion.esFormatoTexto(nombreMascotaText)) {

                    try {

                        MascotaDto mascota = new MascotaDto();
                        mascota.setNombre(nombreMascotaText);

                        Intent PrimeraVezTipoRiu = new Intent(this, AltaMascotaTipoActivity.class);
                        PrimeraVezTipoRiu.putExtra(Constantes.DTO, mascota);
                        // start the next Activity using your prepared Intent
                        startActivity(PrimeraVezTipoRiu);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }else{
                    nombreMascota.setError(getString(R.string.error_nombre_mascota_formato_texto));
                }

            } else {
                nombreMascota.setError(getString(R.string.error_nombre_mascota_vacio));
            }
//       se avisa que se manejo el evento
            handled = true;
        }
        return handled;
    }
}

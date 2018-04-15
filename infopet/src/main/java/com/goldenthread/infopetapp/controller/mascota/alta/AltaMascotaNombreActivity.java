package com.goldenthread.infopetapp.controller.mascota.alta;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.utils.AccionEnum;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.controller.base.BaseActivity;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AltaMascotaNombreActivity extends BaseActivity
        implements AltaMascotaView, TextView.OnEditorActionListener {

    EditText nombreMascota;
    MascotaDto mascota;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se carga la vista
        setContentView(R.layout.alta_mascota_nombre);

        //Se activa un observador sobre la edicion del campo de texto
        nombreMascota = findViewById(R.id.nombre_mascota);
        nombreMascota.setOnEditorActionListener(this);

        AccionEnum accion = AccionEnum.getAccionFromCodigo(this.getIntent().getStringExtra(Constantes.ACCION));

        if(AccionEnum.ES_SEGUNDA_MASCOTA.equals(accion)) {
            TextView mensajeBienvenida = findViewById(R.id.mensaje_bienvenida);
            mensajeBienvenida.setVisibility(View.INVISIBLE);

            TextView mensajeIntroduccion = findViewById(R.id.mensaje_introduccion);
            mensajeIntroduccion.setText(R.string.mensaje_introduccion_otra_mascota);
        }

        if(AccionEnum.EDITAR_MASCOTA.equals(accion)) {
            TextView mensajeBienvenida = findViewById(R.id.mensaje_bienvenida);
            mensajeBienvenida.setVisibility(View.INVISIBLE);

            TextView mensajeIntroduccion = findViewById(R.id.mensaje_introduccion);
            mensajeIntroduccion.setText(R.string.mensaje_introduccion_editar_mascota);
            mascota = (MascotaDto) this.getIntent().getExtras().get(Constantes.MASCOTA_DTO);

            if(mascota != null){
                nombreMascota.setText(mascota.getNombre());
            }
        }


        //Se muestra el teclado virtual apenas empieza la aplicacion
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public List<Object> getModules() {
        List<Object> list = new ArrayList<>();
        list.add(new AltaMascotaModule(this));
        return list;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        boolean handled = false;

        if (actionId == EditorInfo.IME_ACTION_DONE) {

            String nombreMascotaText = v.getText().toString().trim();

            if (!Validacion.isEmpty(nombreMascotaText)) {

                if (Validacion.esFormatoTexto(nombreMascotaText)) {

                    try {

                        if(this.mascota == null){
                            this.mascota = new MascotaDto();
                        }

                        this.mascota.setNombre(nombreMascotaText);

                        Intent tipoMascota = new Intent(this, AltaMascotaTipoActivity.class);
                        tipoMascota.putExtra(Constantes.MASCOTA_DTO, this.mascota);

                        // start the next Activity using your prepared Intent
                        startActivity(tipoMascota);

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

            //se avisa que se manejo el evento
            handled = true;
        }
        return handled;
    }
}

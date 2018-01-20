package com.goldenthread.infopetapp.ui.altaMascota;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.general.Constantes;
import com.goldenthread.infopetapp.general.EnumGeneral;
import com.goldenthread.infopetapp.general.PhotoSetter;
import com.goldenthread.infopetapp.ui.dto.MascotaDto;
import com.goldenthread.infopetapp.ui.general.BaseActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class AltaMascotaTipoActivity extends BaseActivity
        implements AltaMascotaView, View.OnClickListener {

    private ImageView btn_tipo_perro, btn_tipo_gato;
    private TextView mensaje_tipo_mascota;
    private MascotaDto mascota;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_mascota_tipo);

//      TODO en la segunda version tiene que manejar una lista conectada a un servicio REST JSON una lista de tipo mascota

        btn_tipo_perro = (ImageView) findViewById(R.id.btn_tipo_perro);
        btn_tipo_gato = (ImageView) findViewById(R.id.btn_tipo_gato);

        btn_tipo_perro.setOnClickListener(this);
        btn_tipo_gato.setOnClickListener(this);

        mensaje_tipo_mascota = (TextView) findViewById(R.id.mensaje_tipo_mascota);

        mascota = (MascotaDto) this.getIntent().getSerializableExtra(Constantes.DTO);

        mensaje_tipo_mascota.setText(String.format(getString(R.string.mensaje_tipo_mascota), mascota.getNombre()));
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AltaMascotaModule(this));
    }

    public void getBitmap(Uri uri) {
        altaMascotaPresenter.getImage(uri, new PhotoSetter() {
            @Override
            public void onPhotoDownloaded(Bitmap bitmap) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            switch(v.getId()) {
                case R.id.btn_tipo_perro:
                    mascota.setTipoMamifero(EnumGeneral.TipoMamifero.PERRO);
                    break;
                case R.id.btn_tipo_gato:
                    mascota.setTipoMamifero(EnumGeneral.TipoMamifero.GATO);
                    break;
                default:
            }

//            altaMascotaPresenter.actualizar(mascota);

            //se pasa a la seguiente pantalla
            Intent PrimeraVezGeneroRiu = new Intent(this, AltaMascotaGeneroActivity.class);
            PrimeraVezGeneroRiu.putExtra(Constantes.DTO, mascota);

            // start the next Activity using your prepared Intent
            startActivity(PrimeraVezGeneroRiu);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

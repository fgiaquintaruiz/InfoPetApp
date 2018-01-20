package com.goldenthread.infopetapp.ui.menuPrincipal;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.general.Constantes;
import com.goldenthread.infopetapp.ui.dto.MascotaDto;
import com.goldenthread.infopetapp.ui.filtro.MascotaFiltro;
import com.goldenthread.infopetapp.ui.general.BaseActivity;
import com.goldenthread.infopetapp.validacion.Validacion;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class MenuPrincipalActivity extends BaseActivity implements MenuPrincipalView, View.OnClickListener {

    private Uri mImageUri;
    private MascotaDto mascota;
    TextView mensaje_foto_perfil;

    @Inject
    MenuPrincipalPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

//        photoViewer = (ImageView) findViewById(R.id.img_foto_perfil);
//
//        btn_foto_perfil_continuar = (Button) findViewById(R.id.btn_foto_perfil_continuar);
//        btn_foto_perfil_continuar.setOnClickListener(this);
//
        mensaje_foto_perfil = (TextView) findViewById(R.id.mensaje_foto_perfil);
        mascota = (MascotaDto) this.getIntent().getSerializableExtra(Constantes.DTO);
        String datos = "-";
        try {
            MascotaDto dto = presenter.obtener(new MascotaFiltro(mascota.getIdMascota()));
            if(!Validacion.isNull(dto)) {
                datos = dto.getNombre()+"\n";
                datos += dto.getTipoMamifero().getDescripcion()+"\n";
                datos += dto.getGenero().getDescripcion()+"\n";
                if(!Validacion.isNull(dto.getFechaNacimiento())) {
                    datos += dto.getFechaNacimiento().toString()+"\n";
                }
                if(!Validacion.isEmpty(dto.getUriFotoPerfil())) {
                    datos += dto.getUriFotoPerfil();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mensaje_foto_perfil.setText(datos);
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MenuPrincipalModule(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        if (mImageUri != null)
//            outState.putString("Uri", mImageUri.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        if (savedInstanceState.containsKey("Uri")) {
//            mImageUri = Uri.parse(savedInstanceState.getString("Uri"));
//        }
    }

    @Override
    public void onClick(View v) {
        try {
//            presenter.actualizar(mascota);
//
//            Intent MenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
//            MenuPrincipal.putExtra("baseDto", mascota);
//            // start the next Activity using your prepared Intent
//            startActivity(MenuPrincipal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
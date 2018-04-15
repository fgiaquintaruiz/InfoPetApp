package com.goldenthread.infopetapp.controller.mascota.detalle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.controller.base.BaseActivity;
import com.goldenthread.infopetapp.controller.inicio.InicioActivity;
import com.goldenthread.infopetapp.controller.mascota.alta.AltaMascotaNombreActivity;
import com.goldenthread.infopetapp.controller.mascota.lista.ListaMascotaActivity;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.utils.AccionEnum;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.utils.PhotoUtilsImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class DetalleMascotaActivity extends BaseActivity implements DetalleMascotaView{

    private MascotaDto mascota;
    private ImageView fotoMenu;
    private TextView nombre;
    private TextView tipo;
    private TextView fechaNacimiento;
    private AccionEnum accion;

    private static final int MNU_AGREGAR_MASCOTA = 3;

    @Inject
    DetalleMascotaPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_mascota);

        Toolbar toolbar = findViewById(R.id.menu_principal_toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView titulo = toolbar.findViewById(R.id.menu_title);
        titulo.setText(R.string.titulo_detalle_mascota);

        if(this.getIntent().getExtras() != null){
            mascota = (MascotaDto) this.getIntent().getExtras().get(Constantes.MASCOTA_DTO);
        }
        accion = AccionEnum.getAccionFromCodigo(this.getIntent().getStringExtra(Constantes.ACCION));

        fotoMenu = findViewById(R.id.detalle_foto);
        nombre = findViewById(R.id.detalle_nombre);
        tipo = findViewById(R.id.detalle_tipo);
        fechaNacimiento = findViewById(R.id.detalle_fecha_nacimiento);

        fotoMenu.setImageBitmap(PhotoUtilsImpl.recuperarBitmap(mascota.getUriFotoPerfil()));
        nombre.setText(mascota.getNombre());
        tipo.setText(mascota.getTipoMamifero().getDescripcion());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaNacimiento.setText(sdf.format(mascota.getFechaNacimiento()));
    }

    @Override
    public List<Object> getModules() {
        List<Object> list = new ArrayList<>();
        list.add(new DetalleMascotaModule(this));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(AccionEnum.ES_PRIMER_MASCOTA.equals(accion)) {
            // mostrar boton de alta
            menu.add(Menu.NONE, MNU_AGREGAR_MASCOTA, Menu.NONE, R.string.btn_agregar_mascota)
                    .setIcon(R.drawable.ic_add_white_36dp)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            // mostrar boton atras
        }
        getMenuInflater().inflate(R.menu.menu_detalle_mascota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_detalle_editar:
                Intent editarMascota = new Intent(DetalleMascotaActivity.super.getApplicationContext(), AltaMascotaNombreActivity.class);
                editarMascota.putExtra(Constantes.ACCION, AccionEnum.EDITAR_MASCOTA.toString());
                editarMascota.putExtra(Constantes.MASCOTA_DTO, mascota);
                startActivity(editarMascota);
                return true;

            case R.id.menu_detalle_eliminar:
                // mostrar boton de confirmacion y si hace clic en ok se elimina si no se cancela la accion

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle(R.string.titulo_confirmacion_eliminar_mascota)
                        .setMessage(String.format(getString(R.string.mensaje_confirmacion_eliminar_mascota), mascota.getNombre()))
                        .setPositiveButton(R.string.confirmacion_si, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    presenter.borrar(mascota.getIdMascota());
                                    Intent inicio = new Intent(DetalleMascotaActivity.super.getApplicationContext(), InicioActivity.class);
                                    startActivity(inicio);
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();

                                    Toast.makeText(DetalleMascotaActivity.this, String.format(getString(R.string.error_al_borrar_mascota), mascota.getNombre()), Toast.LENGTH_SHORT).show();
                                }


                            }

                        })
                        .setNegativeButton(R.string.confirmacion_no, null)
                        .show();
                return true;

            case MNU_AGREGAR_MASCOTA:
                // agregar segunda mascota
                Intent altaDesdeMenu = new Intent(DetalleMascotaActivity.super.getApplicationContext(), AltaMascotaNombreActivity.class);
                altaDesdeMenu.putExtra(Constantes.ACCION, AccionEnum.ES_SEGUNDA_MASCOTA.toString());
                startActivity(altaDesdeMenu);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

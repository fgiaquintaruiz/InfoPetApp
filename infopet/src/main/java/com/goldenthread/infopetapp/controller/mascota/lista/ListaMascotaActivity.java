package com.goldenthread.infopetapp.controller.mascota.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.controller.base.BaseActivity;
import com.goldenthread.infopetapp.controller.mascota.alta.AltaMascotaNombreActivity;
import com.goldenthread.infopetapp.utils.AccionEnum;
import com.goldenthread.infopetapp.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ListaMascotaActivity extends BaseActivity implements ListaMascotaView {

    private Object[] listaMascotasObj;
    private ListView lista;

    @Inject
    ListaMascotaPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_mascota);

        try {

            // Find the toolbar view inside the activity layout
            Toolbar toolbar = findViewById(R.id.menu_principal_toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayShowTitleEnabled(false);

            TextView titulo = toolbar.findViewById(R.id.menu_title);
            titulo.setText(R.string.titulo_lista_mascota);

            // logica para mostrar la lista de mascotas o el detalle
            listaMascotasObj = (Object[]) this.getIntent().getExtras().get(Constantes.LIST_MASCOTA_DTO);

            lista = findViewById(R.id.list);
            lista.setAdapter(new ListaMascotaAdapter(this, listaMascotasObj));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object> getModules() {
        List<Object> list = new ArrayList<>();
        list.add(new ListaMascotaModule(this));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lista_mascota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_plus:
                Intent altaDesdeMenu = new Intent(ListaMascotaActivity.super.getApplicationContext(), AltaMascotaNombreActivity.class);
                altaDesdeMenu.putExtra(Constantes.ACCION, AccionEnum.ES_SEGUNDA_MASCOTA.toString());
                startActivity(altaDesdeMenu);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
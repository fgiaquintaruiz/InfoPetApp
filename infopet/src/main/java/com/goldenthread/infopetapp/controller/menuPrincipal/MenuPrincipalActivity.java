package com.goldenthread.infopetapp.controller.menuPrincipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.controller.mascota.alta.AltaMascotaNombreActivity;
import com.goldenthread.infopetapp.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MenuPrincipalActivity extends AppCompatActivity implements MenuPrincipalView {

    @Inject
    MenuPrincipalPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.menu_principal);

    }

    public List<Object> getModules() {
        List<Object> list = new ArrayList<>();
        list.add(new MenuPrincipalModule(this));
        return list;
    }

}
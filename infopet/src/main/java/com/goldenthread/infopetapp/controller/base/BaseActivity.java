package com.goldenthread.infopetapp.controller.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.goldenthread.infopetapp.App;

import java.util.List;

import dagger.ObjectGraph;

public abstract class BaseActivity extends AppCompatActivity implements ActivityView {

    private ObjectGraph activityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((App) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);

    }

    @Override protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    public abstract List<Object> getModules();

}

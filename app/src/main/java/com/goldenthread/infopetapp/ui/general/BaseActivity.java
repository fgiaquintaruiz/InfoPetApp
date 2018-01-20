package com.goldenthread.infopetapp.ui.general;

import android.app.Activity;
import android.os.Bundle;

import com.goldenthread.infopetapp.App;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Paula on 16/02/2015.
 */
public abstract class BaseActivity extends Activity implements ActivityView {

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

    protected abstract List<Object> getModules();

}

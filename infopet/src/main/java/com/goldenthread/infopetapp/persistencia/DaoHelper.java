package com.goldenthread.infopetapp.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DaoHelper extends OrmLiteSqliteOpenHelper {

    // the DAO object we use to access the Mascota table
    private Dao<IpMascota, Integer> simpleDao = null;
    private RuntimeExceptionDao<IpMascota, Integer> simpleRuntimeDao = null;

    public DaoHelper(Context context) {
        super(context, Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DaoHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, IpMascota.class);
        } catch (SQLException e) {
            Log.e(DaoHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DaoHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, IpMascota.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DaoHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our Mascota class. It will create it or just give the cached
     * value.
     */
    public Dao<IpMascota, Integer> getMascotaDao() throws SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(IpMascota.class);
        }
        return simpleDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Mascota class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<IpMascota, Integer> getMascotaDaoRe() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(IpMascota.class);
        }
        return simpleRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        simpleDao = null;
        simpleRuntimeDao = null;
    }
}

package com.goldenthread.infopetapp.persistencia;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.goldenthread.infopetapp.filtro.MascotaFiltro;
import com.goldenthread.infopetapp.persistencia.modelo.IpMascota;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.validacion.Validacion;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MascotaDaoImpl extends OrmLiteBaseService<DaoHelper> implements MascotaDao {

    private Application contexto;

    private SharedPreferences mSharedPreferences;

    public MascotaDaoImpl(Application contexto) {
        this.contexto = contexto;

        if (contexto != null) {
            this.daoHelper = OpenHelperManager.getHelper(contexto, DaoHelper.class);
            this.mSharedPreferences = contexto.getSharedPreferences(Constantes.PREFS, Context.MODE_PRIVATE);
        }
    }

    private DaoHelper daoHelper;

    private void getDaoHelper() {
        if (daoHelper == null) {
            daoHelper = OpenHelperManager.getHelper(contexto, DaoHelper.class);
        }
    }

    private void releaseDaoHelper() {
        if (daoHelper != null) {
            OpenHelperManager.releaseHelper();
            daoHelper = null;
        }
    }

    @Override
    public Integer crear(IpMascota modelo) throws Exception {
        getDaoHelper();
        Integer idNuevaMascota = null;
        if (!Validacion.isNull(daoHelper)) {
            try {
                modelo = daoHelper.getMascotaDao().createIfNotExists(modelo);

                if (!Validacion.isNull(modelo.getIdMascota())) {
                    idNuevaMascota = modelo.getIdMascota();
                }
                releaseDaoHelper();
            } catch (SQLException e) {
                throw new Exception(e.getMessage(), e);
            }

        }
        return idNuevaMascota;
    }

    @Override
    public int actualizar(IpMascota modelo) throws Exception {
        getDaoHelper();
        int resultado = 0;
        if (!Validacion.isNull(daoHelper)) {

            try {
                resultado = daoHelper.getMascotaDao().update(modelo);
            } catch (SQLException e) {
                throw new Exception(e.getMessage(), e);
            }
        }
        releaseDaoHelper();
        return resultado;
    }

    @Override
    public int borrar(Integer idMascota) throws Exception {
        getDaoHelper();
        int resultado = 0;
        if (!Validacion.isNull(daoHelper)) {

            try {
                resultado = daoHelper.getMascotaDao().deleteById(idMascota);
            } catch (SQLException e) {
                throw new Exception(e.getMessage(), e);
            }
        }
        releaseDaoHelper();
        return resultado;
    }

    @Override
    public IpMascota obtener(MascotaFiltro mascotaFiltro) throws Exception {
        getDaoHelper();

        IpMascota modelo = null;

        if (!Validacion.isNull(daoHelper)) {
            if (!Validacion.isNull(mascotaFiltro.getIdMascota())) {
                modelo = daoHelper.getMascotaDao().queryForId(mascotaFiltro.getIdMascota());
            }
        }
        releaseDaoHelper();
        return modelo;
    }

    @Override
    public List<IpMascota> obtenerMascotas(MascotaFiltro mascotaFiltro) throws Exception {

        getDaoHelper();

        List<IpMascota> listaMascotas = new ArrayList<>();

        if (!Validacion.isNull(daoHelper)) {

            QueryBuilder <IpMascota, Integer> qb = daoHelper.getMascotaDao().queryBuilder();

            if(mascotaFiltro != null) {

                if (!Validacion.isNull(mascotaFiltro.getIdMascota())) {
                    qb.where().eq("idMascota", mascotaFiltro.getIdMascota());
                }
                if (!Validacion.isEmpty(mascotaFiltro.getNombre())) {
                    qb.where().eq("nombre", mascotaFiltro.getNombre());
                }
                if (!Validacion.isNull(mascotaFiltro.getTipoMamifero())) {
                    qb.where().eq("tipoMamifero", mascotaFiltro.getTipoMamifero().getCodigo());
                }
                if (!Validacion.isNull(mascotaFiltro.getGenero())) {
                    qb.where().eq("genero", mascotaFiltro.getGenero().getCodigo());
                }
                if (!Validacion.isNull(mascotaFiltro.getFechaNacimiento())) {
                    qb.where().eq("fechaNacimiento", mascotaFiltro.getFechaNacimiento());
                }
            }

            listaMascotas = daoHelper.getMascotaDao().query(qb.prepare());
        }

        releaseDaoHelper();
        return listaMascotas;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

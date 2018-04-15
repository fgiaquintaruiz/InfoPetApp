package com.goldenthread.infopetapp.controller.mascota.alta;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.controller.inicio.InicioActivity;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.utils.EnumGeneral;
import com.goldenthread.infopetapp.utils.PhotoUtilsImpl;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.controller.base.BaseActivity;
import com.takusemba.cropme.CropView;
import com.takusemba.cropme.OnCropListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AltaMascotaFotoPerfilActivity extends BaseActivity implements AltaMascotaView, View.OnClickListener {

    private AlertDialog _photoDialog;
    private Uri mImageUri;
    private MascotaDto mascota;

    private Button btn_foto_perfil_continuar;
    private ImageView photoViewer;
    private CropView cropView;
    private TextView mensaje_foto_perfil;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_mascota_foto);

        photoViewer = findViewById(R.id.img_foto_perfil);
        getPhotoDialog();
        setPhotoButton();

        btn_foto_perfil_continuar = findViewById(R.id.btn_continuar);
        btn_foto_perfil_continuar.setOnClickListener(this);

        mensaje_foto_perfil = findViewById(R.id.mensaje_foto_perfil);
        mascota = (MascotaDto) this.getIntent().getSerializableExtra(Constantes.MASCOTA_DTO);
        cropView = findViewById(R.id.crop_foto_perfil);

        String mensajeTipoGenero;
        String aux = mascota.getTipoMamifero().getDescripcion();
        if (mascota.getGenero().equals(EnumGeneral.Genero.MACHO)) {
            mensajeTipoGenero = aux.substring(0, aux.length() - 1).toLowerCase() + getString(R.string.mensaje_diminutivo_tipo_mascota_macho);
        } else {
            mensajeTipoGenero = aux.substring(0, aux.length() - 1).toLowerCase() + getString(R.string.mensaje_diminutivo_tipo_mascota_hembra);
        }
        mensaje_foto_perfil.setText(String.format(getString(R.string.mensaje_foto_perfil),
                mensajeTipoGenero,
                mascota.getNombre()));
    }

    public List<Object> getModules() {
        List<Object> list = new ArrayList<>();
        list.add(new AltaMascotaModule(this));
        return list;
    }

    private void setPhotoButton() {
        photoViewer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!getPhotoDialog().isShowing() && !isFinishing())
                    getPhotoDialog().show();
            }
        });
    }

    // TODO armar menu tipo whatsapp con icono de camara y galeria
    private AlertDialog getPhotoDialog() {
        if (_photoDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    AltaMascotaFotoPerfilActivity.this);
            builder.setTitle(getString(R.string.mensaje_dialogo_carga_foto_perfil));
            builder.setPositiveButton(getString(R.string.btn_sacar_foto), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File photo = null;
                try {
                    // place where to store camera taken picture
                    photo = PhotoUtilsImpl.createTemporaryFile("picture", ".jpg", AltaMascotaFotoPerfilActivity.this);
                    photo.delete();
                } catch (Exception e) {
                    Log.v(getClass().getSimpleName(), getString(R.string.error_no_se_puede_crear_archivo_foto));
                }
                mImageUri = Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent, Constantes.ACTIVITY_SELECT_FROM_CAMERA);
                }
            });

            builder.setNegativeButton(getString(R.string.btn_cargar_foto_galeria), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                Intent galleryIntent;

                if (Build.VERSION.SDK_INT < 19) {
                    galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                } else {
                    galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Constantes.KITKAT_VALUE);
                }
            });
            _photoDialog = builder.create();
        }
        return _photoDialog;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mImageUri != null)
            outState.putString("Uri", mImageUri.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("Uri")) {
            mImageUri = Uri.parse(savedInstanceState.getString("Uri"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == Constantes.KITKAT_VALUE || requestCode == Constantes.ACTIVITY_SELECT_FROM_CAMERA) && resultCode == RESULT_OK) {
            if(data != null && data.getData() != null){
                mImageUri = data.getData();
            }

            performCrop(mImageUri);
        }
    }

    /**
     * this function does the crop operation.
     */
    private void performCrop(Uri uri) {
        // take care of exceptions
        try {
            cropView.setUri(uri);

            photoViewer.setVisibility(View.INVISIBLE);
            cropView.setVisibility(View.VISIBLE);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast.makeText(this, getString(R.string.error_recorte_imagen), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onClick(View v) {

        if(mImageUri != null){

            cropView.crop(new OnCropListener() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    try {
                        // user is returning from cropping the image
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        String bitmapStr = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

                        //Se actualiza la foto de la mascota
                        mascota.setUriFotoPerfil(bitmapStr);
                        altaMascotaPresenter.actualizar(mascota);

                        irInicioActivity();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(AltaMascotaFotoPerfilActivity.super.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure() {
                    System.out.println("fallo el crop");
                }

            });
        } else {
            irInicioActivity();
        }

    }

    private void irInicioActivity() {

        Intent inicio = new Intent(AltaMascotaFotoPerfilActivity.super.getApplicationContext(), InicioActivity.class);

        // start the next Activity using your prepared Intent
        startActivity(inicio);
    }

}
package com.goldenthread.infopetapp.ui.altaMascota;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.general.Constantes;
import com.goldenthread.infopetapp.general.EnumGeneral;
import com.goldenthread.infopetapp.general.PhotoSetter;
import com.goldenthread.infopetapp.general.PhotoUtilsImpl;
import com.goldenthread.infopetapp.general.RoundImageImpl;
import com.goldenthread.infopetapp.ui.dto.MascotaDto;
import com.goldenthread.infopetapp.ui.general.BaseActivity;
import com.goldenthread.infopetapp.ui.menuPrincipal.MenuPrincipalActivity;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Paula on 15/02/2015.
 */
public class AltaMascotaFotoPerfilActivity extends BaseActivity implements AltaMascotaView, View.OnClickListener {

    private AlertDialog _photoDialog;
    private Uri mImageUri;
    private MascotaDto mascota;

    private Button btn_foto_perfil_continuar;
    private ImageView photoViewer;
    private TextView mensaje_foto_perfil;

    @Inject
    AltaMascotaPresenter altaMascotaPresenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_mascota_foto);

        photoViewer = (ImageView) findViewById(R.id.img_foto_perfil);
        getPhotoDialog();
        setPhotoButton();

        btn_foto_perfil_continuar = (Button) findViewById(R.id.btn_foto_perfil_continuar);
        btn_foto_perfil_continuar.setOnClickListener(this);

        mensaje_foto_perfil = (TextView) findViewById(R.id.mensaje_foto_perfil);
        mascota = (MascotaDto) this.getIntent().getSerializableExtra(Constantes.DTO);

        String mensajeTipoGenero;
        String aux = mascota.getTipoMamifero().getDescripcion();
        if (mascota.getGenero().equals(EnumGeneral.Genero.MACHO)) {
            mensajeTipoGenero = aux.substring(0, aux.length() - 1).toLowerCase() + getString(R.string.mensajeDiminutivoTipoMascotaMacho);
        } else {
            mensajeTipoGenero = aux.substring(0, aux.length() - 1).toLowerCase() + getString(R.string.mensajeDiminutivoTipoMascotaHembra);
        }
        mensaje_foto_perfil.setText(String.format(getString(R.string.mensaje_foto_perfil),
                mensajeTipoGenero,
                mascota.getNombre()));
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AltaMascotaModule(this));
    }

    private void setPhotoButton() {
        photoViewer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!getPhotoDialog().isShowing() && !isFinishing())
                    getPhotoDialog().show();
            }
        });
    }

    // TODO armar menu tipo whatapp con icono de camara y galeria
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
//                        place where to store camera taken picture
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
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent, Constantes.KITKAT_VALUE);
                    } else {
                        galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, Constantes.KITKAT_VALUE);
                    }
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
            mImageUri = data.getData();
            getBitmap(mImageUri);
        }// user is returning from cropping the image
        else if (requestCode == Constantes.CROP_PIC && resultCode == RESULT_OK) {
            //get the returned data
            Bundle extras = data.getExtras();
            //get the cropped bitmap
            Bitmap bitmap = extras.getParcelable("data");
            //display the returned cropped image
            setBitmapImageView(bitmap);

            mascota.setUriFotoPerfil(mImageUri.getPath());
        }
    }

    public void getBitmap(Uri uri) {

        PhotoSetter photo = new PhotoSetter() {
            @Override
            public void onPhotoDownloaded(Bitmap bitmap) {
                performCrop(mImageUri);
            }
        };
        altaMascotaPresenter.getImage(uri, photo);
    }

    private void showErrorToast() {
        Toast.makeText(this, getString(R.string.error_cargar_foto), Toast.LENGTH_LONG).show();
    }

    public void setBitmapImageView(Bitmap bitmap) {
//        RoundImage roundedImage = new RoundImage(bitmap);
        RoundImageImpl roundedImage = altaMascotaPresenter.getRoundImage(bitmap);
        photoViewer.setImageDrawable(roundedImage);
    }

    /**
     * this function does the crop operation.
     */
    private void performCrop(Uri uri) {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setData(uri);
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 250);
            cropIntent.putExtra("outputY", 250);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
//            cropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, Constantes.CROP_PIC);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast.makeText(this, getString(R.string.error_recorte_imagen), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            altaMascotaPresenter.actualizar(mascota);

            Intent MenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
            MenuPrincipal.putExtra(Constantes.DTO, mascota);
            // start the next Activity using your prepared Intent
            startActivity(MenuPrincipal);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public Uri getMImageUri() {
        return mImageUri;
    }

    public void setMImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }

}
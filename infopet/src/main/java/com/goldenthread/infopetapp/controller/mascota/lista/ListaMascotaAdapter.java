package com.goldenthread.infopetapp.controller.mascota.lista;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goldenthread.infopetapp.R;
import com.goldenthread.infopetapp.controller.mascota.detalle.DetalleMascotaActivity;
import com.goldenthread.infopetapp.dto.MascotaDto;
import com.goldenthread.infopetapp.utils.Constantes;
import com.goldenthread.infopetapp.utils.PhotoUtilsImpl;

public class ListaMascotaAdapter extends ArrayAdapter<Object> {

    private final Context context;
    private final Object[] listaMascotas;

    public ListaMascotaAdapter(Context context, Object[] listaMascotas) {
        super(context, R.layout.item_lista_mascota, listaMascotas);
        this.context = context;
        this.listaMascotas = listaMascotas;
    }

    class ViewHolder {
        TextView nombre;
        ImageView foto;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View item = convertView;
        ViewHolder holder;
        MascotaDto mascota = (MascotaDto) listaMascotas[position];

        if(item == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.item_lista_mascota, parent, false);

            holder = new ViewHolder();
            holder.nombre = item.findViewById(R.id.nombre);
            holder.nombre.setText(mascota.getNombre());

            holder.foto = item.findViewById(R.id.foto);
            if(mascota.getUriFotoPerfil() != null) {
                holder.foto.setImageBitmap(PhotoUtilsImpl.recuperarBitmap(mascota.getUriFotoPerfil()));
            }

            item.setTag(holder);

            item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //start detalle activity
                    MascotaDto mascota = (MascotaDto) listaMascotas[position];
                    Intent detalleMascota = new Intent(context, DetalleMascotaActivity.class);
                    detalleMascota.putExtra(Constantes.MASCOTA_DTO, mascota);
                    context.startActivity(detalleMascota);

                }
            });
        } else {
            holder = (ViewHolder) item.getTag();
            holder.nombre.setText(mascota.getNombre());
            if(mascota.getUriFotoPerfil() != null) {
                holder.foto.setImageBitmap(PhotoUtilsImpl.recuperarBitmap(mascota.getUriFotoPerfil()));
            }
        }






        return item;
    }


}
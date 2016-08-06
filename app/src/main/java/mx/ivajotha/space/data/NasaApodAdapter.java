package mx.ivajotha.space.data;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.ivajotha.space.R;
import mx.ivajotha.space.model.Photo;
import mx.ivajotha.space.model.Apod;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder>{

    private List<Photo> marsPhoto;
    private OnItemClickListener onItemListener;

    public NasaApodAdapter(){}

    public NasaApodAdapter(List<Photo> photo){
        this.marsPhoto = photo;
    }


    //Cual va hacer el VIEW que se va inflar
    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewhoder, parent , false));
        //Version Extensa de New NasaApodView....
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewhoder,parent,false);
        //NasaApodViewHolder nasaApodViewHolder = new NasaApodViewHolder(v);
        //return null;
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo photo = marsPhoto.get(position); //Obtener el objeto dependiendo de la posicion
        holder.itemApod_text.setText(photo.getCamera().getName());

        //holder.itemApod_img.setImageURI(Uri.parse(photo.getImgSrc()));
        //Picasso.with(getApplicationContext()).load(urlImg).into(urlImgSingle);
        /*Picasso.with(holder.itemApod_img.getContext())
                .load(photo.getImgSrc())
                .into(holder.itemApod_img);
        */
        holder.itemApod_img.setImageURI(photo.getImgSrc());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemListener = onItemClickListener;
    }

    public void setMarsPhoto(List<Photo> photos){
        this.marsPhoto = photos;
    }


    //Controla Cuantos Items vamos a mostrar
    @Override
    public int getItemCount() {
        //return 0;
        return marsPhoto != null? marsPhoto.size():0;

    }

   /* public void setOnItemClickListener() {
    }
    */

    public interface OnItemClickListener {
        void onItemClick(Photo potho);
    }

}

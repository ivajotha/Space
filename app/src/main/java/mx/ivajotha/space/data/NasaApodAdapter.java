package mx.ivajotha.space.data;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mx.ivajotha.space.R;
import mx.ivajotha.space.model.MarsPhotos;
import mx.ivajotha.space.model.Photo;
import mx.ivajotha.space.model.Apod;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder>{

    private List<Photo> marsPhotos;
    private OnItemClickListener onItemClickListener;

    private int lastPosition = -1;

    //public NasaApodAdapter(){}

    public NasaApodAdapter()
    {
        super();
    }

    public NasaApodAdapter(List<Photo> apods){
        this.marsPhotos = apods;
    }

    //Cual va hacer el VIEW que se va inflar
    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_items, parent , false));
        //Version Extensa de New NasaApodView....
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewhoder,parent,false);
        //NasaApodViewHolder nasaApodViewHolder = new NasaApodViewHolder(v);
        //return null;
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo photo = marsPhotos.get(position); //Obtener el objeto dependiendo de la posicion
        holder.itemApod_img.setImageURI(photo.getImgSrc());
        holder.itemApod_text.setText(photo.getCamera().getFullName());
        holder.setItemClick(photo, onItemClickListener);

        setShowItem(holder.itemView, position);

        //holder.itemApod_img.setImageURI(Uri.parse(photo.getImgSrc()));
        //Picasso.with(getApplicationContext()).load(urlImg).into(urlImgSingle);
        /*Picasso.with(holder.itemApod_img.getContext())
                .load(photo.getImgSrc())
                .into(holder.itemApod_img);
        */

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsPhoto(List<Photo> photos){
        this.marsPhotos = photos;
    }

    //Controla Cuantos Items vamos a mostrar
    @Override
    public int getItemCount() {
        //return 0;
        return marsPhotos != null? marsPhotos.size():0;

    }

    public interface OnItemClickListener
    {
        void onItemClick(Photo photo);
    }

    private void setShowItem(View viewToAnimate, int position) {

        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

}

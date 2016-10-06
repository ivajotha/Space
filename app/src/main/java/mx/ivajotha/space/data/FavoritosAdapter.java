package mx.ivajotha.space.data;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.List;
import java.util.Random;

import mx.ivajotha.space.R;
import mx.ivajotha.space.model.ModelFavoritos;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosHolderView> {

    private OnItemClickListener onItemClickListener;
    private List<ModelFavoritos> misfavoritos;

    private int lastPosition = -1;



    public FavoritosAdapter()
    {
        super();
    }
    public FavoritosAdapter(List<ModelFavoritos> favoritosList){
        this.misfavoritos = favoritosList;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public FavoritosHolderView onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FavoritosHolderView(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorito_items,parent,false));
        //return null;

    }

    @Override
    public void onBindViewHolder(FavoritosHolderView holder, int position) {
        ModelFavoritos modelFavoritos = misfavoritos.get(position);
        holder.image_hvfav.setImageURI(modelFavoritos.mfav_url);
        holder.titulo_hvfav.setText(modelFavoritos.mfav_title);
        holder.setItemFavClick(modelFavoritos, onItemClickListener);

        setShowItem(holder.itemView, position);
    }

    public interface OnItemClickListener{
        void onItemClick(ModelFavoritos mFavoritos);
    }

    public void setFavoritos(List<ModelFavoritos> mFav){
        this.misfavoritos = mFav;
    }

    @Override
    public int getItemCount() {
        //return 0;
        return misfavoritos!=null?misfavoritos.size():0;
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

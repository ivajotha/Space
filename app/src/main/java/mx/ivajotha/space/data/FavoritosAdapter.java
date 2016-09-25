package mx.ivajotha.space.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mx.ivajotha.space.R;
import mx.ivajotha.space.model.ModelFavoritos;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosHolderView> {

    private OnItemClickListener onItemClickListener;
    private List<ModelFavoritos> misfavoritos;




    public FavoritosAdapter(){}
    public FavoritosAdapter(List<ModelFavoritos> favoritosList){
        this.misfavoritos = favoritosList;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public FavoritosHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritosHolderView(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_items,parent,false));
        //return null;

    }

    @Override
    public void onBindViewHolder(FavoritosHolderView holder, int position) {
        ModelFavoritos modelFavoritos = misfavoritos.get(position);
        holder.image_hvfav.setImageURI(modelFavoritos.mfav_url);
        holder.titulo_hvfav.setText(modelFavoritos.mfav_title);
        holder.setItemFavClick(modelFavoritos, onItemClickListener);
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
}

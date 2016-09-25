package mx.ivajotha.space.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.ivajotha.space.R;
import mx.ivajotha.space.model.Favoritos;
import mx.ivajotha.space.model.ModelFavoritos;

public class FavoritosHolderView extends RecyclerView.ViewHolder {

    @BindView(R.id.item_apod_img)
    SimpleDraweeView image_hvfav;
    @BindView(R.id.item_apod_text)
    TextView titulo_hvfav;

    private  FavoritosAdapter.OnItemClickListener onItemClickListener;
    private ModelFavoritos modelFav;



    public FavoritosHolderView(View itemfavoriteView) {
        super(itemfavoriteView);
        ButterKnife.bind(this,itemfavoriteView);
    }

    public void setItemFavClick(ModelFavoritos mFavoritos, FavoritosAdapter.OnItemClickListener oiClickListener){
        this.modelFav = mFavoritos;
        this.onItemClickListener = oiClickListener;

    }

    @OnClick(R.id.item_apod_img)
    public void onViewClick(View view){
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick(modelFav);
        }
    }



}

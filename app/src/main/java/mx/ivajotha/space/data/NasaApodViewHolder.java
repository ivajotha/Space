package mx.ivajotha.space.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.OnClick;
import mx.ivajotha.space.model.Photo;
import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.R;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.item_apod_img)
    //ImageView itemApod_img;
    SimpleDraweeView itemApod_img;

    @BindView(R.id.item_apod_text)
    TextView itemApod_text;

    private NasaApodAdapter.OnItemClickListener onItemClickListener;
    private Photo photo;

    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setItemClick(Photo photo, NasaApodAdapter.OnItemClickListener onItemClickListener){
        this.photo=photo;
        this.onItemClickListener = onItemClickListener;
    }
    @OnClick(R.id.item_apod_img)
    public void  onViewClick(View view){
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(photo);
        }
    }

}

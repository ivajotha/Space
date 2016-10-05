package mx.ivajotha.space.data;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

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

    private NasaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;

    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setItemClick(Photo photo, NasaApodAdapter.OnItemClickListener onItemListener){
        this.photo=photo;
        this.onItemListener = onItemListener;
    }

    @OnClick(R.id.item_apod_img)
    public void  onViewClick(View view){
        if(onItemListener != null){
            onItemListener.onItemClick(photo);
        }else{
            Log.d("RES:NULL",photo.getImgSrc());
        }
    }

}

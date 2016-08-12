package mx.ivajotha.space;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.data.ApodService;
import mx.ivajotha.space.data.NasaApodAdapter;
import mx.ivajotha.space.model.Photo;
import mx.ivajotha.space.helper.Data;
import mx.ivajotha.space.model.Apod;
import mx.ivajotha.space.model.MarsPhotos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mars_rover_listing)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                //Log.d("DATA__",photo.getImgSrc());
                Toast.makeText(getApplicationContext(),photo.getImgSrc(),Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                //intent.putExtra("imagen",photo.getImgSrc());
                //intent.putExtra("title",photo.getCamera().getFullName());
                //startActivity(intent);
            }
        });

        //Instance APO
        ApodService apodService = Data.getInstance().create(ApodService.class);

        String apiKey = "5Njm32H3YhmhIkWBJxNpXAReRHJdoXLi4hD4pBvw";
        apodService.getMarsPhoto(apiKey).enqueue(new Callback<MarsPhotos>() {
            @Override
            public void onResponse(Call<MarsPhotos> call, Response<MarsPhotos> response) {
                nasaApodAdapter.setMarsPhoto(response.body().getPhotos());
                //recyclerView.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
                recyclerView.setAdapter(nasaApodAdapter);
            }

            @Override
            public void onFailure(Call<MarsPhotos> call, Throwable t) {

            }
        });

    }
}

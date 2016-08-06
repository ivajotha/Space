package mx.ivajotha.space;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

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

public class ListingActivity extends AppCompatActivity {

/*    @BindView(R.id.boxList)
    RecyclerView recyclerView;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);

/*        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ApodService apodService = Data.getInstance().create(ApodService.class);

        String apiKey = "5Njm32H3YhmhIkWBJxNpXAReRHJdoXLi4hD4pBvw";

        apodService.getMarsPhoto(apiKey).enqueue(new Callback<MarsPhotos>() {
            @Override
            public void onResponse(Call<MarsPhotos> call, Response<MarsPhotos> response) {
                recyclerView.setAdapter(new NasaApodAdapter((ArrayList<Photo>) response.body().getPhotos()));
            }

            @Override
            public void onFailure(Call<MarsPhotos> call, Throwable t) {

            }
        });
*/

    }



}

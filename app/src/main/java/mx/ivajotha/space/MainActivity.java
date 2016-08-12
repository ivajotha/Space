package mx.ivajotha.space;

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

   /* private TextView dateSingle;
    private TextView explanationSingle;
    private TextView titleSingle;
    private ImageView urlImgSingle;
  */

   /* @BindView(R.id.dateSingle) TextView dateSingle;
    @BindView(R.id.explanationSingle) TextView explanationSingle;
    @BindView(R.id.titleSingle) TextView titleSingle;
    @BindView(R.id.imageSingle) ImageView urlImgSingle;
  */

    @BindView(R.id.ListingNasa)
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

        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(Photo potho) {
                Log.d("DATA", potho.getImgSrc());
                Toast.makeText(getApplicationContext(), "HOLA!", Toast.LENGTH_SHORT).show();
            }
        });

        ApodService apodService = Data.getInstance().create(ApodService.class);

        String apiKey = "5Njm32H3YhmhIkWBJxNpXAReRHJdoXLi4hD4pBvw";
        apodService.getMarsPhoto(apiKey).enqueue(new Callback<MarsPhotos>() {
            @Override
            public void onResponse(Call<MarsPhotos> call, Response<MarsPhotos> response) {
                nasaApodAdapter.setMarsPhoto(response.body().getPhotos());
                recyclerView.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
            }

            @Override
            public void onFailure(Call<MarsPhotos> call, Throwable t) {

            }
        });

        /*
        //set Instance Apo
        ApodService apodService = Data.getInstance().create(ApodService.class);

        //Declare variable for response Apod
        //Metodos de ApodService
        //Call<Apod> callApodService = apodService.getTodayApod();

        String apiKey = "5Njm32H3YhmhIkWBJxNpXAReRHJdoXLi4hD4pBvw";
        //Recuerden hacer Call<MarsRoverResponse> para obtener la respuesta deseada
        //Call<Apod> callApodService = apodService.getTodayApodWithQuery(apiKey);
        Call<MarsPhotos> callApodService = apodService.getMarsPhoto(apiKey);


        //Callback de Apod
        callApodService.enqueue(new Callback<MarsPhotos>() {

            @Override
            public void onResponse(Call<MarsPhotos> call, Response<MarsPhotos> response) {
                //Log.d("APOD", response.body().getTitle());
                //dateSingle.setText(response.body().getDate());
                //explanationSingle.setText(response.body().getExplanation());
                //explanationSingle.setText(response.body().getExplanation());
                //titleSingle.setText(response.body().getTitle());
                //String urlImg = response.body().getUrl();
                //Picasso.with(getApplicationContext()).load(urlImg).into(urlImgSingle);
            }

            @Override
            public void onFailure(Call<MarsPhotos> call, Throwable t) {

            }
        });

        */

    }
}

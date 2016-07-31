package mx.ivajotha.space;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mx.ivajotha.space.data.ApodService;
import mx.ivajotha.space.helper.Data;
import mx.ivajotha.space.model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set Instance Apo
        ApodService apodService = Data.getInstance().create(ApodService.class);

        //Declare variable for response Apod

        //Metodos de ApodService
        //Call<Apod> callApodService = apodService.getTodayApod();
        String apiKey = "5Njm32H3YhmhIkWBJxNpXAReRHJdoXLi4hD4pBvw";
        Call<Apod> callApodService = apodService.getTodayApodWithQuery(apiKey);

        //Callback de Apod
        callApodService.enqueue(new Callback<Apod>() {

            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                //Log.d("APOD", response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });

    }
}

package mx.ivajotha.space.data;

import mx.ivajotha.space.model.Apod;
import mx.ivajotha.space.model.MarsPhotos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alumno on 30/07/2016.
 */

public interface ApodService {
    @GET("planetary/apod?api_key=5Njm32H3YhmhIkWBJxNpXAReRHJdoXLi4hD4pBvw")
    Call<Apod> getTodayApod(); //Type resp. Other Ej. Call<List<Apod>>

    @GET("planetary/apod")
    Call<Apod> getTodayApodWithQuery(@Query("api_key") String apiKey);

    //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY
    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&")
    Call<MarsPhotos> getMarsPhoto(@Query("api_key") String apiKey);

}

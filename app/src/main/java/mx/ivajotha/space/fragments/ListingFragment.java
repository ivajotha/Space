package mx.ivajotha.space.fragments;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.DetailActivity;
import mx.ivajotha.space.R;
import mx.ivajotha.space.data.ApodService;
import mx.ivajotha.space.data.NasaApodAdapter;
import mx.ivajotha.space.helper.Data;
import mx.ivajotha.space.model.MarsPhotos;
import mx.ivajotha.space.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */

public class ListingFragment extends Fragment{

    @BindView(R.id.mars_rover_listing)
    RecyclerView recyclerView;

    @BindView(R.id.loadingData)
    ProgressBar loadingData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        ButterKnife.bind(this, view);
        String titleShareToday =  getResources().getString(R.string.titleMarsRover);
        getActivity().setTitle(titleShareToday);

        //return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Log.d("DATA__",photo.getImgSrc());
                //Toast.makeText(getActivity(),photo.getImgSrc(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("imagen",photo.getImgSrc());
                intent.putExtra("title",photo.getCamera().getName());
                intent.putExtra("desc",photo.getCamera().getFullName());
                intent.putExtra("fecha",photo.getEarthDate());
                startActivity(intent);

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
                loadingData.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<MarsPhotos> call, Throwable t) {

                String titleShareToday =  getResources().getString(R.string.notInternetMore);
                Snackbar.make(getView(), titleShareToday, Snackbar.LENGTH_SHORT).show();
                loadingData.setVisibility(View.GONE);

            }
        });

    }


}

package mx.ivajotha.space.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.R;
import mx.ivajotha.space.data.ApodService;
import mx.ivajotha.space.helper.Data;
import mx.ivajotha.space.model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonathan on 13/08/16.
 */
public class OnlyDayFragment extends Fragment{

    /* private TextView dateSingle;
         private TextView explanationSingle;
         private TextView titleSingle;
         private ImageView urlImgSingle;
    */


    @BindView(R.id.dateSingle)
    TextView dateSingle;
    @BindView(R.id.explanationSingle)
    TextView explanationSingle;
    @BindView(R.id.titleSingle)
    TextView titleSingle;
    @BindView(R.id.imageSingle)
    ImageView urlImgSingle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.onlyday_layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        dateSingle = (TextView) findViewById(R.id.dateSingle);
        explanationSingle = (TextView) findViewById(R.id.explanationSingle);
        titleSingle = (TextView) findViewById(R.id.titleSingle);
        urlImgSingle = (ImageView) findViewById(R.id.imageSingle);
        */

        //set Instance Apo
        ApodService apodService = Data.getInstance().create(ApodService.class);
        //Metodos de ApodService
        Call<Apod> callApodService = apodService.getTodayApod();

        //Callback de Apod
        callApodService.enqueue(new Callback<Apod>() {

            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                //Log.d("APOD", response.body().getTitle());
                dateSingle.setText(response.body().getDate());
                explanationSingle.setText(response.body().getExplanation());
                explanationSingle.setText(response.body().getExplanation());
                titleSingle.setText(response.body().getTitle());
                String urlImg = response.body().getUrl();
                Picasso.with(getContext()).load(urlImg).into(urlImgSingle);
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });


    }
}

package mx.ivajotha.space.fragments;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.R;
import mx.ivajotha.space.data.ApodService;
import mx.ivajotha.space.helper.Data;
import mx.ivajotha.space.model.Apod;
import mx.ivajotha.space.model.ModelFavoritos;
import mx.ivajotha.space.sql.DataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonathan on 13/08/16.
 */
public class OnlyDayFragment extends Fragment{

    private String dateFav;
    private String descFav;
    private String titleFav;
    private String urlImg_;
    private String typeMedia;

    private DataSource dataSource;
    //private Toolbar toolbar;
    final static String reg = "(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";

    @BindView(R.id.dateSingle)
    TextView dateSingle;
    @BindView(R.id.explanationSingle)
    TextView explanationSingle;
    @BindView(R.id.titleSingle)
    TextView titleSingle;
    @BindView(R.id.imageSingle)
    ImageView urlImgSingle;
    @BindView(R.id.loadingData)
    ProgressBar loadingData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        dataSource = new DataSource(getActivity());
        final View view = inflater.inflate(R.layout.onlyday_layout, container, false);
        ButterKnife.bind(this, view);
        String titleShareToday =  getResources().getString(R.string.ImageShareTitle);
        getActivity().setTitle(titleShareToday);


        //set Instance Apo
        ApodService apodService = Data.getInstance().create(ApodService.class);
        //Metodos de ApodService
        Call<Apod> callApodService = apodService.getTodayApod();

        //Callback de Apod
        callApodService.enqueue(new Callback<Apod>() {

            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                typeMedia = response.body().getMediaType();

                dateSingle.setText(response.body().getDate());

                explanationSingle.setText(response.body().getExplanation());
                titleSingle.setText(response.body().getTitle());
                urlImg_ = response.body().getUrl();

                dateFav = response.body().getDate();
                descFav = response.body().getExplanation();
                titleFav = response.body().getTitle();

                if (typeMedia.equals("video")){
                    String idVideo = getVideoId(urlImg_);
                    urlImg_ = "http://img.youtube.com/vi/"+idVideo+"/sddefault.jpg";
                }

                Picasso.with(getContext())
                        .load(urlImg_)
                        .into(urlImgSingle);
                loadingData.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_today_apod:
                String titleShareToday =  getResources().getString(R.string.titleShareToday);
                Snackbar.make(getView(), titleShareToday, Snackbar.LENGTH_SHORT).show();
                String TitleShare =  getResources().getString(R.string.ImageShareTitle);
                String appName =  getResources().getString(R.string.app_name);
                String TitleAll = TitleShare + " (App " + appName +"): ";
                shareText(TitleAll + " " + urlImg_);
                return true;
            case R.id.favorito:
                String AddFavorite =  getResources().getString(R.string.AddFavorite);
                Snackbar.make(getView(), AddFavorite, Snackbar.LENGTH_SHORT).show();
                addFav();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void shareText(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        String TitleShare =  getResources().getString(R.string.titleShare);
        startActivity(Intent.createChooser(shareIntent, TitleShare));
    }


    private void addFav() {

        //String titleAll = titleFav + "(" + descFav + ")";

        //public ModelFavoritos (int id, String mfav_title, String mfav_date, String mfav_url)

        ModelFavoritos modelfavoritos = new ModelFavoritos(0,titleFav,dateFav,urlImg_);

        Boolean isFav = dataSource.searchFav(modelfavoritos);

        if(isFav){
            String addFavorite =  getResources().getString(R.string.isFavorite);
            Snackbar.make(getView(),addFavorite,Snackbar.LENGTH_SHORT).show();
        }else{
            dataSource.saveFav(modelfavoritos);
            String addFavorite =  getResources().getString(R.string.AddFavorite);
            Snackbar.make(getView(),addFavorite,Snackbar.LENGTH_SHORT).show();

        }
    }


    public static String getVideoId(String videoUrl) {
        if (videoUrl == null || videoUrl.trim().length() <= 0)
            return null;

        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(videoUrl);

        if (matcher.find())
            return matcher.group(1);

        return null;
    }


}

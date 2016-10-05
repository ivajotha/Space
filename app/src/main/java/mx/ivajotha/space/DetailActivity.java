package mx.ivajotha.space;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.model.ModelFavoritos;
import mx.ivajotha.space.model.Photo;
import mx.ivajotha.space.sql.DataSource;

public class DetailActivity extends AppCompatActivity {

    private DataSource dataSource;

    private Intent details;

    private String urlImg_;

    @BindView(R.id.listing_toolbar)
    Toolbar toolbar;

    @BindView(R.id.details_image)
    SimpleDraweeView detailsImage;


    @BindView(R.id.details_date)
    TextView detailsDate;

    @BindView(R.id.details_title)
    TextView detailsTitle;

    @BindView(R.id.details_extrainfo)
    TextView detailExtraInfo;

    @BindView(R.id.loadingData)
    ProgressBar loadingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataSource = new DataSource(getApplicationContext());

        details = getIntent();

        if (details.getExtras() != null){

            detailsDate.setText(details.getExtras().getString("fecha"));
            detailsTitle.setText(details.getExtras().getString("title"));
            detailExtraInfo.setText(details.getExtras().getString("desc"));
            detailsImage.setImageURI(details.getExtras().getString("imagen"));

            urlImg_ = details.getExtras().getString("imagen");
            loadingData.setVisibility(View.GONE);

        }else{
            String notInternet =  getResources().getString(R.string.notInternet);
            Snackbar.make(findViewById(android.R.id.content),notInternet,Snackbar.LENGTH_SHORT).show();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.apod_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.favorito:
                //Agrega a favoritos
                addFav(details);
                return true;
            case R.id.share_today_apod:

                String titleShareToday =  getResources().getString(R.string.titleShareToday);
                Snackbar.make(findViewById(android.R.id.content), titleShareToday, Snackbar.LENGTH_SHORT).show();
                String TitleShare =  getResources().getString(R.string.ImageShareTitle);
                String appName =  getResources().getString(R.string.app_name);
                String TitleAll = TitleShare + " (App " + appName +"): ";
                shareText(TitleAll + " " + urlImg_);

                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareText(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        String TitleShare =  getResources().getString(R.string.titleShare);
        startActivity(Intent.createChooser(shareIntent, TitleShare));
    }

    private void addFav(Intent details) {
        String fecha = details.getExtras().getString("fecha");
        String title = details.getExtras().getString("title");
        //String desc = details.getExtras().getString("desc");
        String imagen = details.getExtras().getString("imagen");

        //String titleAll = title + "(" + desc + ")";

        //public ModelFavoritos (int id, String mfav_title, String mfav_date, String mfav_url)

        ModelFavoritos modelfavoritos = new ModelFavoritos(0,title,fecha,imagen);

        Boolean isFav = dataSource.searchFav(modelfavoritos);

        if(isFav){
            String addFavorite =  getResources().getString(R.string.isFavorite);
            Snackbar.make(findViewById(android.R.id.content),addFavorite,Snackbar.LENGTH_SHORT).show();
        }else{
            dataSource.saveFav(modelfavoritos);
            String addFavorite =  getResources().getString(R.string.AddFavorite);
            Snackbar.make(findViewById(android.R.id.content),addFavorite,Snackbar.LENGTH_SHORT).show();

        }
    }


}

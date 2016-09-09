package mx.ivajotha.space;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.model.Photo;

public class DetailActivity extends AppCompatActivity {

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

        Intent details = getIntent();
        if (details.getExtras() != null){

            detailsDate.setText(details.getExtras().getString("fecha"));
            detailsTitle.setText(details.getExtras().getString("title"));
            detailExtraInfo.setText(details.getExtras().getString("desc"));
            detailsImage.setImageURI(details.getExtras().getString("imagen"));


            loadingData.setVisibility(View.GONE);
        }else{
            Snackbar.make(findViewById(android.R.id.content),"Favorites",Snackbar.LENGTH_SHORT).show();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favoritos_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.addFavorites:
                Snackbar.make(findViewById(android.R.id.content),"Favorites",Snackbar.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

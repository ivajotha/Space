package mx.ivajotha.space;

import android.content.Intent;
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

    /*@BindView(R.id.details_image)
    SimpleDraweeView detailsImage;
    */

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

        Intent details = getIntent();
        if (details != null){

            String title = String.valueOf(details.getExtras().getInt("title"));

            detailsDate.setText(title);

            loadingData.setVisibility(View.GONE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.apod_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.share_today_apod:
                //Compartimos la url de la imagen seleccionada
                shareText(urlImg_);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareText(String text){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);

        String TitleShare =  getResources().getString(R.string.titleShare);
        startActivity(Intent.createChooser(shareIntent, TitleShare));

    }


}

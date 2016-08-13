package mx.ivajotha.space;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import mx.ivajotha.space.fragments.ListingFragment;
import mx.ivajotha.space.model.Photo;
import mx.ivajotha.space.helper.Data;
import mx.ivajotha.space.model.Apod;
import mx.ivajotha.space.model.MarsPhotos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    /*
    @BindView(R.id.mars_rover_listing)
    RecyclerView recyclerView;
   */

    @BindView(R.id.listing_toolbar)
    Toolbar toolbar;

    @BindView(R.id.listing_navigation_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.listing_navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_navigation_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.mars_apod_item:
                        // ArticleFragment newFragment = new ArticleFragment();
                        /*ListingFragment newFragment  = new ListingFragment();;
                        transaction.replace(R.id.holder_fragment, newFragment)
                        transaction.commit();*/
                        return true;
                }
                return false;
            }
        });

        ActionBarDrawerToggle actionbarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout ,toolbar,R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionbarDrawerToggle);
        actionbarDrawerToggle.syncState();

    }

    private void repleaceFragment() {

    }
}

package mx.ivajotha.space;

import android.support.design.widget.NavigationView;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import mx.ivajotha.space.fragments.ListingFragment;
import mx.ivajotha.space.fragments.OnlyDayFragment;

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

                //

                switch (item.getItemId()){
                    case R.id.mars_rover_item:
                        //getSupportFragmentManager marca error por la version
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new ListingFragment()).commit();
                        break;
                    case R.id.mars_apod_item:
                        //getFragmentManager().beginTransaction().replace(R.id.main_container,new ListingFragment()).commit();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new OnlyDayFragment()).commit();
                        break;
                    case R.id.favory_item:
                        //getFragmentManager().beginTransaction().replace(R.id.main_container,new ListingFragment()).commit();
                        //getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new OnlyDayFragment()).commit();
                        Snackbar.make(findViewById(android.R.id.content),"Favorites",Snackbar.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });


        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse responce) {

                if(object != null) {

                    try {
                        SimpleDraweeView userImage = (SimpleDraweeView) findViewById(R.id.iv_app_ic);
                        userImage.setImageURI("http://graph.facebook.com/" + object.getString("id") + "/picture?type=large");
                        TextView userName = (TextView) findViewById(R.id.iv_app_nn);
                        userName.setText(object.getString("name"));
                        Log.d("name", object.getString("name"));
                        Log.d("id", object.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    finish();
                }
            }
        });
        request.executeAsync();


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

}

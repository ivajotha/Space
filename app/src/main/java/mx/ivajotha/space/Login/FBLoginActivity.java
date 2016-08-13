package mx.ivajotha.space.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.MainActivity;
import mx.ivajotha.space.R;

/**
 * Created by Alumno on 13/08/2016.
 */
public class FBLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {

    @BindView(R.id.fb_login_bottom)
    LoginButton loginButton;

    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager,this);

        if (AccessToken.getCurrentAccessToken() != null){
            startActivity(new Intent(this, MainActivity.class));
            //Snackbar.make(findViewById(android.R.id.content),error.getMessage(), Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        startActivity(new Intent(this, MainActivity.class));
        //Snackbar.make(findViewById(android.R.id.content),"Login", Snackbar.LENGTH_SHORT);
    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content),"Cancel login", Snackbar.LENGTH_SHORT);
    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content),error.getMessage(), Snackbar.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

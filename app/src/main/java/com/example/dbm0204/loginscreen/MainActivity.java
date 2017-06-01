package com.example.dbm0204.loginscreen;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.app.ProgressDialog;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {
    private static final String TAG ="LoginActivity";
    private static final int    REQUEST_SIGNUP=0;
    @InjectView(R.id.login) Button login_button;
    @InjectView(R.id.signup) TextView signupLink;
    @InjectView(R.id.email) EditText email;
    @InjectView(R.id.password) EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login();

            }});
        TextView signupLink =(TextView)findViewById(R.id.signup);
        signupLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //start the Signup Activity
            }
        });
    }
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

    }
    public boolean validate()
    {
        boolean valid =true;

        String emailText = email.toString();
        String passwordText= password.toString();

        if (emailText.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else  {
            email.setError(null);

        }
        if(passwordText.isEmpty()||passwordText.length()<4|| passwordText.length()>10){
            password.setError("between 4 and 10 aplhanumeric Characters");
            valid=false;

        } else  {
            password.setError(null);
        }
        return valid;
    }

    public void onLoginSucess() {
        Button loginButton =(Button) findViewById(R.id.login);
        loginButton.setEnabled(true);
        finish();
    }
    public void onLoginFailed(){
        Toast.makeText(getBaseContext(),"Login Failed",Toast.LENGTH_LONG).show();
        Button loginButton =(Button) findViewById(R.id.login);
        loginButton.setEnabled(true);
    }
}

package com.shaurya.inventory.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shaurya.inventory.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etPassword;
    private AutoCompleteTextView tvUsername;
    private Button btnSignIn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isUserSignedIn();
        initView();
    }

    private void initView() {
        tvUsername = (AutoCompleteTextView) findViewById(R.id.email);
        etPassword = (EditText)findViewById(R.id.password);
        btnSignIn = (Button)findViewById(R.id.email_sign_in_button);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        progressBar = (ProgressBar)findViewById(R.id.login_progress);
    }

    private void isUserSignedIn() {
        ParseUser user = ParseUser.getCurrentUser();
        if (user!=null){
            Toast.makeText(LoginActivity.this,"Welcome Back! "+user.getUsername(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }
    }

    private void signIn() {
        progressBar.setVisibility(View.VISIBLE);
        btnSignIn.setEnabled(false);
        ParseUser.logInInBackground(tvUsername.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                progressBar.setVisibility(View.GONE);
                btnSignIn.setEnabled(true);
                if (user != null) {
                    Toast.makeText(LoginActivity.this,"Welcome Back! "+user.getUsername(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    finish();

                } else {
                    ParseUser.logOut();
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

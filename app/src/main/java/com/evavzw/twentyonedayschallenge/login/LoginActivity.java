package com.evavzw.twentyonedayschallenge.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.main.MainActivity;
import com.evavzw.twentyonedayschallenge.registration.RegisterActivity;

/**
 * A login screen that offers login or register via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Keep track of the login or register task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthLoginTaks = null;
    private UserRegisterTask mAuthRegisterTask = null;

    // UI references.
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.evalogo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Set up the login form.
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLoginOrRegister(mAuthLoginTaks, "signin");
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLoginOrRegister(mAuthRegisterTask, "register");
            }
        });
    }

    /**
     * Attempts Register an account.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLoginOrRegister(AsyncTask<Void, Void, Boolean> task, String stask) {
        if (task != null) {
            return;
        }

        // Reset errors.
        etEmail.setError(null);
        etPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            etPassword.setError(getString(R.string.error_field_required));
            focusView = etPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_field_required));
            focusView = etEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Kick off a background task to perform the user login attempt.
            Intent i = null;
            if (stask.equalsIgnoreCase("register")) {
                task = new UserRegisterTask(email, password);
                i = new Intent(this, RegisterActivity.class);
            } else {
                task = new UserLoginTask(email, password);
                i = new Intent(this, MainActivity.class);
            }

            task.execute((Void) null);
            startActivity(i);
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return null;
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserRegisterTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return null;
        }
    }
}


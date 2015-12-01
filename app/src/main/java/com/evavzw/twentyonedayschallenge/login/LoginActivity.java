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
import com.evavzw.twentyonedayschallenge.dummy.User;
import com.evavzw.twentyonedayschallenge.main.MainActivity;
import com.evavzw.twentyonedayschallenge.registration.RegisterActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A login screen that offers login or register via email & password.
 * Email address needs to present in database for signing in and the password must match.
 * Password needs to be at least 6 characters for registering.
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

    //Validation Statics
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final int MIN_PASSWORD_SIZE = 6;

    //Asynctask String names
    private static final String SIGN_IN = "signin";
    private static final String REGISTER = "register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Shows action bar and logos
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.evalogo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Set up the login form.
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);

        //Email button
        Button btnEmail = (Button) findViewById(R.id.email_sign_in_button);
        btnEmail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLoginOrRegister(mAuthLoginTaks, SIGN_IN);
            }
        });

        //Register button
        Button btnRegister = (Button) findViewById(R.id.register_button);
        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLoginOrRegister(mAuthRegisterTask, REGISTER);
            }
        });

        //TODO: Hide in full release
        //Hidden fill button for testing
        Button mFillButton = (Button) findViewById(R.id.fill_button);
        mFillButton.setFocusable(true);
        mFillButton.setFocusableInTouchMode(true);
        mFillButton.requestFocus();
        mFillButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmail.setText(User.EMAIL.toString());
                etPassword.setText(User.PASSWORD.toString());
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

        //Checks if the email address is in the database and matches the password when Signing In
        if (!cancel && stask.equalsIgnoreCase(SIGN_IN)) {
            if (!isEmailInFoundDatabase(email)) {
                etEmail.setError(getString(R.string.error_notfound_email));
                focusView = etEmail;
                cancel = true;
            } else if (!doesPasswordMatchDatabase(email, password)) {
                etPassword.setError(getString(R.string.error_nomatch_password));
                focusView = etPassword;
                cancel = true;
            }
        }

        //Checks if email address is not the database when Registering
        if (!cancel && stask.equalsIgnoreCase(REGISTER) && isEmailInFoundDatabase(email)) {
                etEmail.setError(getString(R.string.error_found_email));
                focusView = etEmail;
                cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login or login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Kick off a background task to perform the user login or register attempt.
            Intent i = null;
            if (stask.equalsIgnoreCase(REGISTER)) {
                task = new UserRegisterTask(email, password);
                i = new Intent(this, RegisterActivity.class);
            } else {
                task = new UserLoginTask(email, password);
                i = new Intent(this, MainActivity.class);
            }
            task.execute((Void) null); //task.execute()
            startActivity(i);
        }

    }

    /**
     * Validates through a Regular Expression if the email is valid.
     */
    private boolean isEmailValid(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    /**
     * The password needs to be at least 6 characters long
     */
    private boolean isPasswordValid(String password) {
        return password.length() >= MIN_PASSWORD_SIZE;
    }

    /**
     * Checks if the email matches the one found in the database.
     */
    private boolean isEmailInFoundDatabase(String email) {
        //TODO: Need to compare this with the database entry.
        return true;
    }

    /**
     * Checks if the passwords matches the one found in the database.
     */
    private boolean doesPasswordMatchDatabase(String email, String password) {
        //TODO: Need to compare this with the database entry.
        return true;
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


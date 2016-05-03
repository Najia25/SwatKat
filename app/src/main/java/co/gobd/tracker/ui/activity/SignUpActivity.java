package co.gobd.tracker.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import co.gobd.tracker.R;
import co.gobd.tracker.application.GoAssetApplication;
import co.gobd.tracker.service.registration.RegistrationCallback;
import co.gobd.tracker.service.registration.RegistrationService;

public class SignUpActivity extends AppCompatActivity {

    @Inject
    RegistrationService registrationService;

    @Inject
    Context context;

    private EditText etUserName;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ((GoAssetApplication) getApplication()).getComponent().inject(this);

        setupUi();
    }

    public void onSignUpButtonClick(View view){

        final String userName = etUserName.getText().toString();
        final String password = etPassword.getText().toString();
        final String confirmPassword = etConfirmPassword.getText().toString();
        final String email = etEmail.getText().toString();
        final String phone = etPhone.getText().toString();

        registrationService.getRegistered(userName, password, confirmPassword, email, phone, new RegistrationCallback() {

            @Override
            public void onRegistrationSuccess() {
                Toast.makeText(context, R.string.message_reg_successful, Toast.LENGTH_SHORT).show();
                gotoLoginActivity();

            }

            @Override
            public void onRegistrationFailure() {
                Toast.makeText(context, R.string.message_reg_failed, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectionError() {
                Toast.makeText(context, R.string.message_connection_error, Toast.LENGTH_SHORT).show();
            }


        });

    }

    void setupUi(){
        etUserName = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
    }

    private void gotoLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
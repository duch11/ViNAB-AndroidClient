package tech.holm.vinabynabsyncforvikings.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tech.holm.vinabynabsyncforvikings.R;

public class LogInActivity extends AppCompatActivity {
    private Button loginBtn;
    protected TextView loginEmail;
    protected TextView password;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginBtn = findViewById(R.id.loginbtnloginview);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmail = findViewById(R.id.vinab_email);
                password = findViewById(R.id.vinab_password);

                if(loginEmail.getText().toString().equals("my@email.com") && password.getText().toString().equals("123")){

                    //go!
                    startActivity(new Intent(v.getContext(), tech.holm.vinabynabsyncforvikings.Activities.AllAccountsActivity.class));
                } else {
                    Toast.makeText(v.getContext(), "DEBUG: " + loginEmail.getText() + password.getText(), Toast.LENGTH_LONG).show();
                    Toast.makeText(v.getContext(), "Wrong email and/or password, try my@email.com and 123", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

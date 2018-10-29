package tech.holm.vinabynabsyncforvikings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.activities.AccountsActivity;

public class LogInActivity extends AppCompatActivity {

    public Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginBtn = findViewById(R.id.loginbuttonvinab);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView loginEmail = findViewById(R.id.emailLogin);
                TextView password = findViewById(R.id.passwordLogin);
                if(loginEmail.getText().toString().equals("my@email.com") && password.getText().toString().equals("123")){
                    Intent goToAccounts = new Intent(v.getContext(),AccountsActivity.class);
                    startActivity(goToAccounts);
                } else {
                    Toast.makeText(v.getContext(), "Wrong email and/or password, try my@email.com and 123", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}

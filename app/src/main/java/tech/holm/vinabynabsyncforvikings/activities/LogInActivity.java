package tech.holm.vinabynabsyncforvikings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                    startActivity(new Intent(v.getContext(), AllAccountsActivity.class));
                } else {
                    Toast.makeText(v.getContext(), "DEBUG: " + loginEmail.getText() + password.getText(), Toast.LENGTH_LONG).show();
                    Toast.makeText(v.getContext(), "Wrong email and/or password, try my@email.com and 123", Toast.LENGTH_LONG).show();
                }
            }
        });

        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("email", "my@email.com");
        postParam.put("password", "123456");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:3000/user/login";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,
            new JSONObject(postParam), new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    VolleyLog.d("Error: " + error.getMessage());
                }
            })
        {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjReq);

    }
}

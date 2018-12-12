package tech.holm.vinabynabsyncforvikings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


import tech.holm.vinabynabsyncforvikings.model.Account;
import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.adapters.AccountAdapter;

public class AllAccountsActivity extends AppCompatActivity {

    private RecyclerView accountRecycleView;
    private RecyclerView.Adapter accountRecViewAdapter;
    private RecyclerView.LayoutManager accountLayoutManager;

    private String userId = "";

    public static ArrayList<Account> accounts;

    // LOGOUT
    // GETALL
    // create account

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        userId = getIntent().getStringExtra("_id");

        if (userId.equals("")) {
            System.out.println("User ID does not exist!!!");
            finish();
        } else {
            // get all accounts for a user
            GetAllAcountsService();
        }

        //support Toolbar
        android.support.v7.widget.Toolbar accountsToolbar = findViewById(R.id.toolbar_accounts);
        setSupportActionBar(accountsToolbar);

        //set Toolbar properties
        getSupportActionBar().setTitle("ViNAB - All accounts");
        getSupportActionBar().setElevation(0);

        setupArrayList();
        setupRecyclerView();


    }

    private class OnAccountClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //get view position
            int viewPosition = accountRecycleView.getChildLayoutPosition(v);

            //get hold of account
            Account account = accounts.get(viewPosition);

            //toast debug
            Toast.makeText(v.getContext(), account.getAccountName(), Toast.LENGTH_LONG).show();

            //intent to Accounts Activity
            Intent showAccountDetails = new Intent(v.getContext(),TransactionsActivity.class);

            //add data
            showAccountDetails.putExtra("accountID", viewPosition);

            //go!
            startActivity(showAccountDetails);
        }
    }

    private void setupArrayList(){
        accounts = new ArrayList<>();
        accounts.add(new Account("abc",new GregorianCalendar(),"VISA/Dankort",false, "Nordea"));
        accounts.add(new Account("abc",new GregorianCalendar(),"Mastercard Debit",true, "BMO"));
        accounts.add(new Account("abc",new GregorianCalendar(),"VISA Debit",true, "Bank of Switzerland"));
        accounts.add(new Account("abc", (GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC")),"Mastercard Black",true, "Bank of Switzerland"));
    }

    private void setupRecyclerView(){
        //instantiate recyclerview and its parts
        accountRecycleView = findViewById(R.id.accountsListRecyclerView);
        accountRecViewAdapter = new AccountAdapter(accounts, new OnAccountClickListener());
        accountLayoutManager = new LinearLayoutManager(this);

        //connection recycler view parts
        accountRecycleView.setAdapter(accountRecViewAdapter);
        accountRecycleView.setLayoutManager(accountLayoutManager);

        //setting recycler view properties
        accountRecycleView.setHasFixedSize(true);
        accountRecycleView.setItemAnimator(new DefaultItemAnimator());
    }

    private void LogoutService(){
        Map<String, String> postParam= new HashMap<>();
        postParam.put("_id", userId);


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:3000/user/logout";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(postParam), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                // close the app / go back to login screen?
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
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(jsonObjReq);
    }


    private void GetAllAcountsService()
    {
        // GET JSON obj from login text boxes
        Map<String, String> postParam= new HashMap<>();
        postParam.put("_id", userId);


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:3000/user/login";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                new JSONObject(postParam), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONArray user = response.getJSONArray("accounts");

                        // assign Accounts model when it is refactored
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("error: " + e);
                    }

                }

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
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjReq);
    }
}

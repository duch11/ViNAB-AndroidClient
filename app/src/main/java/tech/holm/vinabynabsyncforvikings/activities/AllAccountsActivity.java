package tech.holm.vinabynabsyncforvikings.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import tech.holm.vinabynabsyncforvikings.model.Account;
import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.adapters.AccountAdapter;

public class AllAccountsActivity extends AppCompatActivity {

    private RecyclerView accountRecycleView;
    private RecyclerView.Adapter accountRecViewAdapter;
    private RecyclerView.LayoutManager accountLayoutManager;
    private FloatingActionButton addAccountBtn;

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
        }

        addAccountBtn = findViewById(R.id.add_acc_btn);
        addAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createAccount(new Account(userId));
                    onResume();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //support Toolbar
        android.support.v7.widget.Toolbar accountsToolbar = findViewById(R.id.toolbar_accounts);
        setSupportActionBar(accountsToolbar);

        //set Toolbar properties
        getSupportActionBar().setTitle("ViNAB - All accounts");
        getSupportActionBar().setElevation(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllAccountsService();
        setupRecyclerView();
    }

    private class OnAccountClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //get account viewItem position
            int viewPosition = accountRecycleView.getChildLayoutPosition(v);

            //get hold of account
            Account account = accounts.get(viewPosition);

            //toast debug
            Toast.makeText(v.getContext(), account.getNickName(), Toast.LENGTH_LONG).show();

            //intent to Account Detail Activity
            Intent showAccountDetails = new Intent(v.getContext(),AccountDetailsActivity.class);

            //add data
            showAccountDetails.putExtra("accountID", account.getAccountID());

            //go!
            startActivity(showAccountDetails);
        }
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

    private void createAccount(final Account account) throws JSONException {
        String P_START = "\"";
        String P_MID = "\": \"";
        String P_END_NXT = "\",";
        String P_END = "\"";
        String P_OBJ_DEF = "\": ";
        // define object to send
        JSONObject accountJson = new JSONObject("" +
                "{" +
                P_START + "lastsync" + P_MID + account.getLastsync() + P_END_NXT +
                P_START + "nickName" + P_MID + account.getNickName() + P_END_NXT +
                P_START + "owner_id" + P_MID + userId                + P_END_NXT +
                P_START + "budget"   + P_OBJ_DEF +
                    "{" +
                        P_START + "userName"    + P_MID + account.getBudget_userName()    + P_END_NXT +
                        P_START + "budgetName"  + P_MID + account.getBudget_budgetName()  + P_END_NXT +
                        P_START + "accountName" + P_MID + account.getBudget_accountName() + P_END +
                    "}," +
                P_START + "bank" + P_OBJ_DEF +
                    "{" +
                        P_START + "nickName"    + P_MID + account.getBank_nickName()    + P_END_NXT +
                        P_START + "bankName"    + P_MID + account.getBank_bankName()    + P_END_NXT +
                        P_START + "accountName" + P_MID + account.getBank_accountName() + P_END +
                    "}" +
                "}");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // define backend URL
        String url ="http://10.0.2.2:3000/account/create";

        // define response listener
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Created account with name: " + account.getNickName());
                System.out.println(response.toString());
                // close the app / go back to login screen?
            }
        };

        // define error listener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d("Error: " + error.getMessage());
            }
        };

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                accountJson,
                responseListener,
                errorListener)
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

        // Add the request to the RequestQueue, thereby calling it
        queue.add(jsonObjReq);
    }

    private void logoutService(){
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

    private void getAllAccountsService() {
        // Instantiate the ArrayList
        accounts = new ArrayList<>();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:3000/account/getall?owner_id=" + userId;

        // Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Account acc = new Account();
                        JSONObject jsonObject = response.getJSONObject(i);

                        acc.setAccountID(jsonObject.getString("_id"));
                        acc.setNickName(jsonObject.getString("nickName"));
                        acc.setOwner_id(userId);
                        acc.setLastsync(jsonObject.getString("lastsync"));

                        JSONObject budgetAccount = jsonObject.getJSONObject("budget");
                        acc.setBudget_userName(budgetAccount.getString("userName"));
                        acc.setBudget_budgetName(budgetAccount.getString("budgetName"));
                        acc.setBudget_accountName(budgetAccount.getString("accountName"));

                        JSONObject bankAccount = jsonObject.getJSONObject("bank");
                        acc.setBank_nickName(bankAccount.getString("nickName"));
                        acc.setBank_bankName(bankAccount.getString("bankName"));
                        acc.setBank_accountName(bankAccount.getString("accountName"));

                        accounts.add(acc);

                        System.out.println("Printing account:");
                        System.out.println(acc.getNickName() );
                        System.out.println(acc.getOwner_id() );
                        System.out.println(acc.getAccountID() );

                        accountRecViewAdapter.notifyDataSetChanged();
                    }
                    catch (JSONException e) {
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
        queue.add(jsonArrayRequest);
    }
}

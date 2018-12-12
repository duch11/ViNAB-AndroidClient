package tech.holm.vinabynabsyncforvikings.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.model.Account;

public class AccountDetailsActivity extends AppCompatActivity {


    private String syncAccountID;
    private Account myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        syncAccountID = getIntent().getStringExtra("accountID");
        System.out.println("AccDetails got ID: " + syncAccountID);
        if(!syncAccountID.equals("")){
            //find account with same id
            System.out.println("AccDetails, looking for account...");
            for(Account acc : AllAccountsActivity.accounts) {
                if (acc.getAccountID().equals(syncAccountID)) {
                    System.out.println("AccDetails, found account...");
                    myAccount = acc;
                }
            }
        }

        if(myAccount == null){
            finish();
        } else {
            android.support.v7.widget.Toolbar accountsToolbar = findViewById(R.id.toolbar_account_details);
            setSupportActionBar(accountsToolbar);

            //set Toolbar properties
            getSupportActionBar().setTitle("Connection: " + myAccount.getBank_accountName());
            getSupportActionBar().setElevation(0);

        }


    }

    // UPDATE
    // and maybe delete

    private void UpdateAccount(final Account account) throws JSONException {

        JSONObject accountJson = new JSONObject("" +
                "{" +
                "\"lastsync\": \""+account.getLastsync()+"\"" +
                "\"nickName\": \""+account.getNickName()+"\"" +
                "\"owner_id\": \""+account.getOwner_id()+"\"" +
                "\"budget\": " +
                    "{" +
                        "\"userName\": \""+account.getBudget_userName()+"\"" +
                        "\"budgetName\": \""+account.getBudget_budgetName()+"\"" +
                        "\"accountName\": \""+account.getBudget_accountName()+"\"" +
                    "}" +
                "\"bank\": " +
                    "{" +
                        "\"nickName\": \""+account.getBank_nickName()+"\"" +
                        "\"bankName\": \""+account.getBank_bankName()+"\"" +
                        "\"accountName\": \""+account.getBank_accountName()+"\"" +
                    "}" +
                "}");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:3000/account/update";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,
                accountJson, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Updated account: " + account.getNickName());
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
}

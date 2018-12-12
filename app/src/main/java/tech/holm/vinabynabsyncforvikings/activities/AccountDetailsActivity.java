package tech.holm.vinabynabsyncforvikings.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.model.Account;

public class AccountDetailsActivity extends AppCompatActivity {

    private Button saveBtn;
    private Button deleteBtn;
    private EditText accountName;
    private EditText syncDate;
    private EditText ynabBudgetName;
    private EditText ynabUserName;
    private EditText ynabBudgetAccount;
    private EditText bankAccountName;
    private EditText bankNickName;
    private EditText institution;
    private String syncAccountID;
    private Account myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        saveBtn = findViewById(R.id.save_account_btn);
        deleteBtn = findViewById(R.id.delete_account_btn);
        accountName = findViewById(R.id.account_name_textbox);
        syncDate = findViewById(R.id.lastsync_textbox);
        ynabBudgetName = findViewById(R.id.ynab_budget_edittext);
        ynabUserName = findViewById(R.id.ynab_username_textbox);
        ynabBudgetAccount = findViewById(R.id.ynab_budget_acc_textbox);
        bankAccountName = findViewById(R.id.bank_account_name_edittext);
        bankNickName = findViewById(R.id.bank_nickname_edittext);
        institution = findViewById(R.id.institution_textbox);

        syncAccountID = getIntent().getStringExtra("accountID");
        System.out.println("AccDetails got ID: " + syncAccountID);
        if(!syncAccountID.equals("")){
            //find account with same id
            System.out.println("AccDetails, looking for account...");
            for(Account acc : AllAccountsActivity.accounts) {
                if (acc.getAccountID().equals(syncAccountID)) {
                    System.out.println("AccDetails, found account...");
                    myAccount = acc;
                    setTextFields();
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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Account acc = new Account(
                            myAccount.getAccountID(),
                            syncDate.getText().toString(),
                            accountName.getText().toString(),
                            myAccount.getOwner_id(),
                            ynabUserName.getText().toString(),
                            ynabBudgetName.getText().toString(),
                            ynabBudgetAccount.getText().toString(),
                            bankNickName.getText().toString(),
                            institution.getText().toString(),
                            bankAccountName.getText().toString()
                    );
                    UpdateAccount(acc);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    deleteAccount(myAccount);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    // UPDATE
    // and maybe delete

    public void setTextFields(){

        syncDate.setText(myAccount.getLastsync());
        accountName.setText(myAccount.getNickName());
        ynabUserName.setText(myAccount.getBudget_userName());
        ynabBudgetName.setText(myAccount.getBudget_budgetName());
        ynabBudgetAccount.setText(myAccount.getBudget_accountName());
        bankNickName.setText(myAccount.getBank_nickName());
        bankAccountName.setText(myAccount.getBank_accountName());
        institution.setText(myAccount.getBank_bankName());
    }

    private void UpdateAccount(final Account account) throws JSONException {

        JSONObject accountJson = new JSONObject("" +
                "{" +
                "\"_id\": \""      + account.getAccountID() + "\"," +
                "\"lastsync\": \"" + account.getLastsync()  + "\"," +
                "\"nickName\": \"" + account.getNickName()  + "\"," +
                "\"owner_id\": \"" + account.getOwner_id()  + "\"," +
                "\"budget\": " +
                    "{" +
                        "\"userName\": \""+account.getBudget_userName()+"\"," +
                        "\"budgetName\": \""+account.getBudget_budgetName()+"\"," +
                        "\"accountName\": \""+account.getBudget_accountName()+"\"" +
                    "}," +
                "\"bank\": " +
                    "{" +
                        "\"nickName\": \""+account.getBank_nickName()+"\"," +
                        "\"bankName\": \""+account.getBank_bankName()+"\"," +
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

    private void deleteAccount(Account account) throws JSONException {

        JSONObject accountJson = new JSONObject("" +
                "{" +
                "\"_id\": \""      + account.getAccountID() + "\"," +
                "\"lastsync\": \"" + account.getLastsync()  + "\"," +
                "\"nickName\": \"" + account.getNickName()  + "\"," +
                "\"owner_id\": \"" + account.getOwner_id()  + "\"," +
                "\"budget\": " +
                "{" +
                "\"userName\": \""+account.getBudget_userName()+"\"," +
                "\"budgetName\": \""+account.getBudget_budgetName()+"\"," +
                "\"accountName\": \""+account.getBudget_accountName()+"\"" +
                "}," +
                "\"bank\": " +
                "{" +
                "\"nickName\": \""+account.getBank_nickName()+"\"," +
                "\"bankName\": \""+account.getBank_bankName()+"\"," +
                "\"accountName\": \""+account.getBank_accountName()+"\"" +
                "}" +
                "}");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:3000/account/delete";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,
                accountJson, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Deleted account: ");
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

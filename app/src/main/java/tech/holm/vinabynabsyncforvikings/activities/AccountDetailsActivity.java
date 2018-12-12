package tech.holm.vinabynabsyncforvikings.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        if(!syncAccountID.equals("")){
            //find account with same id
            for(Account acc : AllAccountsActivity.accounts) {
                if (acc.getAccountID() == syncAccountID) {
                    myAccount = acc;
                }
            }
        }

        if(myAccount == null){
            finish();
        }

        android.support.v7.widget.Toolbar accountsToolbar = findViewById(R.id.toolbar_account_details);
        setSupportActionBar(accountsToolbar);

        //set Toolbar properties
        getSupportActionBar().setTitle("Connection: " + myAccount.getBank_accountName());
        getSupportActionBar().setElevation(0);


    }

    // UPDATE
    // and maybe delete
}

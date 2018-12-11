package tech.holm.vinabynabsyncforvikings.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import tech.holm.vinabynabsyncforvikings.model.Account;
import tech.holm.vinabynabsyncforvikings.R;

public class AccountDetailsActivity extends AppCompatActivity {

    private TextView accountNameTxtView;
    private int syncAccountID;
    private Account myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_account_details);
        syncAccountID = getIntent().getIntExtra("accountID",-1);
        if(syncAccountID != -1){
            myAccount = AllAccountsActivity.accounts.get(syncAccountID);
        } else {
            finish();
        }
        android.support.v7.widget.Toolbar accountsToolbar = findViewById(R.id.toolbar_account_details);
        setSupportActionBar(accountsToolbar);

        //set Toolbar properties
        getSupportActionBar().setTitle("Connection: " + myAccount.getAccountName());
        getSupportActionBar().setElevation(0);

        accountNameTxtView = findViewById(R.id.accName_YNAB_SyncDetails);
        accountNameTxtView.setText(myAccount.getAccountName());

    }
}

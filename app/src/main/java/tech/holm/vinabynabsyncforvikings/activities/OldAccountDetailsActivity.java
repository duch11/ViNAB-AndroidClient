package tech.holm.vinabynabsyncforvikings.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import tech.holm.vinabynabsyncforvikings.model.Account;
import tech.holm.vinabynabsyncforvikings.R;

public class OldAccountDetailsActivity extends AppCompatActivity {

    private TextView accountNameTxtView;
    private int syncAccountID;
    private Account myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_activity_sync_account_details);
        syncAccountID = getIntent().getIntExtra("accountID",-1);
        if(syncAccountID != -1){
            myAccount = AllAccountsActivity.accounts.get(syncAccountID);
        } else {
            finish();
        }
        android.support.v7.widget.Toolbar accountsToolbar = findViewById(R.id.toolbar_account_details);
        setSupportActionBar(accountsToolbar);

        //set Toolbar properties
        getSupportActionBar().setTitle("Connection: " + myAccount.getBank_accountName());
        getSupportActionBar().setElevation(0);

        accountNameTxtView = findViewById(R.id.accName_YNAB_SyncDetails);
        accountNameTxtView.setText(myAccount.getBank_accountName());

    }
}

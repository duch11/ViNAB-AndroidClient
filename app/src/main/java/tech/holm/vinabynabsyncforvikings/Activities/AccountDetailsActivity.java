package tech.holm.vinabynabsyncforvikings.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import tech.holm.vinabynabsyncforvikings.R;

public class AccountDetailsActivity extends AppCompatActivity {

    private TextView accountNameTxtView;
    private String syncAccountTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_account_details);
        syncAccountTitle = getIntent().getStringExtra("accountName");
        getSupportActionBar().setTitle("Connection: " + syncAccountTitle);

        accountNameTxtView = findViewById(R.id.accName_YNAB_SyncDetails);
        accountNameTxtView.setText(syncAccountTitle);

    }
}

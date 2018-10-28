package tech.holm.vinabynabsyncforvikings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import tech.holm.vinabynabsyncforvikings.model.Account;
import tech.holm.vinabynabsyncforvikings.model.Transaction;
import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.adapters.TransactionAdapter;

public class TransactionsActivity extends AppCompatActivity {

    private ArrayList<Transaction> transactions;
    private RecyclerView tRecyclerView;
    private RecyclerView.Adapter tAdapter;
    private RecyclerView.LayoutManager tLayoutManager;
    private int syncAccountId;
    private Account thisAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        syncAccountId = getIntent().getIntExtra("accountID", -1);
        if(syncAccountId != -1){
            thisAccount= AccountsActivity.accounts.get(syncAccountId);
        } else {
            finish();
        }

        //support Toolbar
        android.support.v7.widget.Toolbar accountsToolbar = findViewById(R.id.toolbar_transactions);
        setSupportActionBar(accountsToolbar);

        //set Toolbar properties
        getSupportActionBar().setTitle("Transactions: " + thisAccount.getAccountName());
        getSupportActionBar().setElevation(0);

        setupArrayList();
        setupRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_buttons, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.account_settings_menu_btn:

                //intent to Accounts Activity
                Intent showAccountDetails = new Intent(this, AccountDetailsActivity.class);

                //add data
                showAccountDetails.putExtra("accountID", syncAccountId);

                //go!
                startActivity(showAccountDetails);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupArrayList(){
        transactions = new ArrayList<>();
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
        transactions.add(new Transaction("15/04-2018", "Loblaws transaction #123412", "100.22 CAD"));
        transactions.add(new Transaction("16/04-2018", "Wendy receipt #222431", "15.40 CAD"));
        transactions.add(new Transaction("17/04-2018", "Interac transfer #412", "2000.00 CAD"));
    }

    private void setupRecyclerView(){
        //instantiate recyclerview and its parts
        tRecyclerView = findViewById(R.id.transactionsRecyclerView);
        tLayoutManager = new LinearLayoutManager(this);
        tAdapter = new TransactionAdapter(transactions);

        //connection recycler view parts
        tRecyclerView.setAdapter(tAdapter);
        tRecyclerView.setLayoutManager(tLayoutManager);

        //setting recycler view properties
        tRecyclerView.setHasFixedSize(true);
        tRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}

package tech.holm.vinabynabsyncforvikings.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;


import tech.holm.vinabynabsyncforvikings.model.Account;
import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.adapters.AccountAdapter;

public class AllAccountsActivity extends AppCompatActivity {

    private RecyclerView accountRecycleView;
    private RecyclerView.Adapter accountRecViewAdapter;
    private RecyclerView.LayoutManager accountLayoutManager;

    public static ArrayList<Account> accounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

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


}

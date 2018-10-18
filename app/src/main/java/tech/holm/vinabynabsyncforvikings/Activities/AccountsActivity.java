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

import tech.holm.vinabynabsyncforvikings.Model.Account;
import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.Adapters.AccountAdapter;

public class AccountsActivity extends AppCompatActivity {

    private RecyclerView accountRecycleView;
    private RecyclerView.Adapter accountRecViewAdapter;
    private RecyclerView.LayoutManager accountLayoutManager;

    private ArrayList<Account> accounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        getSupportActionBar().setTitle("ViNAB - All accounts");

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
            showAccountDetails.putExtra("accountName", account.getAccountName());

            //go!
            startActivity(showAccountDetails);
        }
    }

    private void setupArrayList(){
        accounts = new ArrayList<>();
        accounts.add(new Account("abc",new GregorianCalendar(),"Nordea - Credit",false));
        accounts.add(new Account("abc",new GregorianCalendar(),"BMO - Debit",true));
        accounts.add(new Account("abc",new GregorianCalendar(),"Bank of Switzerland - Mastercard Black",false));
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

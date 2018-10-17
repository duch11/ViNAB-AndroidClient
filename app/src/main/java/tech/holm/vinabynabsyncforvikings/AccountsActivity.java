package tech.holm.vinabynabsyncforvikings;

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

public class AccountsActivity extends AppCompatActivity {

    private RecyclerView accountRecycleView;
    private RecyclerView.Adapter accountRecViewAdapter;
    private RecyclerView.LayoutManager accountLayoutManager;

    private ArrayList<BankAccount> accounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        getSupportActionBar().setTitle("ViNAB - All accounts");

        accounts = new ArrayList<>();
        accounts.add(new BankAccount("abc",new GregorianCalendar(),"Nordea - Credit",false));
        accounts.add(new BankAccount("abc",new GregorianCalendar(),"BMO - Debit",true));
        accounts.add(new BankAccount("abc",new GregorianCalendar(),"Bank of Switzerland - Mastercard Black",false));

        accountRecycleView = findViewById(R.id.accountsListRecycleView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        accountRecycleView.setHasFixedSize(true);

        // use a linear layout manager
        accountLayoutManager = new LinearLayoutManager(this);
        accountRecycleView.setLayoutManager(accountLayoutManager);

        // specify an adapter and onclick listener
        accountRecViewAdapter = new AccountAdapter(accounts, new OnAccountClickListener());
        accountRecycleView.setAdapter(accountRecViewAdapter);

        // set item animator
        accountRecycleView.setItemAnimator(new DefaultItemAnimator());

    }

    private class OnAccountClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //get view position
            int viewPosition = accountRecycleView.getChildLayoutPosition(v);
            BankAccount bankAccount = accounts.get(viewPosition);
            Toast.makeText(v.getContext(), bankAccount.getAccountName(), Toast.LENGTH_LONG).show();
            Intent showAccountDetails = new Intent(v.getContext(),AccountDetailsActivity.class);
            showAccountDetails.putExtra("accountName", bankAccount.getAccountName());
            startActivity(showAccountDetails);
        }
    }
}

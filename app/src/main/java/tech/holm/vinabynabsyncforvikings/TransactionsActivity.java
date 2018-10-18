package tech.holm.vinabynabsyncforvikings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {

    private ArrayList<Transaction> transactions;
    private RecyclerView tRecyclerView;
    private RecyclerView.Adapter tAdapter;
    private RecyclerView.LayoutManager tLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        setupArrayList();
        setupRecyclerView();
    }

    private void setupArrayList(){
        transactions = new ArrayList<>();
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

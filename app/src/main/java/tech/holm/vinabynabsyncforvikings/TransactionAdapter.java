package tech.holm.vinabynabsyncforvikings;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    private ArrayList<Transaction> transactions;

    public TransactionAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate viewgroup into the activity layout
        View transactionView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.activity_transactions,
                        viewGroup,
                        false);
        return new TransactionViewHolder(transactionView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder transactionViewHolder, int i) {
        //bind data from datasource to our viewholder
        Transaction transaction = transactions.get(i);
        transactionViewHolder.tAmount.setText(transaction.getAmount());
        transactionViewHolder.tTitle.setText(transaction.getTitle());
        transactionViewHolder.tDate.setText(transaction.getDate());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}

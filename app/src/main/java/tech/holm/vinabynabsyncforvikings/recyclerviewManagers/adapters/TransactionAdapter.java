package tech.holm.vinabynabsyncforvikings.recyclerviewManagers.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.Model.Transaction;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.ViewHolders.TransactionViewHolder;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    private ArrayList<Transaction> transactions;

    public TransactionAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflate viewgroup (transactionsActivity) into the activity layout
        View transactionView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.layout_transaction,
                        viewGroup,
                        false);
        return new TransactionViewHolder(transactionView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int i) {
        //bind data from datasource to our viewholder
        Transaction transaction = transactions.get(i);
        holder.getTextViewAmount().setText(transaction.getAmount());
        holder.getTextViewTitle().setText(transaction.getTitle());
        holder.getTextViewDate().setText(transaction.getDate());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}

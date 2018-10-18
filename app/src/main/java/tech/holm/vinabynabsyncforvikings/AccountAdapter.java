package tech.holm.vinabynabsyncforvikings;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {
    private ArrayList<BankAccount> bankAccounts;
    private final View.OnClickListener onAccountClickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AccountAdapter(ArrayList<BankAccount> bankAccounts, View.OnClickListener accountClickListener) {
        this.bankAccounts = bankAccounts;
        this.onAccountClickListener = accountClickListener;
    }

    // Create new views (invoked by the layout manager)
    // add on click listener
    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View accountView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_card_view, parent, false);

        accountView.setOnClickListener(onAccountClickListener);

        return new AccountViewHolder(accountView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        BankAccount bank = bankAccounts.get(position);
        holder.accountName.setText(bank.getAccountName());
        holder.accountDateTime.setText(bank.getLatestDateString());

        if(bank.getHasNewTransactions()){
            holder.accountStatus.setVisibility(View.VISIBLE);
        } else {
            holder.accountStatus.setVisibility(View.INVISIBLE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return bankAccounts.size();
    }


}
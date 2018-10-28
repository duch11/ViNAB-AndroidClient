package tech.holm.vinabynabsyncforvikings.recyclerviewManagers.adapters;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.holm.vinabynabsyncforvikings.model.Account;
import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.viewHolders.AccountViewHolder;

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {
    private ArrayList<Account> accounts;
    private final View.OnClickListener onAccountClickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AccountAdapter(ArrayList<Account> accounts, View.OnClickListener accountClickListener) {
        this.accounts = accounts;
        this.onAccountClickListener = accountClickListener;
    }

    // Create new views (invoked by the layout manager)
    // add on click listener
    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View accountView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_account, parent, false);

        accountView.setOnClickListener(onAccountClickListener);

        return new AccountViewHolder(accountView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Account bank = accounts.get(position);

        holder.getAccountName().setText(bank.getAccountName());
        holder.getBankName().setText(bank.getBankName());
        holder.getAccountDateTime().setText(bank.getLatestDateString());

        if(bank.getHasNewTransactions()){
            holder.getAccountStatus().setVisibility(View.VISIBLE);
        } else {
            int silver = ContextCompat.getColor(holder.getAccountName().getContext(), R.color.silver);
            holder.getAccountStatus().setVisibility(View.INVISIBLE);

            holder.getAccountName().setTypeface(null, Typeface.NORMAL);
            holder.getBankName().setTypeface(null, Typeface.NORMAL);
            holder.getAccountDateTime().setTypeface(null, Typeface.NORMAL);

            holder.getAccountName().setTextColor(silver);
            holder.getBankName().setTextColor(silver);
            holder.getAccountDateTime().setTextColor(silver);

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return accounts.size();
    }


}
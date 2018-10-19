package tech.holm.vinabynabsyncforvikings.recyclerviewManagers.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.holm.vinabynabsyncforvikings.Model.Account;
import tech.holm.vinabynabsyncforvikings.R;
import tech.holm.vinabynabsyncforvikings.recyclerviewManagers.ViewHolders.AccountViewHolder;

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
        holder.getAccountDateTime().setText(bank.getLatestDateString());

        if(bank.getHasNewTransactions()){
            holder.getAccountStatus().setVisibility(View.VISIBLE);
        } else {
            holder.getAccountStatus().setVisibility(View.INVISIBLE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return accounts.size();
    }


}
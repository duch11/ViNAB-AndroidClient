package tech.holm.vinabynabsyncforvikings.recyclerviewManagers.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tech.holm.vinabynabsyncforvikings.R;

public class TransactionViewHolder extends RecyclerView.ViewHolder  {

    private TextView tDate;
    private TextView tTitle;
    private TextView tAmount;

    public TransactionViewHolder(View v) {
        super(v);

        settDate((TextView) v.findViewById(R.id.transaction_date));
        settTitle((TextView) v.findViewById(R.id.transaction_title));
        settAmount((TextView) v.findViewById(R.id.transaction_amount));
    }

    public TextView getTextViewDate() {
        return tDate;
    }

    public void settDate(TextView tDate) {
        this.tDate = tDate;
    }

    public TextView getTextViewTitle() {
        return tTitle;
    }

    public void settTitle(TextView tTitle) {
        this.tTitle = tTitle;
    }

    public TextView getTextViewAmount() {
        return tAmount;
    }

    public void settAmount(TextView tAmount) {
        this.tAmount = tAmount;
    }
}

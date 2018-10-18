package tech.holm.vinabynabsyncforvikings;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TransactionViewHolder extends RecyclerView.ViewHolder  {

    TextView tDate;
    TextView tTitle;
    TextView tAmount;

    public TransactionViewHolder(View v) {
        super(v);

        tDate = v.findViewById(R.id.transaction_date);
        tTitle = v.findViewById(R.id.transaction_title);
        tAmount = v.findViewById(R.id.transaction_amount);
    }
}

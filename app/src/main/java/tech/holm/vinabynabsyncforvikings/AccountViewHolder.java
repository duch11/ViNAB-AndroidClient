package tech.holm.vinabynabsyncforvikings;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class AccountViewHolder extends RecyclerView.ViewHolder {

    TextView accountName;
    TextView accountDateTime;
    ImageView accountStatus;

    public AccountViewHolder(View cardView) {
        super(cardView);
        accountName = (TextView) cardView.findViewById(R.id.account_card_view_name);
        accountDateTime = (TextView) cardView.findViewById(R.id.account_card_view_date);
        accountStatus = (ImageView) cardView.findViewById(R.id.account_card_update_status_img_view);
    }
}
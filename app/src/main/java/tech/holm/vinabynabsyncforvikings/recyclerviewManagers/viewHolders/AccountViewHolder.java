package tech.holm.vinabynabsyncforvikings.recyclerviewManagers.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStructure;
import android.widget.ImageView;
import android.widget.TextView;

import tech.holm.vinabynabsyncforvikings.R;

public class AccountViewHolder extends RecyclerView.ViewHolder {

    private TextView accountName;
    private TextView bankName;
    private TextView accountDateTime;
    private ImageView accountStatus;

    public AccountViewHolder(View cardView) {
        super(cardView);
        setAccountName((TextView) cardView.findViewById(R.id.account_card_view_name));
        setBankName((TextView) cardView.findViewById(R.id.account_card_view_bankname));
        setAccountDateTime((TextView) cardView.findViewById(R.id.account_card_view_date));
        setAccountStatus((ImageView) cardView.findViewById(R.id.account_card_update_status_img_view));
    }

    public TextView getAccountName() {
        return accountName;
    }

    public void setAccountName(TextView accountName) {
        this.accountName = accountName;
    }

    public TextView getAccountDateTime() {
        return accountDateTime;
    }

    public void setAccountDateTime(TextView accountDateTime) {
        this.accountDateTime = accountDateTime;
    }

    public ImageView getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(ImageView accountStatus) {
        this.accountStatus = accountStatus;
    }

    public TextView getBankName() {
        return bankName;
    }

    public void setBankName(TextView bankName) {
        this.bankName = bankName;
    }
}


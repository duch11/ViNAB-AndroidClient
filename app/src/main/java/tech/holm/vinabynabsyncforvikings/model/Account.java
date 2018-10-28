package tech.holm.vinabynabsyncforvikings.Model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Account {
    private String accountHash;
    private GregorianCalendar latestDate;
    private String bankName;
    private String accountName;
    private Boolean hasNewTransactions;

    public Account() {
        this.accountHash = "EMPTY ACCOUNT HASH";
        this.latestDate = new GregorianCalendar(TimeZone.getTimeZone("UTC+2"));
        this.latestDate.set(0,0,0,0,0,0);
        this.accountName = "EMPTY ACCOUNT NAME";
        this.hasNewTransactions = true;
        this.bankName = "EMPTY BANK NAME";
    }

    public Account(String accountHash, GregorianCalendar latestDate, String accountName, Boolean hasNewTransactions, String bankName) {
        this.accountHash = accountHash;
        this.latestDate = latestDate;
        this.accountName = accountName;
        this.hasNewTransactions = hasNewTransactions;
        this.bankName = bankName;
    }

    public GregorianCalendar getLatestDate() {
        return latestDate;
    }

    public String getLatestDateString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        return simpleDateFormat.format(latestDate.getTime());
    }

    public void setLatestDate(GregorianCalendar latestDate) {
        this.latestDate = latestDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Boolean getHasNewTransactions() {
        return hasNewTransactions;
    }

    public void setHasNewTransactions(Boolean hasNewTransactions) {
        this.hasNewTransactions = hasNewTransactions;
    }

    public String getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(String accountHash) {
        this.accountHash = accountHash;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

package tech.holm.vinabynabsyncforvikings.Model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Account {
    private String accountHash;
    private GregorianCalendar latestDate;
    private String accountName;
    private Boolean hasNewTransactions;

    public Account() {
        this.accountHash = "EMPTY";
        this.latestDate = new GregorianCalendar(TimeZone.getTimeZone("UTC+2"));
        latestDate.set(0,0,0,0,0,0);
        this.accountName = "Empty accountStatus";
        this.hasNewTransactions = true;
    }

    public Account(String accountHash, GregorianCalendar latestDate, String accountName, Boolean hasNewTransactions) {
        this.accountHash = accountHash;
        this.latestDate = latestDate;
        this.accountName = accountName;
        this.hasNewTransactions = hasNewTransactions;
    }

    public GregorianCalendar getLatestDate() {
        return latestDate;
    }

    public String getLatestDateString(){
        return "00/0-0000";
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
}

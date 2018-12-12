package tech.holm.vinabynabsyncforvikings.model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Account {

    /*

    _id?: string;

    lastsync: string;
    nickName: string;
    owner_id: string;

    budget: {
        userName: string;
        budgetName: string;
        accountName: string;
    }

    bank: {
        nickName: string;
        bankName: string;
        accountName: string;

    }

    * */
    // Different from standard model
    private GregorianCalendar gregorianCalendar;
    private Boolean hasNewTransactions;

    private String accountID;

    private String lastsync;
    private String nickName;
    private String owner_id;
    private String budget_userName;
    private String budget_budgetName;
    private String budget_accountName;
    private String bank_nickName;
    private String bank_bankName;
    private String bank_accountName;


    public Account() {
        this.gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC+2"));
        this.gregorianCalendar.set(0,0,0,0,0,0);
        this.hasNewTransactions = true;

        this.accountID = "EMPTY ACCOUNT ID";

        this.bank_accountName = "EMPTY ACCOUNT NAME";

        this.bank_bankName = "EMPTY BANK NAME";
        this.gregorianCalendar = new GregorianCalendar();
        this.hasNewTransactions = true;
    }

    public Account(String lastsync, String nickName, String owner_id, String budget_userName, String budget_budgetName, String budget_accountName, String bank_nickName, String bank_bankName, String bank_accountName) {

        this.lastsync = lastsync;
        this.nickName = nickName;
        this.owner_id = owner_id;
        this.budget_userName = budget_userName;
        this.budget_budgetName = budget_budgetName;
        this.budget_accountName = budget_accountName;
        this.bank_nickName = bank_nickName;
        this.bank_bankName = bank_bankName;
        this.bank_accountName = bank_accountName;
    }

    public Account(String accountID, String lastsync, String nickName, String owner_id, String budget_userName, String budget_budgetName, String budget_accountName, String bank_nickName, String bank_bankName, String bank_accountName) {
        this.accountID = accountID;
        this.lastsync = lastsync;
        this.nickName = nickName;
        this.owner_id = owner_id;
        this.budget_userName = budget_userName;
        this.budget_budgetName = budget_budgetName;
        this.budget_accountName = budget_accountName;
        this.bank_nickName = bank_nickName;
        this.bank_bankName = bank_bankName;
        this.bank_accountName = bank_accountName;
    }

    public String getLatestDateString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }

    public Boolean getHasNewTransactions() {
        return hasNewTransactions;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getLastsync() {
        return lastsync;
    }

    public void setLastsync(String lastsync) {
        this.lastsync = lastsync;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getBudget_userName() {
        return budget_userName;
    }

    public void setBudget_userName(String budget_userName) {
        this.budget_userName = budget_userName;
    }

    public String getBudget_budgetName() {
        return budget_budgetName;
    }

    public void setBudget_budgetName(String budget_budgetName) {
        this.budget_budgetName = budget_budgetName;
    }

    public String getBudget_accountName() {
        return budget_accountName;
    }

    public void setBudget_accountName(String budget_accountName) {
        this.budget_accountName = budget_accountName;
    }

    public String getBank_nickName() {
        return bank_nickName;
    }

    public void setBank_nickName(String bank_nickName) {
        this.bank_nickName = bank_nickName;
    }

    public String getBank_bankName() {
        return bank_bankName;
    }

    public void setBank_bankName(String bank_bankName) {
        this.bank_bankName = bank_bankName;
    }

    public String getBank_accountName() {
        return bank_accountName;
    }

    public void setBank_accountName(String bank_accountName) {
        this.bank_accountName = bank_accountName;
    }
}

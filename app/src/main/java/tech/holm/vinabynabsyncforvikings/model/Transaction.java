package tech.holm.vinabynabsyncforvikings.model;

public class Transaction {
    private String date;
    private String title;
    private String amount;

    public Transaction(String date, String title, String amount) {
        this.date = date;
        this.title = title;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

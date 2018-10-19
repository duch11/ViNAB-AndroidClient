package tech.holm.vinabynabsyncforvikings;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import tech.holm.vinabynabsyncforvikings.Model.Account;

public class Accounts {
    public static ArrayList<Account> accounts;

    public Accounts() {
        accounts = new ArrayList<>();

    }

    private static void setupArrayList(){
        accounts = new ArrayList<>();
        accounts.add(new Account("abc",new GregorianCalendar(),"VISA/Dankort",false, "Nordea"));
        accounts.add(new Account("abc",new GregorianCalendar(),"Mastercard Debit",true, "BMO"));
        accounts.add(new Account("abc",new GregorianCalendar(),"VISA Debit",true, "Bank of Switzerland"));
        accounts.add(new Account("abc", (GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC")),"Mastercard Black",true, "Bank of Switzerland"));
    }
}

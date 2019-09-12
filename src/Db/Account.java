package Db;

/**
 * Created by boris on 9-9-2019.
 * Java WIZ XD
 */
public class Account {

    private final int AccountId;
    private final String AccountName;
    private final String AccountSurname;
    private final String AccountPhoneNumber;
    private final String AccountEmail;
    private final String AccountRegisterDate;
    private final BankAccount BankAccount;

    public Account (int id,String name,String surname,String phone,String email, String regDate,BankAccount bankAccount)
    {
        this.AccountId = id;
        this.AccountName = name;
        this.AccountSurname = surname;
        this.AccountPhoneNumber = phone;
        this.AccountEmail = email;
        this.AccountRegisterDate = regDate;
        this.BankAccount = bankAccount;
    }

    public int getAccountId() {
        return AccountId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public String getAccountSurname() {
        return AccountSurname;
    }

    public String getAccountPhoneNumber() {
        return AccountPhoneNumber;
    }

    public String getAccountEmail() {
        return AccountEmail;
    }

    public String getAccountRegisterDate() {
        return AccountRegisterDate;
    }

    public Db.BankAccount getBankAccount() {
        return BankAccount;
    }
}

package Db;

import javafx.beans.property.*;

/**
 * Created by boris on 9-9-2019.
 * Java WIZ XD
 */
public class Account {

    private final IntegerProperty AccountId;
    private final StringProperty AccountName;
    private final StringProperty AccountSurname;
    private final StringProperty UserBirthday;
    private final StringProperty AccountPhoneNumber;
    private final StringProperty AccountEmail;
    private final StringProperty AccountRegisterDate;
    private final IntegerProperty BankAccountId;
    private final DoubleProperty AccountBalance;
    private final BankAccount BankAccount;


    Account(int id, String name, String surname, String birth, String phone, String email, String regDate, BankAccount bankAccount)
    {
        AccountId = new SimpleIntegerProperty(id);
        AccountName = new SimpleStringProperty(name);
        AccountSurname = new SimpleStringProperty(surname);
        UserBirthday = new SimpleStringProperty(birth);
        AccountPhoneNumber = new SimpleStringProperty(phone);
        AccountEmail = new SimpleStringProperty(email);
        AccountRegisterDate = new SimpleStringProperty(regDate);
        BankAccount = bankAccount;
        BankAccountId = new SimpleIntegerProperty(BankAccount.getBankAccountId());
        AccountBalance = new SimpleDoubleProperty(BankAccount.getBankAccountBalance());
    }

    public int getAccountId() {
        return AccountId.getValue();
    }

    public String getAccountName() {
        return AccountName.getValue();
    }

    public String getAccountSurname() {
        return AccountSurname.getValue();
    }

    public String getAccountPhoneNumber() {
        return AccountPhoneNumber.getValue();
    }

    public String getAccountEmail() {
        return AccountEmail.getValue();
    }

    public String getAccountRegisterDate() {
        return AccountRegisterDate.getValue();
    }

    public BankAccount getBankAccount() {
        return BankAccount;
    }

    public String getAccountBalance()
    {
        return String.format("\u20ac %s",AccountBalance.doubleValue());
    }

    public int getBankAccountId() {
        return BankAccountId.get();
    }

    public int bankAccountIdProperty() {
        return BankAccountId.getValue();
    }

    public String getUserBirthday() {
        return UserBirthday.get();
    }

    public String userBirthdayProperty() {
        return UserBirthday.getValue();
    }
}

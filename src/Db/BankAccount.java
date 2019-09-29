package Db;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by boris on 10-9-2019.
 * Java WIZ XD
 */
public class BankAccount {

    private final IntegerProperty BankAccountId;
    private final IntegerProperty BankAccountUserId;
    private final DoubleProperty BankAccountBalance;

    public BankAccount(int id,double balance,int userId)
    {
        BankAccountId = new SimpleIntegerProperty(id);
        BankAccountUserId = new SimpleIntegerProperty(userId);
        BankAccountBalance = new SimpleDoubleProperty(balance);
    }

    public int getBankAccountId() {
        return BankAccountId.getValue();
    }

    public int getBankAccountUserId() {
        return BankAccountUserId.getValue();
    }

    public double getBankAccountBalance() {
        return BankAccountBalance.doubleValue();
    }
}

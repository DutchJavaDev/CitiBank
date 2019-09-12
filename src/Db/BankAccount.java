package Db;

/**
 * Created by boris on 10-9-2019.
 * Java WIZ XD
 */
public class BankAccount {

    private final int BankAccountId;
    private final int BankAccountUserId;
    private final double BankAccountBalance;

    public BankAccount(int id,int userId,double balance)
    {
        this.BankAccountId = id;
        this.BankAccountUserId = userId;
        this.BankAccountBalance = balance;
    }

    public int getBankAccountId() {
        return BankAccountId;
    }

    public int getBankAccountUserId() {
        return BankAccountUserId;
    }

    public double getBankAccountBalance() {
        return BankAccountBalance;
    }
}

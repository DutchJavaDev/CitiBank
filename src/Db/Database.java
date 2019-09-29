package Db;

import Layouts.App;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static Layouts.App.AppException;
import static Layouts.App.Info;
import static Layouts.App.Error;
import static Layouts.App.Warning;
import static Utils.Enums.DbVersions;

/**
 * Created by boris on 10-9-2019.
 * Java WIZ XD
 */
public class Database {

    private static final String JDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static final String LocalDbConnection = "jdbc:mysql://localhost/citibankDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LiveDbConnection = "";
    private static final StringBuilder QueryBuilder = new StringBuilder();

    private static final String USER_TABLE = "citibank_user_accounts";
    private static final String BANK_ACCOUNTS_TABLE = "citibank_bank_accounts";
    private static final String[] USER_TABLE_PARAMS = {
            "userName","surName","userdBirth","userPhone","userEmail","userregisterDate","userBankAccountId"
    };

    private static String ConnectionString;
    private static String UserName = "";
    private static String Password = "";


    public static boolean CreateUserAccount(Object[] values, double balance)
    {
        DatabaseResult userAccount = InsertQuery(USER_TABLE,USER_TABLE_PARAMS,values);

        if(userAccount.isSucces())
        {
            DatabaseResult userBankAccount = CreateUserBankAccount(userAccount.getLastInsertedId(),balance);

            userAccount = UpdateQuery(USER_TABLE,new String[]{"userBankAccountId"},new Object[]{userBankAccount.getLastInsertedId()},"userId",userBankAccount.getLastInsertedId());

            return userBankAccount.isSucces() && userAccount.isSucces();
        }

        return false;
    }

    private static DatabaseResult CreateUserBankAccount(int accountId, double balance)
    {
        return InsertQuery(BANK_ACCOUNTS_TABLE,new String[]{"balance","accountId"},new Object[]{balance,accountId});
    }

    public static ObservableList<Account> GetAllAccounts()
    {
        List<Object[]> db = QueryAll(USER_TABLE,9);
        List<Account> accounts = new ArrayList<>();

        if(db == null)
            return FXCollections.observableList(accounts);

        int AccountId;
        String AccountName;
        String AccountSurname;
        String UserBirth;
        String AccountPhoneNumber;
        String AccountEmail;
        String AccountRegisterDate;
        int BankAccountId;

        for(Object[] dbresult : db)
        {
            AccountId = (int) dbresult[0];
            AccountName = (String) dbresult[1];
            AccountSurname = (String) dbresult[2];
            UserBirth = dbresult[3].toString();
            AccountPhoneNumber = dbresult[4].toString();
            AccountEmail = (String) dbresult[5];
            AccountRegisterDate = dbresult[7].toString();
            BankAccountId = (int) dbresult[8];

            BankAccount account;

            List<Object[]> accs = QueryWhere(BANK_ACCOUNTS_TABLE,"bankId",BankAccountId,3);

            if(accs == null || accs.isEmpty())
            {
                Info(String.format("No Bankaccount with id %s found",BankAccountId));
                account = new BankAccount(-1,-0.0,-1);
            }
            else
            {
                Object[] acc = accs.get(0);
                account = new BankAccount((int)acc[0],(double)acc[1],(int)acc[2]);
            }


            accounts.add(new Account(AccountId,AccountName,AccountSurname,UserBirth,AccountPhoneNumber,AccountEmail,AccountRegisterDate,account));
        }

        return FXCollections.observableList(accounts);
    }


    /**Query's**/
    private static DatabaseResult UpdateQuery(String table, String[] params, Object[] values, String condition, Object conditionValue)
    {
        try {
            Connection connection = GetConnection();

            QueryBuilder.append(String.format("UPDATE %s SET ",table));

            for(int index = 0; index < params.length; index++)
            {
                QueryBuilder.append(String.format("%s = ?%s",params[index],(index == params.length-1 ? "" : ",")));
            }

            QueryBuilder.append(String.format(" WHERE %s = %s",condition,conditionValue.toString()));
            PreparedStatement stmt = connection.prepareStatement(QueryBuilder.toString(),Statement.RETURN_GENERATED_KEYS);

            FillQuery(values, stmt);

            int id = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                return new DatabaseResult(rs.getInt(1), true);
            }

            Warning("Failed to read generated keys, contact the developer!");
            rs.close();
            stmt.close();
            connection.close();
            return new DatabaseResult(-1,true);
        }
        catch (Exception e)
        {
            App.Error(e.getMessage());
            return new DatabaseResult(-1,false);
        }
    }

    private static DatabaseResult InsertQuery(String table, String[] params, Object[] values)
    {
        try {
            Connection connection = GetConnection();

            QueryBuilder.append(String.format("INSERT INTO %s (",table));

            for(int index = 0; index < params.length; index++)
            {
                if(index == params.length-1)
                {
                    QueryBuilder.append(String.format("%s)",params[index]));
                }
                else
                    QueryBuilder.append(String.format("%s,",params[index]));
            }

            QueryBuilder.append(" VALUES (");

            for(int index = 0; index < params.length; index++)
            {
                if(index == params.length-1)
                {
                    QueryBuilder.append("?);");
                }
                else
                    QueryBuilder.append("?,");
            }

            PreparedStatement stmt = connection.prepareStatement(QueryBuilder.toString(),Statement.RETURN_GENERATED_KEYS);

            FillQuery(values,stmt);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                return new DatabaseResult(rs.getInt(1),true);
            }

            Warning("Failed to read generated keys, contact the developer!");
            rs.close();
            stmt.close();
            connection.close();
            return new DatabaseResult(-1,true);
        }
        catch (Exception e)
        {
            Error(e.getMessage());
            return new DatabaseResult(-1,false);
        }
    }

    private static List<Object[]> QueryAll(String table, int rowCount)
    {
        try {
            Connection connection = GetConnection();

            List<Object[]> result = new ArrayList<>();

            QueryBuilder.append(String.format("SELECT * from %s;",table));

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(QueryBuilder.toString());

            while(rs.next())
            {
                Object[] data = new Object[rowCount];

                for(int i = 0; i < data.length; i++)
                {
                    data[i] = rs.getObject(i+1);
                }
                result.add(data);
            }

            connection.close();
            stmt.close();
            rs.close();
            return  result;
        }
        catch (Exception e)
        {
            Error(e.getMessage());
            return  null;
        }
    }

    private static List<Object[]> QueryWhere(String table, String condition, Object conditionValue, int rowCount)
    {
        try {
            Connection connection = GetConnection();

            List<Object[]> result = new ArrayList<>();

            QueryBuilder.append(String.format("SELECT * FROM %s WHERE %s = ?;",table,condition));

            PreparedStatement stmt = connection.prepareStatement(QueryBuilder.toString());

            FillQuery(new Object[]{conditionValue},stmt);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                Object[] data = new Object[rowCount];

                for(int i = 0; i < data.length; i++)
                {
                    data[i] = rs.getObject(i+1);
                }
                result.add(data);
            }

            connection.close();
            stmt.close();
            rs.close();
            return result;
        }
        catch (Exception e)
        {
            Error(e.getMessage());
            return  null;
        }
    }
    /**End Query's**/


    public static void SetDbConnectionTo(DbVersions type)
    {
        switch (type)
        {
            case LIVE:
                ConnectionString = LiveDbConnection;

                // When using live db need a prompt for the password
                // TODO replace with actual prompt for live db connection
                Info("Wachtwoord is nodig als je een live database wilt gebruiken");
                break;

            case LOCAL:
                ConnectionString = LocalDbConnection;
                UserName = "root";
                Password = "";
                break;

            default: AppException("Failed to set Db connection");
        }
    }

    private static Connection GetConnection() throws Exception
    {
        QueryBuilder.setLength(0);
        Class.forName(JDBCDriver);
        return DriverManager.getConnection(ConnectionString,UserName,Password);
    }

    private static void FillQuery(Object[] values, PreparedStatement stmt) throws Exception
    {
        for(int index = 0; index < values.length; index++)
        {
            if(values[index] instanceof Integer)
            {
                stmt.setInt(index+1,(int)values[index]);
            }

            if(values[index] instanceof String)
            {
                stmt.setString(index+1,values[index].toString());
            }

            if(values[index] instanceof Double)
            {
                stmt.setDouble(index+1,(double)values[index]);
            }
        }
    }
}

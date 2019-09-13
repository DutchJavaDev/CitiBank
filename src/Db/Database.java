package Db;

import Layouts.App;

import java.sql.*;

import static Layouts.App.AppException;
import static Layouts.App.Info;
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

    private static final  String USER_TABLE = "citibank_user_accounts";
    private static final String[] USER_TABLE_PARAMS = {
            "userName","surName","userdBirth","userPhone","userEmail","userregisterDate","userBankAccountId"
    };

    private static String ConnectionString;

    private static String UserName = "";
    private static String Password = "";


    public static boolean CreateUserAccount(Object[] values)
    {
        return InsertQuery(USER_TABLE,USER_TABLE_PARAMS,values);
    }

    public static void CreateUserBankAccount(String[] values)
    {

        // TODO
        if (InsertQuery("",new String[]{"userName",""},values))
        {

        }
        else
        {

        }
    }


    private static boolean InsertQuery(String table, String[] params, Object[] values)
    {
        try {
            QueryBuilder.delete(0,QueryBuilder.length());

            Class.forName(JDBCDriver);

            Connection connection = DriverManager.getConnection(ConnectionString,UserName,Password);

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append(String.format("INSERT INTO %s (",table));

            for(int index = 0; index < params.length; index++)
            {
                if(index == params.length-1)
                {
                    queryBuilder.append(String.format("%s)",params[index]));
                }
                else
                    queryBuilder.append(String.format("%s,",params[index]));
            }

            queryBuilder.append(" VALUES (");

            for(int index = 0; index < params.length; index++)
            {
                System.out.println("V:"+index);
                if(index == params.length-1)
                {
                    queryBuilder.append("?);");
                }
                else
                    queryBuilder.append("?,");
            }

            PreparedStatement stmt = connection.prepareStatement(queryBuilder.toString());

            for(int index = 0; index < values.length; index++)
            {
                System.out.println("P:"+index);
                if(values[index] instanceof Integer)
                {
                    stmt.setInt(index+1,(int)values[index]);
                }

                if(values[index] instanceof String)
                {
                    stmt.setString(index+1,(String)values[index]);
                }
            }

            stmt.executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

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
}

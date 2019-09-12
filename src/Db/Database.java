package Db;

import Layouts.App;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static Layouts.App.AppException;
import static Layouts.App.Info;
import static Utils.Enums.DbVersions;

/**
 * Created by boris on 10-9-2019.
 * Java WIZ XD
 */
public class Database {

    private static final String JDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static final String LocalDbConnection = "jdbc:mysql://localhost/citibankDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC\"";
    private static final String LiveDbConnection = "";
    private static final StringBuilder QueryBuilder = new StringBuilder();

    private static String ConnectionString;

    private static String UserName = "";
    private static String Password = "";


    public static void CreateUserAccount(String[] values)
    {
        // TODO
        if (InsertQuery("",new String[]{""},values))
        {

        }
        else
        {

        }
    }

    public static void CreateUserBankAccount(String[] values)
    {
        // TODO
        if (InsertQuery("",new String[]{""},values))
        {

        }
        else
        {

        }
    }


    private static boolean InsertQuery(String table, String[] params, String[] values)
    {
        try {
            QueryBuilder.delete(0,QueryBuilder.length());

            Class.forName(JDBCDriver);

            Connection connection = DriverManager.getConnection(ConnectionString,UserName,Password);

            Statement stmt = connection.prepareStatement("");

            return true;
        }
        catch (Exception e)
        {
            AppException(e.toString());
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

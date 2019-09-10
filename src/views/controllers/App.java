package views.controllers; /**
 * Created by boris on 9-9-2019.
 * Java WIZ XD
 */
import Utils.Enums;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;


import  static javafx.scene.control.Alert.AlertType;

public class App {

    public static final String AppName = "CITIBANK";

    public static final ObservableList<TableColumn> USERACOUNTSCOLUMS = FXCollections.observableArrayList(
            new TableColumn("Naam"),
            new TableColumn("Rekening"),
            new TableColumn("Geb datum"),
            new TableColumn("Saldo"),
            new TableColumn("Email"),
            new TableColumn("Telefoon"),
            new TableColumn("Reg datum")
    );

    public static final ObservableList<TableColumn> TRANSACTIONSCOLUMNS = FXCollections.observableArrayList(
            new TableColumn("Zender"),
            new TableColumn("Ontvanger"),
            new TableColumn("Bedrag"),
            new TableColumn("Datum")
    );

    private static final Alert ALERT = new Alert(AlertType.NONE);
    private static final String LocalDbConnection = "";
    private static final String LiveDbConnection = "";
    private static String ConnectionString;

    public static void AppException()
    {
        Error("Er ging iets fout!");
    }

    public static void AppException(String message)
    {
        Error(message);
    }

    public static void AppException(int accountNumber)
    {
        Error(String.format("Er ging iets fout met de volgende rekening nummer %s",accountNumber));
    }

    static void Error(Object message)
    {
        OpenAlert(message,Alert.AlertType.ERROR);
    }

    static void Warning(Object message)
    {
        OpenAlert(message,AlertType.WARNING);
    }

    static void Info(Object message)
    {
        OpenAlert(message,AlertType.INFORMATION);
    }

    private static void OpenAlert(Object message,AlertType type)
    {
        ALERT.setAlertType(type);
        ALERT.setHeaderText(String.format("Type: %s",type.name()));
        ALERT.setContentText(message.toString());
        ALERT.setTitle(AppName);
        ALERT.showAndWait();
    }

    static void SetDbConnectionTo(Enums.DbVersions type)
    {
        switch (type)
        {
            case LIVE:
                ConnectionString = LiveDbConnection;
                break;

            case LOCAL:
                ConnectionString = LocalDbConnection;
                break;

                default: AppException("Failed to set Db connection");
        }
    }
}

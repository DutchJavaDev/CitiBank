package Layouts;
import Db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import Utils.Enums;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * Created by boris on 2-9-2019.
 * Java WIZ XD
 */
public class MainViewController {

    private final ObservableList<TableColumn> USERACOUNTSCOLUMS = FXCollections.observableArrayList(
            new TableColumn("Naam"),
            new TableColumn("Rekening"),
            new TableColumn("Geb datum"),
            new TableColumn("Saldo"),
            new TableColumn("Email"),
            new TableColumn("Telefoon"),
            new TableColumn("Reg datum")
    );

    private final ObservableList<TableColumn> TRANSACTIONSCOLUMNS = FXCollections.observableArrayList(
            new TableColumn("Zender"),
            new TableColumn("Ontvanger"),
            new TableColumn("Bedrag"),
            new TableColumn("Datum")
    );

    @FXML
    private MenuItem Accounts;

    @FXML
    private MenuItem Transactions;

    @FXML
    private MenuItem NewCustomer;

    @FXML
    private MenuItem EditCustomer;

    @FXML
    private MenuItem DeleteCustomer;

    @FXML
    private MenuItem LocalDatabase;

    @FXML
    private MenuItem LiveDatabase;

    @FXML
    private Label ViewTitle;

    @FXML
    private TableView ViewTable;


    @FXML
    private void initialize()
    {
        // Default
        Database.SetDbConnectionTo(Enums.DbVersions.LOCAL);

        // Default view
        ShowUserAccounts();

        Accounts.setOnAction(i -> ShowUserAccounts());

        Transactions.setOnAction(i -> ShowTransactions());

        NewCustomer.setOnAction(i -> App.OpenUI("bank_add_user","Add User",false,true));
    }

    private void ShowUserAccounts()
    {
        ViewTable.getColumns().clear();
        ViewTable.getColumns().addAll(USERACOUNTSCOLUMS);
    }

    private void ShowTransactions()
    {
        ViewTable.getColumns().clear();
        ViewTable.getColumns().addAll(TRANSACTIONSCOLUMNS);
    }
}

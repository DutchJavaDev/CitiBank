package Layouts;
import Db.Account;
import Db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import Utils.Enums;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


/**
 * Created by boris on 2-9-2019.
 * Java WIZ XD
 */
public class MainViewController {

    private ObservableList USERACOUNTSCOLUMS;

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
        SetUserTableColumns();

        // Default view
        ShowUserAccounts();

        Accounts.setOnAction(i -> ShowUserAccounts());

        Transactions.setOnAction(i -> ShowTransactions());

        NewCustomer.setOnAction(i -> {
            App.OpenUI("bank_add_user","Add User",false,true);
            ShowUserAccounts();
        });
    }

    private void ShowUserAccounts()
    {
        ViewTable.getColumns().clear();
        ViewTable.getItems().clear();
        ViewTable.getColumns().addAll(USERACOUNTSCOLUMS);
        ViewTable.setItems(Database.GetAllAccounts());
    }

    private void ShowTransactions()
    {
        ViewTable.getColumns().clear();
        ViewTable.getItems().clear();
        ViewTable.getColumns().addAll(TRANSACTIONSCOLUMNS);
    }

    private void SetUserTableColumns()
    {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(CreateTableColumn("#Id","AccountId"));
        tableColumns.add(CreateTableColumn("Naam","AccountName"));
        tableColumns.add(CreateTableColumn("AchterNaam","AccountSurname"));
        tableColumns.add(CreateTableColumn("Geb datum","UserBirthday"));
        tableColumns.add(CreateTableColumn("Telefoon","AccountPhoneNumber"));
        tableColumns.add(CreateTableColumn("Email","AccountEmail"));
        tableColumns.add(CreateTableColumn("Reg datum","AccountRegisterDate"));
        tableColumns.add(CreateTableColumn("Saldo","AccountBalance"));
        tableColumns.add(CreateTableColumn("Rekening","BankAccountId"));

        USERACOUNTSCOLUMS = FXCollections.observableArrayList(tableColumns);
    }

    private TableColumn CreateTableColumn(String displayName,String valueName)
    {
        TableColumn column = new TableColumn(displayName);
        column.setCellValueFactory(new PropertyValueFactory(valueName));
        return column;
    }
}

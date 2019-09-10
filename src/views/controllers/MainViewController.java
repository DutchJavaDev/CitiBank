package views.controllers;
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
        App.SetDbConnectionTo(Enums.DbVersions.LOCAL);

        // Default view
        ShowUserAccounts();

        Accounts.setOnAction(i -> ShowUserAccounts());

        Transactions.setOnAction(i -> ShowTransactions());
    }

    private void ShowUserAccounts()
    {
        ViewTable.getColumns().clear();
        ViewTable.getColumns().addAll(App.USERACOUNTSCOLUMS);
    }

    private void ShowTransactions()
    {
        ViewTable.getColumns().clear();
        ViewTable.getColumns().addAll(App.TRANSACTIONSCOLUMNS);
    }
}

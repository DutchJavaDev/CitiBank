package Layouts;
import Db.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

/**
 * Created by boris on 10-9-2019.
 * Java WIZ XD
 */
public class MakeAccountController {

    @FXML
    private TextField UserName;

    @FXML
    private TextField UserSurename;

    @FXML
    private DatePicker UserBirth;

    @FXML
    private TextField UserPhone;

    @FXML
    private TextField UserEmail;

    @FXML
    private Label ErrorReport;

    @FXML
    private Button MakeAccount;

    @FXML
    private CheckBox UserHasBalance;

    @FXML
    private TextField UserBalance;

    @FXML
    private void initialize()
    {
        UserName.setText("");
        UserSurename.setText("");
        UserEmail.setText("");
        UserPhone.setText("");

        UserHasBalance.setOnAction(i -> UserBalance.setDisable(!UserHasBalance.isSelected()));

        UserPhone.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            char c = event.getCharacter().charAt(0);

            // Only allow number characters
            if(!App.IsNumeric(c))
                event.consume();
        });

        UserBalance.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String text = UserBalance.getText();
            boolean containsComma = text.contains(",");
            char c = event.getCharacter().charAt(0);

            if(!App.IsNumeric(c))
            {
                if(c == '.') // TODO? turn , into .
                {
                    // Dont place a comma if field is empty
                    if(text.isEmpty())
                    {
                        event.consume();
                        return;
                    }

                    // Dont add a second comma
                    if(containsComma)
                    {
                        event.consume();
                        return;
                    }
                   return;
                }

                event.consume();
            }
            else {
                // Checks if the user has placed a comma
                String[] split = text.split(".");
                int inputIndex = UserBalance.getCaretPosition();

                if(split.length > 1)
                {
                    // Blocks input after the comma if the lenght is equal to 2
                    if(!(inputIndex < split[0].length()-1) && split[1].length() == 2)
                        event.consume();
                }
            }
        });

        MakeAccount.setOnAction(i -> {

            ErrorReport.setText("");

            if(UserName.getText().isEmpty())
            {
                ErrorReport.setText("Naam mag niet leeg zijn!");
                return;
            }

            if(UserSurename.getText().isEmpty())
            {
                ErrorReport.setText("AchterNaam mag niet leeg zijn!");
                return;
            }

            if(UserBirth.getEditor().getText().isEmpty())
            {
                ErrorReport.setText("Geboorte datum mag niet leeg zijn!");
                return;
            }

            if(UserPhone.getText().isEmpty())
            {
                ErrorReport.setText("Telefoon mag niet leeg zijn!");
                return;
            }

            if(UserEmail.getText().isEmpty())
            {
                ErrorReport.setText("Email mag niet leeg zijn!");
                return;
            }

            if(UserHasBalance.isSelected())
            {
                if(UserBalance.getText().isEmpty())
                {
                    ErrorReport.setText("Saldo mag niet leeg zijn!");
                    return;
                }
            }

            Database.CreateUserAccount(new String[]{
                    // TODO
            });

        });
    }
}

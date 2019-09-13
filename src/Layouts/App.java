package Layouts; /**
 * Created by boris on 9-9-2019.
 * Java WIZ XD
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;
import java.util.stream.*;

import  static javafx.scene.control.Alert.AlertType;

public class App {

    public  static  Stage PrimaryStage;
    public static final String AppName = "CITIBANK";

    private static final Alert ALERT = new Alert(AlertType.NONE);
    private static final Character[] ValidNumericInputs = new Character[]{
            '0','1','2','3','4','5','6','7','8','9'
    };

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

    public static void Error(Object message)
    {
        OpenAlert(message,Alert.AlertType.ERROR);
    }

    public static void Warning(Object message)
    {
        OpenAlert(message,AlertType.WARNING);
    }

    public static void Info(Object message)
    {
        OpenAlert(message.toString(),AlertType.INFORMATION);
    }

    public static boolean IsNumeric(char c)
    {
        return Arrays.stream(ValidNumericInputs).anyMatch(i -> i == c);
    }

    public static void OpenUI(String ui,String title,boolean resize)
    {
         try {
             FXMLLoader loader = new FXMLLoader(App.class.getClassLoader().getResource(String.format("Layouts/%s.fxml",ui)));
             Pane root = loader.load();

             Stage tempUI = new Stage();
             tempUI.setResizable(resize);
             tempUI.setTitle(title);
             tempUI.setScene(new Scene(root));
             tempUI.initOwner(PrimaryStage);
             tempUI.initModality(Modality.WINDOW_MODAL);
             tempUI.showAndWait();
         }
         catch (Exception e)
         {
             Error(e.getMessage());
         }
    }

    private static void OpenAlert(Object message,AlertType type)
    {
        ALERT.setAlertType(type);
        ALERT.setHeaderText(String.format("Type: %s",type.name()));
        ALERT.setContentText(message.toString());
        ALERT.setTitle(AppName);
        ALERT.showAndWait();
    }


}

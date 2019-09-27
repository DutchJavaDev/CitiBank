import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Layouts.App;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Layouts/bank_main_layout.fxml"));
        Parent root = loader.load();

        primaryStage.setResizable(false);
        primaryStage.setTitle(App.AppName);
        primaryStage.setScene(new Scene(root));

        App.PrimaryStage = primaryStage;

        primaryStage.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}

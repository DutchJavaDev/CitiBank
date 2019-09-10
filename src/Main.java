import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.controllers.App;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/controllers/bank_main_layout.fxml"));
        Parent root = loader.load();

        test();

        primaryStage.setResizable(false);
        primaryStage.setTitle(App.AppName);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public void test()
    {
        String connectionString = "jdbc:mysql://localhost/citibankDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(   connectionString,"root","");
            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from dump");
            while(rs.next())
            {
                System.out.println(rs.getInt(rs.findColumn("id"))+"  "+rs.getString(rs.findColumn("account"))+"  ");
            }

            con.close();
            System.out.println("Done");
        }catch(Exception e){
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

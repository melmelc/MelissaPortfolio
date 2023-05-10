//MELISSA C
//C14200162 - SIB

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("JadwalDr.fxml"));
        primaryStage.setTitle("Form Jadwal Dokter");
        primaryStage.setScene(new Scene(root, 1020, 610));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

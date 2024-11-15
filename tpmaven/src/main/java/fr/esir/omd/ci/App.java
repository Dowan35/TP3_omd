package fr.esir.omd.ci;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main( String[] args )
    {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esir/omd/ci/TaskView.fxml"));
        primaryStage.setTitle("Liste de Tâches");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
}

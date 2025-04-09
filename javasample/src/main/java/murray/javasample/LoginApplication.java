package murray.javasample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/murray/javasample/login-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("/murray/javasample/Login-Screen.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
}

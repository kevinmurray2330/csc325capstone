package murray.javasample;

import com.google.cloud.firestore.Firestore;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class DashboardController {
    @FXML private Label welcomeLabel;
    @FXML private Button logoutButton;

    private Firestore fstore;

    public void initialize() {
        // This would be populated after login
        welcomeLabel.setText("Welcome, " + DemoApp.currentUserName + "!");
    }

    @FXML
    private void handleLogout() {
        DemoApp.fauth.signOut();
        DemoApp.setRoot("login");
    }
}
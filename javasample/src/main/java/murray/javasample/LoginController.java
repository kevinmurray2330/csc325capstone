package murray.javasample;

import com.google.firebase.auth.FirebaseAuthException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    private void handleLogin() throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and password cannot be empty");
            return;
        }

        try {
            // Store user info before navigation
            FirebaseUser user = DemoApp.fauth.signInWithEmailAndPassword(email, password).getUser();
            DemoApp.currentUserEmail = user.getEmail();
            DemoApp.currentUserName = user.getDisplayName();

            // Navigate to dashboard
            DemoApp.setRoot("dashboard");
        } catch (FirebaseAuthException e) {
            showAlert("Error", "Login failed: " + e.getMessage());
        }
    }
    @FXML
    private void switchToRegister() throws IOException {
        DemoApp.setRoot("register");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
package murray.javasample;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.cloud.firestore.DocumentReference;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button registerButton;
    @FXML private Button backButton;

    @FXML
    private void handleRegister() throws IOException {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields are required");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match");
            return;
        }

        try {

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setDisplayName(name)
                    .setEmail(email)
                    .setPassword(password);

            UserRecord userRecord = DemoApp.fauth.createUser(request);

            User newUser = new User(name, email, "customer");
            DocumentReference docRef = DemoApp.fstore.collection("users").document(userRecord.getUid());
            docRef.set(newUser);

            showAlert("Success", "Registration successful!");
            DemoApp.setRoot("login");
        } catch (FirebaseAuthException e) {
            showAlert("Error", "Registration failed: " + e.getMessage());
        }
    }

    @FXML
    private void switchToLogin() throws IOException {
        DemoApp.setRoot("login");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
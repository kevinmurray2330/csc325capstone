package murray.javasample;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private FirebaseAuth auth;

    public void initialize() {
        try {
            // Load the new service account JSON
            FileInputStream serviceAccount =
                    new FileInputStream("config/csc325capstone-adminsdk.json"); // Renamed to match your project

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc325capstone-1054a.firebaseio.com") // Your new project URL
                    .setStorageBucket("csc325capstone-1054a.appspot.com") // Add if using Storage
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            auth = FirebaseAuth.getInstance();
        } catch (Exception e) {
            errorLabel.setText("Firebase init error: " + e.getMessage());
        }
    }

    @FXML
    protected void handleLogin() {
        try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailField.getText(),
                    passwordField.getText()
            ).addOnSuccessListener(authResult -> {
                errorLabel.setText("Login successful! Redirecting...");
                // TODO: Redirect to main app screen
            }).addOnFailureListener(e -> {
                errorLabel.setText("Login failed: " + e.getMessage());
            });
        } catch (IllegalArgumentException e) {
            errorLabel.setText("Invalid email/password format");
        }
    }

    @FXML
    protected void handleRegister() {
        try {
            auth.createUserWithEmailAndPassword(
                    emailField.getText(),
                    passwordField.getText()
            ).addOnSuccessListener(authResult -> {
                // Create user document in Firestore
                Map<String, Object> user = new HashMap<>();
                user.put("email", emailField.getText());
                user.put("role", "customer");

                // TODO: Add Firestore document creation
                errorLabel.setText("Registration successful!");
            }).addOnFailureListener(e -> {
                errorLabel.setText("Registration failed: " + e.getMessage());
            });
        } catch (IllegalArgumentException e) {
            errorLabel.setText("Invalid email/password format");
        }
    }
}
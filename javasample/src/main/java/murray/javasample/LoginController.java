package murray.javasample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField passwordID;

    @FXML
    private TextField usernameID;

    @FXML
    void buttonClicked(ActionEvent event) {
        String username = usernameID.getText();
        String password = passwordID.getText();
    }

    @FXML
    void linkClicked(ActionEvent event) {

    }

}

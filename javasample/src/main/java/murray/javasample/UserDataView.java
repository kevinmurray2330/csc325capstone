package murray.javasample;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDataView {
    private final StringProperty userName = new SimpleStringProperty();
    private final StringProperty userEmail = new SimpleStringProperty();
    private final ReadOnlyBooleanWrapper writePossible = new ReadOnlyBooleanWrapper();

    public UserDataView() {
        writePossible.bind(userName.isNotEmpty().and(userEmail.isNotEmpty()));
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public StringProperty userEmailProperty() {
        return userEmail;
    }

    public ReadOnlyBooleanProperty isWritePossibleProperty() {
        return writePossible.getReadOnlyProperty();
    }
}
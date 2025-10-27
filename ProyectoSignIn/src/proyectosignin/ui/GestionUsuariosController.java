/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

/**
 *
 * @author juan
 */
public class GestionUsuariosController {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfMiddleName;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfState;
    @FXML
    private TextField tfZip;
    @FXML
    private PasswordField pfPass;
    @FXML
    private PasswordField pfConfirmPass;
    @FXML
    private Hyperlink hyperLink;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnBack;
    @FXML
    private Label emailMessage;
    @FXML
    private Label nameMessage;
    @FXML
    private Label lastNameMessage;
    @FXML
    private Label midNameMessage;
    @FXML
    private Label streetMessage;
    @FXML
    private Label stateMessage;
    @FXML
    private Label zipMessage;
    @FXML
    private Label phoneMessage;
    @FXML
    private Label cityMessage;
    @FXML
    private Label passwordMessage;
    @FXML
    private Label confirmPassMessage;

    private static final Logger LOGGER = Logger.getLogger("proyectosignin.ui");

    private static final String REGEX_NAME = "^[A-Za-z ]+$";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_])(?=.{8,}).*$";

    /**
     *
     * @param stage
     * @param root
     */
    public void initStage(Stage stage, Parent root) {
        try {

            LOGGER.info("Initializing window");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //Deshabilitar el botón de Sign Up hasta que todos los campos estén rellenos 
            btnSignUp.setDisable(true);

            //Establecer el título de la ventana a “Sign up”
            stage.setTitle("Sign In");

            //La ventana no debe ser redimensionable
            stage.setResizable(false);

            // Limpiar todos los campos de texto. 
            fieldsList().clear();

            //Ajustar el foco al primer texto, email
            tfEmail.requestFocus();

            //Asociacion de manejadores a properties
            tfEmail.focusedProperty()
                    .addListener(this::emailfocusChanged);
            tfFirstName.focusedProperty()
                    .addListener(this::nameFocusChanged);
            tfMiddleName.focusedProperty()
                    .addListener(this::midNameFocusChanged);
            tfLastName.focusedProperty()
                    .addListener(this::lastNameFocusChanged);
            tfPhone.focusedProperty()
                    .addListener(this::phoneFocusChanged);
            tfZip.focusedProperty()
                    .addListener(this::zipFocusChanged);
            tfCity.focusedProperty()
                    .addListener(this::cityFocusChanged);
            tfState.focusedProperty()
                    .addListener(this::stateFocusChanged);
            tfStreet.focusedProperty()
                    .addListener(this::streetFocusChanged);
            pfPass.focusedProperty()
                    .addListener(this::passwordFocusChanged);
            pfConfirmPass.textProperty().addListener(this::confirmPassChanged);

            //Asociar manjeadores a eventos
            btnBack.setOnAction(this::handleExitOnAction);
            btnSignUp.setOnAction(this::handleOnSignUpAction);
            hyperLink.setOnAction(this::handleOnClickLink);

            //Mostrar la ventana 
            stage.show();
            //Centrar la ventana en la pantalla
            stage.centerOnScreen();

        } catch (Exception e) {
            String erroMsg = "Error opening window:\n" + e.getMessage();

            LOGGER.log(Level.SEVERE, erroMsg);
        }

    }

    /**
     *
     * @param event
     */
    private void handleOnClickLink(ActionEvent event) {

        LOGGER.info("volviendo a la pagina de Sign In");

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void streetFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        try {
            if (!newValue) {
                String streer = tfStreet.getText().trim();
                if (streer.isEmpty()) {
                    throw new Exception("This field must be informed");
                }
                showCheckLabel(streetMessage, tfStreet);
            }
        } catch (Exception e) {
            showErrorLabel(e.getMessage(), streetMessage, tfStreet);
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void confirmPassChanged(ObservableValue observable, Object oldValue, Object newValue) {

        try {
            String confirmPass = pfConfirmPass.getText();
            String thisPass = pfPass.getText();

            // 1. Validación de Existencia
            if (confirmPass.isEmpty()) {
                throw new Exception("The field must be informed");
            }

            // 2. Validación de Coincidencia
            if (!thisPass.equals(confirmPass)) {
                throw new Exception("The password doesn't match");
            }

            // Si es válido: Limpiar error y borde
            showCheckLabel(confirmPassMessage, pfConfirmPass);

        } catch (Exception e) {
            // Si falla: Mostrar error y borde
            showErrorLabel(e.getMessage(), confirmPassMessage, pfConfirmPass);
        }

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void passwordFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            if (!newValue) {
                String password = pfPass.getText();

                //Validar contraseña
                if (password.isEmpty()) {
                    throw new Exception("The field must be informed");
                }
                if (!password.matches(REGEX_PASSWORD)) {
                    LOGGER.info("Contraseña no valida");
                    throw new Exception("Password does not meet requirements");
                }
                if (password.length() < 8) {
                    throw new IllegalArgumentException("The password is too short");
                }
                showCheckLabel(passwordMessage, pfPass);
            }
        } catch (Exception e) {
            showErrorLabel(e.getMessage(), passwordMessage, pfPass);
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void cityFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            if (!newValue) {
                String city = tfCity.getText().trim().toUpperCase();
                if (city.isEmpty()) {
                    throw new IllegalStateException("The field must be informed");
                }
                showCheckLabel(cityMessage, tfCity);
            }
        } catch (IllegalStateException e) {
            showErrorLabel(e.getMessage(), cityMessage, tfCity);
        }

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void stateFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        try {
            String state = tfState.getText().trim().toUpperCase();
            if (!newValue) {
                if (state.isEmpty()) {
                    throw new Exception("The field must be informed");
                }
                showCheckLabel(stateMessage, tfState);
            }

        } catch (Exception e) {
            showErrorLabel(e.getMessage(), stateMessage, tfState);
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void zipFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            if (!newValue) {
                String zip = tfZip
                        .getText()
                        .trim();
                if (zip.isEmpty()) {
                    throw new Exception("This field must be informed");
                }
                if (zip.length() != 6) {
                    throw new IllegalArgumentException("Zip length must be 6 digits");
                }
                showCheckLabel(zipMessage, tfZip);
            }
        } catch (Exception num) {
            showErrorLabel(num.getMessage(), zipMessage, tfZip);
        }

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void phoneFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            if (!newValue) {
                String phone = tfPhone
                        .getText()
                        .trim();

                if (phone.isEmpty()) {
                    throw new Exception("This field must be informed");
                }
                if (phone.length() != 9) {
                    throw new Exception("Phone length must be 9 digits");
                }

                showCheckLabel(phoneMessage, tfPhone);
            }

        } catch (Exception num) {
            showErrorLabel(num.getMessage(), phoneMessage, tfPhone);
        }

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void midNameFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            String midName = tfMiddleName.getText().toUpperCase().trim();
            if (!newValue) {
                if (midName.isEmpty()) {
                    throw new Exception("This field must be informed");
                }
                if (midName.length() != 1) {
                    throw new Exception("The mid name must be 1 letter");
                }
                if (!midName.matches(REGEX_NAME)) {
                    throw new Exception("Format mid name invalid");
                }
                showCheckLabel(midNameMessage, tfMiddleName);
            }
        } catch (Exception e) {
            showErrorLabel(e.getMessage(), midNameMessage, tfMiddleName);
        }

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void lastNameFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            if (!newValue) {
                String lastName = tfLastName.getText().toUpperCase().trim();
                if (lastName.isEmpty()) {
                    throw new Exception("This field must be informed");
                }
                if (!lastName.matches(REGEX_NAME)) {
                    throw new Exception("Format last name invalid");
                }
                showCheckLabel(lastNameMessage, tfLastName);
            }
        } catch (Exception e) {
            showErrorLabel(e.getMessage(), lastNameMessage, tfLastName);
        }

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void nameFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            if (!newValue) {
                String fName = tfFirstName.getText().toUpperCase().trim();
                if (fName.isEmpty()) {
                    throw new Exception("This field must be informed");
                }
                if (!fName.matches(REGEX_NAME)) {
                    throw new Exception("Format name invalid");
                }
                showCheckLabel(nameMessage, tfFirstName);
            }
        } catch (Exception name) {
            showErrorLabel(name.getMessage(), nameMessage, tfFirstName);
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void emailfocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            if (!newValue) {
                LOGGER.info("Validando");
                String emailText = tfEmail.getText().trim();
                //Validar que el campo no este vacio 
                if (emailText.isEmpty()) {
                    throw new Exception("This field must be informed");
                }

                //Validar que cumple el límite de caracteres posibles para el campo 
                if (emailText.length() > 255) {
                    throw new Exception("The email exceeds the maximum length");
                }
                //Validar que tenga el formato correcto 
                if (!emailText.matches(REGEX_EMAIL)) {
                    throw new Exception("Invalid email format");
                }
                //Si el campo es válido, mostrar etiqueta asociada al campo con mensaje de validación correcta  
                showCheckLabel(emailMessage, tfEmail);
            }
        } catch (Exception e) {
            showErrorLabel(e.getMessage(), emailMessage, tfEmail);

        }

    }

    /**
     *
     * @param event
     */
    private void handleOnSignUpAction(ActionEvent event) {

    }

    /**
     *
     * @param event
     */
    private void handleExitOnAction(ActionEvent event) {

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to exit?",
                    ButtonType.OK,
                    ButtonType.CANCEL);

            Optional<ButtonType> btnType = alert.showAndWait();
            if (btnType.isPresent()) {
                ButtonType btnOk = btnType.get();
                if (btnOk.equals(ButtonType.OK)) {
                    LOGGER.info("Cerrando programa");
                    //Platform.exit(); --> Posible solucion para cerrar la ventana pero cierra toda la app
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * Utility method for showing messages.
     *
     * @param erroMsg
     * @param label
     * @param textField
     */
    protected void showErrorLabel(String erroMsg, Label label, TextField textField) {

        label.setVisible(true);
        label.setText(erroMsg);
        label.setStyle("-fx-text-fill: red;");
        textField.setStyle("-fx-border-color: red");

    }

    /**
     *
     * @param label
     * @param textField
     */
    protected void showCheckLabel(Label label, TextField textField) {
        label.setVisible(true);
        label.setText("✔");
        label.setStyle("-fx-text-fill: green;");
        textField.setStyle("-fx-border-color: green");
    }

    private List<TextField> fieldsList() {

        List<TextField> fields = new ArrayList<>();

        fields.add(tfZip);
        fields.add(tfEmail);
        fields.add(tfCity);
        fields.add(tfFirstName);
        fields.add(tfLastName);
        fields.add(tfMiddleName);
        fields.add(tfPhone);
        fields.add(tfState);
        fields.add(tfStreet);
        fields.add(pfConfirmPass);
        fields.add(pfPass);

        return fields;

    }

}

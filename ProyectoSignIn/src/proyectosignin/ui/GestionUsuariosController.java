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

import proyectosignin.exceptions.InvalidLastNameException;
import proyectosignin.exceptions.InvalidNameException;
import proyectosignin.exceptions.InvalidMidNameException;

import javafx.stage.Stage;

/**
 *
 * @author juan
 */
public class GestionUsuariosController {

    static final String REGEX_NAME = "^[A-Za-z ]+$";
    static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

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
            //Asociar manjeadores a eventos
            btnBack.setOnAction(this::handleExitOnAction);
            btnSignUp.setOnAction(this::handleOnSignUpAction);

            //Mostrar la ventana 
            stage.show();
            //Centrar la ventana en la pantalla
            stage.centerOnScreen();

        } catch (Exception e) {
            String erroMsg = "Error opening window:\n" + e.getMessage();

            LOGGER.log(Level.SEVERE, erroMsg);
        }

    }

    private void zipFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {

                String zip = tfZip
                        .getText()
                        .trim();
                if (zip.isEmpty()) {
                    throw new NumberFormatException();
                }
                int intZip = Integer.parseInt(zip);

                if (zip.length() != 5) {
                    throw new IllegalArgumentException("Zip length must be 6 digits");
                }
                showCheckLabel(zipMessage);
            } catch (NumberFormatException num) {
                showErrorLabel("This field must be informed", zipMessage);
            } catch (IllegalArgumentException e) {
                showErrorLabel(e.getMessage(), zipMessage);
            }
        }
    }

    private void phoneFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String phone = tfPhone
                        .getText()
                        .trim();

                if (phone.isEmpty()) {
                    throw new NumberFormatException();
                }
                int intPhone = Integer.parseInt(phone);

                if (phone.length() != 9) {
                    throw new IllegalArgumentException("Phone length must be 9 digits");
                }

                showCheckLabel(phoneMessage);

            } catch (NumberFormatException e) {
                showErrorLabel("The phone must be numberic only", phoneMessage);
            } catch (IllegalArgumentException num) {
                showErrorLabel(num.getMessage(), phoneMessage);
            }
        }
    }
//TODO lanzar excepciones si el campo esta vacio

    private void midNameFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        String midName = tfMiddleName.getText().toUpperCase().trim();

        if (!newValue) {
            try {
                if (midName.isEmpty()) {
                    throw new InvalidMidNameException("This field must be informed");
                }
                if (!midName.matches(REGEX_NAME)) {
                    throw new InvalidMidNameException("Format mid name invalid");
                }
                showCheckLabel(midNameMessage);
            } catch (InvalidMidNameException e) {
                showErrorLabel(e.getMessage(), midNameMessage);
            }
        }
    }

    private void lastNameFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        String lastName = tfLastName.getText().toUpperCase().trim();
        if (!newValue) {
            try {
                if (!lastName.matches(REGEX_NAME)) {
                    throw new InvalidLastNameException("Format last name invalid");
                }
                showCheckLabel(lastNameMessage);
            } catch (InvalidLastNameException e) {
                showErrorLabel(e.getMessage(), lastNameMessage);
            }
        }

    }

    private void nameFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        String fName = tfFirstName.getText().toUpperCase().trim();
        if (!newValue) {
            try {
                if (!fName.matches(REGEX_NAME)) {
                    throw new InvalidNameException("Format name invalid");
                }
                showCheckLabel(nameMessage);
            } catch (InvalidNameException name) {
                showErrorLabel(name.getMessage(), nameMessage);
            }
        }
    }

    private void emailfocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            LOGGER.info("Validando");

            String emailText = tfEmail.getText().trim();
            try {
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
                showCheckLabel(emailMessage);

            } catch (Exception e) {
                showErrorLabel(e.getMessage(), emailMessage);

            }
        }

    }

    public void handleOnSignUpAction(ActionEvent event) {

    }

    public void handleExitOnAction(ActionEvent event) {

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
    }

    /**
     * Utility method for showing messages.
     *
     * @param erroMsg
     * @param label
     */
    protected void showErrorLabel(String erroMsg, Label label) {

        label.setVisible(true);
        label.setText(erroMsg);
        label.setStyle("-fx-text-fill: red;");

    }

    protected void showCheckLabel(Label label) {
        label.setVisible(true);
        label.setText("✔");
        label.setStyle("-fx-text-fill: green;");
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

        return fields;

    }

}

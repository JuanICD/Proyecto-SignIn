/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin.ui;

import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.Pane;

import proyectosignin.exceptions.InvalidLastNameException;
import proyectosignin.exceptions.InvalidNameException;
import proyectosignin.exceptions.InvalidMidNameException;

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
    private Pane mainPane;
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
                    .addListener(this::nameFocusChanged);
            tfLastName.focusedProperty()
                    .addListener(this::nameFocusChanged);

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

    /**
     *
     */
    public void handleOnSignUpAction(ActionEvent event) {

    }

    public void handleExitOnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit?",
                ButtonType.OK,
                ButtonType.CANCEL);

        alert.showAndWait();

    }

    private void nameFocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        try {
            //Validar que los campos no lleven espacios de más ni al principio ni al final
            //Convertir todos los caracteres a mayúsculas antes de registrar o de pasar a la base de datos 
            String fName = tfFirstName.getText().toUpperCase().trim();
            String mName = tfMiddleName.getText().toUpperCase().trim();
            String lName = tfLastName.getText().toUpperCase().trim();
            String regexName = "^[A-Za-z ]+$";

            //Validar que los campos no estén vacíos 
            if (fName.isEmpty()) {
                throw new InvalidNameException("The field must be informed ");
            }
            if (mName.isEmpty()) {
                throw new InvalidMidNameException("The field must be informed ");
            }
            if (lName.isEmpty()) {
                throw new InvalidLastNameException("The field must be informed");
            }
            //Validar que no hay datos numéricos 

            //Validar que no contengan caracteres especiales 
            if (!fName.matches(regexName)) {
                throw new InvalidNameException("Format name invalid");
            }
            if (!mName.matches(regexName)) {
                throw new InvalidMidNameException("Format middle name invalid");
            }
            if (!lName.matches(regexName)) {
                throw new InvalidLastNameException("Format last name invalid");
            }

            showCheckLabel(nameMessage);

        } catch (InvalidNameException name) {
            showErrorLabel(name.getMessage(), nameMessage);
        } catch (InvalidMidNameException midName) {
            showErrorLabel(midName.getMessage(), midNameMessage);
        } catch (InvalidLastNameException lastName) {
            showErrorLabel(lastName.getMessage(), lastNameMessage);
        }
    }

    private void emailfocusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            LOGGER.info("Validando");
            String validator = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
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
                if (!emailText.matches(validator)) {
                    throw new Exception("Invalid email format");
                }
                //Si el campo es válido, mostrar etiqueta asociada al campo con mensaje de validación correcta  
                showCheckLabel(emailMessage);

            } catch (Exception e) {
                showErrorLabel(e.getMessage(), emailMessage);

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

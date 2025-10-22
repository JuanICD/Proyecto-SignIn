/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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

    private static final Logger LOGGER = Logger.getLogger("proyectosignin.ui");

    public void initStage(Stage stage) {

        LOGGER.info("Initializing window");

        //Deshabilitar el botón de Sign Up hasta que todos los campos estén rellenos 
        btnSignUp.setDisable(true);

        //Establecer el título de la ventana a “Sign up”
        stage.setTitle("Sign In");

        //La ventana no debe ser redimensionable
        stage.setResizable(false);

        // Limpiar todos los campos de texto. 
        fieldsList().clear();

        //Ajustar el foco al primer texto, email
        tfEmail.focusedProperty().addListener(this::focusChanged);
        tfEmail.requestFocus();

        //Mostrar la ventana 
        stage.show();
        //Centrar la ventana en la pantalla
        stage.centerOnScreen();

    }

    private void focusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            LOGGER.info("onFocus");
        } else if (oldValue) {
            LOGGER.info("onBlur");
        }
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

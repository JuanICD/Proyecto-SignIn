/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin2.ui;



import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

//package proyectosignin2.ui;

import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.ws.rs.InternalServerErrorException;
import proyectosignin2.logic.CustomerRESTClient;
import proyectosignin2.model.Customer;



/**
 * Controlador para el cambio de contraseña.
 * @author puchol
 */
public class ChangePasswordController {

    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField repeatNewPassword;
    @FXML
    private Button back;
    @FXML
    private Button applyExit;
    @FXML
    private Label error1;
    @FXML
    private Label error2;
    @FXML
    private Label error3;

    private static final Logger LOGGER = Logger.getLogger("ui/proyectosignin2.ui");
    private Customer customer;
    
     public void setCustomer(Customer customer){
        this.customer = customer;
    }
   
    public void init(Stage stage, Parent root) {
        LOGGER.info("Initializing Change Password window");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Change Password");
        stage.setResizable(false);

        //applyExit.setDisable(true);

        // Asociar eventos a botones
        applyExit.setOnAction(this::handleApplyExitOnAction);
        back.setOnAction(this::handleBackOnAction);

        // Asociar validaciones a los campos
        currentPassword.textProperty().addListener(this::handleCurrentPassword);
        newPassword.textProperty().addListener(this::handleNewPassword);
        repeatNewPassword.textProperty().addListener(this::handleRepeatNewPassword);

        stage.show();
    }

    // VALIDACIONES CON TRY / CATCH

    private void handleCurrentPassword(ObservableValue observable, String oldValue, String newValue) {
        try {
            validarContraseñaActual(newValue);
            error1.setText("");
        } catch (ValidationException e) {
            error1.setText(e.getMessage());
        } finally {
            checkAllValid();
        }
    }

    private void handleNewPassword(ObservableValue observable, String oldValue, String newValue) {
        try {
            validarNuevaContraseña(newValue);
            error2.setText("");
        } catch (ValidationException e) {
            error2.setText(e.getMessage());
        } finally {
            checkAllValid();
        }
    }

    private void handleRepeatNewPassword(ObservableValue observable, String oldValue, String newValue) {
        try {
            validarRepetición(newPassword.getText(), newValue);
            error3.setText("");
        } catch (ValidationException e) {
            error3.setText(e.getMessage());
        } finally {
            checkAllValid();
        }
    }

    // MÉTODOS DE VALIDACIÓN

    private void validarContraseñaActual(String password) throws ValidationException {
        if (password.isEmpty()) {
            throw new ValidationException("The current password cannot be empty.");
        }
        if (!password.equals(customer.getPassword())) {
            throw new ValidationException("The current password is incorrect.");
        }
    }

    private void validarNuevaContraseña(String password) throws ValidationException {
        if (password.isEmpty()) {
            throw new ValidationException("The new password cannot be empty.");
        }
        if (password.length() < 10) {
            throw new ValidationException("It must have at least 10 characters.");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new ValidationException("It must contain at least one uppercase letter.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new ValidationException("It must contain at least one number.");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'|<>,.?/~`].*")) {
        throw new ValidationException("It must contain at least one symbol (for example: @, #, $, %, &).");
        }
        if (password.equals(customer.getPassword())) {
            throw new ValidationException("The new password cannot be the same as the current one.");
        }
    }

    private void validarRepetición(String nueva, String repetida) throws ValidationException {
        if (repetida.isEmpty()) {
            throw new ValidationException("You must repeat the new password.");
        }
        if (!repetida.equals(nueva)) {
            throw new ValidationException("The passwords do not match.");
        }
    }

    
    // HABILITAR / DESHABILITAR BOTÓN
    

    private void checkAllValid() {
        boolean valid =
                error1.getText().isEmpty() &&
                error2.getText().isEmpty() &&
                error3.getText().isEmpty() &&
                !currentPassword.getText().isEmpty() &&
                !newPassword.getText().isEmpty() &&
                !repeatNewPassword.getText().isEmpty();

        //applyExit.setDisable(!valid);
    }

     
    // EVENTOS DE BOTONES
     

    private void handleApplyExitOnAction(ActionEvent event) {
        
        try{
            //Crear un objeto Customer
            Customer customer = new Customer();
            //Establecer propiedades del objeto a partir de los valores de los campos
            customer.setPassword("");
            CustomerRESTClient client=new CustomerRESTClient();
            client.edit_XML(customer);
            client.close();
            //Indicar al ususario que ha cambiado la contraseña correctamente
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password changed successfully.");
            //abrir ventana de Change Password
            //Solo se me produce la 500 InternalServerErrorException
        }catch(InternalServerErrorException e){
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        
        try {
            validarContraseñaActual(currentPassword.getText());
            validarNuevaContraseña(newPassword.getText());
            validarRepetición(newPassword.getText(), repeatNewPassword.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password changed successfully.");
            alert.showAndWait();

            ((Stage) applyExit.getScene().getWindow()).close();
        } catch (ValidationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }
    
    private void handleBackOnAction(ActionEvent event) {
        ((Stage) back.getScene().getWindow()).close();
    }
    // 
    // CLASE INTERNA DE EXCEPCIÓN PERSONALIZADA
    // 
    private static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}

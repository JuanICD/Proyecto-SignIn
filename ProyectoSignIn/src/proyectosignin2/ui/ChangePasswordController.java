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

/**
 *
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
    
    private static final Logger LOGGER=Logger.getLogger("proyectosignin2.ui");

    public void init(Stage stage,Parent root) {
    LOGGER.info("Initializing login");
    Scene scene = new Scene(root);  
    stage.setScene(scene);
    
    //Establecer el título de la ventana
    stage.setTitle("Change Password");
    
    //La ventana no debe ser redimensionable
    stage.setResizable(false);
    
    //El boton back estara habilitado
    
    //El boton aplicar y salir estara deshabilitado    
    applyExit.setDisable(true);
    
    //Asociar eventos a manejadores    
    applyExit.setOnAction(this::handleApplyExitOnAction);
    back.setOnAction(this::handleBackOnAction);
    
    //Asociación de manejadores a properties
    currentPassword.textProperty().addListener(this::handleCurrentPassword);
    newPassword.textProperty().addListener(this::handleNewPassword);
    repeatNewPassword.textProperty().addListener(this::handleRepeatNewPassword);
    
    //Mostrar la ventana
    stage.show();

    }
/**
 * 
 * @param observable
 * @param oldValue
 * @param newValue 
 */    
private void handleCurrentPassword(ObservableValue observable,
                                      String oldValue,
                                      String newValue){
    
    
}
/**
 * 
 * @param observable
 * @param oldValue
 * @param newValue 
 */
private void handleCurrentPasswordFocusChange(ObservableValue observable,
                                      Boolean oldValue,
                                      Boolean newValue){
    if(oldValue){
    
    }   
}
/**
 * 
 * @param observable
 * @param oldValue
 * @param newValue 
 */
private void handleNewPassword(ObservableValue observable,
                                      String oldValue,
                                      String newValue){


}
/**
 * 
 * @param observable
 * @param oldValue
 * @param newValue 
 */
private void handleRepeatNewPassword(ObservableValue observable,
                                      String oldValue,
                                      String newValue){


}
/**
 * 
 * @param event 
 */
private void handleApplyExitOnAction(ActionEvent event){


}
/**
 * 
 * @param event 
 */
private void handleBackOnAction(ActionEvent event){


}
}
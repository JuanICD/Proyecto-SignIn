/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin2.ui;



import java.util.logging.Logger;
import javafx.fxml.FXML;
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
    private PasswordField cp;
    @FXML
    private PasswordField np;
    @FXML
    private PasswordField rnp;
    @FXML
    private Button b;
    @FXML
    private Button ae;
    @FXML
    private Label e1;
    @FXML
    private Label e2;
    @FXML
    private Label e3;
    
    private static final Logger LOGGER=Logger.getLogger("proyectosignin2.ui");

    public void init(Stage stage) {
    LOGGER.info("Initializing login");
    //Establecer el título de la ventana
    stage.setTitle("Change Password");
    //La ventana no debe ser redimensionable
    stage.setResizable(false);
    //El boton back estara habilitado
    //El boton aplicar y salir estara deshabilitado    
    ae.setDisable(true);

    }
    

    


    
}

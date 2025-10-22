/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin1.ui;

import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for user management windows
 * @author alex
 */
public class GestionUsuariosController {
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPasswd;
    @FXML
    private Button btSignIn;
    @FXML
    private Button btExit;
    @FXML
    private Hyperlink hlRegister;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorSignIn;
    private static final Logger LOGGER=Logger.getLogger("proyectosignin1.ui");

    public void initStage(Stage stage) {
        LOGGER.info("Initializing windows");
//•Establecer el título de la ventana al valor "Sign In".
    stage.setTitle("Sign In");
//• La ventana no debe ser redimensionable.
    stage.setResizable(false);
//• Deshabilitar temporalmente el botón Sign In hasta que ambos campos contengan texto.
    btSignIn.setDisable(true);
//• Mostrar la ventana.
//•Se enfoca en el campo Email
    }
    
}

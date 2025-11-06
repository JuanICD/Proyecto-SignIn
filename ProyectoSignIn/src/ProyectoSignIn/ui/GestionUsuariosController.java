package ProyectoSignIn.ui;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import ProyectoSignIn.model.CustomerRESTClient;
import logic.Customer;

/**
 * Controller class for user management windows
 * @author Alex
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
    
    private Stage stage;

    public void initStage(Stage stage,Parent root) {
        LOGGER.info("Initializing windows");
        this.stage = stage;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //Establecer el titulo de la ventana al valor "Sign In".
        stage.setTitle("Sign In");
        //La ventana no debe ser redimensionable.
        stage.setResizable(false);
        //Deshabilitar temporalmente el boton Sign In hasta que ambos campos contengan texto.
        btSignIn.setDisable(true);
        //Asociar eventos a manejadores
        btSignIn.setOnAction(this::handleBtSignInOnAction);
        btExit.setOnAction(this::handleBtExitOnAction);
        hlRegister.setOnAction(this::handleHlRegisterOnAction);
        tfUser.textProperty().addListener(this::handleTfUserTextChange);
        pfPasswd.textProperty().addListener(this::handlePfPasswdTextChange);
        /*tfUser.focusedProperty().addListener(this::handleTfUserFocusChange);*/
        //Controlar cierre de ventana
        stage.setOnCloseRequest(this::handleWindowCloseRequest);
        //Mostrar la ventana.
        stage.show();
        //Se enfoca en el campo Email
        tfUser.requestFocus();
        lbErrorSignIn.setText("");
    }
   private void handleBtSignInOnAction(ActionEvent event){
    try {
        String email = tfUser.getText().trim();
        String passwd = pfPasswd.getText().trim();
        CustomerRESTClient client = new CustomerRESTClient();
        Customer customer = client.findCustomerByEmailPassword_XML(Customer.class, email, passwd);
        client.close();
        if(customer == null){
            lbErrorSignIn.setText("Incorrect email or password");
        }
        LOGGER.info("User authenticated: " + customer.getEmail());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CambiarContraseña.fxml"));
        Parent root = loader.load();
        GestionUsuariosController controller =loader.getController();
        
        //controller.setCustomer(customer);
        controller.initStage(stage, root);

    } catch (NotAuthorizedException e) {
        Alert alert = new Alert(AlertType.ERROR, "Invalid credentials or server unavailable.");
        alert.showAndWait();
    } catch (InternalServerErrorException e) {
        Alert alert = new Alert(AlertType.ERROR, "Error server down como puchol, try later: " + e.getMessage());
        alert.showAndWait();
    } catch (Exception e) {
        Alert alert = new Alert(AlertType.ERROR, e.getMessage());
        alert.showAndWait();
    }
}
    /**
     * 
     * @param event 
     */

    private void handleBtExitOnAction(ActionEvent event){
        Alert confirm = new Alert(AlertType.CONFIRMATION, "Estas seguro que quieres salir?");
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage.close();
        }
    }
    /**
     * 
     * @param event 
     */
    
    private void handleHlRegisterOnAction(ActionEvent event){
        try{
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("SignUP_View.fxml"));
        Parent root = loader.load();
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
        }catch(Exception e){
           Alert alert = new Alert(AlertType.ERROR, e.getMessage());
            alert.showAndWait(); 
        }
    }

    private void handleTfUserTextChange(ObservableValue observable,String oldValue,String newValue){
        validateFields();
        // Validar formato de email
        if(!newValue.isEmpty() && !isValidEmail(newValue)){
            lbErrorEmail.setText("Invalid email format");
        }else{
            lbErrorEmail.setText("");
        }
    }
    private void handletfErrorSignIn(ObservableValue observable,String oldValue,String newValue){
    
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    

    private void handlePfPasswdTextChange(ObservableValue observable,String oldValue,String newValue){
        validateFields();
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */

    /*private void handleTfUserFocusChange(ObservableValue observable,Boolean oldValue,Boolean newValue){
        if(oldValue){
            String email = tfUser.getText();
            if(!email.isEmpty() && !isValidEmail(email)){
                lbErrorEmail.setText("Invalid email format");
            }else{
                lbErrorEmail.setText("");
            }
        }
    }*/
    /**
     * 
     * @param event 
     */
    
    private void handleWindowCloseRequest(WindowEvent event){
        Alert confirm = new Alert(AlertType.CONFIRMATION, "Estas seguro que quieres salir?");
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.isPresent() && result.get() != ButtonType.OK){
            event.consume();
        }
    }
    /**
     * 
     * @param email
     * @return 
     */
    private boolean isValidEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }
    

    private void validateFields(){
        boolean disable = tfUser.getText().isEmpty() || pfPasswd.getText().isEmpty();
        btSignIn.setDisable(disable);
    }

}
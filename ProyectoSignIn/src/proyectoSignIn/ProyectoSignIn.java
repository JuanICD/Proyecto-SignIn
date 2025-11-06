/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoSignIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyectoSignIn.ui.ChangePasswordController;

/**
 *
 * @author puchol
 */
public class ProyectoSignIn extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectosignin2/ui/ChangePassword.fxml"));

        Parent root = (Parent)loader.load();
        ChangePasswordController controller = loader.getController();
        
        controller.init(stage,root);
        
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import proyectoSignIn.ProyectoSignInApplication;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import proyectoSignIn.ProyectoSignInApplication;

/**
 *
 * @author alex
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GestionUsuariosControllerTest extends ApplicationTest{
    @Override
    public void start(Stage stage) throws Exception {
        //start JavaFX application to be tested    
        new ProyectoSignInApplication().start(stage);
   }
    @Test
    public void testSomeMethod() {
    }
    
}

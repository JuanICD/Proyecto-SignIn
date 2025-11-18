/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoSignIn;

import org.junit.Test;
import static org.junit.Assert.*;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import proyectoSignIn.ProyectoSignInApplication;
/**
 *
 * @author alex
 */
public class ProyectoSignInApplicationTest extends ApplicationTest{

    @Override
    public void start(Stage stage) throws Exception {
        new ProyectoSignInApplication().start(stage);

    }
    @Test
    public void testIncorrectEmail(){
        clickOn("#tfUser");
        write("incorrectEmail@gmail.com");
        clickOn("#pfPasswd");
        write("Abcdabcd*1234");
        clickOn("#btSignIn");
        
    }
    @Test
    public void testIncorrectPasswd(){
    clickOn("#tfUser");
        write("app@gmail.com");
        clickOn("#pfPasswd");
        write("Abcdabcd*2234");
        clickOn("#btSignIn");
    }
    @Test
    public void testlogin(){
    clickOn("#tfUser");
        write("app@gmail.com");
        clickOn("#pfPasswd");
        write("Abcdabcd*1234");
        clickOn("#btSignIn");
    }
    @Test
    public void testIncorrectFormat(){
    clickOn("#tfUser");
        write("incorrectformat.");
        clickOn("#pfPasswd");
        write("Abcdabcd*1234");
        clickOn("#btSignIn");
    }
    @Test
    public void testPasswdEmpty(){
    clickOn("#tfUser");
        write("app@gmail.com");
        clickOn("#btSignIn");
    }
    @Test
    public void testRegister(){
        clickOn("#hlRegister");
    }
    @Test
    public void testExit(){
        clickOn("#btExit");
        clickOn("Aceptar");
    }
}

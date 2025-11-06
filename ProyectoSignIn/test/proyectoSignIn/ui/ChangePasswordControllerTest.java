/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoSignIn.ui;

import javafx.stage.Stage;
import org.junit.Test;
//import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
//import static org.testfx.matcher.base.NodeMatchers.isDisabled;
//import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
//import org.testfx.assertions.api.Assertions;
import proyectoSignIn.ProyectoSignIn;

/**
 *
 * @author puchol
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChangePasswordControllerTest extends ApplicationTest {
    
    
    @Override
    public void start (Stage stage) throws Exception{
        new ProyectoSignIn().start(stage);
    }
    @Test
    public void testCurrentPasswordIsFocused() {
        verifyThat("#currentPassword",isFocused());
        
    }
    /*@Test
    public void test1_InitialState() {
        verifyThat("#currentPassword", hasText(""));
        verifyThat("#tfPassword",hasText(""));
        verifyThat("#btAceptar", isDisabled());
    }*/
    //sea correcta o no(que exista)
    //no permita contraseña nueva sea igual que la vieja
    //que coincidan la nueva cuando se repite
    
}

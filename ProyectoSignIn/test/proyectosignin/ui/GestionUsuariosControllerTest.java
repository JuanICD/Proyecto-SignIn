/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin.ui;

import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import proyectosignin.SignInSignUpApplication;

/**
 *
 * @author juan
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GestionUsuariosControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new SignInSignUpApplication().start(stage);
    }

    @Test
    public void test1_EmailisFocus() {
        verifyThat("#tfEmail", isFocused());
    }

    @Test
    public void test2_EmailAlreadyExist() {
        clickOn("#tfEmail");
        write("juan@dwq.com");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfFirstName");
        write("Juan");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfLastName");
        write("Caiza");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfMiddleName");
        write("I");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfStreet");
        write("P/Castellanos");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfCity");
        write("Madrid");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfState");
        write("Madrid");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfPhone");
        write("626170034");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfZip");
        write("280256");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#pfPass");
        write("Validar1.");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#pfConfirmPass");
        write("Validar1.");
        verifyThat("#btnSignUp", isEnabled());
        
        clickOn("#btnSignUp");
        
        verifyThat("Email already exist", isVisible());

    }
    
    @Test
    public void test3_UserRegister(){
        clickOn("#tfEmail");
        write("juan@caiza.com");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfFirstName");
        write("Juan");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfLastName");
        write("Caiza");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfMiddleName");
        write("I");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfStreet");
        write("P/Castellanos");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfCity");
        write("Madrid");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfState");
        write("Madrid");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfPhone");
        write("626170034");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#tfZip");
        write("280256");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#pfPass");
        write("Validar1.");
        verifyThat("#btnSignUp", isDisabled());
        
        clickOn("#pfConfirmPass");
        write("Validar1.");
        verifyThat("#btnSignUp", isEnabled());
        
        clickOn("#btnSignUp");
        
        verifyThat("User register", isVisible());

    }

}

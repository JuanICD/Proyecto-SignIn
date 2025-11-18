/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignin.ui;

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
 * @author juan
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Error500Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new ProyectoSignInApplication().start(stage);

    }
    @Before
    public void test_changeWindow(){
        clickOn("Register");
        verifyThat("#mainPane", isVisible());
        verifyThat("#tfEmail", isFocused());
    }
    

    @Test
    public void test1_fillFields() {
     
        clickOn("#tfEmail");
        write("juan@" + System.currentTimeMillis() + ".com");
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

        verifyThat("Server error, try later", isVisible());
        clickOn("Aceptar");

    }

}

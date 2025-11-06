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
    public void test2_UserRegister() {
        clickOn("#tfEmail");
        write("puma@marranote.com");
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
        clickOn("Aceptar");

    }

    @Test
    public void test3_EmailAlreadyExist() {
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
        clickOn("Aceptar");

    }

    @Test
    public void test4_LabelsCheck() {
        clickOn("#tfEmail");

        clickOn("#tfFirstName");
        verifyThat("#emailMessage", isVisible());

        clickOn("#tfLastName");
        verifyThat("#nameMessage", isVisible());

        clickOn("#tfMiddleName");
        verifyThat("#lastNameMessage", isVisible());

        clickOn("#tfStreet");
        verifyThat("#midNameMessage", isVisible());

        clickOn("#tfCity");
        verifyThat("#streetMessage", isVisible());

        clickOn("#tfState");
        verifyThat("#cityMessage", isVisible());

        clickOn("#tfPhone");
        verifyThat("#stateMessage", isVisible());

        clickOn("#tfZip");
        verifyThat("#btnSignUp", isVisible());

        clickOn("#pfPass");
        verifyThat("#phoneMessage", isVisible());

        clickOn("#pfConfirmPass");
        verifyThat("#passwordMessage", isVisible());

    }

    @Test
    public void test5_HyperLinkClick() {
        clickOn("Sign In");
        verifyThat("Are you sure you want to exit, all changes will delete", isVisible());
        clickOn("Aceptar");
        verifyThat("Sign In", isVisible());
        clickOn("Register");
        verifyThat("#mainPane", isVisible());
    }

    @Test
    public void test6_BackButton() {
        clickOn("#btnBack");
        verifyThat("Are you sure you want to exit?", isVisible());
        clickOn("Aceptar");
        verifyThat("Sign In", isVisible());
        clickOn("Register");
        verifyThat("#mainPane", isVisible());
    }

}

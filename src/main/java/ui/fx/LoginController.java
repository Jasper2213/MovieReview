package ui.fx;

import service.MovieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblErrorMessage;

    private final MovieService service;

    public LoginController() {
        service = new MovieService();
    }

    public void onLogin(ActionEvent actionEvent) {
        if (service.login(
                txtUsername.getText(),
                txtPassword.getText()
        )) {
            showMovieReviewScreen();
        } else {
            showError("Failed to login");
        }
    }

    public void onRegister(ActionEvent actionEvent) {
        if (service.register(
                txtUsername.getText(),
                txtPassword.getText()
        )) {
            showMovieReviewScreen();
        } else {
            showError("Failed to register");
        }
    }

    private void showMovieReviewScreen() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("i22n");
            FXMLLoader loader = new FXMLLoader(Startup.class.getResource("/fxml/MovieReview.fxml"), resourceBundle);
            Parent parent = loader.load();
            MovieReviewController controller = loader.getController();
            controller.setService(service);
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(parent));
            secondStage.show();

            Stage firstStage = (Stage) txtUsername.getScene().getWindow();
            firstStage.close();
        }
        catch (IOException ex) {
            LOGGER.log(Level.INFO, "Failed to show second screen", ex);
            showError("Failed to show second screen");
        }
    }

    private void showError(String message) {
        lblErrorMessage.setText(message);
    }
}

package controller;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    public Label lblerror;
    @FXML
    private AnchorPane loginAnconepane;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnLogin;


    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {

        if (txtUsername.getText().isEmpty()) {
            lblerror.setText("Please Enter Your Username");
            lblerror.setStyle("-fx-text-fill: red");
        } else {
            ClientController.username = txtUsername.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientView.fxml"));
            Parent load = loader.load();
            ClientController clientController = loader.getController();
            Stage window = (Stage) loginAnconepane.getScene().getWindow();
            window.setScene(new Scene(load));
        }
    }
}


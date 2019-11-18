package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSetPlayer implements Initializable {

    private String[] player;

    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private TextField player3;
    @FXML
    private TextField player4;
    @FXML
    private Button startButton;

    public void backToMenu(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = new String[4];
    }

    /*
    @FXML
    private void getPlayer1(ActionEvent event) {
        player[0] = player1.getText();
    }
    @FXML
    private void getPlayer2(ActionEvent event) {
        player[1] = player2.getText();
    }
    @FXML
    private void getPlayer3(ActionEvent event) {
        player[2] = player3.getText();
    }
    @FXML
    private void getPlayer4(ActionEvent event) {
        player[3] = player4.getText();
    }
     */

    public void PlayGame(ActionEvent event)
    {
        player[0] = player1.getText();
        player[1] = player2.getText();
        player[2] = player3.getText();
        player[3] = player4.getText();

        System.out.println(player[0]);
        System.out.println(player[1]);
        System.out.println(player[2]);
        System.out.println(player[3]);
    }
}

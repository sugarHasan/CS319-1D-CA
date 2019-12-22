package View;

import Control.ClientGameManager;
import Control.GameManager;
import Control.ServerGameManager;
import Model.Offer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class ControllerOfferPopUp implements Initializable {

    /*@FXML public static javafx.scene.control.Label givenNo;
    @FXML public javafx.scene.control.Label takenNo;*/
    @FXML private AnchorPane rootPane;

    @FXML
    private Button acceptOffer;
    @FXML
    private Button refuseOffer;
    @FXML
    private Label givenNo;
    @FXML
    private Label takenNo;
    @FXML
    private Label sender;
    @FXML
    private Label givenResource;
    @FXML
    private Label takenResource;


    /*public void backToMenu(ActionEvent event) throws IOException
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        rootPane.getChildren().setAll(pane);
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import View.Main;

public class ControllerMainMenu implements Initializable {
    public void howToPlay(ActionEvent event) throws IOException
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    public void settings(ActionEvent event) throws IOException
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenuSettings.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    public void PlayGame(ActionEvent event) throws IOException
    {
        Main.server = false;
        Main.multiPlayer = false;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SetPlayers.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void CreateGame(ActionEvent event) throws IOException
    {
        Main.server = true;
        Main.multiPlayer = true;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateGame.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void JoinGame(ActionEvent event) throws IOException
    {
        Main.server = false;
        Main.multiPlayer = true;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("JoinGame.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void exit(ActionEvent event)
    {
        System.exit(0);
    }

    private static Image[] images = new Image[75];
    @FXML
    private ImageView imgView;
    @FXML
    private AnchorPane rootPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init();
    }
    public void init() {

        for (int i = 0; i < images.length; i++) {
            images[i] = (new Image("images/Mainscene/Screen Recording 2019-12-18 at 23.45.35_000" + i + ".jpg"));
        }
        imgView.setImage(images[0]);

        Timeline timeLine = new Timeline();
        Collection<KeyFrame> frames = timeLine.getKeyFrames();
        javafx.util.Duration frameGap = javafx.util.Duration.millis(50);
        javafx.util.Duration frameTime = javafx.util.Duration.ZERO ;
        for (Image img : images) {
            frameTime = frameTime.add(frameGap);
            frames.add(new KeyFrame(frameTime, e -> imgView.setImage(img)));
        }
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }
}

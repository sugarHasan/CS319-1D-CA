package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class ControllerSettings implements Initializable {

    @FXML
    private CheckBox music;

    static private MediaPlayer player ;
    static boolean isSongPlaying = false;
    private String musicFile = "Pusu.mp3";

    public void backToMenu(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        music.setSelected(isSongPlaying);
        if(!isSongPlaying)
        {
            final Media sound = new Media(new File(musicFile).toURI().toString());
            this.player = new MediaPlayer(sound);
        }
    }
    public void PlayMusic()
    {

        if(!isSongPlaying)
        {
            if (player != null) {
                player.play();
                isSongPlaying = true;
            }
        }
        else
        {
            if (player != null) {
                player.stop();
                isSongPlaying = false;
            }
        }
    }
}

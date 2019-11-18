package sample;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private ComboBox playerBox, givenBox, wantedBox;
    @FXML private RadioButton playerRadio, bankRadio;
    @FXML private Button offerButton,
                        //Roads (Edges)
                        E0, E1, E2, E3, E4, E5, E6, E7, E8, E9,  E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20,
                        E21, E22, E23, E24, E25, E26, E27, E28, E29, E30, E31, E32, E33, E34, E35, E36, E37, E38, E39,
                        E40, E41, E42, E43, E44, E45, E46, E47, E48, E49, E50, E51, E52, E53, E54, E55, E56, E57, E58,
                        E59, E60, E61, E62, E63, E64, E65, E66, E67, E68, E69, E70, E71,
                        //Cities (Corners)
                        C0, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20,
                        C21, C22, C23, C24, C25, C26, C27, C28, C29, C30, C31, C32, C33, C34, C35, C36, C37, C38, C39,
                        C40, C41, C42, C43, C44, C45, C46, C47, C48, C49, C50, C51, C52, C53;

    @FXML private Label grainNo, brickNo, oreNo, lumberNo, woolNo;
    @FXML private ListView<String> playerList;

    //private Player playerOne, playerTwo, playerThree, playerFour;

    public void playerBoxPressed(){
        System.out.println( "player box was used");
    }

    public void givenBoxPressed(){
        System.out.println( "given box was used");
    }

    public void wantedBoxPressed(){
        System.out.println( "wanted box was used");
    }

    public void offerButtonPressed(){
        System.out.println( "offer button was used");
    }

    public void playerRadioPressed(){
        System.out.println( "player radio was used");
        playerBox.setDisable( false);
    }

    public void bankRadioPressed(){
        System.out.println( "bank radio was used");
        playerBox.setDisable( true);
    }

    public void edgePressed(){
        //to be implemented
    }

    public void cornerPressed(){
        //to be implemented
    }

    public void initialize(URL url, ResourceBundle rb) {
        playerBox.setDisable( true);
        playerBox.getItems().addAll( "Player 1", "Player 2", "Player 3", "Player 4");
        givenBox.getItems().addAll( "Wool", "Ore", "Grain", "Brick", "Lumber");
        wantedBox.getItems().addAll( "Wool", "Ore", "Grain", "Brick", "Lumber");

        ObservableList<String> data = FXCollections.observableArrayList(
                "Player1", "Player2", "Player3", "Player4"
        );

    }
}

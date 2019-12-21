package Model;
import Model.Building;
import Model.ResourceCard;
import View.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URISyntaxException;

public class Land {

    private final int NO_OF_CORNERS = 6;
    private int number;
    private int[] cornerLocations;
    private ResourceCard type;

    public Land( int number)
    {
        this.number = number;
        cornerLocations = new int[NO_OF_CORNERS];
    }

    public boolean setCornerLocations( int[] locs)
    {
        if ( locs.length != 6)
            return false;
        cornerLocations = locs;
        return true;
    }

    public int[] getCornerLocations()
    {
        return cornerLocations;
    }

    public int getNumber()
    {
        return number;
    }

    public String getResource()
    {
        if ( type != null)
            return type.getCardType();
        return "Desert";
    }

    public void setResource( ResourceCard resource)
    {
        type = resource;
    }

    public void setHex( int location) throws URISyntaxException {
        int x = 0;
        int y = 0;
        if (0 <= location && location <= 2) {
            x = 124 + (106 * location);
            y = 22;
        } else if (3 <= location && location <= 6) {
            x = 71 + (106 * (location - 3));
            y = 99;
        } else if (7 <= location && location <= 11) {
            x = 18 + (106 * (location - 7));
            y = 176;
        } else if (12 <= location && location <= 15) {
            x = 71 + (106 * (location - 12));
            y = 253;
        } else if (16 <= location && location <= 18) {
            x = 124 + (106 * (location - 16));
            y = 330;
        }

        //ImageView hex = new ImageView(new Image("file:/images/Hex/Hex" + landType + ".png"));

        Image img = new Image(getClass().getResource("/images/Hex/WithoutNumber/Hex" + getResource() + ".png").toURI().toString());
        ImageView hex = new ImageView(img);

        //hex.setFitHeight(120);
        //hex.setFitWidth(120);

        AnchorPane hexTiles = Main.hexTiles;

        hexTiles.getChildren().add(hex);

        ((ImageView) hexTiles.getChildren().get(hexTiles.getChildren().size() - 1)).setFitHeight(120);
        ((ImageView) hexTiles.getChildren().get(hexTiles.getChildren().size() - 1)).setFitWidth(120);

        hexTiles.getChildren().get(hexTiles.getChildren().size() - 1).setLayoutX(x);
        hexTiles.getChildren().get(hexTiles.getChildren().size() - 1).setLayoutY(y);


        if (!(getResource().equals("Desert"))) {
            Label number = new Label("" + getNumber());
            number.setFont((new Font("Carter One", 30)));

            hexTiles.getChildren().add(number);

            hexTiles.getChildren().get(hexTiles.getChildren().size() - 1).setLayoutX(x + 51);
            hexTiles.getChildren().get(hexTiles.getChildren().size() - 1).setLayoutY(y + 28);
            //hex.setLayoutX(x + 51);
            //hex.setLayoutY(y + 28);
        }
    }
}

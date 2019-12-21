package Model;

import View.Main;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class Robber {
    private int location;
    private ImageView robberImage;
    private AnchorPane anchorPane;

    public Robber( int loc) throws URISyntaxException {

        //Image img = new Image(getClass().getResource("/images/Robber.png").toURI().toString());
        //robberImage = new ImageView(img);

        anchorPane = Main.robberAnchorPane;
        robberImage = (ImageView) anchorPane.getChildren().get(0);//.add(robberImage);

        //robberImage = ((ImageView)image.getChildren().get(image.getChildren().size() - 1));
        location = loc;
        changeImagePos(location);
    }

    public boolean changeLocation( int newLoc)
    {
        if ( newLoc == location )
            return false;
        location = newLoc;
        changeImagePos(location);
        return true;
    }

    public int getLocation()
    {
        return location;
    }

    public void changeImagePos(int location)
    {
        int x = 0;
        int y = 0;
        if (0 <= location && location <= 2) {
            x = 566 + (106 * location);
            y = 135;
        } else if (3 <= location && location <= 6) {
            x = 513 + (106 * (location - 3));
            y = 210;
        } else if (7 <= location && location <= 11) {
            x = 460 + (106 * (location - 7));
            y = 285;
        } else if (12 <= location && location <= 15) {
            x = 513 + (106 * (location - 12));
            y = 360;
        } else if (16 <= location && location <= 18) {
            x = 566 + (106 * (location - 16));
            y = 435;
        }

        robberImage.setLayoutX(x);
        robberImage.setLayoutY(y);
    }
}

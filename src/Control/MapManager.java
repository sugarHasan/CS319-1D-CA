package Control;
import Model.Map;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class MapManager {
    private Map map;

    public MapManager(AnchorPane robberImage) throws URISyntaxException {
        map = new Map(robberImage);
    }

    public void updateMap()
    {

    }
}

package View;

import Control.ClientGameManager;
import Control.GameManager;
import Control.ServerGameManager;
import Model.Offer;
import Model.Player;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
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


public class Main extends Application implements Initializable {

    private static String[] playerNames;
    private static GameManager gameManager;
    private static ServerGameManager serverGameManager;
    private static ClientGameManager clientGameManager;
    private boolean offer = true;
    private boolean knightCardPlayed = false;
    private String givenResource = "";
    private int givenResourceNumber = 0;
    private String wantedResource = "";
    private int wantedResourceNumber = 0;

    public static boolean multiPlayer;
    public static boolean myTurn;
    public static boolean server;
    public static String myName;

    Parent tableViewParent;
    @FXML private ComboBox playerBox;
    @FXML private RadioButton playerRadio, bankRadio;
    @FXML private  javafx.scene.control.Button offerButton;
    @FXML private  javafx.scene.control.Button givenResourceGrain,givenResourceBrick,givenResourceLumber,givenResourceOre,givenResourceWool;
    @FXML private  javafx.scene.control.Button wantedResourceGrain,wantedResourceBrick,wantedResourceLumber,wantedResourceOre,wantedResourceWool;
    @FXML private  javafx.scene.control.Label grainNo, brickNo, oreNo, lumberNo, woolNo;
    @FXML private  javafx.scene.control.Label KnightNo, VictoryNo, RoadNo, PlentyNo, MonoNo;
    @FXML private ListView<String> playerList;
    @FXML private static javafx.scene.control.Label p1;
    @FXML private static javafx.scene.control.Label p2;
    @FXML private static javafx.scene.control.Label p3;
    @FXML private static javafx.scene.control.Label p4;
    @FXML private static javafx.scene.control.Label p1Score;
    @FXML private static javafx.scene.control.Label p2Score;
    @FXML private static javafx.scene.control.Label p3Score;
    @FXML private static javafx.scene.control.Label p4Score ;
    @FXML private javafx.scene.control.Label playerHighestArmy;
    @FXML private javafx.scene.control.Label playerLongestRoad;
    @FXML private javafx.scene.control.Label playerName;
    @FXML private javafx.scene.control.Label RollNo;
    @FXML private ComboBox playCardNo;
    @FXML private java.lang.String Knight ,RoadBuilding ,YearOfPlenty ,Monopoly ;
    @FXML private java.lang.String Player1Trade ,Player2Trade ,Player3Trade ,Player4Trade ;
    @FXML private ComboBox playCardType;
    @FXML private java.lang.String playCardGrain, playCardBrick, playCardOre, playCardLumber, playCardWool;
    @FXML private javafx.scene.control.Label givenResourcesOfferID;
    @FXML private javafx.scene.control.Label wantedResourcesOfferID;
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
    private ImageView offeredResource;
    @FXML
    private ImageView takenResource;

    @FXML static public AnchorPane hexTiles;
    @FXML static public AnchorPane mapBuildings;
    @FXML static public AnchorPane mapRoads;
    @FXML static public AnchorPane robberAnchorPane;
    @FXML static public Button startCreateGame;
    @FXML static public Button startJoinGame;

    public void backToMenu(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void initializeGame(ActionEvent event) throws IOException, URISyntaxException{
        System.out.println( "INIT GAME");
        tableViewParent = FXMLLoader.load(getClass().getResource("MultiPlayerGame.fxml"));
        hexTiles = (AnchorPane) tableViewParent.lookup("#hexTiles");
        mapBuildings = (AnchorPane) tableViewParent.lookup("#mapBuildings");
        robberAnchorPane = (AnchorPane) tableViewParent.lookup("#robberAnchorPane");
        mapRoads = (AnchorPane) tableViewParent.lookup("#mapRoads");
        //tableViewParent = FXMLLoader.load(getClass().getResource("CreateGame.fxml"));
        //startCreateGame = ((javafx.scene.control.Button) tableViewParent.lookup("#startCreateGame"));
        //startCreateGame.setDisable(true);
        myName = player1.getText();
        serverGameManager = new ServerGameManager(2222, player1.getText(),robberAnchorPane,hexTiles,mapBuildings,mapRoads);
    }

    public void joinGame(ActionEvent event) throws IOException, URISyntaxException {
        System.out.println( "JOIN GAME");
        tableViewParent = FXMLLoader.load(getClass().getResource("MultiPlayerGame.fxml"));
        hexTiles = (AnchorPane) tableViewParent.lookup("#hexTiles");
        mapBuildings = (AnchorPane) tableViewParent.lookup("#mapBuildings");
        robberAnchorPane = (AnchorPane) tableViewParent.lookup("#robberAnchorPane");
        mapRoads = (AnchorPane) tableViewParent.lookup("#mapRoads");
        myName = player1.getText();
        clientGameManager = new ClientGameManager(2222, player1.getText(), robberAnchorPane,hexTiles,mapBuildings,mapRoads);

    }

    public void PlayGame(ActionEvent event) throws IOException, URISyntaxException {

        Parent tableViewParent;
        if(multiPlayer)
            tableViewParent = FXMLLoader.load(getClass().getResource("MultiPlayerGame.fxml"));
        else
            tableViewParent = FXMLLoader.load(getClass().getResource("Game.fxml"));

        grainNo = (javafx.scene.control.Label) tableViewParent.lookup("#grainNo");
        brickNo = (javafx.scene.control.Label) tableViewParent.lookup("#brickNo");
        oreNo = (javafx.scene.control.Label) tableViewParent.lookup("#oreNo");
        lumberNo = (javafx.scene.control.Label) tableViewParent.lookup("#lumberNo");
        woolNo = (javafx.scene.control.Label) tableViewParent.lookup("#woolNo");
        p1 = (javafx.scene.control.Label) tableViewParent.lookup("#p1");
        p2 = (javafx.scene.control.Label) tableViewParent.lookup("#p2");
        p3 = (javafx.scene.control.Label) tableViewParent.lookup("#p3");
        p4 = (javafx.scene.control.Label) tableViewParent.lookup("#p4");
        p1Score = (javafx.scene.control.Label) tableViewParent.lookup("#p1Score");
        p2Score = (javafx.scene.control.Label) tableViewParent.lookup("#p2Score");
        p3Score = (javafx.scene.control.Label) tableViewParent.lookup("#p3Score");
        p4Score = (javafx.scene.control.Label) tableViewParent.lookup("#p4Score");
        playerHighestArmy = ((javafx.scene.control.Label) tableViewParent.lookup("#playerHighestArmy"));
        playerLongestRoad = ((javafx.scene.control.Label) tableViewParent.lookup("#playerLongestRoad"));
        RollNo = ((javafx.scene.control.Label) tableViewParent.lookup("#RollNo"));
        playerName = ((javafx.scene.control.Label) tableViewParent.lookup("#playerName"));
        playerBox = ((javafx.scene.control.ComboBox) tableViewParent.lookup("#playerBox"));
        startCreateGame = ((javafx.scene.control.Button) tableViewParent.lookup("#startCreateGame"));

        hexTiles = (AnchorPane) tableViewParent.lookup("#hexTiles");
        mapBuildings = (AnchorPane) tableViewParent.lookup("#mapBuildings");
        robberAnchorPane = (AnchorPane) tableViewParent.lookup("#robberAnchorPane");
        mapRoads = (AnchorPane) tableViewParent.lookup("#mapRoads");
        /*GivenResource = (ImageView) tableViewParent.lookup("#GivenResource");
        TakenResource = (ImageView) tableViewParent.lookup("#TakenResource");*/



        if(!multiPlayer)
        {
            playerNames = new String[4];
            playerNames[0] = player1.getText();
            playerNames[1] = player2.getText();
            playerNames[2] = player3.getText();
            playerNames[3] = player4.getText();
            for(int i = 0; i < 4; i++)
            {
                if(playerNames[i].isEmpty())
                {
                    playerNames[i] = "Player " + (i + 1);
                }
            }
            p1.setText(playerNames[0]);
            p2.setText(playerNames[1]);
            p3.setText(playerNames[2]);
            p4.setText(playerNames[3]);
        }
        else
        {
            playerNames = new String[4];
            playerNames[0] = player1.getText();
            if(playerNames[0].isEmpty())
            {
                playerNames[0] = "Player 1";
            }
            p1.setText(playerNames[0]);
        }

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        if(multiPlayer) {
            if (server) {
                //serverGameManager = new ServerGameManager(2222, playerNames[0],robberAnchorPane,hexTiles,mapBuildings,mapRoads);
                serverGameManager.nextTurn();
                myTurn = true;
                serverGameManager.sendGameData();
                playerNames = new String[4];
                Player[] p = serverGameManager.getPlayerArray();
                for(int i = 0 ; i < p.length ; i++){
                    playerNames[i] = p[i].getName();
                }
                p1.setText(playerNames[0]);
                p2.setText(playerNames[1]);
                p3.setText(playerNames[2]);
                p4.setText(playerNames[3]);

            } else {
                //clientGameManager = new ClientGameManager(2222, playerNames[0],robberAnchorPane,hexTiles,mapBuildings,mapRoads);
                //clientGameManager.sendMessage("XAA");
                myTurn = false;
            }
        }
        else{
            gameManager = new GameManager(playerNames[0] , playerNames[1] , playerNames[2] , playerNames[3], robberAnchorPane);
            gameManager.nextTurn();
        }
        if(multiPlayer) {
            if (server) {
                RollNo.setText("Turn : " + serverGameManager.getTurnNo());
                this.refreshResources();
                this.refreshPlayerScores();
                this.refreshHighestArmy();
                //this.refreshLongestRoad();
                playerName.setText("" + serverGameManager.getPlayerName());
                serverGameManager.visualizeMap();

            } else {
                RollNo.setText("Turn : " + clientGameManager.getTurnNo());
                this.refreshResources();
                this.refreshPlayerScores();
                this.refreshHighestArmy();
                this.refreshLongestRoad();
                playerName.setText("" + clientGameManager.getPlayerName());
                clientGameManager.visualizeMap();
            }
        }
        else{
            RollNo.setText("Turn : " + gameManager.getTurnNo());
            this.refreshResources();
            this.refreshPlayerScores();
            this.refreshHighestArmy();
            this.refreshLongestRoad();
            playerName.setText("" + gameManager.getPlayerName());
            gameManager.visualizeMap();
        }

       /*// playerBox.setButtonCell();
        startCreateGame.setDisable(true);*/
    }

    public void playerBoxPressed(ActionEvent event) throws IOException{
    }

    public void givenBoxPressed(ActionEvent event) throws IOException{
    }

    public void wantedBoxPressed(ActionEvent event) throws IOException{
    }

    public void refresh(){
        refreshResources();
        refreshDevelopmentCards();
        refreshResources();
        refreshHighestArmy();
        refreshPlayerScores();
    }

    public void playerRadioPressed(ActionEvent event) throws IOException{
        offer = true;
        playerBox.setDisable( false);
        givenResourcesOfferID.setDisable(false);
        wantedResourcesOfferID.setDisable(false);
    }

    public void bankRadioPressed(ActionEvent event) throws IOException{
        offer = false;
        playerBox.setDisable( true);
        givenResourcesOfferID.setDisable(true);
        wantedResourcesOfferID.setDisable(true);
    }

    public void edgePressed(ActionEvent event) throws IOException, URISyntaxException {
        System.out.println("EDGE ");
        if(!multiPlayer) {
            String id = ((Node) event.getSource()).getId();
            int location = Integer.parseInt(id.substring(1));
            if (gameManager.addRoad(location)) {
                ((javafx.scene.control.Button) event.getSource()).setDisable(true);
            }
            refreshResources();
            refreshPlayerScores();
            refreshLongestRoad();
        }
        else if(myTurn){
            if(server) {
                System.out.println("MYTs ");
                String id = ((Node) event.getSource()).getId();
                int location = Integer.parseInt(id.substring(1));
                if (serverGameManager.addRoad(location)) {
                    ((javafx.scene.control.Button) event.getSource()).setDisable(true);
                    serverGameManager.sendMessageToAll("AB" + location+"###");
                }
                refreshResources();
                refreshPlayerScores();
                refreshLongestRoad();
            }
            else{
                String id = ((Node) event.getSource()).getId();
                int location = Integer.parseInt(id.substring(1));
                if (clientGameManager.addRoad(location)) {
                    ((javafx.scene.control.Button) event.getSource()).setDisable(true);
                    clientGameManager.sendMessage("XAB" + location+"###");
                }
                refreshResources();
                refreshPlayerScores();
                refreshLongestRoad();
            }
        }
    }

    //for robber and knight card. Location starts with 0.
    public void hexCenterPressed(ActionEvent event) throws IOException, URISyntaxException {
        String id = ((Node)event.getSource()).getId();
        int location = Integer.parseInt(id.substring(1));
        if ( !multiPlayer)
            if( gameManager.getTurnDice() == 7 || knightCardPlayed )
            {
                gameManager.changeRobberLocation( location);
                if ( knightCardPlayed)
                {
                    knightCardPlayed = false;
                    refreshDevelopmentCards();
                }
            }
        else if ( myTurn)
            if ( server)
                if( serverGameManager.getTurnDice() == 7 || knightCardPlayed )
                {
                    serverGameManager.changeRobberLocation( location);
                    if ( knightCardPlayed)
                    {
                        serverGameManager.playKnightCard( location);
                        knightCardPlayed = false;
                    }
                }
            else
                if( clientGameManager.getTurnDice() == 7 || knightCardPlayed )
                {
                    clientGameManager.changeRobberLocation( location);
                    if ( knightCardPlayed)
                    {
                        clientGameManager.playKnightCard( location);
                        knightCardPlayed = false;
                    }
                }

    }

    public void givenResourcesButtons(ActionEvent event) throws IOException{

        String oldResource = givenResource;

        String id = ((Node) event.getSource()).getId();
        if(!multiPlayer) {
            ((javafx.scene.control.Button) event.getSource()).setStyle(" -fx-background-color: " + gameManager.returnPlayerColor());
        }
        else if(myTurn){
            if(server){
                ((javafx.scene.control.Button) event.getSource()).setStyle(" -fx-background-color: " + serverGameManager.returnPlayerColor());
            }
            else{
                ((javafx.scene.control.Button) event.getSource()).setStyle(" -fx-background-color: " + clientGameManager.returnPlayerColor());
            }
        }
        if(id.equals("givenResourceGrain"))
        {
            givenResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            givenResource = "Grain";
        }
        else if(id.equals("givenResourceBrick"))
        {
            givenResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            givenResource = "Brick";
        }
        else if(id.equals("givenResourceLumber"))
        {
            givenResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            givenResource = "Lumber";
        }
        else if(id.equals("givenResourceOre"))
        {
            givenResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            givenResource = "Ore";
        }
        else if(id.equals("givenResourceWool"))
        {
            givenResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceBrick.setStyle(" -fx-background-color: #FFFFFF");

            givenResource = "Wool";
        }
        else
        {
            givenResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            givenResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            givenResource = "";
        }
        if ( givenResource.equals( oldResource))
            givenResourceNumber++;
        else
            givenResourceNumber = 1;

        givenResourcesOfferID.setText("x " + givenResourceNumber);
    }

    public void wantedResourcesButtons(ActionEvent event) throws IOException{
        String oldResource = wantedResource;

        String id = ((Node)event.getSource()).getId();
        ((javafx.scene.control.Button) event.getSource()).setStyle(" -fx-background-color: " + gameManager.returnPlayerColor());

        if(id.equals("wantedResourceGrain"))
        {
            wantedResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            wantedResource = "Grain";
        }
        else if(id.equals("wantedResourceBrick"))
        {
            wantedResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            wantedResource = "Brick";
        }
        else if(id.equals("wantedResourceLumber"))
        {
            wantedResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            wantedResource = "Lumber";
        }
        else if(id.equals("wantedResourceOre"))
        {
            wantedResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            wantedResource = "Ore";
        }
        else if(id.equals("wantedResourceWool"))
        {
            wantedResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceBrick.setStyle(" -fx-background-color: #FFFFFF");

            wantedResource = "Wool";
        }
        else
        {
            wantedResourceGrain.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceLumber.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceOre.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceBrick.setStyle(" -fx-background-color: #FFFFFF");
            wantedResourceWool.setStyle(" -fx-background-color: #FFFFFF");

            wantedResource = "";
        }
        if ( wantedResource.equals( oldResource))
            wantedResourceNumber++;
        else
            wantedResourceNumber = 1;

        wantedResourcesOfferID.setText("x " + wantedResourceNumber);
    }

    public void offerButtonPressed(ActionEvent event) throws IOException{
            if(!offer){
                if(!givenResource.equals("") && !wantedResource.equals(""))
                {
                    if (!givenResource.equals(wantedResource))
                    {
                        if( !multiPlayer)
                        {
                            gameManager.tradeResource( givenResource, wantedResource);
                        }
                        else if( myTurn)
                        {
                            if( server)
                            {
                                serverGameManager.tradeResource( givenResource, wantedResource);
                            }
                            else
                            {
                                clientGameManager.tradeResource( givenResource, wantedResource);
                            }
                        }
                        refreshResources();
                    }
                }
            }
            else
            {
                if( playerBox.getValue() != null) {
                    int receiverNo = -1;
                    if (playerBox.getValue().equals(Player1Trade))
                        receiverNo = 0;
                    else if (playerBox.getValue().equals(Player2Trade))
                        receiverNo = 1;
                    else if (playerBox.getValue().equals(Player3Trade))
                        receiverNo = 2;
                    else if (playerBox.getValue().equals(Player4Trade))
                        receiverNo = 3;

                    if (!givenResource.equals("") && !wantedResource.equals("") && receiverNo != -1) {
                        if (!givenResource.equals(wantedResource)) {
                            if (!multiPlayer) {
                                gameManager.makeOffer(receiverNo, givenResource, wantedResource, givenResourceNumber, wantedResourceNumber);
                            } else if (myTurn) {
                                if (server) {
                                    serverGameManager.makeOffer(receiverNo, givenResource, wantedResource, givenResourceNumber, wantedResourceNumber);
                                } else {
                                    clientGameManager.makeOffer(receiverNo, givenResource, wantedResource, givenResourceNumber, wantedResourceNumber);
                                }
                            }
                        }
                    }
                }
            }
        }

    private void refreshResources(){
        if(!multiPlayer) {
            int[] resources = gameManager.getResources();
            grainNo.setText("" +  resources[0]);
            brickNo.setText("" +  resources[1]);
            oreNo.setText("" +  resources[2]);
            lumberNo.setText("" +  resources[3]);
            woolNo.setText("" +  resources[4]);
        }
        else {
            if(server) {
                int[] resources = serverGameManager.getResources();
                grainNo.setText("" +  resources[0]);
                brickNo.setText("" +  resources[1]);
                oreNo.setText("" +  resources[2]);
                lumberNo.setText("" +  resources[3]);
                woolNo.setText("" +  resources[4]);
            }
            else{
                int[] resources = clientGameManager.getResources();
                grainNo.setText("" +  resources[0]);
                brickNo.setText("" +  resources[1]);
                oreNo.setText("" +  resources[2]);
                lumberNo.setText("" +  resources[3]);
                woolNo.setText("" +  resources[4]);
            }
        }

    }

    public void refreshDevelopmentCards() {
        if (!multiPlayer) {
            int[] developments = gameManager.getDevelopmentCards();
            KnightNo.setText("" + developments[0]);
            VictoryNo.setText("" + developments[1]);
            RoadNo.setText("" + developments[2]);
            PlentyNo.setText("" + developments[3]);
            MonoNo.setText("" + developments[4]);
        }
        else {
            if (server) {
                int[] developments = serverGameManager.getDevelopmentCards();
                KnightNo.setText("" + developments[0]);
                VictoryNo.setText("" + developments[1]);
                RoadNo.setText("" + developments[2]);
                PlentyNo.setText("" + developments[3]);
                MonoNo.setText("" + developments[4]);
            } else {
                int[] developments = clientGameManager.getDevelopmentCards();
                KnightNo.setText("" + developments[0]);
                VictoryNo.setText("" + developments[1]);
                RoadNo.setText("" + developments[2]);
                PlentyNo.setText("" + developments[3]);
                MonoNo.setText("" + developments[4]);
            }

        }
    }

    public void buyDevelopmentCard( ActionEvent event) throws IOException{
        if(!multiPlayer) {
            gameManager.buyDevelopmentCard();
            refreshResources();
            refreshPlayerScores();
            refreshHighestArmy();
            refreshDevelopmentCards();
        }
        else if(myTurn){
            if(server){
                serverGameManager.buyDevelopmentCard();
                refreshResources();
                refreshPlayerScores();
                refreshHighestArmy();
                refreshDevelopmentCards();
            }
            else{
                clientGameManager.buyDevelopmentCard();
                refreshResources();
                refreshPlayerScores();
                refreshHighestArmy();
                refreshDevelopmentCards();
            }
        }
    }

    public void cornerPressed(ActionEvent event) throws IOException, URISyntaxException {
        if(!multiPlayer) {
            String id = ((Node) event.getSource()).getId();
            int location = Integer.parseInt(id.substring(1));
            if ( gameManager.addSettlement(location)) {
                //((javafx.scene.control.Button) event.getSource()).setStyle(((javafx.scene.control.Button) event.getSource()).getStyle() + " -fx-background-color: " + gameManager.returnPlayerColor());
                //((javafx.scene.control.Button) event.getSource()).setDisable(true);
                //((javafx.scene.control.Button) event.getSource()).setOpacity(0.80);
            } else if ( gameManager.addCity(location)) {
                //((javafx.scene.control.Button) event.getSource()).setStyle(" -fx-background-color: " + gameManager.returnPlayerCityColor());
                //((javafx.scene.control.Button) event.getSource()).setOpacity(1.0);
                //((javafx.scene.control.Button) event.getSource()).setDisable(true);
            } else if ( gameManager.addCapital( location))
            {

            }
            refreshResources();
            refreshPlayerScores();
        }
        else if(myTurn){
            if(server){
                String id = ((Node) event.getSource()).getId();
                int location = Integer.parseInt(id.substring(1));
                if (serverGameManager.addSettlement(location)) {
                    //((javafx.scene.control.Button) event.getSource()).setStyle(((javafx.scene.control.Button) event.getSource()).getStyle() + " -fx-background-color: " + gameManager.returnPlayerColor());
                    //((javafx.scene.control.Button) event.getSource()).setDisable(true);
                    //((javafx.scene.control.Button) event.getSource()).setOpacity(0.80);
                } else if (serverGameManager.addCity(location)) {
                    //((javafx.scene.control.Button) event.getSource()).setStyle(" -fx-background-color: " + gameManager.returnPlayerCityColor());
                    //((javafx.scene.control.Button) event.getSource()).setOpacity(1.0);
                    //((javafx.scene.control.Button) event.getSource()).setDisable(true);
                } else if ( serverGameManager.addCapital( location))
                {

                }
                refreshResources();
                refreshPlayerScores();
            }
            else{
                String id = ((Node) event.getSource()).getId();
                int location = Integer.parseInt(id.substring(1));
                if (clientGameManager.addSettlement(location)) {
                    //((javafx.scene.control.Button) event.getSource()).setStyle(((javafx.scene.control.Button) event.getSource()).getStyle() + " -fx-background-color: " + gameManager.returnPlayerColor());
                    //((javafx.scene.control.Button) event.getSource()).setDisable(true);
                    //((javafx.scene.control.Button) event.getSource()).setOpacity(0.80);
                } else if (clientGameManager.addCity(location)) {
                    //((javafx.scene.control.Button) event.getSource()).setStyle(" -fx-background-color: " + gameManager.returnPlayerCityColor());
                    //((javafx.scene.control.Button) event.getSource()).setOpacity(1.0);
                    //((javafx.scene.control.Button) event.getSource()).setDisable(true);
                } else if ( clientGameManager.addCapital( location))
                {

                }
                refreshResources();
                refreshPlayerScores();
            }
        }
    }

    public void endTurn(ActionEvent event) throws IOException, URISyntaxException {
        if (!multiPlayer) {
            gameManager.nextTurn();
            refreshResources();
            refreshPlayerScores();
            refreshHighestArmy();
            refreshLongestRoad();
            refreshDevelopmentCards();
            playerName.setText("" + gameManager.getPlayerName());
            if (gameManager.getTurnDice() != 0) {
                RollNo.setText("" + gameManager.getTurnDice());
            } else {
                RollNo.setText("Turn : " + gameManager.getTurnNo());
            }
        }
        else if(myTurn){
            if(server){
                String temp = serverGameManager.nextTurn();
                if(temp.equals(myName)) myTurn = true;
                else    myTurn = false;
                refreshResources();
                refreshPlayerScores();
                refreshHighestArmy();
                refreshLongestRoad();
                refreshDevelopmentCards();
                playerName.setText("" + serverGameManager.getPlayerName());
                if (serverGameManager.getTurnDice() != 0) {
                    RollNo.setText("" + serverGameManager.getTurnDice());
                } else {
                    RollNo.setText("Turn : " + serverGameManager.getTurnNo());
                }
            }
            else{
//                clientGameManager.nextTurn();
                clientGameManager.sendMessage("XAA");
                refreshResources();
                refreshPlayerScores();
                refreshHighestArmy();
                refreshLongestRoad();
                refreshDevelopmentCards();
                playerName.setText("" + clientGameManager.getPlayerName());
                if (clientGameManager.getTurnDice() != 0) {
                    RollNo.setText("" + clientGameManager.getTurnDice());
                } else {
                    RollNo.setText("Turn : " + clientGameManager.getTurnNo());
                }
            }
        }
        knightCardPlayed = false;
        offerPopUp();
    }

    public static void refreshPlayerScores()
    {
        if(!multiPlayer) {
            int[] playerScores = gameManager.getScoreBoard();
            for (int i = 0; i < 4; i++) {
                if ( playerScores[i] >= 15 && gameManager.currentPlayerHasCapital() ) {
                    gameOverPopUp(gameOver());
                }
            }

            p1Score.setText("" + playerScores[0]);
            p2Score.setText("" + playerScores[1]);
            p3Score.setText("" + playerScores[2]);
            p4Score.setText("" + playerScores[3]);
        }
        else
        {
            if(server){
                int[] playerScores = serverGameManager.getScoreBoard();
                for (int i = 0; i < 4; i++) {
                    if ( playerScores[i] >= 15 && gameManager.currentPlayerHasCapital() ) {
                        gameOverPopUp(gameOver());
                    }
                }
                for(int i = 0 ; i < 4 ; i++)    playerNames[i] = serverGameManager.getPlayerName(i);
//                System.out.println("MESAJ ICIN");
//                System.out.println(playerNames[1]);
//                System.out.println("MESAJ SONU");
                p1Score.setText("" + playerScores[0]);
                p2Score.setText("" + playerScores[1]);
                p3Score.setText("" + playerScores[2]);
                p4Score.setText("" + playerScores[3]);
                p1.setText(playerNames[0]);
                p2.setText(playerNames[1]);
                p3.setText(playerNames[2]);
                p4.setText(playerNames[3]);
            }
            else{
                int[] playerScores = clientGameManager.getScoreBoard();
                for (int i = 0; i < 4; i++) {
                    if ( playerScores[i] >= 15 && gameManager.currentPlayerHasCapital()) {
                        gameOverPopUp(gameOver());
                    }
                }

                p1Score.setText("" + playerScores[0]);
                p2Score.setText("" + playerScores[1]);
                p3Score.setText("" + playerScores[2]);
                p4Score.setText("" + playerScores[3]);
            }
        }
    }

    public static void gameOverPopUp(String gameWinner) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(gameWinner +" WINS!!"));
        Scene dialogScene = new Scene(dialogVbox, 100, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void offerPopUp() throws IOException, URISyntaxException {
        ArrayList<Offer> offerList = null;
        String Given = "", Taken = "";
        if ( !multiPlayer)
            offerList = gameManager.listOffer();
        else if ( myTurn)
        {
            if ( server)
            {
                offerList = serverGameManager.listOffer();
            }
            else
            {
                offerList = clientGameManager.listOffer();
            }
        }

        if ( offerList != null )
        {
            System.out.println( "INSIDE");
            for(int i = 0; i < offerList.size(); i++) {
                if (offerList.get(i).getDemandedItem().equals("Wool")) {
                    Taken = "Sheep";
                } else if( offerList.get(i).getDemandedItem().equals("Lumber")) {
                    Taken = "Wood";
                }
                else if (offerList.get(i).getDemandedItem().equals("Ore")) {
                    Taken = "Rock";
                }
                else
                    Taken = offerList.get(i).getDemandedItem();

                if (offerList.get(i).getOfferedItem().equals("Wool")) {
                    Given = "Sheep";
                } else if (offerList.get(i).getOfferedItem().equals("Lumber")) {
                    Given = "Wood";
                }
                else if (offerList.get(i).getOfferedItem().equals("Ore")) {
                    Given = "Rock";
                }
                else
                    Given = offerList.get(i).getOfferedItem();

                System.out.println( "INSIDE OFFER");
                /*System.out.println(offerList.get(i).getDemandedItem() + offerList.get(i).getDemandNum());
                System.out.println(offerList.get(i).getOfferedItem()  + offerList.get(i).getOfferNum());*/

                Parent root = FXMLLoader.load(getClass().getResource("OfferPopUp.fxml"));

                offeredResource = (javafx.scene.image.ImageView) root.lookup("#offeredResource");
                takenResource = (javafx.scene.image.ImageView) root.lookup("#takenResource");

                givenNo = (javafx.scene.control.Label) root.lookup("#givenNo");
                takenNo = (javafx.scene.control.Label) root.lookup("#takenNo");
                sender = (javafx.scene.control.Label) root.lookup("#sender");

                acceptOffer = ((javafx.scene.control.Button) root.lookup("#acceptOffer"));
                refuseOffer = ((javafx.scene.control.Button) root.lookup("#refuseOffer"));

                givenNo.setText( offerList.get(i).getOfferNum() + " X ");
                takenNo.setText( "" + offerList.get(i).getDemandNum() + " X ");
                sender.setText( offerList.get(i).getSender().getName());

                javafx.scene.image.Image img = new Image(getClass().getResource("/images/Resources/" + Given + ".png").toURI().toString());
                //offeredResource.setImage(img);

                ImageView hex = new ImageView(img);

                //hex.setFitHeight(120);
                //hex.setFitWidth(120);

                AnchorPane rootPane = ((AnchorPane) root.lookup("#rootPane"));

                rootPane.getChildren().add(hex);

                ((ImageView) rootPane.getChildren().get(rootPane.getChildren().size() - 1)).setFitHeight(32);
                ((ImageView) rootPane.getChildren().get(rootPane.getChildren().size() - 1)).setFitWidth(38);

                rootPane.getChildren().get(rootPane.getChildren().size() - 1).setLayoutX(85);
                rootPane.getChildren().get(rootPane.getChildren().size() - 1).setLayoutY(93);

                javafx.scene.image.Image img2 = new Image(getClass().getResource("/images/Resources/" + Taken + ".png").toURI().toString());
                ImageView hex2 = new ImageView(img2);

                rootPane.getChildren().add(hex2);

                ((ImageView) rootPane.getChildren().get(rootPane.getChildren().size() - 1)).setFitHeight(32);
                ((ImageView) rootPane.getChildren().get(rootPane.getChildren().size() - 1)).setFitWidth(38);

                rootPane.getChildren().get(rootPane.getChildren().size() - 1).setLayoutX(282);
                rootPane.getChildren().get(rootPane.getChildren().size() - 1).setLayoutY(93);

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setTitle("Offer " + (i + 1));
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                //stage.close();
            }
        }
    }

    public static String gameOver(){
        return gameManager.gameOver();
    }

    public void refreshHighestArmy()
    {
        if(!multiPlayer) {
            int army = gameManager.largestArmy();

            if (army == 0) {
                playerHighestArmy.setText(p1.getText());
            } else if (army == 1) {
                playerHighestArmy.setText(p2.getText());
            } else if (army == 2) {
                playerHighestArmy.setText(p3.getText());
            } else if (army == 3) {
                playerHighestArmy.setText(p4.getText());
            }
        }
        else{
            if(server){
                int army = serverGameManager.largestArmy();

                if (army == 0) {
                    playerHighestArmy.setText(p1.getText());
                } else if (army == 1) {
                    playerHighestArmy.setText(p2.getText());
                } else if (army == 2) {
                    playerHighestArmy.setText(p3.getText());
                } else if (army == 3) {
                    playerHighestArmy.setText(p4.getText());
                }
            }
            else{
                int army = clientGameManager.largestArmy();

                if (army == 0) {
                    playerHighestArmy.setText(p1.getText());
                } else if (army == 1) {
                    playerHighestArmy.setText(p2.getText());
                } else if (army == 2) {
                    playerHighestArmy.setText(p3.getText());
                } else if (army == 3) {
                    playerHighestArmy.setText(p4.getText());
                }
            }
        }
    }

    public void refreshLongestRoad()
    {
        if(!multiPlayer) {
            int road = gameManager.longestRoad();
            if (road == 0) {
                playerLongestRoad.setText(p1.getText());
            } else if (road == 1) {
                playerLongestRoad.setText(p2.getText());
            } else if (road == 2) {
                playerLongestRoad.setText(p3.getText());
            } else if (road == 3) {
                playerLongestRoad.setText(p4.getText());
            }
        }
        else {
            if(server){
                int road = serverGameManager.longestRoad();
                if (road == 0) {
                    playerLongestRoad.setText(p1.getText());
                } else if (road == 1) {
                    playerLongestRoad.setText(p2.getText());
                } else if (road == 2) {
                    playerLongestRoad.setText(p3.getText());
                } else if (road == 3) {
                    playerLongestRoad.setText(p4.getText());
                }
            }
            else{
                int road = clientGameManager.longestRoad();
                if (road == 0) {
                    playerLongestRoad.setText(p1.getText());
                } else if (road == 1) {
                    playerLongestRoad.setText(p2.getText());
                } else if (road == 2) {
                    playerLongestRoad.setText(p3.getText());
                } else if (road == 3) {
                    playerLongestRoad.setText(p4.getText());
                }
            }
        }
    }

    public void playerCardPressed(ActionEvent event) throws IOException{
        if(playCardNo.getValue().equals(Monopoly))
        {
            playCardType.setDisable( false);
        }
        else if(playCardNo.getValue().equals(Knight))
        {
            playCardType.setDisable( true);
        }
        else if(playCardNo.getValue().equals(RoadBuilding))
        {
            playCardType.setDisable( true);
        }
        else if(playCardNo.getValue().equals(YearOfPlenty))
        {
            playCardType.setDisable( false);
        }
    }

    public String playerCardType()
    {
        if(playCardType.getValue().equals(playCardGrain))
        {
            return "Grain";
        }
        else if(playCardType.getValue().equals(playCardBrick))
        {
            return "Brick";
        }
        else if(playCardType.getValue().equals(playCardOre))
        {
            return "Ore";
        }
        else if(playCardType.getValue().equals(playCardLumber))
        {
            return "Lumber";
        }
        else if(playCardType.getValue().equals(playCardWool))
        {
            return "Wool";
        }
        return "";
    }

    public void playCard(ActionEvent actionEvent) {
        if(!multiPlayer) {
            if (playCardNo.getValue().equals(Monopoly)) {
                if (!playerCardType().equals("")) {
                    gameManager.playMonopoly(playerCardType());
                }
            } else if (playCardNo.getValue().equals(Knight)) {
                if ( gameManager.knightCardPlayable())
                {
                    knightCardPlayed = true;
                }
            } else if (playCardNo.getValue().equals(RoadBuilding)) {

                gameManager.playRoadBuilding();

            } else if (playCardNo.getValue().equals(YearOfPlenty)) {
                if (!playerCardType().equals("")) {
                    gameManager.playYearOfPlenty(playerCardType());
                }
            }
            refreshResources();
            refreshDevelopmentCards();
        }
        else if(myTurn){
            if(server){
                if (playCardNo.getValue().equals(Monopoly)) {
                    if (!playerCardType().equals("")) {
                        serverGameManager.playMonopoly(playerCardType());
                    }
                } else if (playCardNo.getValue().equals(Knight)) {
                    if ( serverGameManager.knightCardPlayable())
                    {
                        knightCardPlayed = true;
                    }
                } else if (playCardNo.getValue().equals(RoadBuilding)) {

                    serverGameManager.playRoadBuilding();

                } else if (playCardNo.getValue().equals(YearOfPlenty)) {
                    if (!playerCardType().equals("")) {
                        serverGameManager.playYearOfPlenty(playerCardType());
                    }
                }
                refreshResources();
                refreshDevelopmentCards();
            }
            else{
                if (playCardNo.getValue().equals(Monopoly)) {
                    if (!playerCardType().equals("")) {
                        clientGameManager.playMonopoly(playerCardType());
                    }
                } else if (playCardNo.getValue().equals(Knight)) {
                    if ( serverGameManager.knightCardPlayable())
                    {
                        knightCardPlayed = true;
                    }
                } else if (playCardNo.getValue().equals(RoadBuilding)) {

                    clientGameManager.playRoadBuilding();

                } else if (playCardNo.getValue().equals(YearOfPlenty)) {
                    if (!playerCardType().equals("")) {
                        clientGameManager.playYearOfPlenty(playerCardType());
                    }
                }
                refreshResources();
                refreshDevelopmentCards();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        primaryStage.setTitle("Settlers of Catan");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        //Font.createFont("Carter One", Main.class.getResource("/Font.CarterOne/CarterOne.ttf").toURI().toString());

        //Font.createFont(Font.TRUETYPE_FONT, new File("/Font.CarterOne/CarterOne.ttf"));
        launch(args);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    private ObservableValue<? extends String> textValueProperty() {
        return textValueProperty();
    }

    private final StringProperty textValue = new SimpleStringProperty("waiting for input");
}

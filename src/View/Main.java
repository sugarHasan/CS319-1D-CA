package View;

import Control.GameManager;
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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Button;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class Main extends Application implements Initializable {

    private String[] playerNames;
    private static GameManager gameManager;



    @FXML private ComboBox playerBox, givenBox, wantedBox;
    @FXML private RadioButton playerRadio, bankRadio;
    @FXML private  javafx.scene.control.Button offerButton;
    @FXML private  javafx.scene.control.Label grainNo, brickNo, oreNo, lumberNo, woolNo;
    @FXML private  javafx.scene.control.Label KnightNo, VictoryNo, RoadNo, PlentyNo, MonoNo;
    @FXML private ListView<String> playerList;
    @FXML private javafx.scene.control.Label p1, p2, p3, p4;
    @FXML private javafx.scene.control.Label p1Score ,p2Score ,p3Score ,p4Score ;
    @FXML private javafx.scene.control.Label p1Army ,p2Army ,p3Army ,p4Army ;
    @FXML private javafx.scene.control.Label p1Road ,p2Road ,p3Road ,p4Road ;
    @FXML private javafx.scene.control.Label playerName;
    @FXML private javafx.scene.control.Label RollNo;
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

    @FXML private ComboBox playCardNo;
    @FXML private java.lang.String Knight ,RoadBuilding ,YearOfPlenty ,Monopoly ;

    @FXML private java.lang.String Player1Trade ,Player2Trade ,Player3Trade ,Player4Trade;

    @FXML private java.lang.String grainTrade, brickTrade, oreTrade, lumberTrade, woolTrade;
    @FXML private java.lang.String grainWanted, brickWanted, oreWanted, lumberWanted, woolWanted;

    @FXML private ComboBox playCardType;
    @FXML private java.lang.String playCardGrain, playCardBrick, playCardOre, playCardLumber, playCardWool;

    public void backToMenu(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void PlayGame(ActionEvent event) throws IOException
    {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Game.fxml"));
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
        p1Army = ((javafx.scene.control.Label) tableViewParent.lookup("#p1Army"));
        p2Army = ((javafx.scene.control.Label) tableViewParent.lookup("#p2Army"));
        p3Army = ((javafx.scene.control.Label) tableViewParent.lookup("#p3Army"));
        p4Army = ((javafx.scene.control.Label) tableViewParent.lookup("#p4Army"));
        p1Road = ((javafx.scene.control.Label) tableViewParent.lookup("#p1Road"));
        p2Road = ((javafx.scene.control.Label) tableViewParent.lookup("#p2Road"));
        p3Road = ((javafx.scene.control.Label) tableViewParent.lookup("#p3Road"));
        p4Road = ((javafx.scene.control.Label) tableViewParent.lookup("#p4Road"));
        RollNo = ((javafx.scene.control.Label) tableViewParent.lookup("#RollNo"));
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
        playerName = ((javafx.scene.control.Label) tableViewParent.lookup("#playerName"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        gameManager = new GameManager(playerNames[0] , playerNames[1] , playerNames[2] , playerNames[3]);
        this.refreshResources();
        this.refreshPlayerScores();
        this.refreshHighestArmy();
        this.refreshLongestRoad();
        playerName.setText("" + gameManager.getPlayerName());

    }
    public void playerBoxPressed(ActionEvent event) throws IOException{
        System.out.println( "player box was used");
    }

    public void givenBoxPressed(ActionEvent event) throws IOException{
        System.out.println( "given box was used");
    }

    public void wantedBoxPressed(ActionEvent event) throws IOException{
        System.out.println( "wanted box was used");
    }

    public void offerButtonPressed(ActionEvent event) throws IOException{
        System.out.println( "offer button was used");
    }

    public void playerRadioPressed(ActionEvent event) throws IOException{
        System.out.println( "player radio was used");
        playerBox.setDisable( false);
    }

    public void bankRadioPressed(ActionEvent event) throws IOException{
        System.out.println( "bank radio was used");
        playerBox.setDisable( true);
    }

    public void edgePressed(ActionEvent event) throws IOException{
        //to be implemented
        String id = ((Node)event.getSource()).getId();
        int location = Integer.parseInt(id.substring(1));
        if(gameManager.addRoad(location)) {
            ((javafx.scene.control.Button) event.getSource()).setStyle(((javafx.scene.control.Button) event.getSource()).getStyle() + " -fx-background-color: " + gameManager.returnPlayerColor());
            ((javafx.scene.control.Button) event.getSource()).setDisable(true);
            ((javafx.scene.control.Button) event.getSource()).setOpacity(0.80);
        }
        refreshResources();
        refreshPlayerScores();
        refreshLongestRoad();
    }

    private void refreshResources(){
        int[] resources = gameManager.getResources();
        grainNo.setText("" +  resources[0]);
        brickNo.setText("" +  resources[1]);
        oreNo.setText("" +  resources[2]);
        lumberNo.setText("" +  resources[3]);
        woolNo.setText("" +  resources[4]);
    }
    public void refreshDevelopmentCards(){
        int[] developments =  gameManager.getDevelopmentCards();
        KnightNo.setText("" +  developments[0]);
        VictoryNo.setText("" +  developments[1]);
        RoadNo.setText("" +  developments[2]);
        PlentyNo.setText("" +  developments[3]);
        MonoNo.setText("" +  developments[4]);

    }

    public void buyDevelopmentCard( ActionEvent event) throws IOException{
        gameManager.buyDevelopmentCard();
        refreshResources();
        refreshPlayerScores();
        refreshHighestArmy();
        refreshDevelopmentCards();
    }

    public void cornerPressed(ActionEvent event) throws IOException{
        //to be implemented
        String id = ((Node)event.getSource()).getId();
        int location = Integer.parseInt(id.substring(1));
        if ( gameManager.addSettlement(location) ) {
            ((javafx.scene.control.Button) event.getSource()).setStyle(((javafx.scene.control.Button) event.getSource()).getStyle() + " -fx-background-color: " + gameManager.returnPlayerColor());
            ((javafx.scene.control.Button) event.getSource()).setDisable(true);
            ((javafx.scene.control.Button) event.getSource()).setOpacity(0.80);
        }
        refreshResources();
        refreshPlayerScores();
    }
    public void endTurn(ActionEvent event) throws IOException{
        gameManager.nextTurn();
        refreshResources();
        refreshPlayerScores();
        refreshHighestArmy();
        refreshLongestRoad();
        refreshDevelopmentCards();
        playerName.setText("" + gameManager.getPlayerName());
        RollNo.setText("" + gameManager.getTurnDice());
    }

    public void refreshPlayerScores()
    {
        int[] playerScores = gameManager.getScoreBoard();
        p1Score.setText("" + playerScores[0]);
        p2Score.setText("" + playerScores[1]);
        p3Score.setText("" + playerScores[2]);
        p4Score.setText("" + playerScores[3]);
    }


    public void refreshHighestArmy()
    {
        int army = gameManager.largestArmy();
        p1Army.setText("");
        p2Army.setText("");
        p3Army.setText("");
        p4Army.setText("");
        if(army==0){
            p1Army.setText("*");
        }
        else if(army==1){
            p2Army.setText("*");
        }
        else if(army==2){
            p3Army.setText("*");
        }
        else if(army==3){
            p4Army.setText("*");
        }

    }

    public void refreshLongestRoad()
    {
        int road = gameManager.longestRoad();
        p1Road.setText("");
        p2Road.setText("");
        p3Road.setText("");
        p4Road.setText("");
        if(road==0){
            p1Road.setText("*");
        }
        else if(road==1){
            p2Road.setText("*");
        }
        else if(road==2){
            p3Road.setText("*");
        }
        else if(road==3){
            p4Road.setText("*");
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
    public void playerCardType()
    {
        if(playCardType.getValue().equals(playCardGrain))
        {

        }
        else if(playCardType.getValue().equals(playCardBrick))
        {

        }
        else if(playCardType.getValue().equals(playCardOre))
        {

        }
        else if(playCardType.getValue().equals(playCardLumber))
        {

        }
        else if(playCardType.getValue().equals(playCardWool))
        {

        }
    }
    public void playerCard()
    {
        if(playCardNo.getValue().equals(Monopoly))
        {

        }
        else if(playCardNo.getValue().equals(Knight))
        {

        }
        else if(playCardNo.getValue().equals(RoadBuilding))
        {

        }
        else if(playCardNo.getValue().equals(YearOfPlenty))
        {

        }
    }
    public void tradeWithPlayer()
    {
        if(playerBox.getValue().equals(Player1Trade))
        {

        }
        else if(playerBox.getValue().equals(Player2Trade))
        {

        }
        else if(playerBox.getValue().equals(Player3Trade))
        {

        }
        else if(playerBox.getValue().equals(Player4Trade))
        {

        }
    }
    public void tradeMaterial()
    {
        if(givenBox.getValue().equals(grainTrade))
        {

        }
        else if(givenBox.getValue().equals(brickTrade))
        {

        }
        else if(givenBox.getValue().equals(oreTrade))
        {

        }
        else if(givenBox.getValue().equals(lumberTrade))
        {

        }
        else if(givenBox.getValue().equals(woolTrade))
        {

        }
    }
    public void wantedMaterial()
    {
        if(wantedBox.getValue().equals(grainWanted))
        {

        }
        else if(wantedBox.getValue().equals(brickWanted))
        {

        }
        else if(wantedBox.getValue().equals(oreWanted))
        {

        }
        else if(wantedBox.getValue().equals(lumberWanted))
        {

        }
        else if(wantedBox.getValue().equals(woolWanted))
        {

        }
    }
    public void playCardType()
    {
        if(playCardType.getValue().equals(grainWanted))
        {

        }
        else if(playCardType.getValue().equals(brickWanted))
        {

        }
        else if(playCardType.getValue().equals(oreWanted))
        {

        }
        else if(playCardType.getValue().equals(lumberWanted))
        {

        }
        else if(playCardType.getValue().equals(woolWanted))
        {

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        primaryStage.setTitle("Settlers of Catan");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

    public void GameOverPopUp() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Game Over"));
        Scene dialogScene = new Scene(dialogVbox, 100, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private ObservableValue<? extends String> textValueProperty() {
        return textValueProperty();
    }
    private final StringProperty textValue = new SimpleStringProperty("waiting for input");

}

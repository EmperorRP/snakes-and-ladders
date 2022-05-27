package com.ap.final_project;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller  {
    //dice
    @FXML
    private ImageView diceImage;

    Image dice1 = new Image("dice1.png");
    Image dice2 = new Image("dice2.png");
    Image dice3 = new Image("dice3.png");
    Image dice4 = new Image("dice4.png");
    Image dice5 = new Image("dice5.png");
    Image dice6 = new Image("dice6.png");
    List<Image> images = Arrays.asList(dice1, dice2, dice3, dice4, dice5, dice6);

    private Scene scene;
    private Stage stage;
    private Parent root;

    public void homeToGame(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("home.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private Button p1_won;

    @FXML
    private Button p2_won;


    @FXML
    private Button gPLAY;

    @FXML
    private Button gEXIT;

    @FXML
    private AnchorPane homePane;

    @FXML
    private Button HO;

    public void exitGame(ActionEvent e) {
        System.exit(0);
    }

    //run initialize only for Main.fxml

    @FXML
    private Button P1;

    @FXML
    private Button P2;

    Random random = new Random();


    @FXML
    private Circle c1;
    private double x1;
    private double y1;

    @FXML
    private Circle c2;
    private double x2;
    private double y2;

    @FXML
    private Text congoText;

    private int roll(Button theButton) {
        int rolledFace = random.nextInt(6) + 1;
        System.out.println("Dice " + rolledFace);
        theButton.setDisable(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(diceImage.imageProperty(), dice1)),
                new KeyFrame(Duration.millis(50), new KeyValue(diceImage.imageProperty(), dice2)),
                new KeyFrame(Duration.millis(100), new KeyValue(diceImage.imageProperty(), dice3)),
                new KeyFrame(Duration.millis(150), new KeyValue(diceImage.imageProperty(), dice4)),
                new KeyFrame(Duration.millis(200), new KeyValue(diceImage.imageProperty(), dice5)),
                new KeyFrame(Duration.millis(250), new KeyValue(diceImage.imageProperty(), dice6))
        );
        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> {
            diceImage.setImage(images.get(rolledFace - 1));
            theButton.setDisable(false);
        });
        timeline.play();
        return rolledFace;
    }
        //Player Movement
    public void player1Move(ActionEvent e) throws IOException {

        int rolledFace = roll(P1);
        player1.diceRoll(rolledFace);

        Dictionary<Integer, Integer> dictionary = new Hashtable<>();
        dictionary.put(2,38);
        dictionary.put(4,14);
        dictionary.put(9,31);
        dictionary.put(33,85);
        dictionary.put(51,11);
        dictionary.put(52,88);
        dictionary.put(56,15);
        dictionary.put(62,57);
        dictionary.put(80,99);
        dictionary.put(92,53);
        dictionary.put(98,8);

        if((player1.end<100)) {
            //player1.end = dictionary.get(player1.end);
            x1 = valueX(player1.end);
            y1 = valueY(player1.end);
            c1.setLayoutX(x1);
            c1.setLayoutY(y1);
        }
        if (((Hashtable<Integer, Integer>) dictionary).containsKey(player1.end)){
            player1.end = dictionary.get(player1.end);
            x1 = valueX(player1.end);
            y1 = valueY(player1.end);
            c1.setLayoutX(x1);
            c1.setLayoutY(y1);
        }

        if(player1.end == 100){
            //use sceneBuilder to display something
            x1 = valueX(100);
            y1 = valueY(100);
            c1.setLayoutX(x1);
            c1.setLayoutY(y1);
            System.out.println("Congrats Player 1");
            P1.setVisible(false);
            P2.setVisible(false);
            p1_won.setVisible(true);
        }

        P2.setVisible(true);
        P1.setVisible(false);

    }



    public void player2Move(ActionEvent e) throws IOException {
        int rolledFace = roll(P2);
        player2.diceRoll(rolledFace);

        Dictionary<Integer, Integer> dictionary = new Hashtable<>();
        dictionary.put(2,38);
        dictionary.put(4,14);
        dictionary.put(9,31);
        dictionary.put(33,85);
        dictionary.put(51,11);
        dictionary.put(52,88);
        dictionary.put(56,15);
        dictionary.put(62,57);
        dictionary.put(80,99);
        dictionary.put(92,53);
        dictionary.put(98,8);

        if((player2.end<100)) {
            //player1.end = dictionary.get(player1.end);
            x2 = valueX(player2.end);
            y2 = valueY(player2.end);
            c2.setLayoutX(x2);
            c2.setLayoutY(y2);
        }
        if (((Hashtable<Integer, Integer>) dictionary).containsKey(player2.end)){
            player2.end = dictionary.get(player2.end);
            x2 = valueX(player2.end);
            y2 = valueY(player2.end);
            c2.setLayoutX(x2);
            c2.setLayoutY(y2);
        }



        if(player2.end == 100){
            //use sceneBuilder to display something
            x2 = valueX(100);
            y2 = valueY(100);
            c2.setLayoutX(x2);
            c2.setLayoutY(y2);
            System.out.println("Congrats Player 2");
            P1.setVisible(false);
            P2.setVisible(false);
            p2_won.setVisible(true);
            //congratsScene();
        }

        P1.setVisible(true);
        P2.setVisible(false);
    }


    public double valueX(int i) {
        int j = i % 10;
        if((11 <= i) && (i < 20) || (31 <= i) && (i < 40)  || (51 <= i) && (i < 60) || (71 <= i) && (i < 80) || (91 <= i) && (i < 100)){
            j = 460 - 40*j;
        }
        else{
            if((1 <= i) && (i < 10) || (21 <= i) && (i < 30)  || (41 <= i) && (i < 50) || (61 <= i) && (i < 70) || (81 <= i) && (i < 90)){
                j = 20 + j*40;
            }
            else {
                if(i%20 == 0){
                    j = 60;
                }
                else{
                    j = 420;
                }
            }
        }
        return j;
    }

    public double valueY(int i) {
        int j = i / 10;
        if(i%10 == 0){
            j = 460-40*j;
        }
        else{j = 420 - 40 * j;}
        return j;
    }




    class Player{
        private int start;
        private int end;

        Player(){
            start = 0;
            end = 0;
        }
        public void diceRoll(int x){
            if(x==1){
                this.start =1;
            }
            if(this.start == 1) {
                if (this.end <= 100){
                    this.end = this.end + x;
                }
                //this.start = this.end;
            }
        }

        //getter for start
        public int getStart(){
            return this.start;
        }

        //getter for end
        public int getEnd(){
            return this.end;
        }

        //setter for start
        public void setStart(int x){
            this.start = x;
        }
        //setter for end
        public void setEnd(int x){
            this.end = x;
        }
    }
    Player player1 = new Player();
    Player player2 = new Player();
}


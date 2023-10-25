package me.jord.rebuilt2048;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TitleScene extends Scene{
	
	//Constructor: Receives the window/stage from the Main class to change scenes when the game begins.
	//Also includes the logo, Start Game button, styling, and event registration for the start button.
	public TitleScene(Stage primaryStage) {	//primaryStage should be passed in from the main class
		super(new BorderPane(), 280, 350);	//Width: 280; Height: 350
		primaryStage.getIcons().add(new Image("https://gabrielecirulli.github.io/2048/meta/og_image.png"));	//Sets application icon to the original 2048 icon.
		Parent temp = this.getRoot();										//Stores the BorderPane initialized in the call to super()
		BorderPane title = (BorderPane)temp;
		
		//Initializing and styling the 2048 Text.
		Text logo = new Text("2048");
		logo.setFont(new Font("Franklin Gothic Medium", 40));
		logo.setFill(Color.web("#e68400"));
		logo.setStroke(Color.BLACK);
		logo.setStrokeWidth(0.5);
		
		//Initializing and styling the Rebuilt Text.
		Text rebuilt = new Text("r e b u i l t");
		rebuilt.setFont(new Font("Franklin Gothic Medium", 40));
		rebuilt.setStyle("-fx-font-weight: bold; -fx-text-transform: lowercase;");
		rebuilt.setStroke(Color.BLACK);
		rebuilt.setFill(Color.RED);

		//Credit Text: Currently Disabled.
		//Label credit = new Label("By: Jord");
		//credit.setStyle("-fx-font-size: 13px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		//HBox creditBox = new HBox(5, credit);
		//creditBox.setAlignment(Pos.BOTTOM_CENTER);
		
		//Grouping the 2048 and Rebuilt Texts.
		VBox label = new VBox(8, logo, rebuilt);
		label.setAlignment(Pos.TOP_CENTER);
		label.setPadding(new Insets(75, 0, 0, 0));
		
		//Initializing and styling the Start Game button.
		Button startGame = new Button("Start Game");
		startGame.setPrefHeight(40);
		startGame.setPrefWidth(110);
		startGame.setStyle("-fx-background-color:"
							+ "linear-gradient(#ffd65b, #e68400),"
							+ "linear-gradient(#ffef84, #f2ba44),"
							+ "linear-gradient(#ffea6a, #efaa22),"
							+ "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),"
							+ "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
							+ "-fx-background-radius: 30; -fx-background-insets: 0,1,2,3,0; -fx-text-fill: #654b00;"
							+ "-fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10 20 10 20;");
		
		//Setting the Title Menu's BorderPane top to the Logo (2048 rebuilt), and then the center will contain the start game button.
		title.setTop(label);
		title.setCenter(startGame);
		//title.setBottom(creditBox); //Credits are currently disabled.
		title.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)"); //Background gradient color.
		
		//Registering the Start Game button to activate the next scene.
		startGame.setOnAction(e -> {
			GameScene game = new GameScene(new GameObject()); //The GameObject is stored as a private data member of the GameScene.
			//game.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //Currently unused.
			primaryStage.setScene(game);	//Change the window to display the game.
		});
		
		//When hovering over the Start Game button, the button increases in size.
		startGame.setOnMouseEntered(e -> {
			startGame.setPrefHeight(46);
			startGame.setPrefWidth(122);
			startGame.setStyle("-fx-background-color:"
					+ "linear-gradient(#ffd65b, #e68400),"
					+ "linear-gradient(#ffef84, #f2ba44),"
					+ "linear-gradient(#ffea6a, #efaa22),"
					+ "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),"
					+ "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
					+ "-fx-background-radius: 30; -fx-background-insets: 0,1,2,3,0; -fx-text-fill: #654b00;"
					+ "-fx-font-weight: bold; -fx-font-size: 14.5px; -fx-padding: 10 20 10 20;");
		});
		
		//When the mouse exits from hovering over the Start Game button, the button returns to its original size.
		startGame.setOnMouseExited(e -> {
			startGame.setPrefHeight(40);
			startGame.setPrefWidth(110);
			startGame.setStyle("-fx-background-color:"
								+ "linear-gradient(#ffd65b, #e68400),"
								+ "linear-gradient(#ffef84, #f2ba44),"
								+ "linear-gradient(#ffea6a, #efaa22),"
								+ "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),"
								+ "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
								+ "-fx-background-radius: 30; -fx-background-insets: 0,1,2,3,0; -fx-text-fill: #654b00;"
								+ "-fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10 20 10 20;");
		});
		
//Instructions Window: Currently disabled.
/*		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
					case I:
						Stage instructions = new Stage();
						BorderPane iPane = new BorderPane();
						Scene scene = new Scene(iPane, 280, 350);
						instructions.setScene(scene);
						instructions.setTitle("2048 Instructions");
						instructions.show();
						break;
					default:
						break;
				}
			}
		});
*/
	}
}
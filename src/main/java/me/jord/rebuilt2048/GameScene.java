package me.jord.rebuilt2048;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class GameScene extends Scene{
	private GameObject game;
	
	//Constructor: Calls Scene constructor and places the GameObject BorderPane on it. Stores the game object for any KeyEvents that should shift
	//the 2048 board left, right, up, or down.
	public GameScene(GameObject game) {
		super(game, 280, 350); //Window Width: 280; Window Height: 350
		this.game = game;
		game.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)"); //Background gradient in-game
		
		//Keyboard key pressed event handler
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) { //Switch statement that performs a shift based on the keyboard key that was pressed
					case UP:
					case W:
						game.moveUp();		//Moves up if 'W' or the Up arrow key is pressed.
						break;
					case DOWN:
					case S:
						game.moveDown();	//Moves down if 'S' or the Down arrow key is pressed.
						break;
					case LEFT:
					case A:
						game.moveLeft();	//Moves left if 'A' or the Left arrow key is pressed.
						break;
					case RIGHT:
					case D:
						game.moveRight();	//Moves right if 'D' or the Right arrow key is pressed.
						break;
					default:
						break;
				}
			}
		});
	}
}
package me.jord.rebuilt2048;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class GameObject extends BorderPane{
	private Board board;
	
	//Constructor: Initializes a 2048 board/GridPane and sets it on the BorderPane
	public GameObject(){
		board = new Board();
		setLeft(board);
		board.setAlignment(Pos.CENTER);	//Align the GridPane to the center of the screen
		setPadding(new Insets(0, 0, 0, 20));	//Pad the right side of the board
	}
	
	//Methods that call the board's move/shift functions (used to perform shifts using the keyboard from anywhere in the scene)
	public void moveLeft() {
		board.moveLeft();
	}
	public void moveRight() {
		board.moveRight();
	}
	public void moveUp() {
		board.moveUp();
	}
	public void moveDown() {
		board.moveDown();
	}
}
package me.jord.rebuilt2048;

import java.util.Random;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends StackPane{ //Contains the tile and the number value on top. Excludes the actual grid cell.
	private final static int TILE_SIZE = 40;
	private final static int ARC_SIZE = 8;
	private int value;		//Value displayed on top of the StackPane
	private boolean empty; //Currently unused.
	private Rectangle box; //Tile (the one that changes colors based on value, not the grid cell)
	private Text showVal;  //String version of value
	private Paint color;   //Color of the tile (based on value)
	
	//Default constructor: Creates a filled tile with a value of either 2 or 4. Styles the color, shape, and size of the tile.
	public Tile() {
		empty = false; //Currently using value==0 to determine if empty.
		
		//Randomly selecting whether the Tile will contain a 2 (90% chance) or a 4 (10% chance).
		Random rand = new Random();
		int start = rand.nextInt(10);
		if (start<9) {
			value = 2;
		} else {
			value = 4;
		}
		
		//Initializing the Tile box (not the grid cell)
		box = new Rectangle(TILE_SIZE, TILE_SIZE);
		box.setStroke(Color.BLACK);
		if(value==2) {
			box.setFill(Color.rgb(250, 231, 224));
		} else {
			box.setFill(Color.rgb(246,231,202));
		}
		box.setFill(Color.rgb(250, 231, 224));
		box.setArcHeight(ARC_SIZE);
		box.setArcWidth(ARC_SIZE);
		
		showVal = new Text(Integer.toString(value));	//Converts the value to a Text object
		getChildren().addAll(box, showVal);	//Adds the tile box and the value to the StackPane
	}
	
	//Constructor: Receives an argument - if empty==true, an empty(value of 0) and invisible Tile is created. Otherwise, a Tile is created the
	//same way as in the default constructor.
	public Tile(boolean empty) {
		if(empty) {
			empty = true;
			value = 0;
			color = Color.BLACK;
			box = new Rectangle(TILE_SIZE, TILE_SIZE);
			box.setVisible(false);
			box.setArcHeight(ARC_SIZE);
			box.setArcWidth(ARC_SIZE);
			
			showVal = new Text(Integer.toString(value));
			showVal.setVisible(false);
			getChildren().addAll(box, showVal);
			
		} else {
			empty = false;
			Random rand = new Random();
			int start = rand.nextInt(10);
			if (start<9) {
				value = 2;
			} else {
				value = 4;
			}
			box = new Rectangle(TILE_SIZE, TILE_SIZE);
			box.setStroke(Color.BLACK);
			if(value==2) {
				box.setFill(Color.rgb(250, 231, 224));
			} else {
				box.setFill(Color.rgb(246,231,202));
			}
			box.setArcHeight(ARC_SIZE);
			box.setArcWidth(ARC_SIZE);
			
			showVal = new Text(Integer.toString(value));
			getChildren().addAll(box, showVal);
		}
	}
	
	//Turns a Tile that was previously empty and invisible and assigns it a 2 or a 4. Then turns it and the newly assigned value to be visible.
	public void fillEmptyTile() {
		empty = false;
		Random rand = new Random();
		int start = rand.nextInt(10);
		if (start<9) {
			value = 2;
		} else {
			value = 4;
		}
		box.setStroke(Color.BLACK);
		if(value==2) {
			box.setFill(Color.rgb(250, 231, 224));
		} else {
			box.setFill(Color.rgb(246,231,202));
		}
		box.setVisible(true);
		showVal.setText(Integer.toString(value));
		showVal.setVisible(true);
	}
	
	//This function is useful for moves and merges. The Tile that was moved or merged into a different Tile is erased and set back to value of 0.
	public void eraseVisibleTile() {
		empty = true;
		value = 0;
		color = Color.BLACK;
		box.setVisible(false);
		showVal.setVisible(false);
	}
	
	//Returns the value stored by the Tile. Useful for board shifts and finding where to move or merge a Tile.
	public int getVal() {
		return value;
	}
	
	//This function is useful for moves (but not merges). It changes the target Tile to the Tile that is being moved there.
	public void changeVal(int newVal) {
		value = newVal;
		box.setStroke(Color.BLACK);
		
		//This switch statement checks the new value of the Tile and assigns it the Tile color associated with that value in the real 2048.
		switch(value) {
			case 2:
				color = Color.rgb(250, 231, 224);
				box.setFill(color);
				break;
			case 4:
				color = Color.rgb(246,231,202);
				box.setFill(color);
				break;
			case 8:
				color = Color.rgb(254,177,123);
				box.setFill(color);
				break;
			case 16:
				color = Color.rgb(246,148,100);
				box.setFill(color);
				break;
			case 32:
				color = Color.rgb(244,125,95);
				box.setFill(color);
				break;
			case 64:
				color = Color.rgb(255,91,66);
				box.setFill(color);
				break;
			case 128:
				color = Color.rgb(245,208,119);
				box.setFill(color);
				break;
			case 256:
				color = Color.rgb(249,208,103);
				box.setFill(color);
				break;
			case 512:
				color = Color.rgb(249,202,88);
				box.setFill(color);
				break;
			case 1024:
				color = Color.rgb(236,196,64);
				box.setFill(color);
				break;
			case 2048:
				color = Color.rgb(247,200,43);
				box.setFill(color);
				break;
			case 4096:
				color = Color.rgb(249,100,114);
				box.setFill(color);
				break;
			case 8192:
				color = Color.rgb(241,75,97);
				box.setFill(color);
				break;
			case 16384:
				color = Color.rgb(235,66,61);
				box.setFill(color);
				break;
			case 32768:
				color = Color.rgb(116,181,221);
				box.setFill(color);
				break;
			case 65536:
				color = Color.rgb(93,160,228);
				box.setFill(color);
				break;
			case 131072:
				color = Color.rgb(2,125,192);
				box.setFill(color);
				break;
			default:
				color = Color.BLACK;
				box.setFill(Color.BLACK);
				break;
		}
		if(value!=0) {
			box.setVisible(true); //Ensures the Tile and Text value are visible if they were previously an empty Tile.
			showVal.setText(Integer.toString(value)); //Changes the Text value before displaying it
			showVal.setVisible(true);				  //Display the updated Tile value
		}
	}
	
	//This function is useful for merges (not moves). It doubles the target Tile.
	public void timesTwo() {
		value*=2;	//Doubling the value of the calling Tile
		
		////This switch statement checks the new value of the Tile and assigns it the Tile color associated with that value in the real 2048.
		switch(value) {
		case 2:
			color = Color.rgb(250, 231, 224);
			box.setFill(color);
			break;
		case 4:
			color = Color.rgb(246,231,202);
			box.setFill(color);
			break;
		case 8:
			color = Color.rgb(254,177,123);
			box.setFill(color);
			break;
		case 16:
			color = Color.rgb(246,148,100);
			box.setFill(color);
			break;
		case 32:
			color = Color.rgb(244,125,95);
			box.setFill(color);
			break;
		case 64:
			color = Color.rgb(255,91,66);
			box.setFill(color);
			break;
		case 128:
			color = Color.rgb(245,208,119);
			box.setFill(color);
			break;
		case 256:
			color = Color.rgb(249,208,103);
			box.setFill(color);
			break;
		case 512:
			color = Color.rgb(249,202,88);
			box.setFill(color);
			break;
		case 1024:
			color = Color.rgb(236,196,64);
			box.setFill(color);
			break;
		case 2048:
			color = Color.rgb(247,200,43);
			box.setFill(color);
			break;
		case 4096:
			color = Color.rgb(249,100,114);
			box.setFill(color);
			break;
		case 8192:
			color = Color.rgb(241,75,97);
			box.setFill(color);
			break;
		case 16384:
			color = Color.rgb(235,66,61);
			box.setFill(color);
			break;
		case 32768:
			color = Color.rgb(116,181,221);
			box.setFill(color);
			break;
		case 65536:
			color = Color.rgb(93,160,228);
			box.setFill(color);
			break;
		case 131072:
			color = Color.rgb(2,125,192);
			box.setFill(color);
			break;
		default:
			color = Color.BLACK;
			box.setFill(Color.BLACK);
			break;
		}
		showVal.setText(Integer.toString(value));	//Updates the Text value of the recently doubled Tile value
	}
	
	//Returns true or false based on if the value of the Tile is 0 or not.
	public boolean isEmpty() {
		return (value==0);
	}
}
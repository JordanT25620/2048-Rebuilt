//Driver file for Jord's 2048 Rebuilt.
//~723 lines of code.
package me.jord.rebuilt2048;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		TitleScene title = new TitleScene(primaryStage); //Create title screen and menu
		primaryStage.setScene(title);					
		primaryStage.setTitle("2048 Rebuilt");			//Set window title
		primaryStage.show();							//Display title screen
	}
	public static void main(String[] args) {
		launch(args);
	}
}
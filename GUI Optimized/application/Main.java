package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import loot.Inventory;
import map.MapSetup;
import map.Position;
import unit.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public class Main extends Application implements EventHandler<ActionEvent> {
	
	static Stage window;
	
	public void start(Stage primaryStage)throws Exception{
		window = primaryStage;
		Scene start = SceneChange.getTitleScene(primaryStage);
		window.setScene(start);
		window.show();
	}
	
	public static void pickUpItemWindow(GridPane grid, Inventory pot, Position p, Rectangle cell, Player player) {
		Stage pickup = new Stage();
		window = pickup;
		VBox root = new VBox();
        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        Label message;
        
        message = new Label("Pick up item?");
        
        yesBtn.setOnAction(new EventHandler<ActionEvent>() // Make into separate method
		   {@Override
		   	public void handle(ActionEvent event)
		   	{
		   		message.setText("The item will be added to your inventory.");
			   	pot.inventory.put(pot.getImage(), pot.getLootStats());
			   	continueBtn(message);
			   	cell.setFill(MapSetup.emptyImg);
			   	pickup.close();
		   	}});

        noBtn.setOnAction(new EventHandler<ActionEvent>()
		   {@Override
		   	public void handle(ActionEvent event)
		   	{
			   message.setText("The item will disappear.");
			   continueBtn(message);
			   cell.setFill(MapSetup.emptyImg);
			   pickup.close();
		   	}});
        
      root.setAlignment(Pos.CENTER);  
      root.getChildren().add(message);
      root.getChildren().add(yesBtn);
      root.getChildren().add(noBtn);
	  Scene scene = new Scene(root, 300, 100);
	  
	  pickup.setScene(scene);
	  pickup.showAndWait();
	}
	
	public static void continueBtn(Label message) {
		Stage contWindow = new Stage();
		window = contWindow;
		VBox root = new VBox();
		Button continueBtn = new Button("Continue");
		continueBtn.setOnAction(new EventHandler<ActionEvent>()
		   {@Override
		   	public void handle(ActionEvent event)
		   	{
			   	contWindow.close();
		   	}});
		
		root.setAlignment(Pos.CENTER);  
		root.getChildren().add(message);
		root.getChildren().add(continueBtn);
		Scene scene = new Scene(root, 300, 100);
  	  
		contWindow.setScene(scene);
		contWindow.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		return;
	}
}
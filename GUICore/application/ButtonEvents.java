package application;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import loot.Inventory;
import map.MapSetup;

import java.util.List;

/**
 * A class that with methods specific to button handling
 * @author Bonnie's Computer
 *
 */
public class ButtonEvents {

	/**
	 * Method handling when player is looting item
	 * 
	 * @param event ActionEvent when player presses the button
	 * @param inventoryKey int for the key of the item in the inventory
	 * @param message Label of the message displayed to player about loot
	 * @param cell Rectangle where the loot is 
	 * @param pickup A stage to loot items
	 */
    public static void lootItemButton(ActionEvent event, int inventoryKey, Label message, Rectangle cell, Stage pickup) {
        Button actionBtn = (Button)event.getSource();

        if (actionBtn.equals("No")) {
            Inventory.inventory.remove(inventoryKey);
        }

        continueBtn(message);
        cell.setFill(MapSetup.EMPTY_IMG);
        pickup.close();
    }

    /**
     * Method handling when player clicks the continue button and displays the message passed to method
     * 
     * @param message Label of the message displayed to the player 
     */
    public static void continueBtn(Label message) {
        Stage contWindow = new Stage();
        VBox root = new VBox();
        Button continueBtn = new Button("Continue");
        continueBtn.setOnAction(event -> contWindow.close());

        root.setAlignment(Pos.CENTER);
        root.getChildren().add(message);
        root.getChildren().add(continueBtn);
        Scene scene = new Scene(root, 330, 100);

        contWindow.setScene(scene);
        contWindow.showAndWait();
    }

    /**
     * Method to create a button
     * 
     * @param translateX int for the translation for the button along x axis
     * @param translateY int for the translation for the button along y axis
     * @param buttonName String for the text on the button
     * @return A new button created with passed parameters
     */
    public static Button createButton(int translateX, int translateY, String buttonName) {
        Button button = new Button();
        button.setText(buttonName);
        button.setTranslateX(translateX);
        button.setTranslateY(translateY);

        return button;
    }

    /**
     * Method to add a Button to a VBox
     * @param root VBox of where to add the button
     * @param buttonList List of Buttons of all the buttons that need to be added to the VBox
     */
    public static void addButtonToBox(VBox root, List<Button> buttonList) {
        for (Button button : buttonList) {
            root.getChildren().add(button);
        }
    }
    
    /**
     * Method to create an exit button with the word "End"
     * 
     * @param translateX int for the translation for the button along x axis
     * @param translateY int for the translation for the button along y axis
     * @return A new exit button created with passed parameters
     */
    public static Button exitButton(int translateX, int translateY) {
        Button end = createButton(translateX, translateY, "End");
        end.setOnAction(e -> System.exit(1));

        return end;
    }

    /**
     * Method that creates a button to play again
     * 
     * @param stage Stage where the button displays on
     * @param translateX int for the translation for the button along x axis
     * @param translateY int for the translation for the button along y axis
     * @return A button to ask player if they want to play again
     */
    public static Button playButton(Stage stage, int translateX, int translateY) {
        Button play = createButton(translateX, translateY, "Play Again");

        play.setOnAction(e ->{
            Stage newStage = new Stage();
            SceneChange.level =1;
            stage.close();
            SceneChange.setMorality(newStage);
        });

        return play;
    }
}

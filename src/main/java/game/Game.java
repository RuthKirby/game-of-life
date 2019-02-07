package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.InvalidParameterException;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Sets up the elements that make up the game.
 * @author Ruth Bovell
 */
public class Game extends Application {

    private Button btn;
    private Grid grid;
    private VBox container;
    private Label iterLabel;
    private Parameters params;
    private int numberOfCells;
    private ScrollPane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game of Life");
        iterLabel = new Label("Iteration: 1");
        root = new ScrollPane();
        btn = new Button();
        grid = new Grid();
        container = new VBox();
        params = getParameters();

        setUpGrid();
        setUpBtn();
        setUpContainer();
        setUpRoot();

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    /**
     * Event action - button click updates grid with new state
     */
    private void iterBtnClick() {
        btn.setDisable(true);
        grid.update();
        iterLabel.setText("Iteration: " + grid.getIterNum());
        btn.setDisable(false);
    }

    /**
     * Sets up the game's grid. Uses default number if program argument is not found or is less
     * than zero.
     */
    private void setUpGrid() throws InvalidParameterException{
        List<String> list = params.getRaw();
        try {
            numberOfCells = parseInt(list.get(0));
            if (numberOfCells < 0) {
                throw new InvalidParameterException();
            }
        }

        catch (InvalidParameterException | IndexOutOfBoundsException ex) {
            if (ex instanceof InvalidParameterException) {
                System.err.println("Number of cells must be a positive integer. Using default number (3)");
                numberOfCells = 3;
            }

            else if (ex instanceof IndexOutOfBoundsException) {
                System.err.println("No arguments found: Using default number (3)");
                numberOfCells = 3;
            }
        }

        grid.prepareGrid();
        grid.seedInitialTiles(numberOfCells);
    }

    /**
     * Sets up a container that is used to hold the grid, button and iteration number
     * and centers them in window.
     */
    public void setUpContainer() {
        container.getChildren().addAll(grid, btn, iterLabel);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(5);
    }

    /**
     * Sets up the button that is used to get the next iteration of the grid.
     */
    public void setUpBtn() {
        btn.setText("Next Iteration");
        btn.setAlignment(Pos.CENTER);
        btn.setOnAction(e -> iterBtnClick());
    }

    /**
     * Sets up the container that holds all other elements in the game window.
     */
    public void setUpRoot() {
        root.setContent(container);
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setHvalue(0.5);
        root.setVvalue(0.5);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

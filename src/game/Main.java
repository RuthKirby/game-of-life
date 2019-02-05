package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sets up and launches application for the game.
 */
public class Main extends Application {

    Button btn;
    Grid grid;
    VBox vBox;
    Label iterLabel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game of Life");
        iterLabel = new Label("Iteration: 1");
        btn = new Button();
        grid = new Grid();
        vBox = new VBox();

        grid.prepareGrid();
        grid.seedInitialTiles(100);
        vBox.getChildren().addAll(grid, btn, iterLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);


        btn.setText("Next Iteration");
        btn.setAlignment(Pos.CENTER);
        btn.setOnAction(e -> iterBtnClick());
        //BorderPane root = new BorderPane();
        ScrollPane root = new ScrollPane();
        //root.setCenter(vBox);
        root.setContent(vBox);
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setHvalue(0.5);
        root.setVvalue(0.5);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    /**
     * Event action - button click updates grid with new state
     */
    public void iterBtnClick() {
        btn.setDisable(true);
        //grid.getNextIteration();
        grid.update();
        iterLabel.setText("Iteration: " + grid.getIterNum());
        btn.setDisable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

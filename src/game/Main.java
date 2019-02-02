package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Button btn;
    Grid grid;
    VBox vBox;
    Label iterLabel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Game of Life");
        iterLabel = new Label("Iteration: 1");
        btn = new Button();
        grid = new Grid();
        vBox = new VBox();

        grid.prepareGrid();
        grid.seedInitialTiles(3);
        vBox.getChildren().addAll(grid, btn, iterLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);


        btn.setText("Next Iteration");
        btn.setAlignment(Pos.CENTER);
        btn.setOnAction(e -> buttonClick());
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public void buttonClick() {
        btn.setDisable(true);
        grid.getNextIteration();
        grid.update();
        iterLabel.setText("Iteration: " + grid.getIterNum());
        btn.setDisable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

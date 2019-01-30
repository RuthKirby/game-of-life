package game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

public class Tile extends StackPane {

    Rectangle border = new Rectangle(30, 30);

    public Tile (boolean hasCell){

        border.setFill(null);
        border.setStroke(BLACK);
        getChildren().add(border);
        if (hasCell == true) {
            Cell cell = new Cell();
            getChildren().add(cell);
        }

        setAlignment(Pos.CENTER);

    }
}

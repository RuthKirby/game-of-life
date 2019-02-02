package game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

public class Tile extends StackPane {

    private Rectangle border;
    private Cell cell;
    private boolean hasCell;
    private int neighWithCell;

    public Tile (boolean hasCell){
        border = new Rectangle(30, 30);
        neighWithCell = 0;
        cell = new Cell();
        this.hasCell = hasCell;
        createTile();

    }

    private void createTile() {
        border.setFill(null);
        border.setStroke(BLACK);
        getChildren().add(border);
        if (hasCell == true) {
            getChildren().add(cell);
        }

        setAlignment(Pos.CENTER);
    }

    public void setNeighWithCell(int neighWithCell) {
        this.neighWithCell = neighWithCell;
    }

    public boolean hasCell() {
        return hasCell;
    }

    public int getNeighWithCell() {
        return neighWithCell;
    }

    public void setHasCell(boolean hasCell) {
        if (hasCell) {
            if (!getChildren().contains(cell)) {
                getChildren().add(cell);
            }
        }

        else if (!hasCell){
            if (getChildren().contains(cell)) {
                getChildren().remove(cell);
            }
        }
        this.hasCell = hasCell;
    }

}

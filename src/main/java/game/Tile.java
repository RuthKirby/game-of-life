package game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

/**
 * Extends the JavaFX class StackPane in order to provide the tiles that make up the grid. Tiles
 * can be blank or can contain cells.
 * @author Ruth Bovell
 */
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

    /**
     * Sets up the visual aspects of the tile. If it contains a live cell this is added.
     */
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

    /**
     * Adds a cell to tile if parameter is true and
     * removes cell from tile if false. Then set's hasCell variable.
     * @param hasCell boolean - whether or not the tile should have a cell
     */
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
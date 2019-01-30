package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Grid extends GridPane {
    ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
    public Grid () {
        prepareGrid();
        add(new Tile(true), 0, 1);
        add(new Tile(false), 0, 0);
        add(new Tile(true), 1, 0);
        add(new Tile(false), 1, 1);
    }

    public Grid (int numberOfCells) {
        prepareGrid();
        int numrows = numberOfCells + 1;
        int numColumns = numberOfCells + 1;

        for (int i = 1; i < numrows; i++) {
            for (int y = 1; y < numColumns; y++) {

            }
        }

        while (numberOfCells != 0) {
            for (int i = 1; i < numrows; i++) {
                for (int y = 1; y < numColumns; y++) {

                }
            }
        }

        for (int i = 0; i < numrows; i++) {
            for (int y = 0; y < numColumns; y++) {
                add(new Tile(false), i, y);
            }
        }

    }

    /**
     * Sets up the visual aspects of the grid: spacing, alignment and gap between columns and rows
     */
    private void prepareGrid() {
        setPadding(new Insets(10, 10, 10, 10));
        setAlignment(Pos.CENTER);
        setVgap(1);
        setHgap(1);
    }

}

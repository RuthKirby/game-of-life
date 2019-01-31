package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Grid extends GridPane {
    //ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
    Tile[][] Tiles;
    int numRows;
    int numColumns;

    public Grid () {
        //prepareGrid();
        //numRows = numberOfCells + 1;
        //numColumns = numberOfCells + 1;
    }

    /**
     * Sets up the visual aspects of the grid: spacing, alignment and gap between columns and rows
     */
    public void prepareGrid() {
        setPadding(new Insets(10, 10, 10, 10));
        setAlignment(Pos.CENTER);
        setVgap(1);
        setHgap(1);
    }

    /**
     * Puts tiles into an array then adds them to the grid.
     * Tiles with live cells are added one after the other apart from
     * the first/last row/column
     * @param numberOfCells int of tiles that have live cells
     */
    public void seedInitialTiles(int numberOfCells) {
        numColumns = (int) Math.ceil(Math.sqrt((double)numberOfCells)) + 2;
        numRows = (int) Math.ceil(Math.sqrt((double)numberOfCells)) + 2;
        Tiles = new Tile[numRows][numColumns];
        int k = numberOfCells;
        System.out.println(k);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 || i == numRows - 1 || j == 0 || j == numColumns - 1) { //makes sure top/bottom row, left/right column have no live cells
                    Tiles[i][j] = new Tile(false);
                }

                else if (k > 0) {
                    Tiles[i][j] = new Tile(true);
                    k--;
                }

                else {
                    Tiles[i][j] = new Tile(false);
                }

            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                add(Tiles[i][j], i, j);
            }
        }
    }
}

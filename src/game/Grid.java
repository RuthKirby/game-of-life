package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Grid extends GridPane {
    //ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
    Tile[][] tiles;
    int numRows;
    int numColumns;
    boolean hasEnoughCells;
    int iteration;
    public Grid () {
        iteration = 1;
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
     * tiles with live cells are added one after the other apart from
     * the first/last row/column
     * @param numberOfCells int of tiles that have live cells
     */
    public void seedInitialTiles(int numberOfCells) {
        numColumns = (int) Math.ceil(Math.sqrt((double)numberOfCells)) + 2;
        numRows = (int) Math.ceil(Math.sqrt((double)numberOfCells)) + 2;
        tiles = new Tile[numRows][numColumns];
        int k = numberOfCells;
        if (k > 2) {
            hasEnoughCells = true;
        }
        else {
            hasEnoughCells = false;
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 || i == numRows - 1 || j == 0 || j == numColumns - 1) { //makes sure top/bottom row, left/right column have no live cells
                    tiles[i][j] = new Tile(false);
                }

                else if (k > 0) {
                    tiles[i][j] = new Tile(true);
                    k--;
                }

                else {
                    tiles[i][j] = new Tile(false);
                }

            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                add(tiles[i][j], j, i); //grid coordinates are (column, row)
            }
        }
    }

    public void getNextIteration(){
        iteration++;
        
        int tilesWithCells = 0;
        Tile[][] tempTiles = tiles; //to hold current iteration state in order to calculate live cell neighbours
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                int neighWithCell = getCellNeighNo(i, j, tempTiles);
                TileScenario tileScenario = TileScenario.getScenario(neighWithCell, tiles[i][j].hasCell());
                switch (tileScenario) {
                    case CREATION: tiles[i][j].setHasCell(true); tilesWithCells++; break;
                    case OVERCROWDED: tiles[i][j].setHasCell(false); break;
                    case UNDERPOPULATED: tiles[i][j].setHasCell(false); break;
                    case SURVIVAL: tilesWithCells++; break;
                    case STAY_BLANK: break;
                }
            }
        }

        if (tilesWithCells == 0) {

        }

    }

    public int getCellNeighNo (int row, int column, Tile[][] tiles) {
        int neighWithCell = 0;
        int rowMax = tiles.length;
        int colMax = tiles[0].length;
        int [][] eightNeigh = {{ -1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        for (int i = 0; i < eightNeigh.length; i++) {
            if ((row + eightNeigh[i][0] > 0 && row + eightNeigh[i][0] < rowMax)
                    && (column + eightNeigh[i][1] > 0 && column + eightNeigh[i][1] < colMax)) { //check for out of grid
                if (tiles[row + eightNeigh[i][0]][column + eightNeigh[i][1]].hasCell()) {
                    neighWithCell++;
                }
            }
        }

        return neighWithCell;
    }

    public enum TileScenario {

        UNDERPOPULATED(),
        OVERCROWDED(),
        SURVIVAL(),
        CREATION(),
        STAY_BLANK();


        public static TileScenario getScenario(int neighWithCell, boolean hasCell) {
            if (neighWithCell < 2 && hasCell == true) {
                return UNDERPOPULATED;
            }

            else if ((neighWithCell == 2 || neighWithCell == 3) && hasCell == true) {
                return SURVIVAL;
            }

            else if (neighWithCell > 3 && hasCell == true) {
                return OVERCROWDED;
            }

            else if (neighWithCell == 3 && hasCell == false) {
                return CREATION;
            }

            else {
                return STAY_BLANK;
            }

        }
    }

}

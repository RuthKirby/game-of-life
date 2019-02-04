package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Grid extends GridPane {
    int numRows;
    int numColumns;
    boolean hasLiveCellTile;
    int iteration;
    boolean expandGrid;
    ArrayList<ArrayList<Tile>> tiles;

    public Grid () {
        iteration = 1;
        numRows = 0;
        numColumns = 0;
        hasLiveCellTile = false;
        expandGrid = false;
        tiles = new ArrayList<>();
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
        numColumns = (int) Math.ceil(Math.sqrt((double)numberOfCells)) + 3;
        numRows = (int) Math.ceil(Math.sqrt((double)numberOfCells)) + 3;
        System.out.println(tiles.size());
        int k = numberOfCells;
        for (int i = 0; i < numRows; i++) {
            tiles.add(new ArrayList<Tile>());
            for (int j = 0; j < numColumns; j++) {
                System.out.println(tiles.size());
                if (i < 2 || i > numRows - 2 || j == 0 || j == numColumns - 1) { //makes sure top/bottom two rows, left/right column have no live cells
                    tiles.get(i).add(j, new Tile(false));
                }

                else if (k > 0) {
                    tiles.get(i).add(j, new Tile(true));
                    k--;
                }

                else {
                    tiles.get(i).add(j, new Tile(false));
                }
            }
        }

        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                tiles.get(i).get(j).setNeighWithCell(getCellNeighNo(i, j));
            }
        }

        addTilesToGrid();
    }

    public void update() {
        getNextIteration();
        getChildren().clear();
        addTilesToGrid();
    }

    public void getNextIteration(){

        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                TileScenario tileScenario = TileScenario.getScenario(tiles.get(i).get(j).getNeighWithCell(), tiles.get(i).get(j).hasCell());

                switch (tileScenario) {
                    case CREATION: tiles.get(i).get(j).setHasCell(true);
                        if (i == 0 || j == 0 || i == tiles.size() - 1 || j == tiles.size() - 1) {
                            expandGrid = true;
                        }
                        break;
                    case OVERCROWDED: tiles.get(i).get(j).setHasCell(false); break;
                    case UNDERPOPULATED: tiles.get(i).get(j).setHasCell(false); break;
                    case SURVIVAL: tiles.get(i).get(j).setHasCell(true); break;
                    case STAY_BLANK: tiles.get(i).get(j).setHasCell(false); break;
                }
            }
        }

        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                int cellNeighNum = getCellNeighNo(i, j);
                tiles.get(i).get(j).setNeighWithCell(cellNeighNum);
            }
        }

        if (expandGrid) {
            expandGridEdge();
            expandGrid = false;
        }
        System.out.println(tiles.size());
        iteration++;
    }

    public int getCellNeighNo (int row, int column) {
        int neighWithCell = 0;
        int rowMax = tiles.size();
        int colMax = tiles.get(0).size();
        int [][] eightNeigh = {{ -1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        for (int i = 0; i < eightNeigh.length; i++) {
            if ((row + eightNeigh[i][0] >= 0 && row + eightNeigh[i][0] < rowMax)
                    && (column + eightNeigh[i][1] >= 0 && column + eightNeigh[i][1] < colMax)) { //check for out of grid
                if (tiles.get(row + eightNeigh[i][0]).get(column + eightNeigh[i][1]).hasCell()) {
                    neighWithCell++;
                }
            }
        }

        return neighWithCell;
    }

    private void addTilesToGrid() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                add(tiles.get(i).get(j), j, i); //grid coordinates are (column, row)
            }
        }
    }

    private void expandGridEdge () {
        tiles.add(0, new ArrayList<>()); //Adds new row to top of grid
        tiles.add(tiles.size(), new ArrayList<>()); //Adds new row to bottom of grid
        for (int i = 0; i < tiles.size(); i++) {
            if (i > 0){
                tiles.get(i).add(0, new Tile(false)); //Adds new blank column on the left
                tiles.get(i).add(new Tile(false)); //Adds new blank column to the right
            }
            for (int j = 0; j < tiles.size(); j++) {
                if (i == 0 || i == tiles.size() - 1) { //populate newly added top & bottom row
                    tiles.get(i).add(j, new Tile(false));
                }
            }
        }

        numRows = tiles.size();
        numColumns = tiles.get(0).size();

    }

    public int getIterNum() {
        return iteration;
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

package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    @DisplayName("Grid must be created with expected padding and alignment")
    void prepareGrid() {
        Grid grid = new Grid();
        grid.prepareGrid();
        assertEquals(10, grid.getInsets().getBottom());
        assertEquals(10, grid.getInsets().getTop());
        assertEquals(10, grid.getInsets().getLeft());
        assertEquals(10, grid.getInsets().getRight());
        assertEquals("CENTER", grid.getAlignment().toString());
        assertEquals(1, grid.getHgap());
        assertEquals(1, grid.getVgap());
    }

    @Test
    @DisplayName("Grid must be seeded with non-negative numbers only. Grid cannot be seeded twice.")
    void seedInitialTiles() {
        Grid grid = new Grid();
        assertThrows(InvalidParameterException.class, () -> grid.seedInitialTiles(-1));

        Grid gridSeedTwice = new Grid();
        gridSeedTwice.seedInitialTiles(3);
        assertThrows(UnsupportedOperationException.class, () -> gridSeedTwice.seedInitialTiles(10));
    }

    @Test
    @DisplayName("If grid has 0 tiles with cells then next iteration after update should still have zero tiles with cells")
    void update() {
        Grid grid = new Grid();
        grid.seedInitialTiles(0);
        grid.update();
        boolean hasCell = false;
        for (int i = 0; i < grid.getTiles().size(); i++) {
            for(int j = 0; j < grid.getTiles().get(0).size(); j++) {

                if(grid.getTiles().get(i).get(j).hasCell()) {
                    hasCell = true;
                }
            }
        }
        assertFalse(hasCell);
    }
}
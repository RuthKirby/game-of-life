package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    @DisplayName("Tiles should only have 0-8 neighbours with cells. Negative integers and integers over 8 must throw exception")
    void setNeighWithCell() {
        Tile tile = new Tile(true);
        InvalidParameterException eneg = assertThrows(InvalidParameterException.class, () -> {tile.setNeighWithCell(-1);});
        InvalidParameterException ehigh = assertThrows(InvalidParameterException.class, () -> {tile.setNeighWithCell(9);});
        assertEquals(eneg.getMessage(), -1 + " is not a valid input. The number of neighbours a tile has" + " must be between 0 and 8");
        assertEquals(ehigh.getMessage(), 9 + " is not a valid input. The number of neighbours a tile has" + " must be between 0 and 8");
    }

    @Test
    @DisplayName("Setting whether or not a tile should have a sell should add or remove Cell Object from tile")
    void setHasCell() {
        Tile tile = new Tile(true);
        tile.setHasCell(false);
        assertFalse(tile.getChildren().contains(tile.getCell()));
        assertFalse(tile.hasCell());

        Tile tile1 = new Tile(false);
        tile1.setHasCell(true);
        assertTrue(tile1.getChildren().contains(tile1.getCell()));
        assertTrue(tile1.hasCell());
    }
}
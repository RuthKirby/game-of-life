package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class TileScenarioTest {

    @Test
    @DisplayName("Inputs that are not valid scenarios should throw exception. Valid scenarios should return" +
            " correct enum item.")
    void getScenario() {
        assertThrows(InvalidParameterException.class, () -> TileScenario.getScenario(-1, false));
        assertThrows(InvalidParameterException.class, () -> TileScenario.getScenario(9, true));

        //When a tile with a cell has fewer than two neighbours with cells it should return underpopulated.
        assertEquals(TileScenario.UNDERPOPULATED, TileScenario.getScenario(1, true));

        //When a tile with a cell has more than three neighbours it should return overcrowded.
        assertEquals(TileScenario.OVERCROWDED, TileScenario.getScenario(5, true));

        //When a tile with a cell has two or three neighbours with a cell it should return survival.
        assertEquals(TileScenario.SURVIVAL, TileScenario.getScenario(2, true));
        assertEquals(TileScenario.SURVIVAL, TileScenario.getScenario(3, true));


        //When a tile without a cell has exactly three neighbours with a cell it should return creation.
        assertEquals(TileScenario.CREATION, TileScenario.getScenario(3, false));

        //When a tile without a cell doesn't have exactly three neighbours with a cell ut should return blank.
        assertEquals(TileScenario.STAY_BLANK, TileScenario.getScenario(2, false));
    }
}
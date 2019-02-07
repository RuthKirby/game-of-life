package game;

import java.security.InvalidParameterException;

/**
 * Enum storing the scenarios that can change a tile's state.
 */
public enum TileScenario {

    UNDERPOPULATED(),
    OVERCROWDED(),
    SURVIVAL(),
    CREATION(),
    STAY_BLANK();

    /**
     * Retrieves enum item that represents the valid scenarios that
     * can apply to a tile.
     * @param neighWithCell int neighbouring tiles that contain cells
     * @param hasCell boolean whether the tile has cell
     * @return enum item
     */
    public static TileScenario getScenario(int neighWithCell, boolean hasCell) throws InvalidParameterException{
        if(neighWithCell < 0 || neighWithCell > 8) {
            throw new InvalidParameterException(String.format("Parameters %d and %b do not result in a valid scenario.", neighWithCell, hasCell));
        }
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

        else if (neighWithCell != 3 && hasCell == false){
            return STAY_BLANK;
        }

        else {
            throw new InvalidParameterException(String.format("Parameters %d and %b do not result in a valid scenario.", neighWithCell, hasCell));
        }

    }
}

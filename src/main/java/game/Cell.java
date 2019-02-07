package game;

import javafx.scene.shape.Circle;

import static javafx.scene.paint.Color.BLACK;

/**
 *Extends the JavaFX Circle class in order to represent a live cell
 * @author Ruth Bovell
 */
public class Cell extends Circle{
    public Cell (){
        setRadius(5);
        setFill(BLACK);
    }
}

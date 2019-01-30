package game;

import javafx.scene.shape.Circle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

public class Cell extends Circle{
    public Cell (){
        setStroke(RED);
        setRadius(5);
        setFill(BLACK);
    }
}

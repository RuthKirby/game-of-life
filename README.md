# Game of Life

Infinite 2D grid of cells.

## Running the .jar

There is an executable version in the app folder. This can be 
launched from the command line:
`java -jar GameOfLife.jar`

If the command is followed by a non-negative integer it will
use the argument as the number of cells to seed the grid with e.g.
`java -jar GameOfLife.jar 12` will seed the grid with 12 live 
cells. If something other than a non-negative integer is used then the 
grid is seeded with a default number (3).

## Decisions
* Use of ArrayList - To achieve a dynamic structure 
for grid that could grow when needed.
* Cells are all next to each other leaving a blank edge so that
cell creation can be determined. 

## Built with

* [Maven](https://maven.apache.org/) - Management of dependencies
* [JavaFX](https://openjfx.io/) - GUI